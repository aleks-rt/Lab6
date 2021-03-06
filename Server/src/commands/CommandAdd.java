package commands;

import communication.Response;
import elements.HumanBeing;

public class CommandAdd extends Command {

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getManual() {
        return "Добавить новый элемент в коллекцию. Параметры: {element}.";
    }

    @Override
    public Response execute() {
        return new Response(getName(), context.humanList.addHuman((HumanBeing) arguments[0].getValue()));
    }
}
