FROM grafana/loki:main
LABEL authors="vikazeni"
COPY ./loki.yaml /etc/config/loki.yaml
ENTRYPOINT ["/usr/bin/loki", "-config.file=/etc/config/loki.yaml"]