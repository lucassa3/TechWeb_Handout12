<%@ page import="java.util.*,br.edu.insper.model.*,java.io.*,java.text.ParseException,java.text.SimpleDateFormat" %>
		
<body>	
<% 
DAO dao = new DAO();		
dao.remove(request.getParameter("nome"));
dao.close();
%>
	usuario ${param.nome} removido
</body>