package Mangers;

import Commands.*;

import java.util.HashMap;

public class CommandManager {
    public static HashMap<String, Command> commands = new HashMap<String, Command>();


    public CommandManager() {
        commands.put("help", new Help());
        commands.put("info", new Info());
        commands.put("show", new Show());
        commands.put("clear", new Clear());
        commands.put("save", new Save());
        commands.put("average_of_engine_power", new AverageOfEnginePower());
        commands.put("insert", new Insert());
        commands.put("update", new Update());
        commands.put("remove_key", new RemoveKey());
        commands.put("remove_greater", new RemoveGreater());
        commands.put("replace_if_greater", new ReplaceIfGreater());
        commands.put("remove_greater_key", new RemoveGreaterKey());
        commands.put("remove_all_by_capacity", new RemoveAllByCapacity());
        commands.put("filter_less_than_engine_power", new FilterLessThanEnginePower());
        commands.put("execute_script", new ExecuteScript());
        commands.put("exit", new Exit());

    }
    public static Command getCommand(String name){
        return commands.get(name);
    }

    public static void setCommand(HashMap<String, Command> commands) {
        CommandManager.commands = commands;
    }
}
