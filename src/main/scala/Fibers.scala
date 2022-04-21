import zio.{ExitCode, URIO, ZEnv, ZIO}

object Fibers extends zio.App {

  val task1 = ZIO.succeed("10")
  val task2 = ZIO.succeed("12")
  val task3 = ZIO.succeed("3")

  def printThread = s"[${Thread.currentThread().getName}]"

  def asynchronousRoutine() = for {
    _ <- task1.debug(printThread).fork
    _ <- task2.debug(printThread).fork
    _ <- task3.debug(printThread)
  } yield ()

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = asynchronousRoutine().exitCode
}

/*

def synchronousRoutine() = for {
    _ <- task1.debug(printThread)
    _ <- task2.debug(printThread)
    _ <- task3.debug(printThread)
  } yield ()
*/