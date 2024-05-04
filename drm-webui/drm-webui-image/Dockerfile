FROM node:lts
LABEL authors="smile_slime_47"

WORKDIR /root/drm-webui

ADD dist .

RUN npm install -g serve

EXPOSE 80

ENTRYPOINT ["npx","serve"]