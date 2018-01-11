package vhky.javaeeassignment.client.utils

import vhky.javaeeassignment.client.ClientResourceManifest
import vhky.javaeeassignment.common.utils.CipherUtil

/**
 * No Description
 * Created by 锟 at 0:24 on 2018/1/9.
 */
object ClientKey
{
	val key by lazy { ClientResourceManifest.CLIENT_KEY<String>()!!.let { CipherUtil.publicKeyDecoder(it) } }
	val encoder by lazy { CipherUtil.publicKeyDecoder(ClientResourceManifest.CLIENT_KEY()!!).let { CipherUtil.getEncoder(it) } }
	operator fun invoke() = encoder
}