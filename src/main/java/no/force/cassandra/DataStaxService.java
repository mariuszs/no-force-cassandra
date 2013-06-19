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

        session.execute("USE ass;");

        session.execute("DROP TABLE transaction;");

        session.execute("CREATE TABLE transaction (\n" +
                " id varchar,\n" +
                " name varchar," +
                " PRIMARY KEY (id));");

        ResultSet results = session.execute("SELECT * FROM transaction " +
                "WHERE id = 001;");
        for (Row result : results) {
            System.out.println("Result " + result);

        }
        System.out.println("End...");
    }

}
