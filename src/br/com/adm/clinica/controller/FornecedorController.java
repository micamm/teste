package br.com.adm.clinica.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import br.com.dao.EmpresaDAO;
import br.com.dao.FornecedorDAO;
import br.com.model.Empresa;
import br.com.model.Fornecedor;

@ManagedBean
@ViewScoped
public class FornecedorController {
	
	private Fornecedor fornecedor = new Fornecedor();
	
	private Empresa empresa = new Empresa();
	
	private EmpresaDAO empresaDAO = new EmpresaDAO();
	
	private FornecedorDAO fornecedorDAO = new FornecedorDAO();

	private List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
	
	private List<Empresa> empresas = new ArrayList<Empresa>();
	
	private String pesquisa;
	
	private boolean fornecedorJuridico;
	
	private String telefone;
	
	private int tipoFiltro;
	
	private List<String> telefones;
	
	private Integer idEmpresa;
	
	@PostConstruct
	public void init() {
		try {
			fornecedores = fornecedorDAO.findAll();
			empresas = empresaDAO.findAll();
		}catch (NullPointerException e) {
		}
	}
	
	public void filtrar() {
		switch (tipoFiltro) {
		case 1:
			fornecedores = fornecedorDAO.buscarFornecedorPorEmpresa(pesquisa);
			break;
		case 2:
			fornecedores = fornecedorDAO.buscarFornecedorPorNome(pesquisa);
			break;
		case 3:
			fornecedores = fornecedorDAO.buscarFornecedorPorCpf(pesquisa);
			break;
		case 4:
			fornecedores = fornecedorDAO.buscarFornecedorPorCnpj(pesquisa);
			break;	
		default:
			fornecedores = fornecedorDAO.findAll();
			break;
		}
	}
	
	public void adicionarTelefone() {
		if(this.telefones == null) {
			telefones = new ArrayList<String>();
		}
		
		telefones.add(telefone);
		telefone = "";
	}
	
	public void salvar() throws IOException {
		empresa = empresaDAO.findById(idEmpresa);
		this.adicionarTelefone();
		fornecedor.setTelefones(telefones);
		fornecedor.setDataCadastro(new Date());
		fornecedor.setEmpresa(empresa);
		fornecedorDAO.save(fornecedor); 
		fornecedores = fornecedorDAO.findAll();
		FacesContext.getCurrentInstance().getExternalContext().redirect("listarFornecedor.xhtml");
	}
	
	public void voltar() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("listarFornecedor.xhtml");
	}
	
	public void chamarNovaEmpresa() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("novaEmpresa.xhtml");
	}
	
	public void chamarNovoFornecedor() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("novoFornecedor.xhtml");
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public boolean isFornecedorJuridico() {
		return fornecedorJuridico;
	}

	public void setFornecedorJuridico(boolean fornecedorJuridico) {
		this.fornecedorJuridico = fornecedorJuridico;
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

	public int getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(int tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}
	
}
