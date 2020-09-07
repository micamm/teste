package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.model.Fornecedor;
import br.com.util.JPAResourceBean;

public class FornecedorDAO extends GenericDAO<Fornecedor>{
	
	public FornecedorDAO() {
		super(Fornecedor.class);
	}
	
	public List<Fornecedor> buscarFornecedorPorEmpresa(String empresa) {
		EntityManager em = JPAResourceBean.getEntityManager();	
		  String queryJPQL = "SELECT f FROM Fornecedor f WHERE f.empresa.nome = :empresa";
		  return em.createQuery(queryJPQL, Fornecedor.class).setParameter("empresa",empresa).getResultList();	
	}
	
	public List<Fornecedor> buscarFornecedorPorNome(String nome) {
		EntityManager em = JPAResourceBean.getEntityManager();	
		  String queryJPQL = "SELECT f FROM Fornecedor f WHERE f.nome = :nome";
		  return em.createQuery(queryJPQL, Fornecedor.class).setParameter("nome",nome).getResultList();	
	}
	
	public List<Fornecedor> buscarFornecedorPorCpf(String cpf) {
		EntityManager em = JPAResourceBean.getEntityManager();	
		  String queryJPQL = "SELECT f FROM Fornecedor f WHERE f.cpf = :cpf";
		  return em.createQuery(queryJPQL, Fornecedor.class).setParameter("cpf",cpf).getResultList();	
	}
	
	public List<Fornecedor> buscarFornecedorPorCnpj(String cnpj) {
		EntityManager em = JPAResourceBean.getEntityManager();	
		  String queryJPQL = "SELECT f FROM Fornecedor f WHERE f.cnpj = :cnpj";
		  return em.createQuery(queryJPQL, Fornecedor.class).setParameter("cnpj",cnpj).getResultList();	
	}
}
