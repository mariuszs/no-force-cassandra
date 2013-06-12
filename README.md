## Starting cassandra

    mvn cassandra:start jetty:run

## Loading `load.script`

    mvn cassandra:load
    
## Start app

    java -jar -Dcassandra.host=localhosttarget/cassandra-force-jar-with-dependencies.jar 


# Links

http://mojo.codehaus.org/cassandra-maven-plugin/index.html

