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
import vhky.javaeeassignment.common.data.prototype.Teacher
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

	@FXML
	private lateinit var idBox : TextField
	@FXML
	private lateinit var nameBox : TextField
	@FXML
	private lateinit var passwordBox : PasswordField
	@FXML
	private lateinit var departmentBox : TextField
	@FXML
	private lateinit var schoolBox : TextField

	@FXML
	private fun onBack()
	{
		ClientApplication.instance.stage.scene = back
	}

	@FXML
	private fun onSubmit()
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