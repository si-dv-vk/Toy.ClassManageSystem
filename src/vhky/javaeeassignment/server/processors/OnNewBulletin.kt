package vhky.javaeeassignment.server.processors

import vhky.javaeeassignment.common.data.UserType
import vhky.javaeeassignment.common.protocol.request.PublishBulletinRequest
import vhky.javaeeassignment.common.protocol.response.SimpleSuccessResponse
import vhky.javaeeassignment.server.database.Bulletin
import vhky.javaeeassignment.server.database.TheDatabase
import vhky.javaeeassignment.server.utils.PasswordUtil

/**
 * No Description
 * Created by 锟 at 15:26 on 2018/1/9.
 */

fun onNewBulletin(request : PublishBulletinRequest) = PasswordUtil.verifyPassword(request.teachingManagerPassword,
		{ TheDatabase.queryPassword(UserType.TeachingManager, "") })
{
	Bulletin.text = request.bulletin
	SimpleSuccessResponse().toString()
}