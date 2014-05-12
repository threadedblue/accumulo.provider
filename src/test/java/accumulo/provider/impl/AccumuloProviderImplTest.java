package accumulo.provider.impl;

import static org.junit.Assert.assertNotNull;

import org.apache.accumulo.core.client.Connector;
import org.junit.Before;
import org.junit.Test;

import accumulo.provider.AccumuloProvider;

public class AccumuloProviderImplTest {
	
	AccumuloProvider app;

	@Before
	public void testAccumuloProviderImpl() {
		app = new AccumuloProviderImpl();
	}

	@Test
	public void testGetConnection() {
		Connector conn = app.getConnection();
		assertNotNull(conn);
	}

}
