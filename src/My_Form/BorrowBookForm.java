/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package My_Form;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.sql.*;
import My_Classes.DB_connect;
import com.mysql.cj.jdbc.PreparedStatementWrapper;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
/**
 *
 * @author Chrisitian Dedil
 */
public class BorrowBookForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(BorrowBookForm.class.getName());

    /**
     * Creates new form BorrowBookForm
     */
    
    private String check = "";
    private int transactionId;
    private String action = "";
    private int borrowerId;
    
    public BorrowBookForm(int borrowerId) {
        initComponents();
        this.borrowerId = borrowerId;
        
        borrowerName(borrowerId, cmbName);
        loodBooks(cmbBook);
        cmbBook.addItemListener(new java.awt.event.ItemListener() {
       public void itemStateChanged(java.awt.event.ItemEvent evt) {
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            loadAcquisitionNumber(cmbAcquisitionNumber);
            }
           }
        });
        
        populateTable();
        
        rentalDate.setDate( new java.util.Date());
        dueDate.setDate( new java.util.Date());
       
    }
    
    private void makeEnabled(){
        cmbBook.setEnabled(true);
        
      
        dueDate.setEnabled(true);
        
        btnAdd.setEnabled(false);
        btnBorrow.setEnabled(true);
        btnClose.setText("Cancel");
        cmbAcquisitionNumber.setEnabled(true);
        cmbStatus.setEnabled(true);
    }
    
    private void setDefault(){
        cmbBook.setEnabled(false);
        rentalDate.setEnabled(false);
       
        dueDate.setEnabled(false);
        
        btnAdd.setEnabled(true);
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        btnBorrow.setEnabled(false);
        cmbAcquisitionNumber.setEnabled(false);
        rentalDate.setDate(new java.util.Date());
        dueDate.setDate(new java.util.Date());
        btnClose.setText("Close");
        cmbAcquisitionNumber.setSelectedIndex(0);
        cmbBook.setSelectedIndex(0);
        cmbStatus.setSelectedIndex(0);
    }

   
    
    public void populateTable(){
        
        try{
            
            Connection con = DB_connect.getConnection();
            
            String sql = "SELECT t.transaction_id, b.full_name, bo.title, bc.acquisition_number, t.rental_date, t.due_date, t.status " + 
                    "FROM transaction t " +
                    "JOIN borrower b ON t.borrower_id = b.borrower_id " +
                    "JOIN book bo ON t.book_id = bo.book_id " +
                    "JOIN book_copy bc ON t.copy_id = bc.copy_id " + 
                    "WHERE t.borrower_id = ?";
                    
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, borrowerId);
            
            DefaultTableModel model = (DefaultTableModel) tblTransactions.getModel();
            model.setRowCount(0);
            
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                    Date rent  = rs.getDate("rental_date");
                    String rentFormatDate = "";
                    if(rent != null){
                       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                       rentFormatDate = sdf.format(rent);
                    }
                    Date due  = rs.getDate("due_date");
                    String dueFormatDate = "";
                    if(due != null){
                       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                       dueFormatDate = sdf.format(due);
                    }
                     
                model.addRow(new Object[]{
                    rs.getInt("transaction_id"),
                    rs.getString("full_name"),
                    rs.getString("title"),
                    rs.getString("acquisition_number"),
                    rentFormatDate,
                    dueFormatDate,
                    rs.getString("status")
                    
                });
            }
            
            
            
        }catch(Exception error){
            JOptionPane.showMessageDialog(null, error);
        }
        
    }
     // generator for borrower Name:
    public void borrowerName(int borrowerId, JComboBox<String> cmbName){
        
        try{
            
            Connection con = DB_connect.getConnection();
            String sql = "SELECT full_name FROM borrower WHERE borrower_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, borrowerId);
           
            ResultSet rs = pst.executeQuery();
            
            cmbName.removeAllItems();
            while(rs.next()){
                
                String name =  rs.getString("full_name");
                cmbName.addItem(name);
                
            }
            
        }catch(Exception error){
            JOptionPane.showMessageDialog(null, error);
        }
            
            
        
    }
    
    // getter for borroerid;
    
    // generator for book;
    
    public void loodBooks(JComboBox<String> cmbBook){
       
        try{
            Connection con = DB_connect.getConnection();
            String sql = "SELECT title FROM book";
            PreparedStatement pst = con.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery();
            
            cmbBook.removeAllItems();
            
            while(rs.next()){
                
                String title = rs.getString("title");
                cmbBook.addItem(title);
            }
            
        }catch(Exception error){
            JOptionPane.showMessageDialog(null, error);
        }
        
    }
    
    // code for getting the bookName;
    public int getBookNameById(String bookTitle){
        int id = 0;
        
        try{
            Connection  con = DB_connect.getConnection();
            String sql = "SELECT book_id FROM book WHERE title = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, bookTitle);
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                 id = rs.getInt("book_id");
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error);
        }
        return id;
    }
    
    
    // getter of bookid;
    
    private int getBookId(String bookTitle){
        int id = 0;
        
        try{
            Connection con = DB_connect.getConnection();
            String sql = "SELECT book_id FROM book WHERE title = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, bookTitle);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                id = rs.getInt("book_id");
            }
            
        }catch(Exception err){
            
        }
        return id;
    }
        
    // generator for acquisition number;
    
    public void loadAcquisitionNumber(JComboBox<String> cmbAcquisitionNumber){
        
        String bookTitle = cmbBook.getSelectedItem().toString();
        
        int id = getBookId(bookTitle);
        try{
            
           Connection con = DB_connect.getConnection();
           String sql = "SELECT acquisition_number FROM book_copy WHERE book_id = ?";
           PreparedStatement pst = con.prepareStatement(sql);
           
           pst.setInt(1,id);
           
           ResultSet rs = pst.executeQuery();
           
           cmbAcquisitionNumber.removeAllItems();
           while(rs.next()){
               String number = rs.getNString("acquisition_number");
               cmbAcquisitionNumber.addItem(number);
           }
            
            
        }catch(Exception error){
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
    //copyid getter:
    
    public int getCopyId(String acquisition){
        int id = 0;
        
        try{
            
            Connection con = DB_connect.getConnection();
            String sql = "SELECT copy_id FROM book_copy WHERE acquisition_number = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, acquisition);
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                id = rs.getInt("copy_id");
            }
            
            
        }catch(Exception error){
            JOptionPane.showMessageDialog(null, error);
        }
        
        return id;
        
    }
    
    // safevalue indicator:
    private String safeValue(DefaultTableModel model, int row, int col){
         Object val = model.getValueAt(row, col);
         return(val == null) ? "" : val.toString();
     }
    
    // getter borrower status:
    private int getLimit(int borrowerId){
       int lim = 0;
       
       try{
           
           Connection con = DB_connect.getConnection();
           String query = "SELECT borrow_limit FROM borrower WHERE borrower_id = ?";
           PreparedStatement pst = con.prepareStatement(query);
           
           pst.setInt(1, borrowerId);
           
           ResultSet rs = pst.executeQuery();
           
           while(rs.next()){
               
               lim = rs.getInt("borrow_limit");
           }
           
           
       }catch(Exception err){
           
       }
       return lim;
    }
   // getter for book_copies status:
    
    private String getBookStatus(int copyId){
        
        String status = "";
        
        try{
            Connection con = DB_connect.getConnection();
            String sql = "SELECT status FROM book_copy WHERE copy_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, copyId);
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                
                status = rs.getString("status");
            }
            
        }catch(Exception err){
            
        }
        
        return status;
    }
    
    // get count:
    
    private int getCount(int borrowerId){
        int count = 0;
        try{
            
            Connection con = DB_connect.getConnection();
            String sql = "SELECT COUNT(*) FROM transaction WHERE borrower_id = ? AND status = 'Borrowed'";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, borrowerId);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
            count = rs.getInt(1);
            }
            
        }catch(Exception err){
            JOptionPane.showMessageDialog(null, err);
        }
        
        return count;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        btnBorrow = new javax.swing.JButton();
        cmbName = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbBook = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        rentalDate = new com.toedter.calendar.JDateChooser();
        dueDate = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransactions = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        cmbAcquisitionNumber = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel7.setText("Search");

        btnAdd.setText("ADD");
        btnAdd.addActionListener(this::btnAddActionPerformed);

        btnUpdate.setText("UPDATE");
        btnUpdate.setEnabled(false);
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);

        btnDelete.setText("DELETE");
        btnDelete.setEnabled(false);
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        btnClose.setText("Close");
        btnClose.addActionListener(this::btnCloseActionPerformed);

        btnBorrow.setText("Borrow");
        btnBorrow.setEnabled(false);
        btnBorrow.addActionListener(this::btnBorrowActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 67, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBorrow, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(66, 66, 66))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jLabel7)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBorrow, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        cmbName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbName.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setText("Borrower Name:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel2.setText("Transaction Maintenance");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setText("Book:");

        cmbBook.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbBook.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel4.setText("Rental Date:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel6.setText("Due Date:");

        rentalDate.setDateFormatString("yyyy-MM-dd");
        rentalDate.setEnabled(false);

        dueDate.setDateFormatString("yyyy-MM-dd");
        dueDate.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel8.setText("TRANSACTIONS:");

        tblTransactions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Transaction ID", "Borrower Name", "Book", "Acquisition Number", "Rental Date", "Due Date", "Status"
            }
        ));
        tblTransactions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTransactionsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTransactions);

        jLabel9.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel9.setText("Acquisition Number:");

        cmbAcquisitionNumber.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbAcquisitionNumber.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel5.setText("Status:");

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Borrowed", "Returned" }));
        cmbStatus.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 704, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cmbBook, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cmbName, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cmbAcquisitionNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rentalDate, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cmbStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(dueDate, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(22, 22, 22)))
                        .addGap(128, 128, 128)))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbBook, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cmbAcquisitionNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(rentalDate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(dueDate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        
        String close = btnClose.getText().trim();
        if(close.equalsIgnoreCase("close")){
            this.dispose();
        }else{
            setDefault();
        }
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        
        check = btnAdd.getText();
        rentalDate.setEnabled(true);
        makeEnabled();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnBorrowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrowActionPerformed
        // TODO add your handling code here:
        try{
       String status = "Borrowed";   
       int limit = getLimit(borrowerId);
       int count = getCount(borrowerId);
       
       Connection con = DB_connect.getConnection();
       String sql = "INSERT INTO transaction (borrower_id, book_id, copy_id, rental_date, due_date, status) VALUES (?, ?, ?, ?, ?, ?)";
       PreparedStatement pst = con.prepareStatement(sql);
      
       
       String bookUpdate = "UPDATE book_copy SET status = 'Borrowed' WHERE copy_id = ?";
       PreparedStatement ps = con.prepareStatement(bookUpdate);
       
     
       
       String bookTitle = cmbBook.getSelectedItem().toString();
       int bookid = getBookNameById(bookTitle);
       String acquisiton = cmbAcquisitionNumber.getSelectedItem().toString();
       int copyid = getCopyId(acquisiton);
       
       java.util.Date rent = rentalDate.getDate();
       java.util.Date due = dueDate.getDate();
       
       
       
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       java.util.Date today = new java.util.Date();
       String rentStr = sdf.format(rent);
       String dueStr = sdf.format(due);
       String todayStr = sdf.format(today);
       
       String bookStatus = getBookStatus(copyid);
       
       if(rentStr.compareTo(todayStr) < 0){
           JOptionPane.showMessageDialog(null, "Put a proper rental date.");
           return;
       }
       if(dueStr.compareTo(todayStr) < 0){
           JOptionPane.showMessageDialog(null, "Put a proper due date.");
           return;
       }
      
       if(count >= limit){
           JOptionPane.showMessageDialog(null, "Borrower limit reached.");
           return;
       }
       
       if(bookStatus.equalsIgnoreCase("Borrowed")){
           JOptionPane.showMessageDialog(null, "This book is already borrowed");
           return;
       }
       
       
       Date renta = new Date(rent.getTime());
       Date dateDue = new Date(due.getTime());
       
       
     
       // Upadting status of the copyies:
       ps.setInt(1, copyid);
       //Inserting the values: 
       pst.setInt(1, borrowerId);
       pst.setInt(2, bookid);
       pst.setInt(3, copyid);
       pst.setDate(4, renta);
       pst.setDate(5, dateDue);
       pst.setString(6, status);
       
       
       pst.executeUpdate();
       ps.executeUpdate();
       
       populateTable();
       JOptionPane.showMessageDialog(this , "The book has been borrowed");
       
       setDefault();
       
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error);
        }
        
    }//GEN-LAST:event_btnBorrowActionPerformed

    private void tblTransactionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTransactionsMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblTransactions.getModel();
        int row = tblTransactions.getSelectedRow();
        if(row == -1) return;
        
        transactionId = Integer.parseInt(safeValue(model, row, 0));
        cmbBook.setSelectedItem(safeValue(model, row, 2));
        loadAcquisitionNumber(cmbAcquisitionNumber);
        cmbAcquisitionNumber.setSelectedItem(safeValue(model, row, 3));
        String rental = safeValue(model, row, 4);
        String duestr = safeValue(model, row, 5);
        cmbStatus.setSelectedItem(safeValue(model, row, 6));
        SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        try{
            if(rental != null && !rental.isEmpty())
               rentalDate.setDate(sdf.parse(rental));
            if(duestr != null && !duestr.isEmpty())
                dueDate.setDate(sdf.parse(duestr));
        }catch(Exception err){
            JOptionPane.showMessageDialog(null, err);
        }
        
        btnUpdate.setEnabled(true);
        btnDelete.setEnabled(true);
        rentalDate.setEnabled(false);
        makeEnabled();
    }//GEN-LAST:event_tblTransactionsMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
                                          
    if(transactionId == 0){
        JOptionPane.showMessageDialog(this, "Please select a transaction to delete.");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this, 
        "Are you sure you want to delete this transaction?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

    if(confirm != JOptionPane.YES_OPTION) return;

    try {
        Connection con = DB_connect.getConnection();
        String sql = "DELETE FROM transaction WHERE transaction_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, transactionId);

        int deleted = pst.executeUpdate();

        if(deleted > 0){
            JOptionPane.showMessageDialog(this, "Transaction deleted successfully.");
            populateTable(); // refresh table
            setDefault();    // reset form
            transactionId = 0; // reset selected transaction
        } else {
            JOptionPane.showMessageDialog(this, "No transaction deleted.");
        }

    } catch(Exception e){
        JOptionPane.showMessageDialog(this, "Error deleting transaction: " + e.getMessage());
    }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {                                         
    if(transactionId == 0){
        JOptionPane.showMessageDialog(this, "Please select a transaction to update.");
        return;
    }

    try {
        Connection con = DB_connect.getConnection();
        String sql = "UPDATE transaction SET book_id = ?, copy_id = ?, rental_date = ?, due_date = ?, status = ? WHERE transaction_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);

        // get IDs from combo boxes
        String bookTitle = cmbBook.getSelectedItem().toString();
        int bookId = getBookNameById(bookTitle);
           
        String acquisition = cmbAcquisitionNumber.getSelectedItem().toString();
        int copyId = getCopyId(acquisition);
        
        String status = cmbStatus.getSelectedItem().toString();
        java.util.Date rent = rentalDate.getDate();
        java.util.Date due = dueDate.getDate();
        

        pst.setInt(1, bookId);
        pst.setInt(2, copyId);

        pst.setDate(3, new java.sql.Date(rent.getTime()));
        pst.setDate(4, new java.sql.Date(due.getTime()));
        pst.setString(5, status);
        

        pst.setInt(6, transactionId);

        int updated = pst.executeUpdate();

        if(updated > 0){
            JOptionPane.showMessageDialog(this, "Transaction updated successfully.");
            populateTable(); // refresh table
            setDefault();
        } else {
            JOptionPane.showMessageDialog(this, "No transaction updated.");
        }

    } catch(Exception e){
        JOptionPane.showMessageDialog(this, "Error updating transaction: " + e.getMessage());
    }

    }//GEN-LAST:event_btnUpdateActionPerformed

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBorrow;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbAcquisitionNumber;
    private javax.swing.JComboBox<String> cmbBook;
    private javax.swing.JComboBox<String> cmbName;
    private javax.swing.JComboBox<String> cmbStatus;
    private com.toedter.calendar.JDateChooser dueDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private com.toedter.calendar.JDateChooser rentalDate;
    private javax.swing.JTable tblTransactions;
    // End of variables declaration//GEN-END:variables
}
