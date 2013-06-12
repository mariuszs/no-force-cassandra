package no.force.cassandra.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("no.force.cassandra")
@Import({CassandraConfig.class, PropertyPlaceholderConfig.class})
public class NoForceConfig {

}
