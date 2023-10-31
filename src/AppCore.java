import Model.AppModel;
import View.BoardFrame;
import View.WinnerFrame;

public class AppCore {


    public static void main(String[] args) {
        AppModel appModel = AppModel.getInstance();
        BoardFrame.getInstance().setVisible(true);
        WinnerFrame.getInstance().setVisible(false);
    }
}
