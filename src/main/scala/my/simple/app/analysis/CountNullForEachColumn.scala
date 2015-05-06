package my.simple.app.analysis

import java.io.File

import my.simple.app.context.SparkContextFactory
import my.simple.app.util.Logging
import org.apache.spark.rdd.RDD

/**
 * Created by rakumar on 4/27/15.
 */


object CountNullForEachColumn extends  Logging {



    def main (args: Array[String]) {

      args.length match {
        case x: Int if x == 1 => println("Filename is: "+ args(0) )
        case _ => throw new IllegalArgumentException("Arguments are missing, <filename> ");
      }


      //Get Spark context
      val sc = SparkContextFactory.getSparkContext()

      //Create an RDD
      val rdd: RDD[Array[String]] = sc.textFile(args(0)).map(_.split(",",-1))


      logger.info("Input file name is: "+ args(0))

      //Count null for each column
      val partStats = rdd.mapPartitions( (iter: Iterator[Array[String]]) => {
        try {
          val nullStat: Array[Counter] = iter.next().map(d => Counter(d))
          iter.foreach(arr => {
            nullStat.zip(arr).foreach { case (n, d) => n.add(d) }
          })
          Iterator(nullStat)
        }
        catch {
          case e: Exception => Iterator()
        }


      })

      val output: Array[Counter] = partStats.reduce((n1, n2) => {
        n1.zip(n2).map { case (a, b) => a.merge(b) }
      })


      logger.info("printing result for cols: ")
      output.foreach { x =>
        println(x.nullCount)
      }

    }


  }

  /*
  This class will create an object for each column val.
   */
  class Counter extends Serializable {

    var nullCount: Long = 0L

    // Check for null, if not null, return 0, else 1
    def nullOrNot(x: String): Counter = {
      if (x.equals("null") || x.equals("")) {
        this.nullCount = 1
      }
      this

    }
    def add(x: String) = {
      this.merge(new Counter().nullOrNot(x))
      this
    }

    def merge(ob: Counter) = {
      this.nullCount = ob.nullCount + this.nullCount
      this
    }


  }

object Counter extends Serializable {

  def apply(x: String): Counter = new Counter().nullOrNot(x)

}
