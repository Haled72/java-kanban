import java.time.LocalDate;

public static void main(String[] args) {
    TasksManager manager = new TasksManager();
    manager.start();
    String name = ConsoleHelper.readString("Введите ваше имя: ");
    int age = ConsoleHelper.readInt("Введите ваш возраст: ");
    LocalDate birthdate = ConsoleHelper.readDate("Введите дату рождения: ");

    Epic epic1 = new Epic("Разработать новый модуль", "Добавить функциональность для обработки платежей");
    Subtask subtask1 = new Subtask("Реализовать API для платежей", "Создать REST API для приема платежей", TaskStatus.IN_PROGRESS, epic1.getId());
    Subtask subtask2 = new Subtask("Интегрировать с платежной системой", "Настроить интеграцию с Яндекс.Кассой", TaskStatus.NEW, epic1.getId());
    epic1.addSubtask(subtask1);
    epic1.addSubtask(subtask2);
    manager.addNewEpic(epic1);

    Task task1 = new Task("Исправить баг в отчетах", "Исправить ошибку при формировании отчета о продажах", TaskStatus.NEW);
    manager.addNewTask(task1);


    System.out.println("Все задачи:");
    for (Task task : manager.getAllTasks()) {
        System.out.println(task);
    }

    System.out.println("Все эпики:");
    for (Epic epic : manager.getAllEpics()) {
        System.out.println(epic);
        System.out.println("Подзадачи эпика:");
        for (Subtask subtask : manager.getEpicSubtasks(epic.getId())) {
            System.out.println(subtask);
        }
    }
}
