package GUI;

import GCLIENT.GClient_Process;
import com.mycenter.gobject.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Gic
 */
public class GLoginUI extends javax.swing.JFrame {

    int PORT = 0;
    public String IP = "localhost", USERNAME = "";
    String PASSWORD = "password";
    Thread THREAD_TO_RUN_THIS_PROCESS = null;
//    public GChatUI GUI = null;
    GClient_Process CLIENT_PROCESS = null;

    public GLoginUI() {
        initComponents();
        setLocationRelativeTo(null);
        tbPort.requestFocus();
        tbPort.selectAll();
    }

    public String getIP() {
        return IP;
    }

    public int getPort() {
        return PORT;
    }

    public void stop() {
        THREAD_TO_RUN_THIS_PROCESS.stop();
        THREAD_TO_RUN_THIS_PROCESS = null;
        CLIENT_PROCESS = null;
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbServer = new javax.swing.JLabel();
        tbServer = new javax.swing.JTextField();
        lbPort = new javax.swing.JLabel();
        tbPort = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btConnect = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tbUsername = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btLogin = new javax.swing.JButton();
        btRegister = new javax.swing.JButton();
        tbPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Connect to Server"));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        lbServer.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbServer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbServer.setText("Server:");
        jPanel1.add(lbServer);

        tbServer.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tbServer.setText("localhost");
        jPanel1.add(tbServer);

        lbPort.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbPort.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbPort.setText("Port:");
        jPanel1.add(lbPort);

        tbPort.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tbPort.setText("9876");
        jPanel1.add(tbPort);
        jPanel1.add(jLabel1);

        btConnect.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btConnect.setText("Connect");
        btConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConnectActionPerformed(evt);
            }
        });
        jPanel1.add(btConnect);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Login /Register"));
        jPanel2.setMinimumSize(new java.awt.Dimension(668, 120));
        jPanel2.setPreferredSize(new java.awt.Dimension(668, 215));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Username");
        jLabel3.setEnabled(false);

        tbUsername.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tbUsername.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tbUsername.setText("tao");
        tbUsername.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Password");
        jLabel5.setEnabled(false);

        btLogin.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btLogin.setText("Login");
        btLogin.setToolTipText("Dang nhap");
        btLogin.setEnabled(false);
        btLogin.setMaximumSize(new java.awt.Dimension(94, 31));
        btLogin.setMinimumSize(new java.awt.Dimension(94, 31));
        btLogin.setPreferredSize(new java.awt.Dimension(94, 31));
        btLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLoginActionPerformed(evt);
            }
        });

        btRegister.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btRegister.setText("Register");
        btRegister.setToolTipText("Dang ky");
        btRegister.setEnabled(false);
        btRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRegisterActionPerformed(evt);
            }
        });

        tbPassword.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tbPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tbPassword.setText("password");
        tbPassword.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(206, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(btRegister))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tbUsername)
                            .addComponent(tbPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))))
                .addContainerGap(218, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tbUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tbPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btRegister))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConnectActionPerformed
        try {
            PORT = Integer.parseInt(tbPort.getText().trim());
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(this, "Port không hợp lệ\n" + numberFormatException.getMessage());
            return;
        }
        if (!tbServer.getText().trim().isEmpty() && !tbServer.getText().trim().isBlank() && PORT > 0) {
            IP = tbServer.getText().trim();
            try {
                CLIENT_PROCESS = new GClient_Process(this);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(btConnect, "Kết nối thất bại !\n" + ex.getMessage());
                return;
            }
            THREAD_TO_RUN_THIS_PROCESS = new Thread(CLIENT_PROCESS);
            THREAD_TO_RUN_THIS_PROCESS.start();
            CLIENT_PROCESS.send(new GPacket("HI THERE, I WANT CONNECT WITH U !"));
        }
    }//GEN-LAST:event_btConnectActionPerformed

    private void btLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLoginActionPerformed
        USERNAME = tbUsername.getText().trim();
        PASSWORD = tbPassword.getText().trim();
        if (USERNAME.isBlank() || USERNAME.isEmpty() || PASSWORD.isEmpty() || PASSWORD.isBlank()) {
            return;
        }
        CLIENT_PROCESS.send(new GPacket("I WANT TO LOG IN !", USERNAME, PASSWORD));
    }//GEN-LAST:event_btLoginActionPerformed

    private void btRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRegisterActionPerformed
        USERNAME = tbUsername.getText().trim();
        PASSWORD = tbPassword.getText().trim();
        if (USERNAME.isBlank() || USERNAME.isEmpty() || PASSWORD.isEmpty() || PASSWORD.isBlank()) {
            return;
        }
        CLIENT_PROCESS.send(new GPacket("I WANT TO REGISTER A NEW ACCOUNT !", USERNAME, PASSWORD));
    }//GEN-LAST:event_btRegisterActionPerformed

//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */

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
            java.util.logging.Logger.getLogger(GLoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GLoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GLoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GLoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GLoginUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btConnect;
    public javax.swing.JButton btLogin;
    public javax.swing.JButton btRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbPort;
    private javax.swing.JLabel lbServer;
    public javax.swing.JPasswordField tbPassword;
    public javax.swing.JTextField tbPort;
    public javax.swing.JTextField tbServer;
    public javax.swing.JTextField tbUsername;
    // End of variables declaration//GEN-END:variables
}
//</editor-fold>
