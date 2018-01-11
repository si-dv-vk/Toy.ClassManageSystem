package vhky.javaeeassignment.common.protocol.response

import org.json.JSONObject
import vhky.javaeeassignment.common.protocol.NetworkMessage

/**
 * No Description
 * Created by 锟 at 13:40 on 2018/1/9.
 */
class SimpleSuccessResponse : NetworkMessage<SimpleSuccessResponse>("SimpleSuccessResponse")
{
	override val _toJSON : JSONObject.() -> Unit get() = {}
	override val _fromJSON : SimpleSuccessResponse.(JSONObject) -> Unit get() = {}
}