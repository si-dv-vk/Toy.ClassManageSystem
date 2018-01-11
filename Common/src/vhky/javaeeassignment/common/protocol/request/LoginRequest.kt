package vhky.javaeeassignment.common.protocol.request

import org.json.JSONObject
import vhky.javaeeassignment.common.data.UserType
import vhky.javaeeassignment.common.protocol.NetworkMessage
import vhky.javaeeassignment.common.data.Password

/**
 * No Description
 * Created by 锟 at 4:18 on 2018/1/9.
 */
class LoginRequest : NetworkMessage<LoginRequest>("LoginRequest")
{
	lateinit var userType : UserType
	lateinit var id : String
	lateinit var password : Password
	override val _toJSON : JSONObject.() -> Unit get() =
		{
			put("requestType", userType.ordinal)
			put("id", id)
			put("password", password)
		}

	override val _fromJSON : LoginRequest.(JSONObject) -> Unit get() =
		{
			userType = it.getInt("requestType").let { UserType.values()[it] }
			id = it.getString("id")
			password = it.getString("password").let { Password(it) }
		}
}