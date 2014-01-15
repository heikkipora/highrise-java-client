package com.drillsoft.highrise.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "contact-data")
public class Contact {
    @XmlElementWrapper(name = "email-addresses")
    @XmlElement(name = "email-address")
    public List<EmailAddress> emailAddresses;
    @XmlElementWrapper(name = "phone-numbers")
    @XmlElement(name = "phone-number")
    public List<PhoneNumber> phoneNumbers;
    @XmlElementWrapper(name = "addresses")
    @XmlElement(name = "address")
    public List<Address> addresses;
    @XmlElementWrapper(name = "instant-messengers")
    @XmlElement(name = "instant-messenger")
    public List<InstantMessenger> instantMessengers;
    @XmlElementWrapper(name = "twitter-accounts")
    @XmlElement(name = "twitter-account")
    public List<TwitterAccount> twitterAccounts;
    @XmlElementWrapper(name = "web-addresses")
    @XmlElement(name = "web-addresss")
    public List<WebAddress> webAddresses;

    public static class EmailAddress {
        public Integer id;
        public String location;
        public String address;

        public EmailAddress() {
        }

        public EmailAddress(String address, String location) {
            this.address = address;
            this.location = location;
        }
    }

    public static class PhoneNumber {
        public Integer id;
        public String location;
        public String number;
    }

    public static class Address {
        public Integer id;
        public String location;
        public String city;
        public String country;
        public String state;
        public String street;
        public String zip;
    }

    public static class InstantMessenger {
        public Integer id;
        public String location;
        public String address;
        public String protocol;
    }

    public static class TwitterAccount {
        public Integer id;
        public String location;
        public String username;
        public String url;
    }

    public static class WebAddress {
        public Integer id;
        public String location;
        public String url;
    }
}