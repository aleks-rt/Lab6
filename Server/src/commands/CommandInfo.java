package commands;

import communication.Response;

public class CommandInfo extends Command {

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getManual() {
        return "Вывести информацию о коллекции.";
    }

    @Override
    public Response execute() {
        return new Response(getName(), context.humanList.printInfo());
    }
}
