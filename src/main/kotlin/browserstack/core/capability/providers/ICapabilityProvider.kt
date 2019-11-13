package browserstack.core.capability.providers

import browserstack.core.capability.model.OptionalCapability
import org.openqa.selenium.remote.DesiredCapabilities

internal interface ICapabilityProvider {

    fun getCapability(capabilityName: String, optionalCapability: OptionalCapability): DesiredCapabilities
}