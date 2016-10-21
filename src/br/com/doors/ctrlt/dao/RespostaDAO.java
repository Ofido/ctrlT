package br.com.doors.ctrlt.dao;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.doors.ctrlt.model.Resposta;
import br.com.doors.ctrlt.model.Resposta;

@Repository
public class RespostaDAO implements InterfaceRespostaDAO {
	private static final String INCLUIR = "insert into ctrlt.resposta(correta, idQuestao, resposta, complemento) value (?,?,?,?)";
	private static final String EXCLUIR = "delete from ctrlt.resposta where idResposta=?";
	private static final String ALTERAR = "update ctrlt.resposta set correta=?, idQuestao=?, resposta=?, complemento=? where idResposta=?";
	private static final String LISTAR = "select * from ctrlt.resposta";
	private static final String PROCURAR = "select * from ctrlt.resposta where idResposta=?";
	private static final String PROCURARQUESTAO = "select * from ctrlt.resposta, ctrlt.questao where ctrlt.resposta.idQuestao=ctrlt.questao.idQuestao and ctrlt.questao.idQuestao=?";

	private static Connection CONEXAO;

	@Autowired
	public RespostaDAO(DataSource ds) {
		try {
			this.CONEXAO = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void incluir(Resposta t, Long idQuestao) {
		if (t == null) {
			throw new RuntimeException("RESPOSTA NÃO PODE SER NULA"
					+ this.getClass());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(INCLUIR,
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setBoolean(1, t.getCorretaResposta());
			stmt.setLong(2, idQuestao);
			stmt.setString(3, t.getResposta());
			stmt.setBlob(4,
					new ByteArrayInputStream(t.getComplementoResposta()));
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				t.setIdResposta(rs.getLong(1));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NO CADASTRO DE RESPOSTA"
					+ this.getClass() + e.toString());
		}
	}

	@Override
	public void alterar(Resposta t, Long idQuestao) {
		if (t == null) {
			throw new RuntimeException("RESPOSTA NÃO PODE SER NULA"
					+ this.getClass());
		}
		Resposta resposta = procurar(t.getIdResposta());
		if (t.getResposta() == null || t.getResposta().isEmpty()) {
			t.setResposta(resposta.getResposta());
		}
		if (t.getCorretaResposta() == null) {
			t.setCorretaResposta(resposta.getCorretaResposta());
		}
		if (t.getComplementoResposta() == null || t.getFoto64().isEmpty()) {
			t.setComplementoResposta(resposta.getComplementoResposta());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(ALTERAR,
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setBoolean(1, t.getCorretaResposta());
			stmt.setLong(2, idQuestao);
			stmt.setString(3, t.getResposta());
			stmt.setBlob(4,
					new ByteArrayInputStream(t.getComplementoResposta()));
			stmt.setLong(5, t.getIdResposta());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NO ALTERAR DE RESPOSTA"
					+ this.getClass() + e.toString());
		}
	}

	@Override
	public void excluir(Long id) {
		if (id == null) {
			throw new RuntimeException("O CODIGO NAO PDE SER NULO"
					+ this.getClass());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(EXCLUIR);
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NA EXCLUSAO DE RESPOSTA"
					+ this.getClass() + e.toString());
		}
	}

	@Override
	public void excluir(Resposta t) {
		if (t == null) {
			throw new RuntimeException("RESPOSTA NÃO PODE SER NULA"
					+ this.getClass());
		}
		excluir(t.getIdResposta());
	}

	@Override
	public Resposta procurar(Long id) {
		Resposta resposta = null;
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(PROCURAR);

			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				resposta = new Resposta();
				resposta.setIdResposta(rs.getLong("idResposta"));
				resposta.setCorretaResposta(rs.getBoolean("correta"));
				resposta.setResposta(rs.getString("resposta"));
				resposta.setComplementoResposta(rs.getBytes("complemento"));
			}
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NO PROCURAR DE RESPOSTA"
					+ this.getClass() + e.toString());
		}
		return resposta;
	}

	@Override
	public List<Resposta> procurarQuestao(Long idQuestao) {
		List<Resposta> respostas = null;
		Integer aux = 0;
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(PROCURARQUESTAO);

			stmt.setLong(1, idQuestao);

			ResultSet rs = stmt.executeQuery();
			respostas = new ArrayList<Resposta>();
			while (rs.next()) {
				Resposta resposta = new Resposta();
				resposta.setIdResposta(rs.getLong("idResposta"));
				resposta.setCorretaResposta(rs.getBoolean("correta"));
				resposta.setResposta(rs.getString("resposta"));
				resposta.setComplementoResposta(rs.getBytes("complemento"));
				respostas.add(resposta);
				aux++;
			}
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NO PROCURAR DE RESPOSTA"
					+ this.getClass() + e.toString());
		}
		return respostas;
	}

	@Deprecated
	@Override
	public List<Resposta> listarTodos() {
		return null;
	}

	@Deprecated
	@Override
	public void incluir(Resposta t) {
	}

	@Deprecated
	@Override
	public void alterar(Resposta t) {
	}

}
