package my.simple.app.util
import org.slf4j.LoggerFactory

/**
 * Created by rakumar on 4/27/15.
 */
trait Logging {

  val logger = LoggerFactory.getLogger(this.getClass)

}

