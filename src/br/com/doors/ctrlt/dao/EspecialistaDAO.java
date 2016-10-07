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

import br.com.doors.ctrlt.model.Especialista;

@Repository
public class EspecialistaDAO implements InterfaceEspecialistaDAO {
	private static final String LOGIN = "SELECT * FROM especialista WHERE emailespecialista=? AND senhaespecialista=?";
	private static final String INCLUIR = "insert into ctrlt.especialista(cpfEspecialista,senhaEspecialista,nomeEspecialista,emailEspecialista,telefoneEspecialista,fotoEspecialista) value (?,?,?,?,?,?)";
	private static final String EXCLUIR = "delete from ctrlt.especialista where idespecialista=?";
	private static final String ALTERAR = "update ctrlt.especialista set cpfEspecialista=?,senhaEspecialista=?,nomeEspecialista=?,emailEspecialista=?,telefoneEspecialista=?,fotoEspecialista=?";
	private static final String LISTAR = "select * from ctrlt.especialista";
	private static final String PROCURAR = "select * from ctrlt.especialista where idespecialista=?";

	//CONEXÃO
	private static Connection CONEXAO;
	
	@Autowired
	public EspecialistaDAO(DataSource ds) {
		try{
			this.CONEXAO = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void incluir(Especialista t) {
		if (t==null) {
			throw new RuntimeException("ESPECIALISTA NÃO PODE SER NULO" + this.getClass());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(INCLUIR, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, t.getCpfEspecialista());
			stmt.setString(2, t.getSenhaEspecialista());
			stmt.setString(3, t.getNomeEspecialista());
			stmt.setString(4, t.getEmailEspecialista());
			stmt.setString(5, t.getTelefoneEspecialista());
			stmt.setBlob(6, new ByteArrayInputStream(t.getFotoEspecialista()));
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()){
				t.setIdEspecialista(rs.getLong(1));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NO CADASTRO DE Especialista\n" + this.getClass() +"\n"+ e.toString());
		}
	}

	@Override
	public void alterar(Especialista t) {
		if (t==null) {
			throw new RuntimeException("ESPECIALISTA NÃO PODE SER NULO" + this.getClass());
		}
		Especialista especialista = procurar(t.getIdEspecialista());
		if (t.getCpfEspecialista() == null || t.getCpfEspecialista().isEmpty()) {
			t.setCpfEspecialista(especialista.getCpfEspecialista());
		}
		if (t.getSenhaEspecialista() == null || t.getSenhaEspecialista().isEmpty()) {
			t.setSenhaEspecialista(especialista.getSenhaEspecialista());
		}
		if (t.getNomeEspecialista() == null || t.getNomeEspecialista().isEmpty()) {
			t.setNomeEspecialista(especialista.getNomeEspecialista());
		}
		if (t.getEmailEspecialista() == null || t.getEmailEspecialista().isEmpty()) {
			t.setEmailEspecialista(especialista.getEmailEspecialista());
		}
		if (t.getTelefoneEspecialista() == null || t.getTelefoneEspecialista().isEmpty()) {
			t.setTelefoneEspecialista(especialista.getTelefoneEspecialista());
		}
		if (t.getFotoEspecialista() == null || t.getFoto64().isEmpty()) {
			t.setFotoEspecialista(especialista.getFotoEspecialista());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(ALTERAR);
			stmt.setString(1, t.getCpfEspecialista());
			stmt.setString(2, t.getSenhaEspecialista());
			stmt.setString(3, t.getNomeEspecialista());
			stmt.setString(4, t.getEmailEspecialista());
			stmt.setString(5, t.getTelefoneEspecialista());
			stmt.setBlob(6, new ByteArrayInputStream(t.getFotoEspecialista()));
			stmt.setLong(7, t.getIdEspecialista());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NA ALTERACAO DE Especialista\n" + this.getClass() +"\n"+ e.toString());
		}
	}

	@Override
	public void excluir(Long id) {
		if (id==null) {
			throw new RuntimeException("O CÓDIGO NÃO PODE SER NULO" + this.getClass());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(EXCLUIR);
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NA EXCLUSÃO DE ESPECIALISTA" + this.getClass() + e.toString());
		}
	}

	@Override
	public void excluir(Especialista t) {
		excluir(t.getIdEspecialista());
	}

	@Override
	public List<Especialista> listarTodos() {
		List<Especialista> especialistas = new ArrayList<Especialista>();
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(LISTAR);

			ResultSet rs = stmt.executeQuery();
			Especialista especialista;
			while (rs.next()) {
				especialista = new Especialista();
				especialista.setIdEspecialista(rs.getLong("idprofessor"));
				especialista.setNomeEspecialista(rs.getString("nomeprofessor"));
				especialista.setEmailEspecialista(rs.getString("emailprofessor"));
				especialista.setSenhaEspecialista(rs.getString("senhaprofessor"));
				especialista.setCpfEspecialista(rs.getString("cpfProfessor"));
				especialista.setTelefoneEspecialista(rs.getString("telefoneProfessor"));
				especialista.setFotoEspecialista(rs.getBytes("fotoProfessor"));
				especialistas.add(especialista);
			}
			rs.close();
			stmt.close();
			return especialistas;
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NO LISTAR DE ESPECIALISTA" + this.getClass() + e.toString());
		}
	}

	@Override
	public Especialista logar(String email, String senha) {
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
			Especialista especialista;
			if (rs.next()) {
				especialista = new Especialista();
				especialista.setIdEspecialista(rs.getLong("idespecialista"));
				especialista.setNomeEspecialista(rs.getString("nomeespecialista"));
				especialista.setEmailEspecialista(rs.getString("emailespecialista"));
				especialista.setSenhaEspecialista(rs.getString("senhaespecialista"));
			} else {
				especialista = null;
			}
			rs.close();
			stmt.close();
			return especialista;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Especialista procurar(Long id) {
		if (id==null) {
			throw new RuntimeException("O CODIGO NAO PDE SER NULO" + this.getClass());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(PROCURAR);

			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();
			Especialista especialista;
			if (rs.next()) {
				especialista = new Especialista();
				especialista.setIdEspecialista(rs.getLong("idespecialista"));
				especialista.setNomeEspecialista(rs.getString("nomeespecialista"));
				especialista.setEmailEspecialista(rs.getString("emailespecialista"));
				especialista.setSenhaEspecialista(rs.getString("senhaespecialista"));
				especialista.setCpfEspecialista(rs.getString("cpfespecialista"));
				especialista.setTelefoneEspecialista(rs.getString("telefoneespecialista"));
				especialista.setFotoEspecialista(rs.getBytes("fotoespecialista"));
			} else {
				especialista = null;
			}
			rs.close();
			stmt.close();
			return especialista;
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NO PROCURAR DE ESPECIALISTA" + this.getClass() + e.toString());
		}
	}
}
