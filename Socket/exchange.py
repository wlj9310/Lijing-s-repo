import socketserver
import threading
import time
import select
"""
This code is literally from Piazza, it just needs a few modification to send and receive correctly
"""
SR_BYTES = 1024
current_price = 0.0
side_to_send = None
# For some more assistance, please look at this link
# https://www.technovelty.org/python/python-socketserver-class.html

class ExchangeReceiver(socketserver.BaseRequestHandler):
     """This Class Will Receive Messages"""
     def handle(self):
        while True:
            global current_price
            global side_to_send
            try:
                   #TODO: You will have to implement a select like in the URL example
                   #To check whether we need to recv or send data.
                   #if we are receiving data it will be price data.
                   #if we are sending data it will be the side data.
                   #recall that you will have to cast from str to float for your price
                ready_to_read,ready_to_write,in_error=select.select([self.request],[self.request],[],None)
                if len(ready_to_read)>=1 and ready_to_read[0]==self.request:
                    self.data = self.request.recv(1024).decode()
                    if self.data:
                        current_price=float(self.data)
                        #print(current_price)
                        #print('exchange receiver')
                elif len(ready_to_write)>=1 and ready_to_write[0]==self.request:
                    if side_to_send is not None:
                        #print('exchane recv send')
                        self.request.sendall(side_to_send.encode())
                        side_to_send=None
            except Exception as error:
                print("Issue with Exchange Receive Error:{}".format(error))
                break
        #self.request.close()

class ExchangeSend(socketserver.BaseRequestHandler):
    def handle(self):
        while True:
            global current_price
            global side_to_send
            try:
                   #TODO: You will have to implement a select like in the URL examle
                   #To check whether we need to recv or send data
                   #Here you will do the opposite, receiving data will be the side
                   #sending data will be the price
                   #recall that you will have to cast from str to float for your price
                ready_to_read,ready_to_write,in_error=select.select([self.request],[self.request],[],None)
                #print(ready_to_read,ready_to_write,in_error)
                if len(ready_to_read)>=1 and ready_to_read[0]==self.request:
                    self.data=self.request.recv(1024).decode()
                    #print('exchangeshoudaodesidetosend')
                    if self.data:
                        side_to_send=self.data
                elif len(ready_to_write)>=1 and ready_to_write[0]==self.request:
                    if current_price!=0:
                        #print('exchange send sned')
                        #print(current_price)
                        self.request.sendall(str(current_price).encode())
                        current_price=0
                    elif current_price==-1:
                        self.request.sendall(str(-1).encode())
            except Exception as error:
                print("Issue with Exchange Send Error:{}".format(error))
                break
        #self.request.close()
