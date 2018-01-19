package vhky.javaeeassignment.server

import vhky.javaeeassignment.common.utils.ResourceManager
import vhky.javaeeassignment.common.utils.ResourceManifest

/**
 * No Description
 * Created by 锟 at 1:10 on 2018/1/9.
 */
enum class ServerResourceManifest(private val path : String) : ResourceManifest
{
	SERVER_KEY("server.key"),
	DATABASE_URL("database.url");
	override fun getPath() : String = path
	inline operator fun <reified T : Any> invoke() : T? = ResourceManager.load(this)
}