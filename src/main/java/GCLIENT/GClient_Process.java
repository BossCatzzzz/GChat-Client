package GCLIENT;

import GUI.GChatUI;
import GUI.GLoginUI;
import com.mycenter.gobject.GPacket;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Gic
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
    public void run() {
        boolean keepRunning = true;
        while (keepRunning) {
            GPacket PACKET = null;
            try {
                PACKET = (GPacket) IN.readObject();
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
                keepRunning = false;
//                for (int i = 1; i < ui.model.size(); i++) {
//                    ui.model.removeElementAt(i);
//                }
//                ui.clientThread.stop();
                ex.printStackTrace();
                return;
            } catch (ClassNotFoundException ex) {
                System.out.println("\nLỗi khi ép kiểu gói tin:\n" + ex.getMessage());
                JOptionPane.showMessageDialog(LOGIN_GUI, "Lỗi khi ép kiểu gói tin:\n" + ex.getMessage());
            }
            System.out.println("\t\t\t<<<<<" + PACKET.toString());
            switch (PACKET.getAction()) {
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

//                        boolean exists = false;
//                        for (int i = 0; i < CHAT_GUI.MODEL_LIST_ACTIVE_USERs.getSize(); i++) {// xet qua het tat ca cac user dang online...
//                            if (CHAT_GUI.MODEL_LIST_ACTIVE_USERs.getElementAt(i).equals(PACKET.getFirst())) {// neu co user nay roi thi ... exists = true
//                                exists = true;
//                                break;
//                            }
//                        }
//                        if (!exists) { // neu user moi login/signin là user moi thi add vao list dang online
//                            ui.model.addElement(msg.content);
//                        }
                        if (CHAT_GUI.MODEL_LIST_ACTIVE_USERs.indexOf(PACKET.getFirst()) == -1) {// chua co element nay trong list
                            CHAT_GUI.MODEL_LIST_ACTIVE_USERs.addElement(PACKET.getFirst());
                        }
                    }
                    break;
                case "LOGOUT USERs":
                    if (PACKET.getFirst().equals(LOGIN_GUI.USERNAME)) {
                        //test ===>>> that ra if nay khong bh chay 
                    } else {
                        CHAT_GUI.MODEL_LIST_ACTIVE_USERs.removeElement(PACKET.getFirst());
                        CHAT_GUI.board.append("\n[" + PACKET.getFirst() + " đã thoát]\n");
                        CHAT_GUI.PrintIntoTab(PACKET.getFirst(), "\n[" + PACKET.getFirst() + " đã thoát]\n", 0);
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

//            if (pck.type.equals("message") && !pck.content.equals(".bye")) {
//                String msgTime = (new Date()).toString();
//                try {
////                    hist.addMessage(pck, msgTime);
//                    DefaultTableModel table = (DefaultTableModel) ui.historyFrame.table_History.getModel();
//                    table.addRow(new Object[]{"Me", pck.content, pck.recipient, msgTime});
//                } catch (Exception ex) {
//                }
//            }
        } catch (IOException ex) {
            System.out.println("\nLỗi gửi goi tin\n" + ex.getMessage());
        }
    }
}
