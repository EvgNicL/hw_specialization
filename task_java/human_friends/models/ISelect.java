package human_friends.models;

import java.util.ArrayList;

public interface ISelect {
    void getYoungAnimals(ArrayList<Animal> list);
    void getSummaryTable(ArrayList<Group> lG,ArrayList<Type> lT,ArrayList<Animal> lA);
}
