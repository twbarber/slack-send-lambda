# AWS Lambda with Kotlin

## About

Lambda executable `jar` written using Kotlin and Gradle.

## Demployment Instructions

- Run `./gradlew buildZip` to build your `jar`.
- Upload `/build/distributions/lambda-web-test-1.0-SNAPSHOT.zip` to a new Lambda Function
- Set the Handler of the function to `lambda.Main::handler`
- The Logs should show your Lambda host's IP Address
