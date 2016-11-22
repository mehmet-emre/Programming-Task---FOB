docker-machine start
set DOCKER_HOST=tcp://192.168.99.100:2376
set DOCKER_MACHINE_NAME=default
set DOCKER_TLS_VERIFY=1
set DOCKER_TOOLBOX_INSTALL_PATH=C:\Program Files\Docker Toolbox
set DOCKER_CERT_PATH=C:\Users\Emre\.docker\machine\machines\default
mvn clean package docker:build & docker run -p 8080:8080 -t task/server