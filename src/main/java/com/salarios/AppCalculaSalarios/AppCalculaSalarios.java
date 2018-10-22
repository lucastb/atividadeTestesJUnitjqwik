package com.salarios.AppCalculaSalarios;

import com.salarios.models.Funcionario;

public class AppCalculaSalarios {

	public static void main(String[] args) {
		Funcionario f1 = new Funcionario(12345,0,"Lariel", 5000.00,false);
		
		System.out.println("Salario Base: R$"+f1.getSalarioBase());
		System.out.println("Salario Bruto: R$"+f1.getSalarioBruto());
		System.out.println("Salario Liquido: R$"+f1.getSalarioLiquido());
	}

} 
