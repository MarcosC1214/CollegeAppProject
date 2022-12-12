package view;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.SwingUtilities;

import view.StartUp;



public class Main {

	public static void main(String[] args) throws Exception {
		
		
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    new StartUp().initUI();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }
	

	
}