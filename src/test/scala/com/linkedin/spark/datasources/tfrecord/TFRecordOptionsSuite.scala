package com.linkedin.spark.datasources.tfrecord

import org.scalatest.{FlatSpec, Matchers}

class TFRecordOptionsSuite extends FlatSpec with Matchers {
  "TFRecordOptions" should "resolve option keys case-insensitively" in {
    TFRecordOptions.getOrElse(
      Map("recordType" -> "ByteArray"), "recordType", "Example") should be ("ByteArray")
    TFRecordOptions.getOrElse(
      Map("recordtype" -> "ByteArray"), "recordType", "Example") should be ("ByteArray")
    TFRecordOptions.getOrElse(
      Map("RECORDTYPE" -> "ByteArray"), "recordType", "Example") should be ("ByteArray")
  }

  it should "prefer the exact key when duplicate casings are present" in {
    TFRecordOptions.getOrElse(
      Map("recordType" -> "ByteArray", "RECORDTYPE" -> "Example"),
      "recordType",
      "SequenceExample") should be ("ByteArray")
  }

  it should "return the default when an option is absent" in {
    TFRecordOptions.getOrElse(Map.empty, "recordType", "Example") should be ("Example")
  }
}
