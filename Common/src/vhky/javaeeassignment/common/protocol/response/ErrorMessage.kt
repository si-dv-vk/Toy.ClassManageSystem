package vhky.javaeeassignment.common.protocol.response

import org.json.JSONObject
import vhky.javaeeassignment.common.misc.ErrorCode
import vhky.javaeeassignment.common.protocol.NetworkMessage

/**
 * No Description
 * Created by 锟 at 4:49 on 2018/1/9.
 */
class ErrorMessage() : NetworkMessage<ErrorMessage>("ErrorMessage")
{
	var code = ErrorCode.Unknown
	constructor(code : ErrorCode) : this()
	{
		this.code = code
	}
	override val _toJSON : JSONObject.() -> Unit get() = {
		put("code", code.ordinal)
	}
	override val _fromJSON : ErrorMessage.(JSONObject) -> Unit get() = {
		code = it.getInt("code").let { ErrorCode.values()[it] }
	}
	val hint get() = code.hint
}