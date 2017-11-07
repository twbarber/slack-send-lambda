package com.twbarber.slack

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.events.SNSEvent
import com.twbarber.slack.amazon.message
import java.io.InputStream
import java.io.OutputStream


class Handlers(private val slack: SlackService = SlackService()) {

	private val webhookUrl = System.getProperty("WEBHOOK_URL")

	fun snsHandler(event: SNSEvent, context: Context) {
		val logger = context.logger
		logger.log(event.toString())
		logger.log("SNS Configuration Loaded: $webhookUrl")
		event.getMessages().forEach { slack.send(context, it, webhookUrl) }
	}

	fun apiGatewayHandler(request: InputStream, response: OutputStream, context: Context) {
		val logger = context.logger
		logger.log("API Gateway Configuration Loaded... $webhookUrl")
		slack.send(context, "apiGatewayHandler", webhookUrl)
	}

	private fun SNSEvent.getMessages() = this.records.flatMap { listOf(it.sns.message) }

}
