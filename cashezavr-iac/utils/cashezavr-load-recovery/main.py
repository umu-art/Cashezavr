import pika
import os

if __name__ == '__main__':
    credentials = pika.PlainCredentials(os.environ['RABBIT_USER'], os.environ['RABBIT_PASSWORD'])
    parameters = pika.ConnectionParameters('rabbit.cashezavr-db', 5672, '/', credentials)
    connection = pika.BlockingConnection(parameters)
    channel = connection.channel()

    channel.basic_publish(exchange='', routing_key='receiptLoadMessages', body='recovery call')
    connection.close()
