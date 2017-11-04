package com.twbarber.slack

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.events.SNSEvent
import java.io.InputStream
import java.io.OutputStream


class Main {

	companion object {
		private const val WEBHOOK_URL_KEY = "WEBHOOK_URL"
	}

	private val webhookUrl = System.getenv(WEBHOOK_URL_KEY)

	fun snsHandler(event: SNSEvent, context: Context): String {
		val logger = context.logger
		logger.log(event.toString())
		logger.log("SNS Configuration Loaded: $webhookUrl")
		return SlackService.send(context, "snsHandler", webhookUrl)
	}

	fun apiGatewayHandler(request: InputStream, response: OutputStream, context: Context): String {
		val logger = context.logger
		logger.log("API Gateway Configuration Loaded... $webhookUrl")
		return SlackService.send(context, "apiGatewayHandler", webhookUrl)
	}

}
