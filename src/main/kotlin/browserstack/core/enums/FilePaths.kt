package browserstack.core.enums

import java.nio.file.Paths

internal enum class FilePaths(val path: String) {

    DESKTOP_CAPABILITIES(Paths.get("src/main/resources/desktop_browser_capabilities.json").toAbsolutePath().toString()),
    MOBILE_CAPABILITIES(Paths.get("src/main/resources/mobile_browser_capabilities.json").toAbsolutePath().toString())

}