package com.linkedin.spark.datasources.tfrecord

private[tfrecord] object TFRecordOptions {
  def getOrElse(options: Map[String, String], key: String, default: String): String =
    options.get(key).orElse {
      options.collectFirst {
        case (optionKey, value) if optionKey.equalsIgnoreCase(key) => value
      }
    }.getOrElse(default)
}
