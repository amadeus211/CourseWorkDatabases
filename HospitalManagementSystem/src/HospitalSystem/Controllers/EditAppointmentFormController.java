package HospitalSystem.Controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import HospitalSystem.Data.Data;
import HospitalSystem.Data.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EditAppointmentFormController implements Initializable{

    @FXML
    private TextField editApp_appointmentID;

    @FXML
    private Button editApp_updateBtn;

    @FXML
    private TextArea editApp_description;

    @FXML
    private TextField editApp_diagnosis;

    @FXML
    private TextField editApp_treatment;

    @FXML
    private ComboBox<String> editApp_doctor;

    @FXML
    private ComboBox<String> editApp_status;
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private AlertMessage alert = new AlertMessage();

    public void displayFields(){
        editApp_appointmentID.setText(Data.temp_appID);
        editApp_description.setText(Data.temp_appDescription);
        editApp_diagnosis.setText(Data.temp_appDiagnosis);
        editApp_treatment.setText(Data.temp_appTreatment);
        editApp_doctor.getSelectionModel().select(Data.temp_appDoctor);
        editApp_status.getSelectionModel().select(Data.temp_appStatus);
    }
    
    public void doctorList(){
        String sql = "SELECT * FROM doctor WHERE date_delete IS NULL";
        
        connect = Database.connectDB();
        
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            ObservableList listData = FXCollections.observableArrayList();
            while(result.next()){
                listData.add(result.getString("doctor_id"));
            }
            
            editApp_doctor.setItems(listData);
        }catch(Exception e){e.printStackTrace();}
    }


    public void statusList() {
        List<String> statusL = new ArrayList<>();

        for (String data : Data.status) {
            statusL.add(data);
        }

        ObservableList listData = FXCollections.observableList(statusL);
        editApp_status.setItems(listData);
    }

    public void updateApp(){
        if (editApp_appointmentID.getText().isEmpty() || editApp_description.getText().isEmpty()
                || editApp_status.getSelectionModel().getSelectedItem() == null
                || editApp_diagnosis.getText().isEmpty()
                || editApp_treatment.getText().isEmpty()
                || editApp_doctor.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Будь ласка, заповніть усі порожні поля");
        } else {
            String updateData = "UPDATE appointment SET description = ?, status = ?"
                    + ", diagnosis = ?, treatment = ?, doctor = ?, date_modify = ?, date_delete = ?"
                    + " WHERE appointment_id = '"
                    + editApp_appointmentID.getText() + "'";
            connect = Database.connectDB();
            try {
                if (alert.confirmationMessage("Ви впевнені, що хочете оновити призначення з ID: " + editApp_appointmentID.getText()
                        + "?")) {
                    prepare = connect.prepareStatement(updateData);
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(1, editApp_description.getText());
                    if(editApp_status.getSelectionModel().getSelectedItem().equals("Неактивний")){
                        prepare.setString(7, String.valueOf(sqlDate));
                    }else{
                        prepare.setString(7, null);
                    }
                    prepare.setString(2, editApp_status.getSelectionModel().getSelectedItem());
                    prepare.setString(3, editApp_diagnosis.getText());
                    prepare.setString(4, editApp_treatment.getText());
                    prepare.setString(5, editApp_doctor.getSelectionModel().getSelectedItem());
                    prepare.setString(6, String.valueOf(sqlDate));

                    prepare.executeUpdate();
                    alert.successMessage("Оновлення успішне!");
                } else {
                    alert.errorMessage("Скасовано.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        doctorList();
        statusList();
        
        displayFields();
    }
    
}
