package vhky.javaeeassignment.server.utils

import vhky.javaeeassignment.common.data.Password
import vhky.javaeeassignment.common.misc.ErrorCode

/**
 * No Description
 * Created by 锟 at 21:08 on 2018/1/10.
 */
object PasswordUtil
{
	fun verifyPassword(requestPassword : Password, databasePassword : () -> String?, onSuccess : () -> String) : String
	{
		val requestPasswordDecoded = requestPassword[ServerKey()]
		return when(requestPasswordDecoded.second)
		{
			Password.Status.Collapsed -> ErrorCode.CollapsedPassword.toMessage().toString()
			Password.Status.Expired -> ErrorCode.ExpiredLogin.toMessage().toString()
			Password.Status.Ok ->
			{
				val dbPassword = databasePassword()
				if (dbPassword == null || requestPasswordDecoded.first != dbPassword) ErrorCode.WrongPassword.toMessage().toString()
				else onSuccess()
			}
		}
	}
}