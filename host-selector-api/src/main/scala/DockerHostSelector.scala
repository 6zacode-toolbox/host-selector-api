import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpMethods, HttpRequest}
import akka.http.scaladsl.unmarshalling.Unmarshal

import scala.concurrent.{Await, ExecutionContextExecutor}
import scala.concurrent.duration.DurationInt

class DockerHostSelector {
  val prometheusHost = scala.util.Properties.envOrElse("PROMETHEUS_HOST", "127.0.0.1:9090")
  val mappingHost = scala.util.Properties.envOrElse("SELECTOR_HOST_MAPPING", "/source/mapping.json")

  import PrometheusDataProtocol._
  import spray.json._

  def HostSelection(input:DockerCompose, readings:PrometheusReadings, jobMappings: List[JobMapping]): String = {
    val cpuMeasures = readings.cpu.data.result.map( value => {
      val result = value.values.map( _.value.toDouble).sum/value.values.length
      SummaryReading(value.metric.job, result)
    } )
    val cpuSorted = cpuMeasures.toSeq.sortBy(_.value)
    val jobMap = jobMappings.map( mapping => mapping.prometheusJob -> mapping.kubernetesHost).toMap
    if (cpuSorted.length > 0) {
      jobMap(cpuSorted(0).job)
    } else {
      ""
    }
  }

  def readCPUFromPrometheus(): PrometheusData = {
    implicit val system: ActorSystem = ActorSystem()
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher
    val request = HttpRequest(
      method = HttpMethods.GET,
      uri = s"http://$prometheusHost/api/v1/query?query=CPU_Temperature%5B10m%5D"
    )
    val responseFuture = Http().singleRequest(request)
    val timeout = 300.millis
    val responseAsString = Await.result(
      responseFuture.flatMap(resp => Unmarshal(resp.entity).to[String]),
      timeout
    )

    val convertedData = responseAsString.parseJson.convertTo[PrometheusData]
    convertedData
  }

  def readMappingTable() = {

    val contents = io.Source.fromFile(mappingHost).mkString
  }
}
