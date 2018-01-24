import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scala.concurrent.duration.DurationInt
import scala.io.Source

object Testing2 extends App {

//  val talk = Seq(
//    Future {
//      Thread.sleep(1000)
//      "asdfa"
//    },
//    Future.failed(new RuntimeException(".")),
//    Future.successful("asdf"),
//    Future.failed(new RuntimeException("..")),
//    Future.successful("asdf"),
//    Future.failed(new RuntimeException("..."))
//  )
//
//  val rawRes = Future.sequence(talk.map { r =>
//    r.map(Success(_)).recover { case ex: Throwable=>
//      Failure(ex)
//    }
//  }).map(_.span(_.isSuccess))

//  val failedSuccess = Future("something went wrong")
//
//  failedSuccess.onComplete {
//    case Success(s) =>
//      println("success")
//    case Failure(e) =>
//      println("failure")
//  }

//  val f1 =  Future(Source.fromFile("asdf").getLines()
//    .map(_.trim)
//    .filter(line => !line.startsWith("#") && !line.isEmpty)
//    .to[Set])

  val f3 =  Future.successful(Source.fromFile("asdf").getLines()
    .map(_.trim)
    .filter(line => !line.startsWith("#") && !line.isEmpty)
    .to[Set])


  val f2 = f3 andThen {
    case Success(v) =>
      Thread.sleep(1000)
      println("The program waited patiently for this callback to finish.")
    case Failure(e) =>
      println("failed")
      println(e)
  }

  Await.ready(f3, 2.seconds)
  println("F1 is COMPLETED")
  Await.ready(f2, 2.seconds)
  println("F2 is COMPLETED")
//  println(Await.result(failedSuccess, 2.seconds))

}


// Future.successful doesn't create new thread
// if something goes wrong inside of Future.successfull, to handle of its result is impossible