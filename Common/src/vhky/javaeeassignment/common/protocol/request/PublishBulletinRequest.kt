package vhky.javaeeassignment.common.protocol.request

import org.json.JSONObject
import vhky.javaeeassignment.common.data.Password
import vhky.javaeeassignment.common.protocol.NetworkMessage

/**
 * No Description
 * Created by 锟 at 15:23 on 2018/1/9.
 */
class PublishBulletinRequest : NetworkMessage<PublishBulletinRequest>("PublishBulletinRequest")
{
	lateinit var bulletin : String
	lateinit var teachingManagerPassword : Password

	override val _toJSON : JSONObject.() -> Unit get() = {
		put("bulletin", bulletin)
		put("password", teachingManagerPassword.toString())
	}
	override val _fromJSON : PublishBulletinRequest.(JSONObject) -> Unit get() = {
		bulletin = it.getString("bulletin")
		teachingManagerPassword = it.getString("password").let { Password(it) }
	}
}