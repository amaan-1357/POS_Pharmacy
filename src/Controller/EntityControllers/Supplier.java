package Controller.EntityControllers;

import Model.DAOs.SupplierDAO;

import java.util.ArrayList;
import java.util.Hashtable;

public class Supplier {
    private Integer id;
    private String name;
    private String email = null;
    private String phoneNo;
    public static SupplierDAO dao = new SupplierDAO();

    public Supplier() {
    }

    public Supplier(Integer id, String name, String email, String phoneNo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public Supplier(Integer id, String name, String phoneNo) {
        this.id = id;
        this.name = name;
        this.phoneNo = phoneNo;
    }

    public Supplier(String name, String phoneNo) {
        this.name = name;
        this.phoneNo = phoneNo;
    }

    public Supplier(String name, String email, String phoneNo) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public ArrayList<Supplier> getSuppliers(){
        ArrayList<Hashtable<String,String>> data = dao.load();
        ArrayList<Supplier> suppliers = new ArrayList<>();
        for(Hashtable<String,String> d : data){
            if(d.get("email") == null) {
                suppliers.add(new Supplier(Integer.parseInt(d.get("id")),
                        d.get("name"),
                        d.get("phone_no"))
                );
            }
            else{
                suppliers.add(new Supplier(Integer.parseInt(d.get("id")),
                        d.get("name"),
                        d.get("email"),
                        d.get("phone_no"))
                );
            }
        }

        return suppliers;
    }
    public ArrayList<Supplier> loadMultiple(Integer i){
        ArrayList<Hashtable<String,String>> data = dao.loadMultiple(i);
        ArrayList<Supplier> suppliers = new ArrayList<>();
        for(Hashtable<String,String> d : data){
            if(d.get("email") == null) {
                suppliers.add(new Supplier(Integer.parseInt(d.get("id")),
                        d.get("name"),
                        d.get("phone_no"))
                );
            }
            else{
                suppliers.add(new Supplier(Integer.parseInt(d.get("id")),
                        d.get("name"),
                        d.get("email"),
                        d.get("phone_no"))
                );
            }
        }

        return suppliers;
    }

    public boolean insert(){
        return dao.insert(new Hashtable<>(){{
            put("name", name);
            put("email", email);
            put("number", phoneNo);
        }});
    }
    public boolean insertWithoutEmail(){
        return dao.insertWithoutEmail(new Hashtable<>(){{
            put("name", name);
            put("number", phoneNo);
        }});
    }

    public Supplier loadSingle(Integer i){
        Hashtable<String,String> d = dao.loadSingle(i);
        if(d.get("email") == null) {
            return new Supplier(Integer.parseInt(d.get("id")),
                    d.get("name"),
                    d.get("phone_no"));
        }
        else{
            return new Supplier(Integer.parseInt(d.get("id")),
                    d.get("name"),
                    d.get("email"),
                    d.get("phone_no"));
        }
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

}
