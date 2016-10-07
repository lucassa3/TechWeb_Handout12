package br.edu.insper.model;
import java.util.Calendar;

public class Passaportes {
	private Integer id;

	private Integer pessoasId;
	private String pais;
	private Calendar validade;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPessoasId() {
		return pessoasId;
	}
	public void setPessoasId(Integer pessoasId) {
		this.pessoasId = pessoasId;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public Calendar getValidade() {
		return validade;
	}
	public void setValidade(Calendar validade) {
		this.validade = validade;
	}
	
	

}
