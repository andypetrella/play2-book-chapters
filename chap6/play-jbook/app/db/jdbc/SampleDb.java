package db.jdbc;

import play.db.*;

import java.sql.*;

import java.util.List;
import java.util.ArrayList;

public class SampleDb {

    public static Connection connect() {
        return DB.getConnection();
    }

    public static void disconnect(Connection connection) {
        DB.getConnection();
    }

    public static void createTestTable() throws Exception {
        Connection c = connect();
        try {
            c.createStatement().executeUpdate("create table test(value varchar(50))");
        } finally {
            disconnect(c);
        }
    }

    public static void insertTestData(String v) throws Exception {
        Connection c = connect();
        try {
            //OK OK => prepared statement... SQL injection blahblah...
            // >> Just for testing purpose here ^^
            c.createStatement().executeUpdate("insert into test values ('"+v+"')");
        } finally {
            disconnect(c);
        }
    }

    public static List<String> getTestData() throws Exception {
        Connection c = connect();
        try {
            ResultSet resultSet = c.createStatement().executeQuery("select * from test");
            List<String> values = new ArrayList<String>();
            while (resultSet.next()) {
                values.add(resultSet.getString(1));
            }
            return values;
        } finally {
            disconnect(c);
        }
    }

}