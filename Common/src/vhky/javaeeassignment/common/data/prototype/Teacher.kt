package vhky.javaeeassignment.common.data.prototype

import org.json.JSONObject
import vhky.javaeeassignment.common.data.Identity
import vhky.javaeeassignment.common.data.UserType
import vhky.javaeeassignment.common.utils.JSONConvertible

/**
 * No Description
 * Created by 锟 at 1:16 on 2018/1/9.
 */

@Identity(UserType.Teacher)
class Teacher : JSONConvertible<Teacher>
{
	lateinit var ID : String
	lateinit var name : String
	lateinit var school : String
	lateinit var department : String

	override val _toJSON : JSONObject.() -> Unit get() = {
		put("id", ID)
		put("name", name)
		put("school", school)
		put("department", department)
	}
	override val _fromJSON : Teacher.(JSONObject) -> Unit get() = {
		ID = it.getString("id")
		name = it.getString("name")
		school = it.getString("school")
		department = it.getString("department")
	}
}