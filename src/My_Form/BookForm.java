/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package My_Form;

import My_Classes.DB_connect;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Chrisitian Dedil
 */
public class BookForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(BookForm.class.getName());

     private String check = "";
     private int bookid;
    /**
     * Creates new form BookForm
     */
    public BookForm() {
        initComponents();
        setResizable(true);
        this.setExtendedState(BookForm.MAXIMIZED_BOTH);
        populateTable();
        loadCategories(cmbCategory);
        yearPublished.setYear(java.time.Year.now().getValue());
        yearPublished.setMaximum(java.time.Year.now().getValue());
    }
    private void populateTable(){
        int colCount;
        
        try{
            
            Connection cn = DB_connect.getConnection();
        Statement st = cn.createStatement();
        ResultSet res = st.executeQuery(
    "SELECT b.book_id, b.title, b.author, c.category_name, b.publisher, b.publication_year, b.isbn, b.shelf_location, b.remarks, b.class, b.pages, b.source_of_fund, b.cost_price " +
    "FROM book b LEFT JOIN category c ON b.category_id = c.category_id"
);


        DefaultTableModel tblModel = (DefaultTableModel) tbl.getModel();
        tblModel.setRowCount(0);

        while (res.next()) {
            Vector<Object> row = new Vector<>();

            row.add(res.getInt("book_id"));
            row.add(res.getString("class"));
            row.add(res.getString("title"));
            row.add(res.getString("author"));
            row.add(res.getString("category_name"));
            row.add(res.getInt("pages"));
            row.add(res.getString("source_of_fund"));
            row.add(res.getString("cost_price"));
            row.add(res.getString("publisher"));
            row.add(res.getString("publication_year"));
            row.add(res.getString("remarks"));
            row.add(res.getString("isbn"));
            row.add(res.getString("shelf_location"));
            tblModel.addRow(row);
        }
            
        } catch (SQLException ex) {
            System.getLogger(UserMaintenanceForm.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
    private int getCategoryIdByName(String categoryName) {
    int id = 0;

    try {
        Connection con = DB_connect.getConnection();
        String sql = "SELECT category_id FROM category WHERE category_name = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, categoryName);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            id = rs.getInt("category_id");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return id;
}

    public void loadCategories(JComboBox<String> cmbCategory) {
    try {
        Connection con = DB_connect.getConnection();
        String sql = "SELECT category_name FROM category";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        cmbCategory.removeAllItems();

        while (rs.next()) {
            cmbCategory.addItem(rs.getString("category_name"));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
     private void makeEnabled(){
       
      txtTitle.setEnabled(true);
      cmbCategory.setEnabled(true);
      txtAuthor.setEnabled(true);
      txtPublisher.setEnabled(true);
      yearPublished.setEnabled(true);
      txtClass.setEnabled(true);
      txtIsbn.setEnabled(true);
      txtRemarks.setEnabled(true);
      txtSourceOfFunds.setEnabled(true);
      txtPages.setEnabled(true);
      txtCostPrice.setEnabled(true);
      txtShelfLoc.setEnabled(true);
    }
     private void setDefault(){
         txtTitle.setText("");
         txtAuthor.setText("");
         yearPublished.setYear(java.time.Year.now().getValue());
         txtPublisher.setText("");
          txtClass.setText("");
         txtCostPrice.setText("");
         txtIsbn.setText("");
         txtPages.setText("");
         txtSourceOfFunds.setText("");
         txtShelfLoc.setText("");
      txtTitle.setEnabled(false);
      cmbCategory.setEnabled(false);
      txtAuthor.setEnabled(false);
      txtPublisher.setEnabled(false);
      yearPublished.setEnabled(false);
      txtClass.setEnabled(false);
      txtIsbn.setEnabled(false);
      txtRemarks.setEnabled(false);
      txtSourceOfFunds.setEnabled(false);
      txtPages.setEnabled(false);
      txtCostPrice.setEnabled(false);
      txtShelfLoc.setEnabled(false);
      btnAdd.setEnabled(true);
      btnUpdate.setEnabled(false);
      btnSave.setEnabled(false);
      btnDelete.setEnabled(false);
      btnAddCopies.setEnabled(false);
      btnShow.setEnabled(false);
      btnCancel.setEnabled(false);
      cmbCategory.setSelectedIndex(0);
      populateTable();
     }
     
     private String getStatus( int bookId){
         
        String status = "";
         
         try{
             Connection cn = DB_connect.getConnection();
             String sql = "SELECT status FROM book_copy WHERE book_id = ? AND status = 'borrowed' ";
             PreparedStatement ps = cn.prepareStatement(sql);
             
             ps.setInt(1,bookId);
             
             ResultSet rs = ps.executeQuery();
             
             while(rs.next()){
                 status = rs.getString("status");
             }
             
             
             
         }catch(Exception err){
             
         }
         
         return status;
     }
     
     private String safeValue(DefaultTableModel model, int row, int col){
         Object val = model.getValueAt(row, col);
         return(val == null) ? "" : val.toString();
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
        searchbar = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();
        btnAddCopies = new javax.swing.JButton();
        btnShow = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtTitle = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cmbCategory = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtAuthor = new javax.swing.JTextField();
        txtPublisher = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPages = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtClass = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCostPrice = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtRemarks = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtIsbn = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtSourceOfFunds = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtShelfLoc = new javax.swing.JTextField();
        yearPublished = new com.toedter.calendar.JYearChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(226, 223, 210));

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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnDashboard, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                    .addComponent(btnMembers, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnAuthors, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBooks, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 18, Short.MAX_VALUE))
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

        searchbar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        searchbar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        searchbar.addActionListener(this::searchbarActionPerformed);
        searchbar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchbarKeyReleased(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.setEnabled(false);
        btnCancel.addActionListener(this::btnCancelActionPerformed);

        btnAddCopies.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnAddCopies.setText("Add Copies");
        btnAddCopies.setEnabled(false);
        btnAddCopies.addActionListener(this::btnAddCopiesActionPerformed);

        btnShow.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnShow.setText("Show Copies");
        btnShow.setEnabled(false);
        btnShow.addActionListener(this::btnShowActionPerformed);

        btnAdd.setBackground(new java.awt.Color(51, 255, 0));
        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(0, 51, 51));
        btnAdd.setText("ADD");
        btnAdd.setToolTipText("");
        btnAdd.addActionListener(this::btnAddActionPerformed);

        btnUpdate.setBackground(new java.awt.Color(0, 102, 255));
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.setEnabled(false);
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);

        btnDelete.setBackground(new java.awt.Color(255, 51, 51));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setEnabled(false);
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        btnSave.setBackground(new java.awt.Color(204, 204, 204));
        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnSave.setText("Save");
        btnSave.setEnabled(false);
        btnSave.addActionListener(this::btnSaveActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchbar)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                        .addComponent(btnAddCopies, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnShow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(36, 36, 36))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(searchbar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnShow, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddCopies, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107))
        );

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "BookID", "Class", "Title ", "Author", "Category", "Pages", "Source of Fund", "Cost Price", "Publisher", "Year_Published", "Remarks", "ISBN", "Shelf Location"
            }
        ));
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Book Title:");

        txtTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtTitle.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTitle.setEnabled(false);
        txtTitle.addActionListener(this::txtTitleActionPerformed);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Category:");

        cmbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbCategory.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Author:");

        txtAuthor.setEnabled(false);

        txtPublisher.setEnabled(false);
        txtPublisher.addActionListener(this::txtPublisherActionPerformed);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Publisher:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Year Published:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel6.setText("Pages:");

        txtPages.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel7.setText("Class:");

        txtClass.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel8.setText("Cost Price:");

        txtCostPrice.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel9.setText("Remarks:");

        txtRemarks.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel10.setText("ISBN:");

        txtIsbn.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel11.setText("Source of Funds:");

        txtSourceOfFunds.setEnabled(false);

        jPanel5.setBackground(new java.awt.Color(0, 204, 255));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel12.setText("Book Table");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel12)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel13.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel13.setText("Shelf Location: ");

        txtShelfLoc.setEnabled(false);

        yearPublished.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtTitle)
                                .addGap(44, 44, 44))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(12, 12, 12)
                                            .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel5)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(33, 33, 33)
                                            .addComponent(jLabel4)
                                            .addGap(18, 18, 18)
                                            .addComponent(yearPublished, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(57, 57, 57))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                    .addComponent(jLabel8)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(txtCostPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel9)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(txtRemarks, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                    .addComponent(jLabel3)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(52, 52, 52)
                                                    .addComponent(jLabel6)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(txtPages, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(39, 39, 39)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel7))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtClass, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtSourceOfFunds, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtShelfLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 19, Short.MAX_VALUE))))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(cmbCategory)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(txtPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)
                                .addComponent(txtClass, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPages, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtCostPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txtRemarks, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(txtIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtSourceOfFunds, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(txtShelfLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(yearPublished, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
          try {
    Connection cn = DB_connect.getConnection();
    PreparedStatement pst;

    // --- Validate Year Published ---
   

    if (check.equalsIgnoreCase("Add")) {
        pst = cn.prepareStatement(
            "INSERT INTO book (title, author, category_id, publisher, publication_year, isbn, shelf_location, remarks, class, pages, source_of_fund , cost_price ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
        );

        String categoryName = cmbCategory.getSelectedItem().toString();
    int categoryId = getCategoryIdByName(categoryName);
    String priceText = txtCostPrice.getText().replace(",","").trim();
    
    int year = yearPublished.getYear();
       
    pst.setString(1, txtTitle.getText());
    pst.setString(2, txtAuthor.getText());
    pst.setInt(3, categoryId);
    pst.setString(4, txtPublisher.getText());
    pst.setInt(5, year);
    pst.setString(6, txtIsbn.getText());
    pst.setString(7, txtShelfLoc.getText());
    pst.setString(8, txtRemarks.getText());
    pst.setString(9, txtClass.getText());
    pst.setInt(10, Integer.parseInt(txtPages.getText()));
    pst.setString(11, txtSourceOfFunds.getText());
    pst.setBigDecimal(12, new BigDecimal(priceText));
    pst.executeUpdate();
    JOptionPane.showMessageDialog(null, "Added successfully");

    } else if (check.equalsIgnoreCase("Update")) {
        pst = cn.prepareStatement(
            "UPDATE book SET title = ?, author = ?, category_id = ?, publisher = ?, publication_year = ?, isbn = ?, shelf_location = ?, remarks = ?, class = ?, pages = ?, source_of_fund = ?, cost_price = ? WHERE book_id = ?"
        );

         String categoryName = cmbCategory.getSelectedItem().toString();
    int categoryId = getCategoryIdByName(categoryName);
    int year = yearPublished.getYear();
    pst.setString(1, txtTitle.getText());
    pst.setString(2, txtAuthor.getText());
    pst.setInt(3, categoryId);
    pst.setString(4, txtPublisher.getText());
    pst.setInt(5, year );
    pst.setString(6, txtIsbn.getText());
    pst.setString(7, txtShelfLoc.getText());
    pst.setString(8, txtRemarks.getText());
    pst.setString(9, txtClass.getText());
    pst.setInt(10, Integer.parseInt(txtPages.getText()));
    pst.setString(11, txtSourceOfFunds.getText());
    pst.setBigDecimal(12, new java.math.BigDecimal(txtCostPrice.getText()));
    pst.setInt(13, bookid); // ✅ WHERE book_id


    pst.executeUpdate();
    JOptionPane.showMessageDialog(null, "Updated successfully");
    }

    setDefault();
    populateTable();

} catch (SQLException err) {
    JOptionPane.showMessageDialog(null, err.getMessage());
}

    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        
        String bookStatus = getStatus(bookid);
        if(JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this book? ", "Confirm Delete?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
        
        if(bookStatus.equalsIgnoreCase("borrowed")) {
            JOptionPane.showMessageDialog(null, "This book has been borrowed can't be deleted.");
            return;
        }
            try{
                
                Connection cn = DB_connect.getConnection();
                PreparedStatement pst;
                String query = "DELETE FROM book WHERE book_id = '"+bookid+ "' ";
                pst = cn.prepareStatement(query);
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

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
          // TODO add your handling code here:
        check = "Update";
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        btnSave.setEnabled(true);
        makeEnabled();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnMembersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMembersActionPerformed
        // TODO add your handling code here:
        String borrower = btnMembers.getText();
        if(borrower.equalsIgnoreCase("members")){
            BorrowerForm bf = new BorrowerForm();
            bf.setVisible(true);
            this.dispose();
            bf.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_btnMembersActionPerformed

    private void BtnAuthorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAuthorsActionPerformed
        // TODO add your handling code here:
        CategoryForm bf = new CategoryForm();
        bf.setVisible(true);
        this.dispose();
        bf.setLocationRelativeTo(null);
    }//GEN-LAST:event_BtnAuthorsActionPerformed

    private void btnBooksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBooksActionPerformed
        // TODO add your handling code here:
        BookForm bf = new BookForm();
        bf.setVisible(true);
        this.dispose();
        bf.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnBooksActionPerformed

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        // TODO add your handling code here:
        DashForm bf = new DashForm();
        bf.setVisible(true);
        this.dispose();
        bf.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
           makeEnabled();
        btnAdd.setEnabled(false);
        btnSave.setEnabled(true);
        btnCancel.setEnabled(true);
        check = "Add";
    }//GEN-LAST:event_btnAddActionPerformed

    private void tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)tbl.getModel();
          int row = tbl.getSelectedRow();
          if(row == -1) return;
         bookid = Integer.parseInt(safeValue(model, row, 0));
         txtClass.setText(safeValue(model, row, 1));
         txtTitle.setText(safeValue(model, row, 2));
         txtAuthor.setText(safeValue(model, row, 3));
         cmbCategory.setSelectedItem(safeValue(model, row, 4));
         txtPages.setText(safeValue(model, row, 5));
         txtSourceOfFunds.setText(safeValue(model, row, 6));
         txtCostPrice.setText(safeValue(model, row, 7));
         txtPublisher.setText(safeValue(model, row, 8));
         yearPublished.setYear(Integer.parseInt(safeValue(model, row, 9)));
         txtRemarks.setText(safeValue(model, row, 10));
         txtIsbn.setText(safeValue(model, row, 11));
         txtShelfLoc.setText(safeValue(model, row, 12));
          btnAdd.setEnabled(false);
          btnUpdate.setEnabled(true);
          btnDelete.setEnabled(true);
          btnAddCopies.setEnabled(true);
          btnShow.setEnabled(true);
          btnCancel.setEnabled(true);
          
    }//GEN-LAST:event_tblMouseClicked

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        setDefault();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void searchbarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchbarActionPerformed

    private void searchbarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchbarKeyReleased
        // TODO add your handling code here:
                  String title = searchbar.getText();
       int colCount;
        
        try{
            
           Connection cn = DB_connect.getConnection();
           Statement st = cn.createStatement();
           String query =
            "SELECT b.book_id, b.title, b.author, c.category_name, b.publisher, b.publication_year, b.isbn, b.shelf_location, b.remarks, b.class, b.pages, b.source_of_fund, b.cost_price " +
            "FROM book b LEFT JOIN category c ON b.category_id = c.category_id " +
           "WHERE b.title LIKE '%" + title + "%'";

           ResultSet res = st.executeQuery(query);
           ResultSetMetaData rsData = res.getMetaData();
           colCount = rsData.getColumnCount(); 
           
            DefaultTableModel tblModel = (DefaultTableModel)tbl.getModel();
            tblModel.setRowCount(0);
           while(res.next()){
               
            Vector<Object> row = new Vector<>();

              row.add(res.getInt("book_id"));
            row.add(res.getString("class"));
            row.add(res.getString("title"));
            row.add(res.getString("author"));
            row.add(res.getString("category_name"));
            row.add(res.getInt("pages"));
            row.add(res.getString("source_of_fund"));
            row.add(res.getString("cost_price"));
            row.add(res.getString("publisher"));
            row.add(res.getString("publication_year"));
            row.add(res.getString("remarks"));
            row.add(res.getString("isbn"));
            row.add(res.getString("shelf_location"));
            tblModel.addRow(row);


          
            }
            
        } catch (SQLException ex) {
            System.getLogger(UserMaintenanceForm.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }//GEN-LAST:event_searchbarKeyReleased

    private void btnAddCopiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCopiesActionPerformed
        // TODO add your handling code here:
        AddCopiesForm acf = new AddCopiesForm(bookid);
        acf.setLocationRelativeTo(this);
        acf.setVisible(true);
        
    }//GEN-LAST:event_btnAddCopiesActionPerformed

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
        // TODO add your handling code here:
        ShowCopiesForm scf = new ShowCopiesForm(bookid);
        scf.setLocationRelativeTo(this);
        scf.setVisible(true);
    }//GEN-LAST:event_btnShowActionPerformed

    private void txtTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTitleActionPerformed

    private void txtPublisherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPublisherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPublisherActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new BookForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAuthors;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddCopies;
    private javax.swing.JButton btnBooks;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnMembers;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnShow;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchbar;
    private javax.swing.JTable tbl;
    private javax.swing.JTextField txtAuthor;
    private javax.swing.JTextField txtClass;
    private javax.swing.JTextField txtCostPrice;
    private javax.swing.JTextField txtIsbn;
    private javax.swing.JTextField txtPages;
    private javax.swing.JTextField txtPublisher;
    private javax.swing.JTextField txtRemarks;
    private javax.swing.JTextField txtShelfLoc;
    private javax.swing.JTextField txtSourceOfFunds;
    private javax.swing.JTextField txtTitle;
    private com.toedter.calendar.JYearChooser yearPublished;
    // End of variables declaration//GEN-END:variables
}
