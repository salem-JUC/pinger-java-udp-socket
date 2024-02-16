# Pinger Program

## Overview
This project is a simple pinger program implemented in Java that tests the connection 
between a client device and a server using UDP (User Datagram Protocol).
It measures the round-trip time (RTT) between the client and the server.
the program will send 10 ping messages to the target server.
the reply from target server will be considered lost if it took more than 1 second


## Installation
Clone this repository to your local machine:

```bash
git clone https://github.com/your-username/pinger-program.git
```

## Usage
Start the server by running the UDPServer.java program. Pass the port number as a parameter through the command line:
```bash
java UDPServer <port>
```

Run the ping.java program and pass the hostname and the port number:
```bash
java PingerClient <hostname> <server_port>
```
Examble 
```bash
java UDPServer 8080
java PingerClient 192.168.1.100 8080
```
