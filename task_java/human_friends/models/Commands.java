package human_friends.models;

public class Commands implements ICommands{
    String[] commands;

    public Commands() {
        this.commands = new String[]{"sit", "stay", "pounce", "roll", "hide", "paw", "voice", "spin", "jump", "trot", "canter",
                "kick", "walk", "gallop", "run", "scratch", "carry load", " "};

    }
    public void printCommands(){
        System.out.println("\nСписок команд:");
        for (int i = 0; i < commands.length; i++)
            System.out.println(i + " - " + commands[i] + ", ");
        System.out.println("\nВведите 3 команды:");
    }
    @SuppressWarnings("ReassignedVariable")
    public String getCommands(Integer c1, Integer c2, Integer c3){
        String fieldCommands = "";
        for (int i = 0; i < commands.length; i++) {
            if (i == c1) fieldCommands = fieldCommands + commands[i] + "; ";
            if (i == c2) fieldCommands = fieldCommands + commands[i] + "; ";
            if (i == c3) fieldCommands = fieldCommands + commands[i] + "; ";
        }
        return  fieldCommands;
    }

}
