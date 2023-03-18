import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class Task {
    private static int idGenerator = 1;
    private final int id;
    private String title;
    private String description;
    private final Type type;
    private final LocalDateTime dateTime;

    public Task(String title,
                String description,
                Type type,
                LocalDateTime dateTime) {
        this.id = idGenerator++;
        this.title = title;
        this.description = description;
        this.type = type;
        this.dateTime = dateTime;
    }

    public static int getIdGenerator() {
        return idGenerator;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Type getType() {
        return type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public abstract boolean appearsIn(LocalDate localDate);

    @Override
    public String toString() {
        return id + ". " + "Заголовок : " + title +
                "\n   Описание задачи : " + description +
                "\n   Тип : " + type +
                "\n   Дата и время создания : " + DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm").format(dateTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(title, task.title)
                && Objects.equals(description, task.description) && type == task.type
                && Objects.equals(dateTime, task.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, type, dateTime);
    }
}