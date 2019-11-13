package browserstack.core.capability.model

internal data class MobileBrowserCapabilityModel(
    val deviceName: String,
    val realMobile: String,
    val operationSystemVersion: String
) : BaseCapabilityModel()