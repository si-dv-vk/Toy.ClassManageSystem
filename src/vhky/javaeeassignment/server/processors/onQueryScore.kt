package vhky.javaeeassignment.server.processors

import vhky.javaeeassignment.common.protocol.request.QueryScoreRequest
import vhky.javaeeassignment.common.protocol.response.QueryScoreResponse
import vhky.javaeeassignment.server.database.Database
import vhky.javaeeassignment.server.utils.PasswordUtil

/**
 * No Description
 * Created by 锟 at 13:02 on 2018/1/20.
 */

fun onQueryScore(request : QueryScoreRequest) = PasswordUtil.verifyPassword(request.password,
		{ Database.queryPassword(request.userType, request.id) })
{
	QueryScoreResponse().apply {
		data = Database.queryScore(request.userType, request.id)
	}.toString()
}