package id.iglo.api_service

import android.content.Context
import com.google.gson.JsonObject
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException

class ConnectivityInterceptor(private val context : Context)  : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return try {
            chain.proceed(chain.request())
        } catch (e: Exception) {
            Response.Builder().code(-2).body(
                JsonObject().apply {
                    addProperty("error", "failure_client")
                }.asString.toResponseBody("application/json".toMediaType())
            ).build()
        }
    }
}