package com.twbarber.slack

import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.LambdaLogger
import com.amazonaws.services.lambda.runtime.events.SNSEvent
import com.amazonaws.services.lambda.runtime.events.SNSEvent.SNSRecord
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.twbarber.slack.amazon.withMessage
import com.twbarber.slack.amazon.withSingleRecord
import com.twbarber.slack.amazon.withSubject
import com.twbarber.slack.amazon.withTopicArn
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HandlersTest {

	private val mockWebhookUrl = "https://www.google.com"
	private val testMessage = "Test Message"
	private val mockSlack: SlackService = mock()
	private val mockContext: Context = mock { on { it.logger }.thenReturn( mock()) }

	@Test
	fun `snsHandler can parse SNS Event and send Slack Message`() {
		System.setProperty("WEBHOOK_URL", mockWebhookUrl)
		val mockEvent = SNSEvent().withSingleRecord(
			SNSRecord()
				.withMessage(testMessage)
				.withSubject("Highlight 12345")
				.withTopicArn("arn:aws:sns:us-east-1:1111111111:test-topic")
		)

		Handlers(mockSlack).snsHandler(mockEvent, mockContext)
		verify(mockSlack).send(any(), eq(testMessage), eq(mockWebhookUrl))
	}

	@Test
	fun `apiGateway can parse SNS Event and send Slack Message`() {
		System.setProperty("WEBHOOK_URL", mockWebhookUrl)
	}

}