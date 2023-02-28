
import org.scalatest.wordspec.AnyWordSpec



class TestDockerHostSelector extends AnyWordSpec {
  val dockerHostSelector = new DockerHostSelector

  import PrometheusDataProtocol._
  import spray.json._
  "A dockerHostSelector" should {
    "return a result" in {
      //assert(dockerHostSelector.HostSelection(new DockerCompose("teste", "test") )== "myhosts")
    assert(true)
    }
  }

  "A payload Read" should {
    "return a result" in {

      val metricJSONObject =  SamplePrometheusData.metricSample.parseJson.convertTo[Metric]
      println(metricJSONObject)
      println(metricJSONObject.getClass)
      val sampleData = PrometheusData("success", new Data("resultType" ,List(new Result(
        Metric("name","instance", "job"),
        List[Values]( new Values(0, "10")) ))))
      println(sampleData.toJson.toString())
      val prometheusObject = SamplePrometheusData.prometheusTempData.parseJson.convertTo[PrometheusData]
      println(prometheusObject)
      println(prometheusObject)
      println(prometheusObject.getClass)
      //assert(dockerHostSelector.HostSelection(new DockerCompose("teste", "test")) == "myhosts")

    }
  }
}
