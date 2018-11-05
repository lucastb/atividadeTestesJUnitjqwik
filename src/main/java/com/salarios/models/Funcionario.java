package com.salarios.models;

public class Funcionario {
	private int matricula, nroDependentes;
	private String nome;
	private double salarioBase, salarioBruto, salarioLiquido;
	private boolean insalubridade;
	
	public Funcionario(int matricula, int nroDependentes, String nome, double salarioBase, boolean insalubridade) {
		super();
		this.matricula = matricula;
		this.nroDependentes = nroDependentes;
		this.nome = nome;
		this.salarioBase = salarioBase;
		this.insalubridade = insalubridade; 
	}
	
	public int getMatricula() {
		return matricula;
	}

	public int getNroDependentes() {
		return nroDependentes;
	}

	public String getNome() {
		return nome;
	}

	public double getSalarioBase() {
		return salarioBase;
	}

	public double getSalarioBruto() {
		double adicionalInsalubridade = 0;
		double adicionalDependentes = 0;
		
		//Adicional de Insalubridade 
		if(insalubridade) {
			adicionalInsalubridade = (getSalarioBase()*10)/100;
		}
		
		//Adicional de dependentes		
		if(nroDependentes > 0) {
			adicionalDependentes = (getSalarioBase()*nroDependentes)/100;
		}
		
		//Salario Bruto
		salarioBruto = getSalarioBase() + adicionalInsalubridade + adicionalDependentes;
		
		return salarioBruto;
	}

	public double getSalarioLiquido() {
		double inss = 0;
		double inssTemp = 0;
		double ir = 0;
		
		//INSS
		inssTemp = (getSalarioBase()*4.5)/100;
		if (inssTemp <= 5000) {
			inss = inssTemp;
		}else inss = 5000;
		
		//IR
		if		(getSalarioBase() <= 2000)
		{} /*isento*/
		
		else if (2000 < getSalarioBase() && getSalarioBase() <= 5000 )
		{ //paga 12% IR
			ir = getSalarioBase()*0.12;
		}
		
		else if (getSalarioBase() > 5000)
		{  //paga 27% IR
			ir = getSalarioBase()*0.275;
		}
			
		//Liquido
		salarioLiquido = getSalarioBruto() - inss - ir;
		
		return salarioLiquido;
	}

	public boolean getInsalubridade() {
		return insalubridade;
	}
}