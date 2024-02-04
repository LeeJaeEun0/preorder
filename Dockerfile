# OpenJDK 17 베이스 이미지 사용
FROM openjdk:17-jdk

LABEL maintainer="email"

# JAR 파일 인자 및 추가
ARG JAR_FILE=build/libs/docker-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} docker-springboot.jar

# Java 애플리케이션 실행
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/docker-springboot.jar"]