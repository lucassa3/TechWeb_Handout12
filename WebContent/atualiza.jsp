<%@ page import="java.util.*,br.edu.insper.model.*,java.io.*,java.text.ParseException,java.text.SimpleDateFormat" %>


<body>
<%
DAO dao = new DAO();

Pessoas pessoa = new Pessoas();
Passaportes passaporte = new Passaportes();

pessoa.setId(Integer.valueOf(request.getParameter("id")));
pessoa.setNome(request.getParameter("nome"));   
pessoa.setAltura(Double.valueOf(request.getParameter("altura")));

passaporte.setPessoasId(Integer.valueOf(request.getParameter("id")));
passaporte.setPais(request.getParameter("pais"));

String nascimento = request.getParameter("nascimento");
String validade = request.getParameter("validade");
Date dataNasc;
Date dataValid;
try {
	dataNasc = new SimpleDateFormat("yyyy-MM-dd").parse(nascimento);
	Calendar dataNascimento = Calendar.getInstance();
	dataNascimento.setTime(dataNasc);
	pessoa.setNascimento(dataNascimento);
	
	dataValid = new SimpleDateFormat("yyyy-MM-dd").parse(validade);
	Calendar dataValidade = Calendar.getInstance();
	dataValidade.setTime(dataValid);
	passaporte.setValidade(dataValidade);
	
	dao.altera(pessoa);
	dao.alteraPassaporte(passaporte);
	
} catch (ParseException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


dao.close(); 
%>

usuario ${ param.nome } alterado</body>