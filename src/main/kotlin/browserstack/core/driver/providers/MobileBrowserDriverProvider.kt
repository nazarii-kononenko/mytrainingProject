package browserstack.core.driver.providers

import browserstack.core.capability.model.OptionalCapability
import browserstack.core.capability.providers.MobileBrowserCapabilityProvider
import browserstack.core.config.Configs
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL

internal class MobileBrowserDriverProvider : IBaseDriverProvider {

    override fun getDriver(driverName: String, optionalCapability: OptionalCapability): WebDriver {
        val desiredCapabilities = MobileBrowserCapabilityProvider().getCapability(
            driverName,
            optionalCapability
        )
        val bsConfig = Configs.config!!
        return RemoteWebDriver(
            URL("http://${bsConfig.username}:${bsConfig.key}@${bsConfig.server}/wd/hub"),
            desiredCapabilities
        )
    }
}