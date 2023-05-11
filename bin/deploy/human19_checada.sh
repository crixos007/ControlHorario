#!/bin/bash
cd /home/$1
git clone https://rcaraveo:mUymejS_jWhAri75Ygiz@gitlab.venturessoft.com/human/desarrollo/micro/etime/checada.git
#git checkout desarrollo
cd /home/$1/checada
mvn clean package -P $2 -DjarName=HumanChecada
mv /home/$1/checada/target/HumanChecada.jar /home/$1/HUMAN/Checada
rm -rf /home/$1/checada
if [ "$(docker images -q human-etime-check 2> /dev/null)" = "" ]; 
then
pwd no existe imagen activo.
else
pwd eliminado imagen activo actual:
	if [ "$(docker ps -aqf "name=human-etime-check")" = "" ]; 
	then
	pwd no existe contenedor activo.
	else
	pwd eliminado contenedor activo actual:
	docker stop $(docker ps -aqf "name=human-etime-check")
	docker rm $(docker ps -a -f status=exited -q)   
	fi
docker rmi human-etime-check -f
fi
docker build -t human-etime-check /home/$1/HUMAN/Checada
if [ "$(docker ps -aqf "name=human-etime-check")" = "" ]; 
then
pwd no existe contenedor activo.
else
pwd eliminado contenedor activo actual:
docker stop $(docker ps -aqf "name=human-etime-check")
docker rm $(docker ps -a -f status=exited -q)   
fi
docker run -e "CONFIG_ENVIROMENT=env-var-conf-"$3 -e "SERVER_ENVIROMENT=http://192.168.0.62:1000/" --name human-etime-check --mount type=bind,source=$4,target=$4 -d --restart unless-stopped -p 5200:5200 human-etime-check