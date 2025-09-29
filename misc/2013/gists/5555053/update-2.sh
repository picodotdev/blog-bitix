liquibase --driver=com.mysql.jdbc.Driver \
 --classpath=mysql-connector-java-5.1.21.jar \
 --changeLogFile=changelog.xml \
 --url="jdbc:mysql://localhost/liquibase" \
 --username=root \
 --password= \
 update