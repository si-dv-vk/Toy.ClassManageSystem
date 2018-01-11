package vhky.javaeeassignment.common.data

/**
 * No Description
 * Created by 锟 at 0:45 on 2018/1/8.
 */
enum class UserType(private val displayName : String)
{
	Student("Student"),
	Teacher("Teacher"),
	SystemManager("System Manager"),
	TeachingManager("Teaching Manager");

	override fun toString() : String = displayName
}

@Target(AnnotationTarget.CLASS)
annotation class Identity(val identity : UserType)