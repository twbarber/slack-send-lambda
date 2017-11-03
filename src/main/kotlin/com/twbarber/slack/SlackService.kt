package com.twbarber.slack

import com.amazonaws.services.lambda.runtime.Context
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class SlackService(context: Context) {

    val LOG = context.logger

    fun send(rawMessage: String, webhookUrl: String) {
        val message = SlackMessage(rawMessage)
        val body = FormBody.Builder()
            .add("payload", message.asPostBody())
            .build()
        val request = Request.Builder()
            .url(webhookUrl)
            .post(body)
            .build()

        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) = println("NOT ok")
            override fun onResponse(call: Call, response: Response) {
                LOG.log("Slack Post Succeeded.")
                response.body()?.close()
            }
        })
    }

}

data class SlackMessage(private val content: String) {
    fun asPostBody() = "{\"text\": \"$content\" }"
}
