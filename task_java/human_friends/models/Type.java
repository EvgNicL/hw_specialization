package human_friends.models;
public class Type extends Table{
    public String groupName;
    public Type(Integer id, String groupName, String name) {
        super(id,name);
        this.groupName = groupName;
    }
    @Override
    public String toString() {
        return id + ", " + groupName + ", " + name + "\n";
    }
}
