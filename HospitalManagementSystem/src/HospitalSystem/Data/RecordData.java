package HospitalSystem.Data;

import java.util.Date;

public class RecordData {
    private Integer id;
    private Integer patientId;
    private Date date;
    private String time;

    public String getPatientName() {
        return patientName;
    }

    private String patientName;

    private String status;

    public String getDoctorId() {
        return doctorId;
    }
    private String doctorId;



    public RecordData(Integer id, Integer patientId,String patientName, Date date, String time, String status, String doctorId) {
        this.id = id;
        this.patientId = patientId;
        this.date = date;
        this.time = time;
        this.patientName = patientName;
        this.status = status;
        this.doctorId = doctorId;
    }


    public Integer getId() {
        return id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public Date getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }
}
