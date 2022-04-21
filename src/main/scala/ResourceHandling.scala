import zio.{ExitCode, IO, Task, URIO, ZIO}

import scala.io.{BufferedSource, Source}

object ResourceHandling extends zio.App {
  def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = {
    (for {
      value <- operation
      _ <- zio.console.putStrLn(value.toString)
    } yield ()).exitCode

  }

  val fileReader: Task[BufferedSource] = IO(Source.fromFile("/home/knoldus/IdeaProjects/Zio-Learning/hello"))

  val fileCloser = (source: Source) => URIO(source.close())

  val operation: ZIO[Any, Throwable, Int] = fileReader.bracket(fileCloser) { file: BufferedSource =>
    IO(wordCounter(file))
  }

  def wordCounter(file: BufferedSource): Int = {
    file
      .getLines
      .flatMap(_.split(" "))
      .length

  }

  //val list = List("/home/knoldus/IdeaProjects/Zio-Learning/hello", "/home/knoldus/IdeaProjects/Zio-Learning/Knoldus", "/home/knoldus/IdeaProjects/Zio-Learning/Scala")
  //val instanceOfFile: Seq[Task[BufferedSource]] = list.map(str => IO(Source.fromFile(str)))


}
