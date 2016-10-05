package br.com.doors.ctrlt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.doors.ctrlt.model.Assunto;
import br.com.doors.ctrlt.model.Disciplina;

@Repository
public class AssuntoDAO implements InterfaceAssuntoDAO {
	private static final String INCLUIR = "insert into ctrlt.assunto(idDisciplina, nomeAssunto) value (?,?)";
	private static final String EXCLUIR = "delete from ctrlt.assunto where idAssunto=?";
	private static final String ALTERAR = "update ctrlt.assunto set idDisciplina=?, nomeAssunto=? where idAssunto=?";
	private static final String LISTAR = "select * from ctrlt.assunto, ctrlt.disciplina where assunto.idDisciplina = disciplina.idDisciplina";
	private static final String PROCURAR = "select * from ctrlt.assunto, ctrlt.disciplina where assunto.idDisciplina = disciplina.idDisciplina and idassunto=?";

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
	public void incluir(Assunto t) {
		if (t==null) {
			throw new RuntimeException("ASSUNTO NÃO PODE SER NULO" + this.getClass());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(INCLUIR, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setLong(1, t.getDisciplinaAssunto().getIdDisciplina());
			stmt.setString(2, t.getNomeAssunto());
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()){
				t.setIdAssunto(rs.getLong(1));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NO CADASTRO DE ASSUNTO" + this.getClass() + e.toString());
		}
	}

	@Override
	public void alterar(Assunto t) {
		if (t==null) {
			throw new RuntimeException("ASSUNTO NÃO PODE SER NULO" + this.getClass());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(ALTERAR);
			stmt.setLong(1, t.getDisciplinaAssunto().getIdDisciplina());
			stmt.setString(2, t.getNomeAssunto());
			stmt.setLong(3, t.getIdAssunto());
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
	public void excluir(Assunto t) {
		excluir(t.getIdAssunto());
	}

	@Override
	public Assunto procurar(Long id) {
		Assunto assunto = null;
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(PROCURAR);
			
			stmt.setLong(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				Disciplina disciplina = new Disciplina();
				disciplina.setIdDisciplina(rs.getLong("idDisciplina"));
				disciplina.setNomeDisciplina(rs.getString("nomeDisciplina"));
				assunto = new Assunto();
				assunto.setIdAssunto(rs.getLong("idassunto"));
				assunto.setNomeAssunto(rs.getString("nomeassunto"));
				assunto.setDisciplinaAssunto(disciplina);
				
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NA ALTERACAO DE assunto" + this.getClass() + e.toString());
		}
		return assunto;
	}

	@Override
	public List<Assunto> listarTodos() {
		List<Assunto> assuntos = new ArrayList<>();
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(LISTAR);
			
			ResultSet rs = stmt.executeQuery();
			
			Assunto assunto;
			
			while (rs.next()) {
				Disciplina disciplina = new Disciplina();
				disciplina.setIdDisciplina(rs.getLong("idDisciplina"));
				disciplina.setNomeDisciplina(rs.getString("nomeDisciplina"));
				assunto = new Assunto();
				assunto.setIdAssunto(rs.getLong("idAssunto"));
				assunto.setNomeAssunto(rs.getString("nomeAssunto"));
				assunto.setDisciplinaAssunto(disciplina);
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
