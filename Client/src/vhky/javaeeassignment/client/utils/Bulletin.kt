package vhky.javaeeassignment.client.utils

import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import vhky.javaeeassignment.client.ClientApplication
import vhky.javaeeassignment.common.misc.ErrorCode
import vhky.javaeeassignment.common.protocol.messageType
import vhky.javaeeassignment.common.protocol.request.CheckBulletinRequest
import vhky.javaeeassignment.common.protocol.response.CheckBulletinResponse
import vhky.javaeeassignment.common.protocol.response.ErrorMessage
import vhky.javaeeassignment.common.utils.toObject

/**
 * No Description
 * Created by 锟 at 7:54 on 2018/1/9.
 */

object Bulletin
{
	fun check()
	{
		NetworkUtil.sendRequest(CheckBulletinRequest())
		{
			when(it.messageType)
			{
				"CheckBulletinResponse" ->
					AlertTool.alert(it.toObject<CheckBulletinResponse>().text, header = "Bulletin")
				"ErrorMessage" -> it.toObject<ErrorMessage>().code.alert()
				else -> ErrorCode.Unknown.alert()
			}
		}
	}
}