package vhky.javaeeassignment.server

import org.json.JSONObject
import vhky.javaeeassignment.common.utils.Base64Util
import vhky.javaeeassignment.common.utils.ResourceManager
import vhky.javaeeassignment.server.processors.Dispatcher
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * No Description
 * Created by 锟 at 0:48 on 2018/1/9.
 */

@WebServlet(name = "ClassManager", urlPatterns = ["/"], loadOnStartup = 1)
class ClassManagerServer : HttpServlet()
{
	override fun init()
	{
		ResourceManager.apply()
		{
			root = ServerResourceManifest::class
			offset = "resources/"
		}
	}
	override fun doPost(req : HttpServletRequest?, resp : HttpServletResponse?)
	{
		if (req == null || resp == null) return
		val request = JSONObject(req.inputStream.bufferedReader().use{ it.readText() }.let { Base64Util.debase64(it) })
		resp.writer.takeIf { it!=null }?.let()
		{
			it.write(Dispatcher.dispatch(request).let { Base64Util.enbase64(it) })
			resp.status = 200
		}?:apply{resp.status = 500 }
	}

	override fun doGet(req : HttpServletRequest?, resp : HttpServletResponse?)
	{
		resp?.apply { this.writer.println("Class Manager Server") }
	}
}