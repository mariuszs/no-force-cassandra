package no.force.cassandra;

import me.prettyprint.hector.api.Cluster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

@Service
public class ClusterManager {

    private static final Logger LOG = LoggerFactory.getLogger(ClusterManager.class);

    @Autowired
    private Cluster cluster;

    @PreDestroy
    public void cleanup() {
        LOG.info("cleanup");
        cluster.getConnectionManager().shutdown();
    }
}
