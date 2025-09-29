$ dig +short consul.192.168.33.10.xip.io
192.168.33.10

$ dig +short consul.192.168.33.20.xip.io
192.168.33.20

$ dig consul.192.168.33.10.xip.io

; <<>> DiG 9.16.5 <<>> consul.192.168.33.10.xip.io
;; global options: +cmd
;; Got answer:
;; ->>HEADER<<- opcode: QUERY, status: NOERROR, id: 59742
;; flags: qr rd ra ad; QUERY: 1, ANSWER: 1, AUTHORITY: 0, ADDITIONAL: 0

;; QUESTION SECTION:
;consul.192.168.33.10.xip.io.	IN	A

;; ANSWER SECTION:
consul.192.168.33.10.xip.io. 292 IN	A	192.168.33.10

;; Query time: 3 msec
;; SERVER: 192.168.1.1#53(192.168.1.1)
;; WHEN: vie jul 31 11:08:13 CEST 2020
;; MSG SIZE  rcvd: 61

$ dig consul.192.168.33.10.sslip.io

; <<>> DiG 9.16.5 <<>> consul.192.168.33.10.sslip.io
;; global options: +cmd
;; Got answer:
;; ->>HEADER<<- opcode: QUERY, status: NOERROR, id: 52060
;; flags: qr rd ra; QUERY: 1, ANSWER: 1, AUTHORITY: 0, ADDITIONAL: 1

;; OPT PSEUDOSECTION:
; EDNS: version: 0, flags:; udp: 512
;; QUESTION SECTION:
;consul.192.168.33.10.sslip.io.	IN	A

;; ANSWER SECTION:
consul.192.168.33.10.sslip.io. 300 IN	A	192.168.33.10

;; Query time: 263 msec
;; SERVER: 192.168.1.1#53(192.168.1.1)
;; WHEN: vie jul 31 11:09:12 CEST 2020
;; MSG SIZE  rcvd: 74

$ dig consul.192-168-33-10.sslip.io

; <<>> DiG 9.16.5 <<>> consul.192-168-33-10.sslip.io
;; global options: +cmd
;; Got answer:
;; ->>HEADER<<- opcode: QUERY, status: NOERROR, id: 26671
;; flags: qr rd ra; QUERY: 1, ANSWER: 1, AUTHORITY: 0, ADDITIONAL: 1

;; OPT PSEUDOSECTION:
; EDNS: version: 0, flags:; udp: 512
;; QUESTION SECTION:
;consul.192-168-33-10.sslip.io.	IN	A

;; ANSWER SECTION:
consul.192-168-33-10.sslip.io. 300 IN	A	192.168.33.10

;; Query time: 243 msec
;; SERVER: 192.168.1.1#53(192.168.1.1)
;; WHEN: vie jul 31 11:32:54 CEST 2020
;; MSG SIZE  rcvd: 74
