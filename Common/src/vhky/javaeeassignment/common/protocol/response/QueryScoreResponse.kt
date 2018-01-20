package vhky.javaeeassignment.common.protocol.response

import org.json.JSONArray
import org.json.JSONObject
import vhky.javaeeassignment.common.data.transfer.ScoreQueryRecord
import vhky.javaeeassignment.common.protocol.NetworkMessage
import vhky.javaeeassignment.common.utils.toJSON
import vhky.javaeeassignment.common.utils.toObject

/**
 * No Description
 * Created by 锟 at 11:56 on 2018/1/20.
 */
class QueryScoreResponse : NetworkMessage<QueryScoreResponse>("QueryScoreResponse")
{
	lateinit var data : List<ScoreQueryRecord>
	override val _toJSON : JSONObject.() -> Unit get() = {
		JSONArray().apply { data.forEach { put(it.toJSON()) } }.let { put("data", it) }
	}
	override val _fromJSON : QueryScoreResponse.(JSONObject) -> Unit get() = {
		data = it.getJSONArray("data").let { json -> (0 until json.length())
				.map { json.getJSONObject(it).toObject<ScoreQueryRecord>() } }
	}
}