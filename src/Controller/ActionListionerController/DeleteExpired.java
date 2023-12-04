package Controller.ActionListionerController;

import Controller.EntityControllers.Batch;
import Controller.EntityControllers.Product;
import Controller.TableModelController.ProductTableModel;
import View.Panels.SalesPanel;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class DeleteExpired {
    public DeleteExpired(SalesPanel sp) {
        Date da = Date.valueOf(LocalDate.now());
        Batch b = new Batch();
        Product p = new Product();
        ArrayList<Batch> li = b.getBatches();
        for (Batch b1 : li) {
            if (b1.getExpiryDate().before(da)) {
                Integer quantity = b1.getQuantity();
                p = p.loadSingle(b1.getProductId());
                p.setQuantity(p.getQuantity() - quantity);
                p.updateQty(p.getId(), p.getQuantity());
                b1.deleteById(b1.getId());
                ProductTableModel df = (ProductTableModel) sp.getProductModel();
                df.setProductInfo();
            }
        }
    }
}
