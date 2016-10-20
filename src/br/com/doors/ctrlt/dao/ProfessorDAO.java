package br.com.doors.ctrlt.dao;

import java.util.ArrayList;
import java.util.List;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.doors.ctrlt.model.Professor;

@Repository
public class ProfessorDAO implements InterfaceProfessorDAO {
	private static final String LOGIN = "SELECT * FROM ctrlt.professor WHERE emailprofessor=? AND senhaprofessor=?";
	private static final String INCLUIR = "insert into ctrlt.professor(cpfProfessor,licencaProfessor,senhaProfessor,escolaProfessor,tagProfessor,nomeProfessor,emailProfessor,telefoneProfessor,fotoProfessor) value (?,?,?,?,?,?,?,?,?)";
	private static final String EXCLUIR = "delete from ctrlt.professor where idprofessor=?";
	private static final String ALTERAR = "update ctrlt.professor set cpfProfessor=?,licencaProfessor=?,senhaProfessor=?,escolaProfessor=?,tagProfessor=?,nomeProfessor=?,emailProfessor=?,telefoneProfessor=?,fotoProfessor=? where idprofessor=?";
	private static final String LISTAR = "select * from ctrlt.professor";
	private static final String PROCURAR = "select * from ctrlt.professor where idprofessor=?";

	// CONEXÃO
	private static Connection CONEXAO;

	@Autowired
	public ProfessorDAO(DataSource ds) {
		try {
			CONEXAO = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void incluir(Professor t) {
		if (t==null) {
			throw new RuntimeException("PROFESSOR NÃO PODE SER NULO" + this.getClass());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(INCLUIR, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, t.getCpfProfessor());
			stmt.setInt(2, (t.getLicenca()?1:0));
			stmt.setString(3, t.getSenhaProfessor());
			stmt.setString(4, t.getEscolaProfessor());
			stmt.setString(5, t.getTagProfessor());
			stmt.setString(6, t.getNomeProfessor());
			stmt.setString(7, t.getEmailProfessor());
			stmt.setString(8, t.getTelefoneProfessor());
			stmt.setBlob(9, new ByteArrayInputStream(t.getFotoProfessor()));
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()){
				t.setIdProfessor(rs.getLong(1));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NO CADASTRO DE Professor\n" + this.getClass() +"\n"+ e.toString());
		}
	}

	@Override
	public void alterar(Professor t) {
		if (t==null) {
			throw new RuntimeException("PROFESSOR NÃO PODE SER NULO" + this.getClass());
		}
		Professor professor = procurar(t.getIdProfessor());
		if (t.getCpfProfessor() == null || t.getCpfProfessor().isEmpty()) {
			t.setCpfProfessor(professor.getCpfProfessor());
		}
		if (t.getLicenca() == null) {
			t.setLicenca(professor.getLicenca());
		}
		if (t.getSenhaProfessor() == null || t.getSenhaProfessor().isEmpty()) {
			t.setSenhaProfessor(professor.getSenhaProfessor());
		}
		if (t.getEscolaProfessor() == null || t.getEscolaProfessor().isEmpty()) { // TODO FAZER ISSO EM TODOS OS DAO'S
			t.setEscolaProfessor(professor.getEscolaProfessor());
		}
		if (t.getTagProfessor() == null || t.getTagProfessor().isEmpty()) {
			t.setTagProfessor(professor.getTagProfessor());
		}
		if (t.getNomeProfessor() == null || t.getNomeProfessor().isEmpty()) {
			t.setNomeProfessor(professor.getNomeProfessor());
		}
		if (t.getEmailProfessor() == null || t.getEmailProfessor().isEmpty()) {
			t.setEmailProfessor(professor.getEmailProfessor());
		}
		if (t.getTelefoneProfessor() == null || t.getTelefoneProfessor().isEmpty()) {
			t.setTelefoneProfessor(professor.getTelefoneProfessor());
		}
		if (t.getFotoProfessor() == null || t.getFoto64().isEmpty()) {
			t.setFotoProfessor(professor.getFotoProfessor());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(ALTERAR);
			stmt.setString(1, t.getCpfProfessor());
			stmt.setInt(2, (t.getLicenca()?1:0));
			stmt.setString(3, t.getSenhaProfessor());
			stmt.setString(4, t.getEscolaProfessor());
			stmt.setString(5, t.getTagProfessor());
			stmt.setString(6, t.getNomeProfessor());
			stmt.setString(7, t.getEmailProfessor());
			stmt.setString(8, t.getTelefoneProfessor());
			stmt.setBlob(9, new ByteArrayInputStream(t.getFotoProfessor()));
			stmt.setLong(10, t.getIdProfessor());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NA ALTERACAO DE Professor\n" + this.getClass() +"\n"+ e.toString());
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
			throw new RuntimeException("ERRO NA EXCLUSAO DE PROFESSOR" + this.getClass() + e.toString());
		}
	}

	@Override
	public void excluir(Professor t) {
		excluir(t.getIdProfessor());
	}

	@Override
	public List<Professor> listarTodos() {
		List<Professor> professors = new ArrayList<Professor>();
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(LISTAR);

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
	public Professor logar(String email, String senha) {
		System.out.println(email + "     " + senha);
		if (email == null) {
			return null;
		}
		if (senha == null) {
			return null;
		}
		if (email.isEmpty()) {
			return null;
		}
		if (senha.isEmpty()) {
			return null;
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(LOGIN);

			stmt.setString(1, email);
			stmt.setString(2, senha);

			ResultSet rs = stmt.executeQuery();
			Professor professor;
			if (rs.next()) {
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
			} else {
				professor = null;
			}
			rs.close();
			stmt.close();
			return professor;
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NO LOGIN DE PROFESSOR" + this.getClass() + e.toString());
		}
	}

	@Override
	public Professor procurar(Long id) {
		if (id==null) {
			throw new RuntimeException("O CODIGO NAO PDE SER NULO" + this.getClass());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(PROCURAR);

			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();
			Professor professor;
			if (rs.next()) {
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
			} else {
				professor = null;
			}
			rs.close();
			stmt.close();
			return professor;
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NO PROCURAR DE PROFESSOR" + this.getClass() + e.toString());
		}
	}
}
