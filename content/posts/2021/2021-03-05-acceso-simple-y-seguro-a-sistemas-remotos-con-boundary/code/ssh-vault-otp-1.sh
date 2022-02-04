[picodotdev@archlinux ~]$ vault ssh -role otp_ssh -mode otp -strict-host-key-checking=no pi@192.168.1.101
Vault could not locate "sshpass". The OTP code for the session is displayed
below. Enter this code in the SSH password prompt. If you install sshpass,
Vault can automatically perform this step for you.
OTP for the session is: 31233d29-e822-6891-6a34-9a101fb2e344
Password:
Linux raspberrypi 5.10.11+ #1399 Thu Jan 28 12:02:28 GMT 2021 armv6l

The programs included with the Debian GNU/Linux system are free software;
the exact distribution terms for each program are described in the
individual files in /usr/share/doc/*/copyright.

Debian GNU/Linux comes with ABSOLUTELY NO WARRANTY, to the extent
permitted by applicable law.
Last login: Fri Mar  5 15:12:56 2021 from 192.168.1.4
[pi@raspberrypi ~]$ 