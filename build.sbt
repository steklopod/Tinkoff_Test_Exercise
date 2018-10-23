
scalaVersion := "2.12.6"

name := "Tinkoff"
version := "1.0"

resolvers ++= Seq(
  DefaultMavenRepository,
  Resolver.mavenLocal,
  Resolver.sonatypeRepo("snapshots"),
  Classpaths.typesafeReleases
)