package vhky.javaeeassignment.client.controllers

import javafx.fxml.FXML
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import vhky.javaeeassignment.client.ClientApplication
import vhky.javaeeassignment.client.utils.ClientKey
import vhky.javaeeassignment.client.utils.NetworkUtil
import vhky.javaeeassignment.common.data.Password
import vhky.javaeeassignment.common.data.Teacher
import vhky.javaeeassignment.common.protocol.messageType
import vhky.javaeeassignment.common.protocol.request.RegisterNewTeacherRequest
import vhky.javaeeassignment.common.protocol.response.ErrorMessage
import vhky.javaeeassignment.common.utils.toObject

/**
 * No Description
 * Created by 锟 at 13:46 on 2018/1/9.
 */
class NewTeacherFormController
{
	companion object
	{
		lateinit var instance : NewTeacherFormController
	}

	init
	{
		instance = this
	}

	lateinit var back : Scene
	lateinit var systemManagerPassword : Password

	private @FXML lateinit var idBox : TextField
	private @FXML lateinit var nameBox : TextField
	private @FXML lateinit var passwordBox : PasswordField
	private @FXML lateinit var departmentBox : TextField
	private @FXML lateinit var schoolBox : TextField

	private @FXML fun onBack()
	{
		ClientApplication.instance.stage.scene = back
	}

	private @FXML fun onSubmit()
	{
		val teacher = Teacher().apply()
		{
			ID = idBox.text
			name = nameBox.text
			department = departmentBox.text
			school = schoolBox.text
		}

		val request = RegisterNewTeacherRequest().apply()
		{
			systemManagerPassword = this@NewTeacherFormController.systemManagerPassword
			teacherPassword = passwordBox.text.let { Password(ClientKey(), it) }
			this.teacher = teacher
		}

		NetworkUtil.sendRequest(request)
		{
			when (it.messageType)
			{
				"ErrorMessage" -> Alert(Alert.AlertType.ERROR, "Fail due to ${it.toObject<ErrorMessage>().code}").apply { title = ClientApplication.applicationName }.show()
				"SimpleSuccessResponse" -> Alert(Alert.AlertType.INFORMATION, "Success").apply { title = ClientApplication.applicationName }.show()
			}
		}
	}
}