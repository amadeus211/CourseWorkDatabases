package HospitalSystem.Controllers;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import HospitalSystem.Data.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AdminMainFormController implements Initializable {
    @FXML
    private Label patients_PA_dateCreated;

    @FXML
    private Label patients_PI_doctorID;

    @FXML
    private Label patients_PA_patientID;

    @FXML
    private Label patients_PI_address;

    @FXML
    private Label patients_PI_gender;

    @FXML
    private Label patients_PI_mobileNumber;

    @FXML
    private Label patients_PI_patientName;

    @FXML
    private Circle top_profile;

    @FXML
    private Label top_username;

    @FXML
    private Label date_time;

    @FXML
    private Label current_form;

    @FXML
    private Label nav_adminID;

    @FXML
    private Label nav_username;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button record_create_btn;

    @FXML
    private Button add_patient;

    @FXML
    private Button doctors_btn;

    @FXML
    private Button record_btn;

    @FXML
    private Button patients_btn;

    @FXML
    private Button appointments_btn;

    @FXML
    private Button profile_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private AnchorPane patients_form;

    @FXML
    private ComboBox<?> patients_gender;

    @FXML
    private ComboBox<?> patients_Id;

    @FXML
    private ComboBox<?> patients_doctor;

    @FXML
    private TextField patients_mobileNumber;

    @FXML
    private TextField patients_doctorID;

    @FXML
    private TextField patients_patientID;


    @FXML
    private Button logout_btn;

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
    private TableView<DoctorData> dashboad_tableView;

    @FXML
    private TableColumn<AppointmentData, String> dashboad_col_name;

    @FXML
    private TableColumn<AppointmentData, String> dashboad_col_status;

    @FXML
    private TableView<RecordData> record_tableView;

    @FXML
    private TableColumn<RecordData, String> record_col_recordId;
    @FXML
    private TableColumn<RecordData, String> record_col_patientID;
    @FXML
    private TableColumn<RecordData, String> record_col_patientName;
    @FXML
    private TableColumn<RecordData, String> record_col_date;
    @FXML
    private TableColumn<RecordData, String> record_col_time;
    @FXML
    private TableColumn<RecordData, String> record_col_status;
    @FXML
    private TableColumn<RecordData, String> record_col_dcotor;


    @FXML
    private TextField patients_patientName;

    @FXML
    private TextField record_time;


    @FXML
    private TextArea patients_address;

    @FXML
    private AnchorPane appointments_form;

    @FXML
    private TableView<AppointmentData> appointments_tableView;

    @FXML
    private AnchorPane profile_form;

    @FXML
    private Button profile_importBtn;

    @FXML
    private Label profile_label_name;

    @FXML
    private Label profile_label_email;

    @FXML
    private Label profile_label_dateCreated;

    @FXML
    private TextField profile_email;

    @FXML
    private ComboBox<String> profile_status;

    @FXML
    private TableView<PatientsData> patients_tableView;

    @FXML
    private AnchorPane add_patient_form;

    @FXML
    private AnchorPane add_record_form;

    @FXML
    private Label dashboard_AD;

    @FXML
    private TableColumn<DoctorData, String> dashboad_col_doctorID;


    @FXML
    private TableColumn<DoctorData, String> dashboad_col_specialized;

    @FXML
    private AnchorPane doctors_form;

    @FXML
    private TableView<DoctorData> doctors_tableView;

    @FXML
    private TableColumn<DoctorData, String> doctors_col_doctorID;

    @FXML
    private TableColumn<DoctorData, String> doctors_col_name;

    @FXML
    private TableColumn<DoctorData, String> doctors_col_gender;

    @FXML
    private TableColumn<DoctorData, String> doctors_col_contactNumber;

    @FXML
    private TableColumn<DoctorData, String> doctors_col_email;

    @FXML
    private TableColumn<DoctorData, String> doctors_col_specialization;

    @FXML
    private TableColumn<DoctorData, String> doctors_col_address;

    @FXML
    private TableColumn<DoctorData, String> doctors_col_status;

    @FXML
    private TableColumn<DoctorData, String> doctors_col_action;

    @FXML
    private TableColumn<PatientsData, String> patients_col_patientID;

    @FXML
    private TableColumn<PatientsData, String> patients_col_name;

    @FXML
    private TableColumn<PatientsData, String> patients_col_gender;

    @FXML
    private TableColumn<PatientsData, String> patients_col_contactNumber;

    @FXML
    private TableColumn<PatientsData, String> patients_col_date;

    @FXML
    private TableColumn<PatientsData, String> patients_col_dateModify;

    @FXML
    private TableColumn<PatientsData, String> patients_col_dateDelete;

    @FXML
    private TableColumn<PatientsData, String> patients_col_status;

    @FXML
    private DatePicker record_date_picker;

    @FXML
    private TableColumn<PatientsData, String> patients_col_action;

    @FXML
    private TableColumn<AppointmentData, String> appointments_appointmentID;

    @FXML
    private TableColumn<AppointmentData, String> appointments_name;

    @FXML
    private TableColumn<AppointmentData, String> appointments_patientID;

    @FXML
    private TableColumn<AppointmentData, String> appointments_gender;

    @FXML
    private TableColumn<AppointmentData, String> appointments_contactNumber;

    @FXML
    private TableColumn<AppointmentData, String> appointments_description;

    @FXML
    private TableColumn<AppointmentData, String> appointments_date;

    @FXML
    private TableColumn<AppointmentData, String> appointments_dateModify;

    @FXML
    private TableColumn<AppointmentData, String> appointments_dateDelete;

    @FXML
    private TableColumn<AppointmentData, String> appointments_status;

    @FXML
    private TableColumn<AppointmentData, String> appointments_action;

    @FXML
    private Circle profile_circle;

    @FXML
    private Label profile_label_adminIO;

    @FXML
    private TextField profile_adminID;

    @FXML
    private TextField profile_username;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private AlertMessage alert = new AlertMessage();

    private Image image;


    public ObservableList<RecordData> recordGetData() {

        ObservableList<RecordData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM record";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            RecordData recordData;

            while (result.next()) {

                recordData = new RecordData(result.getInt("id"), result.getInt("patient_id"),
                        result.getString("patient_name"), result.getDate("date"),
                        result.getString("time"), result.getString("status"),
                        result.getString("doctor_id"));
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
        record_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        record_col_time.setCellValueFactory(new PropertyValueFactory<>("time"));
        record_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        record_col_dcotor.setCellValueFactory(new PropertyValueFactory<>("doctorId"));

        record_tableView.setItems(recordListData);
    }

    public void dashboardAD() {

        String sql = "SELECT COUNT(id) FROM doctor WHERE status = 'Активний' AND delete_date IS NULL";

        connect = Database.connectDB();

        int tempAD = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempAD = result.getInt("COUNT(id)");
            }
            dashboard_AD.setText(String.valueOf(tempAD));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardTP() {

        String sql = "SELECT COUNT(id) FROM patient WHERE date_delete IS NULL";

        connect = Database.connectDB();

        int tempTP = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempTP = result.getInt("COUNT(id)");
            }
            dashboard_TP.setText(String.valueOf(tempTP));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardAP() {

        String sql = "SELECT COUNT(id) FROM patient WHERE date_delete IS NULL AND status = 'Активний'";

        connect = Database.connectDB();

        int tempAP = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempAP = result.getInt("COUNT(id)");
            }
            dashboard_AP.setText(String.valueOf(tempAP));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardTA() {

        String sql = "SELECT COUNT(id) FROM appointment";

        connect = Database.connectDB();

        int tempTA = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempTA = result.getInt("COUNT(id)");
            }
            dashboard_tA.setText(String.valueOf(tempTA));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ObservableList<DoctorData> dashboardGetDoctorData() {

        ObservableList<DoctorData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM doctor WHERE delete_date IS NULL";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            DoctorData dData;

            while (result.next()) {
                dData = new DoctorData(result.getString("doctor_id"),
                        result.getString("full_name"), result.getString("specialized"),
                        result.getString("status"));

                listData.add(dData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<DoctorData> dashboardGetDoctorListData;

    public void dashboardGetDoctorDisplayData() {
        dashboardGetDoctorListData = dashboardGetDoctorData();

        dashboad_col_doctorID.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        dashboad_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        dashboad_col_specialized.setCellValueFactory(new PropertyValueFactory<>("specialized"));
        dashboad_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        dashboad_tableView.setItems(dashboardGetDoctorListData);

    }

    public void dashboardPatientDataChart() {
        dashboad_chart_PD.getData().clear();

        String selectData = "SELECT date, COUNT(id) FROM patient WHERE date_delete IS NULL GROUP BY TIMESTAMP(date)";

        connect = Database.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series<>();
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboad_chart_PD.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());

        }

    }

    public void dashboardDoctorDataChart() {
        dashboad_chart_DD.getData().clear();

        String selectData = "SELECT date, COUNT(id) FROM doctor WHERE delete_date IS NULL GROUP BY TIMESTAMP(date)";

        connect = Database.connectDB();
        XYChart.Series chart = new XYChart.Series<>();

        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboad_chart_DD.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ObservableList<DoctorData> doctorGetData() {
        ObservableList<DoctorData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM doctor";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            DoctorData dData;
            while (result.next()) {
                dData = new DoctorData(result.getInt("id"), result.getString("doctor_id"),
                        result.getString("password"), result.getString("full_name"),
                        result.getString("email"), result.getString("gender"),
                        result.getLong("mobile_number"), result.getString("specialized"),
                        result.getString("address"), result.getString("image"),
                        result.getDate("date"), result.getDate("modify_date"),
                        result.getDate("delete_date"), result.getString("status"));

                listData.add(dData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<DoctorData> doctorListData;

    public void doctorDisplayData() {
        doctorListData = doctorGetData();

        doctors_col_doctorID.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        doctors_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        doctors_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        doctors_col_contactNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        doctors_col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        doctors_col_specialization.setCellValueFactory(new PropertyValueFactory<>("specialized"));
        doctors_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        doctors_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        doctors_tableView.setItems(doctorListData);

    }

    public void add_doctor() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/HospitalSystem/Layouts/AddDoctorForm.fxml"));
        Stage stage = new Stage();

        stage.setScene(new Scene(root));
        stage.show();

    }

    public void doctorActionButton() {

        connect = Database.connectDB();
        doctorListData = doctorGetData();

        Callback<TableColumn<DoctorData, String>, TableCell<DoctorData, String>> cellFactory = (TableColumn<DoctorData, String> param) -> {
            final TableCell<DoctorData, String> cell = new TableCell<DoctorData, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Редагувати");
                        Button removeButton = new Button("Видалити");

                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent event) -> {
                            try {

                                DoctorData pData = doctors_tableView.getSelectionModel().getSelectedItem();
                                int num = doctors_tableView.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.errorMessage("Будь ласка, спочатку виберіть запис");
                                    return;
                                }

                                Data.temp_doctorID = pData.getDoctorID();
                                Data.temp_doctorName = pData.getFullName();
                                Data.temp_doctorEmail = pData.getEmail();
                                Data.temp_doctorPassword = pData.getPassword();
                                Data.temp_doctorSpecialized = pData.getSpecialized();
                                Data.temp_doctorGender = pData.getGender();
                                Data.temp_doctorMobileNumber = String.valueOf(pData.getMobileNumber());
                                Data.temp_doctorAddress = pData.getAddress();
                                Data.temp_doctorStatus = pData.getStatus();
                                Data.temp_doctorImagePath = pData.getImage();

                                Parent root = FXMLLoader.load(getClass().getResource("/HospitalSystem/Layouts/EditDoctorForm.fxml"));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                            DoctorData pData = doctors_tableView.getSelectionModel().getSelectedItem();
                            int num = doctors_tableView.getSelectionModel().getSelectedIndex();

                            if ((num - 1) < -1) {
                                alert.errorMessage("Будь ласка, спочатку виберіть запис");
                                return;
                            }

                            String deleteData = "UPDATE doctor SET delete_date = ?, status = 'Неактивний' WHERE doctor_id = '"
                                    + pData.getDoctorID() + "'";

                            try {
                                if (alert.confirmationMessage("Ви впевнені, що хочете видалити лікаря з ID: " + pData.getDoctorID() + "?")) {
                                    prepare = connect.prepareStatement(deleteData);
                                    Date date = new Date();
                                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                                    prepare.setString(1, String.valueOf(sqlDate));
                                    prepare.executeUpdate();

                                    doctorGetData();
                                    alert.successMessage("Видалення успішне!");

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox manageBtn = new HBox(editButton, removeButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };
            doctorDisplayData();
            return cell;
        };

        doctors_col_action.setCellFactory(cellFactory);
        doctors_tableView.setItems(doctorListData);
    }

    public ObservableList<PatientsData> patientGetData() {

        ObservableList<PatientsData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM patient";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            PatientsData pData;

            while (result.next()) {
                pData = new PatientsData(result.getInt("id"), result.getInt("patient_id"),
                        result.getString("full_name"),
                        result.getLong("mobile_number"), result.getString("gender"),
                        result.getString("address"),
                        result.getString("doctor"),
                        result.getString("specialized"), result.getDate("date"),
                        result.getDate("date_modify"), result.getDate("date_delete"),
                        result.getString("status"));

                listData.add(pData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<PatientsData> patientListData;

    public void patientDisplayData() {
        patientListData = patientGetData();

        patients_col_patientID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        patients_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        patients_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        patients_col_contactNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        patients_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        patients_col_dateModify.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
        patients_col_dateDelete.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
        patients_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        patients_tableView.setItems(patientListData);
    }

    public void patientActionButton() {

        connect = Database.connectDB();
        patientListData = patientGetData();

        Callback<TableColumn<PatientsData, String>, TableCell<PatientsData, String>> cellFactory = (TableColumn<PatientsData, String> param) -> {
            final TableCell<PatientsData, String> cell = new TableCell<PatientsData, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Редагувати");
                        Button removeButton = new Button("Видалити");

                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent event) -> {
                            try {

                                PatientsData pData = patients_tableView.getSelectionModel().getSelectedItem();
                                int num = patients_tableView.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.errorMessage("Будь ласка, спочатку виберіть запис");
                                    return;
                                }

                                Data.temp_PatientID = pData.getPatientID();
                                Data.temp_address = pData.getAddress();
                                Data.temp_name = pData.getFullName();
                                Data.temp_gender = pData.getGender();
                                Data.temp_number = pData.getMobileNumber();
                                Data.temp_status = pData.getStatus();

                                Parent root = FXMLLoader.load(getClass().getResource("/HospitalSystem/Layouts/EditPatientForm.fxml"));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                            PatientsData pData = patients_tableView.getSelectionModel().getSelectedItem();
                            int num = patients_tableView.getSelectionModel().getSelectedIndex();

                            if ((num - 1) < -1) {
                                alert.errorMessage("Будь ласка, спочатку виберіть запис");
                                return;
                            }

                            String deleteData = "UPDATE patient SET date_delete = ?, status = 'Неактивний' WHERE patient_id = '"
                                    + pData.getPatientID() + "'";

                            try {
                                if (alert.confirmationMessage("Ви впевнені, що хочете видалити пацієнта з ID: " + pData.getPatientID() + "?")) {
                                    prepare = connect.prepareStatement(deleteData);
                                    Date date = new Date();
                                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                                    prepare.setString(1, String.valueOf(sqlDate));
                                    prepare.executeUpdate();

                                    doctorGetData();
                                    alert.successMessage("Видалення успішне!");

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox manageBtn = new HBox(editButton, removeButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };
            doctorDisplayData();
            return cell;
        };

        patients_col_action.setCellFactory(cellFactory);
        patients_tableView.setItems(patientListData);

    }

    public ObservableList<AppointmentData> appointmentGetData() {

        ObservableList<AppointmentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointment";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            AppointmentData aData;
            while (result.next()) {
                aData = new AppointmentData(result.getInt("id"), result.getInt("appointment_id"), result.getLong("patient_id"),
                        result.getString("name"), result.getString("gender"), result.getLong("mobile_number"),
                        result.getString("description"), result.getString("diagnosis"),
                        result.getString("treatment"), result.getString("address"),
                        result.getString("doctor"), result.getString("specialized"),
                        result.getDate("date"), result.getDate("date_modify"),
                        result.getDate("date_delete"), result.getString("status"),
                        result.getDate("schedule"));
                listData.add(aData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<AppointmentData> appointmentListData;

    public void appointmentDisplayData() {
        appointmentListData = appointmentGetData();

        appointments_appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        appointments_patientID.setCellValueFactory(new PropertyValueFactory<>("patientID"));

        appointments_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        appointments_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        appointments_contactNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        appointments_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointments_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        appointments_dateModify.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
        appointments_dateDelete.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
        appointments_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        appointments_tableView.setItems(appointmentListData);

    }

    public void appointmentActionButton() {

        connect = Database.connectDB();
        appointmentListData = appointmentGetData();

        Callback<TableColumn<AppointmentData, String>, TableCell<AppointmentData, String>> cellFactory = (TableColumn<AppointmentData, String> param) -> {
            final TableCell<AppointmentData, String> cell = new TableCell<AppointmentData, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Редагувати");
                        Button removeButton = new Button("Видалити");

                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent event) -> {
                            try {

                                AppointmentData aData = appointments_tableView.getSelectionModel().getSelectedItem();
                                int num = appointments_tableView.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.errorMessage("Будь ласка, спочатку виберіть запис");
                                    return;
                                }

                                Data.temp_appID = String.valueOf(aData.getAppointmentID());
                                Data.temp_appName = aData.getName();
                                Data.temp_appGender = aData.getGender();
                                Data.temp_appAddress = aData.getAddress();
                                Data.temp_appDescription = aData.getDescription();
                                Data.temp_appDiagnosis = aData.getDiagnosis();
                                Data.temp_appTreatment = aData.getTreatment();
                                Data.temp_appMobileNumber = String.valueOf(aData.getMobileNumber());
                                Data.temp_appDoctor = aData.getDoctorID();
                                Data.temp_appSpecialized = aData.getSpecialized();
                                Data.temp_appStatus = aData.getStatus();

                                Parent root = FXMLLoader.load(getClass().getResource("/HospitalSystem/Layouts/EditAppointmentForm.fxml"));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                            AppointmentData aData = appointments_tableView.getSelectionModel().getSelectedItem();
                            int num = appointments_tableView.getSelectionModel().getSelectedIndex();

                            if ((num - 1) < -1) {
                                alert.errorMessage("Будь ласка, спочатку виберіть запис");
                                return;
                            }

                            String deleteData = "UPDATE appointment SET date_delete = ?, status = 'Неактивний' WHERE appointment_id = '"
                                    + aData.getAppointmentID() + "'";

                            try {
                                if (alert.confirmationMessage("Ви впевнені, що хочете видалити призначення з ID: " + aData.getAppointmentID() + "?")) {
                                    prepare = connect.prepareStatement(deleteData);
                                    Date date = new Date();
                                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                                    prepare.setString(1, String.valueOf(sqlDate));
                                    prepare.executeUpdate();

                                    doctorGetData();
                                    alert.successMessage("Видалення успішне!");

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox manageBtn = new HBox(editButton, removeButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };
            doctorDisplayData();
            return cell;
        };

        appointments_action.setCellFactory(cellFactory);
        appointments_tableView.setItems(appointmentListData);

    }

    public void profileUpdateBtn() {
        connect = Database.connectDB();
        if (profile_adminID.getText().isEmpty()
                || profile_username.getText().isEmpty()
                || profile_email.getText().isEmpty()
                || profile_status.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Будь ласка, заповніть усі порожні поля");
        } else {
            if (Data.path == null || "".equals(Data.path)) {
                String updateData = "UPDATE admin SET username = ?, email = ?, gender = ? "
                        + "WHERE admin_id = " + Data.admin_id;

                try {
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, profile_username.getText());
                    prepare.setString(2, profile_email.getText());
                    prepare.setString(3, profile_status.getSelectionModel().getSelectedItem());

                    prepare.executeUpdate();

                    profileDisplayInfo();

                    alert.successMessage("Оновлення успішне!");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                String updateData = "UPDATE admin SET username = ?, email = ?, image = ?, gender = ? "
                        + "WHERE admin_id = " + Data.admin_id;
                try {
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, profile_username.getText());
                    prepare.setString(2, profile_email.getText());

                    String path = Data.path;
                    path = path.replace("\\", "\\\\");
                    Path transfer = Paths.get(path);

                    Path copy = Paths.get("C:\\Users\\User\\Desktop\\Coursework-Hospital\\HospitalManagementSystem\\src\\Admin_Directory\\"
                            + Data.admin_id + ".jpg");

                    Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);
                    prepare.setString(3, copy.toAbsolutePath().toString());
                    prepare.setString(4, profile_status.getSelectionModel().getSelectedItem());

                    prepare.executeUpdate();
                    profileDisplayInfo();
                    profileDisplayImages();
                    alert.successMessage("Оновлення успішне!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void profileDisplayImages() {

        String selectData = "SELECT * FROM admin WHERE admin_id = " + Data.admin_id;
        connect = Database.connectDB();

        String tempPath1 = "";
        String tempPath2 = "";
        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if (result.next()) {
                tempPath1 = "File:" + result.getString("image");
                tempPath2 = "File:" + result.getString("image");

                if (result.getString("image") != null) {
                    image = new Image(tempPath1, 1012, 22, false, true);
                    top_profile.setFill(new ImagePattern(image));

                    image = new Image(tempPath2, 137, 95, false, true);
                    profile_circle.setFill(new ImagePattern(image));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void profileInsertImage() {

        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new ExtensionFilter("Open Image", "*jpg", "*jpeg", "*png"));

        File file = open.showOpenDialog(profile_importBtn.getScene().getWindow());

        if (file != null) {
            Data.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 137, 95, false, true);
            profile_circle.setFill(new ImagePattern(image));
        }

    }

    public void profileDisplayInfo() {

        String sql = "SELECT * FROM admin WHERE admin_id = " + Data.admin_id;
        System.out.println(Data.admin_id);
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                profile_adminID.setText(result.getString("admin_id"));
                profile_username.setText(result.getString("username"));
                profile_email.setText(result.getString("email"));
                profile_status.getSelectionModel().select(result.getString("gender"));

                profile_label_adminIO.setText(result.getString("admin_id"));
                profile_label_name.setText(result.getString("username"));
                profile_label_email.setText(result.getString("email"));
                profile_label_dateCreated.setText(result.getString("date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void profileStatusList() {
        List<String> listS = new ArrayList<>();

        for (String data : Data.gender) {
            listS.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listS);
        profile_status.setItems(listData);
    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);
            add_patient_form.setVisible(false);
            add_record_form.setVisible((false));



            dashboardAD();
            dashboardTP();
            dashboardAP();
            dashboardTA();
            dashboardGetDoctorDisplayData();

            current_form.setText("Вікно статистики");
        } else if (event.getSource() == doctors_btn) {
            dashboard_form.setVisible(false);
            doctors_form.setVisible(true);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);
            add_patient_form.setVisible(false);
            add_record_form.setVisible((false));

            doctorDisplayData();
            doctorActionButton();

            current_form.setText("Вікно лікарів");
        } else if (event.getSource() == patients_btn) {
            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(true);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);
            add_patient_form.setVisible(false);
            add_record_form.setVisible((false));


            patientDisplayData();
            patientActionButton();
            current_form.setText("Вікно пацієнтів");
        } else if (event.getSource() == appointments_btn) {
            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(true);
            profile_form.setVisible(false);
            add_patient_form.setVisible(false);
            add_record_form.setVisible((false));


            appointmentDisplayData();

            current_form.setText("Вікно призначень");
        } else if (event.getSource() == add_patient) {
            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);
            add_patient_form.setVisible(true);
            add_record_form.setVisible((false));

            appointmentDisplayData();

            current_form.setText("Вікно додавання пацієнтів");
        }

        else if (event.getSource() == profile_btn) {
            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(true);
            add_patient_form.setVisible(false);
            add_record_form.setVisible((false));


            profileStatusList();
            profileDisplayInfo();
            profileDisplayImages();

            current_form.setText("Вікно налаштувань");
        }
        else if (event.getSource() == record_btn) {
            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);
            add_patient_form.setVisible(false);
            add_record_form.setVisible((true));

            patientsIdRecordList();
            recordShowData();

            current_form.setText("Вікно додавання запису");
        }


    }

    public void patientConfirmBtn() {

        if (patients_patientID.getText().isEmpty()
                || patients_doctor.getSelectionModel().getSelectedItem() == null
                || patients_patientName.getText().isEmpty()
                || patients_gender.getSelectionModel().getSelectedItem() == null
                || patients_mobileNumber.getText().isEmpty()
                || patients_address.getText().isEmpty()) {
            alert.errorMessage("Будь ласка, заповніть усі порожні поля");
        } else {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            patients_PA_patientID.setText(patients_patientID.getText());
            patients_PA_dateCreated.setText(String.valueOf(sqlDate));

            patients_PI_doctorID.setText((String) patients_doctor.getSelectionModel().getSelectedItem());
            patients_PI_patientName.setText(patients_patientName.getText());
            patients_PI_gender.setText((String) patients_gender.getSelectionModel().getSelectedItem());
            patients_PI_mobileNumber.setText(patients_mobileNumber.getText());
            patients_PI_address.setText(patients_address.getText());
        }

    }

    public void createRecordBtn(){
        if (patients_Id.getItems().isEmpty()
                || record_time.getText().isEmpty()
                || record_date_picker.getValue() == null)
                {
            alert.errorMessage("Щось пішло не так");
        } else {

            Database.connectDB();
            try {
                String patientName = "";
                String doctorId = "";

                String getDoctor = "SELECT doctor, full_name FROM patient WHERE patient_id = '"
                        + (String) patients_Id.getSelectionModel().getSelectedItem() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(getDoctor);

                if (result.next()) {
                    doctorId = result.getString("doctor");
                    patientName = result.getString("full_name");
                }

                    String insertData = "INSERT INTO record (patient_id, patient_name, date, "
                            + "time, status, doctor_id) "
                            + "VALUES(?,?,?,?,?,?)";
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, (String) patients_Id.getSelectionModel().getSelectedItem());
                    prepare.setString(2, patientName);
                    prepare.setString(3, "" + record_date_picker.getValue());
                    prepare.setString(4, record_time.getText());
                    prepare.setString(5, "Незавершено");
                    prepare.setString(6, doctorId);

                    prepare.executeUpdate();

                    alert.successMessage("Додавання успішне!");


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        recordShowData();

    }

    public void patientsIdRecordList(){
        String sql = "SELECT * FROM patient WHERE date_delete IS NULL and status = 'Активний'";

        connect = Database.connectDB();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            ObservableList listData = FXCollections.observableArrayList();
            while(result.next()){
                listData.add(result.getString("patient_id"));
            }

            patients_Id.setItems(listData);
        }catch(Exception e){e.printStackTrace();}
    }



    public void patientAddBtn() {

        if (patients_PA_patientID.getText().isEmpty()
                || patients_PI_doctorID.getText().isEmpty()
                || patients_PA_dateCreated.getText().isEmpty()
                || patients_PI_patientName.getText().isEmpty()
                || patients_PI_gender.getText().isEmpty()
                || patients_PI_mobileNumber.getText().isEmpty()
                || patients_PI_address.getText().isEmpty()) {
            alert.errorMessage("Щось пішло не так");
        } else {

            Database.connectDB();
            try {
                String doctorName = "";
                String doctorSpecialized = "";

                String getDoctor = "SELECT * FROM doctor WHERE doctor_id = '"
                        + patients_PI_doctorID.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(getDoctor);

                if (result.next()) {
                    doctorSpecialized = result.getString("specialized");
                }
                String checkPatientID = "SELECT * FROM patient WHERE patient_id = '"
                        + patients_PA_patientID.getText() + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(checkPatientID);
                if (result.next()) {
                    alert.errorMessage(patients_PA_patientID.getText() + " вже існує");
                } else {
                    String insertData = "INSERT INTO patient (patient_id, full_name, mobile_number, "
                            + "address, doctor, specialized, date, gender, "
                            + "status) "
                            + "VALUES(?,?,?,?,?,?,?,?,?)";
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, patients_PA_patientID.getText());
                    prepare.setString(2, patients_PI_patientName.getText());
                    prepare.setString(3, patients_PI_mobileNumber.getText());
                    prepare.setString(4, patients_PI_address.getText());
                    prepare.setString(5, patients_PI_doctorID.getText());
                    prepare.setString(6, doctorSpecialized);
                    prepare.setString(7, "" + sqlDate);
                    prepare.setString(8, (String) patients_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(9, "Активний");

                    prepare.executeUpdate();

                    alert.successMessage("Додавання успішне!");
                    patientClearFields();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
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

    public void doctorList(){
        String sql = "SELECT * FROM doctor WHERE delete_date IS NULL";

        connect = Database.connectDB();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            ObservableList listData = FXCollections.observableArrayList();
            while(result.next()){
                listData.add(result.getString("doctor_id"));
            }

            patients_doctor.setItems(listData);
            //specializedList();
        }catch(Exception e){e.printStackTrace();}
    }

    public void displayAdminIDUsername() {

        String sql = "SELECT * FROM admin WHERE username = '"
                + Data.admin_username + "'";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                nav_adminID.setText(result.getString("admin_id"));
                String tempUsername = result.getString("username");
                tempUsername = tempUsername.substring(0, 1).toUpperCase() + tempUsername.substring(1);
                nav_username.setText(tempUsername);
                top_username.setText(tempUsername);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void logoutBtn() {

        try {
            if (alert.confirmationMessage("Ви впевнені, що бажаєте вийти?")) {
                Parent root = FXMLLoader.load(getClass().getResource("/HospitalSystem/Layouts/FXMLDocument.fxml"));
                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.show();

                logout_btn.getScene().getWindow().hide();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        runTime();
        displayAdminIDUsername();

        dashboardAD();
        dashboardTP();
        dashboardAP();
        dashboardTA();
        dashboardGetDoctorDisplayData();
        dashboardPatientDataChart();
        dashboardDoctorDataChart();

        doctorDisplayData();
        doctorActionButton();

        patientDisplayData();
        patientActionButton();

        appointmentDisplayData();
        appointmentActionButton();

        patientGenderList();
        doctorList();

        profileStatusList();
        profileDisplayInfo();
        profileDisplayImages();
    }

}
