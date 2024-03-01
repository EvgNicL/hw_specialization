package human_friends.models;


import java.util.ArrayList;

public interface ICollection {
    void add(Object t);
    Integer getLastId(String tableName);
    void delType(Integer id);
    void print(String str);
    void save();
    void load();
    String getNameGroup(Integer id);
    String getNameType(Integer id);
}
