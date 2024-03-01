package human_friends.models;


import java.util.ArrayList;

public class Table implements ITable{
    public  Integer id;
    public  String name;

    public Table(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return  id + ", " + name + '\n';
    }

    public String getName() {
        return name;
    }
}
