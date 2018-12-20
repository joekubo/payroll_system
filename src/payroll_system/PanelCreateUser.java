
package payroll_system;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
public class PanelCreateUser extends javax.swing.JPanel {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
    public PanelCreateUser() {
        initComponents();
        conn = javaconnect.ConnecrDb();
        reset();
    }
//--------------------------------------dialogAllemployees-----------------------------------------------------
    public void showDialogAllEmployees(){
        DialogAllEmployees.setVisible(true);
        DialogAllEmployees.setSize(476,480);
        DialogAllEmployees.setLocationRelativeTo(tableCreateUser);
        DialogAllEmployees.setAlwaysOnTop(true);
        DialogAllEmployees.setResizable(false);
        DialogAllEmployees.setTitle("All Employees...");
        Update_tableAllEmployees();
    }
    private void Update_tableAllEmployees(){
        try{
            String sql = "SELECT staffNo AS [Staff No],firstName AS [F-Name] , lastName AS [L-Name] FROM employeeregistrationtable WHERE status = 'active'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tableAllEmployees.setModel(DbUtils.resultSetToTableModel(rs));
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
    private void selectedRowAllEmployees(){
        try{
            int row = tableAllEmployees.getSelectedRow();
            String Table_click = tableAllEmployees.getValueAt(row, 0).toString();
            String sql = "SELECT * FROM employeeregistrationtable WHERE staffNo = '"+Table_click+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
                if(rs.next()){
                    String staffno = rs.getString("staffNo");
                    txtStaffNo.setText(staffno);
                    
                    String fname = rs.getString("firstName");
                    String lname = rs.getString("lastName");
                    txtName.setText(fname +" "+lname);
                  
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
//--------------------------------------------------------------------------------------------------------------------
    private void reset(){
        txtStaffNo.setText("");
        txtName.setText("");
        txtPassword.setText("");
        txtUsername.setText("");
        txtConfirmPassword.setText("");
        buttonDisplayEmployees.requestFocus();
        buttonSave.setEnabled(true);
        buttonUpdate.setEnabled(false);
        buttonDelete.setEnabled(false);
        Update_table();
    }   
    private void Update_table(){
        try{
            String sql = "SELECT logintable.staffNo AS [Staff No],employeeregistrationtable.firstName||' '||employeeregistrationtable.lastName AS [Name] FROM logintable,employeeregistrationtable WHERE logintable.staffNo = employeeregistrationtable.staffNo";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tableCreateUser.setModel(DbUtils.resultSetToTableModel(rs));
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
            int row = tableCreateUser.getSelectedRow();
            String Table_click = tableCreateUser.getValueAt(row,0).toString();
            String sql = "SELECT * FROM logintable WHERE staffNo = '"+Table_click+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
               if(rs.next()){
                   String staffno = rs.getString("staffNo");
                   txtStaffNo.setText(staffno);
                   
                   String username = rs.getString("username");
                   txtUsername.setText(username);
                   
                   String Password = rs.getString("password");
                   txtPassword.setText(Password);
                   txtConfirmPassword.setText(Password);
                   
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
        try{
            String staffno = txtStaffNo.getText();
            String sql = "SELECT * FROM employeeregistrationtable WHERE staffNo = '"+staffno+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
               if(rs.next()){
                   String fname = rs.getString("firstName");
                   String lname = rs.getString("lastName");
                   txtName.setText(fname+" "+lname);
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
    private void compare(){
        String password = txtPassword.getText();
        String confirm  = txtConfirmPassword.getText();
            if(!password.equals(confirm)){
                txtException.setText("Please Re-Enter & Confirm Password!!!");
                txtPassword.setText("");
                txtConfirmPassword.setText("");
                txtPassword.requestFocus();
            }else{
                
            }
    }
    private void save(){
        try{
            String staffno = txtStaffNo.getText();
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            String confirmpassword = txtConfirmPassword.getText();
            
                if(staffno.equals("")){
                    txtException.setText("Please Enter Staff No");
                    buttonDisplayEmployees.requestFocus();
                }else if(username.equals("")){
                    txtException.setText("Please Enter Username");
                    txtUsername.requestFocus();
                }else if(password.equals("")){
                    txtException.setText("Please Enter Password");
                    txtPassword.requestFocus();
                }else if(confirmpassword.equals("")){
                    txtException.setText("Please Confirm Password"); 
                    txtConfirmPassword.requestFocus();
                }else{
            String sql = "INSERT INTO logintable (staffNo,username,password)VALUES(?,?,?)";
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, staffno);
            pst.setString(2, username);
            pst.setString(3, confirmpassword);
            pst.execute();
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
            String staffno = txtStaffNo.getText();
            String username = txtUsername.getText();
            String confirmpassword = txtConfirmPassword.getText();
            
            String sql = "UPDATE logintable SET staffNo = '"+staffno+"', username = '"+username+"',password = '"+confirmpassword+"'WHERE staffNo = '"+staffno+"'";
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
            String staffno = txtStaffNo.getText();
            String sql = "DELETE FROM logintable WHERE staffno = '"+staffno+"'";
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

        DialogAllEmployees = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableAllEmployees = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtStaffNo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        buttonDisplayEmployees = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        txtConfirmPassword = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCreateUser = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        buttonAddUser = new javax.swing.JButton();
        buttonUpdate = new javax.swing.JButton();
        buttonSave = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        buttonExit = new javax.swing.JButton();
        txtException = new javax.swing.JLabel();

        DialogAllEmployees.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DialogAllEmployees.setBackground(java.awt.Color.white);

        jPanel2.setBackground(java.awt.Color.white);

        tableAllEmployees.setModel(new javax.swing.table.DefaultTableModel(
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
        tableAllEmployees.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableAllEmployeesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableAllEmployees);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout DialogAllEmployeesLayout = new javax.swing.GroupLayout(DialogAllEmployees.getContentPane());
        DialogAllEmployees.getContentPane().setLayout(DialogAllEmployeesLayout);
        DialogAllEmployeesLayout.setHorizontalGroup(
            DialogAllEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogAllEmployeesLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        DialogAllEmployeesLayout.setVerticalGroup(
            DialogAllEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBackground(java.awt.Color.white);
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Create/Edit User Accounts", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 2, 12), java.awt.Color.blue)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel1.setForeground(java.awt.Color.blue);
        jLabel1.setText("Staff No:");

        txtStaffNo.setEditable(false);
        txtStaffNo.setBackground(new java.awt.Color(194, 208, 227));
        txtStaffNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStaffNoKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel2.setForeground(java.awt.Color.blue);
        jLabel2.setText("Name:");

        txtName.setEditable(false);
        txtName.setBackground(new java.awt.Color(194, 208, 227));
        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStaffNoKeyPressed(evt);
            }
        });

        buttonDisplayEmployees.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonDisplayEmployees.setForeground(java.awt.Color.red);
        buttonDisplayEmployees.setText("+++");
        buttonDisplayEmployees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDisplayEmployeesActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel3.setForeground(java.awt.Color.blue);
        jLabel3.setText("Username:");

        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsernameKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel4.setForeground(java.awt.Color.blue);
        jLabel4.setText("Password:");

        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel5.setForeground(java.awt.Color.blue);
        jLabel5.setText("Confirm Password:");

        txtConfirmPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtConfirmPasswordFocusLost(evt);
            }
        });
        txtConfirmPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtConfirmPasswordKeyPressed(evt);
            }
        });

        tableCreateUser.setModel(new javax.swing.table.DefaultTableModel(
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
        tableCreateUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCreateUserMouseClicked(evt);
            }
        });
        tableCreateUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableCreateUserKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableCreateUserKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tableCreateUser);

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        buttonAddUser.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonAddUser.setText("Add User");
        buttonAddUser.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddUserActionPerformed(evt);
            }
        });

        buttonUpdate.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonUpdate.setText("Update");
        buttonUpdate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateActionPerformed(evt);
            }
        });

        buttonSave.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonSave.setText("Save");
        buttonSave.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });

        buttonDelete.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonDelete.setText("Delete");
        buttonDelete.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonAddUser, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAddUser)
                    .addComponent(buttonUpdate)
                    .addComponent(buttonDelete)
                    .addComponent(buttonExit)
                    .addComponent(buttonSave))
                .addContainerGap())
        );

        txtException.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        txtException.setForeground(java.awt.Color.red);
        txtException.setText("  ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(txtException, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtStaffNo, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonDisplayEmployees))
                            .addComponent(txtUsername))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel2)
                                .addGap(3, 3, 3)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(4, 4, 4)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPassword)
                                    .addComponent(txtConfirmPassword))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtStaffNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonDisplayEmployees))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtException))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddUserActionPerformed
        reset();
    }//GEN-LAST:event_buttonAddUserActionPerformed

    private void tableCreateUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableCreateUserKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_DELETE ){
           delete();
        }
    }//GEN-LAST:event_tableCreateUserKeyPressed

    private void tableCreateUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCreateUserMouseClicked
        selectedRow();
        buttonSave.setEnabled(false);
        buttonUpdate.setEnabled(true);
        buttonDelete.setEnabled(true);
    }//GEN-LAST:event_tableCreateUserMouseClicked

    private void tableCreateUserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableCreateUserKeyReleased
       if (evt.getKeyCode()==KeyEvent.VK_DOWN || evt.getKeyCode()==KeyEvent.VK_UP){
            selectedRow();
        }
    }//GEN-LAST:event_tableCreateUserKeyReleased

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        save();
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void buttonDisplayEmployeesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDisplayEmployeesActionPerformed
        showDialogAllEmployees();
        txtException.setText("");
        reset();
    }//GEN-LAST:event_buttonDisplayEmployeesActionPerformed

    private void txtUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyPressed
        txtException.setText("");
    }//GEN-LAST:event_txtUsernameKeyPressed

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        txtException.setText("");
    }//GEN-LAST:event_txtPasswordKeyPressed

    private void txtConfirmPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConfirmPasswordKeyPressed
        txtException.setText("");
    }//GEN-LAST:event_txtConfirmPasswordKeyPressed

    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
       String staffno = txtStaffNo.getText();
       if(staffno.equals("")){
           txtException.setText("Cannot Update Record. Select Table");
           tableCreateUser.requestFocus();
       }else{
           update();
       }
    }//GEN-LAST:event_buttonUpdateActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        String staffno = txtStaffNo.getText();
       if(staffno.equals("")){
           txtException.setText("Cannot Delete Record. Select Table");
           tableCreateUser.requestFocus();
       }else{
           delete();
       }
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void tableAllEmployeesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAllEmployeesMouseClicked
        selectedRowAllEmployees();
        DialogAllEmployees.dispose();
    }//GEN-LAST:event_tableAllEmployeesMouseClicked

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_buttonExitActionPerformed

    private void txtConfirmPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtConfirmPasswordFocusLost
        compare();
    }//GEN-LAST:event_txtConfirmPasswordFocusLost

    private void txtStaffNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStaffNoKeyPressed
       if (evt.getKeyCode()==KeyEvent.VK_ENTER ){
           save();
        }
    }//GEN-LAST:event_txtStaffNoKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JDialog DialogAllEmployees;
    private javax.swing.JButton buttonAddUser;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonDisplayEmployees;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonSave;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable tableAllEmployees;
    private javax.swing.JTable tableCreateUser;
    private javax.swing.JPasswordField txtConfirmPassword;
    private javax.swing.JLabel txtException;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtStaffNo;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
