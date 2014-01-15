package com.drillsoft.highrise;

import com.drillsoft.highrise.domain.Person;
import com.drillsoft.highrise.domain.Tag;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static com.drillsoft.highrise.domain.Contact.EmailAddress;
import static com.google.common.collect.ImmutableList.of;
import static java.lang.System.getProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*
 Define the following properties with -D

 baseUrl - point to your Highrise instance, with trailing "/"
 username - your Highrise account name
 password - your Highrise account password
 */
public class HighriseClientTest {
    private static final String TAG = "test-tag";
    private static final String EMAIL = "test.person@inter.net";
    private static final String FIRSTNAME = "HighriseClient";
    private static final String LASTNAME = "Test";
    private static final String NAME = FIRSTNAME + " " + LASTNAME;
    private static HighriseClient client;
    private static Person person;
    private static Tag tag;

    @BeforeClass
    public static void create() {
        client = new HighriseClient(getProperty("baseUrl"), getProperty("token"));
        createTestPerson();
    }

    @AfterClass
    public static void destroy() {
        destroyTestPerson();
    }

    @Test
    public void getAllReturnsMultiplePersons() throws Exception {
        assertTrue(client.listPeople().size() > 1);
    }

    @Test
    public void getWithIdReturnsTestPerson() throws Exception {
        Person created = client.person(person.id);
        assertEquals(person.id, created.id);
        assertEquals(TAG, created.tags.get(0).name);
        assertEquals(EMAIL, created.contact.emailAddresses.get(0).address);
    }

    @Test
    public void searchByEmailReturnsTestPerson() throws Exception {
        List<Person> people = client.searchPeopleByEmail(EMAIL);
        assertReturnsTestPerson(people);
    }

    @Test
    public void searchByNameReturnsTestPerson() throws Exception {
        List<Person> people = client.searchPeopleByName(NAME);
        assertReturnsTestPerson(people);
    }

    @Test
    public void searchByNonExistingEmailReturnsEmptyList() throws Exception {
        List<Person> people = client.searchPeopleByEmail("invalid-email-address");
        assertEquals(0, people.size());
    }

    private void assertReturnsTestPerson(List<Person> people) {
        assertEquals(1, people.size());
        assertEquals(person.id, people.get(0).id);
    }

    private static void createTestPerson() {
        Person newPerson = new Person();
        newPerson.firstName = FIRSTNAME;
        newPerson.lastName = LASTNAME;
        newPerson.contact.emailAddresses = of(new EmailAddress("Work", EMAIL));
        person = client.create(newPerson);
        tag = client.tag(person, TAG);
    }

    public static void destroyTestPerson() {
        if (client != null && person != null) {
            client.destroy(person);
        }
    }
}
