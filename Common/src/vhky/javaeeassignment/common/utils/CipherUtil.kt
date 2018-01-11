package vhky.javaeeassignment.common.utils

import java.security.Key
import java.security.KeyFactory
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

/**
 * No Description
 * Created by 锟 at 22:50 on 2018/1/8.
 */

object CipherUtil
{
	val KEY_SIZE = 1024
	val KEY_ALGORITHM = "RSA"
	private val CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding"

	val publicKeyDecoder = { key : String -> key.let { base64decoder.decodeBuffer(it) }.let { X509EncodedKeySpec(it).let { keyFactory.generatePublic(it)!! } }}
	val privateKeyDecoder = { key : String -> key.let { base64decoder.decodeBuffer(it) }.let { PKCS8EncodedKeySpec(it).let { keyFactory.generatePrivate(it)!! } }}

	private val base64encoder by lazy { sun.misc.BASE64Encoder() }
	private val base64decoder by lazy { sun.misc.BASE64Decoder() }
	private fun RSACipher() = Cipher.getInstance(CIPHER_ALGORITHM)
	private val keyFactory by lazy { KeyFactory.getInstance(KEY_ALGORITHM) }

	fun getEncoder(key : Key) : (String) -> String
	{
		val cipher = RSACipher().apply { init(Cipher.ENCRYPT_MODE, key) }
		return { source : String -> cipher.doFinal(source.toByteArray()).let { base64encoder.encode(it) } }
	}

	fun getDecoder(key : Key) : (String) -> String
	{
		val cipher = RSACipher().apply { init(Cipher.DECRYPT_MODE, key) }
		return { source : String -> base64decoder.decodeBuffer(source).let { cipher.doFinal(it) }.let { String(it) } }
	}
}