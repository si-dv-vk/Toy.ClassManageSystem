package vhky.javaeeassignment.server.processors

import vhky.javaeeassignment.common.protocol.response.CheckBulletinResponse
import vhky.javaeeassignment.server.database.Bulletin

/**
 * No Description
 * Created by 锟 at 7:50 on 2018/1/9.
 */

fun onCheckBulletin() : String
{
	val response = CheckBulletinResponse()
	response.text = Bulletin.text
	return response.toString()
}