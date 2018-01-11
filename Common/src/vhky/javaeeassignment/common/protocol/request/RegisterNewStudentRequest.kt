package vhky.javaeeassignment.common.protocol.request

import org.json.JSONObject
import vhky.javaeeassignment.common.data.Password
import vhky.javaeeassignment.common.data.Student
import vhky.javaeeassignment.common.protocol.NetworkMessage
import vhky.javaeeassignment.common.utils.toJSON
import vhky.javaeeassignment.common.utils.toObject

/**
 * No Description
 * Created by 锟 at 13:32 on 2018/1/9.
 */
class RegisterNewStudentRequest : NetworkMessage<RegisterNewStudentRequest>("RegisterNewStudentRequest")
{
	lateinit var systemManagerPassword : Password
	lateinit var studentPassword : Password
	lateinit var student : Student
	override val _toJSON : JSONObject.() -> Unit get() = {
		put("systemManagerPassword", systemManagerPassword.toString())
		put("studentPassword", studentPassword.toString())
		put("student", student.toJSON())
	}
	override val _fromJSON : RegisterNewStudentRequest.(JSONObject) -> Unit get() = {
		systemManagerPassword = it.getString("systemManagerPassword").let { Password(it) }
		studentPassword = it.getString("studentPassword").let { Password(it) }
		student = it.getJSONObject("student").toObject()
	}
}