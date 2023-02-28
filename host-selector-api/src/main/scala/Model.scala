sealed trait Action

case class DockerCompose(filter: String, strategy: String) extends Action

// To be replaced by real implementation of it
case class PrometheusReadings(something: String)
