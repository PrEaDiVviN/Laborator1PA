package compulsory.model;

public class Employee {
    private int id;
    private String name;
    private String birthName;
    private int height;
    private String birthDate;
    private String type;

    public Employee() {}

    public Employee(int id, String name, String birthName, int height, String birthDate, String type) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.height = height;
        this.birthName = birthName;
        this.type = type;
    }


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

    public String getBirthName() {
        return birthName;
    }

    public void setBirthName(String birthName) {
        this.birthName = birthName;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Employee ( " + id + ", " + name + ", " + birthName + ", " + height + ", " + birthDate + ", " + type + " )\n";
    }
}
