package rzepaw.configuration

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.FlatSpec

class ConfigurationTest
  extends FlatSpec
    with Configuration
    with LazyLogging {

  "The setting" should "work" in {
    // assert(configuration.getString("x") == "rootx")
    assert(configuration.getString("x") == "mode1x")
  }
}
