name := "elastic-search-examples"

version := "0.1"

scalaVersion := "2.13.2"


libraryDependencies ++= Seq(

  //elastic search
  "org.elasticsearch" % "elasticsearch" % "7.7.1",

  //rest high level client
  "org.elasticsearch.client" % "elasticsearch-rest-high-level-client" % "7.7.1",

  //play json
  "com.typesafe.play" %% "play-json" % "2.9.0",

  // https://mvnrepository.com/artifact/org.scalatest/scalatest
  "org.scalatest" %% "scalatest" % "3.2.0-M2" % Test

)