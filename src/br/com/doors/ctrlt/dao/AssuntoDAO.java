package br.com.doors.ctrlt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.doors.ctrlt.model.Assunto;

public class AssuntoDAO implements InterfaceAssuntoDAO {
	private static final String INCLUIR = "insert into ctrlt.assunto(idDisciplina, nomeAssunto) value (?,?)";
	private static final String EXCLUIR = "delete from ctrlt.assunto where idAssunto=?";
	private static final String ALTERAR = "update ctrlt.assunto set idDisciplina=?, nomeAssunto=? where idAssunto=?";
	private static final String LISTAR = "select * from ctrlt.assunto";
	private static final String PROCURAR = "select * from ctrlt.assunto where idassunto=?";

	// CONEXÃO
	private static Connection CONEXAO;

	@Autowired
	public AssuntoDAO(DataSource ds) {
		try {
			this.CONEXAO = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void incluir(br.com.doors.ctrlt.model.Assunto t) {
		if (t==null) {
			throw new RuntimeException("ASSUNTO NÃO PODE SER NULO" + this.getClass());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(INCLUIR);
			stmt.setLong(1, t.getDisciplinaAssunto().getIdDisciplina());
			stmt.setString(2, t.getNomeAssunto());
			
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()){
				t.setIdAssunto(rs.getLong("idAssunto"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NO CADASTRO DE ASSUNTO" + this.getClass() + e.toString());
		}
	}

	@Override
	public void alterar(br.com.doors.ctrlt.model.Assunto t) {
		if (t==null) {
			throw new RuntimeException("ASSUNTO NÃO PODE SER NULO" + this.getClass());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(ALTERAR);
			stmt.setLong(1, t.getDisciplinaAssunto().getIdDisciplina());
			stmt.setString(2, t.getNomeAssunto());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NA ALTERACAO DE ASSUNTO" + this.getClass() + e.toString());
		}
	}

	@Override
	public void excluir(Long id) {
		if (id==null) {
			throw new RuntimeException("ID NÃO PODE SER NULO" + this.getClass());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(EXCLUIR);
			stmt.setLong(1, id);
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NA EXCLUSAO DE ASSUNTO" + this.getClass() + e.toString());
		}
	}

	@Override
	public void excluir(br.com.doors.ctrlt.model.Assunto t) {
		excluir(t.getIdAssunto());
	}

	@Override
	public br.com.doors.ctrlt.model.Assunto procurar(Long id) {
		Assunto assunto = null;
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(PROCURAR);
			
			stmt.setLong(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				assunto = new Assunto();
				assunto.setIdAssunto(rs.getLong("idassunto"));
				assunto.setNomeAssunto(rs.getString("nomeassunto"));
				
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NA ALTERACAO DE assunto" + this.getClass() + e.toString());
		}
		return assunto;
	}

	@Override
	public List<br.com.doors.ctrlt.model.Assunto> listarTodos() {
		List<Assunto> assuntos = new ArrayList<>();
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(LISTAR);
			
			ResultSet rs = stmt.executeQuery();
			
			Assunto assunto;
			
			while (rs.next()) {
				assunto = new Assunto();
				assunto.setIdAssunto(rs.getLong("idAssunto"));
				assunto.setNomeAssunto(rs.getString("nomeAssunto"));
				assuntos.add(assunto);
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NA ALTERACAO DE DISCIPLINA" + this.getClass() + e.toString());
		}
		return assuntos;
	}

}
