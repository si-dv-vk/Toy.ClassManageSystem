package vhky.javaeeassignment.client.utils

import javafx.application.Platform
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject
import vhky.javaeeassignment.client.ClientResourceManifest
import vhky.javaeeassignment.common.misc.ErrorCode
import vhky.javaeeassignment.common.protocol.NetworkMessage
import vhky.javaeeassignment.common.protocol.response.ErrorMessage
import vhky.javaeeassignment.common.utils.Base64Util
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

/**
 * No Description
 * Created by 锟 at 4:57 on 2018/1/9.
 */
object NetworkUtil
{
	private val JSON = MediaType.parse("application/json; charset=utf-8")
	private val httpClient by lazy()
	{
		OkHttpClient.Builder()
				.connectTimeout(10, TimeUnit.SECONDS)
				.readTimeout(10, TimeUnit.SECONDS)
				.writeTimeout(10, TimeUnit.SECONDS)
				.build()
	}
	private val serverUrl by lazy { ClientResourceManifest.SERVER_URL<String>()!! }
	fun sendRequest(request : NetworkMessage<*>, onResponse : (JSONObject) -> Unit)
	{
		thread()
		{
			try
			{
				val requestBody = RequestBody.create(JSON, Base64Util.enbase64(request.toString()))
				val response = httpClient.newCall(Request.Builder()
						.url(serverUrl).post(requestBody)
						.build()).execute()?.body()?.string()?.let { JSONObject(Base64Util.debase64(it)) }
				Platform.runLater { onResponse(response ?: ErrorCode.NoResponse.toMessage().toJSON()) }
			}
			catch (e : Exception)
			{
				Platform.runLater { onResponse(ErrorCode.Unknown.toMessage().toJSON()) }
				e.printStackTrace()
			}



		}
	}
}