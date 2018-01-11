package vhky.javaeeassignment.common.protocol.response

import org.json.JSONObject
import vhky.javaeeassignment.common.data.UserType
import vhky.javaeeassignment.common.protocol.NetworkMessage
import vhky.javaeeassignment.common.utils.JSONConvertible

/**
 * No Description
 * Created by 锟 at 4:24 on 2018/1/9.
 */
class LoginResponse : NetworkMessage<LoginResponse>("LoginResponse")
{
	lateinit var type : UserType
	lateinit var userData : JSONObject

	override val _toJSON : JSONObject.() -> Unit get() =
		{
			put("requestType", type.ordinal)
			put("userData", userData)
		}

	override val _fromJSON : LoginResponse.(JSONObject) -> Unit get() =
		{
			type = it.getInt("requestType").let { UserType.values()[it] }
			userData = it.getJSONObject("userData")
		}
}