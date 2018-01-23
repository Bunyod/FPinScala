//package testing
//
//import akka.pattern.ask
//import akka.actor.SupervisorStrategy._
//import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, OneForOneStrategy, Props}
//import akka.util.Timeout
//
//import scala.concurrent.duration.DurationInt
//import scala.concurrent.ExecutionContext.Implicits.global
//
///**
//  * Created by bunyod on 8/3/17.
//  */
//object Supervision extends App {
//
//
//  implicit val duration = Timeout(20.seconds)
//
//  override def main(args: Array[String]) {
//    val actorSystem = ActorSystem("SampleSystem")
//    val actor = actorSystem.actorOf(Props(new WordCounterActor(args.head)))
//    (actor ? StartProcessFileMsg).map { result =>
//      println(s"Result=$result")
//    }
//  }
//
//}
//
//case class ProcessStringMsg(str: String)
//case class StringProcessedMsg(words: Int)
//case object StartProcessFileMsg
//
//class StringCounterActor extends Actor with ActorLogging {
//
//  def receive = {
//    case ProcessStringMsg(str) =>
//      val wordsInLine = str.split(" ").length
//      sender() ! StringProcessedMsg(wordsInLine)
//    case _ => log.error("Error: message not recognized")
//  }
//}
//
//class WordCounterActor(filename: String) extends Actor with ActorLogging {
//
//  private var running = false
//  private var totalLines = 0
//  private var linesProcessed = 0
//  private var result = 0
//  private var fileSender: Option[ActorRef] = None
//
//  def receive = {
//    case StartProcessFileMsg =>
//      log.info("Start lines from file ...")
//      if (running) {
//        log.warning("Duplicate start message received")
//      } else {
//        running = true
//        fileSender = Some(sender())
//        import scala.io.Source._
//        fromFile(filename).getLines().foreach { line =>
//          context.actorOf(Props[StringCounterActor]) ! ProcessStringMsg(line)
//          totalLines += 1
//        }
//      }
//
//    case StringProcessedMsg(words) =>
//      result += words
//      linesProcessed += 1
//      if (linesProcessed == totalLines) {
//        fileSender.map(_ ! result)
//      }
//    case _ => log.error("Message not recognized!")
//  }
//}
//
////case object ResumeException extends Exception
////case object StopException extends Exception
////case object RestartException extends Exception
////class TestingActor extends Actor with ActorLogging {
////
////  override val supervisorStrategy = OneForOneStrategy(
////    maxNrOfRetries = 10, withinTimeRange = 10.seconds){
////    case ResumeException => Resume
////    case RestartException => Restart
////    case StopException => Stop
////    case _: Exception => Escalate
////  }
////
////  override def receive = {
////    case m =>
////      log.info(s"Unknown message=$m")
////  }
////
////}
