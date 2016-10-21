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

import br.com.doors.ctrlt.model.Administrador;

@Repository
public class AdministradorDAO implements InterfaceAdministradorDAO {
	private static final String LOGIN = "SELECT * FROM administrador WHERE emailAdm=? AND senhaAdm=?";
	private static final String INCLUIR = "insert into ctrlt.administrador(cpfAdm,senhaAdm,nomeAdm,emailAdm,telefoneAdm) value (?,?,?,?,?)";
	private static final String EXCLUIR = "delete from ctrlt.administrador where idAdm=?";
	private static final String ALTERAR = "update ctrlt.administrador set cpfAdm=?,senhaAdm=?,nomeAdm=?,emailAdm=?,telefoneAdm=?";
	private static final String LISTAR = "select * from ctrlt.administrador";
	private static final String PROCURAR = "select * from ctrlt.administrador where idAdm=?";

	//CONEXÃO
	private static Connection CONEXAO;
	
	@Autowired
	public AdministradorDAO(DataSource ds) {
		try{
			this.CONEXAO = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void incluir(Administrador t) {
		if (t==null) {
			throw new RuntimeException("ADMINISTRADOR NÃO PODE SER NULO" + this.getClass());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(INCLUIR, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, t.getCpfAdm());
			stmt.setString(2, t.getSenhaAdm());
			stmt.setString(3, t.getNomeAdm());
			stmt.setString(4, t.getEmailAdm());
			stmt.setString(5, t.getTelefoneAdm());
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()){
				t.setIdAdm(rs.getLong(1));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NO CADASTRO DE Administrador\n" + this.getClass() +"\n"+ e.toString());
		}
	}

	@Override
	public void alterar(Administrador t) {
		if (t==null) {
			throw new RuntimeException("ADMINISTRADOR NÃO PODE SER NULO" + this.getClass());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(ALTERAR);
			stmt.setString(1, t.getCpfAdm());
			stmt.setString(2, t.getSenhaAdm());
			stmt.setString(3, t.getNomeAdm());
			stmt.setString(4, t.getEmailAdm());
			stmt.setString(5, t.getTelefoneAdm());
			stmt.setLong(6, t.getIdAdm());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NA ALTERACAO DE Administrador\n" + this.getClass() +"\n"+ e.toString());
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
			throw new RuntimeException("ERRO NA EXCLUSÃO DE ADMINISTRADOR" + this.getClass() + e.toString());
		}
	}

	@Override
	public void excluir(Administrador t) {
		excluir(t.getIdAdm());
	}

	@Override
	public List<Administrador> listarTodos() {
		List<Administrador> adms = new ArrayList<Administrador>();
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(LISTAR);

			ResultSet rs = stmt.executeQuery();
			Administrador adm;
			while (rs.next()) {
				adm = new Administrador();
				adm.setIdAdm(rs.getLong("idAdm"));
				adm.setNomeAdm(rs.getString("nomeAdm"));
				adm.setEmailAdm(rs.getString("emailAdm"));
				adm.setSenhaAdm(rs.getString("senhaAdm"));
				adm.setCpfAdm(rs.getString("cpfAdm"));
				adm.setTelefoneAdm(rs.getString("telefoneAdm"));
				adms.add(adm);
			}
			rs.close();
			stmt.close();
			return adms;
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NO LISTAR DE ADMINISTRADOR" + this.getClass() + e.toString());
		}
	}

	@Override
	public Administrador logar(String email, String senha) {
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
			Administrador administrador;
			if (rs.next()) {
				administrador = new Administrador();
				administrador.setIdAdm(rs.getLong("idAdm"));
				administrador.setNomeAdm(rs.getString("nomeAdm"));
				administrador.setEmailAdm(rs.getString("emailAdm"));
				administrador.setSenhaAdm(rs.getString("senhaAdm"));
			} else {
				administrador = null;
			}
			rs.close();
			stmt.close();
			return administrador;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Administrador procurar(Long id) {
		if (id==null) {
			throw new RuntimeException("O CODIGO NAO PDE SER NULO" + this.getClass());
		}
		try {
			PreparedStatement stmt = CONEXAO.prepareStatement(PROCURAR);

			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();
			Administrador adm;
			if (rs.next()) {
				adm = new Administrador();
				adm.setIdAdm(rs.getLong("idAdm"));
				adm.setNomeAdm(rs.getString("nomeAdm"));
				adm.setEmailAdm(rs.getString("emailAdm"));
				adm.setSenhaAdm(rs.getString("senhaAdm"));
				adm.setCpfAdm(rs.getString("cpfAdm"));
				adm.setTelefoneAdm(rs.getString("telefoneAdm"));
			} else {
				adm = null;
			}
			rs.close();
			stmt.close();
			return adm;
		} catch (SQLException e) {
			throw new RuntimeException("ERRO NO PROCURAR DE ADMINISTRADOR" + this.getClass() + e.toString());
		}
	}
}
