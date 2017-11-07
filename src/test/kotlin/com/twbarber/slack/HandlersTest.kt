package com.twbarber.slack

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.events.SNSEvent
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
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
		val mockEvent = getMockEvent()
		Handlers(mockSlack).snsHandler(mockEvent, mockContext)
		verify(mockSlack, times(2)).send(any(), any(), eq(mockWebhookUrl))
	}

	@Test
	fun `apiGateway can parse SNS Event and send Slack Message`() {
		System.setProperty("WEBHOOK_URL", mockWebhookUrl)
	}

    private fun getMockEvent() : SNSEvent =
        snsEvent {
            snsRecord {
                sns {
                    message(testMessage)
                    subject("Highlight 1")
                    topicArn("topic1")
                }
            }
            snsRecord {
                sns {
                    subject("Highlight 2")
                    message(testMessage)
                    topicArn("topic1")
                }
            }
        }
}