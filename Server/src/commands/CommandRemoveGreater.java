package commands;

import communication.Response;
import elements.HumanBeing;

public class CommandRemoveGreater extends Command {
    @Override
    public String getName() {
        return "remove_greater";
    }

    @Override
    public String getManual() {
        return "Удалить из коллекции элементы, превышающие заданный. Параметры: {element}.";
    }

    @Override
    public Response execute() {
        return new Response(getName(), context.humanList.removeGreater((HumanBeing) arguments[0].getValue()));
    }
}
