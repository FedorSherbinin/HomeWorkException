import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserDataApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Введите данные (Фамилия Имя Отчество дата_рождения номер_телефона пол):");
            String input = scanner.nextLine();

            String[] data = input.split(" ");
            if (data.length != 6) {
                throw new IllegalArgumentException("Неверное количество данных. Введите все необходимые данные.");
            }

            String lastName = capitalize(data[0]);
            String firstName = capitalize(data[1]);
            String middleName = capitalize(data[2]);
            String birthDate = data[3];
            long phoneNumber = Long.parseLong(data[4]);
            char gender = data[5].charAt(0);

            validateInput(lastName, firstName, middleName, birthDate, phoneNumber, gender);

            UserData userData = new UserData(lastName, firstName, middleName, birthDate, phoneNumber, gender);

            writeUserDataToFile(userData);
            System.out.println("Данные успешно записаны в файл.");

        } catch (IllegalArgumentException | IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void validateInput(String lastName, String firstName, String middleName, String birthDate, long phoneNumber, char gender) {
        // Проверка формата даты
        if (!birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            throw new IllegalArgumentException("Неверный формат даты. Используйте dd.mm.yyyy.");
        }

        // Проверка формата номера телефона
        if (phoneNumber <= 0) {
            throw new IllegalArgumentException("Неверный формат номера телефона. Введите положительное число.");
        }

        // Проверка формата пола
        if (gender != 'f' && gender != 'm') {
            throw new IllegalArgumentException("Неверный формат пола. Введите 'f' или 'm'.");
        }
    }

    private static void writeUserDataToFile(UserData userData) throws IOException {
        String fileName = userData.getLastName() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(userData.toString());
            writer.newLine();
        }
    }

    private static String capitalize(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}

class UserData {
    private String lastName;
    private String firstName;
    private String middleName;
    private String birthDate;
    private long phoneNumber;
    private char gender;

    public UserData(String lastName, String firstName, String middleName, String birthDate, long phoneNumber, char gender) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public char getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %d %c", lastName, firstName, middleName, birthDate, phoneNumber, gender);
    }
}
