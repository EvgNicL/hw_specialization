package human_friends.models;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Select implements ISelect{
    public Select() {
    }

    @Override
    public void getYoungAnimals(ArrayList<Animal> list) {
        for (var item: list) {
            LocalDate b = LocalDate.parse(item.birthday);
            Period period = getPeriod(b);
            if (period.getYears() >= 1 && period.getYears() <=3) System.out.print(item.toString() +
                    ChronoUnit.MONTHS.between(b.withDayOfMonth(1), LocalDate.now().withDayOfMonth(1)) + " months");
        }
    }
    private Period getPeriod(LocalDate date){
        return Period.between(date, LocalDate.now());
    }
    @Override
    public void getSummaryTable(ArrayList<Group> groups,ArrayList<Type> types,ArrayList<Animal> animals) {
         for (var item: animals) {
            for (var itemT: types){
                if (itemT.name.equals(item.typeName)) {
                    System.out.printf("id: %s, type: %s - %s, nickname: %s, birthday: %s, commands; %s;\n",
                                    item.id, itemT.id, item.typeName, item.name, item.birthday, item.commands);
                    break;  // до этого места работает,  а дальше не хочет... 
    //               for (var itemG: groups){
//                        if (itemG.name.equals(itemT.name)) {
//                            System.out.printf("id: %s, group: %s - %s, type: %s - %s, nickname: %s, birthday: %s, commands; %s;\n",
//                                    item.id, itemG.id, itemG.name, itemT.id,
//                                    item.typeName, item.name, item.birthday, item.commands);
//                           
//                        }
//                    }
                }
            }
        }
    }
}
