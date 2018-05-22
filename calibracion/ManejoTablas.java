/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calibracion;

import java.awt.Component;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author d.kuczerawy
 */
public class ManejoTablas {
    
    public ManejoTablas(Connection con){
        conexion = con;
    }
    
    
    
    public JTable agregarTuplasATabla(ResultSet res,JTable table,int flag) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
        JTable tabla = new JTable(modelo);
        ResultSetMetaData metaDatos = res.getMetaData();
        int numeroColumnas = metaDatos.getColumnCount();
        Object etiquetas[] = new Object[numeroColumnas];
        for(int i = 0; i < numeroColumnas ; i++){
           etiquetas[i] = metaDatos.getColumnLabel(i + 1);
       }
       modelo.setColumnIdentifiers(etiquetas);
       table.setModel(modelo);
       int j=0;
       while(res.next())
            {
                
                Object[] datos = new Object[numeroColumnas];
                
                for(int row = 0;row<numeroColumnas;row++ ){
                    switch(row){
                        case 9:{
                            if(flag == REFRESH){
                                
                                Object aux;
                                aux = res.getObject(row+1);
                                if(aux == null)
                                    break;
                                String[] a = aux.toString().split(" ");
                                a = a[0].split("-");
                                String fecha = a[2]+"/"+a[1]+"/"+a[0];
                                datos[row] = fecha;
                                break;
                            }
                            datos[row]=res.getObject(row+1);
                            break;
                        }
                        case 10:{
                            Object aux;
                            aux = res.getObject(row+1);
                            if(aux == null)
                                break;
                            String[] a = aux.toString().split(" ");
                            a = a[0].split("-");
                            String fecha = a[2]+"/"+a[1]+"/"+a[0];
                            datos[row] = fecha;
                            
                            break;
                        }
                        case 11:{
                            if(flag == BAJA){
                                Object aux;
                                aux = res.getObject(row+1);
                                if(aux == null)
                                    break;
                                String[] a = aux.toString().split(" ");
                                a = a[0].split("-");
                                String fecha = a[2]+"/"+a[1]+"/"+a[0];
                                datos[row] = fecha;
                                break;
                            }
                            if(REFRESH == 1){
                                datos[row] = setearVencimiento(datos);
                                break;
                            }
                            datos[row]=res.getObject(row+1);
                            break;
                        }
                        case 16:{
                            Object aux;
                            aux = res.getObject(row+1);
                            if(aux == null)
                                break;
                            String[] a = aux.toString().split(" ");
                            a = a[0].split("-");
                            String fecha = a[2]+"/"+a[1]+"/"+a[0];
                            datos[row] = fecha;
                            
                        }
                        case 17:{
                            Object aux;
                            aux = res.getObject(row+1);
                            if(aux == null)
                                break;
                            String[] a = aux.toString().split(" ");
                            a = a[0].split("-");
                            String fecha = a[2]+"/"+a[1]+"/"+a[0];
                            datos[row] = fecha;
                            if(flag == REFRESH)
                                datos[row] = setearVencimiento(datos);
                            break;
                        }
                        default:{
                            datos[row]=res.getObject(row+1);
                            break;
                        }
                    }
                  
                }
                
                modelo.addRow(datos);
                
            }
       modelo.setColumnIdentifiers(etiquetas);
       table.setModel(modelo);
       return table;
       
    }
    
    public JTable resizeTable(JTable table){
        
        table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        AffineTransform affinetransform = new AffineTransform(); 
        Font font = new Font("Arial Black", Font.PLAIN, 12);
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        for (int column = 0; column < table.getColumnCount(); column++)
        {
            TableColumn tableColumn = table.getColumnModel().getColumn(column);
            int preferredWidth = tableColumn.getMinWidth();
            int maxWidth = tableColumn.getMaxWidth();
            for (int row = 0; row < table.getRowCount(); row++)
            {
                TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
                Component c = table.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);
                int textwidth = (int)(font.getStringBounds(table.getColumnName(column), frc).getWidth());
                preferredWidth = Math.max(preferredWidth, textwidth);
//  We've exceeded the maximum width, no need to check other rows
                
                if (preferredWidth >= maxWidth)
                {
                    preferredWidth = maxWidth;
                    break;
                }
            }

            tableColumn.setPreferredWidth( preferredWidth );
        }
        return table;
    }
    
    
    private String setearVencimiento(Object[] datos) throws SQLException {
        String sql;
        int i = 0;
        Calendar hoy = new GregorianCalendar();
        Timestamp fecha;
        long dias;
        String []fechaVenc = datos[10].toString().split("/");
        fecha = new Timestamp(Integer.parseInt(fechaVenc[2])-1900, Integer.parseInt(fechaVenc[1])-1, Integer.parseInt(fechaVenc[0]), 0, 0, 0, 0);
        String vencimiento;
        //fecha = (Timestamp) datos[8];
        dias = TimeUnit.MILLISECONDS.toDays(fecha.getTime()- hoy.getTimeInMillis())+1;
        if(dias == 1){
                vencimiento = "'Vence en 1 día'";
            }
            else if(dias<1){
                vencimiento = "'Vencido'";
            }
            else{
                vencimiento = "'Vence en "+dias+" días'"; 
            }
            sql =  "UPDATE Verificación SET Vencimiento = " + vencimiento + " WHERE Código = '"+datos[0]+"' AND Num_informe = '"+datos[1]+"'";
            
            PreparedStatement pstm = conexion.prepareStatement(sql);
            pstm.executeUpdate();
            String[] venc = vencimiento.split("'"); //Si no se usa el split te devuelve las comillas simples en el string.
            return venc[1];
    }
    private final Connection conexion;
    static final int REFRESH = 1;
    static final int BUSCAR = 2;
    static final int BAJA = 3;
}
