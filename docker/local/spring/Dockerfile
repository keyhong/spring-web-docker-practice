# multi-staging build : setting gradle base image 
ARG GRADLE_VERSION=8.5.0-jdk17-alpine

FROM gradle:${GRADLE_VERSION} as jdk_executable_env

# multi-staging build : spring-build-stage
FROM jdk_executable_env as spring-build-stage

ARG BUILD_ENVIRONMENT=local
ARG BUILD_HOME=/build

WORKDIR ${BUILD_HOME}

COPY ["./settings.gradle", "./build.gradle", "./"]

COPY ./src ./src

RUN gradle clean build --no-daemon --warning-mode all -x test \
    && rm -rf ./build/libs/*plain*.jar

# multi-staging build : spring-run-stage
ARG OPEN_JDK_VERSION=17-alpine

FROM jdk_executable_env as spring-run-stage

ARG BUILD_ENVIRONMENT=local
ARG APP_HOME=/app

ENV BUILD_ENV ${BUILD_ENVIRONMENT}

WORKDIR ${APP_HOME}

COPY --from=spring-build-stage /build/build/libs .

COPY ./docker/local/spring/start .
RUN sed -i 's/\r$//g' start
RUN chmod +x start