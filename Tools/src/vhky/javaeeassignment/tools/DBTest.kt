package vhky.javaeeassignment.tools

import java.sql.DriverManager

/**
 * No Description
 * Created by 锟 at 16:54 on 2018/1/18.
 */

fun main(args : Array<String>)
{
	val connection = DriverManager.getConnection("""jdbc:sqlite:\\C:\Users\锟\Desktop\javaee_assignment.db""")
	connection?.let()
	{
		it.createStatement()!!.use()
		{
			val line = emptyList<String>().toMutableList()
			it.executeQuery("select * from Student").let { result ->
				val columnCount = result.metaData.columnCount
				(1..columnCount).forEach { result.metaData.getColumnName(it).let { line.add(it) } }
				line.joinToString().let { println(it) }
				line.clear()
				while (result.next() == true)
				{
					(1..columnCount).forEach { line.add(result.getString(it)) }
					line.joinToString().let { println(it) }
					line.clear()
				}
			}
		}
	}?: println("Error: Data base not found!")
}