package vhky.javaeeassignment.client.controllers

import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.Scene
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import vhky.javaeeassignment.client.ClientApplication
import vhky.javaeeassignment.client.data.TeacherScore

/**
 * No Description
 * Created by 锟 at 22:01 on 2018/1/18.
 */
class TeacherScoreController
{
	companion object
	{
		lateinit var instance : TeacherScoreController
	}
	init
	{
		instance = this
	}
	lateinit var back : Scene
	@FXML	private lateinit var tableView : TableView<TeacherScore>
	//mapping = display to property
	private val dataMapping = mapOf("Course Name" to "courseName", "Course ID" to "courseId", "Student Name" to "studentName",
			"Student ID" to "studentId", "Score" to "score")
	@FXML	private fun initialize()
	{
		val columns = FXCollections.observableArrayList<TableColumn<TeacherScore, String>>()
		dataMapping.forEach { display, property -> columns.add(TableColumn<TeacherScore, String>(display).apply ()
		{
			cellValueFactory = PropertyValueFactory(property)
			this.prefWidth = 116.0
			this.style = "-fx-alignment : CENTER;"
		}) }
		tableView.columns.addAll(columns)
		tableView.isEditable = false
	}
	fun passData(data : List<TeacherScore>)
	{
		tableView.items.apply()
		{
			clear()
			addAll(data)
		}
	}
	@FXML fun onBack()
	{
		ClientApplication.instance.stage.scene = back
	}
}