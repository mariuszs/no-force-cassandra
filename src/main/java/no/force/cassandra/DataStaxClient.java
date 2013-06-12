package no.force.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;

import javax.annotation.PreDestroy;

public class DataStaxClient {

    private Cluster cluster;

    public void connect(String node, String port) {
        cluster = Cluster.builder().withPort(Integer.parseInt(port)).addContactPoint(node).build();

        Metadata metadata = cluster.getMetadata();

        System.out.printf("Connected to cluster: %s\n", metadata.getClusterName());

        for (Host host : metadata.getAllHosts()) {
            System.out.printf("Datacenter: %s; Host: %s; Rack: %s\n",
                    host.getDatacenter(), host.getAddress(), host.getRack());
        }
    }

    public Session createSession() {
        Session session = cluster.connect();
        return session;
    }

    @PreDestroy
    public void close() {
        cluster.shutdown();
    }

}
