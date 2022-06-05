name := """myApp"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.11"

libraryDependencies ++= Seq(
  guice,
  "org.scalatestplus.play" %% "scalatestplus-play"   % "5.0.0" % Test,
  "mysql"                   % "mysql-connector-java" % "5.1.41",
  jdbc,
  "com.typesafe.play"      %% "play-slick"           % "3.0.3",
  "com.typesafe.slick"     %% "slick-codegen"        % "3.2.1",
  "org.scalatest"          %% "scalatest"            % "3.0.5" % Test,
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
