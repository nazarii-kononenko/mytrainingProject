package browserstack.core.capability.providers

import browserstack.core.capability.model.MobileBrowserCapabilityModel
import browserstack.core.capability.model.OptionalCapability
import browserstack.core.capability_finder.CapabilityFinder
import browserstack.core.enums.BSCapabilities
import browserstack.core.enums.FilePaths.MOBILE_CAPABILITIES
import org.openqa.selenium.remote.DesiredCapabilities

internal class MobileBrowserCapabilityProvider : ICapabilityProvider {

    override fun getCapability(capabilityName: String, optionalCapability: OptionalCapability): DesiredCapabilities {
        val baseCapability = CapabilityFinder().findCapability<MobileBrowserCapabilityModel>(
            capabilityName,
            MOBILE_CAPABILITIES.path
        )
        val desiredCapability = DesiredCapabilities()
        desiredCapability.setCapability(BSCapabilities.DEVICE_NAME.capabilityName, baseCapability.deviceName)
        desiredCapability.setCapability(BSCapabilities.REAL_MOBILE.capabilityName, baseCapability.realMobile)
        desiredCapability.setCapability(BSCapabilities.OS_VERSION.capabilityName, baseCapability.operationSystemVersion)
        desiredCapability.setCapability(BSCapabilities.LOCAL_TEST_RUN.capabilityName, optionalCapability.localTestRun)
        optionalCapability.otherCapabilities.map { desiredCapability.setCapability(it.key, it.value) }
        return desiredCapability
    }
}