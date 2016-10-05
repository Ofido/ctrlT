package br.com.doors.ctrlt.dao;

import com.sun.istack.internal.NotNull;

public interface InterfaceLogin<T> {
	public T logar(@NotNull String email,@NotNull String senha);
}
