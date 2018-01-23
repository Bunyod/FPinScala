import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object Testing2 extends App {

  val talk = Seq(
    Future {
      Thread.sleep(1000)
      "я"
    },
    Future.failed(new RuntimeException(".")),
    Future.successful("немного"),
    Future.failed(new RuntimeException("..")),
    Future.successful("заикаюсь"),
    Future.failed(new RuntimeException("..."))
  )

  val rawRes = Future.sequence(talk.map { r =>
    r.map(Success(_)).recover { case ex: Throwable=>
      Failure(ex)
    }
  }).map(_.span(_.isSuccess))

}

/**
  *  На вход Seq[Future[String]]
  *  Получить Future[(Seq[String], Seq[Throwable]) -
  *  результат агрегации выполненых Future и исключений
  */

