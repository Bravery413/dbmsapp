package com.gdufe.dbmsapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import java.sql.*;

/**
 * @author: Bravery
 * @create: 2019-11-16 23:50
 **/

@Controller
public class LoginController {

    /**
     * 登录
     * @param number
     * @param password
     * @param m
     * @return
     * @throws SQLException
     */
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
            con = DriverManager.getConnection("jdbc:sqlserver://182.254.201.74:1433;DatabaseName=SQLS2345", "sa", "Py123456");
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


    @PostMapping("/register")
    public String register(String number, String password, Model m) throws SQLException {
        Connection con = null;
        Statement sql = null;
        ResultSet rs = null;
        String html;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException event) {
            html="连接数据库错误-1";
        }
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://182.254.201.74:1433;DatabaseName=SQLS2345", "sa", "Py123456");
            sql = con.createStatement();
            String condition = "INSERT INTO 注册登录 VALUES('" + number + "','" + password + "')";
            sql.executeUpdate(condition); //执行添加操作：
            html ="注册成功，可以利用该账号登录系统啦。";
            con.close();
        } catch (SQLException event) {
            html="访问数据库错误-2，该学号已经被注册或数据填写太多。";
        }
        m.addAttribute("html", html);
        return "register";

    }



    /**
     * 饼图数据统计
     * @param t1
     * @param m
     * @return
     */
    @PostMapping("/piechartbyxf")
    public String caculate(String t1, Model m){
//        Connection con=null;
//        Statement sql=null;
//        ResultSet rs=null;
//        try{Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");}
//        catch(ClassNotFoundException e){out.print("连接数据库错误-1");}
//        try {
//            con=DriverManager.getConnection("jdbc:sqlserver://182.254.201.74:1433;DatabaseName=SQLS2345","sa","admins");
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


}


