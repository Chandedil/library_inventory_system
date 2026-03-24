/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package My_Form;

import javax.swing.JOptionPane;
import java.sql.*;
import My_Classes.DB_connect;
import com.mysql.cj.x.protobuf.MysqlxPrepare;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Chrisitian Dedil
 */
public class BorrowerForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(BorrowerForm.class.getName());

    /**
     * Creates new form BorrowerForm
     */
    
    private int borrowerId;
    private String check = "";
    public BorrowerForm() {
        initComponents();
        this.setExtendedState(BorrowerForm.MAXIMIZED_BOTH);
        populateTable();
        dateRegistered.setDate(new java.util.Date());
    }

   private void populateTable(){
       
       try{
           Connection con = DB_connect.getConnection();
           String sql = "SELECT * FROM borrower";
           
           PreparedStatement ps = con.prepareStatement(sql);
          ResultSet res =  ps.executeQuery();
          
           DefaultTableModel model = (DefaultTableModel) tblModel.getModel();
           model.setRowCount(0);
           
           while(res.next()){
               
               java.sql.Date date = res.getDate("date_registered");
               String  dateFormat = "";
               if(date != null){
                   java.text.SimpleDateFormat  format = new java.text.SimpleDateFormat("yyyy-MM-dd");
                  dateFormat = format.format(date);
               }
               model.addRow(new Object[] {
                   res.getInt("borrower_id"),
                   res.getString("full_name"),
                   res.getString("email"),
                   res.getString("phone_number"),
                   res.getString("address"),
                   res.getString("borrower_type"),
                   res.getString("status"),
                   
                   dateFormat
               });
           }
           
       }catch(Exception error){
           JOptionPane.showMessageDialog(null, error);
       }
   }
   
   private boolean borrowerExist(String email){
       boolean checker = false;
      try{
          Connection con = DB_connect.getConnection();
          String query = "Select borrower_id FROM borrower WHERE email = ?";
          
          PreparedStatement ps = con.prepareStatement(query);
          
          ps.setString(1, email);
          
          ResultSet rs = ps.executeQuery();
          if(rs.next()){
              checker = true;
          }
          
          
      }catch(Exception error){
          JOptionPane.showMessageDialog(null, error);
      }
      
      return checker;
   }
   
   private void setDefault(){
      btnAdd.setEnabled(true);
      btnSave.setEnabled(false);
      btnDelete.setEnabled(false);
      btnUpdate.setEnabled(false);
      cmbStatus.setEnabled(false);
      txtAdress.setEnabled(false);
      txtBorrowerType.setEnabled(false);
      txtEmail.setEnabled(false);
      txtFullName.setEnabled(false);
      txtPhoneNumber.setEnabled(false);
      dateRegistered.setEnabled(false);
      btnBorrowBook.setEnabled(false);
      dateRegistered.setDate(new java.util.Date());
      txtAdress.setText("");
      txtEmail.setText("");
      txtFullName.setText("");
      txtPhoneNumber.setText("");
      txtBorrowerType.setText("");
      
      
   }
   
   private  void makeEnabled(){
      btnAdd.setEnabled(false);
      btnSave.setEnabled(true);
      cmbStatus.setEnabled(true);
      txtAdress.setEnabled(true);
      btnBorrowBook.setEnabled(true);
      txtBorrowerType.setEnabled(true);
      txtEmail.setEnabled(true);
      txtFullName.setEnabled(true);
      txtPhoneNumber.setEnabled(true);
      dateRegistered.setEnabled(true);
   }
   
   private String safeValue(DefaultTableModel model, int row, int col){
         Object val = model.getValueAt(row, col);
         return(val == null) ? "" : val.toString();
     }
   
   // borrower status getter:
   public String getStatus(int borrowerId){
        String status = "";
        
        try{
            
            Connection con = DB_connect.getConnection();
            String query = "SELECT status FROM borrower WHERE borrower_id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            
            pst.setInt(1, borrowerId);
            
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                status = rs.getString("status");
            }
            
            
        }catch(Exception err){
            JOptionPane.showMessageDialog(null, err);
        }
        
        return status;
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
        btnDashboard = new javax.swing.JButton();
        btnBooks = new javax.swing.JButton();
        BtnAuthors = new javax.swing.JButton();
        btnMembers = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnBorrowBook = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnCancel1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtFullName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtPhoneNumber = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtAdress = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtBorrowerType = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        dateRegistered = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblModel = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));

        jPanel2.setBackground(new java.awt.Color(70, 130, 180));

        btnDashboard.setBackground(new java.awt.Color(70, 130, 180));
        btnDashboard.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnDashboard.setText("Dashboard");
        btnDashboard.setBorder(null);
        btnDashboard.setBorderPainted(false);
        btnDashboard.setContentAreaFilled(false);
        btnDashboard.setFocusPainted(false);
        btnDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnDashboard.addActionListener(this::btnDashboardActionPerformed);

        btnBooks.setBackground(new java.awt.Color(70, 130, 180));
        btnBooks.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnBooks.setText("Books");
        btnBooks.setBorder(null);
        btnBooks.setBorderPainted(false);
        btnBooks.setContentAreaFilled(false);
        btnBooks.setFocusPainted(false);
        btnBooks.setHideActionText(true);
        btnBooks.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnBooks.addActionListener(this::btnBooksActionPerformed);

        BtnAuthors.setBackground(new java.awt.Color(70, 130, 180));
        BtnAuthors.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        BtnAuthors.setText("Category");
        BtnAuthors.setBorder(null);
        BtnAuthors.setBorderPainted(false);
        BtnAuthors.setContentAreaFilled(false);
        BtnAuthors.setFocusPainted(false);
        BtnAuthors.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        BtnAuthors.addActionListener(this::BtnAuthorsActionPerformed);

        btnMembers.setBackground(new java.awt.Color(70, 130, 180));
        btnMembers.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnMembers.setText("Members");
        btnMembers.setBorder(null);
        btnMembers.setBorderPainted(false);
        btnMembers.setContentAreaFilled(false);
        btnMembers.setFocusPainted(false);
        btnMembers.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnMembers.addActionListener(this::btnMembersActionPerformed);

        jButton1.setBackground(new java.awt.Color(70, 130, 180));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton1.setText("Borrowed");
        jButton1.setAutoscrolls(true);
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
            .addComponent(BtnAuthors, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMembers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnBooks, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(242, 242, 242)
                .addComponent(btnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnAuthors, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBooks, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMembers, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnBorrowBook.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnBorrowBook.setText("Borrow Book");
        btnBorrowBook.setEnabled(false);
        btnBorrowBook.addActionListener(this::btnBorrowBookActionPerformed);

        btnAdd.setBackground(new java.awt.Color(153, 255, 153));
        btnAdd.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(this::btnAddActionPerformed);

        btnUpdate.setBackground(new java.awt.Color(51, 153, 255));
        btnUpdate.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.setEnabled(false);
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);

        btnDelete.setBackground(new java.awt.Color(255, 51, 51));
        btnDelete.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setEnabled(false);
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        btnSave.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnSave.setText("Save");
        btnSave.setEnabled(false);
        btnSave.addActionListener(this::btnSaveActionPerformed);

        btnCancel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnCancel1.setText("Cancel");
        btnCancel1.addActionListener(this::btnCancel1ActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnCancel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBorrowBook, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 285, Short.MAX_VALUE)
                .addComponent(btnBorrowBook, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setText("FullName:");

        txtFullName.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setText("Email:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setText("Phone Number:");

        txtEmail.setEnabled(false);

        txtPhoneNumber.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel5.setText("Adress:");

        txtAdress.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel6.setText("Borrower Type:");

        txtBorrowerType.setEnabled(false);
        txtBorrowerType.addActionListener(this::txtBorrowerTypeActionPerformed);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel7.setText("Status:");

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active ", "Inactive", "Blocked", " " }));
        cmbStatus.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel8.setText("Date Registered:");

        dateRegistered.setDateFormatString("yyyy- MM-dd");
        dateRegistered.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel9.setText("Search:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel9)
                                    .addComponent(jTextField2))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dateRegistered, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtBorrowerType)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(13, 13, 13))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 964, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPhoneNumber))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAdress)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtAdress, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtBorrowerType, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateRegistered, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tblModel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "BorrowerID", "FullName", "Email", "Phone Number", "Adress", "Borrower Type", "Status", "Date Registered"
            }
        ));
        tblModel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblModelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblModel);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        // TODO add your handling code here:
        DashForm bf = new DashForm();
        bf.setVisible(true);
        this.dispose();
        bf.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnBooksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBooksActionPerformed
        // TODO add your handling code here:
        BookForm bf = new BookForm();
        bf.setVisible(true);
        this.dispose();
        bf.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnBooksActionPerformed

    private void BtnAuthorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAuthorsActionPerformed
        // TODO add your handling code here:
        CategoryForm bf = new CategoryForm();
        bf.setVisible(true);
        this.dispose();
        bf.setLocationRelativeTo(null);
    }//GEN-LAST:event_BtnAuthorsActionPerformed

    private void btnMembersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMembersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMembersActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        makeEnabled();
        
        check = "add";
        
       
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtBorrowerTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBorrowerTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBorrowerTypeActionPerformed

    private void btnBorrowBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrowBookActionPerformed
        // TODO add your handling code here:
//            make a borrowbook form and send the borrower_id from borrowerForm 
//            to borrowbook form for the actions on borrowing 
//            book last functionability of the system. 
        String status = getStatus(borrowerId);
        if(status.equalsIgnoreCase("blocked") || status.equalsIgnoreCase("Inactive")){
            JOptionPane.showMessageDialog(null, "This borrower is Blocked / Inactive");
            return;
        }

        BorrowBookForm bbf = new BorrowBookForm(borrowerId);
        bbf.setLocationRelativeTo(this);
        bbf.setVisible(true);
        
    }//GEN-LAST:event_btnBorrowBookActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
         java.util.Date date = dateRegistered.getDate();
         
         
         
         
         
         if(date == null){
             JOptionPane.showMessageDialog(null, "Please fill all fields");
             return;
         }
          
         Date sqlDate = new Date(date.getTime());
         
        String borrowerType = txtBorrowerType.getText();
        
        int limit = 0;
        if(borrowerType.equalsIgnoreCase("Student")){
            limit = 4;   
        }else if(borrowerType.equalsIgnoreCase("Teacher")){
            limit = 5;
        }else{
            limit = 3;
        }
            
       
          java.time.LocalDate picked = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
          java.time.LocalDate today = java.time.LocalDate.now();
          
          
          if(picked.isAfter(today)){
              JOptionPane.showMessageDialog(null, "Please chose a date that is not in the future.");
          }
         
          String email = txtEmail.getText().trim();
                  
          
          
          if(borrowerExist(email) &&  check.equalsIgnoreCase("add")){
              JOptionPane.showMessageDialog(null, "This borrower is already recorded.");
              return;
          }
          
       try{
           Connection con = DB_connect.getConnection();
          
       if(check.equalsIgnoreCase("add")){
          
               
               
               String query = "INSERT INTO borrower(full_name, email, phone_number, address, borrower_type, status, borrow_limit, date_registered) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
               PreparedStatement ps = con.prepareStatement(query);
               
               ps.setString(1, txtFullName.getText().trim());
               ps.setString(2, txtEmail.getText().trim());
               ps.setString(3, txtPhoneNumber.getText().trim());
               ps.setString(4, txtAdress.getText().trim());
               ps.setString(5, txtBorrowerType.getText().trim());
               ps.setString(6, cmbStatus.getSelectedItem().toString());
               ps.setInt(7, limit);
               ps.setDate(8, sqlDate);
               
               
               ps.executeUpdate();
               setDefault();
               populateTable();
               JOptionPane.showMessageDialog(null, "borrower added.");
       }else if(check.equalsIgnoreCase("update")){
           
           // dont forget to put a code in update and create a onlclic  action on jtable latest task for borrower maintenance;
           String query = "UPDATE borrower SET full_name = ?, email = ?, phone_number = ?, address = ?, borrower_type = ?, status = ?, date_registered = ? WHERE borrower_id = ?";
           PreparedStatement ps = con.prepareStatement(query);
           
               ps.setString(1, txtFullName.getText().trim());
               ps.setString(2, txtEmail.getText().trim());
               ps.setString(3, txtPhoneNumber.getText().trim());
               ps.setString(4, txtAdress.getText().trim());
               ps.setString(5, txtBorrowerType.getText().trim());
               ps.setString(6, cmbStatus.getSelectedItem().toString());
               ps.setDate(7, sqlDate);
               ps.setInt(8, borrowerId);
               
               ps.executeUpdate();
               setDefault();
               populateTable();
               JOptionPane.showMessageDialog(null, "Updated");
       }
           
       }catch(Exception error){
               JOptionPane.showMessageDialog(null, error);
           }    
    }//GEN-LAST:event_btnSaveActionPerformed

    private void tblModelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblModelMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblModel.getModel();
        int row = tblModel.getSelectedRow();
        
        if(row == -1) return;
        
        borrowerId  = Integer.parseInt(safeValue(model, row, 0));
        txtFullName.setText(safeValue(model, row, 1));
        txtEmail.setText(safeValue(model, row, 2));
        txtPhoneNumber.setText(safeValue(model, row, 3));
        txtAdress.setText(safeValue(model, row, 4));
        txtBorrowerType.setText(safeValue(model, row, 5));
        cmbStatus.setSelectedItem(safeValue(model, row, 6));
        
        try{
           String datestr = safeValue(model, row, 7);
            
           if(!datestr.isEmpty()){
               java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
               java.util.Date date = sdf.parse(datestr);
               dateRegistered.setDate(date);
           }
        }catch(Exception err ){
            JOptionPane.showMessageDialog(null, err);
        }
        
        makeEnabled();
        btnDelete.setEnabled(true);
        btnUpdate.setEnabled(true);
    }//GEN-LAST:event_tblModelMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
         if(JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this book? ", "Confirm Delete?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            
            try{
                
                Connection cn = DB_connect.getConnection();
                PreparedStatement pst;
                String query = "DELETE FROM borrower WHERE borrower_id = ? ";
                
                pst = cn.prepareStatement(query);
                
                pst.setInt(1, borrowerId);
                
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "book sucessfully deleted");
                setDefault();
                populateTable();
                
            }catch(SQLException err){
                JOptionPane.showMessageDialog(null, err);
        }
        }else{
            setDefault();
            populateTable();
        }
        
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel1ActionPerformed
        // TODO add your handling code here:
        setDefault();
    }//GEN-LAST:event_btnCancel1ActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        check =  "update"; 
    }//GEN-LAST:event_btnUpdateActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new BorrowerForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAuthors;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBooks;
    private javax.swing.JButton btnBorrowBook;
    private javax.swing.JButton btnCancel1;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnMembers;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbStatus;
    private com.toedter.calendar.JDateChooser dateRegistered;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTable tblModel;
    private javax.swing.JTextField txtAdress;
    private javax.swing.JTextField txtBorrowerType;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JTextField txtPhoneNumber;
    // End of variables declaration//GEN-END:variables
}
