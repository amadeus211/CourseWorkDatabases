package HospitalSystem.Data;

import java.sql.Date;

public class AppointmentData {

    private Integer id;
    private Integer appointmentID;
    private Long patientID;
    private String name;
    private String gender;
    private String description;
    private String diagnosis;
    private String treatment;
    private Long mobileNumber;
    private String address;
    private Date date;
    private Date dateModify;
    private Date dateDelete;
    private String status;
    private String doctorID;
    private String specialized;
    private Date schedule;

    public AppointmentData(Integer id, Integer appointmentID, Long patientID, String name, String gender,
            Long mobileNumber, String description, String diagnosis, String treatment, String address,
            String doctorID, String specialized,
            Date date, Date dateModify, Date dateDelete, String status, Date schedule) {
        this.id = id;
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.name = name;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.description = description;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.address = address;
        this.doctorID = doctorID;
        this.specialized = specialized;
        this.date = date;
        this.dateModify = dateModify;
        this.dateDelete = dateDelete;
        this.status = status;
        this.schedule = schedule;

    }

    public AppointmentData(Integer appointmentID, Long patientID, String name, String gender,
            Long mobileNumber, String description, String diagnosis, String treatment, String address,
            Date date, Date dateModify, Date dateDelete, String status, Date schedule) {

        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.name = name;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.description = description;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.address = address;
        this.date = date;
        this.dateModify = dateModify;
        this.dateDelete = dateDelete;
        this.status = status;
        this.schedule = schedule;

    }

    public Long getPatientID() {
        return patientID;
    }

    public AppointmentData(Integer appointmentID, String name,
                           String description, Date date, String status) {
        this.appointmentID = appointmentID;
        this.name = name;
        this.description = description;
        this.date = date;
        this.status = status;
    }

    public AppointmentData(Integer appointmentID, String description,
            String diagnosis, String treatment, String doctorID, Date schedule) {
        this.appointmentID = appointmentID;
        this.description = description;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.doctorID = doctorID;
        this.schedule = schedule;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAppointmentID() {
        return appointmentID;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Long getMobileNumber() {
        return mobileNumber;
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

    public String getAddress() {
        return address;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public String getSpecialized() {
        return specialized;
    }

    public Date getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public Date getDateModify() {
        return dateModify;
    }

    public Date getDateDelete() {
        return dateDelete;
    }

    public Date getSchedule() {
        return schedule;
    }
}
