package nl.marcenschede.tests.cassandra;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CassandraApplication.class)
@WebAppConfiguration
public class CassandraApplicationTests {

//	@Rule
//	public CassandraCQLUnit cassandra =
//			new CassandraCQLUnit(new ClassPathCQLDataSet("db/myDb.ddl.cql", true, true, "test1"));
//	public CassandraCQLUnit cassandra = new CassandraCQLUnit(
//			new ClassPathCQLDataSet("db/myDb.ddl.cql", true, true, "test1"),
//			null, 1234, 1234);

	@Test
	public void contextLoads() {

//		System.out.println(cassandra);
	}

}
