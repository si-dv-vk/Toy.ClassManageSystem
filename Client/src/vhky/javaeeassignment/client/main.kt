package vhky.javaeeassignment.client

import javafx.application.Application
import vhky.javaeeassignment.common.utils.ResourceManager

/**
 * No Description
 * Created by 锟 at 23:12 on 2018/1/7.
 */

fun main(args : Array<String>)
{
	ResourceManager.apply()
	{
		root = ClientApplication::class
		offset = "resources/"
	}
	Application.launch(ClientApplication::class.java, *args)
}