package GUI;

import GCLIENT.GClient_Process;
import com.mycenter.gobject.GPacket;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
//        MODEL_LIST_ACTIVE_USERs.addElement("<Tất cả>");
//        listActiveUser.setSelectedIndex(0);
//        textArea.requestFocus();
        board.append("\n<" + listActiveUser.getSelectedValue() + ">\n");//======================== test ==========================
        mainTabbed.add("<Tất cả>", new ChatTab("<Tất cả>", CLIENT_PROCESS));// khi form khoi dong thi tao tab dau tien la tab "All"
        
//============================ su kien re chuot qua item ======================================
        listActiveUser.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent me) {
                Point p = me.getPoint();
                listActiveUser.setSelectedIndex(listActiveUser.locationToIndex(p));
                listActiveUser.setSelectionForeground(Color.RED);
                listActiveUser.setSelectionBackground(Color.BLUE);
                System.out.println("" + listActiveUser.getSelectedValue());
            }
        });
//=============================================================================================        
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
        mainTabbed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainTabbedMouseClicked(evt);
            }
        });

        listActiveUser.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đang online", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 0, 12), new java.awt.Color(0, 127, 5))); // NOI18N
        listActiveUser.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        listActiveUser.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "<All>" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listActiveUser.setToolTipText("Người dùng đang hoạt động");
        listActiveUser.setFocusable(false);
        listActiveUser.setRequestFocusEnabled(false);
        listActiveUser.setSelectionBackground(new java.awt.Color(69, 73, 74));
        listActiveUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listActiveUserMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                listActiveUserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                listActiveUserMouseExited(evt);
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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void listActiveUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listActiveUserMouseEntered
        System.out.println(listActiveUser.getSelectedValue());

    }//GEN-LAST:event_listActiveUserMouseEntered

    private void listActiveUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listActiveUserMouseExited
        listActiveUser.setSelectionForeground(new Color(187, 187, 187));
        listActiveUser.setSelectionBackground(new Color(69, 73, 74));
        System.out.println("tab count :"+mainTabbed.getTabCount());
        System.out.println("cpn count:"+mainTabbed.getComponentCount());
//        for (int i = 0; i < mainTabbed.getTabCount(); i++) {
//            mainTabbed.getComponentAt(i).setForeground(new Color(187, 187, 187));
//        }
//        mainTabbed.setForegroundAt(mainTabbed.getSelectedIndex(), Color.CYAN);
    }//GEN-LAST:event_listActiveUserMouseExited

    private void mainTabbedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainTabbedMouseClicked
//        for (int i = 0; i < mainTabbed.getTabCount(); i++) {
//            mainTabbed.getComponentAt(i).setForeground(new Color(187, 187, 187));
//        }
        mainTabbed.setForegroundAt(mainTabbed.getSelectedIndex(), new Color(187,187,187));
    }//GEN-LAST:event_mainTabbedMouseClicked
//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea board;
    private javax.swing.JButton bthihi;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listActiveUser;
    public javax.swing.JTabbedPane mainTabbed;
    // End of variables declaration//GEN-END:variables
}
// </editor-fold>
