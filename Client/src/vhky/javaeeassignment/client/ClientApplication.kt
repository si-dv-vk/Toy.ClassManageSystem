package vhky.javaeeassignment.client

/**
 * No Description
 * Created by 锟 at 22:50 on 2018/1/7.
 */

import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage

class ClientApplication : Application()
{
	companion object
	{
		lateinit var instance : ClientApplication
		const val applicationName = "UESTC Class Manager"
	}
	init
	{
		instance = this
	}
	lateinit var stage : Stage
	override fun start(stage : Stage)
	{
		this.stage = stage
		stage.scene = Scene(ClientResourceManifest.LOGIN())
		stage.icons.add(ClientResourceManifest.UESTC_LOGO())
		stage.title = applicationName
		stage.isResizable = false
		stage.show()
	}
}
