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
 * @create: 2019-11-23 13:54
 **/

@Controller
public class GradeController {

    @PostMapping("/addGrade")
    public String addGrade(String id, String cid, String grade, Model m) throws SQLException {
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
            String condition = "INSERT INTO 学习成绩 VALUES('" + id + "','" + cid + "','" + grade + "')";
            sql.executeUpdate(condition); //执行添加操作：
            html = "成功de增加了一笔记录";
            con.close();
        } catch (SQLException event) {
            html = "学号或者课程编号有错。";
        }
        m.addAttribute("html", html);
        return "byxf01";
    }

    @GetMapping("/editGrade")
    public ModelAndView editGrade(ModelAndView m) {
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
            rs = sql.executeQuery("SELECT * FROM 学习成绩");
            html.append("<center>针对每条记录，按照“学生课程”修改“总评分”</center><Table border='6' align='center'>");
            String sid = "";
            String cid = "";
            String grade = "";
            while (rs.next()) {
                sid = rs.getString(1);
                cid = rs.getString(2);
                grade = rs.getString(3);
                html.append("<tr><form id='form1' name='form1' method='post' action='/updateGrade'>" +
                        "<TD >学号：<label><input name='sid' type='hidden' value='" + sid + "' /></label>" + sid + "</TD>" +
                        "<TD >课程编号：<label><input name='cid' type='hidden' value='" + cid + "' /></label>" + cid + "</TD>" +
                        "<TD >总评分：<label><input name='grade1' type='hidden' value='" + grade + "' /></label>" + grade + "</TD>");

                html.append("<TD >总评分：<label><input name='grade' type='text' id='grade' value='" + rs.getString(3) + "' size='20' /></label></TD>");
                html.append("<TD ><br><label><input type='submit' name='Submit' value='修改总评分并提交' /></label></form></tr>");
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

    @PostMapping("/updateGrade")
    public ModelAndView updateGrade(String sid, String cid, String grade, ModelAndView m) throws SQLException, UnsupportedEncodingException {

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
            String condition = "UPDATE 学习成绩 SET 总评分=? where 学号=? and 课程编号=?";
            preparedStatement = con.prepareStatement(condition);
            preparedStatement.setString(1, grade);
            preparedStatement.setString(2, sid);
            preparedStatement.setString(3, cid);

            //执行更新操作：
            preparedStatement.executeUpdate();
            con.close();
        } catch (SQLException e) {
            html = "访问数据库错误-2";
        }

        m.addObject("html", html);
        m.setViewName("redirect:/editGrade");
        return m;
    }

    /**
     * 删除编辑
     * @param m
     * @return
     */
    @GetMapping("/chooseGrade")
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
            rs = sql.executeQuery("SELECT * FROM 学习成绩");
            html.append("<center>针对每条记录，按照“课程类别”修改“最低学分”</center><Table border='6' align='center'>");
            String tmp = "";
            String sid = "";
            String cid = "";
            String grade = "";
            while (rs.next()) {
                sid = rs.getString(1);
                cid = rs.getString(2);
                grade = rs.getString(3);
                html.append("<tr><form id='form1' name='form1' method='post' action='/deleteGrade'>" +
                        "<TD >学号：<label><input name='sid' type='hidden' value='" + sid + "' /></label>" + sid + "</TD>" +
                        "<TD >课程编号：<label><input name='cid' type='hidden' value='" + cid + "' /></label>" + cid + "</TD>" +
                        "<TD >总评分：<label><input name='grade1' type='hidden' value='" + grade + "' /></label>" + grade + "</TD>");
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
    @PostMapping("/deleteGrade")
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
            String condition1 = "DELETE 学习成绩  WHERE 课程编号='" + cid + "'";
            //执行更新操作：
            sql.executeUpdate(condition1);
            con.close();
        } catch (SQLException e) {
            html = "访问数据库错误-2";
        }
        m.setViewName("redirect:/chooseGrade");
        return m;
    }



    /**
     * 查询
     * @param t1
     * @param m
     * @return
     */
    @PostMapping("/searchGrade")
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
            condition = "SELECT * FROM 学习成绩 WHERE 课程编号 like '%" + t1 + "%'";
            rs = sql.executeQuery(condition);
            html.append("<table width='779' border='6' align='center'>");
            html.append("<tr>");
            html.append("<td>课程编号</td>");
            html.append("<td>成绩</td>");
            html.append("</tr>");
            while (rs.next()) {
                html.append("<tr>");
                html.append("<TD >" + rs.getString(2) + "</TD>");
                html.append("<TD >" + rs.getString(3) + "</TD>");
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
