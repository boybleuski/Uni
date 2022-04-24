package lab3;

public class ContentBean {
    private String name;
    private int age;

    public ContentBean() {}

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

    public String getMessage() {
        if (age < 18) return "You're too young to visit this site!";
        else if (age > 67) return "Go home grampa!";
        else return "Welcome to our site, " + name + ".";
    }
}
