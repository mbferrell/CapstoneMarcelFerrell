package com.company.MarcelFerrellCapstone.viewModel;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

public class InvoiceViewModel {

    private int id;
    @NotEmpty(message = "Name required")
    @Size(max = 80)
    private String name;

    @NotEmpty(message = "Street required")
    @Size(max = 30)
    private String street;

    @NotEmpty(message = "City required")
    @Size(max = 30)
    private String city;

    @Size(max = 2, min = 2, message = "2-Letter State Code is required only.")
    @NotEmpty
    private String state;

    @NotEmpty(message = "Zip Code required")
    @Size(max = 5, min = 5, message = "Zip Code are 5 digits ")
    private String Zip;

    @NotEmpty(message = "Item type required")
    @Size(max = 20)
    private String itemType;

    @NotEmpty(message = "Item ID required")
    private int itemID;

    @NotEmpty(message = "Quantity required")
    @Size(max = 11)
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return Zip;
    }

    public void setZip(String zip) {
        Zip = zip;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return getId() == that.getId() && getQuantity() == that.getQuantity() && Objects.equals(getName(), that.getName()) && Objects.equals(getStreet(), that.getStreet()) && Objects.equals(getCity(), that.getCity()) && Objects.equals(getState(), that.getState()) && Objects.equals(getZip(), that.getZip()) && Objects.equals(getItemType(), that.getItemType()) && Objects.equals(getItemID(), that.getItemID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getStreet(), getCity(), getState(), getZip(), getItemType(), getItemID(), getQuantity());
    }
}