package com.salarios.AppCalculaSalarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import net.jqwik.api.*;
import net.jqwik.api.constraints.*;

import com.salarios.models.Funcionario;

class CalculaSalariosTeste {
	  
	@Test
	void testeGetSalarioBrutoSimples() {
		Funcionario f = new Funcionario(112233,0,"Pedro", 3000.00,false);
		assertEquals(3000.0,f.getSalarioBruto());
	}
	
	@Test
	void testeGetSalarioBruto1Dependente() {
		Funcionario f = new Funcionario(112233,1,"Pedro", 5000.00,false);
		assertEquals(5050.0,f.getSalarioBruto());
	}
	
	@Test
	void testeGetSalarioBruto2Dependentes() {
		Funcionario f = new Funcionario(112233,2,"Pedro", 7000.00,false);
		assertEquals(7140.0,f.getSalarioBruto());
	}
	
	@Test
	void testeGetSalarioBrutoInsalubridade() {
		Funcionario f = new Funcionario(112233,0,"Pedro", 5000.00,true);
		assertEquals(5500.0,f.getSalarioBruto());
	} 
	
	
	@Test
	void testeGetSalarioLiquidoFaixa1() {
		Funcionario f = new Funcionario(112233,0,"Pedro", 1000.00,false);
		assertEquals(955.0,f.getSalarioLiquido());
	}
	
	@Test
	void testeGetSalarioLiquidoFaixa2() {
		Funcionario f = new Funcionario(112233,0,"Pedro", 4000,false);
		assertEquals(3340.0,f.getSalarioLiquido());
	}
	
	@Test
	void testeGetSalarioLiquidoFaixa3() {
		Funcionario f = new Funcionario(112233,0,"Pedro", 6000,false);
		assertEquals(4080.0,f.getSalarioLiquido());
	}
	
	@Test
	void testeGetSalarioLiquidoRico() {
		Funcionario f = new Funcionario(112233,0,"Pedro", 150000,false);
		assertEquals(103750.0,f.getSalarioLiquido());
	}
	
	@Test
	void testeGetSalarioLiquido1Dependente() {
		Funcionario f = new Funcionario(112233,1,"Pedro", 1000,false);
		assertEquals(965.0,f.getSalarioLiquido());
	}
	
	@Test
	void testeGetSalarioLiquido2Dependentes() {
		Funcionario f = new Funcionario(112233,2,"Pedro", 1000,false);
		assertEquals(975.0,f.getSalarioLiquido());
	} 
	
	@Test
	void testeGetSalarioLiquidoRicoInsalubridade() {
		Funcionario f = new Funcionario(112233,0,"Pedro", 150000,true);
		assertEquals(118750.0,f.getSalarioLiquido());
	}
	
	@Test
	void testeGetSalarioLiquido1DependenteInsalubridade() {
		Funcionario f = new Funcionario(112233,1,"Pedro", 1000,true);
		assertEquals(1065.0,f.getSalarioLiquido());
	}
	
	@Test
	void testeGetSalarioLiquido2DependentesInsalubridade() {
		Funcionario f = new Funcionario(112233,2,"Pedro", 1000,true);
		assertEquals(1075.0,f.getSalarioLiquido());
	} 
	
	@Property
	boolean oSalarioLiquidoDeveSerMenorQueOBruto(@ForAll @DoubleRange(min=964.0,max=30000.0) double umSalario)
	{
		Funcionario f = new Funcionario(112233,2,"Pedro", (double)umSalario,true);
	    return f.getSalarioLiquido() < f.getSalarioBruto();
	}
	
	@Property(tries=500)
	boolean oSalarioLiquidoDeveSerDiferenteDoBruto(@ForAll @DoubleRange(min=964.0,max=30000.0) double umSalario)
	{
		Funcionario f = new Funcionario(112233,2,"Pedro", (double)umSalario,true);
	    return f.getSalarioBruto() != f.getSalarioLiquido();
	}
	
	@Property(tries=750)
	boolean oSalarioCom1DependenteDeveSerMenorQueCom2Dependentes(@ForAll @DoubleRange(min=964.0,max=30000.0) double umSalario)
	{
		Funcionario f1 = new Funcionario(112233,1,"Pedro", (double)umSalario,false);
		Funcionario f2 = new Funcionario(112233,2,"Pedro", (double)umSalario,false);
	    return f2.getSalarioBruto() > f1.getSalarioBruto();
	}
} 
   