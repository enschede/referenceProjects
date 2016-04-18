package nl.marcenschede.tests.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CassandraConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(Runner.class);

    @Bean
    public Cluster cassandraCluster(@Value("${cassandra.contactpoint}") String cassandraContactPoint) {

        logger.debug("cassandra.contactpoint = {}", cassandraContactPoint);

        Cluster cluster = Cluster.builder().addContactPoint(cassandraContactPoint).build();
        return cluster;
    }

    @Bean
    public Session cassandraSession(Cluster cassandraCluster,
                           @Value("${cassandra.keyspace}") String cassandraKeyspace) {

        logger.debug("cassandra.keyspace = {}", cassandraKeyspace);

        Session session = cassandraCluster.connect(cassandraKeyspace);

        return session;
    }

    @Bean
    public Metadata cassandraMetadata(Cluster cassandraCluster) {
        return cassandraCluster.getMetadata();
    }
}
