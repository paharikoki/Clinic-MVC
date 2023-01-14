/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klinik.View;

import Klinik.View.Auth.Login;
import Klinik.View.Doctor.MainDoctor;
import Klinik.View.Patient.MainPatient;
import Klinik.View.Visit.MainVisit;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Administrator
 */
public class MainView extends javax.swing.JFrame {

    /**
     * Creates new form MainView
     */
    public MainView() {
        initComponents();
        viewWhenClose();
    }
    public void viewMainDoctor(){
        this.setVisible(false);
        new MainDoctor().setVisible(true);
    }
    public void viewMainPatient() {
        this.setVisible(false);
        new MainPatient().setVisible(true);
    }
    public void viewMainVisit(){
        this.setVisible(false);
        new MainVisit().setVisible(true);
    }
    public void viewLogout(){
        this.setVisible(false);
        new Login().setVisible(true);
    }
    public void viewWhenClose(){
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                if (validateClose()){
                    dispose();
                }
            }
            public boolean validateClose() {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to close this view? \n Your activity are close all", "Confirm Close View", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    return true;
                }else{
                    JOptionPane.showMessageDialog(null, "Closing view cancelled by user.");
                    return false;
                }
            }
        });
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
        btnViewVisit = new javax.swing.JButton();
        btnViewDoctor = new javax.swing.JButton();
        btnVIewPatient = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Main Form");

        btnViewVisit.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        btnViewVisit.setText("Visit");
        btnViewVisit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewVisitActionPerformed(evt);
            }
        });

        btnViewDoctor.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        btnViewDoctor.setText("Doctor");
        btnViewDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDoctorActionPerformed(evt);
            }
        });

        btnVIewPatient.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        btnVIewPatient.setText("Patient");
        btnVIewPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVIewPatientActionPerformed(evt);
            }
        });

        btnLogout.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(397, 397, 397))
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(btnViewDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVIewPatient, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(btnViewVisit, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(238, 238, 238)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnViewVisit, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVIewPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDoctorActionPerformed
        // TODO add your handling code here:
        viewMainDoctor();
    }//GEN-LAST:event_btnViewDoctorActionPerformed

    private void btnVIewPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVIewPatientActionPerformed
        // TODO add your handling code here:
        viewMainPatient();
    }//GEN-LAST:event_btnVIewPatientActionPerformed

    private void btnViewVisitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewVisitActionPerformed
        // TODO add your handling code here:
        viewMainVisit();
    }//GEN-LAST:event_btnViewVisitActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        viewLogout();
    }//GEN-LAST:event_btnLogoutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnVIewPatient;
    private javax.swing.JButton btnViewDoctor;
    private javax.swing.JButton btnViewVisit;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
