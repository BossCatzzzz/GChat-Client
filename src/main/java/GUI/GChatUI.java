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

        int condition = JComponent.WHEN_FOCUSED;
        InputMap inputMap = mess_textarea.getInputMap(condition);
        ActionMap actionMap = mess_textarea.getActionMap();

        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        inputMap.put(enterKey, enterKey.toString());
        actionMap.put(enterKey.toString(), new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                board.append(mess_textarea.getText() + "\n");

                String msg = mess_textarea.getText();
                String target = listActiveUser.getSelectedValue();

                if (!msg.isEmpty() && !target.isEmpty()) {
                    
                    CLIENT_PROCESS.send(new GPacket("THIS IS MESSAGE", target, msg));
                }

//                CLIENT_PROCESS.send(new Package());

                JTextArea txtArea = (JTextArea) e.getSource();
                txtArea.setText("");

            }
        });

//        textArea.requestFocus();
        board.append("\n<" + listActiveUser.getSelectedValue() + ">\n");//======================== test ==========================
        //=========================================================================================================================
        mainTabbed.add(listActiveUser.getSelectedValue(), new ChatTab(listActiveUser.getSelectedValue()));// khi form khoi dong thi tao tab dau tien la tab "All"

    }

    public void PrintIntoTab(String who, String content, int lr) {
        ChatTab here = null;
        int index = mainTabbed.indexOfTab(who);
        if (index == -1) {// chua co tab voi nguoi nay
            index = mainTabbed.getTabCount();
            here = (ChatTab) mainTabbed.add(who, new ChatTab(who));
        } else {
            here = (ChatTab) mainTabbed.getComponent(index);
        }
        DefaultTableModel model_table_chat = (DefaultTableModel) here.tablechat.getModel();
        if (lr == 0) {
            model_table_chat.addRow(new Object[]{content, ""});
        } else {
            model_table_chat.addRow(new Object[]{"", content});
        }
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        board = new javax.swing.JTextArea();
        mainTabbed = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        listActiveUser = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        mess_textarea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(545, 750));
        setPreferredSize(new java.awt.Dimension(545, 750));
        setResizable(false);
        setSize(new java.awt.Dimension(545, 750));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        board.setEditable(false);
        board.setColumns(20);
        board.setRows(5);
        jScrollPane1.setViewportView(board);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 350, 200));
        getContentPane().add(mainTabbed, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 347, 410));

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

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 128, 410));

        mess_textarea.setColumns(20);
        mess_textarea.setLineWrap(true);
        mess_textarea.setRows(5);
        mess_textarea.setWrapStyleWord(true);
        jScrollPane3.setViewportView(mess_textarea);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 350, 40));

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
            mainTabbed.add(select, new ChatTab(select));

        }
        mainTabbed.setSelectedIndex(index);


    }//GEN-LAST:event_listActiveUserMouseClicked
//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea board;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> listActiveUser;
    private javax.swing.JTabbedPane mainTabbed;
    private javax.swing.JTextArea mess_textarea;
    // End of variables declaration//GEN-END:variables
}
// </editor-fold>
