FROM nginx:stable-alpine3.17
LABEL authors="vikazeni"
COPY ./nginx-proxy.conf /etc/nginx/conf.d/default.conf
COPY ./cert.crt /etc/ssl/cert.crt
COPY ./cert.key /etc/ssl/cert.key