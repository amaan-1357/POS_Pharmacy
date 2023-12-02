package Model.DAOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class ProductCategoryDAO implements IDAO{
    
    public ArrayList<Hashtable<String, String>> load() {
        ArrayList<Hashtable<String, String>> data = new ArrayList<>();

        String query = "select * from product_categories";
        try {
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("rs " + rs);
            while (rs.next()) {
                Hashtable<String, String> o = new Hashtable<>();
                o.put("id", rs.getString(1));
                o.put("name", rs.getString(2));
                data.add(o);
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return data;
    }
    public ArrayList<String> loadNames() {
        ArrayList<String> data = new ArrayList<>();

        String query = "select category_name from product_categories";
        try {
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("rs " + rs);
            while (rs.next()) {
                data.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return data;
    }
    
    public ArrayList<Hashtable<String, String>> loadMultiple(Integer id) {
        ArrayList<Hashtable<String,String>> data = new ArrayList<>();
        String query = "select * from product_categories where category_id = "+id.toString();
        try{
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Hashtable<String,String> o = new Hashtable<>();
                o.put("id", rs.getString(1));
                o.put("name",rs.getString(2));
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

    
    public boolean insert(Hashtable<String, String> data) {
        return false;
    }

    public boolean insert(String name) {
        String query = "insert into product_categories(category_name) values ('" + name + "')";
        try{
            Connection conn = IDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
        } catch(SQLException ex){
            return false;
        }
        return true;
    }

    
    public Hashtable<String, String> loadSingle(Integer id) {
        Hashtable<String,String> data = new Hashtable<>();
        String query = "select * from product_categories where category_id = "+id.toString();
        try{
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Hashtable<String,String> o = new Hashtable<>();
                data.put("id", rs.getString(1));
                data.put("name",rs.getString(2));
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return data;
    }

    
    public ArrayList<Hashtable<String, String>> loadSearched(String keyword) {
        return null;
    }
}
