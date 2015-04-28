package my.simple.app.context

import com.typesafe.config.ConfigFactory
import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by rakumar on 4/27/15.
 */
object SparkContextFactory {

  val config = ConfigFactory.load()
  val master = config.getString("app.spark.master")

  val sc = new SparkContext(new SparkConf().setMaster(master).setAppName("Simple App"))

  def getSparkContext(): SparkContext = sc


  override def toString = "Creating Spark Context"
}
