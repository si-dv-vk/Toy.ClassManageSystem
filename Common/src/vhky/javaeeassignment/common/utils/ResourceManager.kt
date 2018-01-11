package vhky.javaeeassignment.common.utils

import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.image.Image
import java.net.URL
import kotlin.reflect.KClass
import java.io.ObjectInputStream

/**
 * No Description
 * Created by 锟 at 23:24 on 2018/1/7.
 */
object ResourceManager
{
	var root : KClass<*> = this::class
	var offset = ""
	inline fun <reified T : Any> load(resource : ResourceManifest) = root.java.
			getResource(offset + resource.getPath()).let { T::class.resourceLoadMethod(it) }
}

interface ResourceManifest
{
	fun getPath() : String
}

inline fun <reified T : Any> KClass<T>.resourceLoadMethod(url : URL) : T? = when(this)
{
	Parent::class, Node::class -> FXMLLoader.load(url) as? T
	Image::class -> Image(url.openStream()) as? T
	String::class -> url.openStream().bufferedReader().readText() as? T
	ByteArray::class -> url.openStream().bufferedReader().readText().toByteArray() as? T
	else -> url.openStream().let { ObjectInputStream(it) }.use { it.readObject() } as? T
}
