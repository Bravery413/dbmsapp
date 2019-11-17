package com.gdufe.dbmsapp.controller;

import com.sun.deploy.net.HttpResponse;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.Date;

/**
 * @author: Bravery
 * @create: 2019-11-16 23:50
 **/

@Controller
public class LoginController {

    @PostMapping("/login")
    public String Login(String number, String password, Model m) throws SQLException {
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
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=stud1181", "root", "123456");
            sql = con.createStatement();
            String condition = null;
            condition = "SELECT * FROM 注册登录 WHERE 学号='" + number + "' AND 密码='" + password + "'";
            rs = sql.executeQuery(condition);
            int flag = 0;
            while (rs.next()) {
                flag = 1;
            }
            if (flag == 1) {
                html = "<a href='main1287.html' target='_parent'>账号正确进入【主功能】页面</a>";
            } else {
                html = "<a href='login.html'>账号错误111，重新【登录】</a>";
            }
            con.close();
        } catch (SQLException event) {
            html = "访问数据库错误-2";
        }
        m.addAttribute("html", html);
        return "login";
    }

    @PostMapping("/byxf01")
    public String add(String t1, String t2, Model m) throws SQLException {
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
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=stud1181;useUnicode=true;characterEncoding=UTF-8", "root", "123456");
            sql = con.createStatement();
            String condition = "INSERT INTO 毕业学分 VALUES('" + t1 + "'," + t2 + ")";
            sql.executeUpdate(condition); //执行添加操作：
            html = "成功de增加了一笔记录";
            con.close();
        } catch (SQLException event) {
            html = "访问数据库错误-2，该课程类别已经被录入或数据填写超过8个字符。";
        }
        m.addAttribute("html", html);
        return "byxf01";
    }

    @PostMapping("/byxf04")
    public String search(String t1, Model m) throws SQLException, UnsupportedEncodingException {
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
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=stud1181", "sa", "admins");
            sql = con.createStatement();
            String condition = null;
            condition = "SELECT * FROM 毕业学分 WHERE 课程类别 like '%" + t1 + "%'";
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


    @PostMapping("/piechartbyxf")
    public String caculate(String t1, Model m) throws SQLException, UnsupportedEncodingException {
//        Connection con=null;
//        Statement sql=null;
//        ResultSet rs=null;
//        try{Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");}
//        catch(ClassNotFoundException e){out.print("连接数据库错误-1");}
//        try {
//            con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=stud1181","sa","admins");
//            sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            String condition="select count(课程类别) from 毕业学分";
//            rs=sql.executeQuery(condition);
//            int nn=1;
//            while(rs.next()){
//                nn=rs.getInt(1);
//            }
//
//            condition="select count(课程类别) from 毕业学分  group by 课程类别";
//            rs=sql.executeQuery(condition);
//            int m1=0;
//            while(rs.next()){
//                m1=m1+1;
//            }
//            String[] tlTitle=new String[m1];//动态声明数组：用于存储图例标题
//            double[] tldatas=new double[m1];//动态声明数组：用于存储图例标题对应的数据
//            condition="select 课程类别,SUM(最低学分) from 毕业学分  group by 课程类别";
//            rs=sql.executeQuery(condition);
//            int i=0;
//            while(rs.next()){
//                tlTitle[i]=rs.getString(1);//更新图例标题
//                tldatas[i]=rs.getInt(2)*1.0/nn;   //更新图例标题对应的数据
//                i++;
//            }
//            con.close();
//
//            //设置数据集
//            DefaultPieDataset dataset = new DefaultPieDataset();
//            for(i=0;i<m1;i++)
//            { dataset.setValue(tlTitle[i],tldatas[i]);}
//
//            //通过工厂类生成JFreeChart对象
//            JFreeChart chart = ChartFactory.createPieChart3D("毕业学分表数据的统计处理",
//                    dataset, true, false, false);
//
//            PiePlot pieplot = (PiePlot) chart.getPlot();
//            DecimalFormat df = new DecimalFormat("0.00%");//获得一个DecimalFormat对象，主要是设置小数问题
//            NumberFormat nf = NumberFormat.getNumberInstance();//获得一个NumberFormat对象
//            StandardPieSectionLabelGenerator sp = new StandardPieSectionLabelGenerator(
//                    "{0}{2}", nf, df);//获得StandardPieSectionLabelGenerator对象
//            pieplot.setLabelGenerator(sp);//设置饼图显示百分比
//
//            //没有数据的时候显示的内容
//            pieplot.setNoDataMessage("无数据显示");
//            pieplot.setCircular(false);
//            pieplot.setLabelGap(0.02D);
//
//            pieplot.setIgnoreNullValues(true);//设置不显示空值
//            pieplot.setIgnoreZeroValues(true);//设置不显示负值
//
//            //标题文字乱码  IT行业职业分布图
//            TextTitle textTitle = chart.getTitle();
//            textTitle.setFont(new Font("宋体", Font.PLAIN, 20));
//
//            //饼上的文字乱码
//            PiePlot plot = (PiePlot) chart.getPlot();
//            plot.setLabelFont(new Font("宋体", Font.PLAIN, 12));
//
//            //图例文字乱码 饼图下面的5个说明
//            chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
//
//            String filename = ServletUtilities.saveChartAsPNG(chart, 500, 300,
//                    null, session);
//            String graphURL = request.getContextPath()+"/servlet/DisplayChart?filename="+filename;
//
//            return "piechartbyxf";
        return null;
    }

    @GetMapping("/deletebyxf031")
    public String delete(Model m) throws SQLException, UnsupportedEncodingException {
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
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=stud1181", "root", "123456");
            sql = con.createStatement();
            rs = sql.executeQuery("SELECT * FROM 毕业学分");
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

    @PostMapping("/deletebyxf032")
    public ModelAndView test(String t1, ModelAndView m) throws SQLException, UnsupportedEncodingException {
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
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=stud1181", "root", "123456");
            sql = con.createStatement();
            String condition1 = "DELETE 毕业学分  WHERE 课程类别='" + t1 + "'";
            //执行更新操作：
            sql.executeUpdate(condition1);
            con.close();
        } catch (SQLException e) {
            html = "访问数据库错误-2";
        }
        m.setViewName("redirect:/deletebyxf031");
        return m;
    }

    @GetMapping("/updatebyxf021")
    public ModelAndView edit(ModelAndView m) throws SQLException, UnsupportedEncodingException {
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
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=stud1181", "sa", "admins");
            sql = con.createStatement();
            rs = sql.executeQuery("SELECT * FROM 毕业学分");
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
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=stud1181", "sa", "admins");
            sql = con.createStatement();
            String condition1 = "UPDATE 毕业学分 SET 最低学分=" + t2 + " WHERE 课程类别='" + t1 + "'";
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
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=stud1181;useUnicode=true;characterEncoding=UTF-8", "root", "123456");
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
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=stud1181;useUnicode=true;characterEncoding=UTF-8", "root", "123456");
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

}


