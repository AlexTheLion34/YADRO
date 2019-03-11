package options;

import com.beust.jcommander.Parameter;

public class GlobalOptions {

    @Parameter(names = {"--version"}, arity = 0)
    public boolean version = false;

    @Parameter(names = {"-h"})
    public String commandName;

    public void showVersion() {
        System.out.println("0.0.0");
    }

    public void showCommandInfo() {

        switch (commandName) {
            case "help":
                System.out.println("DESCRIPTION:\n\t help - shows list of commands and information");
                break;
            case "list":
                System.out.println("DESCRIPTION:\n\t list - shows shows list of networking interfaces\n" +
                        "OPTIONS:\n\t --server [argument] --port [argument]");
                break;
            case "show":
                System.out.println("DESCRIPTION:\n\t show - shows info about networking interface\n" +
                        "OPTIONS:\n\t --name [argument] --server [argument] --port [argument]");
                break;
            default:
                System.out.println("There is no such command: " + commandName);
                break;
        }
    }
}
