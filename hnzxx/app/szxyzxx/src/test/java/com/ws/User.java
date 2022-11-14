package com.ws;

public class User {
    private String name;
    private String sex;
    private String adress;
    private String age;

    public User(String name, String sex, String adress, String age) {
        this.name = name;
        this.sex = sex;
        this.adress = adress;
        this.age = age;
    }
    public User(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
