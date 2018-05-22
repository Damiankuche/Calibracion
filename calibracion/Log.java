/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calibracion;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author d.kuczerawy
 */
public class Log {
    public void logNuevo(String codigo,String usuario){
        FileWriter file = null;
        PrintWriter pw;
        DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        Date date = new Date();
        
        try{
            file = new FileWriter("Z:\\Calidad\\Calidad\\03-Calibración\\log.dat", true);
            String s = hourdateFormat.format(date)+" | Usuario "+usuario.toUpperCase()+" ingreso el calibre: "+codigo;
            pw = new PrintWriter(file);
            pw.println(s);
            
        }catch(IOException e){
            Logger.getLogger(VentPrincipal.class.getName()).log(Level.SEVERE, null, e);
        }
        finally{
            if(file != null){
                try {
                    file.close();
                } catch (IOException ex) {
                    Logger.getLogger(VentPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
    public void logDelete(String codigo, String usuario,boolean bajaInstrumento){
        FileWriter file = null;
        PrintWriter pw;
        DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        Date date = new Date();
        String s;
        try{
            file = new FileWriter("Z:\\Calidad\\Calidad\\03-Calibración\\log.dat", true);
            if(bajaInstrumento)
                s = hourdateFormat.format(date)+" | Usuario "+usuario.toUpperCase()+" dio de baja el calibre nº: "+codigo;
            else
                s = hourdateFormat.format(date)+" | Usuario "+usuario.toUpperCase()+" quito de la lista de instrumentos dados de baja el calibre nº: "+codigo;
            pw = new PrintWriter(file);
            pw.println(s);
            
        }catch(IOException e){
            Logger.getLogger(VentPrincipal.class.getName()).log(Level.SEVERE, null, e);
        }
        finally{
            if(file != null){
                try {
                    file.close();
                } catch (IOException ex) {
                    Logger.getLogger(VentPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
    public void logVerif(String codigo, String informe,String usuario){
        FileWriter file = null;
        PrintWriter pw;
        DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        Date date = new Date();
        
        try{
            file = new FileWriter("Z:\\Calidad\\Calidad\\03-Calibración\\log.dat", true);
            String s = hourdateFormat.format(date)+" | Usuario "+usuario.toUpperCase()+" ingreso el informe de verificación nº: "+informe+" del calibre nº: "+codigo;
            pw = new PrintWriter(file);
            pw.println(s);
            
        }catch(IOException e){
            Logger.getLogger(VentPrincipal.class.getName()).log(Level.SEVERE, null, e);
        }
        finally{
            if(file != null){
                try {
                    file.close();
                } catch (IOException ex) {
                    Logger.getLogger(VentPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
}
