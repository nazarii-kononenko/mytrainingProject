package browserstack.core.capability_finder

import browserstack.core.capability.model.BaseCapabilityModel
import com.beust.klaxon.Klaxon
import java.io.FileReader

internal class CapabilityFinder {

    inline fun <reified T : BaseCapabilityModel> findCapability(capabilityName: String, fileName: String): T {
        val klaxon = Klaxon()
        val capabilities = klaxon.parseJsonObject(FileReader(fileName))
        val desiredCapability = capabilities.obj(capabilityName) ?: throw IllegalArgumentException()
        return klaxon.parseFromJsonObject<T>(desiredCapability) ?: throw IllegalArgumentException()
    }
}