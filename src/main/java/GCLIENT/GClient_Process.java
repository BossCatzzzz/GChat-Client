package GCLIENT;

import GUI.GChatUI;
import GUI.GLoginUI;
import com.mycenter.gobject.GPacket;
import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Gic vua update
 */
public class GClient_Process implements Runnable {

    GLoginUI LOGIN_GUI = null;
    GChatUI CHAT_GUI = null;
    Socket CLIENT_SOCKET = null;
    ObjectInputStream IN;
    ObjectOutputStream OUT;

    public GClient_Process(GLoginUI t) {
        LOGIN_GUI = t;
        boolean connected = false;
        for (int i = 0; i < 10; i++) {
            try {
                CLIENT_SOCKET = new Socket(InetAddress.getByName(LOGIN_GUI.getIP()), LOGIN_GUI.getPort());// *********************************************************************
//                CLIENT_SOCKET = new Socket(InetAddress.getByName(LOGIN_GUI.getIP()), LOGIN_GUI.getPort(),InetAddress.getByName(LOGIN_GUI.getIP()),56698);  //danh cho test ***************
                connected = true;
                break;
            } catch (IOException iOException) {
                Thread th = Thread.currentThread();
                System.out.println("" + i + "\n" + iOException.getMessage());
                try {
                    th.sleep(1000);
                } catch (InterruptedException ex) {
                }
            }
        }
        if (!connected) {
            JOptionPane.showMessageDialog(LOGIN_GUI, "\nKhông tìm thấy Server\n!!!");
            return;
        }
        try {
            OUT = new ObjectOutputStream(CLIENT_SOCKET.getOutputStream());
            OUT.flush();
            IN = new ObjectInputStream(CLIENT_SOCKET.getInputStream());
        } catch (IOException iOException) {
            System.out.println("\nLoi khi mo In/Out Stream:\n" + iOException.getMessage());
        }
//        hist = ui.hist;
    }

    @Override
    public void run() {// duoc chay khi thread nay dc goi start() *********************************************************************************
        boolean keepRunning = true;
        while (keepRunning) {// loop nay giu cho code ben trong run luon luon chay 
            GPacket PACKET = null;
            try {
                PACKET = (GPacket) IN.readObject();// run se pausse tai day, toi khi nhan dc pkg thi no chay tiep
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(LOGIN_GUI, "Đã ngắt kết nối Server:\n" + ex.getMessage());
                LOGIN_GUI.btConnect.setEnabled(keepRunning);
                LOGIN_GUI.tbPort.setEnabled(keepRunning);
                LOGIN_GUI.tbServer.setEnabled(keepRunning);
                LOGIN_GUI.tbUsername.setEnabled(!keepRunning);
                LOGIN_GUI.tbPassword.setEnabled(!keepRunning);
                LOGIN_GUI.btLogin.setEnabled(!keepRunning);
                LOGIN_GUI.btRegister.setEnabled(!keepRunning);
                LOGIN_GUI.tbPort.selectAll();
//                keepRunning = false;

//                System.out.println("so luong:" + CHAT_GUI.getContentPane().getComponentCount());
                CHAT_GUI.getContentPane().removeAll();// xoa tat ca cpn ben trong form chat khi ma server down
//                ui.clientThread.stop();
                ex.printStackTrace();// in  ra loi nhu bi vang loi
                return;
            } catch (ClassNotFoundException ex) {
                System.out.println("\nLỗi khi ép kiểu gói tin:\n" + ex.getMessage());
                JOptionPane.showMessageDialog(LOGIN_GUI, "Lỗi khi ép kiểu gói tin:\n" + ex.getMessage());
            }

            // xuong day tuc la da nhan goi tin oke 
            System.out.println("\t\t\t<<<<<" + PACKET.toString());
            switch (PACKET.getAction()) {// bat dau xu ly tung loai action cua pkg
                case "OKE BABE! YOUR CONNECTION ACCEPTED :)))":
                    JOptionPane.showMessageDialog(LOGIN_GUI, "Kết nối thành công tới máy chủ !!");
                    LOGIN_GUI.tbUsername.setEnabled(keepRunning);
                    LOGIN_GUI.tbPassword.setEnabled(keepRunning);
                    LOGIN_GUI.btRegister.setEnabled(keepRunning);
                    LOGIN_GUI.btLogin.setEnabled(keepRunning);
                    LOGIN_GUI.tbUsername.selectAll();
                    LOGIN_GUI.tbPort.setEnabled(!keepRunning);
                    LOGIN_GUI.tbServer.setEnabled(!keepRunning);
                    LOGIN_GUI.btConnect.setEnabled(!keepRunning);
                    break;
                case "LOGIN REPONSE":
                    if (PACKET.getFirst().equals("EXISTS")) {
                        JOptionPane.showMessageDialog(LOGIN_GUI, "Đăng nhập thất bại.\nTài khoản này đang hoạt động");
                        LOGIN_GUI.tbUsername.selectAll();
                        LOGIN_GUI.tbUsername.requestFocus();
                    } else if (PACKET.getFirst().equals("NO")) {
                        JOptionPane.showMessageDialog(LOGIN_GUI, "Đăng nhập thất bại.\nSai tên tài khoản hoặc mật khẩu");
                        LOGIN_GUI.tbUsername.selectAll();
                    } else {
//                        ui.menuDangXuat.setText("Đăng xuất (" + top.ip + ":" + top.port + ")");
//                        ui.jTabbedPane1.setSelectedIndex(0);
                        JOptionPane.showMessageDialog(LOGIN_GUI, "Đăng nhập thành công");
                        CHAT_GUI = new GChatUI(LOGIN_GUI, this);
                        CHAT_GUI.show();
                        LOGIN_GUI.hide();
                    }
                    break;
                case "REGISTER REPONSE":
                    if (PACKET.getFirst().equals("OKE!")) {
//                        ui.menuDangXuat.setText("Đăng xuất (" + top.ip + ":" + top.port + ")");
                        JOptionPane.showMessageDialog(LOGIN_GUI, "Đăng ký thành công.");
                    } else {
                        JOptionPane.showMessageDialog(LOGIN_GUI, "Đăng ký không thành công.\nTên đăng nhập đã tồn tại");
                    }
                    break;
                case "ACTIVE USERs":
                    if (!PACKET.getFirst().equals(LOGIN_GUI.USERNAME)) {// neu ma nhan dc new logged la chinh minh thi thoi
                        CHAT_GUI.MODEL_LIST_ACTIVE_USERs.addElement(PACKET.getFirst());
                        if (CHAT_GUI.findTab(PACKET.getFirst()) != null) {
                            CHAT_GUI.findTab(PACKET.getFirst()).SetEnableInput(true);
                        }
                    }
                    break;
                case "LOGOUT USERs":
                    if (PACKET.getFirst().equals(LOGIN_GUI.USERNAME)) {
                        //test ===>>> that ra if nay khong bh chay 
                    } else {
                        CHAT_GUI.MODEL_LIST_ACTIVE_USERs.removeElement(PACKET.getFirst());
                        CHAT_GUI.board.append("\n[" + PACKET.getFirst() + " đã thoát]\n");
                        if (CHAT_GUI.findTab(PACKET.getFirst()) != null) {// neu tab nay
                            CHAT_GUI.PrintIntoTab(PACKET.getFirst(), "\n[" + PACKET.getFirst() + " đã thoát]\n", 0);
                            CHAT_GUI.findTab(PACKET.getFirst()).SetEnableInput(false);
                        } else {
                            CHAT_GUI.PrintIntoTab("<Tất cả>", "\n[" + PACKET.getFirst() + " đã thoát]\n", 0);
                        }
                    }
                    break;
                case "THIS IS FILE":
//                    if (JOptionPane.showConfirmDialog(CHAT_GUI, ("Chấp nhận '" + PACKET.getLast() + "' từ " + msg.sender + " không?")) == 0) {
//                        JFileChooser jf = new JFileChooser();
//                        jf.setSelectedFile(new File(msg.content));
//                        int returnVal = jf.showSaveDialog(ui);
//
//                        String saveTo = jf.getSelectedFile().getPath();
//                        if (saveTo != null && returnVal == JFileChooser.APPROVE_OPTION) {
//                            System.out.println(">>>>>>>>>>>>>" + msg.sender);
//                            Download dwn = new Download(msg.sender, saveTo, ui);
//                            Thread t = new Thread(dwn);
//                            t.start();
//                            send(new Message("upload_res", ui.username, ("" + dwn.port), msg.sender));
//                        } else {
//                            send(new Message("upload_res", ui.username, "NO", msg.sender));
//                        }
//                    } else {
//                        send(new Message("upload_res", ui.username, "NO", msg.sender));
//                    }
                    break;

                case "THIS IS MESSAGE":
                    CHAT_GUI.PrintIntoTab(PACKET.getFirst(), PACKET.getLast(), 0);
                    notify(CHAT_GUI.mainTabbed.indexOfTab(PACKET.getFirst()));// popup title nay len bang cach thay doi mau sac text title
                    break;

                case "THIS IS MESSAGE FOR ALL":
                    if (!PACKET.getFirst().equals(LOGIN_GUI.USERNAME)) {
                        CHAT_GUI.PrintIntoTab("<Tất cả>", PACKET.getLast(), 0);
                        notify(0);
                    }
                    break;

                default:
                    System.out.println("\nGoi chua xac dinh~~~~~~~~~~~~~~\n");
            }
        }// het wihle ========================================================================
    }

    public void send(GPacket pck) {
        try {
            OUT.writeObject(pck);
            OUT.flush();
            System.out.println("\n" + pck.toString() + " >>>>>\n");
        } catch (IOException ex) {
            System.out.println("\nLỗi gửi goi tin\n" + ex.getMessage());
        }
    }

    public void LogOut() {

        LOGIN_GUI.tbUsername.setEnabled(true);
        LOGIN_GUI.tbPassword.setEnabled(true);
        LOGIN_GUI.btRegister.setEnabled(true);
        LOGIN_GUI.btLogin.setEnabled(true);
        LOGIN_GUI.tbUsername.selectAll();
        LOGIN_GUI.tbPort.setEnabled(false);
        LOGIN_GUI.tbServer.setEnabled(false);
        LOGIN_GUI.btConnect.setEnabled(false);
        CHAT_GUI.dispose();
        CHAT_GUI = null;
        LOGIN_GUI.show();
    }

    private void notify(int indexOfTab) {
        try {
            Thread th = Thread.currentThread();
            CHAT_GUI.mainTabbed.setForegroundAt(indexOfTab, Color.RED);
            th.sleep(200);
            CHAT_GUI.mainTabbed.setForegroundAt(indexOfTab, Color.CYAN);
            th.sleep(200);
            CHAT_GUI.mainTabbed.setForegroundAt(indexOfTab, Color.RED);
            th.sleep(200);
            CHAT_GUI.mainTabbed.setForegroundAt(indexOfTab, Color.CYAN);
            th.sleep(200);
            CHAT_GUI.mainTabbed.setForegroundAt(indexOfTab, Color.RED);
        } catch (InterruptedException ex) {
            Logger.getLogger(GClient_Process.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void un_notify(int index) {
        CHAT_GUI.mainTabbed.setForegroundAt(CHAT_GUI.mainTabbed.getSelectedIndex(), new Color(187, 187, 187));
    }
}
