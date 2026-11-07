# Java Port Scanner

A multithreaded TCP port scanner written in Java.

## What it does
- Scans a range of ports on a host
- Uses a pool of 50 threads to check many ports at once
- Shows the service name for common ports (HTTP, SSH, SMB, etc.)
- Reports how long the scan took and how many ports are open

## How it works
It tries to open a socket connection to each port with a 500 ms timeout. If the connection succeeds, the port is open.

## How to run
1. Install Java (JDK 17 or newer)
2. Compile: `javac portScanner.java`
3. Run: `java portScanner`
4. Enter a host (for example `localhost`) and a port range

## Example output
```
Enter host: localhost
Enter start port: 1
Enter end port: 1024
Port 135 is OPEN (RPC)
Port 445 is OPEN (SMB)
Scan finished in 411 ms. Open ports: 2
```

## What I learned
Sockets, timeouts, thread pools (`ExecutorService`), `AtomicInteger`, and `HashMap`.

## Disclaimer
For educational use only. Only scan systems you own or have permission to test.
