package com.twbarber.slack.amazon

import com.amazonaws.services.lambda.runtime.events.SNSEvent
import com.amazonaws.services.lambda.runtime.events.SNSEvent.SNS
import com.amazonaws.services.lambda.runtime.events.SNSEvent.SNSRecord


fun SNSRecord.withMessage(message: String) : SNSEvent.SNSRecord =
	when (this.sns) {
		null -> { this.setSns(SNS().withMessage(message)); this }
		else ->  { this.sns.message = message; this }
	}

fun SNSRecord.withSubject(subject: String) : SNSRecord =
	when (this.sns) {
		null -> { this.setSns(SNS().withSubject(subject)); this }
		else -> { this.sns.subject = subject; this }
	}

fun SNSRecord.withTopicArn(topicArn: String) : SNSRecord =
	when (this.sns) {
		null -> { this.setSns(SNSEvent.SNS().withTopicArn(topicArn)); this }
		else -> { this.sns.topicArn = topicArn; this }
	}

fun SNSEvent.withSingleRecord(record: SNSEvent.SNSRecord) : SNSEvent = this.withRecords(listOf(record))
