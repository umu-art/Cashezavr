FROM grafana/grafana:10.4.1
LABEL authors="vikazeni"
COPY ./provisioning/datasources /etc/grafana/provisioning/datasources
COPY ./provisioning/dashboards /etc/grafana/provisioning/dashboards

ENV GF_USERS_DEFAULT_THEME=light
ENV GF_FEATURE_TOGGLES_ENABLE=tempoSearch,tempoServiceGraph,tempoApmTable