package no.force.cassandra;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataStaxService {

    private static final Logger LOG = LoggerFactory.getLogger(DataStaxService.class);

    @Autowired
    private DataStaxClient dataStaxClient;

    public void execute() {

        Session session = dataStaxClient.createSession();

        ResultSet results = session.execute("SELECT * FROM Transaction " +
                "WHERE id = 001;");
        for (Row result : results) {
            LOG.info("Result {}", result);

        }

    }

}
