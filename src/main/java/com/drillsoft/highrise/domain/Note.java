package com.drillsoft.highrise.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "note")
public class Note {
    public Integer id;
    public String body;
    @XmlElement(name = "author-id")
    public Integer authorId;
    @XmlElement(name = "subject-id")
    public Integer subjectId;
    @XmlElement(name = "subject-name")
    public String subjectName;
    @XmlElement(name = "collection-id")
    public Integer collectionId;
    @XmlElement(name = "collection-type")
    public String collectionType;
    @XmlElement(name = "visible-to")
    public String visibleTo;
    @XmlElement(name = "owner-id")
    public Integer ownerId;
    @XmlElement(name = "group-id")
    public Integer groupId;
    @XmlElement(name = "updated-at")
    public Date updatedAt;
    @XmlElement(name = "created-at")
    public Date createdAt;
    @XmlElementWrapper(name = "attachments")
    @XmlElement(name = "attachment")
    public List<Attachment> attachments;

    public Note() {
    }

    public Note(String body) {
        this.body = body;
    }

    public static class Attachment {
        public Integer id;
        public String url;
        public String name;
        public Integer size;
    }
}