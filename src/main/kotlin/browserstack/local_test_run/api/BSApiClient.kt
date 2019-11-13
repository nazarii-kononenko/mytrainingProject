package browserstack.local_test_run.api

import browserstack.core.exception.BrowserStackApiException
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

class BSApiClient {

    private val apiProvider = BSApiProvider()

    fun getProxyIDByLocalIdentifier(localIdentifier: String): String {
        val responseTriple = apiProvider.getProxyInfo()
        responseTriple.third.fold(
            { data ->
                return data["instances"]
                    .find { it["localIdentifier"].textValue().contains(localIdentifier) }
                    ?.get("id")?.textValue()
                    ?: throw BrowserStackApiException("No proxy with such identifier")
            },
            { fuelError ->
                logger.error {
                    """
                     Response header: ${responseTriple.second.headers}
                     Response status code: ${responseTriple.second.statusCode}
                     Response message: ${responseTriple.second.responseMessage}
                """.trimIndent()
                }
                throw BrowserStackApiException(
                    fuelError.exception.message ?: "Browser stack api is not responding"
                )
            }
        )
    }

    fun disconnectLocalProxyByID(proxyID: String) {
        val responseTriple = apiProvider.disconnectLocalProxyByID(proxyID)
        return when (responseTriple.second.statusCode) {
            200 -> {
                logger.info { "Proxy $proxyID successfully disconnected" }
            }
            401 -> {
                logger.error { """Error: Invalid token""" }
                throw BrowserStackApiException("Authentication failed due to incorrect token.")
            }
            422 -> {
                logger.error { """Error: Incorrect ID""" }
                throw BrowserStackApiException("The ID requested for is incorrect.")
            }
            else -> {
                logger.error {
                    """
                    Response status code: ${responseTriple.second.statusCode}
                    Response message: ${responseTriple.second.responseMessage}
                    """
                }
                throw BrowserStackApiException("Failed to disconnect, please try again")
            }
        }
    }
}