package com.twbarber.slack

import com.amazonaws.services.lambda.runtime.Context
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


open class SlackService(private val httpClient: OkHttpClient = OkHttpClient()) {

    open fun send(context: Context, rawMessage: String, webhookUrl: String) {
        val message = SlackMessage(rawMessage)
        val body = FormBody.Builder()
            .add("payload", message.asPostBody())
            .build()
        val request = Request.Builder()
            .url(webhookUrl)
            .post(body)
            .build()

        httpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) = context.logger.log("Slack Post Failed.")
            override fun onResponse(call: Call, response: Response) {
                context.logger.log("Slack Post Succeeded.")
                response.body()?.close()
            }
        })
    }
}

data class SlackMessage(private val content: String) {
    fun asPostBody() = "{\"text\": \"$content\" }"
}
