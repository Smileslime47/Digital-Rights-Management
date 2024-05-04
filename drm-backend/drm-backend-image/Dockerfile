FROM openjdk:21
LABEL authors="smile_slime_47"

WORKDIR /root/drm-backend

ADD build .

RUN microdnf install findutils

EXPOSE 8080

ENTRYPOINT ["java","-jar","./libs/drm-backend-all.jar"]