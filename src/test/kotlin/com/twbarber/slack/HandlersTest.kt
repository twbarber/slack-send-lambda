package com.twbarber.slack

import com.amazonaws.services.lambda.runtime.Context
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.twbarber.slack.amazon.message
import com.twbarber.slack.amazon.sns
import com.twbarber.slack.amazon.snsEvent
import com.twbarber.slack.amazon.snsRecord
import com.twbarber.slack.amazon.subject
import com.twbarber.slack.amazon.topicArn
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
		val mockEvent = snsEvent {
			snsRecord {
				sns {
					message(testMessage)
					subject("Highlight 1")
					topicArn("topic1")
				}
			}
			snsRecord {
				message(testMessage)
				subject("Highlight 2")
				topicArn("topic 2")
			}
		}
		Handlers(mockSlack).snsHandler(mockEvent, mockContext)
		verify(mockSlack).send(any(), eq(testMessage), eq(mockWebhookUrl))
	}

	@Test
	fun `apiGateway can parse SNS Event and send Slack Message`() {
		System.setProperty("WEBHOOK_URL", mockWebhookUrl)
	}

}