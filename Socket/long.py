import os
import threading
import socket
import numpy as np

"""
Part of this code is literally from the slides
"""

HOST, PORT = "localhost", 9999
#balance = 0.0


class Long():
    def __init__(self):
        global HOST
        global PORT
        self.__balance = 0.0
        self.__current_price = 0.0
        self.__sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.__sock.connect((HOST, PORT))
        self.__prev_ma = 0.0
        self.__increase = 0
        self.__ma_list = []
        self.__is_buy = False
        self.__ma_period = 10

    def send(self, current_side):
        try:
            #TODO: UPDATE the balance equation here, if buy then -1 else 1 * the self.__current_price
            #TODO: THEN send current side
            if current_side=='BUY':
                self.__balance=self.__balance-self.__current_price
                print('Mr. Long Buys at %f' %self.__current_price)
            elif current_side=='SELL':
                self.__balance=self.__balance+self.__current_price
                print('Mr. Long Sells at %f'%self.__current_price )
                print('Balance (MR. LONG):%f'%self.__balance)
            self.__sock.sendall(str(current_side).encode())
        except Exception as error:
            print("Issue with Mr. Long Error:{}".format(error))

    def receive(self):
        while True:
            try:
                price = self.__sock.recv(1024).decode()
                price=float(price)
                #TODO: GRAB PRICE FROM THE SOCKET and save in variable price
                if price:
                    #TODO: convert string price to float and pass in the variable price and self.__ma_period
                    #TODO: to the moving_average function, make sure to assign a variable to the moving average that is returned
                    if price==-1:
                        self.send('STOP')
                        print("FINAL LONG BALANCE: {}".format(self.__balance))
                        break
                    self.__ma_list.append(price)
                    self.__ma_list=self.__ma_list[-self.__ma_period:]
                    ma=self.moving_average(self.__ma_list,self.__ma_period)[-1]
                    if price < self.__current_price and self.__is_buy:
                        #TODO: call send function and send a sell
                        #TODO: make sure to sent self.__is_buy to Fals
                        self.__current_price=price
                        self.send("SELL")
                        self.__is_buy=False
                    else:
                        #TODO: the moving average variable you have pass it in with the price to check_increase
                        self.check_increase(ma,price)
            except Exception as error:
                print("LONG Receive Error: {0}".format(error))
                break
            #print("LONG BALANCE: {}".format(self.__balance))

    def moving_average(self, ne, n):
        ma = None
        #TODO: create the moving average function
        if len(ne)>=n:
            ma=sum(ne[-n:])/n
            ma=[None]*(len(ne)-1)+[ma]
        else:
            ma=[None]*len(ne)
        return ma

    def check_increase(self, ma, price):
        global balance
        if ma:
            #TODO: write code that check if there is an increase in the ma average
            #TODO:self.__prev_ma hold the previous moving average, so you can compare to the moving average
            #TODO:current moving average is store in the variable ma
            #TODO:if there is an increase the have self.__increase increase by 1
            #TODO:Then set the prev_ma variblae to be ma
            #TODO:else if there is a decrease reset self.__increase variable to 0 and set prev_ma to ma
            if ma>=self.__prev_ma:
                self.__increase=self.__increase+1
            else:
                self.__increase=0
            self.__prev_ma=ma
            #Thsi is finished for you here
            if self.__increase == 3 and self.__is_buy==False:
                self.__prev_ma = 0.0
                self.__increase = 0
                self.__current_price = price
                self.send("BUY")
                self.__is_buy = True
