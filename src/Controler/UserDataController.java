package Controler;

import Model.UserDataModel;
import View.UserDataView;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UserDataController {
    private UserDataView view;

    public UserDataController(UserDataView view) {
        this.view = view;
    }

    public void processUserData() {
        try {
            String input = view.readUserDataInput();

            String[] data = input.split(" ");
            validateInput(data);

            String lastName = capitalize(data[0]);
            String firstName = capitalize(data[1]);
            String middleName = capitalize(data[2]);
            String birthDate = data[3];
            long phoneNumber = Long.parseLong(data[4]);
            char gender = data[5].charAt(0);

            UserDataModel userData = new UserDataModel(lastName, firstName, middleName, birthDate, phoneNumber, gender);

            writeUserDataToFile(userData);
            view.showSuccessMessage();

        } catch (IllegalArgumentException | IOException e) {
            view.showErrorMessage(e.getMessage());
        }
    }

    private static void validateInput(String[] data) {
        if (data.length != 6) {
            throw new IllegalArgumentException("Неверное количество данных. Введите все необходимые данные.");
        }

        String birthDate = data[3];
        long phoneNumber = Long.parseLong(data[4]);
        char gender = data[5].charAt(0);

        // Проверка отдельных полей
        validateBirthDate(birthDate);
        validatePhoneNumber(phoneNumber);
        validateGender(gender);
    }

    private static void validateBirthDate(String birthDate) {
        if (!birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            throw new IllegalArgumentException("Неверный формат даты. Используйте dd.mm.yyyy.");
        }
    }

    private static void validatePhoneNumber(long phoneNumber) {
        if (phoneNumber <= 0) {
            throw new IllegalArgumentException("Неверный формат номера телефона. Введите положительное число.");
        }
    }

    private static void validateGender(char gender) {
        if (gender != 'f' && gender != 'm') {
            throw new IllegalArgumentException("Неверный формат пола. Введите 'f' или 'm'.");
        }
    }

    private static void writeUserDataToFile(UserDataModel userData) throws IOException {
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
