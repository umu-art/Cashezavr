FROM python:3.11.4-slim as base

ENV PYTHONDONTWRITEBYTECODE=1
ENV PYTHONUNBUFFERED=1

WORKDIR /app

COPY . .

RUN python -m pip install -r requirements.txt

ENTRYPOINT ["python3", "main.py", ""]