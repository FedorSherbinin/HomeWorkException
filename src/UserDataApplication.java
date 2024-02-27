import Controler.UserDataController;
import View.ConsoleUserDataView;
import View.UserDataView;

public class UserDataApplication {

    public static void main(String[] args) {
        UserDataView view = new ConsoleUserDataView();
        UserDataController controller = new UserDataController(view);

        controller.processUserData();
    }
}