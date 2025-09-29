$ sudo pacman -S tcpdump
$ sudo tcpdump -i any -c5 -nn port 80
$ sudo tcpdump -i any -c5 -nn src 192.168.1.6
$ sudo tcpdump -i any -c5 -nn dst 192.168.1.6

$ sudo tcpdump -i any -c10 -nn -w webserver.pcap port 80
$ tcpdump -nn -r webserver.pcap