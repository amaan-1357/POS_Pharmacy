package Controller.ComponentListenerController;

import View.LoginPage;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class LoginPageComponent extends ComponentAdapter {
    private LoginPage frame;
    private String north = SpringLayout.NORTH;
    private String west = SpringLayout.WEST;
    public LoginPageComponent(LoginPage lp){
        frame = lp;
    }
    @Override
    public void componentResized(ComponentEvent e) {
        int height = frame.getHeight();
        int width = frame.getWidth();
        
        frame.getsLayout().putConstraint(north, frame.getU_name(), ((height / 2) - (height / 10)), north, frame.getRootPane());
        frame.getsLayout().putConstraint(west, frame.getU_name(), ((width / 2) - (width / 10)), west, frame.getRootPane());

        frame.getsLayout().putConstraint(north, frame.getUsername(), ((height / 2) - (height / 10)), north, frame.getRootPane());
        frame.getsLayout().putConstraint(west, frame.getPass(), ((width / 2) - (width / 10)), west, frame.getRootPane());
    }
}
