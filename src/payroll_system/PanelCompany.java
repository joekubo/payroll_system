package payroll_system;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
public class PanelCompany extends javax.swing.JPanel {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
    public PanelCompany() {
        initComponents();
        conn = javaconnect.ConnecrDb();
        Update_table();
    }
    private void Update_table(){
        try{
            String sql = "SELECT id AS '',companyName AS [Company Name], address AS [Address],city AS [City],buildingname AS 'Building',website AS 'Website',office AS 'Office', tel AS [Phone No], email AS [Email], pin AS [PIN No]FROM companytable";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tableCompany.setModel(DbUtils.resultSetToTableModel(rs));
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
    private void emailValidation(){
        boolean status = ValidateEmail.validateEmail(txtEmail.getText());
        if(!status){
            txtEmail.setText("");
            txtEmail.requestFocus();
        }
    }
    private void selectedRow(){
        try{
            int row = tableCompany.getSelectedRow();
            
            String Table_click = tableCompany.getValueAt(row, 0).toString();
            String sql = "SELECT * FROM companytable WHERE id = '"+Table_click+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
                if(rs.next()){
                    String Id = rs.getString("id");
                    txtId.setText(Id);
                    
                    String name = rs.getString("companyName");
                    txtCompanyName.setText(name);
                    
                    String Address = rs.getString("address");
                    txtAddress.setText(Address);
                    
                    String City = rs.getString("city");
                    txtCity.setText(City);
                    
                    String Tel = rs.getString("tel");
                    txtTel.setText(Tel);
                    
                    String Email = rs.getString("email");
                    txtEmail.setText(Email);
                    
                    String Pinno = rs.getString("pin");
                    txtPinNo.setText(Pinno);
                    
                    String Buildingname = rs.getString("buildingname");
                    txtBuildingName.setText(Buildingname);
                    
                    String Website = rs.getString("website");
                    txtWebsite.setText(Website);
                    
                    String Office = rs.getString("office");
                    txtOffice.setText(Office);
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
    private void update(){
        try{
            String Id = txtId.getText();
            String companyname = txtCompanyName.getText();
            String Address = txtAddress.getText();
            String City = txtCity.getText();
            String Tel = txtTel.getText();
            String Email = txtEmail.getText();
            String Pinno = txtPinNo.getText();
            String Buildingname = txtBuildingName.getText();
            String Website = txtWebsite.getText();
            String Office = txtOffice.getText();
            
            String sql = "UPDATE companytable SET companyName = '"+companyname+"',address = '"+Address+"',city = '"+City+"',tel = '"+Tel+"',email = '"+Email+"',pin = '"+Pinno+"',buildingName = '"+Buildingname+"',website = '"+Website+"',office = '"+Office+"' WHERE id = '"+Id+"'";
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
        Update_table();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCompanyName = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtCity = new javax.swing.JTextField();
        txtTel = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtPinNo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCompany = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        buttonUpdate = new javax.swing.JButton();
        buttonExit = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtBuildingName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtOffice = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtWebsite = new javax.swing.JTextField();

        txtId.setText("jTextField1");

        setBackground(java.awt.Color.white);
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Company's Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 2, 12), java.awt.Color.blue)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel1.setForeground(java.awt.Color.blue);
        jLabel1.setText("Company's Name:");

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel2.setForeground(java.awt.Color.blue);
        jLabel2.setText("Address:");

        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel3.setForeground(java.awt.Color.blue);
        jLabel3.setText("City:");

        jLabel4.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel4.setForeground(java.awt.Color.blue);
        jLabel4.setText("Tel:");

        jLabel5.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel5.setForeground(java.awt.Color.blue);
        jLabel5.setText("Email:");

        jLabel6.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel6.setForeground(java.awt.Color.blue);
        jLabel6.setText("PIN No:");

        txtAddress.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAddressKeyTyped(evt);
            }
        });

        txtCity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCityKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCityKeyReleased(evt);
            }
        });

        txtTel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelKeyTyped(evt);
            }
        });

        txtEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEmailFocusLost(evt);
            }
        });
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });

        txtPinNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPinNoKeyReleased(evt);
            }
        });

        tableCompany.setModel(new javax.swing.table.DefaultTableModel(
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
        tableCompany.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCompanyMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableCompany);

        jPanel2.setBackground(java.awt.Color.white);
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        buttonUpdate.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonUpdate.setText("Update");
        buttonUpdate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateActionPerformed(evt);
            }
        });

        buttonExit.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonExit.setText("Exit");
        buttonExit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonUpdate)
                    .addComponent(buttonExit))
                .addContainerGap())
        );

        jLabel7.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel7.setForeground(java.awt.Color.blue);
        jLabel7.setText("Building Name:");

        txtBuildingName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuildingNameKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuildingNameKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel8.setForeground(java.awt.Color.blue);
        jLabel8.setText("Office:");

        txtOffice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtOfficeKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOfficeKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel9.setForeground(java.awt.Color.blue);
        jLabel9.setText("Website:");

        txtWebsite.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtWebsiteKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel4))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtBuildingName, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtOffice, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPinNo, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(246, 246, 246)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtWebsite, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(98, 98, 98))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtWebsite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPinNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuildingName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOffice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
       String name = txtCompanyName.getText();
        if(name.equals("-") && name.equals("")){
            JOptionPane.showMessageDialog(null, "Cannot Update Details. Please Select From table");
            tableCompany.requestFocus();
        }else{
            update();
        }
    }//GEN-LAST:event_buttonUpdateActionPerformed

    private void tableCompanyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCompanyMouseClicked
        selectedRow();
    }//GEN-LAST:event_tableCompanyMouseClicked

    private void txtAddressKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAddressKeyTyped
   
    }//GEN-LAST:event_txtAddressKeyTyped

    private void txtCityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCityKeyTyped
        char vchar = evt.getKeyChar();
      if(((Character.isDigit(vchar)))
          ||(vchar == KeyEvent.VK_BACK_SPACE)
          
          || (vchar == KeyEvent.VK_DELETE)){
        evt.consume();
    }
    }//GEN-LAST:event_txtCityKeyTyped

    private void txtCityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCityKeyReleased
        txtCity.setText(txtCity.getText().toUpperCase());
    }//GEN-LAST:event_txtCityKeyReleased

    private void txtTelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelKeyTyped
    
    }//GEN-LAST:event_txtTelKeyTyped

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        txtEmail.setText(txtEmail.getText().toLowerCase());
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusLost
        emailValidation();
    }//GEN-LAST:event_txtEmailFocusLost

    private void txtPinNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPinNoKeyReleased
        txtPinNo.setText(txtPinNo.getText().toUpperCase());
    }//GEN-LAST:event_txtPinNoKeyReleased

    private void txtBuildingNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuildingNameKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuildingNameKeyTyped

    private void txtBuildingNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuildingNameKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuildingNameKeyReleased

    private void txtOfficeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOfficeKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOfficeKeyTyped

    private void txtOfficeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOfficeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOfficeKeyReleased

    private void txtWebsiteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtWebsiteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtWebsiteKeyReleased

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_buttonExitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableCompany;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtBuildingName;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtCompanyName;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtOffice;
    private javax.swing.JTextField txtPinNo;
    private javax.swing.JTextField txtTel;
    private javax.swing.JTextField txtWebsite;
    // End of variables declaration//GEN-END:variables
}
