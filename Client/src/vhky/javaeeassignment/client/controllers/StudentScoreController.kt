package vhky.javaeeassignment.client.controllers

import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.Scene
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import vhky.javaeeassignment.client.ClientApplication
import vhky.javaeeassignment.client.data.StudentScore

/**
 * No Description
 * Created by 锟 at 22:54 on 2018/1/18.
 */
class StudentScoreController
{
	companion object
	{
		lateinit var instance : StudentScoreController
	}
	init
	{
		instance = this
	}
	lateinit var back : Scene
	private @FXML lateinit var tableView : TableView<StudentScore>
	//mapping = display to property
	private val dataMapping = mapOf("Course Name" to "courseName", "Course ID" to "courseId", "Teacher Name" to "teacherName",
			"Teacher ID" to "teacherId", "Score" to "score")
	private @FXML fun initialize()
	{
		val columns = FXCollections.observableArrayList<TableColumn<StudentScore, String>>()
		dataMapping.forEach { display, property -> columns.add(TableColumn<StudentScore, String>(display).apply ()
		{
			cellValueFactory = PropertyValueFactory(property)
			this.prefWidth = 116.0
			this.style = "-fx-alignment : CENTER;"
		}) }
		tableView.columns.addAll(columns)
		tableView.isEditable = false
	}
	fun passData(data : List<StudentScore>)
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