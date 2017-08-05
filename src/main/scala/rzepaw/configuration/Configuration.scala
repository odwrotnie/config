package rzepaw.configuration

import com.typesafe.config._
import com.typesafe.scalalogging.LazyLogging
import scala.util.Try

trait Configuration
  extends LazyLogging {

  private val CONFIGURATION_MODE_NAME = "configuration.mode"

  private lazy val configurationRoot: Config =
    ConfigFactory.load

  protected lazy val configurationMode: Option[String] =
    Try(configurationRoot.getString(CONFIGURATION_MODE_NAME)).toOption

  private lazy val modeConfig: Option[Config] = for {
    mode <- configurationMode
    config <- Try(configurationRoot.getConfig(mode)).toOption
  } yield config

  lazy val configuration: Config = {

    logger.debug(s"CONFIGURATION - using `${ configurationMode.getOrElse("root") }` config")

    modeConfig match {
      case Some(cfg) => cfg.withFallback(configurationRoot)
      case None => configurationRoot
    }
  }
}
