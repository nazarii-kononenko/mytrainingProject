package browserstack.core.capability.model

data class OptionalCapability(
    val buildName: String = "Default Build Name",
    val localTestRun: String = "false",
    val resolution: String = "1920x1080",
    val otherCapabilities: Map<String, Any> = mutableMapOf()
) : BaseCapabilityModel()