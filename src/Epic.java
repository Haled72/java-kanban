
import java.util.HashMap;
import java.util.Map;

public class Epic extends Task {
    private Map<Integer, Subtask> subtasks = new HashMap<>();


    public Epic(String name, String description) {
        super(name, description, TaskStatus.NEW);
    }


    public void addSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
    }


    public TaskStatus calculateStatus() {
        if (subtasks.isEmpty()) {
            return TaskStatus.NEW;
        }

        return subtasks.values().stream()
                .allMatch(subtask -> subtask.getStatus() == TaskStatus.DONE)
                ? TaskStatus.DONE
                : TaskStatus.IN_PROGRESS;
    }
}