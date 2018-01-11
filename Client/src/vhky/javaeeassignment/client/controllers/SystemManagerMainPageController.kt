package vhky.javaeeassignment.client.controllers

import javafx.fxml.FXML
import javafx.scene.Scene
import vhky.javaeeassignment.client.ClientApplication
import vhky.javaeeassignment.client.ClientResourceManifest
import vhky.javaeeassignment.common.data.Password

/**
 * No Description
 * Created by 锟 at 3:11 on 2018/1/9.
 */
class SystemManagerMainPageController
{
	companion object
	{
		lateinit var instance : SystemManagerMainPageController
	}
	init
	{
		instance = this
	}

	lateinit var systemManagerPassword : Password
	private @FXML fun onNewStudent()
	{
		val scene = Scene(ClientResourceManifest.NEW_STUDENT_FORM())
		NewStudentFormController.instance.apply()
		{
			back = ClientApplication.instance.stage.scene
			systemManagerPassword = this@SystemManagerMainPageController.systemManagerPassword
		}
		ClientApplication.instance.stage.scene = scene
	}
	private @FXML fun onNewTeacher()
	{
		val scene = Scene(ClientResourceManifest.NEW_TEACHER_FORM())
		NewTeacherFormController.instance.apply()
		{
			back = ClientApplication.instance.stage.scene
			systemManagerPassword = this@SystemManagerMainPageController.systemManagerPassword
		}
		ClientApplication.instance.stage.scene = scene
	}
	private @FXML fun onQuit() = System.exit(0)
}