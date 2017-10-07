#!/usr/bin/env bash
OUTPUT="$(docker ps -a | grep db)"
if [ -z "$OUTPUT" ]
then
    echo "Container not exists. Run new one..."

    docker run --name db --restart=always -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=test -v /data/mysql/agroman:/var/lib/mysql -p 33060:3306 -d mysql --character-set-server=utf8 --collation-server=utf8_unicode_ci

    echo "Wait 15s for container up and running to create all databases..."
    for i in $(seq 1 15); do sleep 1; echo -n '.'; done
    echo ''
    until docker exec db mysql -u root -ppassword -e "
            CREATE DATABASE test;
            CREATE DATABASE System;
        "
    do
        echo "Wait 5s and try again..."
        for i in $(seq 1 5); do sleep 1; echo -n '.'; done
        sleep 5;
    done
    echo "Successfully created all databases..."
else
    echo "Container already exists. Start existing..."
    docker start db
fi