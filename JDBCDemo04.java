package cn.itcast.jdbc;

import util.JDBCUtil;

import java.sql.*;
import java.util.Scanner;

public class JDBCDemo04 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username=sc.nextLine();
        System.out.println("请输入密码：");
        String password=sc.nextLine();

        boolean b=new JDBCDemo04().login(username,password);

        if(b){
            System.out.println("登录成功！");
        }else{
            System.out.println("用户名或密码错误");
        }
    }
    public boolean login(String username,String password){
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            conn= JDBCUtil.getConnection();
            //String sql="select * from user where username='”+username“' and password='”+password“'";
            //String sql = "select * from user where username = '"+username+"' and password = '"+password+"' ";
            String sql = "select * from user where username = ? and password = ? ";
            //stmt=conn.createStatement();
            stmt=conn.prepareStatement(sql);
            stmt.setString(1,username);
            stmt.setString(2,password);
            rs=stmt.executeQuery();
            return rs.next();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.close(rs,stmt,conn);
        }
        return false;//出现异常就返回false
    }
}
