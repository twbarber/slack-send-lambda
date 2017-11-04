# Slack Send Lambda

## About

Lambda executable `jar` to send Slack messages via Slack Incoming Webhook.

Written using Kotlin and Gradle.

## Build

- Run `./gradlew jar` to build your `jar`.

## Deploy for use with AWS API Gateway

- Upload `/build/distributions/slack-send-lambda-1.0-SNAPSHOT.zip` to a new Lambda Function
- Set the Handler of the function to `com.twbarber.slack.Main::apiGatewayHandler`

## Deploy for use with AWS SNS

- Upload `/build/distributions/slack-send-lambda-1.0-SNAPSHOT.zip` to a new Lambda Function
- Set the Handler of the function to `com.twbarber.slack.Main::snsHandler`
