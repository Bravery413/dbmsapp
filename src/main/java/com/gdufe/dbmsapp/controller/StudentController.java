package com.gdufe.dbmsapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import java.io.UnsupportedEncodingException;
import java.sql.*;

/**
 * @author: Bravery
 * @create: 2019-11-23 13:37
 **/

@Controller
public class StudentController {
    @PostMapping("/addStudent")
    public String addStudent(String id, String name, String sex, String date, String type, String home, String schoolClass, Model m) throws SQLException {
        Connection con = null;
        Statement sql = null;
        ResultSet rs = null;
        String html;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException event) {
            html = "连接数据库错误-1";
        }
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://182.254.201.74:1433;DatabaseName=SQLS2345;useUnicode=true;characterEncoding=UTF-8", "sa", "Py123456");
            sql = con.createStatement();
            String condition = "INSERT INTO 学生 VALUES('" + id + "','" + name + "','" + sex + "','" + date + "','" + type + "','" + home + "','" + schoolClass + "')";
            sql.executeUpdate(condition); //执行添加操作：
            html = "成功de增加了一笔记录";
            con.close();
        } catch (SQLException event) {
            html = "访问数据库错误-2，该课程类别已经被录入或数据填写超过8个字符。";
        }
        m.addAttribute("html", html);
        return "byxf01";
    }

    /**
     * 编辑
     * @param m
     * @return
     */
    @GetMapping("/editStudent")
    public ModelAndView editStudent(ModelAndView m) {
        Connection con = null;
        Statement sql = null;
        ResultSet rs = null;
        StringBuffer html = new StringBuffer();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException event) {
            html.append("连接数据库错误-1");
        }
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://182.254.201.74:1433;DatabaseName=SQLS2345", "sa", "Py123456");
            sql = con.createStatement();
            rs = sql.executeQuery("SELECT * FROM 学生");
            html.append("<center>针对每条记录，按照“课程”修改“课时”</center><Table border='6' align='center'>");

            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String sex = rs.getString(3);
                String date = rs.getString(4);
                String type = rs.getString(5);
                String home = rs.getString(6);
                String schoolClass = rs.getString(7);

                html.append("<tr><form id='form1' name='form1' method='post' action='/updateStudent'>" +
                        "<TD >学号：<label><input name='id' type='hidden' value='" + id + "' /></label>" + id + "</TD>" +
                        "<TD >姓名：<label><input name='name' type='hidden' value='" + name + "' /></label>" + name + "</TD>" +
                        "<TD >性别：<label><input name='sex' type='hidden' value='" + sex + "' /></label>" + sex + "</TD>" +
                        "<TD >出生日期：<label><input name='date' type='hidden' value='" + date + "' /></label>" + date + "</TD>" +
                        "<TD >籍贯：<label><input name='type' type='hidden' value='" + type + "' /></label>" + type + "</TD>" +
                        "<TD >家庭住址：<label><input name='home' type='hidden' value='" + home + "' /></label>" + home + "</TD>" +
                        "<TD >班级：<label><input name='schoolClass1' type='hidden' value='" + schoolClass + "' /></label>" + schoolClass + "</TD>");

                html.append("<TD >班级：<label><input name='schoolClass' type='text' id='time' value='" + schoolClass + "' size='20' /></label></TD>");
                html.append("<TD ><br><label><input type='submit' name='Submit' value='修改课时并提交' /></label></form></tr>");
            }
            html.append("</Table>");
            con.close();
        } catch (SQLException e1) {
            html.append("访问数据库错误-2");
        }
        m.addObject("html", html);
        m.setViewName("updatebyxf021");
        return m;
    }

    /**
     * 更新
     * @param id
     * @param schoolClass
     * @param m
     * @return
     * @throws SQLException
     * @throws UnsupportedEncodingException
     */
    @PostMapping("/updateStudent")
    public ModelAndView updateStudent(String id, String schoolClass, ModelAndView m) throws SQLException, UnsupportedEncodingException {

        System.out.println(111);
        Connection con = null;
        PreparedStatement preparedStatement;
        ResultSet rs = null;
        String html = "";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException event) {
            html = "连接数据库错误-1";
        }
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://182.254.201.74:1433;DatabaseName=SQLS2345", "sa", "Py123456");
            String condition = "UPDATE 学生 SET 班级=? where 学号=?";
            preparedStatement = con.prepareStatement(condition);
            preparedStatement.setString(1, schoolClass);
            preparedStatement.setString(2, id);
            //执行更新操作：
            preparedStatement.executeUpdate();
            con.close();
        } catch (SQLException e) {
            html = "访问数据库错误-2";
        }

        m.addObject("html", html);
        m.setViewName("redirect:/editStudent");
        return m;
    }


    /**
     * 删除编辑
     * @param m
     * @return
     */
    @GetMapping("/chooseStudent")
    public String choose(Model m){
        Connection con;
        Statement sql;
        ResultSet rs;
        StringBuffer html = new StringBuffer();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException event) {
            html.append("连接数据库错误-1");
        }
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://182.254.201.74:1433;DatabaseName=SQLS2345", "sa", "Py123456");
            sql = con.createStatement();
            rs = sql.executeQuery("SELECT * FROM 学生");
            html.append("<center>针对每条记录，按照“课程类别”修改“最低学分”</center><Table border='6' align='center'>");

            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String sex = rs.getString(3);
                String date = rs.getString(4);
                String type = rs.getString(5);
                String home = rs.getString(6);
                String schoolClass = rs.getString(7);

                html.append("<tr><form id='form1' name='form1' method='post' action='/deleteStudent'>" +
                        "<TD >学号：<label><input name='id' type='hidden' value='" + id + "' /></label>" + id + "</TD>" +
                        "<TD >姓名：<label><input name='name' type='hidden' value='" + name + "' /></label>" + name + "</TD>" +
                        "<TD >性别：<label><input name='sex' type='hidden' value='" + sex + "' /></label>" + sex + "</TD>" +
                        "<TD >出生日期：<label><input name='date' type='hidden' value='" + date + "' /></label>" + date + "</TD>" +
                        "<TD >籍贯：<label><input name='type' type='hidden' value='" + type + "' /></label>" + type + "</TD>" +
                        "<TD >家庭住址：<label><input name='home' type='hidden' value='" + home + "' /></label>" + home + "</TD>" +
                        "<TD >班级：<label><input name='schoolClass1' type='hidden' value='" + schoolClass + "' /></label>" + schoolClass + "</TD>");

                html.append("<TD ><br><label><input type='submit' name='Submit' value='删除' /></label></form></tr>");
            }
            html.append("</Table>");
            con.close();
        } catch (SQLException e1) {
            html.append("访问数据库错误-2");
        }
        m.addAttribute("html", html);
        return "deletebyxf031";
    }

    /**
     * 删除
     * @param id
     * @param m
     * @return
     */
    @PostMapping("/deleteStudent")
    public ModelAndView delete(String id, ModelAndView m) {
        Connection con = null;
        Statement sql = null;
        ResultSet rs = null;
        String html;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException event) {
            html = "连接数据库错误-1";
        }
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://182.254.201.74:1433;DatabaseName=SQLS2345", "sa", "Py123456");
            sql = con.createStatement();
            String condition1 = "DELETE 学生  WHERE 学号='" + id + "'";
            //执行更新操作：
            sql.executeUpdate(condition1);
            con.close();
        } catch (SQLException e) {
            html = "访问数据库错误-2";
        }
        m.setViewName("redirect:/chooseStudent");
        return m;
    }


}
