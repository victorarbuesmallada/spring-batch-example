package org.cascomio.springbatchexample.models;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Property implements Serializable {
    private UUID id;
    private Long price = Long.MIN_VALUE;
    private Date onSaleSince = new Date();
    private String description = "No Desc";
    private String address = "No Address";
    private int numberOfBedrooms;
    private Integer size;
    private PropertyType propertyType;
    private Tenure tenure;
    private SellerType sellerType;
    private String source;
    private Coordinates coordinates;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Date getOnSaleSince() {
        return onSaleSince;
    }

    public void setOnSaleSince(Date onSaleSince) {
        this.onSaleSince = onSaleSince;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public Tenure getTenure() {
        return tenure;
    }

    public void setTenure(Tenure tenure) {
        this.tenure = tenure;
    }

    public SellerType getSellerType() {
        return sellerType;
    }

    public void setSellerType(SellerType sellerType) {
        this.sellerType = sellerType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
