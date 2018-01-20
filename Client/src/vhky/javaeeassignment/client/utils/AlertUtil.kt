package vhky.javaeeassignment.client.utils

import javafx.scene.control.Alert
import vhky.javaeeassignment.client.ClientApplication
import vhky.javaeeassignment.common.misc.ErrorCode
import vhky.javaeeassignment.common.protocol.response.ErrorMessage

/**
 * No Description
 * Created by 锟 at 22:02 on 2018/1/10.
 */

object AlertUtil
{
	enum class Mode { Block, NonBlock }
	fun alert(content : String, type : Alert.AlertType = Alert.AlertType.INFORMATION,
	          mode : Mode = Mode.Block, header : String = "")
	{
		Alert(type).apply()
		{
			title = ClientApplication.applicationName
			contentText = content
			header.takeIf { it != "" }?.let { headerText = it }
		}.let()
		{
			when(mode)
			{
				Mode.Block -> it.showAndWait()
				Mode.NonBlock -> it.show()
			}
		}
	}
	fun error(code : ErrorCode) = alert(code.hint, type = Alert.AlertType.ERROR)
}
fun ErrorCode.alert() = AlertUtil.error(this)
fun ErrorMessage.alert() = this.code.alert()