import java.io.{BufferedWriter, FileWriter}

object cookbook3 extends App {
  val srcName = "C:/cookbook_hw/cookbook.txt"
  val dstName = "C:/cookbook_hw/cookbook_result3.txt"

  val filePointer = scala.io.Source.fromFile(srcName)

  def openSource(fName:String) = {

    val myLines = filePointer.getLines.toSeq

    myLines
  }

  def processSeq(mySeq:Seq[String])= {

    val patternForHeader = "^[^a-z]+$"
    val patternNonEmpty = "^[\\s\\S]+[\\S]+[\\s\\S]+"
    val patternForRecipes = "^[\\s]{4}[\\S]+[\\s\\S]+"
    var isNewHeader: Boolean = false
    var Header: String = ""

    var newSeq: Seq[String] = Seq()
    for (currSeq <- mySeq) {

      if ((currSeq matches patternForHeader) && (currSeq matches patternNonEmpty)) {
        isNewHeader = true
        Header = currSeq
      }
      else if (currSeq matches patternForRecipes) {
        if (isNewHeader) {
          newSeq = newSeq :+ Header
          isNewHeader = false
        }
        newSeq= newSeq :+ currSeq
      }
      }

    newSeq
  }

  def saveSeq(destName:String, mySeq:Seq[String]):Unit = {
    println(s"Saving my Sequence to file $destName")


    mySeq.foreach(println)

    val fw = new FileWriter(destName)
    val bw = new BufferedWriter(fw)

    mySeq.map(_ + "\n").foreach(bw.write)

    bw.close()
    fw.close()
    filePointer.close()
  }

  val mySeq = openSource(srcName)
  val filteredSeq = processSeq(mySeq)
  saveSeq(dstName,filteredSeq)
}