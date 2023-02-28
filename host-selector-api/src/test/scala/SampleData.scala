object SamplePrometheusData {
  val metricSample ="""{"__name__":"CPU_Temperature","instance":"10.10.10.100:9100","job":"node-exporter-pi-100"}"""

  val singleReadingPrometheus =
    """
      |{"metric":
      |{"__name__":"CPU_Temperature","instance":"10.10.10.100:9100","job":"node-exporter-pi-100"},
      |"values":[[1677538442.072,"35.05"],[1677538457.072,"34.56"],[1677538472.072,"34.56"],
      |[1677538487.072,"34.56"],[1677538502.072,"34.56"],[1677538517.072,"35.05"],[1677538532.072,"36.02"],
      |[1677538547.072,"36.02"],[1677538562.072,"36.02"],[1677538577.072,"34.56"],[1677538592.072,"34.56"],
      |[1677538607.072,"34.56"],[1677538622.072,"35.54"],
      |[1677538637.072,"35.05"],[1677538652.072,"35.05"],[1677538667.072,"35.05"],[1677538682.072,"34.56"],[1677538697.072,"35.05"],[1677538712.072,"35.05"],
      |[1677538727.072,"35.05"],[1677538742.072,"35.05"],[1677538757.072,"34.56"],[1677538772.072,"34.56"],[1677538787.072,"34.56"],[1677538802.072,"34.56"],
      |[1677538817.072,"34.56"],[1677538832.072,"34.56"],[1677538847.072,"34.56"],[1677538862.072,"34.56"],[1677538877.072,"35.05"],[1677538892.072,"35.05"],
      |[1677538907.072,"35.05"],[1677538922.072,"35.05"],[1677538937.072,"34.56"],[1677538952.072,"34.56"],[1677538967.072,"34.56"],[1677538982.072,"34.08"],
      |[1677538997.072,"35.54"],[1677539012.072,"35.54"],[1677539027.072,"35.54"]]
      |}
      |""".stripMargin

  val prometheusTempData =
    """
      |{
      |    "status": "success",
      |    "data": {
      |        "resultType": "matrix",
      |        "result": [
      |            {
      |                "metric": {
      |                    "__name__": "CPU_Temperature",
      |                    "instance": "10.10.10.100:9100",
      |                    "job": "node-exporter-pi-100"
      |                },
      |                "values": [
      |                    [
      |                        1677538442.072,
      |                        "35.05"
      |                    ],
      |                    [
      |                        1677538457.072,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538472.072,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538487.072,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538502.072,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538517.072,
      |                        "35.05"
      |                    ],
      |                    [
      |                        1677538532.072,
      |                        "36.02"
      |                    ],
      |                    [
      |                        1677538547.072,
      |                        "36.02"
      |                    ],
      |                    [
      |                        1677538562.072,
      |                        "36.02"
      |                    ],
      |                    [
      |                        1677538577.072,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538592.072,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538607.072,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538622.072,
      |                        "35.54"
      |                    ],
      |                    [
      |                        1677538637.072,
      |                        "35.05"
      |                    ],
      |                    [
      |                        1677538652.072,
      |                        "35.05"
      |                    ],
      |                    [
      |                        1677538667.072,
      |                        "35.05"
      |                    ],
      |                    [
      |                        1677538682.072,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538697.072,
      |                        "35.05"
      |                    ],
      |                    [
      |                        1677538712.072,
      |                        "35.05"
      |                    ],
      |                    [
      |                        1677538727.072,
      |                        "35.05"
      |                    ],
      |                    [
      |                        1677538742.072,
      |                        "35.05"
      |                    ],
      |                    [
      |                        1677538757.072,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538772.072,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538787.072,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538802.072,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538817.072,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538832.072,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538847.072,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538862.072,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538877.072,
      |                        "35.05"
      |                    ],
      |                    [
      |                        1677538892.072,
      |                        "35.05"
      |                    ],
      |                    [
      |                        1677538907.072,
      |                        "35.05"
      |                    ],
      |                    [
      |                        1677538922.072,
      |                        "35.05"
      |                    ],
      |                    [
      |                        1677538937.072,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538952.072,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538967.072,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538982.072,
      |                        "34.08"
      |                    ],
      |                    [
      |                        1677538997.072,
      |                        "35.54"
      |                    ],
      |                    [
      |                        1677539012.072,
      |                        "35.54"
      |                    ],
      |                    [
      |                        1677539027.072,
      |                        "35.54"
      |                    ]
      |                ]
      |            },
      |            {
      |                "metric": {
      |                    "__name__": "CPU_Temperature",
      |                    "instance": "10.10.10.101:9100",
      |                    "job": "node-exporter-pi-101"
      |                },
      |                "values": [
      |                    [
      |                        1677538438.087,
      |                        "34.08"
      |                    ],
      |                    [
      |                        1677538453.087,
      |                        "34.08"
      |                    ],
      |                    [
      |                        1677538468.087,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538483.087,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538498.087,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538513.087,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538528.087,
      |                        "34.08"
      |                    ],
      |                    [
      |                        1677538543.087,
      |                        "34.08"
      |                    ],
      |                    [
      |                        1677538558.087,
      |                        "34.08"
      |                    ],
      |                    [
      |                        1677538573.087,
      |                        "34.08"
      |                    ],
      |                    [
      |                        1677538588.087,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538603.087,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538618.087,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538633.087,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538648.087,
      |                        "34.08"
      |                    ],
      |                    [
      |                        1677538663.087,
      |                        "34.08"
      |                    ],
      |                    [
      |                        1677538678.087,
      |                        "34.08"
      |                    ],
      |                    [
      |                        1677538693.087,
      |                        "34.08"
      |                    ],
      |                    [
      |                        1677538708.087,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538723.087,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538738.087,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538753.087,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677538768.087,
      |                        "35.05"
      |                    ],
      |                    [
      |                        1677538783.087,
      |                        "35.05"
      |                    ],
      |                    [
      |                        1677538798.087,
      |                        "35.05"
      |                    ],
      |                    [
      |                        1677538813.087,
      |                        "35.05"
      |                    ],
      |                    [
      |                        1677538828.087,
      |                        "33.1"
      |                    ],
      |                    [
      |                        1677538843.087,
      |                        "33.1"
      |                    ],
      |                    [
      |                        1677538858.087,
      |                        "33.1"
      |                    ],
      |                    [
      |                        1677538873.087,
      |                        "33.1"
      |                    ],
      |                    [
      |                        1677538888.087,
      |                        "33.59"
      |                    ],
      |                    [
      |                        1677538903.087,
      |                        "33.59"
      |                    ],
      |                    [
      |                        1677538918.087,
      |                        "33.59"
      |                    ],
      |                    [
      |                        1677538933.087,
      |                        "33.59"
      |                    ],
      |                    [
      |                        1677538948.087,
      |                        "33.1"
      |                    ],
      |                    [
      |                        1677538963.087,
      |                        "33.1"
      |                    ],
      |                    [
      |                        1677538978.087,
      |                        "33.1"
      |                    ],
      |                    [
      |                        1677538993.087,
      |                        "33.1"
      |                    ],
      |                    [
      |                        1677539008.087,
      |                        "34.56"
      |                    ],
      |                    [
      |                        1677539023.087,
      |                        "34.56"
      |                    ]
      |                ]
      |            }
      |
      |        ]
      |    }
      |}
      |""".stripMargin

}
