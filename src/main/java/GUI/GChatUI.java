package GUI;

import GCLIENT.GClient_Process;
import com.mycenter.gobject.GPacket;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.DefaultListModel;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

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
//        textArea.requestFocus();
        board.append("\n<" + listActiveUser.getSelectedValue() + ">\n");//======================== test ==========================
        //=========================================================================================================================
        mainTabbed.add(listActiveUser.getSelectedValue(), new ChatTab(listActiveUser.getSelectedValue(), CLIENT_PROCESS));// khi form khoi dong thi tao tab dau tien la tab "All"

    }

    public void PrintIntoTab(String who, String content, int lr) {
        ChatTab here = findTab(who);
        if (here == null) {// chua co tab voi nguoi nay
            here = (ChatTab) mainTabbed.add(who, new ChatTab(who, CLIENT_PROCESS));
        }
        DefaultTableModel model_table_chat = (DefaultTableModel) here.tablechat.getModel();
        if (lr == 0) {
            model_table_chat.addRow(new Object[]{content, ""});
        } else {
            model_table_chat.addRow(new Object[]{"", content});
        }
    }



    public ChatTab findTab(String who) {
        int index = mainTabbed.indexOfTab(who);
        ChatTab here = null;
        if (index != -1) {
            here = (ChatTab) mainTabbed.getComponent(index);
        }
        return here;
    }

    public void SetEnableInput(boolean en) {

    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        board = new javax.swing.JTextArea();
        mainTabbed = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        listActiveUser = new javax.swing.JList<>();
        bthihi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(545, 750));
        setPreferredSize(new java.awt.Dimension(748, 750));
        setResizable(false);
        setSize(new java.awt.Dimension(545, 750));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        board.setEditable(false);
        board.setColumns(20);
        board.setRows(5);
        jScrollPane1.setViewportView(board);

        mainTabbed.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

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

        bthihi.setText("hihi");
        bthihi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthihiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bthihi))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mainTabbed, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mainTabbed, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bthihi)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát?", "CHÚ Ý", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            CLIENT_PROCESS.send(new GPacket("BYE BYE !", LOGIN_GUI.USERNAME));
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            LOGIN_GUI.stop();
        } else {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }//GEN-LAST:event_formWindowClosing

    private void listActiveUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listActiveUserMouseClicked
        String select = listActiveUser.getSelectedValue();
        board.append(select + "\n");// ==================================================test ==========================================
        int index = mainTabbed.indexOfTab(select);
        if (index == -1) {
            index = mainTabbed.getTabCount();
            mainTabbed.add(select, new ChatTab(select, CLIENT_PROCESS));

        }
        mainTabbed.setSelectedIndex(index);


    }//GEN-LAST:event_listActiveUserMouseClicked

    private void bthihiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthihiActionPerformed
        CLIENT_PROCESS.send(new GPacket("LOGOUT"));
        CLIENT_PROCESS.LogOut();
    }//GEN-LAST:event_bthihiActionPerformed
//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea board;
    private javax.swing.JButton bthihi;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listActiveUser;
    private javax.swing.JTabbedPane mainTabbed;
    // End of variables declaration//GEN-END:variables
}
// </editor-fold>
