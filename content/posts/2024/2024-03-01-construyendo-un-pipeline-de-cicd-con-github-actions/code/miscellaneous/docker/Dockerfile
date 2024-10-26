FROM eclipse-temurin:21.0.2_13-jdk-alpine as builder

ARG ARTIFACT_NAME
ARG GITHUB_PACKAGES_REPOSITORY_DOWNLOAD_USERNAME
ARG GITHUB_PACKAGES_REPOSITORY_DOWNLOAD_PASSWORD
ARG VERSION
ARG SHA
ARG BUILD_NUMBER
ARG BUILD_DATE

WORKDIR /builder
COPY . .

RUN GITHUB_PACKAGES_REPOSITORY_DOWNLOAD_USERNAME=${GITHUB_PACKAGES_REPOSITORY_DOWNLOAD_USERNAME} \
    GITHUB_PACKAGES_REPOSITORY_DOWNLOAD_PASSWORD=${GITHUB_PACKAGES_REPOSITORY_DOWNLOAD_PASSWORD} \
    VERSION=${VERSION} \
    SHA=${SHA} \
    BUILD_NUMBER=${BUILD_NUMBER} \
    BUILD_DATE=${BUILD_DATE} \
    ./gradlew --no-daemon -console plain assemble

FROM eclipse-temurin:21.0.2_13-jdk-alpine

ARG USERNAME=organization
ARG USER_UID=1000
ARG USER_GID=$USER_UID

RUN mkdir /app
COPY --from=builder /builder/app/build/distributions/${ARTIFACT_NAME}-app-*.zip /app/
RUN unzip -d /app/ /app/${ARTIFACT_NAME}-app-*.zip \
    && rm /app/${ARTIFACT_NAME}-app-*.zip \
    && mv /app/${ARTIFACT_NAME}-app-* /app/${ARTIFACT_NAME}-app \
    && addgroup -g ${USER_UID} -S ${USERNAME} \
    && adduser -u ${USER_UID} -G ${USERNAME} -S ${USERNAME}

USER ${USERNAME}
EXPOSE 8080

CMD ["/app/${ARTIFACT_NAME}/bin/app"]