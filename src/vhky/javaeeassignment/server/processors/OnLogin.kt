package vhky.javaeeassignment.server.processors

import org.json.JSONObject
import vhky.javaeeassignment.common.data.UserType
import vhky.javaeeassignment.common.protocol.request.LoginRequest
import vhky.javaeeassignment.common.protocol.response.LoginResponse
import vhky.javaeeassignment.common.utils.toJSON
import vhky.javaeeassignment.server.database.Database
import vhky.javaeeassignment.server.utils.PasswordUtil

/**
 * No Description
 * Created by 锟 at 6:22 on 2018/1/9.
 */

fun onLogin(request : LoginRequest) = PasswordUtil.verifyPassword(request.password, { Database.queryPassword(request.userType, request.id)})
{
	val response = LoginResponse()
	response.type = request.userType
	response.userData = when (request.userType)
	{
		UserType.Student -> Database.queryStudent(request.id).toJSON()
		UserType.Teacher -> Database.queryTeacher(request.id).toJSON()
		else -> JSONObject()
	}
	response.toString()
}