package vhky.javaeeassignment.common.protocol

import org.json.JSONObject
import vhky.javaeeassignment.common.protocol.request.CheckBulletinRequest
import vhky.javaeeassignment.common.utils.JSONConvertible
import vhky.javaeeassignment.common.utils.toObject
import kotlin.reflect.full.primaryConstructor

/**
 * No Description
 * Created by 锟 at 4:38 on 2018/1/9.
 */
abstract class NetworkMessage <T : NetworkMessage<T>>(val messageType : String) : JSONConvertible<T>
{
	fun toJSON() = JSONObject().apply()
	{
		put("_messageType", this@NetworkMessage.messageType)
		_toJSON()
	}

	override fun toString() = toJSON().toString()
}
val JSONObject.messageType get() = getString("_messageType")