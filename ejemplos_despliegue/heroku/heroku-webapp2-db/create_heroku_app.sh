#!/bin/bash

heroku create $1

heroku addons:create heroku-postgresql --app $1

heroku config:set SERVER_SSL_ENABLED=false --app $1
heroku config:set SPRING_JPA_HIBERNATE_DDL-AUTO=update --app $1