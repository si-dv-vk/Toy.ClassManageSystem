package vhky.javaeeassignment.client.controllers

import javafx.fxml.FXML
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.TextArea
import vhky.javaeeassignment.client.ClientApplication
import vhky.javaeeassignment.client.utils.AlertTool
import vhky.javaeeassignment.client.utils.NetworkUtil
import vhky.javaeeassignment.client.utils.alert
import vhky.javaeeassignment.common.data.Password
import vhky.javaeeassignment.common.protocol.messageType
import vhky.javaeeassignment.common.protocol.request.PublishBulletinRequest
import vhky.javaeeassignment.common.protocol.response.ErrorMessage
import vhky.javaeeassignment.common.utils.toObject

/**
 * No Description
 * Created by 锟 at 15:04 on 2018/1/9.
 */
class BulletinBoardController
{
	companion object
	{
		lateinit var instance : BulletinBoardController
	}
	init
	{
		instance = this
	}

	lateinit var back : Scene
	lateinit var teachingManagerPassword : Password

	private @FXML lateinit var bulletinEditor : TextArea

	private @FXML fun onBack()
	{
		ClientApplication.instance.stage.scene = back
	}
	private @FXML fun onPublish()
	{
		val request = PublishBulletinRequest()
		request.bulletin =bulletinEditor.text
		request.teachingManagerPassword = teachingManagerPassword

		NetworkUtil.sendRequest(request)
		{
			when (it.messageType)
			{
				"ErrorMessage" -> it.toObject<ErrorMessage>().code.alert()
				"SimpleSuccessResponse" -> AlertTool.alert("Success!")
			}
		}
	}
}