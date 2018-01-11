package vhky.javaeeassignment.tools

import vhky.javaeeassignment.common.utils.CipherUtil
import java.io.File
import java.security.KeyPairGenerator
import java.security.SecureRandom

/**
 * No Description
 * Created by 锟 at 22:36 on 2018/1/8.
 */

val publicKeyFile = { """C:\Users\锟\Desktop\client.key""".let { File(it) }.apply { if (this.exists().not()) createNewFile() } }
val privateKeyFile = { """C:\Users\锟\Desktop\server.key""".let { File(it) }.apply { if (this.exists().not()) createNewFile() } }
val base64encoder by lazy { sun.misc.BASE64Encoder() }
val base64decoder by lazy { sun.misc.BASE64Decoder() }

fun main(args : Array<String>)
{
	val generator = KeyPairGenerator.getInstance(CipherUtil.KEY_ALGORITHM)
	generator.initialize(CipherUtil.KEY_SIZE, SecureRandom())
	val keyPair = generator.genKeyPair()

	publicKeyFile().bufferedWriter().use { it.write(keyPair.public.encoded.let { base64encoder.encode(it) }) }
	privateKeyFile().bufferedWriter().use { it.write(keyPair.private.encoded.let { base64encoder.encode(it) }) }
}