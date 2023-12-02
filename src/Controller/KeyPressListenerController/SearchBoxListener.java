package Controller.KeyPressListenerController;

import Controller.TableModelController.ProductTableModel;
import View.Panels.SalesPanel;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SearchBoxListener implements DocumentListener {
    private SalesPanel frame;
    private ProductTableModel model;
    public SearchBoxListener(SalesPanel sp){
        frame = sp;
        model = (ProductTableModel) frame.getProductModel();
    }
    @Override
    public void insertUpdate(DocumentEvent e) {
        model.setSearchedInfo(frame.getSearchBox().getText());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        if(frame.getSearchBox().getText().isEmpty())
            model.setProductInfo();
        else
            model.setSearchedInfo(frame.getSearchBox().getText());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        if(frame.getSearchBox().getText().isEmpty())
            model.setProductInfo();
        else
            model.setSearchedInfo(frame.getSearchBox().getText());
    }
}
