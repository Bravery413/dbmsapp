<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="java.sql.*" %>
<HTML>
<BODY>
  <% //��ȡ�ύ��ѧ�ź����룺
    String number=request.getParameter("t1");
          if(number==null){number="";}
     //byte b1[]=number.getBytes("ISO-8859-1");
     //number=new String(b1);
     //number="1";
    String password=request.getParameter("t2");
          if(password==null){password="";}
     //byte b2[]=password.getBytes("ISO-8859-1");
     //password=new String(b2);

    Connection con=null;
    Statement sql=null;
    ResultSet rs=null;
       try{Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); }
       catch(ClassNotFoundException event){out.print("�������ݿ����-1");}
       try {con=DriverManager.getConnection("jdbc:sqlserver://182.254.201.74:1433;DatabaseName=SQLS2345","sa","admins");
       sql=con.createStatement();
       String condition1="UPDATE ע���¼ SET ����='"+password+"' WHERE ѧ��='"+number+"'";
       //ִ�и��²�����
       sql.executeUpdate(condition1); 
       //���ص�updatereg.jsp
      response.sendRedirect("updatereg.jsp"); 
      con.close();
     }
    catch(SQLException e){ out.print("�������ݿ����-2");}
 %>
</FONT>
</BODY>
</HTML>