<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="java.sql.*" %>
<HTML>
<BODY>
 <% Connection con;
    Statement sql; 
    ResultSet rs;
       try{Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); }
       catch(ClassNotFoundException event){out.print("连接数据库错误-1");}
    try {  con=DriverManager.getConnection("jdbc:sqlserver://182.254.201.74:1433;DatabaseName=SQLS2345","sa","admins");
         sql=con.createStatement();
         rs=sql.executeQuery("SELECT * FROM 注册登录");
         out.print("针对每行账号，按照“学号”修改“密码”<Table Border>"); 
         String tmp="";
       while(rs.next())
       { tmp=rs.getString(1);
         out.print("<tr><form id='form1' name='form1' method='post' action='updatepwd.jsp'><TD >学号：<label><input name='t1' type='hidden' value='"+tmp+"' /></label>"+tmp+"</TD>"); 
         out.print("<TD >密码：<label><input name='t2' type='text' id='t2' value='"+rs.getString(2)+"' size='20' /></label></TD>");
         out.print("<TD ><br><label><input type='submit' name='Submit' value='修改密码并提交' /></label></form></tr>"); 

         out.print("<tr><form id='form2' name='form2' method='post' action='deletereg.jsp'><TD >学号：<label><input name='t1' type='hidden' value='"+tmp+"' /></label>"+tmp+"</TD>"); 
         out.print("<TD >密码：<label><input name='t2' type='text' id='t2' value='"+rs.getString(2)+"' size='20' /></label></TD>");
         out.print("<TD ><br><label><input type='submit' name='Submit' value='删除该账号' /></label></form></tr>");
        }
        out.print("</Table>");
        con.close();
     }
   catch(SQLException e1) {out.print("访问数据库错误-2"); }
 %>
</BODY>
</HTML>