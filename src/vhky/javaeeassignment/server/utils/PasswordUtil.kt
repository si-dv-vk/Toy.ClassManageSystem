package vhky.javaeeassignment.server.utils

import vhky.javaeeassignment.common.data.Password
import vhky.javaeeassignment.common.data.UserType
import vhky.javaeeassignment.common.misc.ErrorCode
import vhky.javaeeassignment.server.database.TheDatabase

/**
 * No Description
 * Created by 锟 at 21:08 on 2018/1/10.
 */
object PasswordUtil
{
	fun verifyPassword(requestPassword : Password, databasePassword : () -> String, onSuccess : () -> String) : String
	{
		val requestPasswordDecoded = requestPassword[ServerKey()]
		return when(requestPasswordDecoded.second)
		{
			Password.Status.Collapsed -> ErrorCode.CollapsedPassword.toMessage().toString()
			Password.Status.Expired -> ErrorCode.ExpiredLogin.toMessage().toString()
			Password.Status.Ok ->
			{
				val dbPassword = TheDatabase.queryPassword(UserType.TeachingManager, "")
				if (requestPasswordDecoded.first != dbPassword) ErrorCode.WrongPassword.toMessage().toString()
				else onSuccess()
			}
		}
	}
}