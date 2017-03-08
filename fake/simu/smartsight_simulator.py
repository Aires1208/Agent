# -*- coding: utf-8 -*-

import SocketServer
import logging
import threading
import base64

from flask import Flask, jsonify

__author__ = '10172605'


_data_dict = {
    29993: 'meta_data',
    29994: 'colt_data',
    29995: 'span_data',
    29996: 'stat_data',
    39995: 'nfv_data'
}


# tcp 29993
meta_data = []
# tcp 29994
colt_data = []
# udp 29995
stat_data = []
# udp 29996
span_data = []
# udp 39995
nfv_data = []


def append_data(port, data):
    eval(_data_dict.get(port)).append(data)


app = Flask(__name__)

logging.basicConfig(level=logging.DEBUG,
                    format='%(asctime)s [%(filename)s:%(lineno)d] %(levelname)s - %(message)s',
                    datefmt='%Y-%m-%d %H:%M:%S')


@app.route('/api/smartsight/v1.0/meta', methods=['GET'])
def get_meta_data():
    return jsonify({'count': len(meta_data), 'meta_data': meta_data})


@app.route('/api/smartsight/v1.0/colt', methods=['GET'])
def get_colt_data():
    return jsonify({'count': len(colt_data), 'colt_data': colt_data})


@app.route('/api/smartsight/v1.0/span', methods=['GET'])
def get_span_data():
    return jsonify({'count': len(span_data), 'span_data': span_data})


@app.route('/api/smartsight/v1.0/stat', methods=['GET'])
def get_stat_data():
    return jsonify({'count': len(stat_data), 'stat_data': stat_data})


@app.route('/api/smartsight/v1.0/nfv', methods=['GET'])
def get_nfv_data():
    return jsonify({'count': len(nfv_data), 'nfv_data': nfv_data})


def get_base64_data(data):
    try:
        data.decode('utf-8')
    except UnicodeDecodeError:
        missing_padding = 4 - len(data) % 4
        if missing_padding:
            data += b'=' * missing_padding
        data = base64.b64encode(data)
    return data


def start_thread(server):
    server_thread = threading.Thread(target=server)
    server_thread.daemon = True
    server_thread.start()


class TCPRequestHandler(SocketServer.BaseRequestHandler):
    def handle(self):
        data_port = self.server.server_address[1]
        data_name = _data_dict.get(data_port)

        data = self.request.recv(1024)
        base64_data = get_base64_data(data)
        append_data(data_port, base64_data)

        logging.info('Tcp ' + str(data_port) + ': ' + data_name)
        logging.info('Received ' + data_name + ': {}'.format(base64_data))


class TCPServerThread(SocketServer.ThreadingMixIn, SocketServer.TCPServer):
    pass


class UDPRequestHandler(SocketServer.BaseRequestHandler):
    def handle(self):
        data_port = self.server.server_address[1]
        data_name = _data_dict.get(data_port)

        data = self.request[0].strip()
        base64_data = get_base64_data(data)
        append_data(data_port, base64_data)

        logging.info('Udp ' + str(data_port) + ': ' + data_name)
        logging.info('Received ' + data_name + ': {}'.format(base64_data))


class UDPServerThread(SocketServer.ThreadingMixIn, SocketServer.UDPServer):
    pass


if __name__ == '__main__':
    HOST = '0.0.0.0'
    start_thread(TCPServerThread((HOST, 29993), TCPRequestHandler).serve_forever)
    start_thread(TCPServerThread((HOST, 29994), TCPRequestHandler).serve_forever)
    start_thread(UDPServerThread((HOST, 29995), UDPRequestHandler).serve_forever)
    start_thread(UDPServerThread((HOST, 29996), UDPRequestHandler).serve_forever)
    start_thread(UDPServerThread((HOST, 39995), UDPRequestHandler).serve_forever)
    app.run(HOST, debug=True, use_reloader=False)
