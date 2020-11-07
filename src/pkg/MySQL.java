package pkg;

import java.sql.*;
import com.mysql.jdbc.*;

public class MySQL {
   static final Connection CONN = connect();

   static Connection connect() {
      Connection conn = null;
      String url = "jdbc:mysql://localhost/bensin?serverTimezone=UTC";

      try {
         conn = DriverManager.getConnection(url, "root", "");
         System.out.println("Connection enstablished");
      } catch (Exception e) {
         e.printStackTrace();
      }
      return conn;
   }

   static void executeUpdateQuerry(String querry) {
      try {
         Statement stt = CONN.createStatement();
         stt.executeUpdate(querry);

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   static ResultSet executeReadQuerry(String querry) {
      ResultSet rs = null;
      try {
         Statement stt = CONN.createStatement();
         rs = stt.executeQuery(querry);

      } catch (Exception e) {
         e.printStackTrace();
      }
      return rs;
   }

   static void insertUsingPre(String nim, String nama) {
      String q = "insert into mahasiswa values (?,?)";
      try {
         PreparedStatement stt = CONN.prepareStatement(q);
         stt.setString(1, nim);
         stt.setString(2, nama);
         stt.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public static void main(String[] args) {
      
   }
}
