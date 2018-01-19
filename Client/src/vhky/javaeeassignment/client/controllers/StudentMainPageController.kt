package vhky.javaeeassignment.client.controllers

import javafx.fxml.FXML
import javafx.scene.Scene
import javafx.scene.control.Label
import vhky.javaeeassignment.client.ClientApplication
import vhky.javaeeassignment.client.ClientResourceManifest
import vhky.javaeeassignment.client.data.StudentScore
import vhky.javaeeassignment.client.utils.Bulletin
import vhky.javaeeassignment.common.data.Student

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

	private @FXML lateinit var nameBox : Label
	private @FXML lateinit var idBox : Label
	private @FXML lateinit var genderBox : Label
	private @FXML lateinit var birthdayBox : Label
	private @FXML lateinit var majorBox : Label
	private @FXML lateinit var schoolBox : Label

	private @FXML fun onCheckBulletin() = Bulletin.check()
	private @FXML fun onQueryScore()
	{
		val scene = Scene(ClientResourceManifest.STUDENT_SCORE())
		//TODO : Get data from server
		val test = StudentScore().apply()
		{
			courseId = "CourseID"
			courseName = "Fuck"
			teacherId = "TeacherID"
			teacherName = "Bitch"
			score = "100"
		}
		StudentScoreController.instance.back = ClientApplication.instance.stage.scene
		StudentScoreController.instance.passData(List(100, { test }))
		ClientApplication.instance.stage.scene = scene


	}
	private @FXML fun onQuit()
	{
		System.exit(0)
	}

}