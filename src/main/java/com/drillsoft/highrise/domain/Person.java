package com.drillsoft.highrise.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "person")
public class Person {
    @XmlElement(name = "first-name")
    public String firstName;
    @XmlElement(name = "last-name")
    public String lastName;
    public String title;
    public Integer id;
    public String background;
    @XmlElement(name = "linkedin-url")
    public String linkedinUrl;
    @XmlElement(name = "avatar-url")
    public String avatarUrl;
    @XmlElement(name = "company-id")
    public Integer companyId;
    @XmlElement(name = "company-name")
    public String companyName;
    @XmlElement(name = "created-at")
    public Date createdAt;
    @XmlElement(name = "updated-at")
    public Date updatedAt;
    @XmlElement(name = "visible-to")
    public String visibleTo;
    @XmlElement(name = "owner-id")
    public Integer ownerId;
    @XmlElement(name = "group-id")
    public Integer groupId;
    @XmlElement(name = "author-id")
    public Integer authorId;
    @XmlElement(name = "contact-data")
    public Contact contact = new Contact();
    @XmlElementWrapper(name = "tags")
    @XmlElement(name = "tag")
    public List<Tag> tags;
}
