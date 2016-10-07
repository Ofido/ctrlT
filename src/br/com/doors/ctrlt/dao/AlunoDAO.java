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

import br.com.doors.ctrlt.model.Aluno;

@Repository
public class AlunoDAO implements InterfaceAlunoDAO {
	private static final String LOGIN = "SELECT * FROM aluno WHERE emailaluno=? AND senhaaluno=?";
	private static final String INCLUIR = "insert into ctrlt.aluno(telefoneAluno,senhaAluno,aniversarioAluno,cpfAluno,nomeAluno,complementoAluno,ruaAluno,bairroAluno,numeroAluno,estadoAluno,cidadeAluno,emailAluno,cepAluno,fotoAluno) value (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String EXCLUIR = "delete from ctrlt.aluno where idaluno=?";
	private static final String ALTERAR = "update ctrlt.aluno set telefoneAluno=?,senhaAluno=?,aniversarioAluno=?,cpfAluno=?,nomeAluno=?,complementoAluno=?,ruaAluno=?,bairroAluno=?,numeroAluno=?,estadoAluno=?,cidadeAluno=?,emailAluno=?,cepAluno=?,fotoAluno=? where idAluno=?";
	private static final String LISTAR = "select * from ctrlt.aluno";
	private static final String PROCURAR = "select * from ctrlt.aluno where idaluno=?";

	//CONEXÃO
	private static Connection CONEXAO;
	
	@Autowired
	public AlunoDAO(DataSource ds) {
		try{
			this.CONEXAO = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void incluir(Aluno t) {
		if (t==null) {
			throw new RuntimeException("ALUNO NÃO PODE SER NULO" + this.getClass());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(INCLUIR, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, t.getTelefoneAluno());
			stmt.setString(2, t.getSenhaAluno());
			stmt.setLong(3, t.getAniversarioAluno().getTimeInMillis());
			stmt.setString(4, t.getCpfAluno());
			stmt.setString(5, t.getNomeAluno());
			stmt.setString(6, t.getComplementoAluno());
			stmt.setString(7, t.getRuaAluno());
			stmt.setString(8, t.getBairroAluno());
			stmt.setString(9, t.getNumeroAluno());
			stmt.setString(10, t.getEstadoAluno());
			stmt.setString(11, t.getCidadeAluno());
			stmt.setString(12, t.getEmailAluno());
			stmt.setString(13, t.getCepAluno());
			stmt.setBlob(14, new ByteArrayInputStream(t.getFotoAluno()));
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()){
				t.setIdAluno(rs.getLong(1));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NO CADASTRO DE Aluno\n" + this.getClass() +"\n"+ e.toString());
		}
	}

	@Override
	public void alterar(Aluno t) {
		if (t==null) {
			throw new RuntimeException("ALUNO NÃO PODE SER NULO" + this.getClass());
		}
		Aluno aluno = procurar(t.getIdAluno());
		if (t.getTelefoneAluno() == null || t.getTelefoneAluno().isEmpty()) {
			t.setTelefoneAluno(aluno.getTelefoneAluno());
		}
		if (t.getSenhaAluno() == null || t.getSenhaAluno().isEmpty()) {
			t.setSenhaAluno(aluno.getSenhaAluno());
		}
		if (t.getAniversarioAluno() == null) {
			t.setAniversarioAluno(aluno.getAniversarioAluno());
		}
		if (t.getCpfAluno() == null || t.getCpfAluno().isEmpty()) {
			t.setCpfAluno(aluno.getCpfAluno());
		}
		if (t.getNomeAluno() == null || t.getNomeAluno().isEmpty()) {
			t.setNomeAluno(aluno.getNomeAluno());
		}
		if (t.getComplementoAluno() == null || t.getComplementoAluno().isEmpty()) {
			t.setComplementoAluno(aluno.getComplementoAluno());
		}
		if (t.getRuaAluno() == null || t.getRuaAluno().isEmpty()) {
			t.setRuaAluno(aluno.getRuaAluno());
		}
		if (t.getBairroAluno() == null || t.getBairroAluno().isEmpty()) {
			t.setBairroAluno(aluno.getBairroAluno());
		}
		if (t.getNumeroAluno() == null || t.getNumeroAluno().isEmpty()) {
			t.setNumeroAluno(aluno.getNumeroAluno());
		}
		if (t.getEstadoAluno() == null || t.getEstadoAluno().isEmpty()) {
			t.setEstadoAluno(aluno.getEstadoAluno());
		}
		if (t.getCidadeAluno() == null || t.getCidadeAluno().isEmpty()) {
			t.setCidadeAluno(aluno.getCidadeAluno());
		}
		if (t.getEmailAluno() == null || t.getEmailAluno().isEmpty()) {
			t.setEmailAluno(aluno.getEmailAluno());
		}
		if (t.getCepAluno() == null || t.getCepAluno().isEmpty()) {
			t.setCepAluno(aluno.getCepAluno());
		}
		if (t.getFotoAluno() == null || t.getFoto64().isEmpty()) {
			t.setFotoAluno(aluno.getFotoAluno());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(ALTERAR);
			stmt.setString(1, t.getTelefoneAluno());
			stmt.setString(2, t.getSenhaAluno());
			stmt.setLong(3, t.getAniversarioAluno().getTimeInMillis());
			stmt.setString(4, t.getCpfAluno());
			stmt.setString(5, t.getNomeAluno());
			stmt.setString(6, t.getComplementoAluno());
			stmt.setString(7, t.getRuaAluno());
			stmt.setString(8, t.getBairroAluno());
			stmt.setString(9, t.getNumeroAluno());
			stmt.setString(10, t.getEstadoAluno());
			stmt.setString(11, t.getCidadeAluno());
			stmt.setString(12, t.getEmailAluno());
			stmt.setString(13, t.getCepAluno());
			stmt.setBlob(14, new ByteArrayInputStream(t.getFotoAluno()));
			stmt.setLong(15, t.getIdAluno());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NA ALTERACAO DE Aluno\n" + this.getClass() +"\n"+ e.toString());
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
			throw new RuntimeException("ERRO NA EXCLUSAO DE ALUNO" + this.getClass() + e.toString());
		}
	}

	@Override
	public void excluir(Aluno t) {
		excluir(t.getIdAluno());
	}

	@Override
	public List<Aluno> listarTodos() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(LISTAR);

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
	public Aluno logar(String email, String senha) {
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
			PreparedStatement stmt = this.CONEXAO.prepareStatement(LOGIN);
			
			stmt.setString(1, email);
			stmt.setString(2, senha);
			
			ResultSet rs = stmt.executeQuery();
			Aluno aluno;
			if (rs.next()) {
				aluno = new Aluno();
				aluno.setIdAluno(rs.getLong("idaluno"));
				aluno.setNomeAluno(rs.getString("nomealuno"));
				aluno.setEmailAluno(rs.getString("emailaluno"));
				aluno.setSenhaAluno(rs.getString("senhaaluno"));
			} else {
				aluno = null;
			}
			rs.close();
			stmt.close();
			return aluno;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Aluno procurar(Long id) {
		if (id==null) {
			throw new RuntimeException("O CODIGO NAO PDE SER NULO" + this.getClass());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(PROCURAR);

			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();
			Aluno aluno;
			if (rs.next()) {
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
			} else {
				aluno = null;
			}
			rs.close();
			stmt.close();
			return aluno;
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NO PROCURAR DE ALUNO" + this.getClass() + e.toString());
		}
	}
}
