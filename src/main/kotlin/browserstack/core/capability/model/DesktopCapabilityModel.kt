package browserstack.core.capability.model

internal data class DesktopCapabilityModel(
    val browser: String,
    val browserVersion: String,
    val operationSystem: String,
    val operationSystemVersion: String,
    val resolution: String
) : BaseCapabilityModel()