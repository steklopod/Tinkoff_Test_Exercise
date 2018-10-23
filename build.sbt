
scalaVersion := "2.12.6"

name := "Tinkoff"
version := "1.0"
//libraryDependencies += "org.typelevel" %% "cats-core" % "1.4.0"

resolvers ++= Seq(
  DefaultMavenRepository,
  Resolver.mavenLocal,
  Resolver.sonatypeRepo("snapshots"),
  Classpaths.typesafeReleases
)