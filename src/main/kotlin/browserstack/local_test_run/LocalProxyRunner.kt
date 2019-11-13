package browserstack.local_test_run

import browserstack.core.config.Configs
import browserstack.local_test_run.OperationSystems.LINUX
import browserstack.local_test_run.OperationSystems.WINDOWS
import mu.KotlinLogging
import java.io.BufferedReader
import java.io.InputStreamReader

private val logger = KotlinLogging.logger {}

class LocalProxyRunner {

    fun startLocalProxyForSystem(operationSystem: OperationSystems, localIdentifier: String) {

        when (operationSystem) {
            WINDOWS -> {
                val process =
                    runCommand("./src/main/resources/local_proxy/BrowserStackLocal_Win.exe", localIdentifier)
                loggingProcess(process)
            }
            LINUX -> {
                val process =
                    runCommand("./src/main/resources/browserstack/local_linux/BrowserStackLocal_OSX", localIdentifier)
                loggingProcess(process)
            }
        }

    }

    private fun runCommand(filePath: String, localIdentifier: String): Process {
        val processBuilder = ProcessBuilder()
        processBuilder.command(
            filePath,
            "--key", Configs.config!!.key,
            "--enable-logging-for-api",
            "--force-local",
            "--local-identifier", localIdentifier
        )
        return processBuilder.start()
    }

    private fun loggingProcess(process: Process) {
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        var line = reader.readLine()
        while (line != null) {
            logger.info { line }
            line = reader.readLine()
        }
    }
}

enum class OperationSystems {
    WINDOWS,
    LINUX
}