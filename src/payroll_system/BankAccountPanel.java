package payroll_system;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class BankAccountPanel extends javax.swing.JPanel {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
    public BankAccountPanel() {
        initComponents();
        conn = javaconnect.ConnecrDb();
        Update_table();
        reset();
    }
    private void reset(){
        txtBankName.setText("");
        txtBranchName.setText("");
        txtSortCode.setText("");
        txtBankName.requestFocus();
        buttonSave.setEnabled(true);
        buttonUpdate.setEnabled(false);
        buttonDelete.setEnabled(false);
    }
    private void Update_table(){
        try{
            String sql = "SELECT id AS [], bankName AS [Bank],branchName AS [Branch],branchCode AS [Branch Sort Code] FROM bankTable";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tableBankAccounts.setModel(DbUtils.resultSetToTableModel(rs));
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
    private void selectedRow(){
        try{
            int row = tableBankAccounts.getSelectedRow();
            String Table_click = tableBankAccounts.getValueAt(row, 0).toString();
            
            String sql = "SELECT * FROM bankTable WHERE id = '"+Table_click+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
                if(rs.next()){
                    String id = rs.getString("id");
                    txtId.setText(id);
                    
                    String bankname = rs.getString("bankName");
                    txtBankName.setText(bankname);
                    
                    String branch = rs.getString("branchName");
                    txtBranchName.setText(branch);
                    
                    String code = rs.getString("branchCode");
                    txtSortCode.setText(code);
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
            String bankname = txtBankName.getText();
            String branchname = txtBranchName.getText();
            String code = txtSortCode.getText();
            
            if(bankname.equals("")){
                txtBankName.requestFocus();
            }else if(branchname.equals("")){
                txtBranchName.requestFocus();
            }else if(code.equals("")){
                txtSortCode.requestFocus();
            }else{
            String sql = "INSERT INTO bankTable(bankname,branchName,branchCode)VALUES(?,?,?)";
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, bankname);
            pst.setString(2, branchname);
            pst.setString(3, code);
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Saved");
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
        Update_table();
    }
    private void update(){
        try{
            String Id = txtId.getText();
            String bankname = txtBankName.getText();
            String branchname = txtBranchName.getText();
            String code = txtSortCode.getText();
            
            String sql = "UPDATE bankTable SET bankname = '"+bankname+"',branchName = '"+branchname+"',branchCode = '"+code+"' WHERE id = '"+Id+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Updated");
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
        Update_table();
    }
    private void delete(){
        try{
            String Id = txtId.getText();
            String sql = "DELETE FROM bankTable WHERE id = '"+Id+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Deleted");
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
      Update_table();
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtBankName = new javax.swing.JTextField();
        txtBranchName = new javax.swing.JTextField();
        txtSortCode = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBankAccounts = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        buttonAddnew = new javax.swing.JButton();
        buttonUpdate = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        buttonSave = new javax.swing.JButton();
        buttonExit = new javax.swing.JButton();

        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdKeyTyped(evt);
            }
        });

        setBackground(java.awt.Color.white);
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bank Account Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 2, 12), java.awt.Color.blue)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel1.setForeground(java.awt.Color.blue);
        jLabel1.setText("Bank Name:");

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel2.setForeground(java.awt.Color.blue);
        jLabel2.setText("Branch Name:");

        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel3.setForeground(java.awt.Color.blue);
        jLabel3.setText("Branch Sort Code:");

        txtBankName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBankNameKeyReleased(evt);
            }
        });

        txtBranchName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBranchNameKeyReleased(evt);
            }
        });

        txtSortCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSortCodeKeyTyped(evt);
            }
        });

        tableBankAccounts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableBankAccounts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBankAccountsMouseClicked(evt);
            }
        });
        tableBankAccounts.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableBankAccountsKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tableBankAccounts);

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        buttonAddnew.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonAddnew.setText("Add New");
        buttonAddnew.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonAddnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddnewActionPerformed(evt);
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

        buttonDelete.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonDelete.setText("Delete");
        buttonDelete.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });

        buttonSave.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonSave.setText("Save");
        buttonSave.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonAddnew, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                            .addComponent(buttonUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(buttonExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonAddnew)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonExit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSortCode, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBranchName, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBankName, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtBankName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtBranchName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtSortCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAddnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddnewActionPerformed
        reset();
    }//GEN-LAST:event_buttonAddnewActionPerformed

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        save();
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        update();
    }//GEN-LAST:event_buttonUpdateActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        delete();
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
       this.setVisible(false);
    }//GEN-LAST:event_buttonExitActionPerformed

    private void txtBankNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBankNameKeyReleased
       txtBankName.setText(txtBankName.getText().toUpperCase());
    }//GEN-LAST:event_txtBankNameKeyReleased

    private void txtBranchNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBranchNameKeyReleased
        txtBranchName.setText(txtBranchName.getText().toUpperCase());
    }//GEN-LAST:event_txtBranchNameKeyReleased

    private void txtSortCodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSortCodeKeyTyped
        char vchar = evt.getKeyChar();
      if((!(Character.isDigit(vchar)))
          ||(vchar == KeyEvent.VK_BACK_SPACE)
          
          || (vchar == KeyEvent.VK_DELETE)){
        evt.consume();
    }
    }//GEN-LAST:event_txtSortCodeKeyTyped

    private void tableBankAccountsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBankAccountsMouseClicked
        selectedRow();
        buttonSave.setEnabled(false);
        buttonUpdate.setEnabled(true);
        buttonDelete.setEnabled(true);
        
    }//GEN-LAST:event_tableBankAccountsMouseClicked

    private void tableBankAccountsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableBankAccountsKeyReleased
        if (evt.getKeyCode()==KeyEvent.VK_DOWN || evt.getKeyCode()==KeyEvent.VK_UP){
            selectedRow();
        }
    }//GEN-LAST:event_tableBankAccountsKeyReleased

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddnew;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonSave;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableBankAccounts;
    private javax.swing.JTextField txtBankName;
    private javax.swing.JTextField txtBranchName;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtSortCode;
    // End of variables declaration//GEN-END:variables
}
