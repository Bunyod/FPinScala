package xml_parser

import java.nio.file.{Files, Path, Paths, StandardCopyOption}
import scala.collection.JavaConverters._
import scala.io.Source

object BrokenFodtPlaceholders2 extends App {

  val lines = Source.fromFile("src/main/resources/test1.fodt").mkString

  var s = lines

  val pattern0 = "(>\\s*%\\s*%\\s*)".r
  pattern0.findAllMatchIn(s).foreach { matches =>
    s = s.replace(matches.toString(), s">%%")
  }
  val pattern1 = "(>\\s*%\\s*(<.*?>)\\s*%)".r
  pattern1.findAllMatchIn(s).foreach { matches =>
    s = s.replace(matches.toString(), s">${matches.group(2)}%%")
  }
  val pattern2 = ">\\s*%%\\s*(<.*?>)(.*?)%%\\s*<".r
  val pattern3 = "<.*?>".r
  pattern2.findAllMatchIn(s).foreach { matches =>
    val line = matches.toString()
    val labels = matches.group(1)
    val text = matches.group(2)
    val linePattern = pattern3.findAllMatchIn(text).toList
    val placeholder = linePattern.foldLeft(text) {
      (res, t) => res.replace(t.toString(), "")
    }.replace(" ", "")
    if (labels != null) {
      if (matches.toString().startsWith(">"))
        s = s.replace(line, s">$labels${linePattern.mkString}%%$placeholder%%<")
      else
        s = s.replace(line, s"$labels${linePattern.mkString}%%$placeholder%%<")
    }
  }

//  println(s)

  val tempFilesDir = Paths.get(".")
  fodtToPdfAndGetPaths(tempFilesDir, s, "tmpFile")

  def createTempFile(path: Path, name: String, ext: String) = {
    Files.createTempFile(tempFilesDir, name, ext)
  }
  def fodtToPdfAndGetPaths(path: Path, fodtStr: String, tempFileName: String) = {
    val tmpFodtFile = createTempFile(path, tempFileName, ".odt")
    val tmpPdfFile = createTempFile(path, tempFileName, ".pdf")
//    var connection: SocketOpenOfficeConnection = null
    try {
      Files.write(tmpFodtFile, Seq(fodtStr).asJava)
//      connection = new SocketOpenOfficeConnection(8100)
//      connection.connect()
//      val converter = new OpenOfficeDocumentConverter(connection)
//      converter.convert(tmpFodtFile.toFile, tmpPdfFile.toFile) // use tempDocFile if you need docFile
    } catch { case e: Throwable =>
      val problematicFileCopy = createTempFile(path, s"${tempFileName}_problematic", ".odt")
      Files.copy(tmpFodtFile.toAbsolutePath, problematicFileCopy, StandardCopyOption.REPLACE_EXISTING)
      println(s"Error while converting to PDF; input [$tmpFodtFile]; output [$tmpPdfFile]") // use tempDocFile if you need docFile
    } finally {
//      if (connection != null) {
//        connection.disconnect()
//      }
    }
  }
}
