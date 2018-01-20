package vhky.javaeeassignment.client.controllers

import javafx.fxml.FXML
import javafx.scene.Scene
import javafx.stage.FileChooser
import vhky.javaeeassignment.client.ClientApplication
import vhky.javaeeassignment.client.ClientResourceManifest
import vhky.javaeeassignment.client.utils.AlertUtil
import vhky.javaeeassignment.client.utils.NetworkUtil
import vhky.javaeeassignment.client.utils.alert
import vhky.javaeeassignment.common.data.Password
import vhky.javaeeassignment.common.data.UserType
import vhky.javaeeassignment.common.protocol.messageType
import vhky.javaeeassignment.common.protocol.request.QueryScoreRequest
import vhky.javaeeassignment.common.protocol.response.ErrorMessage
import vhky.javaeeassignment.common.protocol.response.QueryScoreResponse
import vhky.javaeeassignment.common.utils.toObject

/**
 * No Description
 * Created by 锟 at 12:50 on 2018/1/9.
 */
class TeachingManagerMainPageController
{
	companion object
	{
		lateinit var instance : TeachingManagerMainPageController
	}
	init
	{
		instance = this
	}

	lateinit var teachingManagerPassword : Password
	@FXML
	private fun onPublishBulletin()
	{
		val scene = Scene(ClientResourceManifest.BULLETIN_BOARD())
		BulletinBoardController.instance.apply()
		{
			back = ClientApplication.instance.stage.scene
			teachingManagerPassword = this@TeachingManagerMainPageController.teachingManagerPassword
		}
		ClientApplication.instance.stage.scene = scene
	}
	@FXML
	private fun onPrintScores()
	{
		NetworkUtil.sendRequest(QueryScoreRequest().apply {
			id = ""
			userType = UserType.TeachingManager
			password = teachingManagerPassword
		})
		{
			when (it.messageType)
			{
				"ErrorMessage" -> it.toObject<ErrorMessage>().alert()
				"QueryScoreResponse" -> it.toObject<QueryScoreResponse>().let()
				{
					response ->
					val fileChooser = FileChooser()
					fileChooser.title = "Save Score Data File To ...."
					fileChooser.extensionFilters.add(FileChooser.ExtensionFilter("Comma-Separated Values", ".csv"))
					val file = fileChooser.showSaveDialog(ClientApplication.instance.stage)
					if (!file.exists()) file.createNewFile()
					file.bufferedWriter().use {
						writer -> writer.write(listOf("Course Name", "Course ID",
							"Teacher Name", "Teacher ID",
							"Student Name", "Student ID", "Score").joinToString(","))
						writer.newLine()
						response.data.forEach { record ->
							writer.write(listOf(record.courseName, record.courseId,
									record.teacherName, record.teacherId,
									record.studentName, record.studentId, record.score).joinToString(","))
							writer.newLine()
						}
					}
					AlertUtil.alert("Success!")
				}
			}
		}
	}
	@FXML
	private fun onQuit() = System.exit(0)
}