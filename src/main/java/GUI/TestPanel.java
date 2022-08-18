package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

import java.awt.HeadlessException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class TestPanel extends javax.swing.JPanel {

    /**
     * Creates new form TestPanel
     */
//    public TestPanel() {
//        initComponents();
//    }
    private static final long serialVersionUID = 9029457020704524363L;
    private JLabel messageLbl1, userImageLbl1, messageLbl, userImageLbl;
    private JPanel msgPanel1, msgPanel;
    String userImageUrl = "http://cdn1.iconfinder.com/data/icons/nuvola2/22x22/apps/personal.png";

    public TestPanel() throws MalformedURLException {
        userImageLbl = new JLabel();
        msgPanel = new LeftArrowBubble();
        messageLbl = new JLabel();
        messageLbl1 = new JLabel();
        msgPanel1 = new RightArrowBubble();
        userImageLbl1 = new JLabel();

        userImageLbl.setIcon(new ImageIcon(new URL(userImageUrl)));
        messageLbl.setText("Hi, How are you?");

        GroupLayout msgPanelLayout = new GroupLayout(msgPanel);
        msgPanel.setLayout(msgPanelLayout);
        msgPanelLayout.setHorizontalGroup(
                msgPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(msgPanelLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(messageLbl)
                                .addContainerGap(162, Short.MAX_VALUE))
        );
        msgPanelLayout.setVerticalGroup(
                msgPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(msgPanelLayout.createSequentialGroup()
                                .addComponent(messageLbl)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        messageLbl1.setIcon(new ImageIcon(new URL(userImageUrl)));
        userImageLbl1.setText("I'm Good.");

        GroupLayout jPanel1Layout = new GroupLayout(msgPanel1);
        msgPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(171, Short.MAX_VALUE)
                                .addComponent(userImageLbl1)
                                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(userImageLbl1)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(userImageLbl)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(msgPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(msgPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(messageLbl1)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(userImageLbl)
                                        .addComponent(msgPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(messageLbl1)
                                        .addComponent(msgPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(22, Short.MAX_VALUE))
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    JOptionPane.showMessageDialog(null, new TestPanel());
                } catch (HeadlessException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

/**
 * @author harsh
 */
class RightArrowBubble extends JPanel {

    private static final long serialVersionUID = -5389178141802153305L;
    private int strokeThickness = 3;
    private int radius = 10;
    private int arrowSize = 12;
    private int padding = strokeThickness / 2;

    @Override
    protected void paintComponent(final Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0.5f, 0.5f, 1f));
        int bottomLineY = getHeight() - strokeThickness;
        int width = getWidth() - arrowSize - (strokeThickness * 2);
        g2d.fillRect(padding, padding, width, bottomLineY);
        RoundRectangle2D.Double rect = new RoundRectangle2D.Double(padding, padding, width, bottomLineY, radius, radius);
        Polygon arrow = new Polygon();
        arrow.addPoint(width, 8);
        arrow.addPoint(width + arrowSize, 10);
        arrow.addPoint(width, 12);
        Area area = new Area(rect);
        area.add(new Area(arrow));
        g2d.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        g2d.setStroke(new BasicStroke(strokeThickness));
        g2d.draw(area);
    }
}

class LeftArrowBubble extends JPanel {

    private static final long serialVersionUID = -5389178141802153305L;
    private int radius = 10;
    private int arrowSize = 12;
    private int strokeThickness = 3;
    private int padding = strokeThickness / 2;

    @Override
    protected void paintComponent(final Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0.5f, 0.8f, 1f));
        int x = padding + strokeThickness + arrowSize;
        int width = getWidth() - arrowSize - (strokeThickness * 2);
        int bottomLineY = getHeight() - strokeThickness;
        g2d.fillRect(x, padding, width, bottomLineY);
        g2d.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        g2d.setStroke(new BasicStroke(strokeThickness));
        RoundRectangle2D.Double rect = new RoundRectangle2D.Double(x, padding, width, bottomLineY, radius, radius);
        Polygon arrow = new Polygon();
        arrow.addPoint(20, 8);
        arrow.addPoint(0, 10);
        arrow.addPoint(20, 12);
        Area area = new Area(rect);
        area.add(new Area(arrow));
        g2d.draw(area);
    }
}
