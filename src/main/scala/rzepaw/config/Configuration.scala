package rzepaw.config

import com.typesafe.config._
import com.typesafe.scalalogging.LazyLogging

trait Configuration
  extends LazyLogging {

  lazy val configuration: Config = {

    val MODE_NAME = "mode"

    val rootConfig = ConfigFactory.load

    val config: Config = Option(rootConfig.getString(MODE_NAME)) match {
      case None =>
        logger.debug(s"Using root config")
        rootConfig
      case Some(mode) =>
        logger.debug(s"Using $mode config")
        rootConfig.getConfig(mode).withFallback(rootConfig)
    }

    config
  }
}
