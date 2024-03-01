package human_friends;
import human_friends.models.*;

import java.io.*;
import java.util.ArrayList;

public class Model implements ICollection {
    String groupFile;
    String typeFile;
    String animalFile;
    ArrayList<Group> animalsGroups;
    ArrayList<Type> animalsTypes;
    ArrayList<Animal> animals;
    Select select;
    public Model() {
        this.groupFile = "AnimalsGroups.txt";
        this.typeFile = "AnimalsTypes.txt";
        this.animalFile = "Animals.txt";
        this.animalsGroups = new ArrayList<>();
        this.animalsTypes = new ArrayList<>();
        this.animals = new ArrayList<>();
        this.select =new Select();
    }

    @Override
    public void add(Object t) {
        switch (t.getClass().getName()){
            case ("human_friends.models.Group"):
                animalsGroups.add((Group) t);
                System.out.println("позиция добавлена");
                System.out.println(t);
                break;
            case ("human_friends.models.Type"):
                animalsTypes.add((Type) t);
                System.out.println("позиция добавлена");
                System.out.println(t);
                break;
            case ("human_friends.models.Animal"):
                animals.add((Animal) t);
                System.out.println("позиция добавлена");
                System.out.println(t);
                 break;
            default:
                System.out.println("ошибка добавления");
        }
    }
    @Override
    public void delType(Integer i) {
        try {
            animalsTypes.removeIf(item -> item.id.equals(i));
        } catch (RuntimeException ex){
            System.out.println("такого id нет");
        }
    }
    public Integer getLastId(String tableName){
        try {
            if (tableName.equals("animalsGroups")){
                if (!animalsGroups.isEmpty()){
                    Group last = animalsGroups.get(animalsGroups.size()-1);
                    return last.id;
                } else return 0;
            }
            if (tableName.equals("animalsTypes")){
                if (!animalsTypes.isEmpty()){
                    Type lastT = animalsTypes.get(animalsTypes.size()-1);
                    return lastT.id;
                } else return 0;
            }
            if (tableName.equals("animals")){
                if (!animals.isEmpty()) {
                    Animal last = animals.get(animals.size()-1);
                    return last.id;
                } else return 0;
            }
        } catch (RuntimeException e) {
            System.out.println("ошибка определения последней записи");
        }
        return 0;
    }
    @Override
    public void print(String str) {
        switch (str){
            case ("g"):
                System.out.println("\n animalsGroups:");
                for (var item: animalsGroups) System.out.println(item.toString());
                break;
            case ("t"):
                System.out.println("\n animalsTypes:");
                for (var item: animalsTypes) System.out.println(item.toString());
                break;
            case ("a"):
                System.out.println("\n animals:");
                for (var item: animals) System.out.println(item.toString());
                break;
            case ("y"):
                System.out.println("\n young animals:");
                select.getYoungAnimals(animals);
                break;
            case ("s"):
                System.out.println("\n summary table:");
                select.getSummaryTable(animalsGroups,animalsTypes,animals);
                break;
            default:
                System.out.println("не корректный ввод");
        }
    }
    private void saveToFile(String file) {
        try (FileWriter writer = new FileWriter(file, false)) {
            switch (file){
                case ("AnimalsGroups.txt"):
                    for (var item : animalsGroups) writer.append(item.toString());
                    writer.flush();
                    break;
                case ("AnimalsTypes.txt"):
                    for (var item : animalsTypes) writer.append(item.toString());
                    writer.flush();
                    break;
                case ("Animals.txt"):
                    for (var item : animals) writer.append(item.toString());
                    writer.flush();
                    break;
                default:
                    System.out.println("----");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private void loadFile(String file) {
       switch (file){
           case ("AnimalsGroups.txt"):
               try {
                   BufferedReader reader = new BufferedReader(new FileReader(file));
                   String line = reader.readLine();
                   while (line != null) {
                       String[] parts = line.split(", ");
                       animalsGroups.add(new Group(Integer.parseInt(parts[0]), parts[1]));
                       line = reader.readLine();
                   }
                   reader.close();
               } catch (IOException e) {
                   System.out.println("ошибка загрузки данных");
               }
               break;
           case ("AnimalsTypes.txt"):
               try {
                   BufferedReader reader = new BufferedReader(new FileReader(file));
                   String line = reader.readLine();
                   while (line != null) {
                       String[] parts = line.split(", ");
                       animalsTypes.add(new Type(Integer.parseInt(parts[0]), parts[1], parts[2]));
                       line = reader.readLine();
                   }
                   reader.close();
               } catch (IOException e) {
                   System.out.println("ошибка загрузки данных");
               }
               break;
           case ("Animals.txt"):
               try {
                   BufferedReader reader = new BufferedReader(new FileReader(file));
                   String line = reader.readLine();
                   while (line != null) {
                       String[] parts = line.split(", ");
                       animals.add(new Animal(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4]));
                       line = reader.readLine();
                   }
                   reader.close();
               } catch (IOException e) {
                   System.out.println("ошибка загрузки данных");
               }
               break;
           default:
               System.out.println("---");
       }
    }
    public void save(){
        saveToFile(groupFile);
        saveToFile(typeFile);
        saveToFile(animalFile);
    }
    public void load(){
        loadFile(groupFile);
        loadFile(typeFile);
        loadFile(animalFile);
    }
    @Override
    public String getNameGroup(Integer id) {
        try {
            for (var item : animalsGroups) {
                if (item.id.equals(id)) return item.name;
            }
        } catch (RuntimeException e){
            System.out.println("id отсутствует");
        }
        return null;
    }
    @Override
    public String getNameType(Integer id) {
        try {
            for (var item : animalsTypes) {
                if (item.id.equals(id)) return item.name;
            }
        } catch (RuntimeException e){
            System.out.println("id отсутствует");
        }
        return null;
    }
    public void getYoungAnimals(){
        select.getYoungAnimals(animals);
    }
    public void getSummaryTable(){
        select.getSummaryTable(animalsGroups,animalsTypes,animals);
    }
}
