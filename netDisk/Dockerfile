FROM anapsix/alpine-java:8_server-jre_unlimited
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
ADD ["target/net-disk-0.0.1.jar", "app.jar"]
EXPOSE 8080
ENV JAVA_OPTS="-Xdebug -Dspring.profiles.active=prod -jar -Xmn256m -Xms256m"
RUN sh -c 'touch /app.jar'
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]