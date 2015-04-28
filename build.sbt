import AssemblyKeys._

assemblySettings

name := "SimpleSparkApp"

version := "1.0"

scalaVersion := "2.11.6"

//assemblyOption in assembly ~= { _.copy(includeScala = false) }


libraryDependencies += "org.apache.spark" %% "spark-core" % "1.3.1" % "provided"

libraryDependencies +=  "org.scalatest" % "scalatest_2.10.0-M4" % "1.9-2.10.0-M4-B1"

libraryDependencies +=  "junit" % "junit" % "4.8.1" % "test"

libraryDependencies += "org.apache.spark" %% "spark-mllib" % "1.3.1"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "1.3.1"

libraryDependencies += "org.apache.spark" %% "spark-streaming" % "1.3.1"

libraryDependencies += "org.apache.spark" %% "spark-streaming-twitter" % "1.3.1"

libraryDependencies += "com.google.code.gson" % "gson" % "2.3"

libraryDependencies += "org.twitter4j" % "twitter4j-core" % "3.0.3"

libraryDependencies += "commons-cli" % "commons-cli" % "1.2"

libraryDependencies += "com.typesafe" % "config" % "0.4.0"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.2"

resolvers += "Akka Repository" at "http://repo.akka.io/releases/"

// Configure JAR used with the assembly plug-in
jarName in assembly := "my-project-assembly.jar"

assemblyOption in assembly :=
  (assemblyOption in assembly).value.copy(includeScala = false)

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) => {
  case PathList(ps@_*) if ps.last endsWith ".RSA" => MergeStrategy.first
  case PathList("META-INF", "mailcap") => MergeStrategy.first
  case PathList("javax", "servlet", xs@_*) => MergeStrategy.first
  case PathList(ps@_*) if ps.last endsWith ".html" => MergeStrategy.first
  case PathList(p @ _*) if p.last endsWith "UnusedStubClass.class" => MergeStrategy.first
  case "application.conf" => MergeStrategy.concat
  case "unwanted.txt" => MergeStrategy.discard
  case PathList("unused", "UnusedStubClass.class") => MergeStrategy.discard
  case PathList("META-INF", "maven", "io.netty", "netty", xs@_*) => MergeStrategy.first
  case PathList("org", "jboss", "netty", xs@_*) => MergeStrategy.first
  case PathList("META-INF", "maven", "org.apache.curator", "curator-client", xs@_*) => MergeStrategy.first
  case PathList("META-INF", "maven", "org.apache.curator", "curator-framework", xs@_*) => MergeStrategy.first
  case PathList("META-INF", "maven", "org.apache.httpcomponents", "httpclient", xs@_*) => MergeStrategy.first
  case PathList("META-INF", "maven", "org.apache.httpcomponents", "httpcore", xs@_*) => MergeStrategy.first
  case PathList("META-INF", "mimetypes.default") => MergeStrategy.first
  case PathList("com", "esotericsoftware", "minlog", "Log$Logger.class") => MergeStrategy.first
  case PathList("com", "esotericsoftware", "minlog", "Log.class") => MergeStrategy.first
  case PathList("com", "google", "common", "base", xs@_*) => MergeStrategy.first
  case PathList("javax", "servlet", xs@_*) => MergeStrategy.first
  case PathList("javax", "activation", xs@_*) => MergeStrategy.first
  case PathList("org", "apache", "commons", "beanutils", xs@_*) => MergeStrategy.first
  case PathList("org", "apache", "commons", "collections", xs@_*) => MergeStrategy.first
  case PathList("org", "apache", "commons", "logging", xs@_*) => MergeStrategy.first
  case PathList("org", "apache", "log4j", xs@_*) => MergeStrategy.last
  case PathList("org", "apache", "jute", xs@_*) => MergeStrategy.first
  case PathList("org", "slf4j", "impl", xs@_*) => MergeStrategy.first
  case PathList("META-INF", "maven", "org.apache.avro", "avro-ipc", xs@_*) => MergeStrategy.first
  case PathList("META-INF", "maven", "org.slf4j", "slf4j-api", xs@_*) => MergeStrategy.first
  case PathList("org", "apache", "hadoop", "yarn", xs@_*) => MergeStrategy.first
  case x if x.startsWith("plugin.properties") => MergeStrategy.last
  case x if x.startsWith("project.clj") => MergeStrategy.last
  case x => old(x)
}
}

fork in Test := true // allow to apply extra setting to Test

javaOptions in Test += "-Dconfig.resource=test.conf" // apply extra setting here
