<%@ page contentType="text/html;charset=GBK"%>
<%@ page
    import="org.jfree.chart.*,org.jfree.chart.plot.PiePlot,
     org.jfree.data.general.DefaultPieDataset,
     org.jfree.chart.servlet.ServletUtilities,
     java.awt.*,org.jfree.chart.title.TextTitle"%>
<%@ page
    import="org.jfree.chart.labels.StandardPieSectionLabelGenerator"%>
<%@ page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page import="java.sql.*" %>
<html>
     <head>
         <title>毕业学分表数据的统计处理（饼图）</title>
     </head>
<%
  %>
     <body>
        <center> <img src="<%=graphURL%>" width=500 height=300 border=0
             usemap="#<%= filename %>"></center>
     </body>
 <%} catch(SQLException event){ out.print("访问数据库错误-2");} %>
 </html>