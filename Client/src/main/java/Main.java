import com.beust.jcommander.JCommander;
import commands.ShowCommand;
import commands.enums.Commands;
import commands.enums.Errors;
import commands.interfaces.Commandable;
import commands.HelpCommand;
import commands.ListCommand;
import options.GlobalOptions;

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

        jc.parse(args);

        try {
            if (globalOptions.version) {
                globalOptions.showVersion();
            } else if (globalOptions.commandName != null) {
                globalOptions.showCommandInfo();
            } else {
                commands.get(jc.getParsedCommand()).performCommand();
            }
        } catch (NullPointerException e){
            System.out.println(Errors.NO_ARGUMENT_ERROR.getMessage());
        }
    }
}
