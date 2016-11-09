package br.com.doors.ctrlt.dao;

import java.io.ByteArrayInputStream;
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

import br.com.doors.ctrlt.model.Assunto;
import br.com.doors.ctrlt.model.Disciplina;
import br.com.doors.ctrlt.model.Especialista;
import br.com.doors.ctrlt.model.Professor;
import br.com.doors.ctrlt.model.Questao;
import br.com.doors.ctrlt.model.TipoQuestao;

@Repository
public class QuestaoDAO implements InterfaceQuestaoDAO {
	private static final String INCLUIR = "insert into ctrlt.questao(idDisciplina, idProfessor, idEspecialista, idAssunto, tempoQuestao, "
			+ "nivelQuestao, questao, ultimoUsoQuestao, validadaQuestao, comentario, tipoQuestao, complementoQuestao) value (?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String EXCLUIR = "delete from ctrlt.questao where idQuestao=?";
	private static final String ALTERAR = "update ctrlt.questao set idDisciplina=?, idProfessor=?, idEspecialista=?, idAssunto=?,tempoQuestao=?, "
			+ "nivelQuestao=?, questao=?, ultimoUsoQuestao=?, validadaQuestao=?, comentario=?, tipoQuestao=?, complementoQuestao=?, quantidadeUso=?, ratingAluno=?, ratingProfessor=? where idQuestao=?";
	private static final String LISTAR = "SELECT * FROM ctrlt.questao, ctrlt.disciplina, ctrlt.assunto, ctrlt.professor, ctrlt.especialista WHERE ctrlt.questao.idDisciplina = ctrlt.disciplina.idDisciplina and ctrlt.questao.idProfessor = ctrlt.professor.idProfessor and ctrlt.questao.idAssunto=ctrlt.assunto.idAssunto and ctrlt.questao.idEspecialista = ctrlt.especialista.idEspecialista";
	private static final String PROCURAR = "SELECT * FROM ctrlt.questao, ctrlt.disciplina, ctrlt.assunto, ctrlt.professor, ctrlt.especialista WHERE ctrlt.questao.idDisciplina = ctrlt.disciplina.idDisciplina and ctrlt.questao.idProfessor = ctrlt.professor.idProfessor and ctrlt.questao.idAssunto=ctrlt.assunto.idAssunto and ctrlt.questao.idEspecialista = ctrlt.especialista.idEspecialista and idquestao=?";
	
	private static Connection CONEXAO;
	
	@Autowired
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
			stmt.setLong(4, t.getAssuntoQuestao().getIdAssunto());
			stmt.setLong(5, t.getTempoQuestao().getTimeInMillis());
			stmt.setLong(6, t.getNivelQuestao());
			stmt.setString(7, t.getQuestao());
			stmt.setLong(8, t.getUltimoUsoQuestao().getTimeInMillis());
			stmt.setBoolean(9, (t.getValidadaQuestao()==null?false:t.getValidadaQuestao()));
			stmt.setString(10, t.getComentario());
			stmt.setInt(11, t.getTipoQuestao().ordinal());
			stmt.setBlob(12, (t.getComplementoQuestao()==null?null:new ByteArrayInputStream(t.getComplementoQuestao())));
			
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
			stmt.setLong(4, t.getAssuntoQuestao().getIdAssunto());
			stmt.setLong(5, t.getTempoQuestao().getTimeInMillis());
			stmt.setLong(6, t.getNivelQuestao());
			stmt.setString(7, t.getQuestao());
			stmt.setLong(8, t.getUltimoUsoQuestao().getTimeInMillis());
			stmt.setBoolean(9, (t.getValidadaQuestao()==null?false:t.getValidadaQuestao()));
			stmt.setString(10, t.getComentario());
			stmt.setInt(11, t.getTipoQuestao().ordinal());
			stmt.setBlob(12, (t.getComplementoQuestao()==null?null:new ByteArrayInputStream(t.getComplementoQuestao())));
			stmt.setLong(13, 0L);
			stmt.setInt(14, 0);
			stmt.setInt(15, 0);
			stmt.setLong(16, t.getIdQuestao());
			
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
				
				Assunto assunto = new Assunto();
				assunto.setIdAssunto(rs.getLong("idAssunto"));
				assunto.setNomeAssunto(rs.getString("nomeAssunto"));
				
				Professor professor = new Professor();
				professor.setIdProfessor(rs.getLong("idProfessor"));
				professor.setNomeProfessor(rs.getString("nomeProfessor"));
				professor.setEmailProfessor(rs.getString("emailProfessor"));
				professor.setSenhaProfessor(rs.getString("senhaProfessor"));
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
				
				assunto.setDisciplinaAssunto(disciplina);
				questao.setDisciplinaQuestao(disciplina);
				questao.setAssuntoQuestao(assunto);
				questao.setCriadorQuestao(professor);
				questao.setValidadorQuestao(especialista);
				questao.setTempoQuestao(tempoQuestao);
				questao.setIdQuestao(rs.getLong("idQuestao"));
				questao.setNivelQuestao(rs.getInt("nivelQuestao"));
				questao.setQuestao(rs.getString("questao"));
				questao.setValidadaQuestao(rs.getBoolean("validadaQuestao"));
				questao.setComentario(rs.getString("comentario"));
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
				
				Assunto assunto = new Assunto();
				assunto.setIdAssunto(rs.getLong("idassunto"));
				assunto.setNomeAssunto(rs.getString("nomeassunto"));
				
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
				
				Calendar ultimoUsoQuestao = Calendar.getInstance();
				ultimoUsoQuestao.setTimeInMillis((rs.getLong("ultimoUsoQuestao")));
				
				assunto.setDisciplinaAssunto(disciplina);
				Questao questao = new Questao();
				questao.setAssuntoQuestao(assunto);
				questao.setDisciplinaQuestao(disciplina);
				questao.setDisciplinaQuestao(disciplina);
				questao.setCriadorQuestao(professor);
				questao.setValidadorQuestao(especialista);
				questao.setTempoQuestao(tempoQuestao);
				questao.setUltimoUsoQuestao(ultimoUsoQuestao);
				questao.setIdQuestao(rs.getLong("idQuestao"));
				questao.setNivelQuestao(rs.getInt("nivelQuestao"));
				questao.setQuestao(rs.getString("questao"));
				questao.setValidadaQuestao(rs.getBoolean("validadaQuestao"));
				questao.setComentario(rs.getString("comentario"));
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
	
	public List<Questao> professorQuestoes() {
		List<Questao> questoes = new ArrayList<Questao>();//TODO AQUI
		return questoes;
	}
	public List<Questao> analistaQuestoes() {
		List<Questao> questoes = new ArrayList<Questao>();
		return questoes;
	}
	public Boolean professorArrumarQuestoes() {
		if (professorQuestoes().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	public Boolean analistaValidarQuestoes() {
		if (analistaQuestoes().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}

}
