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

import br.com.doors.ctrlt.model.Turma;

@Repository
public class TurmaDAO implements InterfaceTurmaDAO {
	private static final String INCLUIR = "insert into ctrlt.disciplina(nomeDisciplina) value (?)";
	private static final String EXCLUIR = "delete from ctrlt.disciplina where idDisciplina=?";
	private static final String ALTERAR = "update ctrlt.disciplina set nomeDisciplina=? where idDisciplina=?";
	private static final String LISTAR = "select * from ctrlt.disciplina";
	private static final String PROCURAR = "select * from ctrlt.disciplina where idDisciplina=?";
	
	//CONEXÃO
	private static Connection CONEXAO;
	
	@Autowired
	public TurmaDAO(DataSource ds) {
		try{
			this.CONEXAO = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void incluir(Turma t) {
		if (t==null) {
			throw new RuntimeException("DISCIPLINA NÃO PODE SER NULA" + this.getClass());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(INCLUIR, PreparedStatement.RETURN_GENERATED_KEYS);
			
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()){
				t.setIdTurma(rs.getLong(1));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NO CADASTRO DE DISCIPLINA" + this.getClass() + e.toString());
		}
	}

	@Override
	public void alterar(Turma t) {
		if (t==null) {
			throw new RuntimeException("DISCIPLINA NÃO PODE SER NULA" + this.getClass());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(ALTERAR);
			
			
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
	public void excluir(Turma t) {
		if (t==null) {
			throw new RuntimeException("DISCIPLINA NÃO PODE SER NULA" + this.getClass());
		}
		excluir(t.getIdTurma());
	}

	@Override
	public List<Turma> listarTodos() {
		List<Turma> disciplinas = new ArrayList<>();
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(LISTAR);
			
			ResultSet rs = stmt.executeQuery();
			
			Turma disciplina;
			
			while (rs.next()) {
				disciplina = new Turma();
				disciplina.setIdTurma(rs.getLong("idTurma"));
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
	public Turma procurar(Long id) {
		Turma disciplina = null;
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(PROCURAR);
			
			stmt.setLong(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				disciplina = new Turma();
				disciplina.setIdTurma(rs.getLong("idTurma"));
				
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NA ALTERACAO DE DISCIPLINA" + this.getClass() + e.toString());
		}
		return disciplina;
	}

}
