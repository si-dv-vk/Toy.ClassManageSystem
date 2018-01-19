package vhky.javaeeassignment.server.database

import vhky.javaeeassignment.common.data.Student
import vhky.javaeeassignment.common.data.Teacher
import vhky.javaeeassignment.common.data.UserType
import vhky.javaeeassignment.common.utils.toJSON
import vhky.javaeeassignment.server.ServerResourceManifest
import vhky.javaeeassignment.server.utils.LogUtil
import java.sql.DriverManager
import java.sql.ResultSet

/**
 * No Description
 * Created by 锟 at 6:25 on 2018/1/9.
 */
object Database
{
	private val connection by lazy()
	{
		Class.forName("org.sqlite.JDBC")
		DriverManager.getConnection(ServerResourceManifest.DATABASE_URL())
	}
	private fun <T> query(sql : String, onResult : ResultSet.()->T) : T = connection.createStatement().executeQuery(sql).use { onResult(it) }
	fun queryPassword(userType : UserType, id : String) : String?
	{
		val onGetPassword  : ResultSet.()->String? = { if(next()) getString(1) ?: null else null }
		return when (userType)
		{

			UserType.TeachingManager, UserType.SystemManager ->
				query("select Password from Password where UserType == ${userType.ordinal}", onGetPassword)
			else ->
				query("select Password from Password where ID == $id and UserType == ${userType.ordinal}", onGetPassword)
		}
	}
	fun queryStudent(id : String) : Student
	{
		val student = Student()
		query("select * from Student where ID == $id")
		{
			student.ID = getString("ID")
			student.birthday = getString("Birthday")
			student.gender = getString("Gender")
			student.name = getString("Name")
			student.school = getString("School")
			student.major = getString("Major")
		}
		return student
	}

	fun queryTeacher(id : String) : Teacher
	{
		val teacher = Teacher()
		query("select * from Teacher where ID == $id")
		{
			teacher.ID = getString("ID")
			teacher.department = getString("Department")
			teacher.name = getString("Name")
			teacher.school = getString("School")
		}
		return teacher
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