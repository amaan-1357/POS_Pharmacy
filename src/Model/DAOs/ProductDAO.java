package Model.DAOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class ProductDAO implements IDAO{
    
    public ArrayList<Hashtable<String, String>> load() {
        ArrayList<Hashtable<String,String>> data = new ArrayList<>();

        String query = "select * from products";
        try {
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("rs " + rs);
            while (rs.next()) {
                Hashtable<String,String> o = new Hashtable<>();
                o.put("id", rs.getString(1 ));
                o.put("name", rs.getString(2));
                o.put("price", rs.getString(3));
                o.put("category_id", rs.getString(4));
                o.put("supplier_id", rs.getString(5));
                o.put("quantity", rs.getString(6));
                o.put("limit", rs.getString(7));
                o.put("status", rs.getString(8));
                data.add(o);
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return data;
    }

    public Integer getPID(String name){
        String query = "select product_id from products where product_name = '"+name+"'";
        try {
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                return Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return 0;
    }
    
    
    public ArrayList<Hashtable<String, String>> loadMultiple(Integer id) {
        ArrayList<Hashtable<String,String>> data = new ArrayList<>();
        String query = "select * from products where product_id = "+id.toString();
        try{
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Hashtable<String,String> o = new Hashtable<>();
                o.put("id", rs.getString(1));
                o.put("name",rs.getString(2));
                o.put("price", rs.getString(3));
                o.put("category_id", rs.getString(4));
                o.put("supplier_id", rs.getString(5));
                o.put("quantity", rs.getString(6));
                o.put("limit", rs.getString(7));
                o.put("status", rs.getString(8));
                data.add(o);
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return data;
    }

    
    public boolean update(Hashtable<String, String> data) {
        return false;
    }

    public boolean updateQuantity(Integer PID, Integer quantity){
        String query = "UPDATE products SET quantity = " + quantity.toString() + " WHERE product_id = " + PID+";";
        try{
            Connection conn = IDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
        } catch(SQLException ex){
            return false;
        }
        return true;
    }
    
    public boolean insert(Hashtable<String, String> data) {
        return false;
    }

    
    public Hashtable<String, String> loadSingle(Integer id) {
        Hashtable<String,String> data = new Hashtable<>();
        String query = "select * from products where product_id = "+id.toString();
        try{
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                data.put("id", rs.getString(1));
                data.put("name",rs.getString(2));
                data.put("price", rs.getString(3));
                data.put("category_id", rs.getString(4));
                data.put("supplier_id", rs.getString(5));
                data.put("quantity", rs.getString(6));
                data.put("limit", rs.getString(7));
                data.put("status", rs.getString(8));
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return data;
    }

    
    public ArrayList<Hashtable<String, String>> loadSearched(String keyword) {
        ArrayList<Hashtable<String,String>> data = new ArrayList<>();
        String query = "SELECT * FROM products WHERE product_name LIKE '%" + keyword + "%';";
        try{
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Hashtable<String,String> o = new Hashtable<>();
                o.put("id", rs.getString(1));
                o.put("name",rs.getString(2));
                o.put("price", rs.getString(3));
                o.put("category_id", rs.getString(4));
                o.put("supplier_id", rs.getString(5));
                o.put("quantity", rs.getString(6));
                o.put("limit", rs.getString(7));
                o.put("status", rs.getString(8));
                data.add(o);
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return data;
    }
}
