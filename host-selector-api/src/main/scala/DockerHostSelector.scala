class DockerHostSelector {
  def HostSelection(input:DockerCompose, readings:PrometheusReadings): String = {
    val cpuMeasures = readings.cpu.data.result.map( value => {
      val result = value.values.map( _.value.toDouble).sum/value.values.length
      SummaryReading(value.metric.job, result)
    } )
    val cpuSorted = cpuMeasures.toSeq.sortBy(_.value)
    if (cpuSorted.length > 0) {
      cpuSorted(0).job
    } else {
      ""
    }
  }
}
