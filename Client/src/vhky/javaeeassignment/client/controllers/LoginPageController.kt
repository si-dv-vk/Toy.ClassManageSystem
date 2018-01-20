package vhky.javaeeassignment.client.controllers

import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.image.ImageView
import vhky.javaeeassignment.client.ClientApplication
import vhky.javaeeassignment.client.ClientResourceManifest
import vhky.javaeeassignment.client.utils.ClientKey
import vhky.javaeeassignment.client.utils.NetworkUtil
import vhky.javaeeassignment.client.utils.alert
import vhky.javaeeassignment.common.data.Password
import vhky.javaeeassignment.common.data.UserType
import vhky.javaeeassignment.common.data.prototype.Student
import vhky.javaeeassignment.common.data.prototype.Teacher
import vhky.javaeeassignment.common.misc.ErrorCode
import vhky.javaeeassignment.common.protocol.messageType
import vhky.javaeeassignment.common.protocol.request.LoginRequest
import vhky.javaeeassignment.common.protocol.response.ErrorMessage
import vhky.javaeeassignment.common.protocol.response.LoginResponse
import vhky.javaeeassignment.common.utils.toObject

/**
 * No Description
 * Created by 锟 at 1:06 on 2018/1/8.
 */

class LoginPageController
{
	@FXML private fun initialize()
	{
		identitySelectionBox.items = FXCollections.observableArrayList(*UserType.values())
		identitySelectionBox.value = UserType.Student
		cover.image =ClientResourceManifest.LOGIN_PAGE_COVER()
		loginButton.isDefaultButton = true
	}
	@FXML private lateinit var cover : ImageView
	@FXML private lateinit var identitySelectionBox : ChoiceBox<UserType>
	@FXML private lateinit var usernameBox : TextField
	@FXML private lateinit var passwordBox : PasswordField
	@FXML private lateinit var loginButton : Button

	private val UserType.destination : ClientResourceManifest get() = when(this)
	{
		UserType.Student -> ClientResourceManifest.STUDENT_MAIN
		UserType.Teacher -> ClientResourceManifest.TEACHER_MAIN
		UserType.SystemManager -> ClientResourceManifest.SYSTEM_MANAGER_MAIN
		UserType.TeachingManager -> ClientResourceManifest.TEACHING_MANAGER_MAIN
	}

	@FXML private fun onLogin()
	{
		loginButton.isDisable = true

		val request = LoginRequest()
		val password = Password(ClientKey.encoder, passwordBox.text)
		request.userType = identitySelectionBox.value
		request.id = usernameBox.text
		request.password = password
		NetworkUtil.sendRequest(request, {
			when (it.messageType)
			{
				"ErrorMessage" ->
				{
					it.toObject<ErrorMessage>().code.alert()
					loginButton . isDisable = false
				}
				"LoginResponse" ->
				{
					val response = it.toObject<LoginResponse>()
					ClientApplication.instance.stage.scene = Scene(response.type.destination())
					when(response.type)
					{
						UserType.Student -> StudentMainPageController.instance.apply {
							currentUser = response.userData.toObject<Student>()
							this.password = password
						}
						UserType.Teacher -> TeacherMainPageController.instance.apply {
							currentUser = response.userData.toObject<Teacher>()
							this.password = password
						}
						UserType.SystemManager -> SystemManagerMainPageController.instance.systemManagerPassword = password
						UserType.TeachingManager -> TeachingManagerMainPageController.instance.teachingManagerPassword = password
					}
				}
				else -> ErrorCode.Unknown.alert()
			}
		})
	}
}