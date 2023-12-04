import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate d = LocalDate.now();
        Date da = Date.valueOf(LocalDate.now());
        System.out.println(da);
    }
}