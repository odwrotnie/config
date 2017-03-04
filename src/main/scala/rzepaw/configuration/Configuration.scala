package rzepaw.configuration

import com.typesafe.config._
import com.typesafe.scalalogging.LazyLogging

import scala.util.Try

trait Configuration
  extends LazyLogging {

  lazy val configuration: Config = {

    val MODE_NAME = "mode"

    val rootConfig = ConfigFactory.load

    Try(rootConfig.getString(MODE_NAME)).toOption match {
      case None =>
        logger.debug(s"Using root config")
        rootConfig
      case Some(mode) =>
        logger.debug(s"Using $mode config")
        rootConfig.getConfig(mode).withFallback(rootConfig)
    }
  }
}
