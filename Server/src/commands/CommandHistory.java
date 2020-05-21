package commands;

import communication.Response;

public class CommandHistory extends Command {
    @Override
    public String getName() {
        return "history";
    }

    @Override
    public String getManual() {
        return "Вывести последние 7 команд сервера (без аргументов).";
    }

    @Override
    public Response execute() {
        return new Response(getName(), context.handlerCommands.getHistory());
    }
}
