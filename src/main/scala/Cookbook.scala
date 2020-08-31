object CookBook extends App {
  val srcName = "cook_book_txt"
  val dstName = "cook_book_results.txt"
  def openSource(fName:String) = {
    //actually get a real sequence of strings
    Seq("abra","dabra") //just a placeholder
  }

  def processSeq(mySeq:Seq[String])= {
    Seq("RECIPE","ingredient") //another placeholder
  }

  def saveSeq(destName:String, mySeq:Seq[String]) = {
    println(s"Saving my Sequence to file $dstName")
    scala.util.Success
  }

  //the actual program runs here, little tiny pipeline like a factory
  val mySeq = openSource(srcName)
  val filteredSeq = processSeq(Array("Val","Some"))
  saveSeq(dstName,filteredSeq)
}
