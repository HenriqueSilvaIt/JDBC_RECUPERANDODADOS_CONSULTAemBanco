package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class ProgramJDBC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		// Como os recursos abaixo Connection, Statement e ResultSet
		// São recursos que não são controlados pela JVM do java
		// é interessante que fechamos esses recursos manualmente
		// para evitar que o programa tenha algum tipo de vazamento de memoria
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		//Todas as operações que acessam banco de dados(Recurso externo)
		// pode gerar exceções por isso é importante criar um bloco try
		try {
			conn = DB.getConnection(); // Faz conexão com o banco (propriedades do banco
			// está no db.proprieties
			
			st = conn.createStatement(); // Instanciando objeto do tipo Statement
			
			rs = st.executeQuery("SELECT * FROM departament");
			// Esse metodo espera que colocamos um comando sql
				// quando colocamos o rs como variavel, estamos armezando
				// o resultado da consulta no resultSet
			
			while (rs.next()) { //result set por padrão começa na posição 0
				// para percorrer esses dados usamos a função next e ai ele vai percorrer
				// até da falso, porque o next retorna falso quando ta no ultimo
				System.out.println(rs.getInt("Id") + " | " + rs.getString("Name"));
				// para imprimir as 2 colunar da tabela, rs.getId("nomedaColuna")
				// e depois contatena com o nome da outra coluna
				// o get deve ser seguido do tipo de dados da coluna int, string ou etc
			}
			
			}
		catch (SQLException s) {
			s.printStackTrace();// ele vai imprimi o stackTraCE COM as mensagems de erro
		} 
		
		// Como os recursos abaixo Connection, Statement e ResultSet
				// São recursos que não são controlados pela JVM do java
				// é interessante que fechamos esses recursos manualmente
				// para evitar que o programa tenha algum tipo de vazamento de memoria
		//Fechando recursos e externos e conexões
		finally {
			DB.closeResultSet(rs); // como essas declarações pode gerar exceções
			// ao inves de criar try catch para cada uma delas 
			// o ideal é ir na classe db criar um método para fechar
			// esses recursos
			DB.closeStatement(st);
			DB.closeConnection();
			// m
		}
		
	}

}
