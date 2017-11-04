package com.twbarber.slack

import com.amazonaws.services.lambda.runtime.Context
import java.io.InputStream
import java.io.OutputStream


class Main {

	companion object {
		private const val WEBHOOK_URL_KEY = "webhookUrl"
	}

	fun snsHandler(context: Context): String {
		val logger = context.logger
		val webhookUrl = System.getenv(WEBHOOK_URL_KEY)
		logger.log("SNS Configuration Loaded... $webhookUrl")
		return "snsHandler Complete"
		// SlackService.send(context, "", config[slackWebhookUrl])
	}

	fun apiGatewayHandler(request: InputStream, response: OutputStream, context: Context): String {
		val logger = context.logger
		val webhookUrl = System.getenv(WEBHOOK_URL_KEY)
		logger.log("API Gateway Configuration Loaded... $webhookUrl")
		return response.write("apiGatewayHandler Complete".toByteArray(Charsets.UTF_8)).toString()
		// SlackService.send(context, "", config[slackWebhookUrl])
	}

}
