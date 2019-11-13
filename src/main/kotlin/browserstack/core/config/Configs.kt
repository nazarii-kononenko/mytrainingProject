package browserstack.core.config

import browserstack.core.config.model.BSConfigModel
import browserstack.core.exception.BrowserStackConfigurationException

object Configs {
    var config: BSConfigModel? = null
        get() = field ?: throw BrowserStackConfigurationException("Config is not initialized.")
}