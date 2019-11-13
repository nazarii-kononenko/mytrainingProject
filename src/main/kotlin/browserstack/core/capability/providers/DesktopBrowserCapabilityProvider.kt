package browserstack.core.capability.providers

import browserstack.core.capability.model.DesktopCapabilityModel
import browserstack.core.capability.model.OptionalCapability
import browserstack.core.capability_finder.CapabilityFinder
import browserstack.core.enums.BSCapabilities.*
import browserstack.core.enums.FilePaths.DESKTOP_CAPABILITIES
import org.openqa.selenium.remote.DesiredCapabilities

internal class DesktopBrowserCapabilityProvider : ICapabilityProvider {

    override fun getCapability(
        capabilityName: String,
        optionalCapability: OptionalCapability
    ): DesiredCapabilities {
        val baseCapability = CapabilityFinder().findCapability<DesktopCapabilityModel>(
            capabilityName, DESKTOP_CAPABILITIES.path
        )
        val desiredCapability = DesiredCapabilities()
        desiredCapability.setCapability(BROWSER.capabilityName, baseCapability.browser)
        desiredCapability.setCapability(BROWSER_VERSION.capabilityName, baseCapability.browserVersion)
        desiredCapability.setCapability(OS.capabilityName, baseCapability.operationSystem)
        desiredCapability.setCapability(OS_VERSION.capabilityName, baseCapability.operationSystemVersion)
        desiredCapability.setCapability(RESOLUTION.capabilityName, baseCapability.resolution)
        desiredCapability.setCapability(LOCAL_TEST_RUN.capabilityName, optionalCapability.localTestRun)
        optionalCapability.otherCapabilities.map { desiredCapability.setCapability(it.key, it.value) }
        return desiredCapability
    }
}