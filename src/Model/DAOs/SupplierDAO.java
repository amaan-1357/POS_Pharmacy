package Model.DAOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class SupplierDAO implements IDAO{
    
    public ArrayList<Hashtable<String, String>> load() {
        ArrayList<Hashtable<String,String>> data = new ArrayList<>();

        String query = "SELECT supplier_id, supplier_name FROM suppliers;";
        try {
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("rs " + rs);
            while (rs.next()) {
                Hashtable<String,String> o = new Hashtable<>();
                o.put("id", rs.getString(1));
                o.put("name", rs.getString(2));
                data.add(o);
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return data;
    }

    
    public ArrayList<Hashtable<String, String>> loadMultiple(Integer id) {
        ArrayList<Hashtable<String,String>> data = new ArrayList<>();
        String query = "select * from suppliers where supplier_id = "+id.toString();
        try{
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Hashtable<String,String> o = new Hashtable<>();
                o.put("id", rs.getString(1));
                o.put("name", rs.getString(2));
                if(!(rs.getString(3).isEmpty()))
                    o.put("email", rs.getString(3));
                o.put("phone_no", rs.getString(4));
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
        String query = "insert into suppliers(supplier_name,email,phone_number) values ('" +
                data.get("name") + "','" + data.get("email") + "','" + data.get("number") + "');";
        try{
            Connection conn = IDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
        } catch(SQLException ex){
            return false;
        }
        return true;
    }

    public boolean insertWithoutEmail(Hashtable<String, String> data) {
        String query = "insert into suppliers(supplier_name,phone_number) values ('" +
                data.get("name") + "','" + data.get("number") + "');";
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
        String query = "select * from suppliers where supplier_id = "+id.toString();
        try{
            Connection conn = IDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                data.put("id", rs.getString(1));
                data.put("name", rs.getString(2));
                if(!(rs.getString(3).isEmpty()))
                    data.put("email", rs.getString(3));
                data.put("phone_no", rs.getString(4));
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
