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
         <title>��ҵѧ�ֱ����ݵ�ͳ�ƴ�����ͼ��</title>
     </head>
<%
  %>
     <body>
        <center> <img src="<%=graphURL%>" width=500 height=300 border=0
             usemap="#<%= filename %>"></center>
     </body>
 <%} catch(SQLException event){ out.print("�������ݿ����-2");} %>
 </html>