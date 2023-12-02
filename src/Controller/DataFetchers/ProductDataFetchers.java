package Controller.DataFetchers;

import Controller.EntityControllers.Product;
import Controller.EntityControllers.ProductCategory;
import Controller.EntityControllers.Supplier;

import java.util.ArrayList;
import java.util.Hashtable;

public class ProductDataFetchers {
    private Product product = new Product();
    private ProductCategory productCategory = new ProductCategory();
    private Supplier supplier = new Supplier();

    public ProductDataFetchers(){}

    public ArrayList<Hashtable<String,String>> getProductInfo(){
        ArrayList<Product> products = product.getProducts();
        ArrayList<Hashtable<String,String>> data = new ArrayList<>();
        for(Product p: products){
            Hashtable<String,String> o = new Hashtable<>();
            o.put("id", p.getId().toString());
            o.put("name", p.getName().toString());
            o.put("price",p.getPrice().toString());
            productCategory = productCategory.loadSingle(p.getCategoryID());
            o.put("category",productCategory.getName());
            supplier = supplier.loadSingle(p.getSupplierID());
            o.put("supplier",supplier.getName());
            o.put("quantity",p.getQuantity().toString());
            o.put("limit",p.getLimit().toString());
            o.put("status",p.getStatus());
            data.add(o);
        }
        return data;
    }
    public ArrayList<Hashtable<String,String>> getSearchedProducts(String keyword){
        ArrayList<Product> products = product.loadSearched(keyword);
        ArrayList<Hashtable<String,String>> data = new ArrayList<>();
        for(Product p: products){
            Hashtable<String,String> o = new Hashtable<>();
            o.put("id", p.getId().toString());
            o.put("name", p.getName().toString());
            o.put("price",p.getPrice().toString());
            productCategory = productCategory.loadSingle(p.getCategoryID());
            supplier = supplier.loadSingle(p.getSupplierID());
            o.put("quantity",p.getQuantity().toString());
            o.put("limit",p.getLimit().toString());
            o.put("status",p.getStatus());
            data.add(o);
        }
        return data;
    }
}
