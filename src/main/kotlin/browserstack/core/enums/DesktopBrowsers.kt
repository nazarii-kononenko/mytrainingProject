package browserstack.core.enums

enum class DesktopBrowsers(private val driverName: String) : IBaseListOfDrivers {

    OSX_MOJAVE_SAFARI_12("OSX_Mojave_Safari_12.1_1920x1080"),
    WIN_10_CHROME_75("Win_10_Chrome_75_1920x1080");

    override fun getDriverName(): String {
        return driverName
    }
}