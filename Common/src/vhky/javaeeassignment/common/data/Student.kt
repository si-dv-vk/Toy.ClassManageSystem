package vhky.javaeeassignment.common.data

import org.json.JSONObject
import vhky.javaeeassignment.common.utils.JSONConvertible

/**
 * No Description
 * Created by 锟 at 1:18 on 2018/1/9.
 */
@Identity(UserType.Student)
class Student : JSONConvertible<Student>
{
	lateinit var ID : String
	lateinit var name : String
	lateinit var gender : String
	lateinit var birthday : String
	lateinit var major : String
	lateinit var school : String

	override val _toJSON : JSONObject.() -> Unit get() = {
		put("id", ID)
		put("name", name)
		put("gender", gender)
		put("birthday", birthday)
		put("major", major)
		put("school", school)
	}
	override val _fromJSON : Student.(JSONObject) -> Unit get() = {
		ID = it.getString("id")
		name = it.getString("name")
		gender = it.getString("gender")
		birthday = it.getString("birthday")
		major = it.getString("major")
		school = it.getString("school")
	}
}