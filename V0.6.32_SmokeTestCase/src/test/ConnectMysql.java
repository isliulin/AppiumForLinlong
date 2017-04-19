package test;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
 
 
public class ConnectMysql {
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        String sql;
        // MySQL��JDBC URL��д��ʽ��jdbc:mysql://�������ƣ����Ӷ˿�/���ݿ������?����=ֵ
        // ������������Ҫָ��useUnicode��characterEncoding
        // ִ�����ݿ����֮ǰҪ�����ݿ����ϵͳ�ϴ���һ�����ݿ⣬�����Լ�����
        // �������֮ǰ��Ҫ�ȴ���javademo���ݿ�
        String url = "jdbc:mysql://192.168.4.124:3306/testlink?"
                + "user=testlink&password=testlink&useUnicode=true&characterEncoding=UTF8";
 
        try {
            // ֮����Ҫʹ������������䣬����ΪҪʹ��MySQL����������������Ҫ��������������
            // ����ͨ��Class.forName�������ؽ�ȥ��Ҳ����ͨ����ʼ������������������������ʽ������
            Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
            // or:
            // com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
            // or��
            // new com.mysql.jdbc.Driver();
            System.out.println("�ɹ�����MySQL��������");
            // һ��Connection����һ�����ݿ�����
            conn = DriverManager.getConnection(url);
            // Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
            Statement stmt = conn.createStatement();
            sql = "select * from users t ";
            ResultSet result = stmt.executeQuery(sql);// executeUpdate���᷵��һ����Ӱ����������������-1��û�гɹ�
            System.out.println("id/tlogin");
            
//            if (result != -1) {
//                System.out.println("�������ݱ�ɹ�");
//                sql = "insert into student(NO,name) values('2012001','��ΰ��')";
//                result = stmt.executeUpdate(sql);
//                sql = "insert into student(NO,name) values('2012002','��С��')";
//                result = stmt.executeUpdate(sql);
//                sql = "select * from student";
//                ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
//                System.out.println("ѧ��\t����");
                while (result.next()) {
                    System.out
                            .println(result. getString(1) + "\t" + result.getString(2));// ��������ص���int���Ϳ�����getInt()
                }
//            }
        } catch (SQLException e) {
            System.out.println("MySQL��������");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
 
    }
 
}
