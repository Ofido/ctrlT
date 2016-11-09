package br.com.doors.ctrlt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.doors.ctrlt.model.Aluno;
import br.com.doors.ctrlt.model.Especialista;
import br.com.doors.ctrlt.model.Professor;
import br.com.doors.ctrlt.model.Turma;

@Repository
public class TurmaDAO implements InterfaceTurmaDAO {
	private static final String INCLUIR = "insert into ctrlt.turma(nomeTurma, idProfessor) value (?,?)";
	private static final String INCLUIRALUNO = "insert into ctrlt.pertence_aluno_turma(idTurma, idAluno) value (?,?)";
	private static final String INCLUIRPROFESSOR = "insert into ctrlt.pertence_professor_turma(idTurma, idProfessor) value (?,?)";
	private static final String EXCLUIR = "delete from ctrlt.turma where idTurma=?";
	private static final String ALTERAR = "update ctrlt.turma set nomeTurma=?, idProfessor=? where idTurma=?";
	private static final String LISTAR = "select * from ctrlt.turma, ctrlt.professor where ctrlt.turma.idProfessor = ctrl.professor.idProfessor";
	private static final String LISTARPROEFSSORES = "select * from ctrlt.turma, ctrlt.pertence_professor_turma, ctrl.professor where ctrlt.turma.idTurma = ctrl.pertence_professor_turma.idTurma AND ctrlt.pertence_professor_turma.idProfessor = ctrl.professor.idProfessor";
	private static final String LISTARALUNOS = "select * from ctrlt.turma, ctrlt.pertence_aluno_turma, ctrlt.aluno where ctrlt.turma.idTurma = ctrl.pertence_aluno_turma.idTurma AND ctrl.pertence_aluno_turma.idAluno = ctrl.aluno.idAluno";
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
			throw new RuntimeException("TURMA NÃO PODE SER NULA" + this.getClass());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(INCLUIR, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, t.getNomeTurma());
			stmt.setLong(2, t.getRegenteTurma().getIdProfessor());
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()){
				t.setIdTurma(rs.getLong(1));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NO CADASTRO DE TURMA" + this.getClass() + e.toString());
		}
	}
	
	public void incluirNaTurma(Object t) {
		//TODO
	}

	@Override
	public void alterar(Turma t) {		
		if (t==null) {
			throw new RuntimeException("TURMA NÃO PODE SER NULA" + this.getClass());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(ALTERAR);
			stmt.setString(1, t.getNomeTurma());
			stmt.setLong(2, t.getRegenteTurma().getIdProfessor());
			stmt.setLong(3, t.getIdTurma());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NA ALTERACAO DE TURMA" + this.getClass() + e.toString());
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
			throw new RuntimeException("ERRO NA EXCLUSAO DE TURMA" + this.getClass() + e.toString());
		}
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
				turma.setNomeTurma(rs.getString("nomeTurma"));
				Professor professor = new Professor();
				professor.setIdProfessor(rs.getLong("idprofessor"));
				professor.setNomeProfessor(rs.getString("nomeprofessor"));
				professor.setEmailProfessor(rs.getString("emailprofessor"));
				professor.setSenhaProfessor(rs.getString("senhaprofessor"));
				professor.setCpfProfessor(rs.getString("cpfProfessor"));
				professor.setLicenca((rs.getInt("licencaProfessor")==1?true:false));
				professor.setEscolaProfessor(rs.getString("escolaProfessor"));
				professor.setTagProfessor(rs.getString("tagProfessor"));
				professor.setTelefoneProfessor(rs.getString("telefoneProfessor"));
				professor.setFotoProfessor(rs.getBytes("fotoProfessor"));
				turma.setRegenteTurma(professor);
				turma.setProfessoresTurma(listarProfTurma(turma));
				turma.setAlunosTurma(listarAlunoTurma(turma));
				turmas.add(turma);
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NA ALTERACAO DE TURMA" + this.getClass() + e.toString());
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
				turma.setNomeTurma(rs.getString("nomeTurma"));
				Professor professor = new Professor();
				professor.setIdProfessor(rs.getLong("idprofessor"));
				professor.setNomeProfessor(rs.getString("nomeprofessor"));
				professor.setEmailProfessor(rs.getString("emailprofessor"));
				professor.setSenhaProfessor(rs.getString("senhaprofessor"));
				professor.setCpfProfessor(rs.getString("cpfProfessor"));
				professor.setLicenca((rs.getInt("licencaProfessor")==1?true:false));
				professor.setEscolaProfessor(rs.getString("escolaProfessor"));
				professor.setTagProfessor(rs.getString("tagProfessor"));
				professor.setTelefoneProfessor(rs.getString("telefoneProfessor"));
				professor.setFotoProfessor(rs.getBytes("fotoProfessor"));
				turma.setRegenteTurma(professor);
				turma.setProfessoresTurma(listarProfTurma(turma));
				turma.setAlunosTurma(listarAlunoTurma(turma));
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NA ALTERACAO DE TURMA" + this.getClass() + e.toString());
		}
		return turma;
	}

	@Override
	public List<Professor> listarProfTurma(Long id) {
		List<Professor> professors = new ArrayList<Professor>();
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(LISTARPROEFSSORES);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			Professor professor;
			while (rs.next()) {
				professor = new Professor();
				professor.setIdProfessor(rs.getLong("idprofessor"));
				professor.setNomeProfessor(rs.getString("nomeprofessor"));
				professor.setEmailProfessor(rs.getString("emailprofessor"));
				professor.setSenhaProfessor(rs.getString("senhaprofessor"));
				professor.setCpfProfessor(rs.getString("cpfProfessor"));
				professor.setLicenca((rs.getInt("licencaProfessor")==1?true:false));
				professor.setEscolaProfessor(rs.getString("escolaProfessor"));
				professor.setTagProfessor(rs.getString("tagProfessor"));
				professor.setTelefoneProfessor(rs.getString("telefoneProfessor"));
				professor.setFotoProfessor(rs.getBytes("fotoProfessor"));
				professors.add(professor);
			}
			rs.close();
			stmt.close();
			return professors;
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NO LISTAR DE PROFESSOR" + this.getClass() + e.toString());
		}
	}

	@Override
	public List<Aluno> listarAlunoTurma(Long id) {
		List<Aluno> alunos = new ArrayList<Aluno>();
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(LISTARALUNOS);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			Aluno aluno;
			while (rs.next()) {
				aluno = new Aluno();
				aluno.setIdAluno(rs.getLong("idaluno"));
				aluno.setTelefoneAluno(rs.getString("telefonealuno"));
				aluno.setSenhaAluno(rs.getString("senhaaluno"));
				Calendar aqui = Calendar.getInstance();
				aqui.setTimeInMillis(rs.getLong("aniversarioaluno"));
				aluno.setAniversarioAluno(aqui);
				aluno.setCpfAluno(rs.getString("cpfaluno"));
				aluno.setNomeAluno(rs.getString("nomealuno"));
				aluno.setComplementoAluno(rs.getString("complementoaluno"));
				aluno.setRuaAluno(rs.getString("ruaaluno"));
				aluno.setBairroAluno(rs.getString("bairroaluno"));
				aluno.setNumeroAluno(rs.getString("numeroaluno"));
				aluno.setEstadoAluno(rs.getString("estadoaluno"));
				aluno.setCidadeAluno(rs.getString("cidadealuno"));
				aluno.setEmailAluno(rs.getString("emailaluno"));
				aluno.setCepAluno(rs.getString("cepaluno"));
				aluno.setFotoAluno(rs.getBytes("fotoaluno"));
				alunos.add(aluno);
			}
			rs.close();
			stmt.close();
			return alunos;
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NO LISTAR DE ALUNO" + this.getClass() + e.toString());
		}
	}
	
	@Override
	public List<Professor> listarProfTurma(Turma turma) {
		if (turma==null) {
			throw new RuntimeException("TURMA NÃO PODE SER NULA" + this.getClass());
		}
		return listarProfTurma(turma.getIdTurma());
	}

	@Override
	public List<Aluno> listarAlunoTurma(Turma turma) {
		if (turma==null) {
			throw new RuntimeException("TURMA NÃO PODE SER NULA" + this.getClass());
		}
		return listarAlunoTurma(turma.getIdTurma());
	}

	@Override
	public void excluir(Turma t) {
		if (t==null) {
			throw new RuntimeException("TURMA NÃO PODE SER NULA" + this.getClass());
		}
		excluir(t.getIdTurma());
	}
}
