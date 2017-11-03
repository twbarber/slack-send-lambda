package com.twbarber.slack.config

import com.natpryce.konfig.PropertyGroup
import com.natpryce.konfig.getValue
import com.natpryce.konfig.stringType

object settings : PropertyGroup() {
    val slackWebhookUrl by stringType

}