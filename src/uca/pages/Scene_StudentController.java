/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uca.pages;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import uca.helpers.DBConnection;
import uca.helpers.Notes;
import uca.helpers.Student;




public class Scene_StudentController implements Initializable {
    
    public Student s1;
    private Parent root;
    private Scene scene;
    private Stage stage;

//    Buttons;
    @FXML
    private ImageView menu;
    @FXML
    private JFXButton menuback;
    @FXML
    private AnchorPane profile_pane;
    @FXML
    private AnchorPane home_page;
    @FXML
    private AnchorPane notes_page;
    @FXML
    private Pane menubar;
    @FXML
    private Label Title;
    @FXML
    private Label First_Name;
    @FXML
    private Label Last_Name;
    @FXML
    private Label Birth_Day;
    @FXML
    private Label Address;
    @FXML
    private Label CNE;
    
//    TableView;
     @FXML
     private TableView<Notes> NotesTable ;
     @FXML
     private TableColumn<Notes,String> module_name;
     @FXML
     private TableColumn<Notes,String> validation;
     @FXML
     private TableColumn<Notes,String> add_date;
     
    String query = null;
    Connection con =  null;
    Statement stmt = null;
    ResultSet  rs = null;
    
    @FXML
    ObservableList<Notes> NotesList = FXCollections.observableArrayList();
    
       @FXML
   private void Refresh() throws SQLException, ClassNotFoundException{
       NotesList.clear();
       query = "Select module_name,notes,validation,add_date from notes where Student_cne='"+s1.cne+"'";
       stmt = con.prepareStatement(query);
       rs = stmt.executeQuery(query);
       
       while(rs.next()){
           NotesList.add(new Notes (
                           rs.getString("module_name"),
                           rs.getFloat("notes"),
                           rs.getString("validation"),
                           rs.getString("add_date").substring(0, 11)));
           NotesTable.setItems(NotesList);
       }
   }
       private void loadData() throws SQLException, ClassNotFoundException {
        
        con =  DBConnection.getconnect();
        
        module_name.setCellValueFactory(new PropertyValueFactory<>("module_name"));
        validation.setCellValueFactory(new PropertyValueFactory<>("validation"));
        add_date.setCellValueFactory(new PropertyValueFactory<>("add_date"));
        
        
    }

    public void getStudent(String cne,String first_name,String last_name,String birth_date,String address){
        s1 = new Student(cne,first_name,last_name,birth_date,address);
    }
    public void log_out(ActionEvent event) throws IOException{
                root = FXMLLoader.load(getClass().getResource("Scene_Login.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                String css = this.getClass().getResource("style_login.css").toExternalForm(); 
                scene.getStylesheets().add(css);
                stage.centerOnScreen();
                stage.show();
    }
    public void show_notes(ActionEvent event) throws IOException, SQLException, ClassNotFoundException{
        //Student Infos
        menubar.setVisible(true);
        notes_page.setVisible(true);
        profile_pane.setVisible(false);
        home_page.setVisible(false);
        Title.setText("Welcome Mr "+s1.first_name+" "+s1.last_name);
        First_Name.setText(s1.first_name);
        Last_Name.setText(s1.last_name);
        Birth_Day.setText(s1.birth_date.substring(0,11));
        Address.setText(s1.address);
        CNE.setText(""+s1.cne);
        Refresh();
    }
    public void show_home(ActionEvent event) throws IOException{
        //Student Infos
        menubar.setVisible(false);
        notes_page.setVisible(false);
        profile_pane.setVisible(false);
        home_page.setVisible(true);
    }
    public void show_profil(ActionEvent event) throws IOException{
        //Student Infos
        menubar.setVisible(true);
        profile_pane.setVisible(true);
        notes_page.setVisible(false);
        home_page.setVisible(false);
        Title.setText("Welcome Mr "+s1.first_name+" "+s1.last_name);
        First_Name.setText(s1.first_name);
        Last_Name.setText(s1.last_name);
        Birth_Day.setText(s1.birth_date.substring(0,11));
        Address.setText(s1.address);
        CNE.setText(""+s1.cne);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            con =  DBConnection.getconnect();
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(Scene_LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Scene_StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //menu start
//        profile_pane.setVisible(false);
//        menubar.setVisible(false);
        if(home_page.isVisible()==false){
                menu.setOnMouseClicked(event -> {
                menubar.setVisible(true);
            });
                menuback.setOnMouseClicked(event -> {
                menubar.setVisible(false);
            });
        }        

    }    


}
