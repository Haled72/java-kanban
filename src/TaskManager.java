import java.util.*;
import java.util.stream.Collectors;

class TasksManager {
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private int idCounter = 0;

    private int generateId() {

        return idCounter++;
    }


    public int addNewTask(Task task) {
        task.setId(generateId());
        tasks.put(task.getId(), task);
        return task.getId();
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void deleteTask(int id) {
        tasks.remove(id);

        subtasks.values().removeIf(subtask -> subtask.getEpicId() == id);
    }
public int addNewEpic(Epic epic){
        epic.setId(generateId());
        epics.put(epic.getId(),epic);
        return epic.getId();
}
public Epic getEpic(int id){
        return epics.get(id);
}
   public void updateEpic(Epic epic){
        epics.put(epic.getId(),epic);
   }
public void deleteEpic(int id){
        epics.remove(id);
        subtasks.values().removeIf(subtask -> subtask.getEpicId() == id);
}

    public List<Subtask> getEpicSubtasks(int epicId) {
        return subtasks.values().stream()
                .filter(subtask -> subtask.getEpicId() == epicId)
                .collect(Collectors.toList());
    }


    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }
   public List<Epic> getAllEpics(){
        return new ArrayList<>(epics.values());
   }
    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Создать новую задачу");
            System.out.println("2. Создать новый эпик");
            System.out.println("3. Вывести все задачи");
            System.out.println("4. Вывести все эпики");
            System.out.println("5. Удалить задачу");
            System.out.println("6. Удалить эпик");
            System.out.println("0. Выход");

            int choice = ConsoleHelper.readInt("Ваш выбор: ");

            switch (choice) {
                case 1:

                    String taskName = ConsoleHelper.readString("Введите название задачи: ");
                    String taskDescription = ConsoleHelper.readString("Введите описание: ");
                    TaskStatus taskStatus = TaskStatus.NEW; // Или выбрать из списка
                    addNewTask(new Task(taskName, taskDescription, taskStatus));
                    break;
                case 2:
                    String epicName = ConsoleHelper.readString("Введите название эпика: ");
                    String epicDescription = ConsoleHelper.readString("Введите описание: ");
                    addNewEpic(new Epic(epicName, epicDescription));
                    break;
                case 3:
                    List<Task> allTasks = getAllTasks();
                    System.out.println("Все задачи:");
                    for (Task task : allTasks) {
                        System.out.println(task);
                    }
                    break;
                case 4:
                    List<Epic> allEpics = getAllEpics();
                    System.out.println("Все эпики:");
                    for (Epic epic : allEpics) {
                        System.out.println(epic);
                        System.out.println("Подзадачи эпика:");
                        for (Subtask subtask : getEpicSubtasks(epic.getId())) {
                            System.out.println(subtask);
                        }
                    }
                    break;
                case 5:
                    int taskIdToDelete = ConsoleHelper.readInt("Введите ID задачи для удаления: ");
                    deleteTask(taskIdToDelete);
                    break;
                case 6:
                    int epicIdToDelete = ConsoleHelper.readInt("Введите ID эпика для удаления: ");
                    deleteEpic(epicIdToDelete);
                    break;
                case 0:
                    System.out.println("Выход из программы.");
                    return;
                default:
                    System.out.println("Некорректный выбор.");
            }
        }
    }
}