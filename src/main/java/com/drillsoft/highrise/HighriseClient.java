package com.drillsoft.highrise;

import com.drillsoft.highrise.domain.Name;
import com.drillsoft.highrise.domain.People;
import com.drillsoft.highrise.domain.Person;
import com.drillsoft.highrise.domain.Tag;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.util.List;
import java.util.logging.Logger;

import static javax.ws.rs.client.Entity.entity;
import static javax.ws.rs.core.MediaType.APPLICATION_XML_TYPE;
import static org.glassfish.jersey.client.authentication.HttpAuthenticationFeature.basic;

public class HighriseClient {
    private final WebTarget highrise;
    private final Logger log = Logger.getLogger(HighriseClient.class.getName());

    public HighriseClient(String highriseRootUrl, String authenticationToken) {
        Client client = ClientBuilder.newClient(new ClientConfig()
                .register(basic(authenticationToken, "notneeded"))
                .register(new LoggingFilter(log, true)));
        highrise = client.target(highriseRootUrl);
    }

    public List<Person> listPeople() {
        return highrise.path("people.xml").request(APPLICATION_XML_TYPE).get(People.class).persons;
    }

    public List<Person> searchPeopleByName(String name) {
        return highrise.path("people/search.xml").queryParam("term", name).request(APPLICATION_XML_TYPE).get(People.class).persons;
    }

    public List<Person> searchPeopleByEmail(String email) {
        return highrise.path("people/search.xml").queryParam("criteria[email]", email).request(APPLICATION_XML_TYPE).get(People.class).persons;
    }

    public Person person(Integer id) {
        return highrise.path("people/" + id + ".xml").request(APPLICATION_XML_TYPE).get(Person.class);
    }

    public Person create(Person person) {
        return highrise.path("people.xml").request(APPLICATION_XML_TYPE).post(entity(person, APPLICATION_XML_TYPE), Person.class);
    }

    public Tag tag(Person person, String tagName) {
        return highrise.path("people/" + person.id + "/tags.xml").request(APPLICATION_XML_TYPE).post(entity(new Name(tagName), APPLICATION_XML_TYPE), Tag.class);
    }

    public void destroy(Person person) {
        highrise.path("people/" + person.id + ".xml").request(APPLICATION_XML_TYPE).delete();
    }
}
