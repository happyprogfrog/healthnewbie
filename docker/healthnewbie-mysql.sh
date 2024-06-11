docker run -d \
--name healthnewbie-mysql \
-e MYSQL_ROOT_PASSWORD="healthnewbie" \
-e MYSQL_USER="healthnewbie" \
-e MYSQL_PASSWORD="healthnewbie" \
-e MYSQL_DATABASE="healthnewbie" \
-p 3306:3306 \
mysql:latest