package vhky.javaeeassignment.client

import vhky.javaeeassignment.common.utils.ResourceManager
import vhky.javaeeassignment.common.utils.ResourceManifest

/**
 * No Description
 * Created by 锟 at 0:28 on 2018/1/9.
 */
enum class ClientResourceManifest(private val path : String) : ResourceManifest
{
	LOGIN("login.fxml"),
	UESTC_LOGO("uestc_logo.jpg"),
	CLIENT_KEY("client.key"),
	LOGIN_PAGE_COVER("login_cover.jpg"),
	STUDENT_MAIN("student_main.fxml"),
	TEACHER_MAIN("teacher_main.fxml"),
	SYSTEM_MANAGER_MAIN("system_manager_main.fxml"),
	TEACHING_MANAGER_MAIN("teaching_manager_main.fxml"),
	SERVER_URL("server.url"),
	NEW_STUDENT_FORM("new_student_form.fxml"),
	NEW_TEACHER_FORM("new_teacher_form.fxml"),
	BULLETIN_BOARD("bulletin_board.fxml"),
	STUDENT_SCORE("student_score.fxml"),
	TEACHER_SCORE("teacher_score.fxml");

	override fun getPath() : String = path
	inline operator fun <reified T : Any> invoke() : T? = ResourceManager.load(this)
}