package com.client;

import com.beust.jcommander.JCommander;
import com.client.commands.ShowCommand;
import com.client.commands.enums.Commands;
import com.client.commands.enums.Errors;
import com.client.commands.interfaces.Commandable;
import com.client.commands.HelpCommand;
import com.client.commands.ListCommand;
import com.client.options.GlobalOptions;

import java.util.HashMap;
import java.util.Map;


public class Main {

    public static void main(String[] args) {

        GlobalOptions globalOptions = new GlobalOptions();

        Map<String, Commandable> commands = new HashMap<>();

        HelpCommand helpCommand = new HelpCommand();
        ListCommand listCommand = new ListCommand();
        ShowCommand showCommand = new ShowCommand();

        commands.put(Commands.HELP.getName(), helpCommand);
        commands.put(Commands.LIST.getName(), listCommand);
        commands.put(Commands.SHOW.getName(), showCommand);

        JCommander jc = new JCommander(globalOptions);

        jc.addCommand(helpCommand);
        jc.addCommand(listCommand);
        jc.addCommand(showCommand);

        try {
            jc.parse(args);
            if (globalOptions.version) {
                globalOptions.showVersion();
            } else if (globalOptions.commandName != null) {
                globalOptions.showCommandInfo();
            } else {
                commands.get(jc.getParsedCommand()).performCommand();
            }
        } catch (Exception e){
            if (e.getClass() == NullPointerException.class) {
                System.out.println(Errors.NO_ARGUMENT_ERROR.getMessage());
            } else {
                System.out.println(Errors.INVALID_ARGUMENT_ERROR.getMessage());
            }

        }
    }
}
