package Controller.EntityControllers;

import Model.DAOs.ProductCategoryDAO;

import java.util.ArrayList;
import java.util.Hashtable;

public class ProductCategory {
    private Integer id;
    private String name;
    public static ProductCategoryDAO dao = new ProductCategoryDAO();
    public ProductCategory() {
    }

    public ProductCategory(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public ArrayList<ProductCategory> getProductCategories(){
        ArrayList<Hashtable<String,String>> data = dao.load();
        ArrayList<ProductCategory> productCategories = new ArrayList<>();
        for(Hashtable<String,String> d : data){
            productCategories.add(new ProductCategory(Integer.parseInt(d.get("id")),
                    d.get("name"))
            );
        }

        return productCategories;
    }
    public ArrayList<String> getNames(){
        return dao.loadNames();
    }
    public ArrayList<ProductCategory> loadMultiple(Integer i){
        ArrayList<Hashtable<String,String>> data = dao.loadMultiple(i);
        ArrayList<ProductCategory> productCategories = new ArrayList<>();
        for(Hashtable<String,String> d : data){
            productCategories.add(new ProductCategory(Integer.parseInt(d.get("id")),
                    d.get("name"))
            );
        }

        return productCategories;
    }
    public ProductCategory loadSingle(Integer i){
        Hashtable<String,String> data = dao.loadSingle(i);
        return new ProductCategory(Integer.parseInt(data.get("id")),
                data.get("name"));
    }
    public boolean insert(String name){
        return dao.insert(name);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
