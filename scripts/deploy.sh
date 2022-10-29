#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/id_rsa \
	target/sweater-0.0.1-SNAPSHOT.jar \
	root@home.wutreg.com:/root/

echo 'Restart server...'

ssh -i ~/.ssh/id_rsa root@home.wutreg.com << EOF

pgrep java | xargs kill -9
nohup java -jar sweater-0.0.1-SNAPSHOT.jar > log.txt &

EOF

echo 'Bye'