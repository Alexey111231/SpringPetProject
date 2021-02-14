#!/usr/bin/env bash

mvn clean package

echo 'Copy files ...'

scp target/sring.pet.project-1.0-SNAPSHOT.jar \
    root@45.80.69.176:/root

echo 'Restart server...'

ssh root@45.80.69.176 << EOF
pgrep java | xargs kill -9
nohup java -jar sring.pet.project-1.0-SNAPSHOT.jar > log.txt &
EOF

echo 'Bye'