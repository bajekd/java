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

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ContactsHelperTest {

	@After
	public void close() {
		DbHelper.getInstance().close();
	}

	@Before
	public void init() throws SQLException {
		DbHelper.getInstance().init();

		try (Connection connection = DbHelper.getConnection(); Statement stmt = connection.createStatement()) {
			stmt.execute("TRUNCATE TABLE contacts");
			stmt.execute("ALTER TABLE contacts ALTER COLUMN id RESTART WITH 1");
		}
	}

	@Test
	public void testLoad() throws SQLException {
		List<Contact> contacts = ContactsHelper.getInstance().getContacts();
		Assert.assertNotNull(contacts);
		Assert.assertTrue(contacts.isEmpty());

		try (Connection connection = DbHelper.getConnection(); Statement stmt = connection.createStatement()) {
			stmt.execute("INSERT INTO contacts (name, contacts) VALUES ('Błażej Dobek', 'jankowalski@gmail.com')");
			stmt.execute("INSERT INTO contacts (name, contacts) VALUES ('Adam Nowak', 'adamnowak@gmail.com')");
			stmt.execute("INSERT INTO contacts (name, contacts) VALUES ('Jan Kowalski', 'jankowalski@gmail.com')");

			contacts = ContactsHelper.getInstance().getContacts();
			Assert.assertNotNull(contacts);
			Assert.assertEquals(3, contacts.size());

			Contact contact = contacts.get(0);
			Assert.assertNotNull(contact);
			Assert.assertEquals(1L, contact.getId());
			Assert.assertEquals("Błażej Dobek", contact.getName());
			Assert.assertEquals("jankowalski@gmail.com", contact.getContacts());

			contact = contacts.get(2);
			Assert.assertNotNull(contact);
			Assert.assertEquals(3L, contact.getId());
			Assert.assertEquals("Jan Kowalski", contact.getName());
			Assert.assertEquals("jankowalski@gmail.com", contact.getContacts());
		}
	}
}
