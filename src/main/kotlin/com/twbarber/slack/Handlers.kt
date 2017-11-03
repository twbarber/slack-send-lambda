package com.twbarber.slack

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.events.SNSEvent
import com.natpryce.konfig.ConfigurationProperties
import com.twbarber.slack.config.settings.slackWebhookUrl
import java.io.InputStream
import java.io.OutputStream

fun snsHandler(request: SNSEvent, context: Context) {
	val logger = context.logger
	val config = loadConfig()
	logger.log("Configuration Loaded...")
	SlackService.send(context, "", config[slackWebhookUrl])
}

fun apiGatewayHandler(request: InputStream, response: OutputStream, context: Context) {
	val logger = context.logger
	val config = loadConfig()
	logger.log("Configuration Loaded...")
	SlackService.send(context, "", config[slackWebhookUrl])
}

private fun loadConfig(): ConfigurationProperties =
	ConfigurationProperties.fromResource("application.properties")
