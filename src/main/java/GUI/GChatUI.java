package GUI;

import GCLIENT.GClient_Process;
import com.mycenter.gobject.GPacket;
import javax.swing.DefaultListModel;

/**
 *
 * @author Gic
 */
public class GChatUI extends javax.swing.JFrame {

    GClient_Process CLIENT_PROCESS = null;
    GLoginUI LOGIN_GUI = null;
    public DefaultListModel MODEL_LIST_ACTIVE_USERs = null;

    public GChatUI(GLoginUI top_ui, GClient_Process pc) {
        initComponents();
        CLIENT_PROCESS = pc;
        LOGIN_GUI = top_ui;
        setLocationRelativeTo(null);

        this.setTitle(top_ui.USERNAME);
        listActiveUser.setModel(MODEL_LIST_ACTIVE_USERs = new DefaultListModel());
        MODEL_LIST_ACTIVE_USERs.addElement("<Tất cả>");
        listActiveUser.setSelectedIndex(0);

        tbmain.append("\n<" + listActiveUser.getSelectedValue() + ">\n");

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbmain = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        listActiveUser = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(700, 700));
        setPreferredSize(new java.awt.Dimension(700, 700));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tbmain.setColumns(20);
        tbmain.setRows(5);
        jScrollPane1.setViewportView(tbmain);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        listActiveUser.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listActiveUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listActiveUserMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listActiveUser);

        getContentPane().add(jScrollPane2, java.awt.BorderLayout.LINE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        CLIENT_PROCESS.send(new GPacket("BYE BYE !", LOGIN_GUI.USERNAME));
        LOGIN_GUI.stop();
    }//GEN-LAST:event_formWindowClosing

    private void listActiveUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listActiveUserMouseClicked
        String select = listActiveUser.getSelectedValue();
        tbmain.append(select);
    }//GEN-LAST:event_listActiveUserMouseClicked
//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listActiveUser;
    public javax.swing.JTextArea tbmain;
    // End of variables declaration//GEN-END:variables
}
// </editor-fold>
