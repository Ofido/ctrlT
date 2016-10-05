package br.com.doors.ctrlt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.doors.ctrlt.model.Aluno;

@Repository
public class AlunoDAO implements InterfaceAlunoDAO {
	private static final String LOGIN = "SELECT * FROM aluno WHERE emailaluno=? AND senhaaluno=?";

	//CONEXÃO
	private static Connection CONEXAO;
	
	@Autowired
	public AlunoDAO(DataSource ds) {
		try{
			this.CONEXAO = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void incluir(Aluno t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterar(Aluno t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Aluno t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Aluno> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Aluno logar(String email, String senha) {
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
			Aluno aluno;
			if (rs.next()) {
				aluno = new Aluno();
				aluno.setIdAluno(rs.getLong("idaluno"));
				aluno.setNomeAluno(rs.getString("nomealuno"));
				aluno.setEmailAluno(rs.getString("emailaluno"));
				aluno.setSenhaAluno(rs.getString("senhaaluno"));
			} else {
				aluno = null;
			}
			rs.close();
			stmt.close();
			return aluno;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Aluno procurar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
