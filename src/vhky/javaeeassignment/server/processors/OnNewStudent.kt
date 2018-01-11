package vhky.javaeeassignment.server.processors

import vhky.javaeeassignment.common.data.UserType
import vhky.javaeeassignment.common.protocol.request.RegisterNewStudentRequest
import vhky.javaeeassignment.common.protocol.response.SimpleSuccessResponse
import vhky.javaeeassignment.server.database.TheDatabase
import vhky.javaeeassignment.server.utils.PasswordUtil
import vhky.javaeeassignment.server.utils.ServerKey

/**
 * No Description
 * Created by 锟 at 14:14 on 2018/1/9.
 */

fun onNewStudent(request : RegisterNewStudentRequest) = PasswordUtil.verifyPassword(request.systemManagerPassword,
		{ TheDatabase.queryPassword(UserType.SystemManager, "")})
{
	request.apply { TheDatabase.writeStudent(student, studentPassword[ServerKey()].first) }
	SimpleSuccessResponse().toString()
}