package HospitalSystem.Data;

import java.sql.Date;

public class DoctorData {

    private String doctorID;
    private String password;
    private String fullName;
    private String email;
    private String gender;
    private Long mobileNumber;
    private String specialized;
    private String address;
    private String image;
    private Date date;
    private Date dateModify;
    private Date dateDelete;
    private String status;

    public DoctorData( String doctorID, String password, String fullName,
            String email, String gender, Long mobileNumber, String specialized, String address,
            String image, Date date, Date dateModify, Date dateDelete, String status) {
        this.doctorID = doctorID;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.specialized = specialized;
        this.address = address;
        this.image = image;
        this.date = date;
        this.dateModify = dateModify;
        this.dateDelete = dateDelete;
        this.status = status;
    }

    public DoctorData(String doctorID, String fullName, String specialized, String status) {
        this.doctorID = doctorID;
        this.fullName = fullName;
        this.specialized = specialized;
        this.status = status;
    }


    public String getDoctorID() {
        return doctorID;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public Date getDateModify() {
        return dateModify;
    }

    public Date getDateDelete() {
        return dateDelete;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public String getSpecialized() {
        return specialized;
    }

    public String getAddress() {
        return address;
    }

    public String getImage() {
        return image;
    }

    public Date getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

}
