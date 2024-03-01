package human_friends.models;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Select implements ISelect{
    public Select() {
    }

    @Override
    public void getYoungAnimals(ArrayList<Animal> list) {
        for (var item: list) {
            LocalDate b = LocalDate.parse(item.birthday);
            Period period = getPeriod(b);
            if (period.getYears() >= 1 && period.getYears() <=3) System.out.println(item.toString() + period.getMonths());
        }
    }
    private Period getPeriod(LocalDate date){
        return Period.between(date, LocalDate.now());
    }
    @Override
    public void getSummaryTable(ArrayList<Group> groups,ArrayList<Type> types,ArrayList<Animal> animals) {
        for (var itemGroup: groups) {
            for (var itemType: types){
                for (var itemAnimal: animals) {
                    if (itemAnimal.typeName.equals(itemType.name) && itemType.name.equals(itemGroup.name))
                        System.out.printf("id: %s, group: %s - %s, type: %s - %s, nickname: %s, birthday: %s;\n",
                                itemAnimal.id, itemGroup.id, itemGroup.name, itemType.id, itemType.name,
                                itemAnimal.name, itemAnimal.birthday);
                }
            }
        }
    }
}
