package com.salarios.AppCalculaSalarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import net.jqwik.api.*;
import net.jqwik.api.constraints.*;

import com.salarios.models.Funcionario;

class TesteCalculoSalarios {
	  
	@Test
	void testeGetSalarioBrutoSimples() {
		
		Funcionario f = new Funcionario(112233,0,"João", 2000.00,false);
		double salarioBruto = f.getSalarioBruto();
		assertEquals(2000.0,salarioBruto);
	}
	
	@Test
	void testeGetSalarioBruto1Dependente() {
		Funcionario f = new Funcionario(112233,1,"João", 2000.00,false);
		double salarioBruto = f.getSalarioBruto();
		assertEquals(2010.0,salarioBruto);
	}
	
	@Test
	void testeGetSalarioBruto2Dependentes() {
		Funcionario f = new Funcionario(12345,2,"Fulano", 1000.00,false);
		double salarioBruto = f.getSalarioBruto();
		assertEquals(1020.0,salarioBruto);
	}
	
	@Test
	void testeGetSalarioBrutoInsalubridade() {
		Funcionario f1 = new Funcionario(12345,0,"Fulano", 1000.00,true);
		double salarioBruto = f1.getSalarioBruto();
		assertEquals(1100.0,salarioBruto);
	} 
	
	
	@Test
	void testeGetSalarioLiquidoFaixa1() {
		Funcionario f1 = new Funcionario(12345,0,"Fulano", 1000.00,false);
		double salarioLiquido = f1.getSalarioLiquido();
		assertEquals(955.0,salarioLiquido);
	}
	
	@Test
	void testeGetSalarioLiquidoFaixa2() {
		Funcionario f1 = new Funcionario(12345,0,"Fulano", 4000,false);
		double salarioLiquido = f1.getSalarioLiquido();
		assertEquals(3340.0,salarioLiquido);
	}
	
	@Test
	void testeGetSalarioLiquidoFaixa3() {
		Funcionario f1 = new Funcionario(12345,0,"Fulano", 6000,false);
		double salarioLiquido = f1.getSalarioLiquido();
		assertEquals(4080.0,salarioLiquido);
	}
	
	@Test
	void testeGetSalarioLiquidoRico() {
		Funcionario f1 = new Funcionario(12345,0,"Fulano", 150000,false);
		double salarioLiquido = f1.getSalarioLiquido();
		assertEquals(103750.0,salarioLiquido);
	}
	
	@Test
	void testeGetSalarioLiquido1Dependente() {
		Funcionario f1 = new Funcionario(12345,1,"Fulano", 1000,false);
		double salarioLiquido = f1.getSalarioLiquido();
		assertEquals(965.0,salarioLiquido);
	}
	
	@Test
	void testeGetSalarioLiquido2Dependentes() {
		Funcionario f1 = new Funcionario(12345,2,"Fulano", 1000,false);
		double salarioLiquido = f1.getSalarioLiquido();
		assertEquals(975.0,salarioLiquido);
	} 
	
	@Test
	void testeGetSalarioLiquidoRicoInsalubridade() {
		Funcionario f1 = new Funcionario(12345,0,"Fulano", 150000,true);
		double salarioLiquido = f1.getSalarioLiquido();
		assertEquals(118750.0,salarioLiquido);
	}
	
	@Test
	void testeGetSalarioLiquido1DependenteInsalubridade() {
		Funcionario f1 = new Funcionario(12345,1,"Fulano", 1000,true);
		double salarioLiquido = f1.getSalarioLiquido();
		assertEquals(1065.0,salarioLiquido);
	}
	
	@Test
	void testeGetSalarioLiquido2DependentesInsalubridade() {
		Funcionario f1 = new Funcionario(12345,2,"Fulano", 1000,true);
		double salarioLiquido = f1.getSalarioLiquido();
		assertEquals(1075.0,salarioLiquido);
	} 
	
	@Property(tries=1000)
	boolean oSalarioLiquidoDeveSerMenorQueOBruto(
		@ForAll @DoubleRange(min=800.0,max=20000.0) double umSalario){
		Funcionario f = new Funcionario(12345,2,"Fulano", (double)umSalario,true);
	    return f.getSalarioBruto() >= f.getSalarioLiquido();
	}
	
	@Property(tries=500)
	boolean oSalarioLiquidoDeveSerDiferenteDoBruto(
		@ForAll @DoubleRange(min=800.0,max=20000.0) double umSalario){
		Funcionario f = new Funcionario(12345,2,"Fulano", (double)umSalario,true);
	    return f.getSalarioBruto() != f.getSalarioLiquido();
	}
	
	@Property(tries=750)
	boolean oSalarioCom1DependenteDeveSerMenorQueCom2Dependentes(
		@ForAll @DoubleRange(min=800.0,max=20000.0) double umSalario){
		Funcionario f1 = new Funcionario(12345,1,"Fulano", (double)umSalario,false);
		Funcionario f2 = new Funcionario(12345,2,"Fulano", (double)umSalario,false);
	    return f2.getSalarioBruto() > f1.getSalarioBruto();
	}
} 
   