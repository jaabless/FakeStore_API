package com.example.fakestoreapi.model;

public class User {
    private int id;
    private String email;
    private String username;
    private String password;
    private Name name;
    private Address address;

    public User() {}


    public User(int id, String email, String username, String password, Name name, Address address) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Name getName() { return name; }
    public void setName(Name name) { this.name = name; }
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public static class Name {
        private String firstname;
        private String lastname;

        public Name() {}

        public Name(String firstname, String lastname) {
            this.firstname = firstname;
            this.lastname = lastname;
        }

        public String getFirstname() { return firstname; }
        public void setFirstname(String firstname) { this.firstname = firstname; }
        public String getLastname() { return lastname; }
        public void setLastname(String lastname) { this.lastname = lastname; }
    }

    public static class Address {
        private String street;
        private String city;
        private String zipcode;
        private Geo geolocation;
        private String number;

        public Address() {}

        public Address(String street, String city, String zipcode, Geo geolocation, String number) {
            this.street = street;
            this.city = city;
            this.zipcode = zipcode;
            this.geolocation = geolocation;
            this.number = number;
        }

        public String getStreet() { return street; }
        public void setStreet(String street) { this.street = street; }
        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }
        public String getZipcode() { return zipcode; }
        public void setZipcode(String zipcode) { this.zipcode = zipcode; }
        public Geo getGeolocation() { return geolocation; }
        public void setGeolocation(Geo geolocation) { this.geolocation = geolocation; }
        public String getNumber() { return number; }
        public void setNumber(String number) { this.number = number; }
    }

    public static class Geo {
        private String lat;
        private String lng;

        public Geo() {}

        public Geo(String lat, String lng) {
            this.lat = lat;
            this.lng = lng;
        }

        public String getLat() { return lat; }
        public void setLat(String lat) { this.lat = lat; }
        public String getLng() { return lng; }
        public void setLng(String lng) { this.lng = lng; }
    }
}