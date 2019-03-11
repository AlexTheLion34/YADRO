package commands;

import com.beust.jcommander.Parameters;
import commands.enums.Commands;
import commands.interfaces.Commandable;

@Parameters(commandNames = {"help"})
public class HelpCommand implements Commandable {

    public void performCommand() {
        System.out.println("NAME:\n\t cli_net - client displays network interfaces and info \n");
        System.out.println("USAGE:\n\t cli_net [global options] command [command options] [arguments...]\n");
        System.out.println("VERSION:\n\t 0.0.0 \n");
        System.out.println("COMMANDS:\n\t help - shows description and list of commands\n\t " +
                "list [command options] [arguments...] - shows list of networking interfaces\n\t " +
                "show [command options] [arguments...] - shows info about networking interface\n");
        System.out.println("GLOBAL OPTIONS:\n\t --version - shows version information" +
                "\n\t -h [command name] - shows info about command");
    }
}
