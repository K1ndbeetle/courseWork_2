import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthlyTask extends Task{

    public MonthlyTask(String title,
                       String description,
                       Type type,
                       LocalDateTime localDateTime) {
        super(title, description, type, localDateTime);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return getDateTime().getDayOfMonth() == localDate.getDayOfMonth();
    }

    @Override
    public String toString() {
        return super.toString() + " " + "(ежемесячная задача)";
    }
}