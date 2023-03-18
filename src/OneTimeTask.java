import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class OneTimeTask extends Task{
    private LocalDate dateCompletion;

    public OneTimeTask(String title,
                       String description,
                       Type type,
                       LocalDateTime localDateTime,
                       LocalDate dateCompletion) {
        super(title, description, type, localDateTime);
        this.dateCompletion = dateCompletion;
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return dateCompletion.equals(localDate);
    }

    @Override
    public String toString() {
        return super.toString() + " " + "(однократная задача)" +
                "\n   Задачу необходимо выполнить - "
                + DateTimeFormatter.ofPattern("dd.MM.yyyy").format(dateCompletion);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OneTimeTask that = (OneTimeTask) o;
        return Objects.equals(dateCompletion, that.dateCompletion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dateCompletion);
    }
}