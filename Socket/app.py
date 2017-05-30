import threading
import exchange
import liquidity
import long
import socketserver


def main():
    HOST_LONG, PORT_LONG = "localhost", 9999
    HOST_LIQUIDITY, PORT_LIQUIDITY = "localhost", 9998
    mrlong = socketserver.TCPServer((HOST_LONG, PORT_LONG), exchange.ExchangeSend)
    long_thread = threading.Thread(target=mrlong.serve_forever)
    long_thread.start()
    #TODO: Create a TCP Connection for SIR LIQUIDITY calling the exchange's ExchangeReceiver, call the variable sirliq
    #TODO Create a thread for the TCPServer you called sirliq and call the serve_forever command as your target, call the variable liq_thread
    #TODO: Call start function for liq_thread
    #TODO: Create a variable call mrlong that calls the Long class
    #TODO: Create a variable receiving that create a thread whose target calls variable's mrlong's  recieve function
    #TODO: Call start function for receiving variable
    sirliq=socketserver.TCPServer((HOST_LIQUIDITY,PORT_LIQUIDITY),exchange.ExchangeReceiver)
    liq_thread=threading.Thread(target=sirliq.serve_forever)
    liq_thread.start()

    mrlong=long.Long()
    receiving=threading.Thread(target=mrlong.receive)
    receiving.start()
    liq = liquidity.Liquidity()
    sending = threading.Thread(target=liq.send)
    liquid_receive = threading.Thread(target=liq.receive)
    sending.start()
    liquid_receive.start()

