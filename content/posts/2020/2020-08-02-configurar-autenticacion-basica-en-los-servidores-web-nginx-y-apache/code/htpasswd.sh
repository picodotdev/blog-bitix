# Crear un archivo de credenciales con un suaurio
htpasswd -c -b -B .localhost.htpasswd user password

# Añadir o modificar un usuario a un archivo de credenciales
htpasswd -b -B .localhost.htpasswd user password

# Eliminar una credencial
htpasswd -b -D .localhost.htpasswd user