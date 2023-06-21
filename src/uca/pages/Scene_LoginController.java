/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uca.pages;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import uca.helpers.DBConnection;



public class Scene_LoginController implements Initializable {
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Label wrong_infos;
    
    private Parent root;
    private Scene scene;
    private Stage stage;
    
    
    String query = null;
    String query1 = null;
    String query2 = null;
    Connection con =  null;
    Statement stmt = null;
    Statement stmt1 = null;
    Statement stmt2 = null;
    ResultSet  rs = null;
    ResultSet rs1 = null;
    ResultSet rs2 = null;
    
    @FXML
    private void connect(ActionEvent event) throws SQLException, IOException{
       query ="SELECT cne,last_name,first_name,birth_date,adress FROM students where email= UPPER('"+email.getText()+"') or email= LOWER('"+email.getText()+"')  and password='"+password.getText()+"'";
       query1 ="SELECT * FROM admin where email= UPPER('"+email.getText()+"')or email= LOWER('"+email.getText()+"') and password='"+password.getText()+"'";
       query2 ="SELECT * FROM TEACHERS where email= UPPER('"+email.getText()+"')or email= LOWER('"+email.getText()+"') and password='"+password.getText()+"'";
       stmt = con.prepareStatement(query);
       stmt1 = con.prepareStatement(query1);
       stmt2 = con.prepareStatement(query2);
       rs = stmt.executeQuery(query);
       rs1 = stmt1.executeQuery(query1);
       rs2 = stmt2.executeQuery(query2);

       if(rs.next()){
           
           //Student
                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Scene_Student.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                root = fxmlloader.load();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();
                String css = this.getClass().getResource("style_login.css").toExternalForm(); 
            Scene_StudentController loader = fxmlloader.getController();
            loader.getStudent(rs.getString("cne"),rs.getString("last_name"),rs.getString("first_name"),rs.getString("birth_date"),rs.getString("adress"));
                scene.getStylesheets().add(css);
                stage.show();
       }else if(rs1.next()){
                //Admin
            int id_Admin = rs1.getInt("id");
                wrong_infos.setText("test1");
                email.setText("");
                password.setText("");
       }else if(rs2.next()){
           
                //Prof
            int id_prof = rs2.getInt("tid");
                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Scene_Prof.fxml"));
                root = fxmlloader.load();
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                String css = this.getClass().getResource("torino.css").toExternalForm();
            Scene_ProfController loader = fxmlloader.getController();
            loader.getTeacher(rs2.getString(4));
                scene.getStylesheets().add(css);
                stage.centerOnScreen();
                stage.show();
       }else{
           wrong_infos.setText("Password or Email Invalid pls try again ");
                 email.setText("");
                password.setText("");
       }
       
       
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            con =  DBConnection.getconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Scene_LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

}
