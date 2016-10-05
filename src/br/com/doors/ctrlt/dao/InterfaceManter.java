package br.com.doors.ctrlt.dao;

import java.util.List;

public interface InterfaceManter<T> {
	public void incluir(T t);

	public void alterar(T t);

	public void excluir(Long id);

	public void excluir(T t);

	public T procurar(Long id);
	
	public List<T> listarTodos();
}
