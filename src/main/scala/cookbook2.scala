import java.io.{BufferedWriter, FileWriter}

object cookbook2 extends App {
  //  val srcName = "13177-8.txt"
  //  val dstName = "13177-8-results.txt"
  val srcName = "C:/cookbook_hw/cookbook.txt"
  val dstName = "C:/cookbook_hw/cookbook_result2.txt"

  val filePointer = scala.io.Source.fromFile(srcName)

  def openSource(fName:String) = {
    //actually get a real sequence of strings

    //val filePointer = scala.io.Source.fromFile(srcName)
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
    for (i <- 0 to 3880) {

      if ((mySeq(i) matches patternForHeader) && (mySeq(i) matches patternNonEmpty)) {
        newSeq = newSeq :+ mySeq(i)
        isNewHeader = true
      }
      else if (mySeq(i) matches patternForRecipes) {
        isNewHeader =true
        Header = mySeq (i)
        newSeq = newSeq :+ Header
        isNewHeader = false
        Header = mySeq (i)
      }
    }

    newSeq
  }

  def saveSeq(destName:String, mySeq:Seq[String]):Unit = {
    println(s"Saving my Sequence to file $destName") //destName <- dstName


    mySeq.foreach(println) //we are good up to here

    val fw = new FileWriter(destName)
    val bw = new BufferedWriter(fw)

    mySeq.map(_ + "\n").foreach(bw.write) // adding new line to each line before writing

    bw.close()
    fw.close()
    filePointer.close()
  }

  //the actual program runs here, little tiny pipeline like a factory
  val mySeq = openSource(srcName)
  val filteredSeq = processSeq(mySeq)
  saveSeq(dstName,filteredSeq)
}