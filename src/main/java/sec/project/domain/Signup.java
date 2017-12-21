package sec.project.domain;

public class Signup {

    private String name;
    private String address;

    public Signup() {
        super();
    }

    public Signup(String name, String address) {
        this();
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
