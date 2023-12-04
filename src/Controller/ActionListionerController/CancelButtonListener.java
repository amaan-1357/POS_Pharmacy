package Controller.ActionListionerController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The CancelButtonListener class represents an ActionListener for the cancel button in various input frames.
 */
public class CancelButtonListener implements ActionListener {
    private final JFrame frame;

    /**
     * Constructor for the CancelButtonListener.
     *
     * @param frame The JFrame associated with the cancel button.
     */
    public CancelButtonListener(JFrame frame) {
        this.frame = frame;
    }

    /**
     * Invoked when the cancel button is clicked. Disposes of the associated frame.
     *
     * @param actionEvent The action event triggered by the button click.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        frame.dispose();
    }
}
