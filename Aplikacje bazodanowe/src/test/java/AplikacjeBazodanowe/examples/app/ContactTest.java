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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ContactTest {

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
	public void testDelete() throws SQLException {
		try (Connection connection = DbHelper.getConnection(); Statement stmt = connection.createStatement()) {
			stmt.execute("INSERT INTO contacts (name, contacts) VALUES ('Błażej Dobek', 'blazejdobek94@gmail.com')");
			stmt.execute("INSERT INTO contacts (name, contacts) VALUES ('Adam Nowak', 'adamnowak@gmail.com')");
			stmt.execute("INSERT INTO contacts (name, contacts) VALUES ('Jan Kowalski', 'jankowalski@gmail.com')");

			List<Contact> contacts = ContactsHelper.getInstance().getContacts();
			Assert.assertEquals(3, contacts.size());

			final Contact contact = contacts.get(1);
			Assert.assertNotEquals(-1, contact.getId());
			contact.delete();
			Assert.assertEquals(-1, contact.getId());

			contacts = ContactsHelper.getInstance().getContacts();
			Assert.assertEquals(2, contacts.size());
			Assert.assertEquals(1L, contacts.get(0).getId());
			Assert.assertEquals(3L, contacts.get(1).getId());
		}
	}

	@Test
	public void testSave() throws SQLException {
		final Contact c = new Contact();
		c.setName("Błażej Dobek");
		c.setContacts("blazejdobek94@gmail.com");
		Assert.assertEquals(-1L, c.getId());

		c.save();
		Assert.assertEquals(1L, c.getId());

		try (Connection connection = DbHelper.getConnection(); Statement stmt = connection.createStatement()) {
			try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM contacts")) {
				Assert.assertTrue("Count should return at least one row", rs.next());
				Assert.assertEquals(1L, rs.getLong(1));
				Assert.assertFalse("Count should not return more than one row", rs.next());
			}

			try (ResultSet rs = stmt.executeQuery("SELECT * FROM contacts")) {
				Assert.assertTrue("Select should return at least one row", rs.next());
				Assert.assertEquals(1L, rs.getLong(1));
				Assert.assertEquals("Błażej Dobek", rs.getString("name"));
				Assert.assertEquals("blazejdobek94@gmail.com", rs.getString("contacts"));
				Assert.assertFalse("Select should not return more than one row", rs.next());
			}
		}

		c.setName("Błażej Dobek");
		c.save();

		Assert.assertEquals(1L, c.getId());
		Assert.assertEquals("Błażej Dobek", c.getName());
		Assert.assertEquals("blazejdobek94@gmail.com", c.getContacts());

		final List<Contact> contacts = ContactsHelper.getInstance().getContacts();
		Assert.assertEquals(1, contacts.size());

		final Contact contact = contacts.get(0);
		Assert.assertEquals(1L, contact.getId());
		Assert.assertEquals("Błażej Dobek", contact.getName());
		Assert.assertEquals("blazejdobek94@gmail.com", contact.getContacts());
	}

}