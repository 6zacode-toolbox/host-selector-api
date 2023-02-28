
import org.scalatest.wordspec.AnyWordSpec



class TestDockerHostSelector extends AnyWordSpec {
  val dockerHostSelector = new DockerHostSelector

  import PrometheusDataProtocol._
  import spray.json._


  "A Prometheus Data String conversion " should {
    "return a expected object" in {
      val sampleData = SampleConversions.sampleSimplePrometheusData
      val sampleConverted = sampleData.parseJson.convertTo[PrometheusData]
      assert(SampleConversions.samplePrometheusDataObject == sampleConverted)

    }
  }

  "A Metric String conversion " should {
    "return a expected object" in {
      val sampleData = SamplePrometheusData.metricSample
      val sampleConverted = sampleData.parseJson.convertTo[Metric]
      assert(sampleConverted.name == "CPU_Temperature")
      assert(sampleConverted.instance == "10.10.10.100:9100")
      assert(sampleConverted.job == "node-exporter-pi-100")

    }
  }
  "A Prometheus Payload " should {
    "be converted without issues" in {

      val prometheusObject = SamplePrometheusData.prometheusTempData.parseJson.convertTo[PrometheusData]
      assert(prometheusObject.getClass.getName == "PrometheusData")
      assert(prometheusObject.data.result.length == 2)
      assert(prometheusObject.data.result(1).values.length == 40)

    }
  }
  "A dockerHostSelector" should {
    "return a result" in {
      val readings = new PrometheusReadings(cpu = SamplePrometheusData.prometheusTempData.parseJson.convertTo[PrometheusData], container = SamplePrometheusData.prometheusTempData.parseJson.convertTo[PrometheusData])
      assert(dockerHostSelector.HostSelection(new DockerCompose("teste", "test"), readings) == "node-exporter-pi-101")
      assert(true)
    }
  }
}
