ubuntu@nginx:~$ sudo ls -lh /etc/letsencrypt/live/www.27.0.174.19.sslip.io/
total 4.0K
lrwxrwxrwx 1 root root  48 Dec 21 13:50 cert.pem -> ../../archive/www.27.0.174.19.sslip.io/cert1.pem
lrwxrwxrwx 1 root root  49 Dec 21 13:50 chain.pem -> ../../archive/www.27.0.174.19.sslip.io/chain1.pem
lrwxrwxrwx 1 root root  53 Dec 21 13:50 fullchain.pem -> ../../archive/www.27.0.174.19.sslip.io/fullchain1.pem
lrwxrwxrwx 1 root root  51 Dec 21 13:50 privkey.pem -> ../../archive/www.27.0.174.19.sslip.io/privkey1.pem
-rw-r--r-- 1 root root 692 Dec 21 13:50 README
ubuntu@nginx:~$ sudo ls -lh /etc/letsencrypt/archive/www.27.0.174.19.sslip.io/
total 20K
-rw-r--r-- 1 root root 1.9K Dec 21 13:50 cert1.pem
-rw-r--r-- 1 root root 3.7K Dec 21 13:50 chain1.pem
-rw-r--r-- 1 root root 5.5K Dec 21 13:50 fullchain1.pem
-rw------- 1 root root 1.7K Dec 21 13:50 privkey1.pem
