package Controller.EntityControllers;

import Model.DAOs.ProductDAO;

import java.util.ArrayList;
import java.util.Hashtable;

public class Product {
    private Integer id;
    private String name;
    private Double price;
    private Integer categoryID;
    private Integer supplierID;
    private Integer quantity;
    private Integer limit;
    private String status;
    public static ProductDAO dao = new ProductDAO();

    public Product() {
    }

    public Product(Integer id, String name, Double price, Integer categoryID, Integer supplierID, Integer quantity, Integer limit, String status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryID = categoryID;
        this.supplierID = supplierID;
        this.quantity = quantity;
        this.limit = limit;
        this.status = status;
    }


    public ArrayList<Product> getProducts(){
        ArrayList<Hashtable<String,String>> data = dao.load();
        ArrayList<Product> products = new ArrayList<>();
        for(Hashtable<String,String> d : data){
            products.add(new Product(Integer.parseInt(d.get("id")),
                    d.get("name"),
                    Double.valueOf(d.get("price")),
                    Integer.parseInt(d.get("category_id")),
                    Integer.parseInt(d.get("supplier_id")),
                    Integer.parseInt(d.get("quantity")),
                    Integer.parseInt(d.get("limit")),
                    d.get("status"))
            );
        }

        return products;
    }
    public ArrayList<Product> loadMultiple(Integer i){
        ArrayList<Hashtable<String,String>> data = dao.loadMultiple(i);
        ArrayList<Product> products = new ArrayList<>();
        for(Hashtable<String,String> d : data){
            products.add(new Product(Integer.parseInt(d.get("id")),
                    d.get("name"),
                    Double.valueOf(d.get("price")),
                    Integer.parseInt(d.get("category_id")),
                    Integer.parseInt(d.get("supplier_id")),
                    Integer.parseInt(d.get("quantity")),
                    Integer.parseInt(d.get("limit")),
                    d.get("status"))
            );
        }
        return products;
    }

    public ArrayList<Product> loadSearched(String keyword){
        ArrayList<Hashtable<String,String>> data = dao.loadSearched(keyword);
        ArrayList<Product> products = new ArrayList<>();
        for(Hashtable<String,String> d : data){
            products.add(new Product(Integer.parseInt(d.get("id")),
                    d.get("name"),
                    Double.valueOf(d.get("price")),
                    Integer.parseInt(d.get("category_id")),
                    Integer.parseInt(d.get("supplier_id")),
                    Integer.parseInt(d.get("quantity")),
                    Integer.parseInt(d.get("limit")),
                    d.get("status"))
            );
        }
        return products;
    }

    public Integer getPID(String name){
        return dao.getPID(name);
    }
    public boolean updateQty(Integer PID, Integer quantity){
        return dao.updateQuantity(PID, quantity);
    }

    public Product loadSingle(Integer i){
        Hashtable<String,String> d = dao.loadSingle(i);
       return new Product(Integer.parseInt(d.get("id")),
               d.get("name"),
               Double.valueOf(d.get("price")),
               Integer.parseInt(d.get("category_id")),
               Integer.parseInt(d.get("supplier_id")),
               Integer.parseInt(d.get("quantity")),
               Integer.parseInt(d.get("limit")), 
               d.get("status")
        );
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public Integer getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Integer supplierID) {
        this.supplierID = supplierID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getStatus() {
        return status;
    }
}
