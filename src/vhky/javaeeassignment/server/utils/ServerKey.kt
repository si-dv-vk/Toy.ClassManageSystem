package vhky.javaeeassignment.server.utils

import vhky.javaeeassignment.server.ServerResourceManifest
import vhky.javaeeassignment.common.utils.CipherUtil

/**
 * No Description
 * Created by 锟 at 1:09 on 2018/1/9.
 */
object ServerKey
{
	val key by lazy { CipherUtil.privateKeyDecoder(ServerResourceManifest.SERVER_KEY()!!) }
	val decoder by lazy { key.let { CipherUtil.getDecoder(it) } }
	operator fun invoke() = decoder
}