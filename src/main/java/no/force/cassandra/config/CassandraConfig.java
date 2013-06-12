package no.force.cassandra.config;

import me.prettyprint.cassandra.model.ConfigurableConsistencyLevel;
import me.prettyprint.cassandra.service.CassandraHostConfigurator;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.HConsistencyLevel;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.factory.HFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CassandraConfig {
    private static final Logger LOG = LoggerFactory.getLogger(CassandraConfig.class);

    public static final String CLUSTER_NAME = "Test Cluster";
    public static final String KEYSPACE_NAME = "Forcespace";

    @Value("${cassandra.host}")
    public String cassandraHost = "localhost";
    @Value("${cassandra.port}")
    public String cassandraPort = "9160";

    @Bean
    public CassandraHostConfigurator buildConfiguration() {
        return new CassandraHostConfigurator();
    }

    @Bean
    public Cluster buildCluster() {
        LOG.info("Cluster at address {}:{}", cassandraHost, cassandraPort);
        return HFactory.getOrCreateCluster(CLUSTER_NAME, cassandraHost + ":" + cassandraPort);
    }

    @Bean
    public ConfigurableConsistencyLevel buildConsistencyLevel() {
        ConfigurableConsistencyLevel ccl = new ConfigurableConsistencyLevel();
        ccl.setDefaultReadConsistencyLevel(HConsistencyLevel.ONE);
        return ccl;
    }

    @Bean
    public Keyspace buildHFactory() {
        return HFactory.createKeyspace(KEYSPACE_NAME, buildCluster(), buildConsistencyLevel());
    }
}

