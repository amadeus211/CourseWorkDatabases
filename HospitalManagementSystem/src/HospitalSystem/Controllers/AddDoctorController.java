package HospitalSystem.Controllers;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class AddDoctorController implements Initializable {

    @FXML
    private TextField editDoctor_doctorID;

    @FXML
    private TextField editDoctor_fullName;

    @FXML
    private TextField editDoctor_email;

    @FXML
    private TextField editDoctor_password;

    @FXML
    private ComboBox<String> editDoctor_specialized;

    @FXML
    private ComboBox<String> editDoctor_gender;

    @FXML
    private TextField editDoctor_mobileNumber;

    @FXML
    private ImageView editDoctor_imageView;

    @FXML
    private Button editDoctor_importBtn;

    @FXML
    private TextArea editDoctor_address;

    @FXML
    private ComboBox<String> editDoctor_status;

    private AlertMessage alert = new AlertMessage();

    private Image image;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void importBtn() {

        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new ExtensionFilter("Open Image", "*jpg", "*png", "*jpeg"));

        File file = open.showOpenDialog(editDoctor_importBtn.getScene().getWindow());

        if (file != null) {

            Data.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 112, 121, false, true);
            editDoctor_imageView.setImage(image);

        }

    }

    public void displayDoctorData() {

        String sql = "SELECT * FROM doctor WHERE doctor_id = '"
                + editDoctor_doctorID.getText() + "'";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                editDoctor_fullName.setText(result.getString("full_name"));
                editDoctor_email.setText(result.getString("email"));
                editDoctor_password.setText(result.getString("password"));
                editDoctor_specialized.getSelectionModel().select(result.getString("specialized"));
                editDoctor_gender.getSelectionModel().select(result.getString("gender"));
                editDoctor_mobileNumber.setText(result.getString("mobile_number"));
                editDoctor_address.setText(result.getString("address"));
                editDoctor_status.getSelectionModel().select(result.getString("status"));

                image = new Image("File:" + result.getString("image"), 112, 121, false, true);
                editDoctor_imageView.setImage(image);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String registerDoctorID() {
        String doctorID = "DID-";
        int tempID = 0;
        String sql = "SELECT MAX(id) FROM doctor";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempID = result.getInt("MAX(id)");
            }

            if (tempID == 0) {
                tempID += 1;
                doctorID += tempID;
            } else {
                doctorID += (tempID + 1);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return doctorID;

    }

    public void addBtn() {
        connect = Database.connectDB();


        if (editDoctor_doctorID.getText().isEmpty()
                || editDoctor_fullName.getText().isEmpty()
                || editDoctor_email.getText().isEmpty()
                || editDoctor_password.getText().isEmpty()
                || editDoctor_specialized.getSelectionModel().getSelectedItem() == null
                || editDoctor_gender.getSelectionModel().getSelectedItem() == null
                || editDoctor_mobileNumber.getText().isEmpty()
                || editDoctor_address.getText().isEmpty()
                || editDoctor_status.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Будь ласка, заповніть усі порожні поля");
        } else {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            try {
                String insertData = "INSERT INTO doctor (doctor_id, full_name, email, password, " +
                        "specialized, gender, mobile_number, address, status, date) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                prepare = connect.prepareStatement(insertData);
                prepare.setString(1, editDoctor_doctorID.getText());
                prepare.setString(2, editDoctor_fullName.getText());
                prepare.setString(3, editDoctor_email.getText());
                prepare.setString(4, editDoctor_password.getText());
                prepare.setString(5, editDoctor_specialized.getSelectionModel().getSelectedItem());
                prepare.setString(6, editDoctor_gender.getSelectionModel().getSelectedItem());
                prepare.setString(7, editDoctor_mobileNumber.getText());
                prepare.setString(8, editDoctor_address.getText());
                prepare.setString(9, editDoctor_status.getSelectionModel().getSelectedItem());
                prepare.setDate(10, sqlDate);

                if (alert.confirmationMessage("Ви впевнені, що хочете створити цього лікаря?")) {
                    prepare.executeUpdate();
                    alert.successMessage("Лікаря створено успішно");
                } else {
                    alert.errorMessage("Скасовано.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                alert.errorMessage("Помилка створення лікаря.");
            }
        }
        displayDoctorData();
    }

    public void cancelBtn() {
        displayDoctorData();
    }

    public void setField() {
        editDoctor_doctorID.setText(registerDoctorID());

    }

    public void specializationList() {
        List<String> specializationL = new ArrayList<>();

        for (String data : Data.specialization) {
            specializationL.add(data);
        }

        ObservableList listData = FXCollections.observableList(specializationL);
        editDoctor_specialized.setItems(listData);
    }

    public void genderList() {
        List<String> genderL = new ArrayList<>();

        for (String data : Data.gender) {
            genderL.add(data);
        }

        ObservableList listData = FXCollections.observableList(genderL);
        editDoctor_gender.setItems(listData);
    }

    public void statusList() {
        List<String> statusL = new ArrayList<>();

        for (String data : Data.status) {
            statusL.add(data);
        }

        ObservableList listData = FXCollections.observableList(statusL);
        editDoctor_status.setItems(listData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setField();
        specializationList();
        genderList();
        statusList();
    }

}
