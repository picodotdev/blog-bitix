$ systemctl status systemd-timesyncd.service
$ systemctl status systemd-timesyncd.service
● systemd-timesyncd.service - Network Time Synchronization
     Loaded: loaded (/usr/lib/systemd/system/systemd-timesyncd.service; enabled; vendor preset: enabled)
     Active: active (running) since Thu 2021-10-07 19:09:48 CEST; 51min ago
       Docs: man:systemd-timesyncd.service(8)
   Main PID: 355 (systemd-timesyn)
     Status: "Initial synchronization to time server 193.182.111.142:123 (2.arch.pool.ntp.org)."
      Tasks: 2 (limit: 38345)
     Memory: 2.1M
        CPU: 65ms
     CGroup: /system.slice/systemd-timesyncd.service
             └─355 /usr/lib/systemd/systemd-timesyncd

oct 07 19:09:48 archlinux systemd[1]: Starting Network Time Synchronization...
oct 07 19:09:48 archlinux systemd[1]: Started Network Time Synchronization.
oct 07 19:10:18 archlinux systemd-timesyncd[355]: Initial synchronization to time server 193.182.111.142:123 (2.arch.pool.ntp.org).