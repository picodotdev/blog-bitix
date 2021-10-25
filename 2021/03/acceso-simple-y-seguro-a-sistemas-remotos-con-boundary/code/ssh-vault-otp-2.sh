[picodotdev@archlinux ~]$ vault write ssh/creds/otp_ssh username=pi ip=192.168.1.101
Key                Value
---                -----
lease_id           ssh/creds/otp_ssh/7vB38cBFRWl883ePHusCBAMI
lease_duration     768h
lease_renewable    false
ip                 192.168.1.101
key                93e489c2-c30c-6e4e-f22c-96e719d41fd3
key_type           otp
port               22
username           pi
[picodotdev@archlinux ~]$ ssh pi@192.168.1.101
Password:
Linux raspberrypi 5.10.11+ #1399 Thu Jan 28 12:02:28 GMT 2021 armv6l

The programs included with the Debian GNU/Linux system are free software;
the exact distribution terms for each program are described in the
individual files in /usr/share/doc/*/copyright.

Debian GNU/Linux comes with ABSOLUTELY NO WARRANTY, to the extent
permitted by applicable law.
Last login: Fri Mar  5 15:12:56 2021 from 192.168.1.4
[pi@raspberrypi ~]$ 