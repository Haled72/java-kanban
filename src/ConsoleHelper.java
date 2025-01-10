import java.util.Scanner;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class ConsoleHelper {

    private static final Scanner scanner = new Scanner(System.in);


    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }


    public static int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readString(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Пожалуйста, введите целое число.");
            }
        }
    }


    public static LocalDate readDate(String prompt) {
        while (true) {
            try {
                String dateString = readString(prompt);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Некорректный формат даты. Введите дату в формате дд.мм.гггг.");
            }
        }
    }


    public static int selectFromOptions(String[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }

        int choice = readInt("Выберите опцию: ");
        while (choice < 1 || choice > options.length) {
            System.out.println("Некорректный выбор. Попробуйте снова.");
            choice = readInt("Выберите опцию: ");
        }
        return choice - 1;
    }
}