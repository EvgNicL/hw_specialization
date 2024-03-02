package human_friends.models;
import java.util.Arrays;

public class Animal extends Table{
    public String birthday;
    public String typeName;
    public String commands;
    public Animal(Integer id, String typeName, String name, String birthday, String commands) {
        super(id, name);
        this.typeName = typeName;
        this.birthday = birthday;
        this.commands = commands;
    }

    @Override
    public String toString() {
        return id + ", " + typeName + ", " + name + ", " + birthday + ", " + commands+ "\n";
    }
}
