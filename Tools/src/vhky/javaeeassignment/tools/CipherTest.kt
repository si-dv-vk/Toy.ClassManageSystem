package vhky.javaeeassignment.tools

import vhky.javaeeassignment.common.utils.CipherUtil

/**
 * No Description
 * Created by 锟 at 23:22 on 2018/1/8.
 */

fun main(args : Array<String>)
{
	val decoder = CipherUtil.getDecoder(privateKeyFile().readText().let { CipherUtil.privateKeyDecoder(it) })

	println("Decoded Text = ${decoder("""Akqr61PQeD9GhYYOWBEziFc+Q8vv+AmxiM9X0UAAPBka43k94mpi0L8/qg8jnZB1vkTiC+9SvjWm
aJ9UWyhsPvMnKIsQegjlySxWcsdOcBqFWl3OZAW+zp4qOV4orqOB79QY7EcQZGtLA5NADSJsmmSm
7pSQUkgMetxuRps3O50=""")}")
}