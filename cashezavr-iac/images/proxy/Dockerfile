FROM nginx:stable-alpine3.17
LABEL authors="vikazeni"
COPY ./nginx-proxy.conf /etc/nginx/conf.d/default.conf
COPY ./complex/cert.crt /etc/ssl/complex/cert.crt
COPY ./complex/cert.key /etc/ssl/complex/cert.key