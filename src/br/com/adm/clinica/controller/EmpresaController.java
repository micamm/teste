package br.com.adm.clinica.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;

import com.google.gson.Gson;

import br.com.dao.EmpresaDAO;
import br.com.model.Empresa;

@ManagedBean
@ViewScoped
public class EmpresaController {
	
	private Empresa empresa = new Empresa();
	
	private EmpresaDAO empresaDAO = new EmpresaDAO();
	
	public void salvar() throws IOException {
		empresaDAO.save(empresa);
		FacesContext.getCurrentInstance().getExternalContext().redirect("listarFornecedor.xhtml");
	}
	
	public void voltar() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("listarFornecedor.xhtml");
	}
	
	public void pesquisaCep(AjaxBehaviorEvent event) {

		try {

			URL url = new URL("https://viacep.com.br/ws/" + empresa.getCep() + "/json/");
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

			String cep = "";
			StringBuilder jsonCep = new StringBuilder();
			while ((cep = br.readLine()) != null) {
				jsonCep.append(cep);

			}
			Empresa gsonAux = new Gson().fromJson(jsonCep.toString(), Empresa.class);

			empresa.setCep(gsonAux.getCep());
			empresa.setLogradouro(gsonAux.getLogradouro());
			empresa.setLocalidade(gsonAux.getLocalidade());
			empresa.setUf(gsonAux.getUf());

		} catch (Exception e) {
	
		}

	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
