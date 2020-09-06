import java.io.{BufferedWriter, FileWriter}

object CookBook extends App {
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

    //mySeq.slice(0,8) //just some lines

    //iterative solution

    //val pattern = "/^[^a-z]*$".r
    val patternForHeader = "^[^a-z]*$"
    val patternForTab = "^[\\s]{4}"

    var newSeq: Seq[String] = Seq()
    for (i <- 0 to 3880) {
      //newSeq = newSeq :+ mySeq(i) //so we keep making new sequence using old one as base

      if(mySeq(i) matches patternForHeader){
        //if(mySeq(i) matches patternForEmptyLine) {
          newSeq = newSeq :+ mySeq(i) //so we keep making new sequence using old one as base
        //}
      }
    }

    //functional solution
    //val funSeq = mySeq.zipWithIndex.filter(t => t._2 % 2 == 0 && t._2 < 12).map(t => t._1) //all even lines under 8
    //funSeq
    newSeq
  }

  def saveSeq(destName:String, mySeq:Seq[String]) = {
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