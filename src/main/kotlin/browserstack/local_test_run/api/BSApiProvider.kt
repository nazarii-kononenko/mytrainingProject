package browserstack.local_test_run.api

import com.fasterxml.jackson.databind.node.ObjectNode
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.jackson.jacksonDeserializerOf
import com.github.kittinunf.result.Result
import browserstack.core.config.Configs

class BSApiProvider {

    fun getProxyInfo(): Triple<Request, Response, Result<ObjectNode, FuelError>> {
        val proxyInfoUrl =
            "https://www.browserstack.com/local/v1/list?auth_token=${Configs.config!!.key}&last=5&state=running"
        return Fuel.get(proxyInfoUrl).responseObject(jacksonDeserializerOf())
    }

    fun disconnectLocalProxyByID(proxyID: String): Triple<Request, Response, Result<ObjectNode, FuelError>> {
        val disconnectProxyUrl =
            "https://www.browserstack.com/local/v1/$proxyID?auth_token=${Configs.config!!.key}"
        return Fuel.delete(disconnectProxyUrl).responseObject(jacksonDeserializerOf())
    }
}