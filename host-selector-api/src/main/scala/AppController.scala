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
  def process(input: DockerCompose): ToResponseMarshallable = {
    val selector = new DockerHostSelector()
    val resultCPU = selector.readCPUFromPrometheus()
    selector.HostSelection(input, new PrometheusReadings(resultCPU, resultCPU),List[JobMapping]())
  }
}
