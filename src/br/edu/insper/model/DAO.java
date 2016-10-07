package br.edu.insper.model;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;;

public class DAO { 
	private Connection connection = null;
	public DAO() {
	    try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try {
			connection = DriverManager.getConnection(
			"jdbc:mysql://localhost/meus_dados", "root", "lsa895107");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }   
	
	
	public void adiciona(Pessoas pessoa) {
		String sql = "INSERT INTO Pessoas" +
		"(nome,nascimento,altura) values(?,?,?)";
		
		String sqlId = "select pessoas.id from pessoas order by pessoas.id desc limit 1";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt = connection.prepareStatement(sql);
			stmt.setString(1,pessoa.getNome());
			stmt.setDate(2, new Date(pessoa.getNascimento().getTimeInMillis()));
			stmt.setDouble(3,pessoa.getAltura());
			stmt.execute();
			stmt.close();
			

			PreparedStatement stmtId = connection.prepareStatement("SELECT * FROM Pessoas");
			ResultSet rs = stmtId.executeQuery();
			while (rs.next()) {
				pessoa.setId(rs.getInt("id"));
			}
			System.out.println(pessoa.getId());
			rs.close();
			stmtId.close();
			
		

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void adicionaPassaporte(Passaportes passaporte, int id) {
		String sql = "INSERT INTO Passaportes" +
		"(pessoa_id,pais,validade) values(?,?,?)";
		
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1,id);
			stmt.setString(2,passaporte.getPais());
			stmt.setDate(3, new Date(passaporte.getValidade().getTimeInMillis()));
			stmt.execute();
			stmt.close();
			
			passaporte.setPessoasId(id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<Pessoas> getLista() {
	
		List<Pessoas> pessoas = new ArrayList<Pessoas>();
	
		
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Pessoas");
			ResultSet rs = stmt.executeQuery();
	
			while (rs.next()) {
				Pessoas pessoa = new Pessoas();
				
				pessoa.setId(rs.getInt("id"));
				pessoa.setNome(rs.getString("nome"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("nascimento"));
				pessoa.setNascimento(data);
				pessoa.setAltura(rs.getDouble("altura"));
				pessoas.add(pessoa);
			}
			rs.close();
			stmt.close();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pessoas;
	}
	
	
	public List<Passaportes> getListaPassaportes() {
		
		List<Passaportes> passaportes = new ArrayList<Passaportes>();
	
		
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Passaportes");
			ResultSet rs = stmt.executeQuery();
	
			while (rs.next()) {
				Passaportes passaporte = new Passaportes();
				
				passaporte.setPessoasId(rs.getInt("pessoa_id"));
				passaporte.setPais(rs.getString("pais"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("validade"));
				passaporte.setValidade(data);
				passaportes.add(passaporte);
			}
			rs.close();
			stmt.close();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return passaportes;
	}
	
	public void altera(Pessoas pessoa) {
		String sql = "UPDATE Pessoas SET " + "nome=?, nascimento=?, altura=? WHERE id=?";
		PreparedStatement stmt;

		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, pessoa.getNome());
			stmt.setDate(2, new Date(pessoa.getNascimento().getTimeInMillis()));
			stmt.setDouble(3, pessoa.getAltura());
			stmt.setInt(4, pessoa.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void alteraPassaporte(Passaportes passaporte) {
		String sql = "UPDATE Passaportes SET " + "pais=?, validade=? WHERE pessoa_id=?";
		PreparedStatement stmt;

		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, passaporte.getPais());
			stmt.setDate(2, new Date(passaporte.getValidade().getTimeInMillis()));
			stmt.setInt(3, passaporte.getPessoasId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void remove(String nome) {
		
		try {

			
			PreparedStatement stmt2 = connection.prepareStatement("Select * from Pessoas where pessoas.nome=?");
			stmt2.setString(1, nome);
			ResultSet rs = stmt2.executeQuery();
			rs.next();
			int tempId = rs.getInt("id");
			stmt2.close();
			
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM Pessoas WHERE nome=?");
			stmt.setString(1, nome);
			stmt.execute();
			stmt.close();
			
			PreparedStatement stmt3 = connection.prepareStatement("DELETE FROM Passaportes WHERE pessoa_id=?");
			stmt3.setInt(1, tempId);
			stmt3.execute();
			stmt3.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		

}