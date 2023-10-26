package co.inventorsoft.academy.jdbc.model;

import java.util.Objects;

public class Company {
    private Long id;
    private String companyName;
    private String phone;
    private String address;
    private String city;
    private String region;
    private String zip;
    private String country;

    public Company() {
    }

    public Company(Long id, String companyName, String phone, String address, String city, String region, String zip, String country) {
        this.id = id;
        this.companyName = companyName;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.region = region;
        this.zip = zip;
        this.country = country;
    }

    public Company(String companyName, String phone, String address, String city, String region, String zip, String country) {
        this.companyName = companyName;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.region = region;
        this.zip = zip;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id) && Objects.equals(companyName, company.companyName) && Objects.equals(phone, company.phone) && Objects.equals(address, company.address) && Objects.equals(city, company.city) && Objects.equals(region, company.region) && Objects.equals(zip, company.zip) && Objects.equals(country, company.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, phone, address, city, region, zip, country);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
