import PrometheusDataProtocol.jsonFormat
import spray.json.{DefaultJsonProtocol, JsArray, JsNumber, JsObject, JsString, JsValue, JsonFormat, deserializationError}

case class Metric (name: String,instance: String,job: String)
case class Data ( resultType: String, result: Seq[Result]          )
case class Result (        metric: Metric,                   values: Seq[Values]             )

case class PrometheusData ( status: String, data: Data )

case class Values (epoch: Double,value: String)

import spray.json._
object PrometheusDataProtocol extends DefaultJsonProtocol {


  implicit val MetricProtocol: JsonFormat[Metric] = new JsonFormat[Metric] {
    def read(json: JsValue) = {
      json.asJsObject.getFields("__name__", "instance", "job") match {
        case Seq(JsString(name), JsString(instance), JsString(job)) => Metric(name, instance, job)
        case _ => deserializationError("Expected fields: 'name' (JSON string) and 'instance' (JSON string)")
      }
    }

    def write(obj: Metric) = JsObject("__name__" -> JsString(obj.name), "instance" -> JsString(obj.instance), "job" -> JsString(obj.job))
  }

  implicit val ValuesProtocol: JsonFormat[Values] = new JsonFormat[Values] {
    def read(json: JsValue) = {
      json match {
        case y: JsArray => Values(y.elements(0).convertTo[Double],y.elements(1).convertTo[String])
        case _ => deserializationError("Expected fields: 'name' (JSON string) and 'instance' (JSON string)")
        //  case List[JsNumber(epoch), JsString(value)] => Values(epoch.doubleValue, value)
      }

    }

    def write(obj: Values) = JsArray( JsNumber(obj.epoch),JsString(obj.value))

  }
  implicit val formatterResult = jsonFormat(Result, "metric", "values")
  implicit val formatterData = jsonFormat(Data, "resultType", "result")
  implicit val formatterPrometheusData = jsonFormat(PrometheusData, "status", "data")
}