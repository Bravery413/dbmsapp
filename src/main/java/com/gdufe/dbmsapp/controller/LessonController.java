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
 * @create: 2019-11-23 13:40
 **/

@Controller
public class LessonController {

    /**
     * 增加
     * @param cid
     * @param name
     * @param time
     * @param credit
     * @param type
     * @param m
     * @return
     * @throws SQLException
     */
    @PostMapping("/addLesson")
    public String addLesson(String cid, String name, String time, String credit, String type, Model m) throws SQLException {
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
            String condition = "INSERT INTO 课程 VALUES('" + cid + "','" + name + "','" + time + "','" + credit + "','" + type + "')";
            sql.executeUpdate(condition); //执行添加操作：
            html = "成功de增加了一笔记录";
            con.close();
        } catch (SQLException event) {
            html = "学号或者课程编号有错。";
        }
        m.addAttribute("html", html);
        return "byxf01";
    }



    /**
     * 列表查询
     * @param m
     * @return
     */
    @GetMapping("/editLesson")
    public ModelAndView editLesson(ModelAndView m) {
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
            rs = sql.executeQuery("SELECT * FROM 课程");
            html.append("<center>针对每条记录，按照“课程”修改“课时”</center><Table border='6' align='center'>");
            String cid = "";
            String name = "";
            String time = "";
            String credit = "";
            String type = "";
            while (rs.next()) {
                cid = rs.getString(1);
                name = rs.getString(2);
                time = rs.getString(3);
                credit = rs.getString(4);
                type = rs.getString(5);
                html.append("<tr><form id='form1' name='form1' method='post' action='/updateLesson'>" +
                        "<TD >课程编号：<label><input name='cid' type='hidden' value='" + cid + "' /></label>" + cid + "</TD>" +
                        "<TD >课程名称：<label><input name='name' type='hidden' value='" + name + "' /></label>" + name + "</TD>" +
                        "<TD >课时：<label><input name='time1' type='hidden' value='" + time + "' /></label>" + time + "</TD>" +
                        "<TD >学分：<label><input name='credit1' type='hidden' value='" + credit + "' /></label>" + credit + "</TD>" +
                        "<TD >课程类别：<label><input name='type' type='hidden' value='" + type + "' /></label>" + type + "</TD>");

                html.append("<TD >课时：<label><input name='time' type='text' id='time' value='" + time + "' size='20' /></label></TD>");
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
     * @param cid
     * @param time
     * @param m
     * @return
     * @throws SQLException
     * @throws UnsupportedEncodingException
     */
    @PostMapping("/updateLesson")
    public ModelAndView updateLesson(String cid, String time, ModelAndView m) throws SQLException, UnsupportedEncodingException {
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
            String condition = "UPDATE 课程 SET 课时=? where 课程编号=?";
            preparedStatement = con.prepareStatement(condition);
            preparedStatement.setString(1, time);
            preparedStatement.setString(2, cid);
            //执行更新操作：
            preparedStatement.executeUpdate();
            con.close();
        } catch (SQLException e) {
            html = "访问数据库错误-2";
        }

        m.addObject("html", html);
        m.setViewName("redirect:/editLesson");
        return m;
    }

    /**
     * 删除编辑
     * @param m
     * @return
     */
    @GetMapping("/chooseLesson")
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
            rs = sql.executeQuery("SELECT * FROM 课程");
            html.append("<center>针对每条记录，按照“课程类别”修改“最低学分”</center><Table border='6' align='center'>");
            String cid = "";
            String name = "";
            String time = "";
            String credit = "";
            String type = "";
            while (rs.next()) {
                cid = rs.getString(1);
                name = rs.getString(2);
                time = rs.getString(3);
                credit = rs.getString(4);
                type = rs.getString(5);
                html.append("<tr><form id='form1' name='form1' method='post' action='/deleteLesson'>" +
                        "<TD >课程编号：<label><input name='cid' type='hidden' value='" + cid + "' /></label>" + cid + "</TD>" +
                        "<TD >课程名称：<label><input name='name' type='hidden' value='" + name + "' /></label>" + name + "</TD>" +
                        "<TD >课时：<label><input name='time1' type='hidden' value='" + time + "' /></label>" + time + "</TD>" +
                        "<TD >学分：<label><input name='credit1' type='hidden' value='" + credit + "' /></label>" + credit + "</TD>" +
                        "<TD >课程类别：<label><input name='type' type='hidden' value='" + type + "' /></label>" + type + "</TD>");
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
     * @param cid
     * @param m
     * @return
     */
    @PostMapping("/deleteLesson")
    public ModelAndView delete(String cid, ModelAndView m) {
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
            String condition1 = "DELETE 课程  WHERE 课程编号='" + cid + "'";
            //执行更新操作：
            sql.executeUpdate(condition1);
            con.close();
        } catch (SQLException e) {
            html = "访问数据库错误-2";
        }
        m.setViewName("redirect:/chooseLesson");
        return m;
    }

    /**
     * 查询
     * @param t1
     * @param m
     * @return
     */
    @PostMapping("/searchLesson")
    public String search(String t1, Model m){
        Connection con = null;
        Statement sql = null;
        ResultSet rs = null;
        StringBuffer html = new StringBuffer();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException event) {
            html.append("访问数据库错误-1");
        }
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://182.254.201.74:1433;DatabaseName=SQLS2345", "sa", "Py123456");
            sql = con.createStatement();
            String condition = null;
            condition = "SELECT * FROM 课程 WHERE 课程名称 like '%" + t1 + "%'";
            rs = sql.executeQuery(condition);
            html.append("<table width='779' border='6' align='center'>");
            html.append("<tr>");
            html.append("<td>课程编号</td>");
            html.append("<td>课程名称</td>");
            html.append("<td>课时</td>");
            html.append("<td>学分</td>");
            html.append("<td>课程类别</td>");
            html.append("</tr>");
            while (rs.next()) {
                html.append("<tr>");
                html.append("<TD >" + rs.getString(1) + "</TD>");
                html.append("<TD >" + rs.getString(2) + "</TD>");
                html.append("<TD >" + rs.getString(3) + "</TD>");
                html.append("<TD >" + rs.getString(4) + "</TD>");
                html.append("<TD >" + rs.getString(5) + "</TD>");
                html.append("</tr>");
            }
            html.append("</Table>");
            con.close();
            m.addAttribute("html", html);

        } catch (SQLException event) {
            html.append("访问数据库错误-2");
        }
        return "byxf04";
    }


}
