package accumulo.provider;

import org.apache.accumulo.core.client.Connector;

public interface AccumuloProvider {
	
	public static final String IOP_PROPERTIES = "iop.properties";
	public static final String ACCUMULO_INSTANCE = "accumulo.instance";
	public static final String ZOOKEEPER_URI = "zookeeper.uri";
	public static final String USER = "user";
	public static final String PASSWORD = "password";

	Connector getConnection();
}