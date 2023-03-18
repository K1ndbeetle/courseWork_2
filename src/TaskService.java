import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
public class TaskService {
    private Map<Integer, Task> taskMap = new HashMap<>();
    public Map<Integer, Task> getTaskMap() {
        return taskMap;
    }

    public void add(Task task) {
        taskMap.put(task.getId(), task);
    }

    public List<Task> tasksDate(LocalDate localDate) {
        List<Task> mapValues = new ArrayList<>((getTaskMap().values()));
        return mapValues.stream()
                .filter(task -> task.appearsIn(localDate))
                .collect(Collectors.toList());
    }

    public void printTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("Задачи не найдены!");
        }
        tasks.forEach(System.out::println);
    }

    public Task removeTask(int id) {
        for (Map.Entry<Integer, Task> task : getTaskMap().entrySet()) {
            if (task.getKey() == id) {
                return taskMap.remove(id);
            }
        }
        throw new TaskNotFoundException("Задача не найдена!");
    }
}