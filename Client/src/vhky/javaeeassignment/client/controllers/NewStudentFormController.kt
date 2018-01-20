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
import vhky.javaeeassignment.common.data.prototype.Student
import vhky.javaeeassignment.common.protocol.messageType
import vhky.javaeeassignment.common.protocol.request.RegisterNewStudentRequest
import vhky.javaeeassignment.common.protocol.response.ErrorMessage
import vhky.javaeeassignment.common.utils.toObject

/**
 * No Description
 * Created by 锟 at 13:45 on 2018/1/9.
 */
class NewStudentFormController
{
	companion object
	{
		lateinit var instance : NewStudentFormController
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
	private lateinit var genderBox : TextField
	@FXML
	private lateinit var birthdayBox : TextField
	@FXML
	private lateinit var majorBox : TextField
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
		val student = Student().apply()
		{
			ID = idBox.text
			name = nameBox.text
			gender = genderBox.text
			birthday = birthdayBox.text
			major = majorBox.text
			school = schoolBox.text
		}

		val request = RegisterNewStudentRequest().apply()
		{
			systemManagerPassword = this@NewStudentFormController.systemManagerPassword
			studentPassword = passwordBox.text.let { Password(ClientKey(), it) }
			this.student = student
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