/*
 * #%L
 * PZ
 * %%
 * Copyright (C) 2020 TestPurposes - without this maven tests crash
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package AplikacjeBazodanowe.examples.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.googlecode.flyway.core.Flyway;

public class DbHelper {

	public static Connection getConnection() throws SQLException {
		return DbHelper.getInstance().getDataSource().getConnection();
	}

	public static DbHelper getInstance() {
		return DbHelper.INSTANCE;
	}

	private static final DbHelper INSTANCE = new DbHelper();

	private BasicDataSource ds;

	private DbHelper() {
	}

	public void close() {
		if (ds != null) {
			try {
				ds.close();
			} catch (final SQLException e) {
				System.out.printf("\nFailed to close the data source", e);
			}
		}
	}

	public DataSource getDataSource() {
		return ds;
	}

	public void init() {
		final Properties properties = new Properties();
		properties.put("db.path", "target/db");
		properties.put("db.username", "sa");
		properties.put("db.password", "");
		try {
			properties.load(getClass().getResourceAsStream("/db/db_properties"));
		} catch (final IOException e) {
			System.out.printf("Failed to load the properties");
		}

		ds = new BasicDataSource();
		ds.setDriverClassName("org.h2.Driver");
		ds.setUrl("jdbc:h2:" + properties.getProperty("db.path"));
		ds.setUsername(properties.getProperty("db.username"));
		ds.setPassword(properties.getProperty("db.password"));

		final Flyway flyway = new Flyway();
		flyway.setDataSource(ds);
		flyway.migrate();
	}

	public void registerShutdownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				close();
			}
		}));
	}

}
