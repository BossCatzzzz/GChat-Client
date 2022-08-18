package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Gic
 */
public class ChatTab extends javax.swing.JPanel {

    String title = "";

    public ChatTab(String who) {
        initComponents();
        TextAreaCellRenderer renderer = new TextAreaCellRenderer();
        tablechat.getColumnModel().getColumn(0).setCellRenderer(renderer);
        tablechat.getColumnModel().getColumn(1).setCellRenderer(renderer);
        tablechat.setShowGrid(false);
        tablechat.setIntercellSpacing(new Dimension(0, 0));

        tablechat.setTableHeader(null);

        title = who;
//        target = to;
//        this.me = me;
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablechat = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        tablechat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablechat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tablechatFocusGained(evt);
            }
        });
        tablechat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablechatMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablechat);
        if (tablechat.getColumnModel().getColumnCount() > 0) {
            tablechat.getColumnModel().getColumn(0).setResizable(false);
            tablechat.getColumnModel().getColumn(1).setResizable(false);
        }

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void tablechatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablechatMousePressed
//        System.out.println("maintbMousePressed " + this.target);
//
//        JTabbedPane j = (JTabbedPane) this.getParent();
//        j.setForegroundAt(j.indexOfTab(target), Color.BLACK);
        System.out.println("mouse press\n");
    }//GEN-LAST:event_tablechatMousePressed

    private void tablechatFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablechatFocusGained
        System.out.println("focus\n");
    }//GEN-LAST:event_tablechatFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tablechat;
    // End of variables declaration//GEN-END:variables
}

// <editor-fold defaultstate="collapsed" desc="Text Area Cell Renderer">  
class TextAreaCellRenderer extends JTextArea implements TableCellRenderer {

    private final List<List<Integer>> rowAndCellHeights = new ArrayList<>();

    // public static class UIResource extends TextAreaCellRenderer implements UIResource {}
    @Override
    public void updateUI() {
        super.updateUI();
        setLineWrap(true);
//        setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
//        setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        // setMargin(new Insets(2, 2, 2, 2));
        // setBorder(BorderFactory.createEmptyBorder());
        setName("Table.cellRenderer");
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setFont(table.getFont());
        setText(Objects.toString(value, ""));
        adjustRowHeight(table, row, column);

        return this;
    }

    /**
     * Calculate the new preferred height for a given row, and sets the height
     * on the table.
     * http://blog.botunge.dk/post/2009/10/09/JTable-multiline-cell-renderer.aspx
     */
    private void adjustRowHeight(JTable table, int row, int column) {
        // The trick to get this to work properly is to set the width of the column to the
        // textarea. The reason for this is that getPreferredSize(), without a width tries
        // to place all the text in one line. By setting the size with the with of the column,
        // getPreferredSize() returnes the proper height which the row should have in
        // order to make room for the text.
        // int cWidth = table.getTableHeader().getColumnModel().getColumn(column).getWidth();
        // int cWidth = table.getCellRect(row, column, false).width; // Ignore IntercellSpacing
        // setSize(new Dimension(cWidth, 1000));

        setBounds(table.getCellRect(row, column, false));
        // doLayout();

        int preferredHeight = getPreferredSize().height;
        while (rowAndCellHeights.size() <= row) {
            rowAndCellHeights.add(new ArrayList<>(column));
        }
        List<Integer> list = rowAndCellHeights.get(row);
        while (list.size() <= column) {
            list.add(0);
        }
        list.set(column, preferredHeight);
        int max = list.stream().max(Integer::compare).get();
        if (table.getRowHeight(row) != max) {
            table.setRowHeight(row, max);
        }
    }

    // Overridden for performance reasons. ---->
    @Override
    public boolean isOpaque() {
        Color back = getBackground();
        Object o = SwingUtilities.getAncestorOfClass(JTable.class, this);
        if (o instanceof JTable) {
            JTable table = (JTable) o;
            boolean colorMatch = Objects.nonNull(back) && back.equals(table.getBackground()) && table.isOpaque();
            return !colorMatch && super.isOpaque();
        } else {
            return super.isOpaque();
        }
    }

    @Override
    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        // String literal pool
        // if (propertyName == "document" || ((propertyName == "font" || propertyName == "foreground") && oldValue != newValue)) {
        if ("document".equals(propertyName)) {
            super.firePropertyChange(propertyName, oldValue, newValue);
        } else if (("font".equals(propertyName) || "foreground".equals(propertyName)) && !Objects.equals(oldValue, newValue)) {
            super.firePropertyChange(propertyName, oldValue, newValue);
        }
    }

    @Override
    public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {
        /* Overridden for performance reasons. */ }

    @Override
    public void repaint(long tm, int x, int y, int width, int height) {
        /* Overridden for performance reasons. */ }

    @Override
    public void repaint(Rectangle r) {
        /* Overridden for performance reasons. */ }

    @Override
    public void repaint() {
        /* Overridden for performance reasons. */ }

    @Override
    public void invalidate() {
        /* Overridden for performance reasons. */ }

    @Override
    public void validate() {
        /* Overridden for performance reasons. */ }

    @Override
    public void revalidate() {
        /* Overridden for performance reasons. */ }
    // <---- Overridden for performance reasons.
}
// </editor-fold> 
