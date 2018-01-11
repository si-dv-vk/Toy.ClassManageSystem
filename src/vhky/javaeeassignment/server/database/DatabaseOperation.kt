package vhky.javaeeassignment.server.database

import vhky.javaeeassignment.common.data.Student
import vhky.javaeeassignment.common.data.Teacher
import vhky.javaeeassignment.common.data.UserType
import vhky.javaeeassignment.common.utils.toJSON
import vhky.javaeeassignment.server.utils.LogUtil
import vhky.javaeeassignment.server.utils.ServerKey
import javax.servlet.ServletContext
import kotlin.math.log10

/**
 * No Description
 * Created by 锟 at 6:25 on 2018/1/9.
 */
object TheDatabase
{
	fun queryPassword(userType : UserType, id : String) : String = "123"
	fun queryStudent(id : String) : Student = Student().apply {
		ID = id
		name = "Kun Zhang"
		gender = "Male"
		birthday = "1996"
		school = "UESTC"
		major = "Software Engineering"
	}

	fun queryTeacher(id : String) : Teacher = Teacher().apply {
		ID = id
		name = "Kun Zhang"
		school = "UESTC"
		department = "XR"
	}

	fun writeTeacher(teacher : Teacher, teacherPassword : String)
	{
		LogUtil.info("""
			New Teacher:
			Detail = ${teacher.toJSON()}
			Password = $teacherPassword
		""".trimIndent())
	}
	fun writeStudent(student : Student, studentPassword : String)
	{
		LogUtil.info("""
			New Student:
			Detail = ${student.toJSON()}
			Password = $studentPassword
		""".trimIndent())
	}
}