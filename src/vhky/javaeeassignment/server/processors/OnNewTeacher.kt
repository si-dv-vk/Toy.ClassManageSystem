package vhky.javaeeassignment.server.processors

import vhky.javaeeassignment.common.data.UserType
import vhky.javaeeassignment.common.protocol.request.RegisterNewTeacherRequest
import vhky.javaeeassignment.common.protocol.response.SimpleSuccessResponse
import vhky.javaeeassignment.server.database.Database
import vhky.javaeeassignment.server.utils.PasswordUtil
import vhky.javaeeassignment.server.utils.ServerKey

/**
 * No Description
 * Created by 锟 at 14:35 on 2018/1/9.
 */

fun onNewTeacher(request : RegisterNewTeacherRequest) = PasswordUtil.verifyPassword(request.systemManagerPassword,
		{ Database.queryPassword(UserType.SystemManager, "")})
{
	request.apply { Database.writeTeacher(teacher, teacherPassword[ServerKey()].first)}
	SimpleSuccessResponse().toString()
}