FROM docker.dev.xunyunedu.com/tomcat:7.7u80
LABEL maintainer="Bright Chan <858798033@qq.com>"
# delete file
RUN rm -rf /usr/local/apache-tomcat-7.0.104/webapps/*
# copy source code
COPY target/szxyzxx-0.0.1 /usr/local/apache-tomcat-7.0.104/webapps/ROOT
# container listener port
EXPOSE 8080
# startup web application services by self
CMD ["catalina.sh", "run"]
