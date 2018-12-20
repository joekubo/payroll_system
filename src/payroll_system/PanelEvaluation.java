package payroll_system;
import com.toedter.calendar.JCalendar;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
public class PanelEvaluation extends javax.swing.JPanel {
        Connection conn = null;
        PreparedStatement pst = null;
        PreparedStatement pst1 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        private String staffno;
        DefaultTableModel dm = new DefaultTableModel();
    public PanelEvaluation() {
        initComponents();
        conn = javaconnect.ConnecrDb();
        buttonView.setToolTipText("View Tier 1 & Tier 2");
        buttonShowRegisteredEmployees.setToolTipText("Click Here to Select Employee");
        monthNyear();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, 0);
        chooserPaymentDate.getDateEditor().setDate(c.getTime());
        chooserDateIncome.getDateEditor().setDate(c.getTime());
        resetDeductions();
        Update_TablePayment();
         resetPayment();
         checkRelief();
         //tablePayment.setModel(getdata());
         jButton6.setEnabled(false);
         jButton5.setEnabled(false);
         buttonView.setEnabled(false);
         jButton3.setEnabled(false);
    }
    private void monthNyear(){
    JCalendar calendar = new JCalendar();
    String mymonth;
    SimpleDateFormat sdf1 = new SimpleDateFormat("MMMM");
    java.util.Date date1 = calendar.getDate();
    mymonth = sdf1.format(date1);
    comboMonth.setSelectedItem(mymonth);
   
    SimpleDateFormat sdf2 = new SimpleDateFormat("YYYY");
    java.util.Date date2 = calendar.getDate();
    mymonth = sdf2.format(date2);
    comboYear.setSelectedItem(mymonth);
    }
    private void saveKra(){
        try{
            String Pin = txtPinNo.getText();
            String Name = txtEmployeeName.getText();
            String Basicsalary = txtBasicSalaryIncome.getText();
            String Actualcontribution = txtNSSF.getText();
            String Personalrelief = txtRelief.getText();
            String Residencestatus = "Resident";
            String Employeetype = "Primary Employee";
            String Houseallowance = "0.00";
            String Transportallowance = "0.00";
            String Leavepay = "0.00";
            String Overtimeallowance = "0.00";
            String Directorsfee = "0.00";
            String Lumpsum = "0.00";
            String OtherAllowance = "0.00";
            String Totalcashpay = "";
            String ValueOFCB = "0.00";
            String OtherNCB = "0.00";
            String TotalNCPay = "";
            String Globalincome = "0.00";
            String Typehousing = "Benefit not given";
            String Rentofhouse = "";
            String Computedrent = "";
            String Rentrecovered = "";
            String Netvaluehousing = "";
            String Totalgrosspay = "";
            String Cashpay = "";
            String Permissiblelimit = "";
            String Mortgageinterest = "0.00";
            String DepositHOSP = "0.00";
            String Amountbenefit = "";
            String Taxablepay = "";
            String Taxpayable = "";
            String AmountIR = "0.00";
            String Payetax = "";
            String Sapt = "";
            String Month = comboMonth.getSelectedItem().toString();
            String Year = comboYear.getSelectedItem().toString();
            
            String sql = "INSERT INTO kratable(pin,name,residenceStatus,employeeType,basicSalary,houseAll,transportAll,leavePay,overtimeAll,directorsFee,lumpSum,otherAll,totalCashPay,valueOfCB,otherNCB,totalNCPay,globalIncome,typeHousing,rentOfHouse,computedRent,rentRecovered,netValueHousing,totalGrossPay,cashPay,actualContribution,permissibleLimit,mortgageInterest,depositHOSP,amountBenefit,taxablePay,taxPayable,personalRelief,amountIR,payeTax,SAPT,month,year)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, Pin);
            pst.setString(2, Name);
            pst.setString(3, Residencestatus);
            pst.setString(4, Employeetype);
            pst.setString(5, Basicsalary);
            pst.setString(6, Houseallowance);
            pst.setString(7, Transportallowance);
            pst.setString(8, Leavepay);
            pst.setString(9, Overtimeallowance);
            pst.setString(10, Directorsfee);
            pst.setString(11, Lumpsum);
            pst.setString(12, OtherAllowance);
            pst.setString(13, Totalcashpay);
            pst.setString(14, ValueOFCB);
            pst.setString(15, OtherNCB);
            pst.setString(16, TotalNCPay);
            pst.setString(17, Globalincome);
            pst.setString(18, Typehousing);
            pst.setString(19, Rentofhouse);
            pst.setString(20, Computedrent);
            pst.setString(21, Rentrecovered);
            pst.setString(22, Netvaluehousing);
            pst.setString(23, Totalgrosspay);
            pst.setString(24, Cashpay);
            pst.setString(25, Actualcontribution);
            pst.setString(26, Permissiblelimit);
            pst.setString(27, Mortgageinterest);
            pst.setString(28, DepositHOSP);
            pst.setString(29, Amountbenefit);
            pst.setString(30, Taxablepay);
            pst.setString(31, Taxpayable);
            pst.setString(32, Personalrelief);
            pst.setString(33, AmountIR);
            pst.setString(34, Payetax);
            pst.setString(35, Sapt);
            pst.setString(36, Month);
            pst.setString(37, Year);
            
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
    }
    
    
 //---------------------------------------------------DialogBenefits--------------------------------------------------------------------------------
    
    private void showDialogBenefits(){
        DialogBenefits.setVisible(true);
        DialogBenefits.setSize(485,410);
        DialogBenefits.setLocationRelativeTo(null);
        DialogBenefits.setAlwaysOnTop(true);
        DialogBenefits.setResizable(false);
        DialogBenefits.setTitle("Benefits");
        resetBenefits();
        Update_tableBenefits();
    }
    private void fillComboBenefits(){
        try{
            String sql = "SELECT * FROM benefitsregistrationtable WHERE status = '1'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                String Benefits = rs.getString("benefitName");
                comboBenefit.addItem(Benefits);
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
    private void removeComboBenefits(){
        try{
            String sql = "SELECT * FROM benefitsregistrationtable";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                String Benefits = rs.getString("benefitName");
                comboBenefit.removeItem(Benefits);
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
    
    private void resetBenefits(){
        comboBenefit.setSelectedItem("Select Benefit");
        txtBenefitsAmount.setText("");
        comboBenefit.requestFocus();
        buttonSave.setEnabled(true);
        buttonUpdate.setEnabled(false);
        buttonDelete.setEnabled(false);
    }
    private void Update_tableBenefits(){
        try{
            String sql = "SELECT benefitId AS '',benefitName AS 'Benefit',amount AS 'Amount' FROM benefitstable WHERE "
                    + "staffNo = '"+txtStaffNo.getText()+"' AND month = '"+comboMonth.getSelectedItem().toString()+"' AND "
                    + "year = '"+comboYear.getSelectedItem().toString()+"' AND status = '1'";
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
    private void selectedRowBenefits(){
        try{
            int row = tableBenefits.getSelectedRow();
            String Table_click = tableBenefits.getValueAt(row, 0).toString();
            String sql = "SELECT * FROM benefitstable WHERE benefitid = '"+Table_click+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
                if(rs.next()){
                    String Id = rs.getString("benefitid");
                    txtBenefitsId.setText(Id);
                    
                    String Beneft = rs.getString("benefitName");
                    comboBenefit.setSelectedItem(Beneft);
                    
                    String Amount = rs.getString("amount");
                    txtBenefitsAmount.setText(Amount);
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
    private void saveBenefits(){
        try{
            String Staffno = txtStaffNo.getText();
            String Month = comboMonth.getSelectedItem().toString();
            String Year = comboYear.getSelectedItem().toString();
            String Benefit = comboBenefit.getSelectedItem().toString();
            String Amount = txtBenefitsAmount.getText();
            String Status = "1";
            
            if(Benefit.equals("Select Benefit")){
                comboBenefit.requestFocus();
            }else{
            String sql = "INSERT INTO benefitstable(benefitName,staffNo,month,year,amount,status)VALUES(?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, Benefit);
            pst.setString(2, Staffno);
            pst.setString(3, Month);
            pst.setString(4, Year);
            pst.setString(5, Amount);
            pst.setString(6, Status);
           
            pst.execute();
           
            resetBenefits();
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
        Update_tableBenefits();
    }
    private void updateBenefits(){
         try{
            String Id = txtBenefitsId.getText();
            String Staffno = txtStaffNo.getText();
            String Benefit = comboBenefit.getSelectedItem().toString();
            String Month = comboMonth.getSelectedItem().toString();
            String Year = comboYear.getSelectedItem().toString();
            String Amount = txtBenefitsAmount.getText();
            
            
            String sql = "UPDATE benefitstable SET benefitName = '"+Benefit+"',staffNo = '"+Staffno+"',month = '"+Month+"',year = '"+Year+"',"
                    + "amount = '"+Amount+"' WHERE benefitId = '"+Id+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            resetBenefits();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        Update_tableBenefits();
    }
    private void deleteBenefits(){
         try{
            String Id = txtBenefitsId.getText();
            String sql = "UPDATE benefitstable SET status = '0' WHERE benefitId = '"+Id+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            resetBenefits();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        Update_tableBenefits();
    }
    private void grossSalary(){
        try{
            String sql = "SELECT  COALESCE(SUM(amount), 0) FROM benefitstable WHERE staffNo = '"+txtStaffNo.getText()+"' AND month = "
                    + "'"+comboMonth.getSelectedItem().toString()+"' AND year = '"+comboYear.getSelectedItem().toString()+"' AND status = '1'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                String basic = txtBasicSalaryIncome.getText();
                basic = basic.trim();
                double Basic = Double.parseDouble(basic);
                
                double Totalamount = rs.getDouble("COALESCE(SUM(amount), 0)");
                txtGross.setText(String.format("%.2f",Basic + Totalamount));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e+" panelevaluation.grossalary");
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
    }
//-------------------------------------------------Loans-----------------------------------------------------------------------------------------
    private void evaluateBalancebf(){
        try{
           String sql = "SELECT COALESCE(SUM(takenLoan),0),COALESCE(SUM(paidLoan),0) FROM loantable WHERE staffno = "
                   + "'"+txtStaffNo.getText()+"'AND loantable.year = '"+comboYear.getSelectedItem().toString()+"'";
           pst = conn.prepareStatement(sql);
           rs = pst.executeQuery();
           if(rs.next()){
               double Totalloan = rs.getDouble("COALESCE(SUM(takenLoan),0)");
               double Totalpaid = rs.getDouble("COALESCE(SUM(paidLoan),0)");
               txtBal_c_f.setText(String.format("%.2f",Totalloan - Totalpaid));
           }
        }catch(Exception e){
            //JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
    }
    private void calculateloan(){
        String addloan = txtAddLoan.getText();
               addloan = addloan.trim();
               double Addloan = Double.parseDouble(addloan);
               
               String paidamount = txtDeductionAmount.getText();
               paidamount = paidamount.trim();
               double Paidamount = Double.parseDouble(paidamount);
               String Balance_cf = txtBal_c_f.getText();
               double balance_cf = Double.parseDouble(Balance_cf);
               txtBal_c_f.setText(String.format("%.2f",balance_cf));
               
               double balance_cd = (balance_cf + Addloan) - Paidamount;
               txtBal_cd.setText(String.format("%.2f", balance_cd));
        
    }
    private void saveLoan(){
        try{
                String deductionname = txtDeduction.getText();
                String Amount = txtDeductionAmount.getText();
            
                if(deductionname.equals("")){
                    txtDeduction.requestFocus();
                }else if(Amount.equals("")){
                    txtDeductionAmount.requestFocus();
                }else{
                String sql = "INSERT INTO loantable(staffno,month,year,bal_cf,paidLoan,takenLoan,balance)VALUES(?,?,?,?,?,?,?)";
                pst = conn.prepareStatement(sql);
                
                pst.setString(1, txtStaffNo.getText());
                pst.setString(2, comboMonth.getSelectedItem().toString());
                pst.setString(3, comboYear.getSelectedItem().toString());
                pst.setString(4, txtBal_c_f.getText());
                pst.setString(5, txtDeductionAmount.getText());
                pst.setString(6, txtAddLoan.getText());
                pst.setString(7, txtBal_cd.getText());
                
                pst.execute();
             }
            }catch(Exception e){
                //JOptionPane.showMessageDialog(null ,e);
            }finally{
                try{
                    rs.close();
                    pst.close();
                }catch(Exception e){
                    
                }
            }
    }
//------------------------------------------------deductionsDialog---------------------------------------------------------------------------------------
private void showDialogDeduction(){
    DialogDeductions.setVisible(true);
    DialogDeductions.setSize(582,237);
    DialogDeductions.setResizable(false);
    DialogDeductions.setAlwaysOnTop(true);
    DialogDeductions.setLocationRelativeTo(null);
    Update_tableDeduction();
    resetDeds();
    DialogDeductions.setTitle("Deductions");
}
private void resetDeds(){
    txtDeduction.setText("");
    txtDeductionAmount.setText("0.00");
    txtAddLoan.setText("0.00");
    txtDeduction.requestFocus();
    buttonSaveDeduction.setEnabled(false);
    buttonDeleteDeduction.setEnabled(false);
}
private void Update_tableDeduction(){
    try{
        String Month = comboMonth.getSelectedItem().toString();
        String Year = comboYear.getSelectedItem().toString();
        
        String sql = "SELECT deductionId AS 'id',deductionName AS 'Deduction',amount AS 'Amount' FROM deductionstable WHERE staffNo = '"+staffno+"'"
                + " AND month = '"+Month+"' AND year = '"+Year+"'";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        tableDeductions.setModel(DbUtils.resultSetToTableModel(rs));
        TableColumn idColumn = tableDeductions.getColumn("id");
        idColumn.setMaxWidth(0);
        idColumn.setMinWidth(0);
        idColumn.setPreferredWidth(0);
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

    private String staffNo;
    private double initial_amount;
private void selectedRowDeductionTable(){
        try{
            
            int row = tableDeductions.getSelectedRow();
            String Table_click = tableDeductions.getValueAt(row, 0).toString();
           
            String sql = "SELECT * FROM deductionstable WHERE deductionId = '"+Table_click+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            if(rs.next()){
                String id = rs.getString("deductionId");
                txtDeductionId.setText(id);
                
                String deductionname = rs.getString("deductionName");
                txtDeduction.setText(deductionname);
               
                String Amount = rs.getString("amount");
                txtDeductionAmount.setText(Amount); 
                
                staffNo = rs.getString("staffno");
                
                String initial = txtDeductionAmount.getText();
                initial = initial.trim();
                double initialamount = Double.parseDouble(initial);
                initial_amount = initialamount;
                
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
  private void saveDeduction(){
        try{
            String deductionname = txtDeduction.getText();
            String Amount = txtDeductionAmount.getText();
            
            if(deductionname.equals("")){
                txtDeduction.requestFocus();
            }else if(Amount.equals("")){
                txtDeductionAmount.requestFocus();
            }else{
            String sql = "INSERT INTO deductionstable(staffNo,deductionName,amount,month,year)VALUES(?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, txtStaffNo.getText()); 
            pst.setString(2, deductionname);
            pst.setString(3, Amount);
            pst.setString(4, comboMonth.getSelectedItem().toString());
            pst.setString(5, comboYear.getSelectedItem().toString());
            pst.execute();
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
        Update_tableDeduction();
    }
   
    private void deleteDeductions(){
        try{
            
            String sql = "DELETE FROM deductionstable WHERE deductionId = '"+txtDeductionId.getText()+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            calculation();
            resetDeds();
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
            
            String sql = "DELETE FROM loantable WHERE staffno = '"+txtStaffNo.getText()+"' AND month = '"+comboMonth.getSelectedItem().toString()+"'"
                    + " AND year = '"+comboYear.getSelectedItem().toString()+"'";
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
        Update_tableDeduction();
    }
    private void showTotalDeduction(){
              String staffno = txtStaffNo.getText();
              String Month = comboMonth.getSelectedItem().toString();
              String Year = comboYear.getSelectedItem().toString();
        try{
            String sql = "SELECT COALESCE(SUM(amount),0) FROM deductionsTable WHERE staffNo = '"+staffno+"'AND month = '"+Month+"' "
                    + "AND year = '"+Year+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
                if(rs.next()){
                    txtOtherDeductions.setText(rs.getString("COALESCE(SUM(amount),0)"));
                }
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e+ " panelevaluation.showtotaldeduction");
       }finally{
           try{
               rs.close();
               pst.close();
           }catch(Exception e){
               
           }
       }
    }
//------------------------------------------------allEmployees-------------------------------------------------------------------------------------------
public void showDialogAllEmployees(){
        DialogAllEmployees.setVisible(true);
        DialogAllEmployees.setSize(476,480);
        DialogAllEmployees.setLocationRelativeTo(null);
        DialogAllEmployees.setAlwaysOnTop(true);
        DialogAllEmployees.setResizable(false);
        DialogAllEmployees.setTitle("All Employees...");
        Update_tableAllEmployees();
    }   
private void checkRelief(){
    try{
        String sql = "SELECT * FROM taxablerangestable WHERE id = '1'";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
         if(rs.next()){
             String relief = rs.getString("reliefAmount");
             txtRelief.setText(relief);
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
private void selectedRowAllEmployees(){
        try{
            buttonUpdatePayment.setEnabled(false);
            buttonDeletePayment.setEnabled(false);
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
                    txtEmployeeName.setText(fname +" "+lname);
                    
                    String basic = rs.getString("basicSalary");
                    txtBasicSalaryIncome.setText(basic);
                    
                    String pin = rs.getString("pinNo");
                    txtPinNo.setText(pin);
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
//-------------------------------------------------------------------------------------------------------------------------------------------------------
 
    private void selectedRowPayment(){
        try{
            
            buttonSavePayment.setEnabled(false);
            buttonUpdatePayment.setEnabled(true);
            buttonDeletePayment.setEnabled(true);
            
            int row = tablePayment.getSelectedRow();
            String Table_click = tablePayment.getValueAt(row, 0).toString();
            
            String sql = "SELECT * FROM paymentTable WHERE paymentId = '"+Table_click+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            if(rs.next()){
                String Id = rs.getString("paymentId");
                txtPaymentId.setText(Id);
                System.out.println("paymentid: "+Id);
                
                String name = rs.getString("name");
                txtEmployeeName.setText(name);
                 System.out.println("name: "+name);
                
                staffno = rs.getString("staffNo");
                txtStaffNo.setText(staffno);
                 System.out.println("staffno: "+staffno);
                
                String month = rs.getString("month");
                comboMonth.setSelectedItem(month);
                 System.out.println("month: "+month);
                
                String year = rs.getString("year");
                comboYear.setSelectedItem(year);
                 System.out.println("year: "+year);
                
                String advance = rs.getString("advance");
                txtAdvance.setText(advance);
                 System.out.println("advance: "+advance);
                
                String paydate = rs.getString("datePay");
                ((JTextField)chooserPaymentDate.getDateEditor().getUiComponent()).setText(paydate);
                 System.out.println("paydate: "+paydate);
                
                String Nssf = rs.getString("nssf");
                txtNSSF.setText(Nssf);
                 System.out.println("nssf: "+Nssf);
                
                String Nhif = rs.getString("nhif");
                txtNHIF.setText(Nhif);
                 System.out.println("nhif: "+Nhif);
                
                String Paye = rs.getString("paye");
                txtPAYE.setText(Paye);
                 System.out.println("paye: "+Paye);
                
                String otherdeductions = rs.getString("otherDeductions");
                txtOtherDeductions.setText(otherdeductions);
                 System.out.println("otherdeductions: "+otherdeductions);
                
                String taxable = rs.getString("taxableAmount");
                txtTaxableAmount.setText(taxable);
                 System.out.println("taxable: "+taxable);
                
                String totaldeduction = rs.getString("totalDeductions");
                txtTotalDeductions.setText(totaldeduction);
                 System.out.println("totaldeductions: "+totaldeduction);
                
                String net = rs.getString("netSalary");
                txtNetSalary.setText(net); 
                System.out.println("net: "+net);
                
                String Tier1 = rs.getString("tier1");
                txtTier1.setText(Tier1);
                
                String Tier2 = rs.getString("tier2");
                txtTier2.setText(Tier2);
                
                String Gross = rs.getString("gross");
                txtGross.setText(Gross);
                 System.out.println("gross: "+Gross);
                
                
            }
        
        }catch(Exception e){
            //JOptionPane.showMessageDialog(null,e);
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
                    txtEmployeeName.setText(fname+" "+lname);
                    
                    String basic = rs.getString("basicSalary");
                txtBasicSalaryIncome.setText(basic);
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
    private void updatePayment(){
        try{
             String Id =  txtPaymentId.getText();
             String Staffno = txtStaffNo.getText();
             String Name = txtEmployeeName.getText();
             String Month = comboMonth.getSelectedItem().toString();
             String Year = comboYear.getSelectedItem().toString();
             String Paydate = ((JTextField)chooserPaymentDate.getDateEditor().getUiComponent()).getText();
             String Nssf = txtNSSF.getText();
             String Nhif = txtNHIF.getText();
             String Paye = txtPAYE.getText();
             String Otherdeductions = txtOtherDeductions.getText();
             String Taxableamount = txtTaxableAmount.getText();
             String Totaldeduction = txtTotalDeductions.getText();
             String Netsalary = txtNetSalary.getText();
             String Advance = txtAdvance.getText();
             String Gross = txtGross.getText();
             
             String sql = "UPDATE paymenttable SET staffno = '"+Staffno+"',name = '"+Name+"',month = '"+Month+"'"
                     + ",year = '"+Year+"',nssf = '"+Nssf+"',nhif = '"+Nhif+"',advance = '"+Advance+"',otherdeductions = '"+Otherdeductions+"',"
                     + "paye = '"+Paye+"',taxableamount = '"+Taxableamount+"',totalDeductions = '"+Totaldeduction+"',netsalary = '"+Netsalary+"'"
                     + ",gross = '"+Gross+"',datepay = '"+Paydate+"' WHERE paymentid = '"+Id+"'";
             pst = conn.prepareStatement(sql);
             pst.execute();
             JOptionPane.showMessageDialog(null, "Updated!");
             Update_TablePayment();
             resetDeductions();
             resetPayment();
             
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e+" panelevaluaation.updatePayment");
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        Update_TablePayment();
    }
    private void updateallamounts(){
        try{
            String current = txtDeductionAmount.getText();
            current = current.trim();
            double currentamount = Double.parseDouble(current);
            String sql = "UPDATE loantable SET paidloan = paidloan + ('"+currentamount+"' - '"+initial_amount+"'), balance = balance - "
                    + "('"+currentamount+"' - '"+initial_amount+"') WHERE staffno = '"+staffNo+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e+" panelevaluation.updateallamounts");
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
    }
    private void deleteKra(){
        try{
            String sql = "DELETE FROM kratable WHERE name = '"+txtEmployeeName.getText()+"' AND month = '"+comboMonth.getSelectedItem().toString()+"'"
                    + " AND year = '"+comboYear.getSelectedItem().toString()+"'";
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
    }
    
    
    private void deletePayment(){
        String id = txtPaymentId.getText();
        String Month = comboMonth.getSelectedItem().toString();
        String Year = comboYear.getSelectedItem().toString();
        try{
            String sql = "DELETE FROM paymentTable WHERE paymentId = '"+id+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            deleteKra();
            JOptionPane.showMessageDialog(null,"Deleted");
            resetPayment();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
            try{
                String sql = "DELETE FROM advanceTable WHERE month = '"+Month+"' AND year = '"+Year+"' AND payrollnumber = '"+staffno+"'";
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
            try{
                String sql = "DELETE FROM deductionsTable WHERE staffNo = '"+staffno+"' AND month = '"+Month+"' AND year = '"+Year+"' ";
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
            try{
                String sql = "DELETE FROM benefitstable WHERE  month = '"+Month+"' AND year = '"+Year+"' AND staffNo = '"+staffno+"'";
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
            try{
                String sql = "DELETE FROM loantable WHERE  month = '"+Month+"' AND year = '"+Year+"' AND staffNo = '"+staffno+"'";
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
            Update_TablePayment();
        }
    }
    public void Update_TablePayment(){
        try{
            String Month = comboMonth.getSelectedItem().toString();
            String Year = comboYear.getSelectedItem().toString();
            String sql = "SELECT paymentTable.paymentId AS 'id',paymentTable.staffNo AS 'Staff No',"
                    + "employeeRegistrationTable.firstName||' '||employeeRegistrationTable.lastName AS 'Name',employeeRegistrationTable.basicsalary AS 'Basic'"
                    + ",paymentTable.gross AS 'Gross',paymentTable.month AS 'Month',paymentTable.year AS 'Year',paymentTable.datePay AS 'Payment Date',paymentTable.nssf AS"
                    + " 'NSSF',paymentTable.nhif AS 'NHIF',paymentTable.paye AS 'PAYE',paymentTable.otherDeductions AS "
                    + "'Other Deductions',paymentTable.advance AS 'Advance',paymentTable.totalDeductions"
                    + " AS 'Total Deductions',paymentTable.netSalary AS 'Net Salary' FROM employeeRegistrationTable,paymentTable WHERE "
                    + "employeeRegistrationTable.staffNo = paymentTable.staffNo AND paymentTable.month = '"+Month+"' AND "
                    + "paymentTable.year = '"+Year+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tablePayment.setModel(DbUtils.resultSetToTableModel(rs));
            TableColumn idColumn = tablePayment.getColumn("id");
            idColumn.setMaxWidth(0);
            idColumn.setMinWidth(0);
            idColumn.setPreferredWidth(0);
        }catch(Exception e){
            //JOptionPane.showMessageDialog(null,e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
    }
//---------------------------save button to the payment records(paymentTable)---------------------------------------------------------------------------------------------
    private void resetPayment(){
        txtStaffNo.setText("");
        txtEmployeeName.setText("");
        txtBasicSalaryIncome.setText("0.00");
        txtNSSF.setText("0.00");
        txtTier1.setText("0.00");
        txtTier2.setText("0.00");
        txtNHIF.setText("0.00");
        txtPAYE.setText("0.00");
        txtAdvance.setText("0.00");
        txtOtherDeductions.setText("0.00");
        txtTaxableAmount.setText("0.00");
        txtTotalDeductions.setText("0.00");
        txtNetSalary.setText("0.00");
        buttonSavePayment.setEnabled(true);
        buttonUpdatePayment.setEnabled(false);
        buttonDeletePayment.setEnabled(false);
        buttonPayslip.setEnabled(false);
        //monthNyear();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, 0);
        chooserPaymentDate.getDateEditor().setDate(c.getTime());
        buttonShowRegisteredEmployees.requestFocus();
    }
    
    private void savePayment(){
        try{
            String staffno = txtStaffNo.getText();
            if(staffno.equals("")){
                buttonShowRegisteredEmployees.requestFocus();
            }else{
            String sql = "INSERT INTO paymentTable (staffNo,month,year,datePay,nssf,nhif,paye,advance,otherDeductions,taxableAmount,"
                    + "totalDeductions,netSalary,tier1,tier2,Name,gross) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, txtStaffNo.getText());
            pst.setString(2, comboMonth.getSelectedItem().toString());
            pst.setString(3, comboYear.getSelectedItem().toString());
            pst.setString(4, ((JTextField)chooserPaymentDate.getDateEditor().getUiComponent()).getText());
            pst.setString(5, txtNSSF.getText());
            pst.setString(6, txtNHIF.getText());
            pst.setString(7, txtPAYE.getText());
            pst.setString(8, txtAdvance.getText());
            pst.setString(9, txtOtherDeductions.getText());
            pst.setString(10, txtTaxableAmount.getText());
            pst.setString(11, txtTotalDeductions.getText());
            pst.setString(12, txtNetSalary.getText());
            pst.setString(13, txtTier1.getText());
            pst.setString(14, txtTier2.getText());
            pst.setString(15, txtEmployeeName.getText());
            pst.setString(16, txtGross.getText());
           
            pst.execute();
            saveKra();
            JOptionPane.showMessageDialog(null,"Saved");
            resetPayment();
           }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        Update_TablePayment();
    }
 //----------------------------------------------------------DialogAdvance--------------------------------------------------------------------------------
    private void Update_tableAdvance(){
        try{
            String payrollnumber = txtStaffNo.getText();
            String month = comboMonth.getSelectedItem().toString();
            String year = comboYear.getSelectedItem().toString();
           
            String sql = "SELECT advanceId AS '',advanceDate AS 'Advance Date',advanceAmount AS 'Amount' FROM advanceTable WHERE payrollNumber = '"+payrollnumber+"' AND month = '"+month+"'AND year = '"+year+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tableAdvance.setModel(DbUtils.resultSetToTableModel(rs));
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
    private void selectedRowAdvanceTable(){
        try{
            
            int row = tableAdvance.getSelectedRow();
            String Table_click = tableAdvance.getValueAt(row, 0).toString();
            String sql = "SELECT * FROM advanceTable WHERE  advanceId = '"+Table_click+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            if(rs.next()){
                String Id = rs.getString("advanceId");
                txtAdvanceId.setText(Id);
                
                String advancedate = rs.getString("advanceDate");
                ((JTextField)chooserDateIncome.getDateEditor().getUiComponent()).setText(advancedate);
                
                String amount = rs.getString("advanceAmount");
                txtAdvanceAmount.setText(amount);        
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
    private void resetAdvance(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, 0);
        chooserDateIncome.getDateEditor().setDate(c.getTime());
        txtAdvanceAmount.setText("0.00");
        chooserDateIncome.requestFocus();
        buttonSaveAdvance.setEnabled(true);
        buttonDeleteAdvance.setEnabled(false);
    }
    private void saveAdvance(){
        try{
            String amount = txtAdvanceAmount.getText();
            amount = amount.trim();
            double Advance = Double.parseDouble(amount);
            
            String basic = txtBasicSalaryIncome.getText();
            basic = basic.trim();
            double Basicsalary = Double.parseDouble(basic);
            
            if(amount.equals("") && amount.equals("0.0")){
                txtAdvanceAmount.requestFocus();
            }else if(Advance >= Basicsalary){
                JOptionPane.showMessageDialog(null,"Cannot take Advance of that amount","Error!",JOptionPane.ERROR_MESSAGE);
                txtAdvanceAmount.setText("");
                txtAdvanceAmount.requestFocus();
            }else{
            String sql = "INSERT INTO advanceTable(payrollNumber,advanceDate,advanceAmount,month,year)VALUES(?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, txtStaffNo.getText());
            pst.setString(2, ((JTextField)chooserDateIncome.getDateEditor().getUiComponent()).getText());
            pst.setString(3, amount);
            pst.setString(4, comboMonth.getSelectedItem().toString());
            pst.setString(5, comboYear.getSelectedItem().toString());
            pst.execute();   
            
            resetAdvance();
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
        Update_tableAdvance();
    }
    private void deleteAdvance(){
        try{
            String Id = txtAdvanceId.getText();
            String sql = "DELETE FROM advanceTable WHERE advanceId = '"+Id+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            calculation();
            resetAdvance();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
               rs.close();
               pst.close();
            }catch(Exception e){
                
            }
        }
        Update_tableAdvance();
    }
     private void showTotalAdvance(){
         String payrollnumber = txtStaffNo.getText();
              String Month = comboMonth.getSelectedItem().toString();
              String Year = comboYear.getSelectedItem().toString();
        try{
            String sql = "SELECT sum(advanceAmount) FROM advanceTable WHERE payrollNumber = '"+payrollnumber+"'AND month = '"+Month+"' AND year = '"+Year+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
                if(rs.next()){
                    String totaladvance = rs.getString("SUM(advanceAmount)");
                    txtAdvance.setText(totaladvance);
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
      private void calculation(){
     String grossSalary = txtGross.getText();
     String otherDeductions = txtOtherDeductions.getText();
     String advance = txtAdvance.getText();
        double nssf = 200;
        //double nssf;
        double nhif;
        double paye;
        double tax;
        double tier1;
        double tier2;
        double a,b;
        
//------------------------------------------------calculating nssf current evaluation and tier 1 & tier 2---------------------------------------------------------------------
        
        grossSalary = grossSalary.trim();
        double gross = Double.parseDouble(grossSalary);

        otherDeductions = otherDeductions.trim();
        double others = Double.parseDouble(otherDeductions);

        advance = advance.trim();
        double advnce = Double.parseDouble(advance);
        
        
        
       try{
           double taxableAmount; 
           
           String sql = "SELECT * FROM  taxableRangesTable";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
           if(rs.next()){
            float nssfrate = rs.getFloat("nssfRate");
            
           
//            if (gross < 6000){
//
//            tier1 = 0;
//            tier2 = 0;
//            nssf = 0;
//
//        }else if( gross  == 6000){
//            tier1 = 6000 * nssfrate;
//            tier2 = 0;
//            nssf = tier1;
//        }else if(gross  > 6000 && gross  < 12000){
//            tier1 = 6000 * nssfrate;
//            a = gross  - 6000;
//            tier2 = a * nssfrate;
//            nssf = tier1 + tier2;
//        }else if (gross  == 12000){
//            tier1 = 6000 * nssfrate;
//            tier2 = 6000 * nssfrate;
//            nssf = tier1 + tier2;
//        }else if(gross  > 12000 && gross < 18000){
//            tier1 = 6000 * nssfrate;
//            b = (gross - 12000) * nssfrate;
//            tier2 = (6000 * nssfrate) + b;
//            nssf = tier1 + tier2;
//
//        }else{
//            tier1 = 6000 * nssfrate;
//            tier2 = (6000 * nssfrate) + (6000 * nssfrate);
//            nssf = tier1 + tier2;
//        }
 
            taxableAmount = gross  - nssf;  
           
//---------------------------calculate NHIF rate in effect from 1st April 2015--------------------------------------------------------
           
         if (gross <= 5999){
            nhif = 150;
        }else if(gross >= 6000 && gross < 7999){
            nhif = 300;
            
        }else if(gross >= 8000 && gross < 11999){
            nhif = 400;
            
        }else if(gross >= 12000 && gross < 14999){
            nhif = 500;
        }else if(gross >= 15000 && gross < 19999){
            nhif = 600;
        }else if(gross >= 20000 && gross < 24999){
            nhif = 750;
        }else if(gross >= 25000 && gross < 29999){
            nhif = 850;
        }else if(gross >= 30000 && gross < 34999){
            nhif = 900;
        }else if(gross >= 35000 && gross < 39999){
            nhif = 950;
        }else if(gross >= 40000 && gross < 44999){
            nhif = 1000;
        }else if(gross >= 45000 && gross < 49999){
            nhif = 1100;
        }else if(gross >= 50000 && gross < 59999){
            nhif = 1200;
        }else if(gross >= 60000 && gross < 69999){
            nhif = 1300;
        }else if(gross >= 70000 && gross < 79999){
            nhif = 1400;
        }else if(gross >= 80000 && gross < 89999){
            nhif = 1500;
        }else if(gross >= 90000 && gross < 99999){
            nhif = 1600;
        }else{
            nhif = 1700;
        }
//-------------------------------------------------------------------------------calculating PAYE--------------------------------------------------------
         
            double Amount1 = rs.getDouble("amount1");
            double Amount2 = rs.getDouble("amount2");
            double Amount3 = rs.getDouble("amount3");
            double Amount4 = rs.getDouble("amount4");
            double relief = rs.getDouble("reliefAmount");
            double amount1rate = rs.getDouble("amount1Rate");
            double amount2rate = rs.getDouble("amount2Rate");
            double amount3rate = rs.getDouble("amount3Rate");
            double amount4rate = rs.getDouble("amount4Rate");
            double amount5rate = rs.getDouble("amount5Rate");
            double c,d,e,f,g;

            c = Amount1 * amount1rate;
            d = ((taxableAmount - Amount1) * amount2rate) + c;
            e = ((taxableAmount - Amount2) * amount3rate) + ((Amount2 -Amount1) * amount2rate) + c;
            f = ((taxableAmount - Amount3) * amount4rate) + ((Amount3 - Amount2) * amount3rate) + ((Amount2 -Amount1) * amount2rate) + c;
            g = ((taxableAmount - Amount4) * amount5rate) +((Amount4 - Amount3) * amount4rate) + ((Amount3 - Amount2) * amount3rate) + ((Amount2 -Amount1) * amount2rate) + c;

            if (taxableAmount >= 1 && taxableAmount < Amount1){
            tax = c;
        }else if(taxableAmount > Amount1 && taxableAmount <= Amount2){
            tax = d;
        }else if(taxableAmount > Amount2 && taxableAmount <= Amount3){
            tax = e;
        }else if(taxableAmount > Amount3 && taxableAmount <= Amount4){
            tax = f;
        }else{
            tax = g;
        }
            paye = tax - relief;
            txtPAYE.setText(String.format("%.2f",paye));
            txtNHIF.setText(String.format("%.2f",nhif));
            txtTaxableAmount.setText(String.format("%.2f", taxableAmount));
            //txtTier1.setText(String.format("%.2f", tier1));
            //txtTier2.setText(String.format("%.2f",tier2));
            //txtNSSF.setText(String.format("%.2f",tier1 + tier2));
            txtNSSF.setText(String.format("%.2f",nssf));
            txtTotalDeductions.setText(String.format("%.2f", nssf + nhif + paye + advnce + others));
            txtNetSalary.setText(String.format("%.2f",(gross)-(nssf + nhif + paye + advnce + others) ));
     } 
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,e+" panelevaluation.calculation");
       }finally{
           try{
               rs.close();
               pst.close();
           }catch(Exception e){
               
           }
       }
     }
     private void resetDeductions(){
         txtNSSF.setText("0.00");
         txtNHIF.setText("0.00");
         txtPAYE.setText("0.00");
         txtAdvance.setText("0.00");
         txtOtherDeductions.setText("0.00");
         txtTaxableAmount.setText("0.00");
         txtTotalDeductions.setText("0.00");
         txtNetSalary.setText("0.00");
     }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DialogAdvance = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtAdvanceAmount = new javax.swing.JTextField();
        buttonSaveAdvance = new javax.swing.JButton();
        buttonDeleteAdvance = new javax.swing.JButton();
        chooserDateIncome = new com.toedter.calendar.JDateChooser();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableAdvance = new javax.swing.JTable();
        txtAdvanceId = new javax.swing.JTextField();
        DialogTier = new javax.swing.JDialog();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        txtTier1 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtTier2 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtPaymentId = new javax.swing.JTextField();
        DialogAllEmployees = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableAllEmployees = new javax.swing.JTable();
        DialogDeductions = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        txtDeduction = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDeductionAmount = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDeductions = new javax.swing.JTable();
        buttonDeleteDeduction = new javax.swing.JButton();
        buttonSaveDeduction = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        txtBal_cd = new javax.swing.JTextField();
        txtBal_c_d = new javax.swing.JLabel();
        txtAddLoan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtBal_c_f = new javax.swing.JTextField();
        txtException = new javax.swing.JLabel();
        txtDeductionId = new javax.swing.JTextField();
        txtPinNo = new javax.swing.JTextField();
        txtRelief = new javax.swing.JTextField();
        DialogBenefits = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        buttonDelete = new javax.swing.JButton();
        buttonAdd = new javax.swing.JButton();
        buttonSave = new javax.swing.JButton();
        buttonUpdate = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableBenefits = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtBenefitsAmount = new javax.swing.JTextField();
        comboBenefit = new javax.swing.JComboBox();
        txtBenefitsId = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        buttonShowRegisteredEmployees = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtBasicSalaryIncome = new javax.swing.JTextField();
        txtEmployeeName = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtStaffNo = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        txtTotalDeductions = new javax.swing.JTextField();
        txtNHIF = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtNSSF = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtPAYE = new javax.swing.JTextField();
        txtAdvance = new javax.swing.JTextField();
        txtOtherDeductions = new javax.swing.JTextField();
        txtTaxableAmount = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtNetSalary = new javax.swing.JTextField();
        buttonView = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        txtAddPayment = new javax.swing.JButton();
        buttonSavePayment = new javax.swing.JButton();
        buttonDeletePayment = new javax.swing.JButton();
        buttonPayslip = new javax.swing.JButton();
        buttonUpdatePayment = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        txtGross = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablePayment = new javax.swing.JTable();
        comboYear = new javax.swing.JComboBox();
        jLabel24 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        comboMonth = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        chooserPaymentDate = new com.toedter.calendar.JDateChooser();

        DialogAdvance.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DialogAdvance.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                DialogAdvanceWindowClosing(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                DialogAdvanceWindowClosed(evt);
            }
        });

        jPanel8.setBackground(java.awt.Color.white);
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.yellow, java.awt.Color.blue));

        jPanel7.setBackground(java.awt.Color.white);
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "advance", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 2, 12), java.awt.Color.blue)); // NOI18N

        jLabel22.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel22.setForeground(java.awt.Color.blue);
        jLabel22.setText("Date:");

        jLabel23.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel23.setForeground(java.awt.Color.blue);
        jLabel23.setText("Advance:");

        txtAdvanceAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAdvanceAmountFocusLost(evt);
            }
        });
        txtAdvanceAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAdvanceAmountKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAdvanceAmountKeyPressed(evt);
            }
        });

        buttonSaveAdvance.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        buttonSaveAdvance.setText("SAVE");
        buttonSaveAdvance.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonSaveAdvance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveAdvanceActionPerformed(evt);
            }
        });

        buttonDeleteAdvance.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        buttonDeleteAdvance.setText("DELETE");
        buttonDeleteAdvance.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonDeleteAdvance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteAdvanceActionPerformed(evt);
            }
        });

        chooserDateIncome.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAdvanceAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chooserDateIncome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonSaveAdvance, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonDeleteAdvance, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSaveAdvance)
                    .addComponent(chooserDateIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtAdvanceAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttonDeleteAdvance))
                .addGap(18, 18, 18))
        );

        tableAdvance.setForeground(java.awt.Color.black);
        tableAdvance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableAdvance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableAdvanceMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tableAdvance);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout DialogAdvanceLayout = new javax.swing.GroupLayout(DialogAdvance.getContentPane());
        DialogAdvance.getContentPane().setLayout(DialogAdvanceLayout);
        DialogAdvanceLayout.setHorizontalGroup(
            DialogAdvanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        DialogAdvanceLayout.setVerticalGroup(
            DialogAdvanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        txtAdvanceId.setText("jTextField1");

        jPanel11.setBackground(java.awt.Color.lightGray);
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, java.awt.Color.pink));

        jPanel12.setBackground(java.awt.Color.white);
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "tier 1 & 2", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 2, 12), java.awt.Color.blue)); // NOI18N

        txtTier1.setFont(new java.awt.Font("DejaVu Sans", 2, 12)); // NOI18N
        txtTier1.setText("                    ");
        txtTier1.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, null));

        jLabel34.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel34.setForeground(java.awt.Color.blue);
        jLabel34.setText("TIER 2:");

        txtTier2.setFont(new java.awt.Font("DejaVu Sans", 2, 12)); // NOI18N
        txtTier2.setText("                    ");
        txtTier2.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, null));

        jLabel31.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel31.setForeground(java.awt.Color.blue);
        jLabel31.setText("TIER 1:");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel34)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTier1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTier2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtTier1))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txtTier2))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout DialogTierLayout = new javax.swing.GroupLayout(DialogTier.getContentPane());
        DialogTier.getContentPane().setLayout(DialogTierLayout);
        DialogTierLayout.setHorizontalGroup(
            DialogTierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DialogTierLayout.setVerticalGroup(
            DialogTierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        DialogAllEmployees.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DialogAllEmployees.setBackground(java.awt.Color.white);
        DialogAllEmployees.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                DialogAllEmployeesWindowClosing(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                DialogAllEmployeesWindowClosed(evt);
            }
        });

        jPanel2.setBackground(java.awt.Color.white);

        tableAllEmployees.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableAllEmployees.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableAllEmployeesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tableAllEmployeesMouseEntered(evt);
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

        DialogDeductions.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DialogDeductions.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                DialogDeductionsWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                DialogDeductionsWindowClosing(evt);
            }
        });

        jPanel1.setBackground(java.awt.Color.white);

        txtDeduction.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDeductionKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDeductionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDeductionKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel2.setForeground(java.awt.Color.blue);
        jLabel2.setText("Deduction:");

        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel3.setForeground(java.awt.Color.blue);
        jLabel3.setText("Amount:");

        txtDeductionAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDeductionAmountActionPerformed(evt);
            }
        });
        txtDeductionAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDeductionAmountKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDeductionAmountKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDeductionAmountKeyReleased(evt);
            }
        });

        tableDeductions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableDeductions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDeductionsMouseClicked(evt);
            }
        });
        tableDeductions.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableDeductionsKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tableDeductions);

        buttonDeleteDeduction.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        buttonDeleteDeduction.setText("DELETE");
        buttonDeleteDeduction.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonDeleteDeduction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteDeductionActionPerformed(evt);
            }
        });

        buttonSaveDeduction.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        buttonSaveDeduction.setText("SAVE");
        buttonSaveDeduction.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonSaveDeduction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveDeductionActionPerformed(evt);
            }
        });

        jPanel5.setBackground(java.awt.Color.white);
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "loan details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 2, 12), java.awt.Color.blue)); // NOI18N

        txtBal_cd.setEditable(false);

        txtBal_c_d.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        txtBal_c_d.setForeground(java.awt.Color.blue);
        txtBal_c_d.setText("Bal c/d:");

        txtAddLoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAddLoanKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAddLoanKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel6.setForeground(java.awt.Color.blue);
        jLabel6.setText("Add Loan:");

        jLabel8.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel8.setForeground(java.awt.Color.blue);
        jLabel8.setText("Bal c/f:");

        txtBal_c_f.setEditable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtBal_c_d)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAddLoan)
                    .addComponent(txtBal_c_f)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtBal_cd, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtBal_c_f, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtAddLoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBal_cd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBal_c_d))
                .addGap(20, 20, 20))
        );

        txtException.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        txtException.setForeground(java.awt.Color.red);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel3)))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDeduction, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(txtDeductionAmount))
                        .addGap(90, 90, 90)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(buttonSaveDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonDeleteDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(22, 22, 22))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtException, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(240, 240, 240))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtDeductionAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonDeleteDeduction))
                                .addGap(13, 13, 13)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buttonSaveDeduction)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtException, javax.swing.GroupLayout.DEFAULT_SIZE, 12, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout DialogDeductionsLayout = new javax.swing.GroupLayout(DialogDeductions.getContentPane());
        DialogDeductions.getContentPane().setLayout(DialogDeductionsLayout);
        DialogDeductionsLayout.setHorizontalGroup(
            DialogDeductionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DialogDeductionsLayout.setVerticalGroup(
            DialogDeductionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        txtDeductionId.setText("jTextField1");

        txtPinNo.setText("jTextField1");

        txtRelief.setText("jTextField1");

        DialogBenefits.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DialogBenefits.setBackground(java.awt.Color.white);
        DialogBenefits.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                DialogBenefitsWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                DialogBenefitsWindowClosing(evt);
            }
        });

        jPanel4.setBackground(java.awt.Color.white);
        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel4.setForeground(java.awt.Color.blue);
        jLabel4.setText("Benefit Name:");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        buttonDelete.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonDelete.setText("Delete");
        buttonDelete.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });

        buttonAdd.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonAdd.setText("Add");
        buttonAdd.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
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

        buttonUpdate.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonUpdate.setText("Update");
        buttonUpdate.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAdd)
                    .addComponent(buttonSave)
                    .addComponent(buttonDelete)
                    .addComponent(buttonUpdate))
                .addContainerGap())
        );

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
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableBenefitsKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableBenefitsKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tableBenefits);

        jLabel5.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel5.setForeground(java.awt.Color.blue);
        jLabel5.setText("Amount:");

        txtBenefitsAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBenefitsAmountActionPerformed(evt);
            }
        });
        txtBenefitsAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBenefitsAmountKeyTyped(evt);
            }
        });

        comboBenefit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Benefit" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBenefitsAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBenefit, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBenefit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtBenefitsAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout DialogBenefitsLayout = new javax.swing.GroupLayout(DialogBenefits.getContentPane());
        DialogBenefits.getContentPane().setLayout(DialogBenefitsLayout);
        DialogBenefitsLayout.setHorizontalGroup(
            DialogBenefitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        DialogBenefitsLayout.setVerticalGroup(
            DialogBenefitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        txtBenefitsId.setEditable(false);
        txtBenefitsId.setBackground(java.awt.Color.lightGray);
        txtBenefitsId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBenefitsIdKeyTyped(evt);
            }
        });

        setBackground(java.awt.Color.white);
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Evaluations & Payments", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 2, 12), java.awt.Color.blue)); // NOI18N

        jPanel6.setBackground(java.awt.Color.white);
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Employee", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 2, 12), java.awt.Color.blue)); // NOI18N

        buttonShowRegisteredEmployees.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        buttonShowRegisteredEmployees.setForeground(java.awt.Color.red);
        buttonShowRegisteredEmployees.setText("+++");
        buttonShowRegisteredEmployees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonShowRegisteredEmployeesActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel16.setForeground(java.awt.Color.blue);
        jLabel16.setText("Name:");

        txtBasicSalaryIncome.setEditable(false);
        txtBasicSalaryIncome.setBackground(new java.awt.Color(177, 193, 214));
        txtBasicSalaryIncome.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                txtBasicSalaryIncomeComponentAdded(evt);
            }
        });
        txtBasicSalaryIncome.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtBasicSalaryIncomeInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                txtBasicSalaryIncomeCaretPositionChanged(evt);
            }
        });
        txtBasicSalaryIncome.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtBasicSalaryIncomePropertyChange(evt);
            }
        });
        txtBasicSalaryIncome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBasicSalaryIncomeKeyPressed(evt);
            }
        });
        txtBasicSalaryIncome.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                txtBasicSalaryIncomeVetoableChange(evt);
            }
        });

        txtEmployeeName.setEditable(false);
        txtEmployeeName.setBackground(new java.awt.Color(177, 193, 214));

        jLabel17.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel17.setForeground(java.awt.Color.blue);
        jLabel17.setText("Basic Sal:");

        jLabel15.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel15.setForeground(java.awt.Color.blue);
        jLabel15.setText("Staff No:");

        txtStaffNo.setEditable(false);
        txtStaffNo.setBackground(new java.awt.Color(177, 193, 214));
        txtStaffNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStaffNoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(12, 12, 12)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtStaffNo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonShowRegisteredEmployees, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtEmployeeName)
                    .addComponent(txtBasicSalaryIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtStaffNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonShowRegisteredEmployees))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtBasicSalaryIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(89, Short.MAX_VALUE))
        );

        jPanel9.setBackground(java.awt.Color.white);
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "calculations", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 2, 12), java.awt.Color.blue)); // NOI18N

        txtTotalDeductions.setEditable(false);
        txtTotalDeductions.setBackground(new java.awt.Color(177, 193, 214));
        txtTotalDeductions.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTotalDeductionsKeyPressed(evt);
            }
        });

        txtNHIF.setEditable(false);
        txtNHIF.setBackground(new java.awt.Color(177, 193, 214));
        txtNHIF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNHIFKeyPressed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel26.setForeground(java.awt.Color.blue);
        jLabel26.setText(" NHIF:");

        jLabel30.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel30.setForeground(java.awt.Color.blue);
        jLabel30.setText("Total Deductions:");

        txtNSSF.setEditable(false);
        txtNSSF.setBackground(new java.awt.Color(177, 193, 214));
        txtNSSF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNSSFKeyPressed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel29.setForeground(java.awt.Color.blue);
        jLabel29.setText("Taxable Amount:");

        jLabel25.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel25.setForeground(java.awt.Color.blue);
        jLabel25.setText("NSSF:");

        jLabel32.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel32.setForeground(java.awt.Color.blue);
        jLabel32.setText("NET SALARY:");

        txtPAYE.setEditable(false);
        txtPAYE.setBackground(new java.awt.Color(177, 193, 214));
        txtPAYE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPAYEKeyPressed(evt);
            }
        });

        txtAdvance.setEditable(false);
        txtAdvance.setBackground(new java.awt.Color(177, 193, 214));
        txtAdvance.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtAdvancePropertyChange(evt);
            }
        });
        txtAdvance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAdvanceKeyPressed(evt);
            }
        });

        txtOtherDeductions.setEditable(false);
        txtOtherDeductions.setBackground(new java.awt.Color(177, 193, 214));
        txtOtherDeductions.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtOtherDeductionsPropertyChange(evt);
            }
        });
        txtOtherDeductions.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtOtherDeductionsKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOtherDeductionsKeyReleased(evt);
            }
        });

        txtTaxableAmount.setEditable(false);
        txtTaxableAmount.setBackground(new java.awt.Color(177, 193, 214));
        txtTaxableAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTaxableAmountKeyPressed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel27.setForeground(java.awt.Color.blue);
        jLabel27.setText("PAYE:");

        txtNetSalary.setEditable(false);
        txtNetSalary.setBackground(new java.awt.Color(177, 193, 214));
        txtNetSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNetSalaryActionPerformed(evt);
            }
        });
        txtNetSalary.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNetSalaryKeyPressed(evt);
            }
        });

        buttonView.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        buttonView.setForeground(java.awt.Color.red);
        buttonView.setText(">");
        buttonView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonViewActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jButton3.setForeground(java.awt.Color.blue);
        jButton3.setText("ADVANCE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel17.setBackground(java.awt.Color.white);
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txtAddPayment.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        txtAddPayment.setText("ADD");
        txtAddPayment.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtAddPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAddPaymentActionPerformed(evt);
            }
        });

        buttonSavePayment.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonSavePayment.setText("SAVE");
        buttonSavePayment.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonSavePayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSavePaymentActionPerformed(evt);
            }
        });

        buttonDeletePayment.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonDeletePayment.setText("DELETE");
        buttonDeletePayment.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonDeletePayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeletePaymentActionPerformed(evt);
            }
        });

        buttonPayslip.setBackground(java.awt.Color.lightGray);
        buttonPayslip.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonPayslip.setForeground(java.awt.Color.red);
        buttonPayslip.setText("Payslip");
        buttonPayslip.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonPayslip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPayslipActionPerformed(evt);
            }
        });

        buttonUpdatePayment.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonUpdatePayment.setText("UPDATE");
        buttonUpdatePayment.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonUpdatePayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdatePaymentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtAddPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSavePayment, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonUpdatePayment, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonDeletePayment, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonPayslip, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSavePayment)
                    .addComponent(txtAddPayment)
                    .addComponent(buttonDeletePayment)
                    .addComponent(buttonPayslip)
                    .addComponent(buttonUpdatePayment))
                .addGap(10, 10, 10))
        );

        jButton5.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jButton5.setForeground(java.awt.Color.blue);
        jButton5.setText("Deductions:");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jButton6.setForeground(java.awt.Color.blue);
        jButton6.setText("Benefits");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        txtGross.setEditable(false);
        txtGross.setBackground(new java.awt.Color(177, 193, 214));
        txtGross.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGrossActionPerformed(evt);
            }
        });
        txtGross.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGrossKeyPressed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel28.setForeground(java.awt.Color.blue);
        jLabel28.setText("Gross Sal:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel28)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtGross, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPAYE, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtNSSF)
                                        .addComponent(txtNHIF, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10)
                                .addComponent(buttonView, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAdvance)
                                .addGap(103, 103, 103)))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtTotalDeductions, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTaxableAmount, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtOtherDeductions, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel32)
                                .addGap(2, 2, 2)
                                .addComponent(txtNetSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(txtOtherDeductions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonView)
                            .addComponent(jButton5)
                            .addComponent(jButton6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(txtTaxableAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29)
                            .addComponent(txtGross, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTotalDeductions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtNSSF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNHIF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPAYE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtAdvance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNetSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel32)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        tablePayment.setForeground(java.awt.Color.black);
        tablePayment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablePayment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePaymentMouseClicked(evt);
            }
        });
        tablePayment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablePaymentKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(tablePayment);

        comboYear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Year", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        comboYear.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                comboYearPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel24.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel24.setForeground(java.awt.Color.blue);
        jLabel24.setText("PAYMENT DATE:");

        jLabel20.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel20.setForeground(java.awt.Color.blue);
        jLabel20.setText("Year:");

        comboMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        comboMonth.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                comboMonthPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel19.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel19.setForeground(java.awt.Color.blue);
        jLabel19.setText("Month:");

        chooserPaymentDate.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(comboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel24))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(comboYear, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chooserPaymentDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(162, 162, 162))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comboYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(comboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooserPaymentDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tablePaymentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablePaymentKeyReleased
        if (evt.getKeyCode()==KeyEvent.VK_DOWN || evt.getKeyCode()==KeyEvent.VK_UP){
            selectedRowPayment();
        }
    }//GEN-LAST:event_tablePaymentKeyReleased

    private void tablePaymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePaymentMouseClicked
        selectedRowPayment();
        buttonSavePayment.setEnabled(false);
        buttonDeletePayment.setEnabled(true);
        buttonPayslip.setEnabled(true);
        jButton5.setEnabled(true);
        buttonView.setEnabled(true);
        jButton6.setEnabled(true);
        jButton3.setEnabled(true);
    }//GEN-LAST:event_tablePaymentMouseClicked

    private void comboMonthPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboMonthPopupMenuWillBecomeInvisible
        Update_TablePayment();
         Update_tableBenefits();
    }//GEN-LAST:event_comboMonthPopupMenuWillBecomeInvisible
    private void showDialogTier(){
        DialogTier.setTitle("Tiers");
        DialogTier.setVisible(true);
        DialogTier.setSize(284,220);
        DialogTier.setResizable(false);
        DialogTier.setAlwaysOnTop(true);
        DialogTier.setLocationRelativeTo(null);
    }
    private void txtStaffNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStaffNoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStaffNoKeyPressed

    private void txtBasicSalaryIncomeVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_txtBasicSalaryIncomeVetoableChange

    }//GEN-LAST:event_txtBasicSalaryIncomeVetoableChange

    private void txtBasicSalaryIncomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBasicSalaryIncomeKeyPressed
       calculation();
    }//GEN-LAST:event_txtBasicSalaryIncomeKeyPressed

    private void txtBasicSalaryIncomePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtBasicSalaryIncomePropertyChange

    }//GEN-LAST:event_txtBasicSalaryIncomePropertyChange

    private void txtBasicSalaryIncomeCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtBasicSalaryIncomeCaretPositionChanged

    }//GEN-LAST:event_txtBasicSalaryIncomeCaretPositionChanged

    private void txtBasicSalaryIncomeInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtBasicSalaryIncomeInputMethodTextChanged

    }//GEN-LAST:event_txtBasicSalaryIncomeInputMethodTextChanged

    private void txtBasicSalaryIncomeComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_txtBasicSalaryIncomeComponentAdded

    }//GEN-LAST:event_txtBasicSalaryIncomeComponentAdded

    private void buttonShowRegisteredEmployeesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonShowRegisteredEmployeesActionPerformed
        
        showDialogAllEmployees();
    }//GEN-LAST:event_buttonShowRegisteredEmployeesActionPerformed

    private void buttonSaveAdvanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveAdvanceActionPerformed
        saveAdvance();
         try{
             String sql = "SELECT SUM(advanceAmount) FROM advanceTable WHERE payrollNumber = '"+txtStaffNo.getText()+"' AND "
                     + "month = '"+comboMonth.getSelectedItem().toString()+"' AND '"+comboYear.getSelectedItem().toString()+"'";
             pst = conn.prepareStatement(sql);
             rs = pst.executeQuery();
                if(rs.next()){
                   double Total_Advance = rs.getDouble("SUM(advanceAmount)");
                   if(rs.wasNull()){
                       txtAdvance.setText("0.00");
                   }else{
                       txtAdvance.setText(String.format("%.2f",Total_Advance));
                   }
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
    }//GEN-LAST:event_buttonSaveAdvanceActionPerformed

    private void buttonDeleteAdvanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteAdvanceActionPerformed
        deleteAdvance();
        try{
             String sql = "SELECT SUM(advanceAmount) FROM advanceTable WHERE payrollNumber = '"+txtStaffNo.getText()+"' AND "
                     + "month = '"+comboMonth.getSelectedItem().toString()+"' AND '"+comboYear.getSelectedItem().toString()+"'";
             pst = conn.prepareStatement(sql);
             rs = pst.executeQuery();
                if(rs.next()){
                   double Total_Advance = rs.getDouble("SUM(advanceAmount)");
                   if(rs.wasNull()){
                       txtAdvance.setText("0.00");
                   }else{
                       txtAdvance.setText(String.format("%.2f",Total_Advance));
                   }
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
        
    }//GEN-LAST:event_buttonDeleteAdvanceActionPerformed

    private void tableAdvanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAdvanceMouseClicked
        selectedRowAdvanceTable();
        buttonSaveAdvance.setEnabled(false);
        buttonDeleteAdvance.setEnabled(true);
    }//GEN-LAST:event_tableAdvanceMouseClicked

    private void ForAdvance(){
        try{
            String sql = "SELECT COALESCE(SUM(advanceamount),0) FROM advancetable WHERE payrollnumber = '"+txtStaffNo.getText()+"' AND "
                    + "month = '"+comboMonth.getSelectedItem().toString()+"' AND year = '"+comboYear.getSelectedItem().toString()+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
                if(rs.next()){
                    txtAdvance.setText(String.format("%.2f", rs.getDouble("COALESCE(SUM(advanceamount),0)")));
                }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"panelevaluation.ForAdvance");
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
    }
    private void DialogAdvanceWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_DialogAdvanceWindowClosing
        ForAdvance();
        calculation();
    }//GEN-LAST:event_DialogAdvanceWindowClosing

    private void tableAllEmployeesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAllEmployeesMouseClicked
        selectedRowAllEmployees();
        DialogAllEmployees.dispose();
        buttonSavePayment.setEnabled(true);
        Update_TablePayment();
        jPanel9.setEnabled(true);
        jButton6.setEnabled(true);
        jButton5.setEnabled(true);
        buttonView.setEnabled(true);
        jButton3.setEnabled(true);
        txtAdvance.setText("0.00");
        grossSalary();
        showTotalDeduction();
        calculation();
        resetDeds();
        
        
    }//GEN-LAST:event_tableAllEmployeesMouseClicked

    private void DialogAllEmployeesWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_DialogAllEmployeesWindowClosing
     
    }//GEN-LAST:event_DialogAllEmployeesWindowClosing

    private void txtAdvanceAmountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAdvanceAmountKeyTyped
        char vchar = evt.getKeyChar();
      if((!(Character.isDigit(vchar)))
          ||(vchar == KeyEvent.VK_BACK_SPACE)
          
          || (vchar == KeyEvent.VK_DELETE)){
        evt.consume();
    }
    }//GEN-LAST:event_txtAdvanceAmountKeyTyped

    private void txtDeductionAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDeductionAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDeductionAmountActionPerformed

    private void buttonDeleteDeductionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteDeductionActionPerformed
      deleteDeductions();
    }//GEN-LAST:event_buttonDeleteDeductionActionPerformed

    private void buttonSaveDeductionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveDeductionActionPerformed
//        String cf = txtBal_c_f.getText();
//        cf = cf.trim();
//        double balncf = Double.parseDouble(cf);
//        
//        String add = txtAddLoan.getText();
//        add = add.trim();
//        double addloan = Double.parseDouble(add);
//        
        String balcd = txtBal_cd.getText();
        balcd = balcd.trim();
        double Balcd = Double.parseDouble(balcd);

            if(Balcd < 0){
                txtAddLoan.requestFocus();
              //txtDeductionAmount.setText("0.00");
                saveDeduction();
                calculateloan();
            }else{
                if(txtDeduction.getText().equals("LOAN")){
                    saveDeduction();
                    saveLoan();
                    ForDeduction();
                    evaluateBalancebf();
                    resetDeds();
                }else{
                    saveDeduction();
                    ForDeduction();
                }
            }
        
        
    }//GEN-LAST:event_buttonSaveDeductionActionPerformed

    private void txtDeductionAmountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDeductionAmountKeyTyped
       char vchar = evt.getKeyChar();
      if((!(Character.isDigit(vchar)))
          ||(vchar == KeyEvent.VK_BACK_SPACE)
          
          || (vchar == KeyEvent.VK_DELETE)){
        evt.consume();
    }
    }//GEN-LAST:event_txtDeductionAmountKeyTyped

    private void txtDeductionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDeductionKeyReleased
        txtDeduction.setText(txtDeduction.getText().toUpperCase());
        if(txtDeduction.getText().equals("LOAN")){
            DialogDeductions.setVisible(true);
            DialogDeductions.setSize(914,237);
            DialogDeductions.setResizable(false);
            DialogDeductions.setAlwaysOnTop(true);
            DialogDeductions.setLocationRelativeTo(null);
            Update_tableDeduction();
            DialogDeductions.setTitle("Deductions");
            txtDeductionAmount.setText("");
            evaluateBalancebf();
            calculateloan();
        }else{
            DialogDeductions.setVisible(true);
            DialogDeductions.setSize(582,237);
            DialogDeductions.setResizable(false);
            DialogDeductions.setAlwaysOnTop(true);
            DialogDeductions.setLocationRelativeTo(null);
            Update_tableDeduction();
            DialogDeductions.setTitle("Deductions");
        }
    }//GEN-LAST:event_txtDeductionKeyReleased

    private void tableDeductionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDeductionsMouseClicked
        selectedRowDeductionTable();
        buttonSaveDeduction.setEnabled(false);
        buttonDeleteDeduction.setEnabled(true);
    }//GEN-LAST:event_tableDeductionsMouseClicked

    private void tableDeductionsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableDeductionsKeyReleased
        if (evt.getKeyCode()==KeyEvent.VK_DOWN || evt.getKeyCode()==KeyEvent.VK_UP){
            selectedRowDeductionTable();
        }
    }//GEN-LAST:event_tableDeductionsKeyReleased

    private void txtDeductionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDeductionKeyTyped
        char vchar = evt.getKeyChar();
      if(((Character.isDigit(vchar)))
          ||(vchar == KeyEvent.VK_BACK_SPACE)
          
          || (vchar == KeyEvent.VK_DELETE)){
        evt.consume();
    }
    }//GEN-LAST:event_txtDeductionKeyTyped

    private void txtDeductionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDeductionKeyPressed
        buttonSaveDeduction.setEnabled(true);
        buttonDeleteDeduction.setEnabled(false);
        txtException.setText("");
    }//GEN-LAST:event_txtDeductionKeyPressed

    private void txtDeductionAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDeductionAmountKeyPressed
        buttonSaveDeduction.setEnabled(true);
        buttonDeleteDeduction.setEnabled(false);
    }//GEN-LAST:event_txtDeductionAmountKeyPressed

    private void txtAdvanceAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAdvanceAmountKeyPressed
        buttonSaveAdvance.setEnabled(true);
        buttonDeleteAdvance.setEnabled(false);
    }//GEN-LAST:event_txtAdvanceAmountKeyPressed
    private void ForDeduction(){
        try{
             String sql = "SELECT COALESCE(SUM(amount), 0) FROM deductionstable WHERE staffNo = '"+txtStaffNo.getText()+"' AND "
                     + "month = '"+comboMonth.getSelectedItem().toString()+"' AND year = '"+comboYear.getSelectedItem().toString()+"'";
             pst = conn.prepareStatement(sql);
             rs = pst.executeQuery();
                if(rs.next()){
                   txtOtherDeductions.setText(String.format("%.2f", rs.getDouble("COALESCE(SUM(amount), 0)")));
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
    private void DialogDeductionsWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_DialogDeductionsWindowClosing
        showTotalDeduction();
        calculation();
        resetDeds();
        
        
        
        txtBal_c_f.setText("0.00");
        txtBal_cd.setText("0.00");
        txtAddLoan.setText("0.00");
    }//GEN-LAST:event_DialogDeductionsWindowClosing

    private void comboYearPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboYearPopupMenuWillBecomeInvisible
       Update_TablePayment();
       Update_tableBenefits();
    }//GEN-LAST:event_comboYearPopupMenuWillBecomeInvisible

    private void DialogAllEmployeesWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_DialogAllEmployeesWindowClosed
     
    }//GEN-LAST:event_DialogAllEmployeesWindowClosed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        showDialogDeduction();
        txtDeduction.setText("");
        txtDeductionAmount.setText("0.00");
        txtAddLoan.setText("0.00");
    }//GEN-LAST:event_jButton5ActionPerformed

    public void individualPayslip(){
        String Staffno = txtStaffNo.getText();
        String Month = comboMonth.getSelectedItem().toString();
        String Year = comboYear.getSelectedItem().toString();
        try {
            JasperDesign jd = JRXmlLoader.load("payslip.jrxml");
            String sql = "SELECT * FROM companytable,employeeregistrationtable,paymenttable,loantable WHERE "
                    + "employeeregistrationtable.staffNo = paymenttable.staffNo AND loantable.staffno = paymenttable.staffno "
                    + "AND paymenttable.staffNo = '"+Staffno+"' AND paymenttable.month = '"+Month+"' AND paymenttable.year"
                    + " = '"+Year+"'";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp,false);
                   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {

            }
        }

    }
    private void buttonPayslipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPayslipActionPerformed
    if(txtNetSalary.getText().equals("0.00")){
        JOptionPane.showMessageDialog(null, "No Payslip to Display. Please Select record from the Below table");
        tablePayment.requestFocus();
    }else{
        individualPayslip();
    }
    }//GEN-LAST:event_buttonPayslipActionPerformed

    private void buttonDeletePaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeletePaymentActionPerformed
       deletePayment();
    }//GEN-LAST:event_buttonDeletePaymentActionPerformed

    private void buttonSavePaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSavePaymentActionPerformed
        String net = txtNetSalary.getText();
        net = net.trim();
        double netsalary = Double.parseDouble(net);
        if(netsalary == 0){
            JOptionPane.showMessageDialog(null, "Error!, Cannot save 0 values!!");
            resetPayment();
        }else{
            try{
                String staffno = txtStaffNo.getText();
                String Month = comboMonth.getSelectedItem().toString();
                String Year = comboYear.getSelectedItem().toString();

                String sql = "SELECT * FROM paymenttable WHERE staffNo = ? AND month = ? AND year = ?";
                pst = conn.prepareStatement(sql);

                pst.setString(1, staffno);
                pst.setString(2, Month);
                pst.setString(3, Year);

                rs = pst.executeQuery();
                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "Record Exists. Enter another employee");
                    resetPayment();
                    buttonShowRegisteredEmployees.requestFocus();
                }else{
                    savePayment();
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
        
    }//GEN-LAST:event_buttonSavePaymentActionPerformed

    private void txtAddPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddPaymentActionPerformed
        resetPayment();
        monthNyear();
    }//GEN-LAST:event_txtAddPaymentActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        String payrollnumber = txtStaffNo.getText();
        if(payrollnumber.equals("")){
            JOptionPane.showMessageDialog(null, "Please Select Employee by clicking the +++ button");
            buttonShowRegisteredEmployees.requestFocus();
        }else{
            DialogAdvance.setVisible(true);
            DialogAdvance.setResizable(false);
            DialogAdvance.setAlwaysOnTop(true);
            DialogAdvance.setSize(475,360);
            DialogAdvance.setLocationRelativeTo(null);
            Update_tableAdvance();
            DialogAdvance.setTitle("Advances");
            resetAdvance();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void buttonViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonViewActionPerformed
        showDialogTier();
    }//GEN-LAST:event_buttonViewActionPerformed

    private void txtNetSalaryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNetSalaryKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNetSalaryKeyPressed

    private void txtNetSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNetSalaryActionPerformed

    }//GEN-LAST:event_txtNetSalaryActionPerformed

    private void txtTaxableAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTaxableAmountKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTaxableAmountKeyPressed

    private void txtOtherDeductionsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOtherDeductionsKeyReleased
        calculation();
    }//GEN-LAST:event_txtOtherDeductionsKeyReleased

    private void txtOtherDeductionsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOtherDeductionsKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOtherDeductionsKeyPressed

    private void txtOtherDeductionsPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtOtherDeductionsPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOtherDeductionsPropertyChange

    private void txtAdvanceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAdvanceKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdvanceKeyPressed

    private void txtAdvancePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtAdvancePropertyChange

    }//GEN-LAST:event_txtAdvancePropertyChange

    private void txtPAYEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPAYEKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPAYEKeyPressed

    private void txtNSSFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNSSFKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNSSFKeyPressed

    private void txtNHIFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNHIFKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNHIFKeyPressed

    private void txtTotalDeductionsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalDeductionsKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalDeductionsKeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
     showDialogBenefits(); 
     comboBenefit.removeAllItems();
     comboBenefit.addItem("Select Benefit");
     fillComboBenefits();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
       
        deleteBenefits();
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        resetBenefits();
    }//GEN-LAST:event_buttonAddActionPerformed

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
       String benefit = comboBenefit.getSelectedItem().toString();
       String Ben = txtBenefitsAmount.getText();
       Ben = Ben.trim();
       double Benefitamount = Double.parseDouble(Ben);
        if(benefit.equals("AIRTIME")){
            txtBenefitsAmount.setText(String.format("%.2f", Benefitamount * 0.30));
            saveBenefits();
        }else{
            saveBenefits();
        }
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        updateBenefits();
    }//GEN-LAST:event_buttonUpdateActionPerformed

    private void tableBenefitsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBenefitsMouseClicked
        selectedRowBenefits();
        buttonSave.setEnabled(false);
        buttonUpdate.setEnabled(true);
        buttonDelete.setEnabled(true);
    }//GEN-LAST:event_tableBenefitsMouseClicked

    private void tableBenefitsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableBenefitsKeyReleased
        if (evt.getKeyCode()==KeyEvent.VK_DOWN || evt.getKeyCode()==KeyEvent.VK_UP){
            selectedRowBenefits();
        }
    }//GEN-LAST:event_tableBenefitsKeyReleased

    private void DialogBenefitsWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_DialogBenefitsWindowOpened
       // totalLoans();
    }//GEN-LAST:event_DialogBenefitsWindowOpened

    private void txtBenefitsIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBenefitsIdKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBenefitsIdKeyTyped
    
    private void DialogBenefitsWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_DialogBenefitsWindowClosing
        removeComboBenefits();
        grossSalary();
        ForAdvance();
        //ForDeduction();
        calculation();
        showTotalDeduction();
    }//GEN-LAST:event_DialogBenefitsWindowClosing

    private void DialogAdvanceWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_DialogAdvanceWindowClosed
        
    }//GEN-LAST:event_DialogAdvanceWindowClosed

    private void txtAdvanceAmountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAdvanceAmountFocusLost
        
    }//GEN-LAST:event_txtAdvanceAmountFocusLost

    private void tableAllEmployeesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAllEmployeesMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tableAllEmployeesMouseEntered

    private void tableBenefitsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableBenefitsKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tableBenefitsKeyPressed

    private void txtBenefitsAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBenefitsAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBenefitsAmountActionPerformed

    private void txtBenefitsAmountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBenefitsAmountKeyTyped
         char vchar = evt.getKeyChar();
      if((!(Character.isDigit(vchar)))
          ||(vchar == KeyEvent.VK_BACK_SPACE)
          
          || (vchar == KeyEvent.VK_DELETE)){
        evt.consume();
    }
    }//GEN-LAST:event_txtBenefitsAmountKeyTyped

    private void txtGrossActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGrossActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGrossActionPerformed

    private void txtGrossKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGrossKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGrossKeyPressed

    private void buttonUpdatePaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdatePaymentActionPerformed
        String net = txtNetSalary.getText();
        net = net.trim();
        double net_sal = Double.parseDouble(net);
            if(net_sal == 0){
                JOptionPane.showMessageDialog(null, "Error!!! Please Choose Employee!");
                buttonShowRegisteredEmployees.requestFocus();
            }else{
               updatePayment();
            }
    }//GEN-LAST:event_buttonUpdatePaymentActionPerformed
    public void  allPayslips(){
        
        String Month = comboMonth.getSelectedItem().toString();
        String Year = comboYear.getSelectedItem().toString();
        try {
            JasperDesign jd = JRXmlLoader.load("payslip.jrxml");
            String sql = "SELECT * FROM companytable,employeeregistrationtable,paymenttable WHERE employeeregistrationtable.staffNo = "
                    + "paymenttable.staffNo AND paymenttable.month = '"+Month+"' AND paymenttable.year  = '"+Year+"'";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp,false);
                   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {

            }
        }

    }
    private void DialogDeductionsWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_DialogDeductionsWindowOpened
        evaluateBalancebf();
    }//GEN-LAST:event_DialogDeductionsWindowOpened

    private void txtDeductionAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDeductionAmountKeyReleased
        calculateloan();
    }//GEN-LAST:event_txtDeductionAmountKeyReleased

    private void txtAddLoanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAddLoanKeyReleased
       calculateloan();
    }//GEN-LAST:event_txtAddLoanKeyReleased

    private void txtAddLoanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAddLoanKeyTyped
        char vchar = evt.getKeyChar();
      if((!(Character.isDigit(vchar)))
          ||(vchar == KeyEvent.VK_BACK_SPACE)
          
          || (vchar == KeyEvent.VK_DELETE)){
        evt.consume();
    }
    }//GEN-LAST:event_txtAddLoanKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DialogAdvance;
    public javax.swing.JDialog DialogAllEmployees;
    private javax.swing.JDialog DialogBenefits;
    private javax.swing.JDialog DialogDeductions;
    private javax.swing.JDialog DialogTier;
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonDeleteAdvance;
    private javax.swing.JButton buttonDeleteDeduction;
    private javax.swing.JButton buttonDeletePayment;
    private javax.swing.JButton buttonPayslip;
    private javax.swing.JButton buttonSave;
    private javax.swing.JButton buttonSaveAdvance;
    private javax.swing.JButton buttonSaveDeduction;
    private javax.swing.JButton buttonSavePayment;
    private javax.swing.JButton buttonShowRegisteredEmployees;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JButton buttonUpdatePayment;
    private javax.swing.JButton buttonView;
    private com.toedter.calendar.JDateChooser chooserDateIncome;
    private com.toedter.calendar.JDateChooser chooserPaymentDate;
    private javax.swing.JComboBox comboBenefit;
    public javax.swing.JComboBox comboMonth;
    public javax.swing.JComboBox comboYear;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    public javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tableAdvance;
    public javax.swing.JTable tableAllEmployees;
    private javax.swing.JTable tableBenefits;
    private javax.swing.JTable tableDeductions;
    private javax.swing.JTable tablePayment;
    private javax.swing.JTextField txtAddLoan;
    private javax.swing.JButton txtAddPayment;
    private javax.swing.JTextField txtAdvance;
    private javax.swing.JTextField txtAdvanceAmount;
    private javax.swing.JTextField txtAdvanceId;
    private javax.swing.JLabel txtBal_c_d;
    private javax.swing.JTextField txtBal_c_f;
    private javax.swing.JTextField txtBal_cd;
    private javax.swing.JTextField txtBasicSalaryIncome;
    private javax.swing.JTextField txtBenefitsAmount;
    private javax.swing.JTextField txtBenefitsId;
    private javax.swing.JTextField txtDeduction;
    private javax.swing.JTextField txtDeductionAmount;
    private javax.swing.JTextField txtDeductionId;
    private javax.swing.JTextField txtEmployeeName;
    private javax.swing.JLabel txtException;
    private javax.swing.JTextField txtGross;
    private javax.swing.JTextField txtNHIF;
    private javax.swing.JTextField txtNSSF;
    private javax.swing.JTextField txtNetSalary;
    private javax.swing.JTextField txtOtherDeductions;
    private javax.swing.JTextField txtPAYE;
    private javax.swing.JTextField txtPaymentId;
    private javax.swing.JTextField txtPinNo;
    private javax.swing.JTextField txtRelief;
    public javax.swing.JTextField txtStaffNo;
    private javax.swing.JTextField txtTaxableAmount;
    private javax.swing.JLabel txtTier1;
    private javax.swing.JLabel txtTier2;
    private javax.swing.JTextField txtTotalDeductions;
    // End of variables declaration//GEN-END:variables

    private static class WorkBookNSheet {

        public WorkBookNSheet() {
        }
    }
}

