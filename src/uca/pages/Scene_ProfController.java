/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uca.pages;


import java.sql.Statement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import uca.helpers.DBConnection;
import uca.helpers.Student;
import uca.helpers.Students;
import uca.helpers.Teacher;

/**
 *
 * @author lenovo
 */
public class Scene_ProfController implements Initializable {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); 
    // sql variables 
    @FXML 
    private TableView<Students> studentsTable;
 
    @FXML 
    private TableColumn<Students, String> cnecol;
    
    @FXML 
    private TableColumn<Students, String> nomcol;
    
    @FXML 
    private TableColumn<Students, String> prenomcol;
    
    @FXML 
    private TableColumn<Students, String> datecol;

              
    
   
    
    //DSFLSJF
    
    @FXML
    private Button btnNotes;

    @FXML
    private Button btnAddNotes;
    
    @FXML
    private Button btnSO;

    @FXML
    private Button btnsave;

    @FXML
    private Pane pnlNotes;

    @FXML 
    private TextField getcne;

    @FXML 
    private TextField getnote;

    @FXML
    private Pane pnlAddNotes;

    @FXML
    private Button so;
    
    @FXML 
    private ComboBox<String> comboClasses;
    
    public Teacher t1;    
    private Parent root;
    private Scene scene;
    private Stage stage;
    
    
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    Statement stmt =null;
    ResultSet resultSet = null ;
    Students student = null ;
    
    private static String filter = "'IDSD2'";
    
    
    @FXML 
    ObservableList<Students> StudentsList = FXCollections.observableArrayList();
    
    
    public void getTeacher(String tsubject){
        t1 = new Teacher(tsubject);
    }
    
    
 @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        try {
            // add items to comboBox in Notes pane;
            loadDate();
            addItemsCombo();
            
        } catch (SQLException ex) {
            Logger.getLogger(Scene_ProfController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }

     
     private void refreshTable(String filter) throws SQLException{
          StudentsList.clear();
            
            query = "SELECT * FROM students where class = " + filter;
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
          
            System.out.println("connection established");
            
            while (resultSet.next()){
                
                StudentsList.add(new  Students(
                        resultSet.getString("cne"),
                        resultSet.getString("LAST_NAME"),
                        resultSet.getString("FIRST_NAME"),
                        resultSet.getString("BIRTH_DATE").substring(0,11)
                ));
                studentsTable.setItems(StudentsList);
            }
                connection.close();
    }
     
    
    private void loadDate() throws SQLException{
        connection = DBConnection.getconnect();
         
        cnecol.setCellValueFactory(new PropertyValueFactory<>("cne") );
        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom") );
        prenomcol.setCellValueFactory(new PropertyValueFactory<>("prenom") );
        datecol.setCellValueFactory(new PropertyValueFactory<>("date") );
        refreshTable(filter);

    }

    private void addItemsCombo() throws SQLException{
        connection = DBConnection.getconnect();
        query = "SELECT name_class FROM all_classes";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        
      
           while (resultSet.next()){
               
           comboClasses.getItems().addAll(resultSet.getString(1));
            }
        
        comboClasses.setOnAction(event ->{
            filter = "'" + comboClasses.getSelectionModel().getSelectedItem() + "'";
            try {
                loadDate();
            } catch (SQLException ex) {
                Logger.getLogger(Scene_ProfController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }
    
    public void updateDB(ActionEvent event) throws SQLException {
        if(Float.parseFloat(getnote.getText())> 12){
        query = String.format("UPDATE Notes SET notes = %f, module_name='%s',validation='valide' WHERE Student_cne = '%s'",Float.parseFloat(getnote.getText()),t1.tsubject,getcne.getText());
        stmt=connection.prepareStatement(query);
        stmt.executeUpdate(query);
        }else if(Float.parseFloat(getnote.getText()) < 12){
        query = String.format("UPDATE Notes SET notes = %f, module_name='%s',validation='non valide' WHERE Student_cne = '%s'",Float.parseFloat(getnote.getText()),t1.tsubject,getcne.getText());
        stmt=connection.prepareStatement(query);
        stmt.executeUpdate(query);
        }
        getnote.clear();
        getcne.clear();
        System.out.println("row Updated");
    }
    @FXML
  public void handleClicks(ActionEvent actionEvent) throws SQLException {
        if (actionEvent.getSource() == btnNotes) {
//          pnlNotes.setStyle("-fx-background-color : #1620A1");
            pnlNotes.toFront();  
        }
        if (actionEvent.getSource() == btnAddNotes) {
            pnlAddNotes.toFront();
        }
        if (actionEvent.getSource() == btnsave ) {
            
            
//            try{
//                
//                
//                connection = DbConnect.getConnect();
//                String cne = getcne.getText();
//                String note = getnote.getText();  
//                System.out.println(note + " " + cne);
//                query = "update students set note = 1 where cne = 'G130726002'";
//                Statement stmt = connection.createStatement();
//                ResultSet rs = stmt.executeQuery(query);
////        
////                preparedStatement.executeQuery();
//                connection.close();
//
//                
//                
//            }catch(Exception e){
//                e.printStackTrace();
//            }
            
        }
//        if(actionEvent.getSource()==btnOrders)
//        {
//            pnlOrders.setStyle("-fx-background-color : #464F67");
//            pnlOrders.toFront();
//        }
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
    
}
