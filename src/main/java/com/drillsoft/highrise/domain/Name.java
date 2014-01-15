package com.drillsoft.highrise.domain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement
public class Name {
    @XmlValue
    public String value;

    public Name() {
    }

    public Name(String value) {
        this.value = value;
    }
}
