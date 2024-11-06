import javax.swing.SwingUtilities; //import para interface grafica 

public class Main { //class main 
	//metodo para rodar a aplicação grafica 
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {//responsável pela manipulação de componentes da interface gráfica
				new FrutaManagerGUI();//interface grafica é inicializada 
		
			}
		});

	}

}
