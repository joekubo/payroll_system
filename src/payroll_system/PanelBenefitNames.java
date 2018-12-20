package payroll_system;

import java.awt.event.KeyEvent;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class PanelBenefitNames extends javax.swing.JPanel {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
    public PanelBenefitNames() {
        initComponents();
        conn = javaconnect.ConnecrDb();
        update_table();
        reset();
    }
    private void update_table(){
        try{
            String sql = "SELECT id AS '',benefitname AS 'Benefit' FROM benefitsregistrationtable WHERE status = '1'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tableBenefits.setModel(DbUtils.resultSetToTableModel(rs));
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
    private void reset(){
        txtBenefit.setText("");
        txtBenefit.requestFocus();
        buttonSave.setEnabled(true);
        buttonUpdate.setEnabled(false);
        buttonDelete.setEnabled(false);
    }
    private void selectedRow(){
        try{
            int row = tableBenefits.getSelectedRow();
            String table_click = tableBenefits.getValueAt(row,0).toString();
            
            String sql = "SELECT * FROM benefitsregistrationtable WHERE id = '"+table_click+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                String Id = rs.getString("id");
                txtBenefitId.setText(Id);
                
                String Benefit = rs.getString("benefitName");
                txtBenefit.setText(Benefit);
            }
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
    private void save(){
        try{
            String Benefit = txtBenefit.getText();
            String Status = "1";
            if(Benefit.equals("")){
                txtBenefit.requestFocus();
                txtException.setText("Please Enter Benefit Name");
            }else{
                String sql = "INSERT INTO benefitsregistrationtable(benefitName,status)VALUES(?,?)";
                pst = conn.prepareStatement(sql);
                pst.setString(1, Benefit);
                pst.setString(2, Status);
                
                pst.execute();
                txtException.setText("***** SAVED *****");
                reset();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        update_table();
    }
    private void update(){
        try{
            String Id = txtBenefitId.getText();
            String Benefit = txtBenefit.getText();
            
            String sql = "UPDATE benefitsregistrationtable SET benefitName = '"+Benefit+"' WHERE id = '"+Id+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            txtException.setText("***** UPDATED *****");
            reset();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        update_table();
    }
    private void delete(){
        try{
            String sql = "UPDATE benefitsregistrationtable SET status = '0' WHERE id = '"+txtBenefitId.getText()+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            reset();
            txtException.setText("***** DELETE *****");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        update_table();
    }
    private void benefitBeingUsed(){
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtBenefitId = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtBenefit = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBenefits = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        buttonAdd = new javax.swing.JButton();
        buttonSave = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        buttonUpdate = new javax.swing.JButton();
        buttonExit = new javax.swing.JButton();
        txtException = new javax.swing.JLabel();

        txtBenefitId.setText("jTextField1");

        setBackground(java.awt.Color.white);
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Benefit Types", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 2, 12), java.awt.Color.blue)); // NOI18N

        jLabel16.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel16.setForeground(java.awt.Color.blue);
        jLabel16.setText("Benefit:");

        txtBenefit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBenefitKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBenefitKeyReleased(evt);
            }
        });

        tableBenefits.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableBenefits.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBenefitsMouseClicked(evt);
            }
        });
        tableBenefits.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableBenefitsKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tableBenefits);

        jPanel17.setBackground(java.awt.Color.white);
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        buttonAdd.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonAdd.setText("ADD");
        buttonAdd.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

        buttonSave.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonSave.setText("SAVE");
        buttonSave.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });

        buttonDelete.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonDelete.setText("Delete");
        buttonDelete.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(buttonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSave)
                    .addComponent(buttonAdd)
                    .addComponent(buttonDelete)
                    .addComponent(buttonUpdate)
                    .addComponent(buttonExit))
                .addGap(10, 10, 10))
        );

        txtException.setFont(new java.awt.Font("DejaVu Sans", 3, 12)); // NOI18N
        txtException.setForeground(java.awt.Color.red);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1)
                                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBenefit, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtException, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBenefit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(txtException, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        reset();
    }//GEN-LAST:event_buttonAddActionPerformed

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
      save();
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
       delete();
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        update();
    }//GEN-LAST:event_buttonUpdateActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_buttonExitActionPerformed

    private void txtBenefitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBenefitKeyReleased
       txtBenefit.setText(txtBenefit.getText().toUpperCase());
    }//GEN-LAST:event_txtBenefitKeyReleased

    private void txtBenefitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBenefitKeyPressed
        txtException.setText("");
    }//GEN-LAST:event_txtBenefitKeyPressed

    private void tableBenefitsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBenefitsMouseClicked
        buttonSave.setEnabled(false);
        buttonUpdate.setEnabled(true);
        buttonDelete.setEnabled(true);
        txtException.setText("");
        selectedRow();
    }//GEN-LAST:event_tableBenefitsMouseClicked

    private void tableBenefitsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableBenefitsKeyReleased
        if (evt.getKeyCode()==KeyEvent.VK_DOWN || evt.getKeyCode()==KeyEvent.VK_UP){
            selectedRow();
        }
    }//GEN-LAST:event_tableBenefitsKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonSave;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableBenefits;
    private javax.swing.JTextField txtBenefit;
    private javax.swing.JTextField txtBenefitId;
    private javax.swing.JLabel txtException;
    // End of variables declaration//GEN-END:variables
}
