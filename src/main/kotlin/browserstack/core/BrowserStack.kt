package browserstack.core

import browserstack.core.capability.model.OptionalCapability
import browserstack.core.config.Configs
import browserstack.core.config.model.BSConfigModel
import browserstack.core.driver.providers.IBaseDriverProvider
import browserstack.core.enums.IBaseListOfDrivers
import browserstack.local_test_run.LocalProxyRunner
import browserstack.local_test_run.OperationSystems
import browserstack.local_test_run.api.BSApiClient
import org.openqa.selenium.WebDriver

class BrowserStack {

    private val bsApiClient = BSApiClient()

    fun setConfig(configs: BSConfigModel): BrowserStack {
        Configs.config = configs
        return this
    }

    inline fun <reified T : IBaseDriverProvider, P : IBaseListOfDrivers> getDriver(
        driverName: P,
        optionalCapability: OptionalCapability = OptionalCapability()
    ): WebDriver {
        return T::class.java
            .getDeclaredConstructor()
            .newInstance()
            .getDriver(driverName.getDriverName(), optionalCapability)
    }

    fun startLocalProxy(operationSystem: OperationSystems, localIdentifier: String) {
        LocalProxyRunner().startLocalProxyForSystem(operationSystem, localIdentifier)
    }

    fun stopLocalProxy(localIdentifier: String) {
        val proxyId = bsApiClient.getProxyIDByLocalIdentifier(localIdentifier)
        bsApiClient.disconnectLocalProxyByID(proxyId)
    }
}
