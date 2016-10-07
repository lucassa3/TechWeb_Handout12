<%@ page import="java.util.*,br.edu.insper.model.*" %>
<table border='1'>       
<% DAO dao = new DAO();

List<Pessoas> pessoas = dao.getLista();
List<Passaportes> passaportes = dao.getListaPassaportes();		

for (int i = 0; i < pessoas.size(); i++) { 
	Pessoas pessoa = pessoas.get(i);
	Passaportes passaporte = passaportes.get(i); %>
	
	<tr>
    	<td><%=pessoa.getId()%></td>
        <td><%=pessoa.getNome()%></td>
        <td><%=pessoa.getNascimento().getTime()%></td>
        <td><%=pessoa.getAltura()%></td>
        <td><%=passaporte.getPais()%></td>
        <td><%=passaporte.getValidade().getTime()%></td>
    </tr>
    
<% } %>
</table>
</body>