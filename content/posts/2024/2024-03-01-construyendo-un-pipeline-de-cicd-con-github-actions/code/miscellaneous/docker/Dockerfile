FROM ubuntu:24.04 AS build

ARG ARTIFACT
ARG ARTIFACT_NAME

RUN apt update \
    && apt install unzip

RUN mkdir /app/
COPY ${ARTIFACT} /app/
RUN unzip -d /app/ /app/${ARTIFACT_NAME}-*.zip \
    && rm /app/${ARTIFACT_NAME}-*.zip \
    && mv /app/${ARTIFACT_NAME}-* /app/${ARTIFACT_NAME}

RUN ls -l /app/

FROM eclipse-temurin:21-jdk

ARG USERNAME=picodotdev
ARG GROUP_NAME=${USERNAME}
ARG USER_UID=2000
ARG USER_GID=${USER_UID}

RUN groupadd --gid ${USER_GID} ${GROUP_NAME} \
    && useradd --uid ${USER_UID} --gid ${USER_GID} ${USERNAME}

RUN mkdir /app/
COPY --from=build /app/ /app/

USER ${USERNAME}
EXPOSE 8080

CMD ["/app/platform-github-actions-workflows-gradle-app/bin/app"]