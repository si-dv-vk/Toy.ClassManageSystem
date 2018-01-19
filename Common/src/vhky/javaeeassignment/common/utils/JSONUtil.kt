package vhky.javaeeassignment.common.utils

import org.json.JSONObject
import kotlin.reflect.full.primaryConstructor

/**
 * No Description
 * Created by 锟 at 1:31 on 2018/1/9.
 */

fun JSONConvertible<*>.toJSON() = JSONObject().apply { _toJSON() }
inline fun <reified T : JSONConvertible<T>> JSONObject.toObject() = T::class.primaryConstructor!!.call().apply { _fromJSON(this@toObject) }

interface JSONConvertible<T : JSONConvertible<T>>
{
	val _toJSON : JSONObject.() -> Unit
	val _fromJSON : T.(JSONObject) -> Unit
}