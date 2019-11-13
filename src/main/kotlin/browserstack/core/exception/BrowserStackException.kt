package browserstack.core.exception

open class BrowserStackException(message: String) : Exception(message)

class BrowserStackConfigurationException(message: String) : BrowserStackException(message)

class BrowserStackApiException(message: String) : BrowserStackException(message)