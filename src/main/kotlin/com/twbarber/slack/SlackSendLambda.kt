package com.twbarber.slack

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.events.SNSEvent
import com.natpryce.konfig.ConfigurationProperties
import com.twbarber.slack.config.settings.slackWebhookUrl
import java.io.InputStream
import java.io.OutputStream

object SlackSendLambda {

	fun snsHandler(request: SNSEvent, context: Context): String {
		val logger = context.logger
		logger.log("Process Running...")
		val config = loadConfig()
		logger.log("Configuration Loaded...")

		SlackService.send("", config[slackWebhookUrl])
		return "SlackSendLambda.snsHandler Success"
	}

	fun apiGatewayHandler(request: InputStream, response: OutputStream, context: Context): String {
		val logger = context.logger
		logger.log("Process Running...")
		val config = loadConfig()
		logger.log("Configuration Loaded...")

		SlackService.send("", config[slackWebhookUrl])
		return "SlackSendLambda.apiGatewayHandler Success"
	}

	private fun loadConfig(): ConfigurationProperties {
		return ConfigurationProperties.fromResource("application.properties")
	}

}


