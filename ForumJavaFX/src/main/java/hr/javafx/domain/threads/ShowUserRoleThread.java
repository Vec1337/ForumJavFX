package hr.javafx.domain.threads;

import hr.javafx.domain.ForumApplication;
import hr.javafx.domain.controllers.LoginScreenController;
import javafx.application.Platform;

public class ShowUserRoleThread implements Runnable{

    @Override
    public void run() {
        while(true) {
            Platform.runLater(() -> {
                ForumApplication.getMainStage().setTitle(
                        LoginScreenController.loggedUserRole.toString()
                );
            });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
