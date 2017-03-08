package rzepaw.configuration

import com.typesafe.config._
import com.typesafe.scalalogging.LazyLogging

trait Configuration
  extends LazyLogging {

  lazy val configuration: Config = {

    val MODE_NAME = "configuration.mode"
    val DEFAULT_CONF = "default"

    val rootConfig = ConfigFactory.load

    val mode: String = rootConfig.getString(MODE_NAME)
    logger.debug(s"Using $mode config")

    val modeConfig = rootConfig.getConfig(mode)
    val defaultConfig = rootConfig.getConfig(DEFAULT_CONF)

    modeConfig.withFallback(defaultConfig)
  }
}
