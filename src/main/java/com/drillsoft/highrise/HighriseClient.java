package com.drillsoft.highrise;

import com.drillsoft.highrise.domain.Name;
import com.drillsoft.highrise.domain.Note;
import com.drillsoft.highrise.domain.People;
import com.drillsoft.highrise.domain.Person;
import com.drillsoft.highrise.domain.Tag;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.glassfish.jersey.media.multipart.file.StreamDataBodyPart;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.ByteArrayInputStream;
import java.io.File;
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
                .register(MultiPartFeature.class)
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

    public Note addNote(Person person, String text) {
        Note note = new Note(text);
        return highrise.path("people/" + person.id + "/notes.xml").request(APPLICATION_XML_TYPE).post(entity(note, APPLICATION_XML_TYPE), Note.class);
    }

    public Note addAttachments(Person person, List<File> attachments) {
        FormDataMultiPart form = new FormDataMultiPart();
        for (File attachment : attachments) {
            form.bodyPart(new FileDataBodyPart("note[files][]", attachment));
        }
        return highrise.path("people/" + person.id + "/notes.xml").request(APPLICATION_XML_TYPE).post(entity(form, form.getMediaType()), Note.class);
    }

    public void destroy(Person person) {
        highrise.path("people/" + person.id + ".xml").request(APPLICATION_XML_TYPE).delete();
    }
}