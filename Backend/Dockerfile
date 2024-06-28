FROM quay.io/wildfly/wildfly:30.0.1.Final-jdk17 as Final
COPY standalone-microprofile-jaeger.xml /opt/jboss/wildfly/standalone/configuration/
ENV WILDFLY_USER=user
ENV WILDFLY_PASS=userPassword
ENV DS_NAME=ejPostgresDS
ENV DS_USER=myuser
ENV DS_PASS=mypassword
ENV DS_URI=jdbc:postgresql://10.128.0.4:5432/mydatabase
ENV JBOSS_CLI=$JBOSS_HOME/bin/jboss-cli.sh
ENV DEPLOYMENT_DIR=$JBOSS_HOME/standalone/deployments/
RUN echo "Adding WildFly administrator" && \
    $JBOSS_HOME/bin/add-user.sh -u $WILDFLY_USER -p $WILDFLY_PASS --silent
RUN echo "Starting WildFly server" && \
    bash -c '$JBOSS_HOME/bin/standalone.sh -c standalone.xml &' && \
    bash -c 'until `$JBOSS_CLI -c ":read-attribute(name=server-state)" 2> /dev/null | grep -q running`; do echo `$JBOSS_CLI -c ":read-attribute(name=server-state)" 2> /dev/null`; sleep 1; done' && \
    curl --location --output /tmp/postgresql-42.2.16.jar --url https://jdbc.postgresql.org/download/postgresql-42.2.16.jar && \
    $JBOSS_CLI --connect --command="module add --name=org.postgresql --resources=/tmp/postgresql-42.2.16.jar --dependencies=javax.api,javax.transaction.api" && \
    $JBOSS_CLI --connect --command="/subsystem=datasources/jdbc-driver=postgres:add(driver-name=\"postgres\",driver-module-name=\"org.postgresql\",driver-class-name=org.postgresql.Driver)" && \
    $JBOSS_CLI --connect --command="data-source add \
        --name=${DS_NAME} \
        --jndi-name=java:jboss/datasources/${DS_NAME} \
        --user-name=${DS_USER} \
        --password=${DS_PASS} \
        --driver-name=postgres \
        --connection-url=${DS_URI} \
        --min-pool-size=10 \
        --max-pool-size=20 \
        --blocking-timeout-wait-millis=5000 \
        --statistics-enabled=true \
        --enabled=true" && \
    $JBOSS_CLI --connect --command=":shutdown" && \
    rm -rf $JBOSS_HOME/standalone/configuration/standalone_xml_history/ $JBOSS_HOME/standalone/log/* && \
    rm -f /tmp/*.jar
COPY ./Examen/target/Examen.war ${DEPLOYMENT_DIR}
EXPOSE 8080 8009 8787
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone.xml", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
