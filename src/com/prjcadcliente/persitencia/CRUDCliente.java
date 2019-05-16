package com.prjcadcliente.persitencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.prjcadcliente.dominio.Cliente;

/**
 * <b>CRUDCliente</b><br>
 * Essa classe permite manipular as informaçoes do cliente. Aqui voce encontrará
 * os seguintes comandos:
 * <ul>
 * <li>Cadastro</li>
 * <li>Pesquisar por nome e por id</li>
 * <li>Atualizar</li>
 * <li>Deletar</li>
 * </ul>
 * 
 * @author thiago.jbezerra
 *
 */

public class CRUDCliente {

	// Declaração das instancias de comunicação com o banco
	// de dados
	private Connection con = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;

	public String cadastrar(Cliente cliente) {

		String msg = "";

		// Criação dos objetos para a conexao com o banco de dados
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();// pega a pasta do driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/clientedb", "root", "");// Realiza a conexão
																									// do driver

			String consulta = "INSERT INTO tbcliente(nome,email,telefone,idade)values(?,?,?,?)";// Trazer o resultado da
																								// tabela

			pst = con.prepareStatement(consulta);

			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getEmail());
			pst.setString(3, cliente.getTelefone());
			pst.setInt(4, cliente.getIdade());

			int r = pst.executeUpdate();

			if (r > 0)
				msg = "Cadastro realizo com sucesso";
			else
				msg = "Não foi possivel cadastrar";

		} catch (SQLException ex) {
			msg = "Erro ao tentar cadastrar: " + ex.getMessage();// mostrar mensagem de erro
		} catch (Exception e) {
			msg = "Erro inesperado:" + e.getMessage();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			} // quando terminar de processar o banco, ele fecha
		}

		return msg;
	}

	public String atualizar(Cliente cliente) {
		String msg = "";

		// Criação dos objetos para a conexao com o banco de dados
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();// pega a pasta do driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/clientedb", "root", "");// Realiza a conexão
																									// do driver

			String consulta = "UPDATE tbcliente SET nome=?,email=?,telefone=?,idade =?)WHERE id =?";// Trazer o
																									// resultado da
																									// tabela

			pst = con.prepareStatement(consulta);

			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getEmail());
			pst.setString(3, cliente.getTelefone());
			pst.setInt(4, cliente.getIdade());

			int r = pst.executeUpdate();

			if (r > 0)
				msg = "Atualizado com sucesso";
			else
				msg = "Não foi possivel atualizar";

		} catch (SQLException ex) {
			msg = "Erro ao tentar cadastrar: " + ex.getMessage();// mostrar mensagem de erro
		} catch (Exception e) {
			msg = "Erro inesperado:" + e.getMessage();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			} // quando terminar de processar o banco, ele fecha
		}

		return msg;
	}

	public String deletar(Cliente cliente) {
		String msg = "";

		// Criação dos objetos para a conexao com o banco de dados
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();// pega a pasta do driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/clientedb", "root", "");// Realiza a conexão
																									// do driver

			String consulta = "DELETE FROM TBCCLIENTE WHERE ID=?";// Trazer o resultado da tabela

			pst = con.prepareStatement(consulta);

			pst.setString(1, cliente.getNome());

			int r = pst.executeUpdate();

			if (r > 0)
				msg = "DELETADO  com sucesso";
			else
				msg = "Não foi possivel deletar";

		} catch (SQLException ex) {
			msg = "Erro ao tentar cadastrar: " + ex.getMessage();// mostrar mensagem de erro
		} catch (Exception e) {
			msg = "Erro inesperado:" + e.getMessage();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			} // quando terminar de processar o banco, ele fecha
		}

		return msg;
	}

	public List<Cliente> PesquisarPorNome(String nome) {
		
		List<Cliente> lista = new ArrayList<Cliente>();
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/clientedb", "root", "");
			
			
			String consulta = "Select * from tbcliente where nome=?";
			
			pst = con.prepareStatement(consulta);
			
			pst.setString(1, nome);
			
			rs = pst.executeQuery();
			
			/*
			 * vamos pegar um cliente por vez que esta no rs e adiciona lo 
			 * a lista de pegar clientas para enta retornala
			 */
			
			while(rs.next()) {
				lista.add(new Cliente(rs.getInt(0),rs.getNString(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
				
			}//FIM DO WHILE
		}//FIM DO TRY
		catch(SQLException ex) {
			ex.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			try {con.close();} catch(Exception e) {e.printStackTrace();}
		}
		
		
		
		return lista;
	}

	public Cliente PesquisarPorId(int id) {
		return null;
	}

	public List<Cliente> PesquisarTodos() {
List<Cliente> lista = new ArrayList<Cliente>();
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/clientedb", "root", "");
			
			
			String consulta = "Select * from tbcliente";
			
			pst = con.prepareStatement(consulta);
			
			
			
			rs = pst.executeQuery();
			
			/*
			 * vamos pegar um cliente por vez que esta no rs e adiciona lo 
			 * a lista de pegar clientas para enta retornala
			 */
			
			while(rs.next()) {
				lista.add(new Cliente(rs.getInt(0),rs.getNString(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
				
			}//FIM DO WHILE
		}//FIM DO TRY
		catch(SQLException ex) {
			ex.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			try {con.close();} catch(Exception e) {e.printStackTrace();}
		}
		
		
		
		return lista;
	}
}
