import socket
import time
import threading
import numpy as np

SENT_BYTES = 1024

"""
Part of this code is literally from the slides
"""
HOST, PORT = "localhost", 9998

class Liquidity():
    def __init__(self):
        #TODO: call the random seed of 12345 of numpy
        #TODO: create a variable called self.__list_prices that calls the np.random that Sebastien mentioend
        np.random.seed(12345)
        self.__list_prices=np.random.uniform(low=12.5,high=14.5,size=(500,))
        self.__balance = 0.0
        self.__cp = 0.0
        #TODO: Create a socket connection called self.__sock
        #TODO: call connect for HOST and PORT
        self.__sock=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        self.__sock.connect((HOST,PORT))


    def send(self):
        while True:
            try:
                 #TODO: Create a for loop that iterates through the prices in self.__list_prices
                 #TODO: call teh sendall function of self.__sock and send the price
                 #TODO sent self.__cp to the price you just sent to keep track of the current price
                 #TODO: call sleep for the time asked in the pdf of the assignment
                for i in self.__list_prices:
                    self.__sock.sendall(str(i).encode())
                    self.__cp=i
                    time.sleep(0.1)
                self.__sock.sendall(str(-1).encode())
                break
            except Exception as error:
                print("Issue with Sir Liquidity Error:{}".format(error))
                break
        print("Sent all prices, Closing........")

    def receive(self):
        while True:
            try:
                received = self.__sock.recv(1024).decode()
                if received:
                  #TODO: If data is received, Keep track of the balance for Sir Liquidity here
                  #TODO: Then set receive to None
                  #TODO: print the current balance of sir liquidity
                    if received=='BUY':
                        self.__balance=self.__balance+self.__cp
                    elif received=='SELL':
                        self.__balance=self.__balance-self.__cp
                        print('Balance (MR. LIQUIDITY):%f'%self.__balance)
                        print('===============================')
                    elif received=='STOP':
                        break
            except Exception as error:
                print("Receive Error: {0}".format(error))
                break
        print("FINAL LIQUIDITY BALANCE: {}".format(self.__balance))
