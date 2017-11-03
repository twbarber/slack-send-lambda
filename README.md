# Slack Send Lambda

## About

Lambda executable `jar` to send Slack messages via Slack Incoming Webhook.

Written using Kotlin and Gradle.

## Demployment Instructions

- Run `./gradlew jar` to build your `jar`.
- Upload `/build/distributions/slack-send-lambda-1.0-SNAPSHOT.zip` to a new Lambda Function
- Set the Handler of the function to `lambda.Main::handler`
- The Logs should show your Lambda host's IP Address
