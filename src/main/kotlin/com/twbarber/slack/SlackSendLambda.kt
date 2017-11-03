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
		val config = ConfigurationProperties.fromResource("application.properties.example")
		logger.log("Configuration Loaded...")
		SlackService.send("", config[slackWebhookUrl])
		return "Successfully executed Main.handler()"
	}

	fun apiGatewayHandler(request: InputStream, response: OutputStream, context: Context): String {
		val logger = context.logger
		logger.log("Process Running...")
		val config = ConfigurationProperties.fromResource("application.properties.example")
		logger.log("Configuration Loaded...")
		SlackService.send("", config[slackWebhookUrl])
		return "Successfully executed Main.handler()"
	}

}


