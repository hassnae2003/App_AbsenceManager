FROM openjdk:17-jdk-slim

WORKDIR /app

COPY app/build/outputs/apk/debug/app-debug.apk /app/app-debug.apk

CMD ["sleep", "infinity"]
