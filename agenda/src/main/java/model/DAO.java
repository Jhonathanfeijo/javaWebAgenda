package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	
	Connection con = null;
	
	private Connection getConnection() {
		try {
			
		return DriverManager.getConnection("jdbc:mysql://localhost/agenda","root","1234");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
	}
	
	public void adiciona(JavaBeans contato) {
		this.con=getConnection();
		try {
			String query="insert into contatos(nome,email,telefone) values(?,?,?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getFone());
			stmt.execute();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		public void edita(JavaBeans contato) {
			this.con=getConnection();
			try {
				String query="update contatos set nome=?,email=?,telefone=? where id=?";
				PreparedStatement stmt = con.prepareStatement(query);
				
				stmt.setString(1, contato.getNome());
				stmt.setString(2, contato.getEmail());
				stmt.setString(3, contato.getFone());
				stmt.setLong(4, contato.getId());
				stmt.execute();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public List<JavaBeans> getList() {
		this.con=getConnection();
		
		List<JavaBeans> contatos = new ArrayList<JavaBeans>();
		String query = "select * from contatos order by nome";
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				JavaBeans contato = new JavaBeans();
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setFone(rs.getString("telefone"));
				contatos.add(contato);
			}
			rs.close();
			stmt.close();
			return contatos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void selecionaContato(JavaBeans contato) {
		this.con=getConnection();
		String comando ="select * from contatos where id=?";
		try {
			PreparedStatement stmt = con.prepareStatement(comando);
			stmt.setLong(1, contato.getId());
			ResultSet rs = stmt.executeQuery();
			stmt.setLong(1, contato.getId());
			while(rs.next()) {
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setFone(rs.getString("telefone"));
			}
			rs.close();
			stmt.close();
			this.con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
	}

	public void delete(long id) {
		this.con=getConnection();
		String comando = "delete from contatos where id=?;";
		try {
			PreparedStatement stmt = con.prepareStatement(comando);
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
			this.con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
