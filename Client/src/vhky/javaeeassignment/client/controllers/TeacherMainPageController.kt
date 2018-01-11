package vhky.javaeeassignment.client.controllers

import javafx.fxml.FXML
import javafx.scene.control.Label
import vhky.javaeeassignment.client.utils.Bulletin
import vhky.javaeeassignment.common.data.Teacher

/**
 * No Description
 * Created by 锟 at 3:11 on 2018/1/9.
 */
class TeacherMainPageController
{
	companion object
	{
		lateinit var instance : TeacherMainPageController
	}
	init
	{
		instance = this
	}
	var currentUser : Teacher? = null
	set(value)
	{
		field = value
		nameBox.text = value?.name
		idBox.text = value?.ID
		departmentBox.text = value?.department
		schoolBox.text = value?.school
	}

	private @FXML lateinit var nameBox : Label
	private @FXML lateinit var idBox : Label
	private @FXML lateinit var departmentBox : Label
	private @FXML lateinit var schoolBox : Label

	private @FXML fun onQuit() = System.exit(0)
	private @FXML fun onQueryClass() = Unit
	private @FXML fun onCheckBulletin() = Bulletin.check()
}