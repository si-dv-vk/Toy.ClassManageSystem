package vhky.javaeeassignment.server.utils

import java.util.logging.Logger

/**
 * No Description
 * Created by 锟 at 22:35 on 2018/1/10.
 */
object LogUtil
{
	val logger by lazy { Logger.getAnonymousLogger()!! }
	fun info(message : String) = logger.info(message)
	fun warning(message : String) = logger.warning(message)
	fun error(message : String) = logger.severe(message)
}