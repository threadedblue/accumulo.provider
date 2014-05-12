package accumulo.provider.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.accumulo.core.client.AccumuloException;
import org.apache.accumulo.core.client.AccumuloSecurityException;
import org.apache.accumulo.core.client.Connector;
import org.apache.accumulo.core.client.Instance;
import org.apache.accumulo.core.client.ZooKeeperInstance;
import org.apache.accumulo.core.client.security.tokens.AuthenticationToken;
import org.apache.accumulo.core.client.security.tokens.PasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import accumulo.provider.AccumuloProvider;

public class AccumuloProviderImpl implements AccumuloProvider {

	Logger log = LoggerFactory.getLogger(AccumuloProviderImpl.class);

	private static Properties properties = new Properties();

	public AccumuloProviderImpl() {
		InputStream is = this.getClass().getClassLoader()
				.getResourceAsStream(IOP_PROPERTIES);
		try {
			properties.load(is);
		} catch (IOException e) {
			log.error("", e);
		}
	}

	Connector conn;

	public void activate(Map<String, Object> map) {
	}

	@Override
	public Connector getConnection() {
		if (conn == null) {
			log.debug("Connector was null.");
			try {
				String instance = properties.getProperty(ACCUMULO_INSTANCE);
				String uri = properties.getProperty(ZOOKEEPER_URI);
				Instance inst = new ZooKeeperInstance(instance, uri);
				String password = properties.getProperty(PASSWORD);
				AuthenticationToken token = new PasswordToken(password);
				try {
					String user = properties.getProperty(USER);
					conn = inst.getConnector(user,
							token);
				} catch (AccumuloException e) {
					log.error("", e);
				} catch (AccumuloSecurityException e) {
					log.error("", e);
				}
			} catch (Exception e) {
				log.error("", e);
			}
		}
		return conn;
	}
}