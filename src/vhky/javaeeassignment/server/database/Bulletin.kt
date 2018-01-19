package vhky.javaeeassignment.server.database

import vhky.javaeeassignment.server.utils.LogUtil
import kotlin.concurrent.thread

/**
 * No Description
 * Created by 锟 at 15:27 on 2018/1/9.
 */
object Bulletin
{
	var text = readLastBulletin()
	set(value)
	{
		field = value
		thread { writeBulletin(value) }
	}
	private fun readLastBulletin() : String
	{
		//TODO: Read
		return "Hello, World!"
	}
	private fun writeBulletin(text : String)
	{
		//TODO: Write
		LogUtil.info("Bulletin was written as :\n$text")
	}
}