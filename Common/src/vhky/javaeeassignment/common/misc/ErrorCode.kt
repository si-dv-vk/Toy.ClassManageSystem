package vhky.javaeeassignment.common.misc

import vhky.javaeeassignment.common.protocol.response.ErrorMessage

/**
 * No Description
 * Created by 锟 at 20:19 on 2018/1/10.
 */
@Suppress("MemberVisibilityCanPrivate", "unused")
enum class ErrorCode(val hint : String)
{
	Unknown("Sorry, an unknown error occurred, and I can do nothing for you"),
	IllegalRequestType("Sorry, please retry"),
	WrongPassword("Sorry, your id and password don't match"),
	ExpiredLogin("Your login state has been expired, please re-login"),
	CollapsedPassword("Sorry, please retry"),
	NoResponse("Server dose not respond, please check your network or inform the server manager");
	override fun toString() = hint
	fun toMessage() = ErrorMessage(this)
}