import store.exceptions.StoreNotFoundException;
import store.ui.CommandLineInterface;

public class Main {
    public static void main(String[] args) throws StoreNotFoundException {

        CommandLineInterface commands = new CommandLineInterface();
        commands.start();

        System.out.println("gg");
    }
}