/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snakegamefinalproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Pasukan
 */
public class Player {
    int id;
    String name;
    String password;
    int score;
    private final static String url = "jdbc:mysql://localhost:3306/snake";
    private final static String conname = "root";
    private final static String conpass = "wordpass";
    private static ObservableList<Player> list = FXCollections.observableArrayList();
    private static final String Starter = "create table players(\r\n"+"id int AUTO_INCREMENT primary key,\r\n"+"name varchar(20),\r\n"+"password varchar(20),\r\n"+"score int(7)\r\n" + ");";
    private static final String GetTable = "SELECT name, score FROM players ORDER BY score DESC";
    private static final String Login = "SELECT password FROM players\r\nWHERE name='";

    public Player(String name,int score){
        this.name = name;
        this.score = score;
    }
    public String getName(){
        return this.name;
    }
    public int getScore(){
        return this.score;
    }
    public static boolean SignUp(String name, String password){
        try (
            Connection con = DriverManager.getConnection(url,conname,conpass); 
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM players\r\nWHERE name ='"+name+"';")
        ){
            if (rs.next()){
                return false;
            }
            else{
                stmt.executeUpdate("INSERT INTO players(name, password, score)\r\nVALUES ('"+name+"','"+password+"',"+"0);");
                return true;
            }
        }
        catch (SQLException e) {
            printSQLException(e);
        }
        return false;
    }
    public static boolean Login(String name, String password){
        try (
            Connection con = DriverManager.getConnection(url,conname,conpass); 
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(Login+name+"';")
        ){ 
            while (rs.next()){
                String test = rs.getString("password");            
                if(password.equals(rs.getString("password"))){
                    return true;
                }
                else{
                    System.out.println(test);
                    System.out.println(password);
                    return false;
                }
            }
        }catch (SQLException e) {
            printSQLException(e);
        }
        return false;
    }
    public static ObservableList<Player> records(){
        try (
            Connection con = DriverManager.getConnection(url,conname,conpass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(GetTable)
        ){
            String nametemp;
            int scoretemp;
            while(rs.next()){
                nametemp = rs.getString("name");
                scoretemp = rs.getInt("score");
                list.addAll(new Player(nametemp,scoretemp));
            }
        }
        catch (SQLException e) {
            printSQLException(e);
        }
        return list;
    }
    public static void Initiate(){
        try (
            Connection con = DriverManager.getConnection(url,conname,conpass);
            Statement stmt = con.createStatement();
            ResultSet rs = null;
        ){
            stmt.executeUpdate(Starter);
        }
        catch (SQLException e) {
            printSQLException(e);
        }
    }


    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
