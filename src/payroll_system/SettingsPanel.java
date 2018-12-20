package payroll_system;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;


public class SettingsPanel extends javax.swing.JPanel {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
    public SettingsPanel() {
        initComponents();
        conn = javaconnect.ConnecrDb();
        Update_tableSettings();
    }
    private void Update_tableSettings(){
        try{
            String sql = "SELECT * FROM taxablerangestable";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tableSettings.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
    }
    private void update_taxabletable(){
        try{
            int row = tableSettings.getSelectedRow();
            String Amount1 = tableSettings.getValueAt(row, 1).toString();
            String Rate1 = tableSettings.getValueAt(row, 2).toString();
            String Amount2 = tableSettings.getValueAt(row, 3).toString();
            String Rate2 = tableSettings.getValueAt(row, 4).toString();
            String Amount3 = tableSettings.getValueAt(row, 5).toString();
            String Rate3 = tableSettings.getValueAt(row, 6).toString();
            String Amount4 = tableSettings.getValueAt(row, 7).toString();
            String Rate4 = tableSettings.getValueAt(row, 8).toString();
            String Rate5 = tableSettings.getValueAt(row, 9).toString();
            String Nssfrate = tableSettings.getValueAt(row, 10).toString();
            String Reliefamount = tableSettings.getValueAt(row, 11).toString();
            
            String sql = "UPDATE taxablerangestable SET amount1 = '"+Amount1+"',amount1Rate = '"+Rate1+"'"
                    + ",amount2 = '"+Amount2+"',amount2Rate = '"+Rate2+"',amount3 = '"+Amount3+"',"
                    + "amount3Rate = '"+Rate3+"',amount4 = '"+Amount4+"',amount4Rate = '"+Rate4+"',"
                    + "amount5Rate = '"+Rate5+"',nssfRate = '"+Nssfrate+"',reliefAmount = '"+Reliefamount+"' WHERE id = '1'";
            pst = conn.prepareStatement(sql);
            pst.execute();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        Update_tableSettings();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSettings = new javax.swing.JTable();
        buttonUpdate = new javax.swing.JButton();
        buttonExit = new javax.swing.JButton();

        setBackground(java.awt.Color.white);
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Settings", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 2, 12), java.awt.Color.blue)); // NOI18N

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.gray));

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tableSettings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSettingsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableSettings);

        buttonUpdate.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonUpdate.setText("Update");
        buttonUpdate.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateActionPerformed(evt);
            }
        });

        buttonExit.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonExit.setText("Exit");
        buttonExit.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(165, 165, 165)
                        .addComponent(buttonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonUpdate)
                    .addComponent(buttonExit))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Calculations Settings", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(198, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        update_taxabletable();
    }//GEN-LAST:event_buttonUpdateActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
       this.setVisible(false);
    }//GEN-LAST:event_buttonExitActionPerformed

    private void tableSettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSettingsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableSettingsMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tableSettings;
    // End of variables declaration//GEN-END:variables
}
