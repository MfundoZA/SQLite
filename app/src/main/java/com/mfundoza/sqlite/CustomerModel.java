package com.mfundoza.sqlite;

public class CustomerModel {
    // Fields
    private int id;
    private String name;
    private int age;
    private boolean isActiveCustomer;

    // Constructors
    public CustomerModel() {}

    public CustomerModel(int id, String name, int age, boolean isActiveCustomer) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isActiveCustomer = isActiveCustomer;
    }

    // Getters and setters

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActiveCustomer() {
        return isActiveCustomer;
    }

    public void setActiveCustomer(boolean activeCustomer) {
        isActiveCustomer = activeCustomer;
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isActiveCustomer=" + isActiveCustomer +
                '}';
    }
}
