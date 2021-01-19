package cn.itcast.jdbc;

import java.sql.*;

public class jdbcDemo {
    public static void main(String[] args)  {

        Connection conn=null;
        Statement stmt=null;
        ResultSet re=null;

        try {
            //1,注册驱动
            Class.forName("com.mysql.jdbc.Driver");//在调用函数static void registerDriver(Driver driver)
            //2,定义SQL
            //DML句子
            //String sql="insert into stu value(3,'fu',22,null,null,null,null)";//执行语句,增
            //String sql="update stu set age=18 where id=3";//改
            //String sql="delete from stu where id=3";//删除
            //DLL句子
            //String sql="create table student (id int, name varchar(20))";
            //DQL语句
            String sql="select * from stu";

            //3,获取connection对象
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/db1","root","root");
            //jdbc:mysql://ip地址(域名):端口号/数据库名称
            //4，获取statement用来执行sql
            stmt=conn.createStatement();
            //5,执行sql
            //int count=stmt.executeUpdate(sql);
            re=stmt.executeQuery(sql);

            while(re.next()){
                int id=re.getInt("id");
                String name=re.getString("name");
                int age=re.getInt("age");
                System.out.println(id+name+age);
            }


            /*re.next();//光标向下移动
           int id=re.getInt("id");
           String name=re.getString("name");
           int age=re.getInt("age");

            System.out.println(id+name+age);*/




            /*//6,处理结果
            System.out.println(count);
            if(count>0){
                System.out.println("修改成功");
            }else{
                System.out.println("修改失败");
            }*/

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {

            if(re!=null){
                try {
                    re.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if(stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if(conn!=null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }
}
