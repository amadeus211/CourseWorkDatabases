package HospitalSystem.Data;

import java.sql.Date;

public class AppointmentData {

    private Integer id;
    private Integer appointmentID;
    private Long patientID;
    private String description;
    private String diagnosis;
    private String treatment;
    private Date date;
    private Date dateModify;
    private Date dateDelete;
    private String status;
    private String doctorID;
    private String specialized;

    public AppointmentData( Integer appointmentID, Long patientID,
             String description, String diagnosis, String treatment,
            String doctorID,
            Date date, Date dateModify, Date dateDelete, String status) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;


        this.description = description;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.doctorID = doctorID;
        this.date = date;
        this.dateModify = dateModify;
        this.dateDelete = dateDelete;
        this.status = status;

    }

    public AppointmentData(Integer appointmentID, Long patientID,
            String description, String diagnosis, String treatment,
            Date date, Date dateModify, Date dateDelete, String status) {

        this.appointmentID = appointmentID;
        this.patientID = patientID;

        this.description = description;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.date = date;
        this.dateModify = dateModify;
        this.dateDelete = dateDelete;
        this.status = status;

    }

    public Long getPatientID() {
        return patientID;
    }

    public AppointmentData(Integer appointmentID,
                           String description, Date date, String status) {
        this.appointmentID = appointmentID;

        this.description = description;
        this.date = date;
        this.status = status;
    }

    public AppointmentData(Integer appointmentID, String description,
            String diagnosis, String treatment, String doctorID) {
        this.appointmentID = appointmentID;
        this.description = description;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.doctorID = doctorID;

    }


    public Integer getAppointmentID() {
        return appointmentID;
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

}
