package HospitalSystem.Data;

import java.sql.Date;

public class PatientsData {
    
    private Integer id;
    private Integer patientID;
    private String name;

    public PatientsData(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public PatientsData(int patientId, String name, String surname, String gender,
                        long mobileNumber, String address, String status,
                        Date date, Date dateModify, Date dateDelete) {
        this.patientID = patientId;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.status = status;
        this.date = date;
        this.dateModify = dateModify;
        this.dateDelete = dateDelete;
    }

    public String getSurname() {
        return surname;
    }

    private String surname;
    private Long mobileNumber;
    private String address;

    private String description;
    private String diagnosis;
    private String treatment;
    private String doctor;
    private String specialized;
    private String gender;
    private Date date;
    private Date dateModify;
    private Date dateDelete;
    private String status;

    public PatientsData(Integer patientID, String name, String surname, Long mobileNumber
            , String gender, String address,
                        String doctor, Date date, Date dateModify
            , Date dateDelete, String status){
        this.patientID = patientID;
        this.name = name;
        this.surname = surname;

        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.address = address;
        this.doctor = doctor;

        this.date = date;
        this.dateModify = dateModify;
        this.dateDelete = dateDelete;
        this.status = status;
    }
    
    public PatientsData(Integer patientID, String name, String gender,
                        Long mobileNumber, String address, String status, Date date
            , Date dateModify, Date dateDelete){
        this.patientID = patientID;
        this.name = name;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.status = status;
        this.date = date;
        this.dateModify = dateModify;
        this.dateDelete = dateDelete;
    }

    public PatientsData(Integer id, Integer patientID, String name, String gender
            , String description, String diagnosis, String treatment
            , String doctor, Date date){
        this.id = id;
        this.patientID = patientID;
        this.name = name;
        this.gender = gender;
        this.description = description;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.doctor = doctor;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public Date getDateModify() {
        return dateModify;
    }

    public Date getDateDelete() {
        return dateDelete;
    }

    public Integer getId(){
        return id;
    }
    public Integer getPatientID(){
        return patientID;
    }

    public String getName(){
        return name;
    }
    public String getGender(){
        return gender;
    }
    public Long getMobileNumber(){
        return mobileNumber;
    }
    public String getAddress(){
        return address;
    }
    public String getDoctor(){
        return doctor;
    }
    public String getSpecialized(){
        return specialized;
    }
    public Date getDate(){
        return date;
    }
    public String getStatus(){
        return status;
    }

}
