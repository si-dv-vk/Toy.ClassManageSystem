package vhky.javaeeassignment.client.controllers

import javafx.fxml.FXML
import javafx.scene.Scene
import javafx.scene.control.Label
import vhky.javaeeassignment.client.ClientApplication
import vhky.javaeeassignment.client.ClientResourceManifest
import vhky.javaeeassignment.client.data.StudentScore
import vhky.javaeeassignment.client.utils.Bulletin
import vhky.javaeeassignment.client.utils.NetworkUtil
import vhky.javaeeassignment.client.utils.alert
import vhky.javaeeassignment.common.data.Password
import vhky.javaeeassignment.common.data.UserType
import vhky.javaeeassignment.common.data.prototype.Student
import vhky.javaeeassignment.common.protocol.messageType
import vhky.javaeeassignment.common.protocol.request.QueryScoreRequest
import vhky.javaeeassignment.common.protocol.response.ErrorMessage
import vhky.javaeeassignment.common.protocol.response.QueryScoreResponse
import vhky.javaeeassignment.common.utils.toObject

/**
 * No Description
 * Created by 锟 at 3:09 on 2018/1/9.
 */
class StudentMainPageController
{
	companion object
	{
		lateinit var instance : StudentMainPageController
	}
	init
	{
		instance = this
	}

	var currentUser : Student? = null
	set(value)
	{
		field = value
		nameBox.text = field?.name
		idBox.text = field?.ID
		genderBox.text = field?.gender
		birthdayBox.text = field?.birthday
		majorBox.text = field?.major
		schoolBox.text = field?.school
	}

	lateinit var password : Password

	@FXML
	private lateinit var nameBox : Label
	@FXML
	private lateinit var idBox : Label
	@FXML
	private lateinit var genderBox : Label
	@FXML
	private lateinit var birthdayBox : Label
	@FXML
	private lateinit var majorBox : Label
	@FXML
	private lateinit var schoolBox : Label

	@FXML
	private fun onCheckBulletin() = Bulletin.check()
	@FXML
	private fun onQueryScore()
	{
		NetworkUtil.sendRequest(QueryScoreRequest().apply {
			id = currentUser!!.ID
			userType = UserType.Student
			password = this@StudentMainPageController.password
		})
		{
			when(it.messageType)
			{
				"ErrorMessage" -> it.toObject<ErrorMessage>().code.alert()
				"QueryScoreResponse" -> it.toObject<QueryScoreResponse>().apply {
					val dataToPass = data.map { StudentScore().apply {
						courseId = it.courseId
						courseName = it.courseName
						teacherId = it.teacherId
						teacherName = it.teacherName
						score = it.score
					} }
					val scene = Scene(ClientResourceManifest.STUDENT_SCORE())
					StudentScoreController.instance.back = ClientApplication.instance.stage.scene
					StudentScoreController.instance.passData(dataToPass)
					ClientApplication.instance.stage.scene = scene
				}
			}
		}
	}
	@FXML
	private fun onQuit()
	{
		System.exit(0)
	}
}