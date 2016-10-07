package br.com.doors.ctrlt.dao;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import br.com.doors.ctrlt.model.Assunto;
import br.com.doors.ctrlt.model.Disciplina;
import br.com.doors.ctrlt.model.Especialista;
import br.com.doors.ctrlt.model.Professor;
import br.com.doors.ctrlt.model.Questao;
import br.com.doors.ctrlt.model.TipoQuestao;

public class QuestaoDAO implements InterfaceQuestaoDAO {
	private static final String INCLUIR = "insert into ctrlt.questao(idDisciplina, idProfessor, idEspecialista, tempoQuestao, "
			+ "nivelQuestao, questao, validadaQuestao, comentarioQuestao, tipoQuestao, complementoQuestao, quantidadeUso, ratingAluno, ratingProfessor) value (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String EXCLUIR = "delete from ctrlt.questao where idQuestao=?";
	private static final String ALTERAR = "update ctrlt.questao set idDisciplina=?, idProfessor=?, idEspecialista=?, tempoQuestao=?, "
			+ "nivelQuestao=?, questao=?, validadaQuestao=?, comentarioQuestao=?, tipoQuestao=?, complementoQuestao=?, quantidadeUso=?, ratingAluno=?, ratingProfessor=? where idQuestao=?";
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
			stmt.setLong(1, t.getDisciplinaQuestao().getIdDisciplina());
			stmt.setLong(2, t.getCriadorQuestao().getIdProfessor());
			stmt.setLong(3, t.getValidadorQuestao().getIdEspecialista());
			stmt.setLong(4, t.getTempoQuestao().getTimeInMillis());
			stmt.setLong(5, t.getNivelQuestao());
			stmt.setString(6, t.getQuestao());
			//TODO POR ULTIMO USO QUESTAO AQUI
			stmt.setBoolean(7, t.getValidadaQuestao());
			stmt.setString(8, t.getComentario());
			stmt.setInt(9, t.getTipoQuestao().ordinal());
			stmt.setBlob(10, (t.getComplementoQuestao()==null?null:new ByteArrayInputStream(t.getComplementoQuestao())));
			stmt.setLong(11, t.getUsoQuestao());
			stmt.setInt(12, t.getRatingAlunoQuestao());
			stmt.setInt(13, t.getRatingProfessorQuestao());
			
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()){
				t.setIdQuestao(rs.getLong(1));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NO CADASTRO DE QUESTAO" + this.getClass() + e.toString());
		}
	}

	@Override
	public void alterar(Questao t) {
		if (t==null) {
			throw new RuntimeException("QUESTAO NÃO PODE SER NULA" + this.getClass());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(ALTERAR);
			stmt.setLong(1, t.getDisciplinaQuestao().getIdDisciplina());
			stmt.setLong(2, t.getCriadorQuestao().getIdProfessor());
			stmt.setLong(3, t.getValidadorQuestao().getIdEspecialista());
			stmt.setLong(4, t.getTempoQuestao().getTimeInMillis());
			stmt.setLong(5, t.getNivelQuestao());
			stmt.setString(6, t.getQuestao());
			//TODO POR ULTIMO USO QUESTAO AQUI
			stmt.setBoolean(7, t.getValidadaQuestao());
			stmt.setString(8, t.getComentario());
			stmt.setInt(9, t.getTipoQuestao().ordinal());
			stmt.setBlob(10, (t.getComplementoQuestao()==null?null:new ByteArrayInputStream(t.getComplementoQuestao())));
			stmt.setLong(11, t.getUsoQuestao());
			stmt.setInt(12, t.getRatingAlunoQuestao());
			stmt.setInt(13, t.getRatingProfessorQuestao());
			stmt.setLong(14, t.getIdQuestao());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NO ALTERAR DE QUESTAO" + this.getClass() + e.toString());
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
			throw new RuntimeException("ERRO NA EXCLUSAO DE QUESTAO" + this.getClass() + e.toString());
		}
		
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
		Questao questao = new Questao();
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(PROCURAR);
			
			stmt.setLong(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				
				Disciplina disciplina = new Disciplina();
				disciplina.setIdDisciplina(rs.getLong("idDisciplina"));
				disciplina.setNomeDisciplina(rs.getString("nomeDisciplina"));
				
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

				Especialista especialista = new Especialista();
				especialista.setIdEspecialista(rs.getLong("idespecialista"));
				especialista.setNomeEspecialista(rs.getString("nomeespecialista"));
				especialista.setEmailEspecialista(rs.getString("emailespecialista"));
				especialista.setSenhaEspecialista(rs.getString("senhaespecialista"));
				especialista.setCpfEspecialista(rs.getString("cpfespecialista"));
				especialista.setTelefoneEspecialista(rs.getString("telefoneespecialista"));
				especialista.setFotoEspecialista(rs.getBytes("fotoespecialista"));

				Calendar tempoQuestao = Calendar.getInstance();
				tempoQuestao.setTimeInMillis(rs.getLong("tempoQuestao"));
				
				questao.setDisciplinaQuestao(disciplina);
				questao.setCriadorQuestao(professor);
				questao.setValidadorQuestao(especialista);
				questao.setTempoQuestao(tempoQuestao);
				questao.setNivelQuestao(rs.getInt("nivelQuestao"));
				questao.setQuestao(rs.getString("questao"));
				questao.setValidadaQuestao(rs.getBoolean("validadaQuestao"));
				questao.setComentario(rs.getString("comentarioQuestao"));
				questao.setTipoQuestao(TipoQuestao.tipo(rs.getInt("tipoQuestao")));
				questao.setComplementoQuestao(rs.getBytes("complementoQuestao"));
				questao.setUsoQuestao(rs.getLong("quantidadeUso"));
				questao.setRatingAlunoQuestao(rs.getInt("ratingAluno"));
				questao.setRatingProfessorQuestao(rs.getInt("ratingProfessor"));
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NA PROCURAR DE questao" + this.getClass() + e.toString());
		}
		return questao;
	}

	@Override
	public List<Questao> listarTodos() {
		List<Questao> questaos = new ArrayList<Questao>();
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(LISTAR);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Disciplina disciplina = new Disciplina();
				disciplina.setIdDisciplina(rs.getLong("idDisciplina"));
				disciplina.setNomeDisciplina(rs.getString("nomeDisciplina"));
				
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

				Especialista especialista = new Especialista();
				especialista.setIdEspecialista(rs.getLong("idespecialista"));
				especialista.setNomeEspecialista(rs.getString("nomeespecialista"));
				especialista.setEmailEspecialista(rs.getString("emailespecialista"));
				especialista.setSenhaEspecialista(rs.getString("senhaespecialista"));
				especialista.setCpfEspecialista(rs.getString("cpfespecialista"));
				especialista.setTelefoneEspecialista(rs.getString("telefoneespecialista"));
				especialista.setFotoEspecialista(rs.getBytes("fotoespecialista"));

				Calendar tempoQuestao = Calendar.getInstance();
				tempoQuestao.setTimeInMillis(rs.getLong("tempoQuestao"));
				
				Questao questao = new Questao();				
				questao.setDisciplinaQuestao(disciplina);
				questao.setCriadorQuestao(professor);
				questao.setValidadorQuestao(especialista);
				questao.setTempoQuestao(tempoQuestao);
				questao.setNivelQuestao(rs.getInt("nivelQuestao"));
				questao.setQuestao(rs.getString("questao"));
				questao.setValidadaQuestao(rs.getBoolean("validadaQuestao"));
				questao.setComentario(rs.getString("comentarioQuestao"));
				questao.setTipoQuestao(TipoQuestao.tipo(rs.getInt("tipoQuestao")));
				questao.setComplementoQuestao(rs.getBytes("complementoQuestao"));
				questao.setUsoQuestao(rs.getLong("quantidadeUso"));
				questao.setRatingAlunoQuestao(rs.getInt("ratingAluno"));
				questao.setRatingProfessorQuestao(rs.getInt("ratingProfessor"));
				questaos.add(questao);
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NA LISTAR DE questao" + this.getClass() + e.toString());
		}
		return questaos;
	}

}
