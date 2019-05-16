package com.prjcadcliente.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.prjcadcliente.dominio.Cliente;
import com.prjcadcliente.persitencia.CRUDCliente;

public class GerenciarCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtEmal;
	private JTextField txtTelefone;
	private JTextField txtIdade;
	private JTable tbClientes;
	private Cliente cliente;
	private CRUDCliente crud;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciarCliente frame = new GerenciarCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GerenciarCliente() {
		
		
		//vamos intanciar as classes Clienete e crud
		
		cliente = new Cliente();
		crud = new CRUDCliente();
		
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 412, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(119, 70, 48, 14);
		contentPane.add(label);
		
		JLabel lblCliente = new JLabel("Nome do Cliente:");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCliente.setBounds(10, 0, 96, 14);
		contentPane.add(lblCliente);
		
		txtNome = new JTextField();
		txtNome.setBounds(10, 25, 376, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(10, 56, 48, 14);
		contentPane.add(lblEmail);
		
		txtEmal = new JTextField();
		txtEmal.setColumns(10);
		txtEmal.setBounds(10, 80, 376, 20);
		contentPane.add(txtEmal);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTelefone.setBounds(10, 111, 55, 14);
		contentPane.add(lblTelefone);
		
		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(10, 136, 199, 20);
		contentPane.add(txtTelefone);
		
		txtIdade = new JTextField();
		txtIdade.setColumns(10);
		txtIdade.setBounds(10, 192, 112, 20);
		contentPane.add(txtIdade);
		
		JLabel lblIdade = new JLabel("Idade:");
		lblIdade.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIdade.setBounds(10, 167, 48, 14);
		contentPane.add(lblIdade);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//pASSA os dados do formulario para o objeto cliente
				cliente.setNome(txtNome.getText());
				cliente.setEmail(txtEmal.getText());
				cliente.setTelefone(txtTelefone.getText());
				cliente.setIdade(Integer.parseInt(txtIdade.getText()));
				String retorno = crud.cadastrar(cliente);
				JOptionPane.showConfirmDialog(null, retorno);
				
				txtEmal.setText("");
				txtTelefone.setText("");
				txtNome.setText("");
				txtIdade.setText("");
				
				
				
				
				
				
				
			}
		});
		btnCadastrar.setBounds(10, 223, 81, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String id = JOptionPane.showInputDialog("Digite o Id do cliente");
				
				//pASSA os dados do formulario para o objeto cliente
				cliente.setNome(txtNome.getText());
				cliente.setEmail(txtEmal.getText());
				cliente.setTelefone(txtTelefone.getText());
				cliente.setIdade(Integer.parseInt(txtIdade.getText()));
				String retorno = crud.atualizar(cliente);
				cliente.setId(Integer.parseInt(id));	
				JOptionPane.showConfirmDialog(null, retorno);
				txtEmal.setText("");
				txtTelefone.setText("");
				txtNome.setText("");
				txtIdade.setText("");
				id="0";
				
				
				
				
			}
		});
		btnAtualizar.setBounds(97, 223, 89, 23);
		contentPane.add(btnAtualizar);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				String id = JOptionPane.showInputDialog("Digite o ID do cliente para apagar");
				cliente.setId(Integer.parseInt(id));
				
				JOptionPane.showConfirmDialog(null, crud.deletar(cliente));
				
				
			}
		});
		btnDelete.setBounds(196, 223, 89, 23);
		contentPane.add(btnDelete);
		
		JButton bntPesquisar = new JButton("Pesquisar");
		bntPesquisar.setBounds(297, 223, 89, 23);
		contentPane.add(bntPesquisar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 257, 376, 203);
		contentPane.add(scrollPane);
		
		
		String[] colunas = {"Id","nome","E-Mail","Telefone","Idade"};
		
		Object[][] dados = {
				
				{15,"Guilherme","guilherme@gmail.com","11",12},
				{55,"Guilherme","guilherme@gmail.com","11",12},
				{7,"Guilherme","guilherme@gmail.com","11",12},
				{25,"Guilherme","guilherme@gmail.com","11",12},
		};
		
		
		//vamos contruir o modelo de dados para exibir na tabela
		
		DefaultTableModel modelo = new DefaultTableModel(dados,colunas);
		tbClientes = new JTable(modelo);
		scrollPane.setViewportView(tbClientes);
	}
}
