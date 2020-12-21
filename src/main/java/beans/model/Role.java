package beans.model;

public class Role {
    private int id;
    private String name;

    public Role() {}
    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Role {" +
            "id=" + getId() + "," +
            "name=" + getName() +
            "}";
    }
}
