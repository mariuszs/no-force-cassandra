package no.force.cassandra;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.exceptions.HectorException;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.Mutator;
import me.prettyprint.hector.api.query.ColumnQuery;
import me.prettyprint.hector.api.query.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HectorService {

    private static final Logger LOG = LoggerFactory.getLogger(HectorService.class);

    private static final String COLUMN_FAMILY = "Transaction";

    @Autowired
    private Keyspace keyspace;

    public void execute() {
        LOG.info("Executed...");
        try {
            Mutator<String> mutator = HFactory.createMutator(keyspace, StringSerializer.get());

            mutator.insert("001", COLUMN_FAMILY, HFactory.createStringColumn("name", "Sample transaction"));

            ColumnQuery<String, String, String> columnQuery = HFactory.createStringColumnQuery(keyspace);
            columnQuery.setColumnFamily(COLUMN_FAMILY).setKey("001").setName("name");
            QueryResult<HColumn<String, String>> result = columnQuery.execute();

            System.out.println("Read HColumn from cassandra: " + result.get());
            System.out.println("Verify on CLI with:  get Keyspace1.Transaction['001'] ");
        } catch (HectorException e) {
            e.printStackTrace();
        }

    }

}
