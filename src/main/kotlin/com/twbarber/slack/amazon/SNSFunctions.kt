@file:JvmName("SNSFunctions")

package com.twbarber.slack.amazon

import com.amazonaws.services.lambda.runtime.events.SNSEvent
import com.amazonaws.services.lambda.runtime.events.SNSEvent.SNS
import com.amazonaws.services.lambda.runtime.events.SNSEvent.SNSRecord


fun SNSRecord.message(message: String) : SNSEvent.SNSRecord =
	when (this.sns) {
		null -> { this.setSns(SNS().withMessage(message)); this }
		else ->  { this.sns.message = message; this }
	}

fun SNSRecord.subject(subject: String) : SNSRecord =
	when (this.sns) {
		null -> { this.setSns(SNS().withSubject(subject)); this }
		else -> { this.sns.subject = subject; this }
	}

fun SNSRecord.topicArn(topicArn: String) : SNSRecord =
	when (this.sns) {
		null -> { this.setSns(SNSEvent.SNS().withTopicArn(topicArn)); this }
		else -> { this.sns.topicArn = topicArn; this }
	}

fun snsEvent(init: SNSEvent.() -> Unit) : SNSEvent {
    val event = SNSEvent()
    event.init()
    return event
}

fun SNSEvent.snsRecord(init: SNSRecord.() -> Unit): SNSEvent {
    val record = SNSRecord()
    record.init()
    records = records.orEmpty() + record
    return this
}
fun SNSRecord.sns(init: SNS.() -> Unit): SNSRecord {
    val sns = SNS()
    sns.init()
    return this
}

