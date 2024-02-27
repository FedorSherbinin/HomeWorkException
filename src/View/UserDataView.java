package View;

public interface UserDataView {
    String readUserDataInput();

    void showSuccessMessage();

    void showErrorMessage(String errorMessage);
}