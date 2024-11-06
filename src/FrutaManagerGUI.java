import javax.swing.*;  //biblioteca swing 
import java.awt.*; //manipulação grafica 
import java.awt.event.ActionEvent; //tratamento de eventos 
import java.awt.event.ActionListener;
import java.util.ArrayList; //import do array lsit 


public class FrutaManagerGUI {
	private ArrayList<String> frutas; // array para as frutas 
	private DefaultListModel<String> listModel; //interface grafica para exibir a lista 
	private JList<String> list; //exibe as frutas para o usuario 
	
	public FrutaManagerGUI() { 
		frutas = new ArrayList<>(); //istanciando a lista
		listModel = new DefaultListModel<>();
		
		//Jframe para configurar a janela em geral 
		JFrame frame = new JFrame ("Gerenciador de frutas na lista");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//fechar janela 
		frame.setSize(600, 300); //proporção da tabela
		frame.setLayout(new BorderLayout()); 
		
		//define o layout do painel 
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		//Jpanel para inserir algo escrito 
		JTextField frutaField = new JTextField(15);
		panel.add(frutaField);
		//botão para adicionar 
		JButton addButton = new JButton("Adicionar");
		panel.add(addButton);
		//botão para modificar 
		JButton modifyButton = new JButton("Modificar");
		modifyButton.setEnabled(false);
		panel.add(modifyButton);
		//botão para remover 
		JButton removeButton = new JButton("Remover");
		removeButton.setEnabled(false);
		panel.add(removeButton);
		
		//para adicionar tudo ao painel o botão e campo para o texto  
		frame.add(panel, BorderLayout.NORTH); 
		list = new JList<>(listModel);
		JScrollPane scrollPane = new JScrollPane(list);
		frame.add(scrollPane, BorderLayout.CENTER);
		
		//botão para listar as frutas
		JButton listButton = new JButton("Listar Frutas");
		frame.add(listButton, BorderLayout.SOUTH);
		
		//execução do botão adicionar 
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String novaFruta = frutaField.getText();
				if (!novaFruta.isEmpty()) {
					frutas.add(novaFruta); //adicionar uma nova fruta no array 
					listModel.addElement(novaFruta);//adiciona o elemento no painel
					frutaField.setText("");//limpa o campo apos adicionar a fruta 
					JOptionPane.showMessageDialog(frame, novaFruta + " foi adicionada. ");//mensagem de confirmação
				}
				}
		});
		//execução do botão modificar 
		modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { //verifica o item selecionado 
				int selectedIndex = list.getSelectedIndex();
				if (selectedIndex != -1) { //verificar se não tem item selecionado 
					String frutasSelecionada = listModel.getElementAt(selectedIndex);
					String novaFruta = JOptionPane.showInputDialog(frame, "Modificar" + frutasSelecionada + " para:", frutasSelecionada);// obtém o nome da fruta selecionada.
					if (novaFruta != null && !novaFruta.isEmpty()) { //verifica se o nome da fruta é valido 
						frutas.set(selectedIndex, novaFruta); //atualiza a fruta no array 
						listModel.set(selectedIndex, novaFruta); //atualiza o modelo da fruta 
						JOptionPane.showMessageDialog(frame, "Fruta "+frutasSelecionada+" foi modificade para "+novaFruta);//retorna para o usuario 
					}
				} else { //caso o usuario não selecione uma fruta 
					JOptionPane.showMessageDialog(frame, "Selecione uma fruta para modificar.");	
				}
			}
		});
		
		removeButton.addActionListener(new ActionListener () {
		//execução do botão remover 
			@Override
			public void actionPerformed(ActionEvent e) { 
				int selectedIndex = list.getSelectedIndex();//verifica o item selecionado 
				if(selectedIndex != -1) { //verifica se não tem nada selecionado 
					String frutaRemovida = frutas.remove(selectedIndex); //remove a furta do array 
					listModel.remove(selectedIndex);
					JOptionPane.showMessageDialog(frame, frutaRemovida + " foi removida. ");//retorna para o usuario 	
				}else {
					JOptionPane.showMessageDialog(frame, "Selecione uma fruta para remover. ");//caso o usuario não selecione nada 
				}
			}
		});
		//ação para listar a fruta 
		listButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e ) {
				if (frutas.isEmpty()) { //verifica se existe frutas na lista 
					JOptionPane.showMessageDialog(frame, "Nenhuma fruta na lista. ");//retorna para o usuario se não tiver frutas 
				} else {
					JOptionPane.showMessageDialog(frame, "Frutas: "+ frutas);//exibe todas as frutas da lista 
				}
			}
		});
		list.addListSelectionListener(e ->{
			boolean selectionExists = !list.isSelectionEmpty(); //verifica se há item selecionado 
			removeButton.setEnabled(selectionExists); //habilita e desabilita o botão 
			modifyButton.setEnabled(selectionExists); //habilita e desabilita o botão 
		});
		frame.setVisible(true);	//torna a janela visivel 
	}
	
}

