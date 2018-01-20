package vhky.javaeeassignment.common.protocol.request

import org.json.JSONObject
import vhky.javaeeassignment.common.data.Password
import vhky.javaeeassignment.common.data.UserType
import vhky.javaeeassignment.common.protocol.NetworkMessage

/**
 * No Description
 * Created by 锟 at 11:47 on 2018/1/20.
 */
class QueryScoreRequest : NetworkMessage<QueryScoreRequest>("QueryScoreRequest")
{
	lateinit var userType : UserType
	lateinit var id : String
	lateinit var password : Password

	override val _toJSON : JSONObject.() -> Unit get() = {
		put("userType", userType.ordinal)
		put("id", id)
		put("password", password.toString())
	}

	override val _fromJSON : QueryScoreRequest.(JSONObject) -> Unit get() = {
		userType = it.getInt("userType").let { UserType.values()[it] }
		id = it.getString("id")
		password = it.getString("password").let { Password(it) }
	}
}