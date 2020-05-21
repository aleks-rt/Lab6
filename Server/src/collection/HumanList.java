package collection;

import elements.Car;
import elements.HumanBeing;

import java.util.*;
import java.util.stream.Collectors;


public class HumanList {
    private LinkedList<HumanBeing> humans;
    private HandlerId handlerId;

    public HumanList() {
        humans = new LinkedList<>();
        handlerId = new HandlerId();
    }

    public LinkedList<HumanBeing> getHumans() {
        return humans;
    }

    public String addHuman(HumanBeing human) {
        human.setId(handlerId.provideId());
        human.setCreationDate();
        humans.add(human);
        return "Элемент добавлен.";
    }

    public String clear() {
        handlerId.clear();
        humans.clear();
        return "Коллекция очищенна.";
    }

    public String removeHead() {
        try {
            HumanBeing human = humans.getFirst();
            handlerId.removeId(human.getId());
            humans.removeFirst();
            return human.toString();
        }
        catch (NoSuchElementException e) {
            return "Коллекция пуста, первого элемента нет!";
        }
    }

    public void remove(HumanBeing human) {
        handlerId.removeId(human.getId());
        humans.remove(human);
    }

    public String removeById(int id) {
        if (!handlerId.contains(id))
            return "Элемент HumanBeing с таким id не найден.";
        for (HumanBeing human : humans) {
            if (human.getId() == id) {
                remove(human);
                break;
            }
        }
        return "Элемент Hu с таким id удален.";
    }

    public String updateById(int id, HumanBeing modifiedHuman) {
        try {
            humans.stream().filter(humanBeing -> humanBeing.getId() == id).findFirst().get().updateHuman(modifiedHuman);
            return "Элемент HumanBeing с введенным id заменен.";
        } catch (Exception e) {
            return "Элемент HumanBeing с таким id не найден.";
        }
    }

    public String removeGreater(HumanBeing srcHuman) {
        humans = humans.stream().filter(humanBeing -> humanBeing.getImpactSpeed() > srcHuman.getImpactSpeed())
                .collect(Collectors.toCollection(LinkedList::new));
        return "Команда выполнена!";
    }

    public void sort(Comparator<HumanBeing> comparator) {
        humans = humans.stream().sorted(comparator).collect(Collectors.toCollection(LinkedList::new));
    }

    public void sort() {
        humans = humans.stream().sorted((h1, h2) -> Float.compare(h1.getImpactSpeed(), h2.getImpactSpeed())).collect(Collectors.toCollection(LinkedList::new));
    }

    public String printInfo() {
        return "Тип коллекции: " + humans.getClass() + ", Размер: " + humans.size();
    }

    private LinkedList<HumanBeing> getDescendingHumans() {
        LinkedList<HumanBeing> result = humans.stream().sorted((h1, h2) -> Float.compare(h1.getImpactSpeed(), h2.getImpactSpeed()) * -1).collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    public String printDescending() {
        return getDescendingHumans().toString();
    }

    public String printFieldDescendingCar() {
        LinkedList<Car> result = getDescendingHumans().stream().map(x -> x.getCar()).collect(Collectors.toCollection(LinkedList::new));
        return result.toString();
    }

    public String printUniqueCar() {
        HashSet<Car> cars = humans.stream().map(x -> x.getCar()).collect(Collectors.toCollection(HashSet::new));
        return cars.toString();
    }

    public String getAlphabet(LinkedList<HumanBeing> srcHumans) {
        srcHumans = srcHumans.stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
                .collect(Collectors.toCollection(LinkedList::new));
        return srcHumans.toString();
    }

    public String getAlphabet() {
        return getAlphabet(new LinkedList<>(humans));
    }
}
