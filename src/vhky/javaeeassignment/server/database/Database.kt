package vhky.javaeeassignment.server.database

import vhky.javaeeassignment.common.data.UserType
import vhky.javaeeassignment.common.data.prototype.Student
import vhky.javaeeassignment.common.data.prototype.Teacher
import vhky.javaeeassignment.common.data.transfer.ScoreQueryRecord
import vhky.javaeeassignment.server.ServerResourceManifest
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
	private fun execute(sql : String) = connection.createStatement().use { it.execute(sql) }
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
		execute("insert into Password(UserType, ID, Password) " +
				"values(${UserType.Teacher.ordinal}, '${teacher.ID}', '$teacherPassword')")
		execute("insert into Teacher(ID, Name, School, Department) " +
				"values('${teacher.ID}', '${teacher.name}', '${teacher.school}', '${teacher.department}')")
	}
	fun writeStudent(student : Student, studentPassword : String)
	{
		execute("insert into Password(UserType, ID, Password) " +
				"values(${UserType.Student.ordinal}, '${student.ID}', '$studentPassword')")
		execute("insert into Student(ID, Name, Gender, Birthday, Major, School) " +
				"values('${student.ID}', '${student.name}', '${student.gender}'," +
				" '${student.birthday}', '${student.major}', '${student.school}')")
	}
	fun queryScore(userType : UserType, id : String) : List<ScoreQueryRecord>
	{
		fun ResultSet.convertToObject() : List<ScoreQueryRecord>
		{
			val list = emptyList<ScoreQueryRecord>().toMutableList()
			while (this.next())
			{
				val record = ScoreQueryRecord()
				record.let {
					it.courseName = getString("CourseName")
					it.courseId = getString("CourseID")
					it.studentName = getString("StudentName")
					it.studentId = getString("StudentID")
					it.teacherName = getString("TeacherName")
					it.teacherId = getString("TeacherID")
					it.score = getString("Score")
				}
				list.add(record)
			}
			return list.toList()
		}
		val sql = "select Course.Name as CourseName, CourseID, " +
				"Teacher.Name as TeacherName, Teacher.ID as TeacherID, " +
				"Student.Name as StudentName, StudentID, Score from Score join Course on " +
				"Score.CourseID == Course.ID join Teacher on Course.TeacherID == Teacher.ID " +
				"join Student on Score.StudentID == Student.ID"
		return when (userType)
		{
			UserType.Student -> query("$sql where StudentID == $id"){ convertToObject() }
			UserType.Teacher -> query("$sql where Teacher.ID == $id"){ convertToObject() }
			UserType.TeachingManager -> query(sql){ convertToObject() }
			else -> emptyList()
		}
	}
}