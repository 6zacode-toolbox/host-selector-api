import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat
import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.model._
import akka.http.scaladsl.unmarshalling.Unmarshal

import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, ExecutionContextExecutor, Future}
import scala.util.{Failure, Success}

class AppController {
  implicit val dcFormat: RootJsonFormat[DockerCompose] = jsonFormat2(DockerCompose.apply)

  val prometheusHost = scala.util.Properties.envOrElse("PROMETHEUS_HOST", "127.0.0.1:9090" )
  import PrometheusDataProtocol._
  import spray.json._

  def process(input: DockerCompose): ToResponseMarshallable = {

    val resultCPU = readCPUFromPrometheus()
    val selector = new DockerHostSelector()

    selector.HostSelection(input, new PrometheusReadings(resultCPU, resultCPU))
  }

  private def readCPUFromPrometheus(): PrometheusData = {
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
}
