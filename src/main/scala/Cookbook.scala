import java.io.FileWriter

object Cookbook extends App {

  val srcName = "C:/cookbook_hw/cookbook.txt"
  val dstName = "C:/cookbook_hw/cookbook_result1.txt"
  def openSource(fName:String) = {
    //actually get a real sequence of strings

    val filePointer = scala.io.Source.fromFile(srcName)
    val myLines = filePointer.getLines.toSeq
    filePointer.close()
    myLines
  }

  def processSeq(mySeq:Seq[String])= {
   mySeq.slice(0,20) //just some lines
    var newSeq: Seq[String] = Seq()
    for (i <- 0 to 20 by 2) {
      newSeq = newSeq :+ mySeq(i) //so we keep making new sequence using old one as base
    }
    newSeq
    val funSeq = mySeq.zipWithIndex.filter(t => t._2 % 2 == 0 && t._2 < 12).map(t => t._1) // all even  lines under 8
    funSeq
  }

  def saveSeq(dstName:String, mySeq:Seq[String]) = {
    println(s"Saving my Sequence to file $dstName")
    mySeq.foreach(println) // we are good up to here

    val fw = new FileWriter(dstName, true)
    mySeq.map(_ + "\n").foreach(fw.write)
    fw.close()

  }
  //the actual program runs here, little tiny pipeline like a factory
  val mySeq = openSource(srcName)
  val filteredSeq = processSeq(mySeq)
  saveSeq(dstName,filteredSeq)
}
