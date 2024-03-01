package human_friends;
import java.time.LocalDate;
import java.util.Scanner;
public class View {
    Scanner scanner;

    public View() {
        this.scanner = new Scanner(System.in);
    }

    public int startRqw() {
        System.out.println("\n1 - продолжить работу\n2 - выход \n");
        return scanner.nextInt();
    }
    public int menuRqw(){
        try {
            System.out.println("menu:\n" +
                    "1 - добавить группу животных \n" +
                    "2 - добавить вид животных \n" +
                    "3 - добавить животное \n" +
                    "4 - удалить вид животных\n" +
                    "5 - просмотреть список всех животных \n" +
                    "6 - просмотреть список молодых животных \n" +
                    "7 - просмотреть сводную таблицу\n");
            return scanner.nextInt();
        } catch (RuntimeException e){
            System.out.println("\nне корректный ввод\n");
        }
        return 0;
    }
    public Integer fkIdRqw(){
        try {
            System.out.println("Выберите id: ");
            return scanner.nextInt();
        } catch (RuntimeException e){
            System.out.println("\nне корректный ввод\n");
        }
        return 0;
    }
    public int idRqw() {
        try {
            System.out.println("Введите id: ");
            return scanner.nextInt();
        } catch (RuntimeException e){
            System.out.println("\nне корректный ввод\n");
        }
        return 0;
    }
    public String nameRqw() {
            System.out.println("Введите название: ");
            return scanner.next();
    }
    public String nicknameRqw() {
            System.out.println("Введите кличку животного: ");
            return scanner.next();
    }
    public String birthdayRqw() {
        try {
            System.out.println("Введите дату рождения животного (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(scanner.next());
            if (date.getYear() < 1900) System.out.println("это слишком старое животное");
            if (date.getYear() > LocalDate.now().getYear() || (date.getYear() == LocalDate.now().getYear()
                    && date.getMonthValue() > LocalDate.now().getMonthValue()) ||  (date.getYear() == LocalDate.now().getYear()
                    && date.getMonthValue() == LocalDate.now().getMonthValue())
                    && date.getDayOfMonth() > LocalDate.now().getDayOfMonth()) System.out.println("это слишком молодое животное");
            return date.toString();
        } catch (RuntimeException e){
            System.out.println("\nне корректный ввод\n");
        }
        return null;
    }
}
