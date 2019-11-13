package browserstack.core.enums

internal enum class BSCapabilities(val capabilityName: String) {
    BROWSER("browser"),
    BROWSER_VERSION("browserVersion"),
    OS("os"),
    OS_VERSION("os_version"),
    RESOLUTION("resolution"),
    LOCAL_TEST_RUN("localTestRun"),

    DEVICE_NAME("device"),
    REAL_MOBILE("real_mobile")
}