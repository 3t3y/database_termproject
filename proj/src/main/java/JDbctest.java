import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDbctest {


    public static void query(String tableName, String columnName, String conditionValue) {
        // JDBC连接参数
        String url = "jdbc:mysql://localhost:3306/kiosksystem?serverTimezone=Asia/Seoul";
        String user = "root";
        String password = "zf980618";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // 表名和对应的查询条件
            Map<String, Object[]> tableQueries = new HashMap<>();
            tableQueries.put(tableName, new Object[]{columnName, conditionValue});

            // 添加更多的表和对应的查询条件

            // 查询数据
            queryDataInTables(connection, tableQueries);

            System.out.println("成功从多个表中查询数据。");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void queryDataInTables(Connection connection, Map<String, Object[]> tableQueries) throws SQLException {
        for (Map.Entry<String, Object[]> entry : tableQueries.entrySet()) {
            String tableName = entry.getKey();
            Object[] queryConditions = entry.getValue();
            queryDataInTable(connection, tableName, queryConditions);
        }
    }
    public static void queryDataInTable(Connection connection, String tableName, Object... queryConditions) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        query.append(tableName).append(" WHERE ").append(queryConditions[0]).append(" = ?");

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            // 设置查询条件值
            preparedStatement.setObject(1, queryConditions[1]);

            // 执行查询语句
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // 处理查询结果
                while (resultSet.next()) {

                   String resultValue = resultSet.getString("column_name");


                }
            }
        }
    }
    public static void InsertQuery(String tablename,String primarykey) {
        // JDBC连接参数
        String url = "jdbc:mysql://localhost:3306/kiosksystem?serverTimezone=Asia/Seoul";
        String user = "root";
        String password = "zf980618";


        try  {
            Connection connection = DriverManager.getConnection(url, user, password);
            // 表名和对应的数据
            Map<String, Object[]> tableData = new HashMap<>();
            tableData.put(tablename, new Object[]{primarykey});

            // 添加更多的表和对应的数据

            // 插入数据
            insertDataIntoTables(connection, tableData);

            System.out.println("Insert sucessfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertDataIntoTables(Connection connection, Map<String, Object[]> tableData) throws SQLException {
        for (Map.Entry<String, Object[]> entry : tableData.entrySet()) {
            String tableName = entry.getKey();
            Object[] data = entry.getValue();
            insertDataIntoTable(connection, tableName, data);
        }
    }

    private static void insertDataIntoTable(Connection connection, String tableName, Object[] data) throws SQLException {
        StringBuilder insertQuery = new StringBuilder("INSERT INTO ");
        insertQuery.append(tableName).append(" (");

        for (int i = 1; i <= data.length; i++) {
            insertQuery.append("column").append(i);
            if (i < data.length) {
                insertQuery.append(", ");
            }
        }

        insertQuery.append(") VALUES (");

        for (int i = 0; i < data.length; i++) {
            insertQuery.append("?");
            if (i < data.length - 1) {
                insertQuery.append(", ");
            }
        }

        insertQuery.append(")");

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery.toString())) {
            // 设置值
            for (int i = 0; i < data.length; i++) {
                preparedStatement.setObject(i + 1, data[i]);
            }

            // 执行插入语句
            preparedStatement.executeUpdate();
        }
    }
  public  static void update(String tablename,String primarykey){
      String url = "jdbc:mysql://localhost:3306/kiosksystem?serverTimezone=Asia/Seoul";
      String user = "root";
      String password = "zf980618";

      try (Connection connection = DriverManager.getConnection(url, user, password)) {
          // 表名和对应的更新数据
          Map<String, Object[]> tableUpdates = new HashMap<>();
          tableUpdates.put(tablename, new Object[]{primarykey});

          // 添加更多的表和对应的更新数据

          // 更新数据
          updateDataInTables(connection, tableUpdates);

          System.out.println("成功更新多个表的数据。");
      } catch (SQLException e) {
          e.printStackTrace();
      }
  }

    private static void updateDataInTables(Connection connection, Map<String, Object[]> tableUpdates) throws SQLException {
        for (Map.Entry<String, Object[]> entry : tableUpdates.entrySet()) {
            String tableName = entry.getKey();
            Object[] updateData = entry.getValue();
            updateDataInTable(connection, tableName, updateData);
        }
    }

    private static void updateDataInTable(Connection connection, String tableName, Object[] updateData) throws SQLException {
        StringBuilder updateQuery = new StringBuilder("UPDATE ");
        updateQuery.append(tableName).append(" SET ");

        for (int i = 1; i <= updateData.length; i++) {
            updateQuery.append("column").append(i).append(" = ?");
            if (i < updateData.length) {
                updateQuery.append(", ");
            }
        }

        // Add your condition for updating, e.g., WHERE id = ?
        updateQuery.append(" WHERE id = ?");

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery.toString())) {
            // Set values for update
            for (int i = 0; i < updateData.length; i++) {
                preparedStatement.setObject(i + 1, updateData[i]);
            }

            // Set the condition value, e.g., ID
            preparedStatement.setInt(updateData.length + 1, 123);

            // Execute the update statement
            preparedStatement.executeUpdate();
        }
  }
  public static void Delete(String tablename,String primarykey ){

          // JDBC连接参数
      String url = "jdbc:mysql://localhost:3306/kiosksystem?serverTimezone=Asia/Seoul";
      String user = "root";
      String password = "zf980618";

          try (Connection connection = DriverManager.getConnection(url, user, password)) {
              // 表名和对应的删除条件
              Map<String, Object[]> tableDeletes = new HashMap<>();
              tableDeletes.put(tablename, new Object[]{primarykey});

              // 添加更多的表和对应的删除条件

              // 删除数据
              deleteDataInTables(connection, tableDeletes);

              System.out.println("成功从多个表中删除数据。");
          } catch (SQLException e) {
              e.printStackTrace();
          }
      }

      public static void deleteDataInTables(Connection connection, Map<String, Object[]> tableDeletes) throws SQLException {
          for (Map.Entry<String, Object[]> entry : tableDeletes.entrySet()) {
              String tableName = entry.getKey();
              Object[] deleteConditions = entry.getValue();
              deleteDataInTable(connection, tableName, deleteConditions);
          }
      }

      public static void deleteDataInTable(Connection connection, String tableName, Object... deleteConditions) throws SQLException {

          StringBuilder deleteQuery = new StringBuilder("DELETE FROM ");
          deleteQuery.append(tableName).append(" WHERE id = ?");  // 请根据实际情况修改删除条件

          try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery.toString())) {
              // 设置删除条件值
              for (int i = 0; i < deleteConditions.length; i++) {
                  preparedStatement.setObject(i + 1, deleteConditions[i]);
              }

              // 执行删除语句
              preparedStatement.executeUpdate();
          }

  }

}

