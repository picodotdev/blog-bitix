sudo pacman -S virt-install dnsmasq dmidecode
sudo usermod -a -G libvirtd picodotdev

mkdir -p /etc/qemu
sudo cat << EOF > /etc/qemu/bridge.conf
allow virbr0
EOF

sudo systemctl start libvirtd.service

