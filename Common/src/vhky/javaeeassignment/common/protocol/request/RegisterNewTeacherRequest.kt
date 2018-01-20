package vhky.javaeeassignment.common.protocol.request

import org.json.JSONObject
import vhky.javaeeassignment.common.data.Password
import vhky.javaeeassignment.common.data.prototype.Teacher
import vhky.javaeeassignment.common.protocol.NetworkMessage
import vhky.javaeeassignment.common.utils.toJSON
import vhky.javaeeassignment.common.utils.toObject

/**
 * No Description
 * Created by 锟 at 13:38 on 2018/1/9.
 */
class RegisterNewTeacherRequest : NetworkMessage<RegisterNewTeacherRequest>("RegisterNewTeacherRequest")
{
	lateinit var systemManagerPassword : Password
	lateinit var teacherPassword : Password
	lateinit var teacher : Teacher
	override val _toJSON : JSONObject.() -> Unit get() = {
		put("systemManagerPassword", systemManagerPassword.toString())
		put("teacherPassword", teacherPassword.toString())
		put("teacher", teacher.toJSON())
	}
	override val _fromJSON : RegisterNewTeacherRequest.(JSONObject) -> Unit get() = {
		systemManagerPassword = it.getString("systemManagerPassword").let { Password(it) }
		teacherPassword = it.getString("teacherPassword").let { Password(it) }
		teacher = it.getJSONObject("teacher").toObject()
	}
}