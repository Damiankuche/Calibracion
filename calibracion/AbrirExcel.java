/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calibracion;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author d.kuczerawy
 */


public class AbrirExcel extends Thread {
    
    /**
     *
     * @param num_calibre
     * @throws java.lang.Exception
     */
    
    private void crear(File destino) {
        File origen = new File("Z:\\Calidad\\Calidad\\03-Calibración\\Incert\\INCERT VACIO.xls");
                try{
                    OutputStream out;
            try (InputStream in = new FileInputStream(origen)) {
                out = new FileOutputStream(destino);
                byte[] buf = new byte[1024];
                int len;
                while((len = in.read(buf)) > 0){
                    out.write(buf, 0, len);
                }
            }
                    out.close();
                }catch(IOException ex){
                    Logger.getLogger(VentPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                
                }
    }
    
    public void abrir(String num_calibre,String usuario) throws IOException {
    File file = new File("Z:\\Calidad\\Calidad\\03-Calibración\\Incert\\INCERT "+num_calibre+".xls");
    FileWriter escribir;
    PrintWriter pw;
    DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    Date date = new Date();
    if(!file.exists()){
        int res = JOptionPane.showConfirmDialog(null, "El archivo no existe. Desea crearlo?", "Confirmar creacion de archivo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        switch(res){
            case 0:{
                this.crear(file);
                JOptionPane.showMessageDialog(null,"Archivo creado.");
                escribir = new FileWriter("Z:\\Calidad\\Calidad\\03-Calibración\\log.dat", true);
            String s = hourdateFormat.format(date)+" | Usuario "+usuario.toUpperCase()+" creo el archivo INCERT "+num_calibre;
            pw = new PrintWriter(escribir);
            pw.println(s);
            pw.close();
            escribir.close();
            break;
            }
            case 1:{
                return;
            }
            case -1:{
                return;
            }
            
        }
        
    }
    
    try {
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            Logger.getLogger(VentPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
    
    
}
