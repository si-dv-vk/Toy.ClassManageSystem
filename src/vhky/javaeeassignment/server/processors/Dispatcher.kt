package vhky.javaeeassignment.server.processors

import org.json.JSONObject
import vhky.javaeeassignment.common.misc.ErrorCode
import vhky.javaeeassignment.common.protocol.messageType
import vhky.javaeeassignment.common.protocol.response.ErrorMessage
import vhky.javaeeassignment.common.utils.toObject

/**
 * No Description
 * Created by 锟 at 6:18 on 2018/1/9.
 */

object Dispatcher
{
	fun dispatch(request : JSONObject) : String = when (request.messageType)
	{
		"LoginRequest" -> onLogin(request.toObject())
		"CheckBulletinRequest" -> onCheckBulletin()
		"RegisterNewStudentRequest" -> onNewStudent(request.toObject())
		"RegisterNewTeacherRequest" -> onNewTeacher(request.toObject())
		"PublishBulletinRequest" -> onNewBulletin(request.toObject())
		"QueryScoreRequest" -> onQueryScore(request.toObject())
		else -> ErrorMessage(ErrorCode.IllegalRequestType).toString()
	}
}