<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="java.sql.*" %>
<HTML>
<BODY>
  <% //获取提交的学号和密码：
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
       catch(ClassNotFoundException event){out.print("连接数据库错误-1");}
       try {con=DriverManager.getConnection("jdbc:sqlserver://182.254.201.74:1433;DatabaseName=SQLS2345","sa","admins");
       sql=con.createStatement();
       String condition1="UPDATE 注册登录 SET 密码='"+password+"' WHERE 学号='"+number+"'";
       //执行更新操作：
       sql.executeUpdate(condition1); 
       //返回到updatereg.jsp
      response.sendRedirect("updatereg.jsp"); 
      con.close();
     }
    catch(SQLException e){ out.print("访问数据库错误-2");}
 %>
</FONT>
</BODY>
</HTML>