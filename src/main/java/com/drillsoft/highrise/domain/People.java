package com.drillsoft.highrise.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class People {
    @XmlElement(name = "person")
    public List<Person> persons = new ArrayList<Person>();
}
