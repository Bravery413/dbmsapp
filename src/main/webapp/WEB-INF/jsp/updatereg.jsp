<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="java.sql.*" %>
<HTML>
<BODY>
 <% Connection con;
    Statement sql; 
    ResultSet rs;
       try{Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); }
       catch(ClassNotFoundException event){out.print("�������ݿ����-1");}
    try {  con=DriverManager.getConnection("jdbc:sqlserver://182.254.201.74:1433;DatabaseName=SQLS2345","sa","admins");
         sql=con.createStatement();
         rs=sql.executeQuery("SELECT * FROM ע���¼");
         out.print("���ÿ���˺ţ����ա�ѧ�š��޸ġ����롱<Table Border>"); 
         String tmp="";
       while(rs.next())
       { tmp=rs.getString(1);
         out.print("<tr><form id='form1' name='form1' method='post' action='updatepwd.jsp'><TD >ѧ�ţ�<label><input name='t1' type='hidden' value='"+tmp+"' /></label>"+tmp+"</TD>"); 
         out.print("<TD >���룺<label><input name='t2' type='text' id='t2' value='"+rs.getString(2)+"' size='20' /></label></TD>");
         out.print("<TD ><br><label><input type='submit' name='Submit' value='�޸����벢�ύ' /></label></form></tr>"); 

         out.print("<tr><form id='form2' name='form2' method='post' action='deletereg.jsp'><TD >ѧ�ţ�<label><input name='t1' type='hidden' value='"+tmp+"' /></label>"+tmp+"</TD>"); 
         out.print("<TD >���룺<label><input name='t2' type='text' id='t2' value='"+rs.getString(2)+"' size='20' /></label></TD>");
         out.print("<TD ><br><label><input type='submit' name='Submit' value='ɾ�����˺�' /></label></form></tr>");
        }
        out.print("</Table>");
        con.close();
     }
   catch(SQLException e1) {out.print("�������ݿ����-2"); }
 %>
</BODY>
</HTML>