package HospitalSystem.Controllers;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

public class EditDoctorFormController implements Initializable {

    @FXML
    private TextField editDoctor_doctorID;

    @FXML
    private TextField editDoctor_fullName;

    @FXML
    private TextField editDoctor_surname;

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
                editDoctor_fullName.setText(result.getString("name"));
                editDoctor_fullName.setText(result.getString("surname"));
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

    public void updateBtn() {
        connect = Database.connectDB();

        if (editDoctor_doctorID.getText().isEmpty()
                || editDoctor_fullName.getText().isEmpty()
                || editDoctor_surname.getText().isEmpty()
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

            if (Data.path == null || "".equals(Data.path)) {
                String updateData = "UPDATE doctor SET name = ?, email = ?, password = ?, specialized = ?, " +
                        "gender = ?, mobile_number = ?, address = ?, status = ?, modify_date = ?, date_delete = ?, surname = ? WHERE doctor_id = ?";
                try {
                    if (alert.confirmationMessage("Ви впевнені, що хочете оновити лікаря з ID: " + editDoctor_doctorID.getText() + "?")) {
                        System.out.println("no image");
                        prepare = connect.prepareStatement(updateData);
                        prepare.setString(1, editDoctor_fullName.getText());
                        prepare.setString(2, editDoctor_email.getText());
                        prepare.setString(3, editDoctor_password.getText());
                        prepare.setString(4, editDoctor_specialized.getSelectionModel().getSelectedItem());
                        prepare.setString(5, editDoctor_gender.getSelectionModel().getSelectedItem());
                        prepare.setString(6, editDoctor_mobileNumber.getText());
                        prepare.setString(7, editDoctor_address.getText());
                        if(editDoctor_status.getSelectionModel().getSelectedItem().equals("Неактивний")) {
                            prepare.setString(10, String.valueOf(sqlDate));
                        }else{
                            prepare.setString(10, null);
                        }
                        prepare.setString(8, editDoctor_status.getSelectionModel().getSelectedItem());
                        prepare.setDate(9, sqlDate);
                        prepare.setString(11, editDoctor_surname.getText());
                        prepare.setString(12, editDoctor_doctorID.getText());

                        prepare.executeUpdate();
                        alert.successMessage("Оновлення успішне");

                    } else {
                        alert.errorMessage("Скасовано.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    if (alert.confirmationMessage("Ви впевнені, що хочете оновити лікаря з ID: " + editDoctor_doctorID.getText() + "?")) {
                        try {
                            String path = Data.path;
                            path = path.replace("\\", "\\\\");
                            Path transfer = Paths.get(path);

                            Path copy = Paths.get("C:\\Users\\User\\Desktop\\Coursework-Hospital\\HospitalManagementSystem\\src\\Doctor_Directory\\" + editDoctor_doctorID.getText() + ".jpg");

                            Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);

                            String insertImage = copy.toString();
                            insertImage = insertImage.replace("\\", "\\\\");

                            String updateData = "UPDATE doctor SET name = ?, surname = ?, email = ?, password = ?, specialized = ?, " +
                                    "gender = ?, mobile_number = ?, image = ?, address = ?, status = ?, modify_date = ?" +
                                    ", date_delete = ?, surname = ? WHERE doctor_id = ?";
                            System.out.println(" image");

                            prepare = connect.prepareStatement(updateData);
                            prepare.setString(1, editDoctor_fullName.getText());
                            prepare.setString(2, editDoctor_surname.getText());
                            prepare.setString(3, editDoctor_email.getText());
                            prepare.setString(4, editDoctor_password.getText());
                            prepare.setString(5, editDoctor_specialized.getSelectionModel().getSelectedItem());
                            prepare.setString(6, editDoctor_gender.getSelectionModel().getSelectedItem());
                            prepare.setString(7, editDoctor_mobileNumber.getText());
                            prepare.setString(8, insertImage);
                            prepare.setString(9, editDoctor_address.getText());
                            prepare.setString(10, editDoctor_status.getSelectionModel().getSelectedItem());
                            if(editDoctor_status.getSelectionModel().getSelectedItem().equals("Неактивний")){
                                prepare.setString(12, String.valueOf(sqlDate));
                            }else{
                                prepare.setString(12, null);

                            }
                            prepare.setString(13, editDoctor_surname.getText());
                            prepare.setString(14, editDoctor_doctorID.getText());
                            prepare.setString(11, String.valueOf(sqlDate));


                            prepare.executeUpdate();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        alert.errorMessage("Скасовано.");
                    }

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        displayDoctorData();
    }

    public void cancelBtn() {
        displayDoctorData();
    }

    public void setField() {
        editDoctor_surname.setText(Data.temp_doctorSurname);
        editDoctor_doctorID.setText(Data.temp_doctorID);
        editDoctor_fullName.setText(Data.temp_doctorName);
        editDoctor_email.setText(Data.temp_doctorEmail);
        editDoctor_password.setText(Data.temp_doctorPassword);
        editDoctor_specialized.getSelectionModel().select(Data.temp_doctorSpecialized);
        editDoctor_gender.getSelectionModel().select(Data.temp_doctorGender);
        editDoctor_mobileNumber.setText(Data.temp_doctorMobileNumber);
        editDoctor_address.setText(Data.temp_doctorAddress);
        editDoctor_status.getSelectionModel().select(Data.temp_doctorStatus);

        image = new Image("File:" + Data.temp_doctorImagePath, 112, 121, false, true);
        editDoctor_imageView.setImage(image);
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
        //displayDoctorData();

        setField();
        specializationList();
        genderList();
        statusList();
    }

}
