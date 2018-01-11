package vhky.javaeeassignment.common.utils

/**
 * No Description
 * Created by 锟 at 8:09 on 2018/1/9.
 */

object Base64Util
{
	private val encoder by lazy { sun.misc.BASE64Encoder() }
	private val decoder by lazy { sun.misc.BASE64Decoder() }
	fun enbase64(text : String) = encoder.encode(text.toByteArray())!!
	fun debase64(text : String) = decoder.decodeBuffer(text).let { String(it!!) }
}