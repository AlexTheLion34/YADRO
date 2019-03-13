package com.client.commands;

import com.beust.jcommander.Parameters;
import com.client.commands.interfaces.Commandable;

@Parameters(commandNames = {"help"})
public class HelpCommand implements Commandable {

    public void performCommand() {
        System.out.println("NAME:\n\t cli_net - client displays network interfaces and info \n");
        System.out.println("USAGE:\n\t cli_net [global com.client.options] command [command com.client.options] [arguments...]\n");
        System.out.println("VERSION:\n\t 0.0.0 \n");
        System.out.println("COMMANDS:\n\t help - shows description and list of com.client.commands\n\t " +
                "list [command com.client.options] [arguments...] - shows list of networking interfaces\n\t " +
                "show [command com.client.options] [arguments...] - shows info about networking interface\n");
        System.out.println("GLOBAL OPTIONS:\n\t --version - shows version information" +
                "\n\t -h [command name] - shows info about command");
    }
}
