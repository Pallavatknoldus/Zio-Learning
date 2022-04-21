import zio.ZIO
import zio.console._

import java.io.IOException

object MyApp extends zio.App {

  def run(args: List[String]) =
    addFunction.exitCode

  val succeedOne = ZIO.succeed(1)
  val succeedTwo = ZIO.succeed(2)

  val addFunction: ZIO[Any, Nothing, Int] = for {
    one <- succeedOne
    two <- succeedTwo
  } yield one + two

}