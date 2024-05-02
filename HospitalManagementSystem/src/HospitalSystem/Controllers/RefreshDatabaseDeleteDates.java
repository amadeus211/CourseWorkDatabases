package HospitalSystem.Controllers;

import HospitalSystem.Data.Data;
import HospitalSystem.Data.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefreshDatabaseDeleteDates {

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void refresh(String table){
        String sql = "UPDATE'" + table + "' SET date_delete = NULL WHERE status = 'Активний' AND date_delete IS NOT NULL";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);

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

}
