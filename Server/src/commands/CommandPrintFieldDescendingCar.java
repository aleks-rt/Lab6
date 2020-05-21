package commands;

import communication.Response;

public class CommandPrintFieldDescendingCar extends Command {
    @Override
    public String getName() {
        return "print_field_descending_car";
    }

    @Override
    public String getManual() {
        return "Вывести значения поля \"car\" всех элементов в порядке убывания.";
    }

    @Override
    public Response execute() {
        return new Response(getName(), context.humanList.printFieldDescendingCar());
    }
}
