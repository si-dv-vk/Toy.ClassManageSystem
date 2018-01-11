package vhky.javaeeassignment.common.protocol.response

import org.json.JSONObject
import vhky.javaeeassignment.common.protocol.NetworkMessage

/**
 * No Description
 * Created by 锟 at 4:52 on 2018/1/9.
 */

class CheckBulletinResponse : NetworkMessage<CheckBulletinResponse>("CheckBulletinResponse")
{
	lateinit var text : String

	override val _toJSON : JSONObject.() -> Unit get() = {
		put("text", text)
	}
	override val _fromJSON : CheckBulletinResponse.(JSONObject) -> Unit get() = {
		text = it.getString("text")
	}
}