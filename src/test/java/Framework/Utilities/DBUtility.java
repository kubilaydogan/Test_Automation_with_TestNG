package Framework.Utilities;

import Framework.Core.ConfigurationReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtility {

    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;

    // our methods\\
    // 1. establishConnection(ORACLE)
    // 2. getRowsCount (String sql)
    // 3. runSQLQuery (String sql)

    public static void establishConnection(DBType dbType) throws SQLException {
        switch(dbType){
            case ORACLE:
                connection = DriverManager.getConnection(ConfigurationReader.get("oracledb.url"),
                        ConfigurationReader.get("oracledb.username"),
                        ConfigurationReader.get("oracledb.password"));
                break;
            default:
                connection = null;
        }
    }

    public static int getRowsCount(String sql) throws SQLException {
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultSet = statement.executeQuery(sql);
        resultSet.last();
        return resultSet.getRow();
    }

    public static List<Map<String, Object>> runSQLQuery(String sql) throws SQLException {
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultSet = statement.executeQuery(sql);

        List<Map<String, Object>> list = new ArrayList<>();
        ResultSetMetaData rsMdata = resultSet.getMetaData();
        int c = rsMdata.getColumnCount();

        while(resultSet.next()){
            Map<String, Object> rowMap = new HashMap<>();

            for(int col=1;col<=c; col++){                 // DIKKAT: 1'DEN BASLAR   // her satirdaki hucreleri teker teker map'e atiyoruz
                rowMap.put(rsMdata.getColumnName(col), resultSet.getObject(col));
            }
            list.add(rowMap);
        }
        return list;
    }

    public static void closeConnections() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}


