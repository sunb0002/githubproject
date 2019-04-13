#!/usr/bin/env bash

# Multiple docker compose files override one by one
# https://docs.docker.com/compose/extends/

FILES=`ls -tr compose*.yml | xargs -I {} echo "-f {}" | xargs`

if [[ $1 == "--down" ]] || [[  $1 == "-d" ]]; then
    docker-compose --compatibility ${FILES} down
else
    docker-compose --compatibility ${FILES} up -d
    docker ps
fi