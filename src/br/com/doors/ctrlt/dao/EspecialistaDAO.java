package br.com.doors.ctrlt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.doors.ctrlt.model.Especialista;

@Repository
public class EspecialistaDAO implements InterfaceEspecialistaDAO {
	private static final String LOGIN = "SELECT * FROM especialista WHERE emailespecialista=? AND senhaespecialista=?";

	//CONEXÃO
	private static Connection CONEXAO;
	
	@Autowired
	public EspecialistaDAO(DataSource ds) {
		try{
			this.CONEXAO = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void incluir(Especialista t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterar(Especialista t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Especialista t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Especialista> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Especialista logar(String email, String senha) {
		System.out.println(email + "     " + senha);
		if (email == null) {
			return null;
		}
		if (senha == null) {
			return null;
		}
		if (email.isEmpty()) {
			return null;
		}
		if (senha.isEmpty()) {
			return null;
		}
		try {
			PreparedStatement stmt = this.CONEXAO.prepareStatement(LOGIN);
			
			stmt.setString(1, email);
			stmt.setString(2, senha);
			
			ResultSet rs = stmt.executeQuery();
			Especialista especialista;
			if (rs.next()) {
				especialista = new Especialista();
				especialista.setIdEspecialista(rs.getLong("idespecialista"));
				especialista.setNomeEspecialista(rs.getString("nomeespecialista"));
				especialista.setEmailEspecialista(rs.getString("emailespecialista"));
				especialista.setSenhaEspecialista(rs.getString("senhaespecialista"));
			} else {
				especialista = null;
			}
			rs.close();
			stmt.close();
			return especialista;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Especialista procurar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
