package ognora.contactlist;

public class ContactModel {

    String name, phno ;

    public ContactModel(String name, String phno) {
        this.name = name;
        this.phno = phno;
    }

    public String getName() {
        return name;
    }

    public String getPhno() {
        return phno;
    }
}
