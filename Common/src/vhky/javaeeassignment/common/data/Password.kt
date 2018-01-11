package vhky.javaeeassignment.common.data

import org.json.JSONException
import org.json.JSONObject
import vhky.javaeeassignment.common.utils.Base64Util
import vhky.javaeeassignment.common.utils.CipherUtil
import java.security.Key

/**
 * No Description
 * Created by 锟 at 19:30 on 2018/1/10.
 */
class Password()
{
	companion object
	{
		var expireTime = 3600000L
		var allowedTimeDifference = 600000L
	}
	override fun toString() = value
	private var value = ""
	constructor(encodedPassword : String) : this()
	{
		value = encodedPassword
	}
	constructor(encoder : (String) -> String, password : String) : this()
	{
		set(encoder, password)
	}
	constructor(key : Key, password : String) : this()
	{
		set(key, password)
	}

	enum class Status{ Ok, Expired, Collapsed }
	//password and whether this password is valid
	operator fun get(decoder : (String) -> String) : Pair<String, Status>
	{
		val decoded = value.let { decoder(it) }
		val json : JSONObject
		try
		{
			json = JSONObject(decoded)
		}
		catch (e : JSONException) { return "" to Status.Collapsed }
		val password = json.getString("password")
		val time = json.getLong("time")
		return password to ((System.currentTimeMillis() - time)
				in -allowedTimeDifference..expireTime).let { if (it) Status.Ok else Status.Expired }
	}
	operator fun set(encoder : (String) -> String, password : String)
	{
		val json = JSONObject()
		json.put("password", password)
		json.put("time", System.currentTimeMillis())
		value = json.toString().let { encoder(it) }
	}
	operator fun get(key : Key) = get(CipherUtil.getDecoder(key))
	operator fun set(key : Key, password : String) = set(CipherUtil.getDecoder(key), password)
	operator fun get(key : String) = get(key.let { Base64Util.debase64(it) }.let { CipherUtil.privateKeyDecoder(it) })
	operator fun set(key : String, password : String) = set(key.let { Base64Util.debase64(it) }.let { CipherUtil.publicKeyDecoder(it) }, password)
}