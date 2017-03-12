package rzepaw.configuration

import com.typesafe.config._
import com.typesafe.scalalogging.LazyLogging

import scala.util.Try

trait Configuration
  extends LazyLogging {

  val CONFIGURATION_MODE_NAME = "configuration.mode"

  lazy val configurationRoot: Config =
    ConfigFactory.load
  lazy val configurationMode: String =
    configurationRoot.getString(CONFIGURATION_MODE_NAME)

  lazy val configuration: Config = {
    logger.debug(s"Using $configurationMode config")
    val modeConfig: Option[Config] =
      Try(configurationRoot.getConfig(configurationMode)).toOption
    modeConfig match {
      case Some(cfg) => cfg.withFallback(configurationRoot)
      case None => configurationRoot
    }
  }
}
