package vhky.javaeeassignment.client.controllers

import javafx.fxml.FXML
import javafx.scene.Scene
import vhky.javaeeassignment.client.ClientApplication
import vhky.javaeeassignment.client.ClientResourceManifest
import vhky.javaeeassignment.common.data.Password

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
	private @FXML fun onPublishBulletin()
	{
		val scene = Scene(ClientResourceManifest.BULLETIN_BOARD())
		BulletinBoardController.instance.apply()
		{
			back = ClientApplication.instance.stage.scene
			teachingManagerPassword = this@TeachingManagerMainPageController.teachingManagerPassword
		}
		ClientApplication.instance.stage.scene = scene
	}
	private @FXML fun onPrintScores() = Unit
	private @FXML fun onQuit() = System.exit(0)
}