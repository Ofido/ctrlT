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
	private static final String INCLUIR = "insert into ctrlt.turma(nomeTurma, idProfessor) value (?,?)";
	private static final String EXCLUIR = "delete from ctrlt.turma where idTurma=?";
	private static final String ALTERAR = "update ctrlt.turma set nomeTurma=?, idProfessor=? where idTurma=?";
	private static final String LISTAR = "select * from ctrlt.turma, ctrl.professor where ctrlt.turma.idProfessor = ctrl.professor.idProfessor";
	private static final String PROCURAR = "select * from ctrlt.turma where idTurma=?";
	
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
		List<Turma> turmas = new ArrayList<>();
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(LISTAR);
			
			ResultSet rs = stmt.executeQuery();
			
			Turma turma;
			
			while (rs.next()) {
				turma = new Turma();
				turma.setIdTurma(rs.getLong("idTurma"));
				turmas.add(turma);
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NA ALTERACAO DE DISCIPLINA" + this.getClass() + e.toString());
		}
		return turmas;
	}

	@Override
	public Turma procurar(Long id) {
		Turma turma = null;
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(PROCURAR);
			
			stmt.setLong(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				turma = new Turma();
				turma.setIdTurma(rs.getLong("idTurma"));
				
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NA ALTERACAO DE DISCIPLINA" + this.getClass() + e.toString());
		}
		return turma;
	}

}
