import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        TaskService taskService = new TaskService();
        Scanner scanner = new Scanner(System.in);
        menu();
        while (scanner.hasNextLine()) {
            String menu = scanner.nextLine();
            switch (menu) {
                case "1":
                    taskService.add(repeatability(title(), description(), type(), LocalDateTime.now()));
                    menu();
                    break;
                case "2":
                    System.out.println("Введите дату ГГГГ-ММ-ДД: ");
                    String date = scanner.nextLine();
                    try {
                        checkDate(date);
                        LocalDate localDate = LocalDate.parse(date);
                        System.out.println("\nСписок задач на " + localDate + ":");
                        try {
                            taskService.printTasks(taskService.tasksDate(localDate));
                        } catch (TaskNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        menu();
                    } catch (DateTimeParseException e) {
                        System.out.println(e.getMessage());
                        menu();
                    }
                    break;
                case "3":
                    System.out.println("Введите id задачи, которую необходимо удалить: ");
                    int taskId = scanner.nextInt();
                    try {
                        taskService.removeTask(taskId);
                        System.out.println("Задача удалена.");
                    } catch (TaskNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    menu();
                    scanner.nextLine();
                    break;
                case "4":
                    System.exit(0);
                default:
                    System.out.println("Ошибка выбора! Введите значение от 1 до 4");
                    menu();
            }
        }
        scanner.close();
    }

    public static String title() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название задачи: ");
        try {
            String argument = scanner.nextLine();
            check(argument);
            return argument;
        } catch (IncorrectArgumentException e) {
            System.out.println(e);
            return title();
        }
    }

    public static String description() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите описание задачи: ");
        try {
            String argument = scanner.nextLine();
            check(argument);
            return argument;
        } catch (IncorrectArgumentException e) {
            System.out.println(e);
            return description();
        }
    }

    public static Type type() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите тип задачи: \n 1 - Личная \n 2 - Рабочая");
        String typeValue = scanner.nextLine();
        try {
            check(typeValue);
        } catch (IncorrectArgumentException e) {
            System.out.println(e);
            return type();
        }
        switch (typeValue) {
            case "1":
                return Type.PERSONAL;
            case "2":
                return Type.WORK;
            default:
                System.out.println("Тип задачи выбран не верно.");
                return type();
        }
    }

    public static Task repeatability(String title, String description, Type type, LocalDateTime localDateTime) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите повторяемость задачи (1 - однократно, 2 - ежедневно, 3 - еженедельно, " +
                "4 - ежемесячно, 5 - ежегодно): ");
        String period = scanner.nextLine();
        try {
            check(period);
        } catch (IncorrectArgumentException e) {
            System.out.println(e);
            return repeatability(title, description, type, localDateTime);
        }
        switch (period) {
            case "1":
                System.out.println("Введите дату ГГГГ-ММ-ДД: ");
                try {
                    String date = scanner.nextLine();
                    checkDate(date);
                    LocalDate localDate = LocalDate.parse(date);
                    return new OneTimeTask(title, description, type, LocalDateTime.now(), localDate);
                } catch (DateTimeParseException e) {
                    System.out.println(e.getMessage());
                    return repeatability(title, description, type, localDateTime);
                }
            case "2":
                return new DailyTask(title, description, type, LocalDateTime.now());
            case "3":
                return new WeeklyTask(title, description, type, LocalDateTime.now());
            case "4":
                return new MonthlyTask(title, description, type, LocalDateTime.now());
            case "5":
                return new YearlyTask(title, description, type, LocalDateTime.now());
            default:
                System.out.println("Ошибка выбора! Введите значение от 1 до 5");
                return repeatability(title, description, type, localDateTime);
        }
    }

    public static void menu() {
        System.out.println("\nВыберите: " +
                "\n 1 - Добавить задачу " +
                "\n 2 - Получить задачи на день " +
                "\n 3 - Удалить задачу по id " +
                "\n 4 - Выйти ");
    }

    public static void check(String argument) {
        if (argument.isEmpty() || argument.isBlank()) {
            throw new IncorrectArgumentException(argument);
        }
    }

    public static void checkDate(String argument) {
        try {
            LocalDate.parse(argument);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("\nОшибка! Необходимо ввести дату в указанном формате.", argument, 0);
        }
    }
}