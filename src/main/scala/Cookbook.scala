import java.io.FileWriter

object Cookbook extends App {
  val srcName = "C:/cookbook_hw/cookbook.txt"
  val dstName = "C:/cookbook_hw/cookbook_result1.txt"
  def openSource(fName:String) = {
    //actually get a real sequence of strings

    val filePointer = scala.io.Source.fromFile(srcName)
  //  Seq("abra","dabra") //just a placeholder
    val myLines = filePointer.getLines.toSeq
    filePointer.close()
    myLines
  }

  def processSeq(mySeq:Seq[String])= {
    //TODO actually do the assignment
   mySeq.slice(0,10) //just some lines
    var newSeq: Seq[String] = Seq()
    for (i <- 0 to 8 by 2) {
      newSeq = newSeq :+ mySeq(i) //adds seq
    }
    newSeq
    val funSeq = mySeq.zipWithIndex.filter(t => t._2 % 2 == 0).map(t => t._1) // all even  lines // added index and got rid off
    funSeq
  }

  def saveSeq(destName:String, mySeq:Seq[String]) = {
    println(s"Saving my Sequence to file $dstName")
    mySeq.foreach(println) // we are good up to here
    val fw = new FileWriter(destName)
    mySeq.map(_ + "\n").foreach(fw.write)
    fw.close()
    scala.util.Success
  }

  //the actual program runs here, little tiny pipeline like a factory
  val mySeq = openSource(srcName)
  val filteredSeq = processSeq(mySeq)
  saveSeq(dstName,filteredSeq)
}
