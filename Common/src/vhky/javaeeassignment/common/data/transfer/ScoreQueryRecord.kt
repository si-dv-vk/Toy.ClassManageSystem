package vhky.javaeeassignment.common.data.transfer

import org.json.JSONObject
import vhky.javaeeassignment.common.utils.JSONConvertible

/**
 * No Description
 * Created by 锟 at 12:03 on 2018/1/20.
 */
class ScoreQueryRecord : JSONConvertible<ScoreQueryRecord>
{
	lateinit var teacherId : String
	lateinit var teacherName : String
	lateinit var studentId : String
	lateinit var studentName : String
	lateinit var courseId : String
	lateinit var courseName : String
	lateinit var score : String

	override val _toJSON : JSONObject.() -> Unit get() = {
		put("teacherId", teacherId)
		put("teacherName", teacherName)
		put("studentId", studentId)
		put("studentName", studentName)
		put("courseId", courseId)
		put("courseName", courseName)
		put("score", score)
	}
	override val _fromJSON : ScoreQueryRecord.(JSONObject) -> Unit get() = {
		teacherId = it.getString("teacherId")
		teacherName = it.getString("teacherName")
		studentId= it.getString("studentId")
		studentName = it.getString("studentName")
		courseId= it.getString("courseId")
		courseName = it.getString("courseName")
		score = it.getString("score")
	}
}