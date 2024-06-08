FROM grafana/tempo:latest
LABEL authors="vikazeni"
COPY ./tempo.yaml /etc/tempo.yaml
ENTRYPOINT ["/tempo", "-config.file=/etc/tempo.yaml"]