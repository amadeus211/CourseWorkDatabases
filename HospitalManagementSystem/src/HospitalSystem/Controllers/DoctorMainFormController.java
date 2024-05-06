package HospitalSystem.Controllers;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import HospitalSystem.Data.AppointmentData;
import HospitalSystem.Data.Data;
import HospitalSystem.Data.Database;
import HospitalSystem.Data.RecordData;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class DoctorMainFormController implements Initializable {

    @FXML
    private Circle top_profile;

    @FXML
    private TableView<RecordData> record_tableView;

    @FXML
    private ComboBox<String> record_time_select;

    @FXML
    private ComboBox<String> patients_Id;

    @FXML
    private DatePicker record_date_picker;

    @FXML
    private TableColumn<RecordData, String> record_col_recordId;
    @FXML
    private TableColumn<RecordData, String> record_col_patientID;
    @FXML
    private TableColumn<RecordData, String> record_col_patientName;
    @FXML
    private TableColumn<RecordData, String> record_col_patientSurname;
    @FXML
    private TableColumn<RecordData, String> record_col_date;
    @FXML
    private TableColumn<RecordData, String> record_col_time;
    @FXML
    private TableColumn<RecordData, String> record_col_status;



    @FXML
    private Button logout_btn;

    @FXML
    private Label date_time;

    @FXML
    private Label current_form;

    @FXML
    private Label nav_adminID;

    @FXML
    private Label nav_username;

    @FXML
    private Label nav_surname;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button patients_btn;

    @FXML
    private Button appointments_btn;

    @FXML
    private Button profile_btn;

    @FXML
    private Button record_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private AnchorPane add_record_form;

    @FXML
    private Label dashboard_IP;

    @FXML
    private Label dashboard_TP;

    @FXML
    private Label dashboard_AP;

    @FXML
    private Label dashboard_tA;

    @FXML
    private AreaChart<?, ?> dashboad_chart_PD;

    @FXML
    private BarChart<?, ?> dashboad_chart_DD;

    @FXML
    private TableView<AppointmentData> dashboad_tableView;

    @FXML
    private TableColumn<AppointmentData, String> dashboad_col_appointmentID;


    @FXML
    private TableColumn<AppointmentData, String> dashboad_col_description;

    @FXML
    private TableColumn<AppointmentData, String> dashboad_col_appointmentDate;

    @FXML
    private TableColumn<AppointmentData, String> dashboad_col_status;

    @FXML
    private AnchorPane patients_form;

    @FXML
    private TextField patients_patientID;

    @FXML
    private TextField patients_patientName;

    @FXML
    private TextField patients_patientSurname;

    @FXML
    private TextField patients_mobileNumber;

    @FXML
    private TextArea patients_address;

    @FXML
    private Label patients_PA_patientID;

    @FXML
    private Label patients_PA_dateCreated;

    @FXML
    private Label patients_PI_patientName;

    @FXML
    private Label patients_PI_patientSurname;

    @FXML
    private Label patients_PI_gender;

    @FXML
    private Label patients_PI_mobileNumber;

    @FXML
    private Label patients_PI_address;

    @FXML
    private AnchorPane appointments_form;

    @FXML
    private TableView<AppointmentData> appointments_tableView;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_appointmentID;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_patientID;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_description;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_date;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_dateModify;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_dateDelete;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_status;

    @FXML
    private TextField appointment_appointmentID;

    @FXML
    private ComboBox<String> appointment_patient_id;

    @FXML
    private ComboBox<?> patients_record_id;

    @FXML
    private TextField appointment_description;

    @FXML
    private TextField appointment_diagnosis;

    @FXML
    private TextField appointment_treatment;

    @FXML
    private ComboBox<String> appointment_status;



    @FXML
    private ComboBox<String> patients_gender;

    @FXML
    private AnchorPane profile_form;

    @FXML
    private Circle profile_circleImage;

    @FXML
    private Button profile_importBtn;

    @FXML
    private Label profile_label_doctorID;

    @FXML
    private Label profile_label_name;

    @FXML
    private Label profile_label_surname;

    @FXML
    private Label profile_label_email;

    @FXML
    private Label profile_label_dateCreated;

    @FXML
    private TextField profile_doctorID;

    @FXML
    private TextField profile_name;

    @FXML
    private TextField profile_surname;

    @FXML
    private TextField profile_email;

    @FXML
    private ComboBox<String> profile_gender;

    @FXML
    private TextField profile_mobileNumber;

    @FXML
    private TextArea profile_address;

    @FXML
    private ComboBox<String> profile_specialized;

    @FXML
    private ComboBox<String> profile_status;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Image image;

    private final AlertMessage alert = new AlertMessage();

    public void createRecordBtn() {
        if (patients_Id.getItems().isEmpty()
                || record_time_select.getItems().isEmpty()
                || record_date_picker.getValue() == null) {
            alert.errorMessage("Щось пішло не так");
        } else {
            try {
                String patientId = (String) patients_Id.getSelectionModel().getSelectedItem();

                String insertData = "INSERT INTO record (patient_id, date, "
                        + "time, status, doctor_id) " +
                        "SELECT ?, ?, ?, 'Незавершено', patient.doctor " +
                        "FROM patient WHERE patient.patient_id = ?";

                connect = Database.connectDB();
                prepare = connect.prepareStatement(insertData);
                prepare.setString(1, patientId);
                prepare.setString(2, record_date_picker.getValue().toString());
                prepare.setString(3, (String) record_time_select.getSelectionModel().getSelectedItem());
                prepare.setString(4, patientId);

                prepare.executeUpdate();
                alert.successMessage("Додавання успішне!");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        patients_Id.getSelectionModel().clearSelection();
        record_time_select.getSelectionModel().clearSelection();
        record_date_picker.setValue(null);
        recordShowData();
    }


    public void patientsIdRecordListToAdd() {
        String sql = "SELECT patient_id FROM patient WHERE date_delete IS NULL AND status = 'Активний' AND doctor = ?";

        try {
            connect = Database.connectDB();
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, nav_adminID.getText());
            result = prepare.executeQuery();

            ObservableList<String> listData = FXCollections.observableArrayList();
            while(result.next()) {
                listData.add(result.getString("patient_id"));
            }

            patients_Id.setItems(listData);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void patientsTimeList() {
        List<String> recordTimesAll = new ArrayList<>(Arrays.asList(Data.records_time));

        String current_patient = "";

        String currentDate = "" + record_date_picker.getValue();

        if(patients_Id.getSelectionModel().getSelectedItem()==null && !(record_date_picker.getValue() ==null)){
            alert.errorMessage("Введіть ID пацієнта");
            record_date_picker.setValue(null);
            return;
        }
        else{
            current_patient = (String) patients_Id.getSelectionModel().getSelectedItem();
        }

        List<String> currentDoctorRecordTimes = new ArrayList<>();

        String current_doctor = "";
        String sql = "SELECT doctor FROM patient WHERE patient_id = ?";
        try {
            connect = Database.connectDB();
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, current_patient);
            result = prepare.executeQuery();
            if (result.next()) {
                current_doctor = result.getString("doctor");
            } else {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sqlRecord = "SELECT time FROM record WHERE doctor_id = ? AND date = ? AND status = 'Незавершено'";
        try {
            prepare = connect.prepareStatement(sqlRecord);
            prepare.setString(1, current_doctor);
            prepare.setString(2, currentDate);
            result = prepare.executeQuery();
            while (result.next()) {
                currentDoctorRecordTimes.add(result.getString("time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        recordTimesAll.removeAll(currentDoctorRecordTimes);

        ObservableList<String> observableRecordTimes = FXCollections.observableArrayList(recordTimesAll);
        record_time_select.setItems(observableRecordTimes);
    }


    public ObservableList<RecordData> recordGetData() {

        ObservableList<RecordData> listData = FXCollections.observableArrayList();

        String sql = "SELECT record.*, patient.name, patient.surname " +
                "FROM record " +
                "JOIN patient ON record.patient_id = patient.patient_id " +
                "WHERE record.doctor_id = ?";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, Data.doctor_id);
            result = prepare.executeQuery();

            while (result.next()) {
                RecordData recordData = new RecordData(result.getInt("id"),
                        result.getInt("patient_id"),
                        result.getDate("date"),
                        result.getString("time"),
                        result.getString("status"),
                        result.getString("doctor_id"),
                        result.getString("name"),
                        result.getString("surname"));
                listData.add(recordData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }


    public ObservableList<RecordData> recordListData;

    public void recordShowData() {
        recordListData = recordGetData();

        record_col_recordId.setCellValueFactory(new PropertyValueFactory<>("id"));
        record_col_patientID.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        record_col_patientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        record_col_patientSurname.setCellValueFactory(new PropertyValueFactory<>("patientSurname"));
        record_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        record_col_time.setCellValueFactory(new PropertyValueFactory<>("time"));
        record_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        record_tableView.setItems(recordListData);
    }

    public void completeRecord(){
        if (patients_record_id.getItems().isEmpty()) {
            alert.errorMessage("Будь ласка, введіть ID запису");
        } else {

            String updateQuery = "UPDATE record SET status = 'Завершено' WHERE id = ?";

            connect = Database.connectDB();

            try {
                if (alert.confirmationMessage("Ви впевнені, що хочете завершити запис з ID: "
                        + patients_record_id.getSelectionModel().getSelectedItem() + "?")) {
                    prepare = connect.prepareStatement(updateQuery);
                    prepare.setString(1, (String) patients_record_id.getSelectionModel().getSelectedItem());
                    prepare.executeUpdate();

                    alert.successMessage("Запис завершено!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        recordShowData();
    }

    public void patientsIdRecordList(){
        String sql = "SELECT * FROM record WHERE status = 'Незавершено' and doctor_id='"
                + Data.doctor_id + "'";

        connect = Database.connectDB();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            ObservableList listData = FXCollections.observableArrayList();
            while(result.next()){
                listData.add(result.getString("id"));
            }

            patients_record_id.setItems(listData);
        }catch(Exception e){e.printStackTrace();}
    }


    public void dashbboardDisplayTP() {
        String sql = "SELECT COUNT(patient_id) FROM patient WHERE doctor = '"
                + Data.doctor_id + "'";
        connect = Database.connectDB();
        int getTP = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                getTP = result.getInt("COUNT(patient_id)");
            }
            dashboard_TP.setText(String.valueOf(getTP));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashbboardDisplayIP() {
        String sql = "SELECT COUNT(patient_id) FROM patient WHERE status = 'Неактивний' AND doctor = '"
                + Data.doctor_id + "'";
        connect = Database.connectDB();
        int getIP = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                getIP = result.getInt("COUNT(patient_id)");
            }
            dashboard_IP.setText(String.valueOf(getIP));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashbboardDisplayAP() {
        String sql = "SELECT COUNT(patient_id) FROM patient WHERE status = 'Активний' AND doctor = '"
                + Data.doctor_id + "'";
        connect = Database.connectDB();
        int getAP = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                getAP = result.getInt("COUNT(patient_id)");
            }
            dashboard_AP.setText(String.valueOf(getAP));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashbboardDisplayTA() {
        String sql = "SELECT COUNT(appointment_id) FROM appointment WHERE doctor = '"
                + Data.doctor_id + "'";
        connect = Database.connectDB();
        int getTA = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                getTA = result.getInt("COUNT(appointment_id)");
            }
            dashboard_tA.setText(String.valueOf(getTA));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<AppointmentData> dashboardAppointmentTableView() {

        ObservableList<AppointmentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointment WHERE status = 'Активний' and doctor = '"
                + Data.doctor_id + "'";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            AppointmentData aData;
            while (result.next()) {
                aData = new AppointmentData(result.getInt("appointment_id"),
                        result.getString("description"),
                        result.getDate("date"), result.getString("status"));

                listData.add(aData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<AppointmentData> dashboardGetData;

    public void dashboardDisplayData() {
        dashboardGetData = dashboardAppointmentTableView();

        dashboad_col_appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        dashboad_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        dashboad_col_appointmentDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        dashboad_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        dashboad_tableView.setItems(dashboardGetData);
    }

    public void dashboardNOP() {

        dashboad_chart_PD.getData().clear();

        String sql = "SELECT date, COUNT(patient_id) FROM patient WHERE doctor = '"
                + Data.doctor_id + "' GROUP BY TIMESTAMP(date)";
        connect = Database.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series<>();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboad_chart_PD.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void dashboardNOA() {

        dashboad_chart_DD.getData().clear();

        String sql = "SELECT date, COUNT(appointment_id) FROM appointment WHERE doctor = '"
                + Data.doctor_id + "' GROUP BY TIMESTAMP(date)";
        connect = Database.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series<>();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboad_chart_DD.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void patientConfirmBtn() {

        if (patients_patientID.getText().isEmpty()
                || patients_patientName.getText().isEmpty()
                || patients_patientSurname.getText().isEmpty()
                || patients_gender.getSelectionModel().getSelectedItem() == null
                || patients_mobileNumber.getText().isEmpty()
                || patients_address.getText().isEmpty()) {
            alert.errorMessage("Будь ласка, заповніть усі порожні поля");
        } else {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            patients_PA_patientID.setText(patients_patientID.getText());
            patients_PA_dateCreated.setText(String.valueOf(sqlDate));

            patients_PI_patientName.setText(patients_patientName.getText());
            patients_PI_patientSurname.setText(patients_patientSurname.getText());
            patients_PI_gender.setText(patients_gender.getSelectionModel().getSelectedItem());
            patients_PI_mobileNumber.setText(patients_mobileNumber.getText());
            patients_PI_address.setText(patients_address.getText());
        }

    }

    public void patientAddBtn() {

        if (patients_PA_patientID.getText().isEmpty()
                || patients_PA_dateCreated.getText().isEmpty()
                || patients_PI_patientName.getText().isEmpty()
                || patients_PI_patientSurname.getText().isEmpty()
                || patients_PI_gender.getText().isEmpty()
                || patients_PI_mobileNumber.getText().isEmpty()
                || patients_PI_address.getText().isEmpty()) {
            alert.errorMessage("Щось пішло не так");
        } else {

            Database.connectDB();
            try {
                String checkPatientID = "SELECT * FROM patient WHERE patient_id = '"
                        + patients_PA_patientID.getText() + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(checkPatientID);
                if (result.next()) {
                    alert.errorMessage(patients_PA_patientID.getText() + " вже існує");
                } else {
                    String insertData = "INSERT INTO patient (patient_id, name, surname, mobile_number, "
                            + "address, doctor, date, gender, "
                            + "status) "
                            + "VALUES(?,?,?,?,?,?,?,?,?)";
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, patients_PA_patientID.getText());
                    prepare.setString(2, patients_PI_patientName.getText());
                    prepare.setString(3, patients_PI_patientSurname.getText());
                    prepare.setString(4, patients_PI_mobileNumber.getText());
                    prepare.setString(5, patients_PI_address.getText());
                    prepare.setString(6, nav_adminID.getText());
                    prepare.setString(7, "" + sqlDate);
                    prepare.setString(8, patients_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(9, "Підтвердження");

                    prepare.executeUpdate();

                    alert.successMessage("Додавання успішне!");
                    patientClearFields();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void patientRecordBtn() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/HospitalSystem/Layouts/RecordPageForm.fxml"));
            Stage stage = new Stage();

            stage.setTitle("Список пацієнтів");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void patientClearFields() {
        patients_patientID.clear();
        patients_patientName.clear();
        patients_gender.getSelectionModel().clearSelection();
        patients_mobileNumber.clear();
        patients_address.clear();

        patients_PA_patientID.setText("");
        patients_PA_dateCreated.setText("");

        patients_PI_patientName.setText("");
        patients_PI_gender.setText("");
        patients_PI_mobileNumber.setText("");
        patients_PI_address.setText("");

    }

    private void patientGenderList() {

        List<String> listG = new ArrayList<>();

        for (String data : Data.gender) {
            listG.add(data);
        }
        ObservableList listData = FXCollections.observableList(listG);

        patients_gender.setItems(listData);

    }

    public void appointmentInsertBtn() {


        if (appointment_appointmentID.getText().isEmpty()
                || appointment_patient_id.getSelectionModel().getSelectedItem() == null
                || appointment_description.getText().isEmpty()
                || appointment_status.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Будь ласка, заповніть усі порожні поля");
        } else {
            String checkAppointmentID = "SELECT * FROM appointment WHERE appointment_id = "
                    + appointment_appointmentID.getText();
            connect = Database.connectDB();
            try {
                statement = connect.createStatement();
                result = statement.executeQuery(checkAppointmentID);

                if (result.next()) {
                    alert.errorMessage(appointment_appointmentID.getText() + " вже існує");
                } else {
                    String getSpecialized = "";
                    String getDoctorData = "SELECT * FROM doctor WHERE doctor_id = '"
                            + Data.doctor_id + "'";

                    statement = connect.createStatement();
                    result = statement.executeQuery(getDoctorData);

                    if (result.next()) {
                        getSpecialized = result.getString("specialized");
                    }

                    String insertData = "INSERT INTO appointment (appointment_id, patient_id"
                            + ", description, diagnosis, treatment"
                            + ",date, status, doctor) "
                            + "VALUES(?,?,?,?,?,?,?,?)";
                    prepare = connect.prepareStatement(insertData);

                    prepare.setString(1, appointment_appointmentID.getText());
                    prepare.setString(2, appointment_patient_id.getSelectionModel().getSelectedItem());
                    prepare.setString(3, appointment_description.getText());
                    prepare.setString(4, appointment_diagnosis.getText());
                    prepare.setString(5, appointment_treatment.getText());

                    java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

                    prepare.setString(6, "" + sqlDate);
                    prepare.setString(7, (String) appointment_status.getSelectionModel().getSelectedItem());
                    prepare.setString(8, Data.doctor_id);

                    prepare.executeUpdate();

                    appointmentShowData();
                    appointmentAppointmentID();
                    appointmentClearBtn();
                    alert.successMessage("Додавання успішне!");

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void appointmentUpdateBtn() {

        if (appointment_appointmentID.getText().isEmpty()
                || appointment_description.getText().isEmpty()
                || appointment_status.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Будь ласка, заповніть усі порожні поля");
        } else {
            java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

            String updateData = "UPDATE appointment SET name = '"
                    +  "', description = '"
                    + appointment_description.getText() + "', status = '"
                    + appointment_status.getSelectionModel().getSelectedItem() + "', date_modify = '"
                    + sqlDate + "' WHERE appointment_id = '"
                    + appointment_appointmentID.getText() + "'";

            connect = Database.connectDB();

            try {
                if (alert.confirmationMessage("Ви впевнені, що хочете оновити призначення з ID: "
                        + appointment_appointmentID.getText() + "?")) {
                    prepare = connect.prepareStatement(updateData);
                    prepare.executeUpdate();

                    appointmentShowData();
                    appointmentAppointmentID();
                    appointmentClearBtn();
                    alert.successMessage("Оновлення успішне!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void appointmentDeleteBtn() {

        if (appointment_appointmentID.getText().isEmpty()) {
            alert.errorMessage("Будь ласка, спочатку виберіть запис");
        } else {

            String updateData = "UPDATE appointment SET date_delete = ?, status ='Неактивний' WHERE appointment_id = '"
                    + appointment_appointmentID.getText() + "'";

            connect = Database.connectDB();

            try {
                java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

                if (alert.confirmationMessage("Ви впевнені, що хочете видалити призначення з ID: "
                        + appointment_appointmentID.getText() + "?")) {
                    prepare = connect.prepareStatement(updateData);

                    prepare.setString(1, String.valueOf(sqlDate));
                    prepare.executeUpdate();

                    appointmentShowData();
                    appointmentAppointmentID();
                    appointmentClearBtn();

                    alert.successMessage("Видалення успішне!");
                } else {
                    alert.errorMessage("Скасовано.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void appointmentClearBtn() {
        appointment_appointmentID.clear();
        appointment_patient_id.getSelectionModel().clearSelection();
        appointment_description.clear();
        appointment_treatment.clear();
        appointment_diagnosis.clear();
        appointment_status.getSelectionModel().clearSelection();
        appointmentAppointmentID();
    }
;
    private Integer appointmentID;

    public void appointmentGetAppointmentID() {
        String sql = "SELECT MAX(appointment_id) FROM appointment";
        connect = Database.connectDB();

        int tempAppID = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                tempAppID = result.getInt("MAX(appointment_id)");
            }
            if (tempAppID == 0) {
                tempAppID += 1;
            } else {
                tempAppID += 1;
            }
            appointmentID = tempAppID;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void appointmentAppointmentID() {
        appointmentGetAppointmentID();

        appointment_appointmentID.setText("" + appointmentID);
        appointment_appointmentID.setDisable(true);

    }

    private Integer patientId;

    public void patientGetPatientID() {
        String sql = "SELECT MAX(patient_id) FROM patient";
        connect = Database.connectDB();

        int tempPatID = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                tempPatID = result.getInt("MAX(patient_id)");
            }
            if (tempPatID == 0) {
                tempPatID += 1;
            } else {
                tempPatID += 1;
            }
            patientId = tempPatID;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void patientPatientID() {
        patientGetPatientID();
        patients_patientID.setText("" + patientId);
    }

    public void appointmentStatusList() {
        List<String> listS = new ArrayList<>();

        for (String data : Data.status) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        appointment_status.setItems(listData);

    }

    public ObservableList<AppointmentData> appointmentGetData() {

        ObservableList<AppointmentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointment WHERE  doctor = '"
                + Data.doctor_id + "'";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            AppointmentData appData;

            while (result.next()) {

                appData = new AppointmentData(result.getInt("appointment_id"), result.getLong("patient_id"),
                        result.getString("description"),
                        result.getString("diagnosis"), result.getString("treatment"),
                        result.getDate("date"),
                        result.getDate("date_modify"), result.getDate("date_delete"),
                        result.getString("status"));
                listData.add(appData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<AppointmentData> appoinmentListData;

    public void appointmentShowData() {
        appoinmentListData = appointmentGetData();

        appointments_col_appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        appointments_col_patientID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        appointments_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointments_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        appointments_col_dateModify.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
        appointments_col_dateDelete.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
        appointments_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        appointments_tableView.setItems(appoinmentListData);
    }


    public void appointmentSelect() {

        AppointmentData appData = appointments_tableView.getSelectionModel().getSelectedItem();
        int num = appointments_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        appointment_appointmentID.setText("" + appData.getAppointmentID());
        appointment_patient_id.getSelectionModel().select(String.valueOf(appData.getPatientID()));
        appointment_description.setText(appData.getDescription());
        appointment_diagnosis.setText(appData.getDiagnosis());
        appointment_treatment.setText(appData.getTreatment());
        appointment_status.getSelectionModel().select(appData.getStatus());

    }

    public void profileUpdateBtn() {

        connect = Database.connectDB();

        if (profile_doctorID.getText().isEmpty()
                || profile_name.getText().isEmpty()
                || profile_surname.getText().isEmpty()
                || profile_email.getText().isEmpty()
                || profile_gender.getSelectionModel().getSelectedItem() == null
                || profile_mobileNumber.getText().isEmpty()
                || profile_address.getText().isEmpty()
                || profile_specialized.getSelectionModel().getSelectedItem() == null
                || profile_status.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Будь ласка, заповніть усі порожні поля");
        } else {
            if (Data.path == null || "".equals(Data.path)) {
                String updateData = "UPDATE doctor SET name = ?, surname = ?, email = ?"
                        + ", gender = ?, mobile_number = ?, address = ?, specialized = ?, status = ?, modify_date = ?"
                        + " WHERE doctor_id = '"
                        + Data.doctor_id + "'";
                try {
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, profile_name.getText());
                    prepare.setString(2, profile_surname.getText());
                    prepare.setString(3, profile_email.getText());
                    prepare.setString(4, profile_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(5, profile_mobileNumber.getText());
                    prepare.setString(6, profile_address.getText());
                    prepare.setString(7, profile_specialized.getSelectionModel().getSelectedItem());
                    prepare.setString(8, profile_status.getSelectionModel().getSelectedItem());
                    prepare.setString(9, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert.successMessage("Оновлення успішне!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                String updateData = "UPDATE doctor SET name = ?, surname = ?, email = ?"
                        + ", gender = ?, mobile_number = ?, address = ?, image = ?, specialized = ?, status = ?, modify_date = ?"
                        + " WHERE doctor_id = '"
                        + Data.doctor_id + "'";
                try {
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, profile_name.getText());
                    prepare.setString(2, profile_surname.getText());
                    prepare.setString(3, profile_email.getText());
                    prepare.setString(4, profile_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(5, profile_mobileNumber.getText());
                    prepare.setString(6, profile_address.getText());
                    String path = Data.path;
                    path = path.replace("\\", "\\\\");
                    Path transfer = Paths.get(path);

                    Path copy = Paths.get("C:\\Users\\User\\Desktop\\Coursework-Hospital\\HospitalManagementSystem\\src\\Doctor_Directory\\"
                            + Data.doctor_id + ".jpg");

                    try {
                        Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    prepare.setString(7, copy.toAbsolutePath().toString());
                    prepare.setString(8, profile_specialized.getSelectionModel().getSelectedItem());
                    prepare.setString(9, profile_status.getSelectionModel().getSelectedItem());
                    prepare.setString(10, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert.successMessage("Оновлення успішне!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void profileChangeProfile() {

        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new ExtensionFilter("Open Image", "*.png", "*.jpg", "*.jpeg"));

        File file = open.showOpenDialog(profile_importBtn.getScene().getWindow());

        if (file != null) {
            Data.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 128, 103, false, true);
            profile_circleImage.setFill(new ImagePattern(image));
        }

    }

    public void profileLabels() {
        String selectData = "SELECT * FROM doctor WHERE doctor_id = '"
                + Data.doctor_id + "'";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if (result.next()) {
                profile_label_doctorID.setText(result.getString("doctor_id"));
                profile_label_name.setText(result.getString("name"));
                profile_label_surname.setText(result.getString("surname"));
                profile_label_email.setText(result.getString("email"));
                profile_label_dateCreated.setText(result.getString("date"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void profileFields() {
        String selectData = "SELECT * FROM doctor WHERE doctor_id = '"
                + Data.doctor_id + "'";

        connect = Database.connectDB();
        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if (result.next()) {
                profile_doctorID.setText(result.getString("doctor_id"));
                profile_name.setText(result.getString("name"));
                profile_surname.setText(result.getString("surname"));
                profile_email.setText(result.getString("email"));
                profile_gender.getSelectionModel().select(result.getString("gender"));
                profile_mobileNumber.setText(result.getString("mobile_number"));
                profile_address.setText(result.getString("address"));
                profile_specialized.getSelectionModel().select(result.getString("specialized"));
                profile_status.getSelectionModel().select(result.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void profileDisplayImages() {

        String selectData = "SELECT * FROM doctor WHERE doctor_id = '"
                + Data.doctor_id + "'";
        String temp_path1 = "";
        String temp_path2 = "";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if (result.next()) {
                temp_path1 = "File:" + result.getString("image");
                temp_path2 = "File:" + result.getString("image");

                if (result.getString("image") != null) {
                    image = new Image(temp_path1, 1012, 22, false, true);
                    top_profile.setFill(new ImagePattern(image));

                    image = new Image(temp_path2, 128, 103, false, true);
                    profile_circleImage.setFill(new ImagePattern(image));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void profileGenderList() {

        List<String> listG = new ArrayList<>();

        for (String data : Data.gender) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        profile_gender.setItems(listData);

    }

    public void patientIdAppointmentList(){
        String sql = "SELECT * FROM patient WHERE doctor = '" + nav_adminID.getText() + "' and status = 'Активний'";

        connect = Database.connectDB();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            ObservableList listData = FXCollections.observableArrayList();
            while(result.next()){
                listData.add(result.getString("patient_id"));
            }
            appointment_patient_id.setItems(listData);
        }catch(Exception e){e.printStackTrace();}
    }

    private String[] specialization =  {"Косметолог", "Психіатр", "Дерматолог", "Гінеколог", "Кардіолог", "Терапевт", "Уролог", "Хірург",
            "Дерматолог", "Кардіолог", "Невролог", "Травматолог"};

    public void profileSpecializedList() {

        List<String> listS = new ArrayList<>();

        for (String data : specialization) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        profile_specialized.setItems(listData);
    }

    public void profileStatusList() {

        List<String> listS = new ArrayList<>();

        for (String data : Data.status) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        profile_status.setItems(listData);
    }

    public void displayAdminIDNumberName() {

        String name = Data.doctor_name;
//        String surname = Data.doctor_surname;
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
//        surname = surname.substring(0, 1).toUpperCase() + surname.substring(1);

        nav_username.setText(name);
        nav_adminID.setText(Data.doctor_id);
//        nav_surname.setText(surname);

    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);
            dashboardDisplayData();
            dashbboardDisplayIP();
            dashbboardDisplayTP();
            dashbboardDisplayAP();
            dashbboardDisplayTA();
            dashboardNOP();
            dashboardNOA();
            current_form.setText("Вікно статистики");
        } else if (event.getSource() == patients_btn) {
            dashboard_form.setVisible(false);
            patients_form.setVisible(true);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);
            add_record_form.setVisible(false);
            patientPatientID();
            current_form.setText("Вікно пацієнтів");
        } else if (event.getSource() == appointments_btn) {
            dashboard_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(true);
            profile_form.setVisible(false);
            add_record_form.setVisible(false);
            patientIdAppointmentList();

            current_form.setText("Вікно призначень");
        } else if (event.getSource() == profile_btn) {
            dashboard_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(true);
            add_record_form.setVisible(false);

            current_form.setText("Вікно налаштувань");
        }
        else if(event.getSource() == record_btn){
        dashboard_form.setVisible(false);
        patients_form.setVisible(false);
        appointments_form.setVisible(false);
        profile_form.setVisible(false);
        current_form.setText("Вікно записів");
        add_record_form.setVisible(true);
        recordShowData();
        patientsIdRecordList();
        patientsIdRecordListToAdd();
        }
    }

    public void logoutBtn() {

        try {
            if (alert.confirmationMessage("Ви впевнені, що хочете вийти?")) {
                Data.doctor_id = "";
                Data.doctor_name = "";
                Parent root = FXMLLoader.load(getClass().getResource("/HospitalSystem/Layouts/DoctorPage.fxml"));
                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.show();

                logout_btn.getScene().getWindow().hide();

                Data.doctor_id = "";
                Data.doctor_name = "";
                Data.temp_PatientID = 0;
                Data.temp_name = "";
                Data.temp_gender = "";
                Data.temp_number = Long.parseLong("0");
                Data.temp_address = "";
                Data.temp_status = "";
                Data.temp_date = "";
                Data.temp_path = "";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void runTime() {
        new Thread() {
            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(() -> {
                        date_time.setText(format.format(new Date()));
                    });
                }
            }
        }.start();
    }

    RefreshDatabaseDeleteDates refreshDatabaseDeleteDates = new RefreshDatabaseDeleteDates();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayAdminIDNumberName();
        runTime();

        refreshDatabaseDeleteDates.refresh("appointment");
        refreshDatabaseDeleteDates.refresh("patients");
        refreshDatabaseDeleteDates.refresh("doctor");

        dashbboardDisplayIP();
        dashbboardDisplayTP();
        dashbboardDisplayAP();
        dashbboardDisplayTA();
        dashboardDisplayData();
        dashboardNOP();
        dashboardNOA();

        appointmentShowData();
        appointmentStatusList();
        appointmentAppointmentID();

        patientGenderList();

        profileLabels();
        profileFields();
        profileGenderList();
        profileSpecializedList();
        profileStatusList();
        profileDisplayImages();
    }
}
