package browserstack.core.enums

enum class MobileBrowsers(private val driverName: String) : IBaseListOfDrivers {

    IPHONE_OSX("iPhone XS");

    override fun getDriverName(): String {
        return driverName
    }
}