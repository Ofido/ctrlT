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

import br.com.doors.ctrlt.model.Disciplina;

@Repository
public class DisciplinaDAO implements InterfaceDisciplinaDAO {
	private static final String INCLUIR = "insert into ctrlt.disciplina(nomeDisciplina) value (?)";
	private static final String EXCLUIR = "delete from ctrlt.disciplina where idDisciplina=?";
	private static final String ALTERAR = "update ctrlt.disciplina set nomeDisciplina=? where idDisciplina=?";
	private static final String LISTAR = "select * from ctrlt.disciplina";
	private static final String PROCURAR = "select * from ctrlt.disciplina where idDisciplina=?";
	
	//CONEXÃO
	private static Connection CONEXAO;
	
	@Autowired
	public DisciplinaDAO(DataSource ds) {
		try{
			this.CONEXAO = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void incluir(Disciplina t) {
		if (t==null) {
			throw new RuntimeException("DISCIPLINA NÃO PODE SER NULA" + this.getClass());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(INCLUIR, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, t.getNomeDisciplina());
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()){
				t.setIdDisciplina(rs.getLong(1));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NO CADASTRO DE DISCIPLINA" + this.getClass() + e.toString());
		}
	}

	@Override
	public void alterar(Disciplina t) {
		if (t==null) {
			throw new RuntimeException("DISCIPLINA NÃO PODE SER NULA" + this.getClass());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(ALTERAR);
			stmt.setLong(2, t.getIdDisciplina());
			stmt.setString(1, t.getNomeDisciplina());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NA ALTERACAO DE DISCIPLINA" + this.getClass() + e.toString());
		}
	}

	@Override
	public void excluir(Long id) {
		if (id==null) {
			throw new RuntimeException("O CODIGO NAO PDE SER NULO" + this.getClass());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(EXCLUIR);
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NA EXCLUSAO DE DISCIPLINA" + this.getClass() + e.toString());
		}
	}

	@Override
	public void excluir(Disciplina t) {
		if (t==null) {
			throw new RuntimeException("DISCIPLINA NÃO PODE SER NULA" + this.getClass());
		}
		excluir(t.getIdDisciplina());
	}

	@Override
	public List<Disciplina> listarTodos() {
		List<Disciplina> disciplinas = new ArrayList<>();
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(LISTAR);
			
			ResultSet rs = stmt.executeQuery();
			
			Disciplina disciplina;
			
			while (rs.next()) {
				disciplina = new Disciplina();
				disciplina.setIdDisciplina(rs.getLong("idDisciplina"));
				disciplina.setNomeDisciplina(rs.getString("nomeDisciplina"));
				disciplinas.add(disciplina);
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NA ALTERACAO DE DISCIPLINA" + this.getClass() + e.toString());
		}
		return disciplinas;
	}

	@Override
	public Disciplina procurar(Long id) {
		Disciplina disciplina = null;
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(PROCURAR);
			
			stmt.setLong(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				disciplina = new Disciplina();
				disciplina.setIdDisciplina(rs.getLong("idDisciplina"));
				disciplina.setNomeDisciplina(rs.getString("nomeDisciplina"));
				
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NA ALTERACAO DE DISCIPLINA" + this.getClass() + e.toString());
		}
		return disciplina;
	}

}
