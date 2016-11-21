package br.com.doors.ctrlt.dao;

import java.util.List;

import br.com.doors.ctrlt.model.Aluno;
import br.com.doors.ctrlt.model.Professor;
import br.com.doors.ctrlt.model.Turma;

public interface InterfaceTurmaDAO extends InterfaceManter<Turma>{
	public List<Professor> listarProfTurma(Long id);
	public List<Professor> listarProfTurma(Turma turma);
	public List<Aluno> listarAlunoTurma(Long id);
	public List<Aluno> listarAlunoTurma(Turma turma);
	public void incluirNaTurma(Object o, Turma t);
	public List<Turma> listarTurmaProf(Long id);
	public List<Turma> listarTurmaProf(Professor prof);
	public List<Turma> listarTurmaAluno(Long id);
	public List<Turma> listarTurmaAluno(Aluno aluno);
}
