package Controller.TableModelController;

import Controller.DataFetchers.ProductDataFetchers;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Hashtable;

public class ProductTableModel extends DefaultTableModel {
    private ProductDataFetchers pdf = new ProductDataFetchers();
    public ProductTableModel(){
        this.addColumn("PID");
        this.addColumn("Name");
        this.addColumn("Price");
        this.addColumn("Category");
        this.addColumn("Supplier");
        this.addColumn("Qty");
        this.addColumn("Status");
        setProductInfo();
    }
    public void setProductInfo(){
        ArrayList<Hashtable<String,String >> productInfo = pdf.getProductInfo();
        setRowCount(0);
        for(Hashtable<String,String> hs: productInfo){
            Object[] a= {hs.get("id"),
                    hs.get("name"),
                    hs.get("price"),
                    hs.get("category"),
                    hs.get("supplier"),
                    hs.get("quantity"),
                    hs.get("status")
            };
            this.addRow(a);
        }
    }
    public void setSearchedInfo(String keyword){
        ArrayList<Hashtable<String,String >> productInfo = pdf.getSearchedProducts(keyword);
        setRowCount(0);
        for(Hashtable<String,String> hs: productInfo){
            Object[] a= {hs.get("id"),
                    hs.get("name"),
                    hs.get("price"),
                    hs.get("category"),
                    hs.get("supplier"),
                    hs.get("quantity"),
                    hs.get("status")
            };
            this.addRow(a);
        }
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
