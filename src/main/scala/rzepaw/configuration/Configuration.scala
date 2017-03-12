package rzepaw.configuration

import com.typesafe.config._
import com.typesafe.scalalogging.LazyLogging

import scala.util.Try

trait Configuration
  extends LazyLogging {

  lazy val configuration: Config = {

    val MODE_NAME = "configuration.mode"

    val rootConfig = ConfigFactory.load

    val mode: String = rootConfig.getString(MODE_NAME)
    logger.debug(s"Using $mode config")

    val modeConfig: Option[Config] = Try(rootConfig.getConfig(mode)).toOption

    modeConfig match {
      case Some(cfg) => cfg.withFallback(rootConfig)
      case None => rootConfig
    }
  }
}
