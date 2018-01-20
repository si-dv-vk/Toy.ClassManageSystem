package vhky.javaeeassignment.client.controllers

import javafx.fxml.FXML
import javafx.scene.Scene
import javafx.scene.control.Label
import vhky.javaeeassignment.client.ClientApplication
import vhky.javaeeassignment.client.ClientResourceManifest
import vhky.javaeeassignment.client.data.TeacherScore
import vhky.javaeeassignment.client.utils.Bulletin
import vhky.javaeeassignment.client.utils.NetworkUtil
import vhky.javaeeassignment.client.utils.alert
import vhky.javaeeassignment.common.data.Password
import vhky.javaeeassignment.common.data.UserType
import vhky.javaeeassignment.common.data.prototype.Teacher
import vhky.javaeeassignment.common.protocol.messageType
import vhky.javaeeassignment.common.protocol.request.QueryScoreRequest
import vhky.javaeeassignment.common.protocol.response.ErrorMessage
import vhky.javaeeassignment.common.protocol.response.QueryScoreResponse
import vhky.javaeeassignment.common.utils.toObject

/**
 * No Description
 * Created by 锟 at 3:11 on 2018/1/9.
 */
class TeacherMainPageController
{
	companion object
	{
		lateinit var instance : TeacherMainPageController
	}
	init
	{
		instance = this
	}
	lateinit var password : Password
	var currentUser : Teacher? = null
	set(value)
	{
		field = value
		nameBox.text = value?.name
		idBox.text = value?.ID
		departmentBox.text = value?.department
		schoolBox.text = value?.school
	}

	@FXML
	private lateinit var nameBox : Label
	@FXML
	private lateinit var idBox : Label
	@FXML
	private lateinit var departmentBox : Label
	@FXML
	private lateinit var schoolBox : Label

	@FXML
	private fun onQuit() = System.exit(0)
	@FXML
	private fun onQueryClass()
	{
		NetworkUtil.sendRequest(QueryScoreRequest().apply {
			id = currentUser!!.ID
			userType = UserType.Teacher
			password = this@TeacherMainPageController.password
		})
		{
			when(it.messageType)
			{
				"ErrorMessage" -> it.toObject<ErrorMessage>().code.alert()
				"QueryScoreResponse" -> it.toObject<QueryScoreResponse>().apply {
					val dataToPass = data.map { TeacherScore().apply {
						courseId = it.courseId
						courseName = it.courseName
						studentId = it.studentId
						studentName = it.studentName
						score = it.score
					} }
					val scene = Scene(ClientResourceManifest.TEACHER_SCORE())
					TeacherScoreController.instance.back = ClientApplication.instance.stage.scene
					TeacherScoreController.instance.passData(dataToPass)
					ClientApplication.instance.stage.scene = scene
				}
			}
		}
	}
	@FXML
	private fun onCheckBulletin() = Bulletin.check()
}