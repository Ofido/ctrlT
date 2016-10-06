package br.com.doors.ctrlt.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import br.com.doors.ctrlt.model.Questao;

public class QuestaoDAO implements InterfaceQuestaoDAO {
	private static final String INCLUIR = "insert into ctrlt.questao(idQuestao, idDisciplina, idProfessor, idEspecialista, tempoQuestao, "
			+ "nivelQuestao, questao, ultimoUsoQuestao, validadaQuestao, comentárioQuestao, tipoQuestao, complementoQuestao, quantidadeUso, ratingAluno, ratingProfessor) value (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String EXCLUIR = "delete from ctrlt.questao where idQuestao=?";
	private static final String ALTERAR = "update ctrlt.questao set idDisciplina=?, nomeQuestao=? where idQuestao=?";
	private static final String LISTAR = "SELECT * FROM ctrlt.questao, ctrlt.disciplina, ctrlt.professor, ctrlt.especialista WHERE ctrlt.questao.idDisciplina = ctrlt.disciplina.idDisciplina and ctrlt.questao.idProfessor = ctrlt.professor.idProfessor and ctrlt.questao.idEspecialista = ctrlt.especialista.idEspecialista";
	private static final String PROCURAR = "SELECT * FROM ctrlt.questao, ctrlt.disciplina, ctrlt.professor, ctrlt.especialista WHERE ctrlt.questao.idDisciplina = ctrlt.disciplina.idDisciplina and ctrlt.questao.idProfessor = ctrlt.professor.idProfessor and ctrlt.questao.idEspecialista = ctrlt.especialista.idEspecialista and idquestao=?";
	
	private static Connection CONEXAO;
	
	public QuestaoDAO(DataSource ds) {
		try {
			this.CONEXAO = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void incluir(Questao t) {
		if (t==null) {
			throw new RuntimeException("QUESTAO NÃO PODE SER NULA" + this.getClass());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(INCLUIR, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setLong(1, t.getIdQuestao());
			stmt.setLong(2, t.getDisciplinaQuestao().getIdDisciplina());
			stmt.setLong(3, t.getCriadorQuestao().getIdProfessor());
			stmt.setLong(4, t.getValidadorQuestao().getIdEspecialista());
			stmt.setLong(5, t.getTempoQuestao().getTimeInMillis());
			stmt.setInt(6, t.getTipoQuestao().ordinal());
			stmt.setString(7, t.getQuestao());
			
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()){
				t.setIdQuestao(rs.getLong(1));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NO CADASTRO DE ASSUNTO" + this.getClass() + e.toString());
		}
	}

	@Override
	public void alterar(Questao t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(Questao t) {
		if (t==null) {
			throw new RuntimeException("QUESTAO NÃO PODE SER NULA" + this.getClass());
		}
		excluir(t.getIdQuestao());
	}

	@Override
	public Questao procurar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Questao> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
