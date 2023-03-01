import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpMethods, HttpRequest}
import akka.http.scaladsl.unmarshalling.Unmarshal

import scala.concurrent.{Await, ExecutionContextExecutor}
import scala.concurrent.duration.DurationInt

/**
 * Selector of host to run payload on a cluster based on metrics
 */
class DockerHostSelector {
  val prometheusHost = scala.util.Properties.envOrElse("PROMETHEUS_HOST", "127.0.0.1:9090")
  val mappingHost = scala.util.Properties.envOrElse("SELECTOR_HOST_MAPPING", "/source/mapping.json")

  import PrometheusDataProtocol._
  import spray.json._


  /**
   * Core mechanism for selection of hosts.
   * All input as provided as parameters to make easier to test it.
   * @param input
   * @param readings
   * @param jobMappings
   * @return
   */

  def HostSelection(input:DockerCompose, readings:PrometheusReadings, jobMappings: List[JobMapping]): String = {
    val cpuMeasures = readings.cpu.data.result.map( value => {
      val result = value.values.map( _.value.toDouble).sum/value.values.length
      SummaryReading(value.metric.job, result)
    } )
    val cpuSorted = cpuMeasures.toSeq.sortBy(_.value)
    val jobMap = jobMappings.map( mapping => mapping.prometheusJob -> mapping.kubernetesHost).toMap
    val onlyKnownHosts = cpuSorted.collect { case host if jobMap.contains(host.job) => SummaryReading(host.job, host.value) }
    if (onlyKnownHosts.length > 0) {
      jobMap(onlyKnownHosts(0).job)
    } else {
      ""
    }
  }

  /**
   * Query State of Prometheus metrics, ie: "CPU_Temperature%5B10m%5D" that is the encoded version of "CPU_Temperature[10m]"
   * @param query
   * @return
   */
  def readFromPrometheus(query:String): PrometheusData = {
    implicit val system: ActorSystem = ActorSystem()
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher
    val request = HttpRequest(
      method = HttpMethods.GET,
      uri = s"http://$prometheusHost/api/v1/query?query=$query"
    )
    val responseFuture = Http().singleRequest(request)
    val timeout = 60000.millis
    val responseAsString = Await.result(
      responseFuture.flatMap(resp => Unmarshal(resp.entity).to[String]),
      timeout
    )

    val convertedData = responseAsString.parseJson.convertTo[PrometheusData]
    convertedData
  }

  /**
   * Load a list of mapping PrometheusJob to their K8s CRD alias
   * @return
   */
  def readMappingTable() = {
    val contents = io.Source.fromFile(mappingHost).mkString
    contents.parseJson.convertTo[List[JobMapping]]
  }
}
