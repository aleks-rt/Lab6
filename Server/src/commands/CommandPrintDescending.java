package commands;

import communication.Response;

public class CommandPrintDescending extends Command {

    @Override
    public String getName() {
        return "print_descending";
    }

    @Override
    public String getManual() {
        return "Вывести элементы коллекции в порядке убывания";
    }

    @Override
    public Response execute() {
        return new Response(getName(), context.humanList.printDescending());
    }
}
