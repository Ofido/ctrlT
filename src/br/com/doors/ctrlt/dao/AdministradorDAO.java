package br.com.doors.ctrlt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.doors.ctrlt.model.Administrador;

@Repository
public class AdministradorDAO implements InterfaceAdministradorDAO {
	private static final String LOGIN = "SELECT * FROM administrador WHERE emailAdm=? AND senhaAdm=?";

	//CONEXÃO
	private static Connection CONEXAO;
	
	@Autowired
	public AdministradorDAO(DataSource ds) {
		try{
			this.CONEXAO = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void incluir(Administrador t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterar(Administrador t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Administrador t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Administrador> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Administrador logar(String email, String senha) {
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
			PreparedStatement stmt = CONEXAO.prepareStatement(LOGIN);
			
			stmt.setString(1, email);
			stmt.setString(2, senha);
		
			ResultSet rs = stmt.executeQuery();
			Administrador administrador;
			if (rs.next()) {
				administrador = new Administrador();
				administrador.setIdAdm(rs.getLong("idAdm"));
				administrador.setNomeAdm(rs.getString("nomeAdm"));
				administrador.setEmailAdm(rs.getString("emailAdm"));
				administrador.setSenhaAdm(rs.getString("senhaAdm"));
			} else {
				administrador = null;
			}
			rs.close();
			stmt.close();
			return administrador;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Administrador procurar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
