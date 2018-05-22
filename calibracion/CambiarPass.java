/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calibracion;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author d.kuczerawy
 */
public class CambiarPass extends javax.swing.JFrame {

    /**
     * Creates new form CambiarPass
     */
    public CambiarPass() {
        initComponents();
    }

    CambiarPass(VentPrincipal o) {
        initComponents();
        ventppal = o;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        anteriorPasswordField = new javax.swing.JPasswordField();
        nuevoPasswordField = new javax.swing.JPasswordField();
        nuevoRepetirPasswordField = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        leyendaLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cambiar contraseña");
        setAlwaysOnTop(true);
        setType(java.awt.Window.Type.UTILITY);

        jLabel1.setText("Escriba su contraseña:");

        jLabel2.setText("Escriba la nueva contraseña:");

        jLabel3.setText("Repita la nueva contraseña:");

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        leyendaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(56, 56, 56))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(leyendaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(anteriorPasswordField)
                            .addComponent(nuevoPasswordField)
                            .addComponent(nuevoRepetirPasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(anteriorPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nuevoPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nuevoRepetirPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(leyendaLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        cerrarVentana();
    }//GEN-LAST:event_cancelarActionPerformed

    private void cerrarVentana(){
        ventppal.setEnabled(true);
        ventppal.toFront();
        dispose();
    }
    
    private void aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarActionPerformed
        if(passToString(nuevoPasswordField).equals("") || passToString(nuevoRepetirPasswordField).equals("") || passToString(anteriorPasswordField).equals("")){
            leyendaLabel.setText("Los campos están vacios.");
        }else if(passToString(nuevoPasswordField).equals(passToString(anteriorPasswordField))){
            leyendaLabel.setText("La contraseña a modificar es igual a la anterior.");
        }else if(passToString(nuevoPasswordField).equals(passToString(nuevoRepetirPasswordField)) && passToString(anteriorPasswordField).equals(ventppal.contraseña)){
            modificarContraseña();
            cerrarVentana();
        }else{ 
            leyendaLabel.setText("Las contraseñas no coinciden.");
        }
            
    }//GEN-LAST:event_aceptarActionPerformed
    
    private void modificarContraseña(){
        ventppal.sql = "UPDATE Usuario "+"SET Usuario.[Contraseña] = ?" + " WHERE Usuario.[Usuario] = ?";
        PreparedStatement pstm;
        try {
            pstm = ventppal.conexion.prepareStatement( ventppal.sql );
            pstm.setString(1, passToString(nuevoPasswordField));
            pstm.setString(2, ventppal.usuario);
            int res = pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(CambiarPass.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
    }
    
    private String passToString(javax.swing.JPasswordField password){
        String contraseña;
        StringBuffer cadena = new StringBuffer();
            char[] pass;
            pass = password.getPassword();
            for (int x=0;x<pass.length;x++){
            cadena =cadena.append(pass[x]);
        }
        contraseña = cadena.toString();        
        
        return contraseña;
    }
    
    /**
     * @param args the command line arguments
     */
    
    private VentPrincipal ventppal;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField anteriorPasswordField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel leyendaLabel;
    private javax.swing.JPasswordField nuevoPasswordField;
    private javax.swing.JPasswordField nuevoRepetirPasswordField;
    // End of variables declaration//GEN-END:variables
}