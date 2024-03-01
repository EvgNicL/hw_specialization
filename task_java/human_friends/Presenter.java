package human_friends;
import human_friends.models.Animal;
import human_friends.models.Commands;
import human_friends.models.Group;
import human_friends.models.Type;
public class Presenter {
    Model model;
    View view;

    public Presenter() {
        this.model = new Model();
        this.view = new View();
    }
    public void start(){
        model.load();
        int x = 1;
        while (x == 1){
            menu();
            x = view.startRqw();
        }
        model.save();
    }
    private void menu(){
        switch (view.menuRqw()){
            case 1:
                model.add(new Group(model.getLastId("animalsGroups") + 1, view.nameRqw()));
                break;
            case 2:
                int idType= model.getLastId("animalsTypes") + 1;
                model.print("g");
                model.add(new Type(idType, model.getNameGroup(view.fkIdRqw()), view.nameRqw()));
                break;
            case 3:
                int idAnimal= model.getLastId("animals") + 1;
                model.print("t");
                String typeName = model.getNameType(view.fkIdRqw());
                Commands commands = new Commands();
                String nickname = view.nicknameRqw();
                String birthday = view.birthdayRqw();
                commands.printCommands();

                model.add(new Animal(idAnimal, typeName, nickname, birthday,
                        commands.getCommands(view.fkIdRqw(), view.fkIdRqw(), view.fkIdRqw())));
                break;
            case 4:
                model.delType(view.idRqw());
                break;
            case 5:
                model.print("a");
                break;
            case 6:
                model.print("y");
                break;
            case 7:
                model.print("s");
                break;
            default:
                System.out.println("____");
        }
    }
}
