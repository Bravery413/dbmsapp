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
 * @create: 2019-11-16 19:11
 **/

@Controller
public class PlanController {

    /**
     * 新增
     * @param t1
     * @param t2
     * @param m
     * @return
     */
    @PostMapping("/byxf01")
    public String add(String t1, String t2, Model m) {
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
            String condition = "INSERT INTO 专业计划 VALUES('" + t1 + "'," + t2 + ")";
            sql.executeUpdate(condition); //执行添加操作：
            html = "成功de增加了一笔记录";
            con.close();
        } catch (SQLException event) {
            html = "访问数据库错误111-2，该课程类别已经被录入或数据填写超过8个字符。";
        }
        m.addAttribute("html", html);
        return "byxf01";
    }



    /**
     * 编辑
     * @param m
     * @return
     */
    @GetMapping("/updatebyxf021")
    public ModelAndView edit(ModelAndView m)  {
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
            rs = sql.executeQuery("SELECT * FROM 专业计划");
            html.append("<center>针对每条记录，按照“课程类别”修改“最低学分”</center><Table border='6' align='center'>");
            String tmp = "";
            while (rs.next()) {
                tmp = rs.getString(1);
                html.append("<tr><form id='form1' name='form1' method='post' action='/updatebyxf022'><TD >课程类别：<label><input name='t1' type='hidden' value='" + tmp + "' /></label>" + tmp + "</TD>");
                html.append("<TD >最低学分：<label><input name='t2' type='text' id='t2' value='" + rs.getString(2) + "' size='20' /></label></TD>");
                html.append("<TD ><br><label><input type='submit' name='Submit' value='修改最低学分并提交' /></label></form></tr>");
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
     * @param t1
     * @param t2
     * @param m
     * @return
     * @throws SQLException
     * @throws UnsupportedEncodingException
     */
    @PostMapping("/updatebyxf022")
    public ModelAndView update(String t1, String t2, ModelAndView m) throws SQLException, UnsupportedEncodingException {

        Connection con = null;
        Statement sql = null;
        ResultSet rs = null;
        String html = "";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException event) {
            html = "连接数据库错误-1";
        }
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://182.254.201.74:1433;DatabaseName=SQLS2345", "sa", "admins");
            sql = con.createStatement();
            String condition1 = "UPDATE 专业计划 SET 最低学分=" + t2 + " WHERE 课程类别='" + t1 + "'";
            //执行更新操作：
            sql.executeUpdate(condition1);
            con.close();
        } catch (SQLException e) {
            html = "访问数据库错误-2";
        }

        m.addObject("html", html);
        m.setViewName("redirect:/updatebyxf021");
        return m;
    }


    /**
     * 删除编辑
     * @param m
     * @return
     */
    @GetMapping("/deletebyxf031")
    public String delete(Model m){
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
            rs = sql.executeQuery("SELECT * FROM 专业计划");
            html.append("<center>针对每条记录，按照“课程类别”修改“最低学分”</center><Table border='6' align='center'>");
            String tmp = "";
            while (rs.next()) {
                tmp = rs.getString(1);
                html.append("<tr><form id='form2' name='form2' method='post' action='/deletebyxf032'><TD >课程类别：<label><input name='t1' type='hidden' value='" + tmp + "' /></label>" + tmp + "</TD>");
                html.append("<TD >最低学分：<label><input name='t2' type='text' id='t2' value='" + rs.getString(2) + "' size='20' /></label></TD>");
                html.append("<TD ><br><label><input type='submit' name='Submit' value='删除该记录' /></label></form></tr>");
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
     * @param t1
     * @param m
     * @return
     */
    @PostMapping("/deletebyxf032")
    public ModelAndView test(String t1, ModelAndView m) {
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
            String condition1 = "DELETE 专业计划  WHERE 课程类别='" + t1 + "'";
            //执行更新操作：
            sql.executeUpdate(condition1);
            con.close();
        } catch (SQLException e) {
            html = "访问数据库错误-2";
        }
        m.setViewName("redirect:/deletebyxf031");
        return m;
    }



    /**
     * 查询
     * @param t1
     * @param m
     * @return
     */
    @PostMapping("/byxf04")
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
            con = DriverManager.getConnection("jdbc:sqlserver://182.254.201.74:1433;DatabaseName=SQLS2345", "sa", "admins");
            sql = con.createStatement();
            String condition = null;
            condition = "SELECT * FROM 专业计划 WHERE 课程类别 like '%" + t1 + "%'";
            rs = sql.executeQuery(condition);
            html.append("<table width='779' border='6' align='center'>");
            html.append("<tr>");
            html.append("<td>课程类别</td>");
            html.append("<td>最低学分</td>");
            html.append("</tr>");
            while (rs.next()) {
                html.append("<tr>");
                html.append("<TD >" + rs.getString(1) + "</TD>");
                html.append("<TD >" + rs.getString(2) + "</TD>");
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
