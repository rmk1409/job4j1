package ru.job4j.tracker;

/**
 * The enter to the program.
 * Created by roman.pogorelov on 30.08.2019
 */
public class StartUI {

    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";

    /**
     * Константа меню для показа всех заявок.
     */
    private static final String ALL = "1";

    /**
     * Константа меню для редактирования заявки.
     */
    private static final String EDIT = "2";

    /**
     * Константа меню для удаления заявки.
     */
    private static final String DELETE = "3";

    /**
     * Константа меню для поиска заявки по id.
     */
    private static final String FIND_BY_ID = "4";

    /**
     * Константа меню для поиска заявок по имени.
     */
    private static final String FIND_BY_NAME = "5";

    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     *
     * @param input   ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        do {
            menu.show();
            menu.select(Integer.valueOf(input.ask("select:")));
        } while (!"y".equals(this.input.ask("Exit?(y): ")));
    }

//    /**
//     * Метод реализует добавления новой заявки в хранилище.
//     */
//    private void createItem() {
//        System.out.println("------------ Добавление новой заявки --------------");
//        String name = this.input.ask("Введите имя заявки :");
//        String desc = this.input.ask("Введите описание заявки :");
//        Item item = new Item(name, desc);
//        this.tracker.add(item);
//        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
//        System.out.println("---------------------------------");
//    }
//
//    /**
//     * Метод показывает все заявки.
//     */
//    private void showAllItems() {
//        System.out.println("--------------- Показ всех заявок ---------------");
//        Item[] all = this.tracker.findAll();
//        if (all.length == 0) {
//            System.out.println("Заявок нет.");
//        } else {
//            for (Item item : all) {
//                System.out.println(String.format("%s %s %s.", item.getId(), item.getName(), item.getDescription()));
//            }
//        }
//        System.out.println("---------------------------------");
//    }
//
//    /**
//     * Метод редактирует заявку
//     */
//    private void editItem() {
//        System.out.println("------------------ Редактирование заявки -----------------");
//        String id = this.input.ask("Введите id редактируемой заявки :");
//        String name = this.input.ask("Введите имя заявки :");
//        String desc = this.input.ask("Введите описание заявки :");
//        Item item = new Item(name, desc);
//        System.out.println(this.tracker.replace(id, item) ? "Заявка успешно изменена." : "Заявка не изменена.");
//        System.out.println("---------------------------------");
//    }
//
//    /**
//     * Метод удаляет заявку
//     */
//    private void deleteItem() {
//        System.out.println("------------------- Удаление заявки -----------------");
//        String id = this.input.ask("Введите id редактируемой заявки :");
//        System.out.println(this.tracker.delete(id) ? "Заявка успешно удалена." : "Заявка не удалена.");
//        System.out.println("---------------------------------");
//    }
//
//    /**
//     * Метод находит заявку по id
//     */
//    private void findById() {
//        System.out.println("------------------- Поиск заявки по id -----------------");
//        Item item = this.tracker.findById(this.input.ask("Введите id заявки :"));
//        String result = item == null ? "Заявка не найдена" : String.format("Заявка найдена - %s %s %s.", item.getId(), item.getName(), item.getDescription());
//        System.out.println(result);
//        System.out.println("---------------------------------");
//    }
//
//    /**
//     * Метод находит заявки по имени
//     */
//    private void findByName() {
//        System.out.println("------------------- Поиск заявок по имени -----------------");
//        Item[] items = this.tracker.findByName(this.input.ask("Введите имя заявки :"));
//        if (items.length == 0) {
//            System.out.println("Заявки не найдены");
//        } else {
//            for (Item item : items) {
//                System.out.println(String.format("%s %s %s.", item.getId(), item.getName(), item.getDescription()));
//            }
//        }
//        System.out.println("---------------------------------");
//    }
//
//    private void showMenu() {
//        System.out.println("Меню.");
//        System.out.println("0. Add new item");
//        System.out.println("1. Show all items");
//        System.out.println("2. Edit item");
//        System.out.println("3. Delete item");
//        System.out.println("4. Find item by Id");
//        System.out.println("5. Find items by name");
//        System.out.println("6. Exit Program");
//    }

    /**
     * Запускт программы.
     *
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
