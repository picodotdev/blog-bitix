$ docker run -it --rm \
    -v "${PWD}/nginx/log:/var/log/nginx" \
    picodotdev/goaccess:1.0 goaccess --no-global-config --log-format=VCOMBINED /var/log/nginx/nginx-access.log /var/log/nginx/goaccess-access.log
