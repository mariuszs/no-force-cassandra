package no.force.cassandra.config;

import no.force.cassandra.DataStaxClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CassandraConfig {
    private static final Logger LOG = LoggerFactory.getLogger(CassandraConfig.class);

    @Value("${cassandra.cluster}")
    public String clusterName = "Test Cluster";

    @Value("${cassandra.keyspace}")
    public String keySpaceName = "Forcespace";

    @Value("${cassandra.host}")
    public String cassandraHost = "localhost";

    @Value("${cassandra.port}")
    public String cassandraPort = "9160";

    @Bean
    public DataStaxClient buildDataStaxService() {
        DataStaxClient dataStaxClient = new DataStaxClient();
        dataStaxClient.connect(cassandraHost, cassandraPort);
        return dataStaxClient;
    }


}

