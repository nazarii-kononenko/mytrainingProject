package browserstack.core.driver.providers

import browserstack.core.capability.model.OptionalCapability
import org.openqa.selenium.WebDriver

interface IBaseDriverProvider {

    fun getDriver(driverName: String, optionalCapability: OptionalCapability): WebDriver
}