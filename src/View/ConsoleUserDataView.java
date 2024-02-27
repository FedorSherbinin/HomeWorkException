package View;

import java.util.Scanner;

public class ConsoleUserDataView implements UserDataView {
    private Scanner scanner;

    public ConsoleUserDataView() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String readUserDataInput() {
        System.out.println("Введите данные (Фамилия Имя Отчество дата_рождения номер_телефона пол):");
        return scanner.nextLine();
    }

    @Override
    public void showSuccessMessage() {
        System.out.println("Данные успешно записаны в файл.");
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        System.err.println("Ошибка: " + errorMessage);
    }
}