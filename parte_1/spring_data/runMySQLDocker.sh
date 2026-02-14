 docker run --rm -e MYSQL_ROOT_PASSWORD=password \
 -e MYSQL_DATABASE=posts -p 3306:3306 -d mysql:9.5.0