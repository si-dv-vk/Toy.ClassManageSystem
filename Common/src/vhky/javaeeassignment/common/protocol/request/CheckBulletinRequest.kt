package vhky.javaeeassignment.common.protocol.request

import org.json.JSONObject
import vhky.javaeeassignment.common.protocol.NetworkMessage

/**
 * No Description
 * Created by 锟 at 4:36 on 2018/1/9.
 */
class CheckBulletinRequest : NetworkMessage<CheckBulletinRequest>("CheckBulletinRequest")
{
	override val _toJSON : JSONObject.() -> Unit get() = {}
	override val _fromJSON : CheckBulletinRequest.(JSONObject) -> Unit get() = {}
}