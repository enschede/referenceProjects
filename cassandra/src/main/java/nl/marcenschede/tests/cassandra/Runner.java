package nl.marcenschede.tests.cassandra;

import com.datastax.driver.core.*;
import com.datastax.driver.core.querybuilder.Ordering;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.google.common.util.concurrent.ListenableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class Runner implements CommandLineRunner {

    static final Logger logger = LoggerFactory.getLogger(Runner.class);

    private final Metadata cassandraMetadata;
    private final Session cassandraSession;
    private final KeyspaceMetadata keyspaceMetadata;

    @Autowired
    public Runner(Metadata cassandraMetadata,
                  Session cassandraSession,
                  @Value("${cassandra.keyspace}") String cassandraKeyspace) {
        this.cassandraMetadata = cassandraMetadata;
        this.cassandraSession = cassandraSession;

        this.keyspaceMetadata = cassandraMetadata.getKeyspace(cassandraKeyspace);
    }

    @Override
    public void run(String... strings) throws Exception {

        logger.debug("cassandraMetadata = {}, cassandraSession = {}", cassandraMetadata, cassandraSession);

        TableMetadata t2 = keyspaceMetadata.getTable("t2");

        Statement i1 = QueryBuilder.insertInto(t2).value("voornaam","marc").value("achternaam","enschede").value("gebjaar", 1966).value("aantal", 3);
        Statement i2 = QueryBuilder.insertInto(t2).value("voornaam","marc").value("achternaam","enschede").value("gebjaar", 1967).value("aantal", 3);
        Statement i3 = QueryBuilder.insertInto(t2).value("voornaam","marc").value("achternaam","enschede").value("gebjaar", 1968).value("aantal", 3);
        Statement i4 = QueryBuilder.insertInto(t2).value("voornaam","marc").value("achternaam","enschede").value("gebjaar", 1968).value("aantal", 4);

        cassandraSession.execute(i1);
        cassandraSession.execute(i2);
        cassandraSession.execute(i3);
        cassandraSession.execute(i4);

        Ordering ordering = QueryBuilder.asc("gebjaar");
        Select select = QueryBuilder.select().all().from("t2");
        ResultSet rows = cassandraSession.execute(select);

        ListenableFuture<ResultSet> future = rows.fetchMoreResults();
        future.get().forEach(row -> logger.info("Row = {}", row.toString()));
    }
}