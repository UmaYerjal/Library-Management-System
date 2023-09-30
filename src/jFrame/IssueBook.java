/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jFrame;
import java.util.Date;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author Uma
 */
public class IssueBook extends javax.swing.JFrame {

    /**
     * Creates new form IssueBook
     */
    public IssueBook() {
        initComponents();
    }
    public void getBookDetails(){
        int bookId=Integer.parseInt(txt_bookId.getText());
       try{
       Connection con=DBConnection.getConnection();
//    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root",""); 
       PreparedStatement pst=con.prepareStatement("select * from book_details where book_id=?");
//         PreparedStatement pst=con.prepareStatement("select * from book_details where book_id=?");      
         pst.setInt(1,bookId);
         ResultSet rs=pst.executeQuery();
       if(rs.next()){
           lbl_bookid.setText(rs.getString("book_id"));
           lbl_bookName.setText(rs.getString("book_name"));
           lbl_author.setText(rs.getString("author"));
           lbl_quantity.setText(rs.getString("quantity"));
           
       }
       else{
         lbl_bookError.setText("invalid book id");
               }
       }catch (Exception e){
    e.printStackTrace();
    }
    }
//to fetch the student details from the database and display it to student details panel
    
    public void getStudentDetails(){
        int studentId=Integer.parseInt(txt_studentId.getText());
       try{
       Connection con=DBConnection.getConnection();
//    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root",""); 
       PreparedStatement pst=con.prepareStatement("select * from student_details where student_id=?");
       pst.setInt(1,studentId);
       ResultSet rs=pst.executeQuery();
       if(rs.next()){
           lbl_studentid.setText(rs.getString("student_id"));
           lbl_StudentName.setText(rs.getString("name"));
           lbl_year.setText(rs.getString("year"));
           lbl_branch.setText(rs.getString("branch"));
           
       }
       else{
           lbl_studentError.setText("invalid student id");
       }
       }catch (Exception e){
    e.printStackTrace();
    }
    }
    //insert issue book details to database
    public boolean issueBook(){
    boolean isIssued=false;
    int bookId=Integer.parseInt(txt_bookId.getText());
    int studentId=Integer.parseInt(txt_studentId.getText());
    String bookName=lbl_bookName.getText();
    String studentName=lbl_StudentName.getText();
    
    Date uIssueDate=data_issueDate.getDatoFecha();
    Date uDueDate=data_dueDate.getDatoFecha();
    
    
    Long l1=uIssueDate.getTime();
    long l2=uDueDate.getTime();
    java.sql.Date sIssueDate=new java.sql.Date(l1);
    java.sql.Date sDueDate=new java.sql.Date(l2);

    try{
    Connection con=DBConnection.getConnection();
    String sql="insert into issue_book_details(book_id,book_name,student_id,student_name,"
            + "issue_date,due_date,status)values(?,?,?,?,?,?,?)";
    PreparedStatement pst=con.prepareStatement(sql);
    pst.setInt(1,bookId);
    pst.setString(2,bookName);
    pst.setInt(3,studentId);
    pst.setString(4,studentName);
    pst.setDate(5,sIssueDate);
    pst.setDate(6,sDueDate);
    pst.setString(7,"pending");
    
    int rowCount=pst.executeUpdate();
    if(rowCount>0){
    isIssued=true;
    }
    else{
    isIssued=false;
    }
    }catch(Exception e){
    e.printStackTrace();
    }
    return isIssued;
    
    
    }

    //updating book count
    public void updateBookCount(){
    int bookId=Integer.parseInt(txt_bookId.getText());
    try{
        Connection con=DBConnection.getConnection();
        String sql="update issue_book_details set quantity=quantity-1 where book_id=?";
        PreparedStatement pst=con.prepareStatement(sql);
        pst.setInt(1,bookId);
        
        int rowCount=pst.executeUpdate();
        
        if(rowCount>0){
           JOptionPane.showMessageDialog(this,"book count updated");
           int initialCount=Integer.parseInt(lbl_quantity.getText());
           lbl_quantity.setText(Integer.toString(initialCount-1));
        }else{
            JOptionPane.showMessageDialog(this,"can't update the book count");
        }
    }catch(Exception e){
        e.printStackTrace();
    }
    }
    
    //checking whether book already allocated or not
    public boolean isAlreadyIssued(){
    boolean isAlredyIssued=false;
    int bookId=Integer.parseInt(txt_bookId.getText());
    int studentId=Integer.parseInt(txt_studentId.getText());
    try{
        Connection con=DBConnection.getConnection();
        String sql="select * from issue_book_details where book_id=? and student_id=? and status=?";
        PreparedStatement pst=con.prepareStatement(sql);
        pst.setInt(1,bookId);
        pst.setInt(2,studentId);
        pst.setString(3,"pending");
        
       ResultSet rs= pst.executeQuery();
       if(rs.next()){
           isAlredyIssued=true;
       } else{
          isAlredyIssued=false;
       }
    }catch(Exception e)
    {
    e.printStackTrace();
    }
    return isAlredyIssued;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_main = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lbl_branch = new javax.swing.JLabel();
        lbl_studentid = new javax.swing.JLabel();
        lbl_StudentName = new javax.swing.JLabel();
        lbl_year = new javax.swing.JLabel();
        lbl5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        student_name = new javax.swing.JLabel();
        lbl7 = new javax.swing.JLabel();
        lbl8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_studentError = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lbl_quantity = new javax.swing.JLabel();
        lbl_bookid = new javax.swing.JLabel();
        lbl_bookName = new javax.swing.JLabel();
        lbl_author = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        lbl4 = new javax.swing.JLabel();
        lbl_bookError = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txt_bookId = new app.bolivia.swing.JCTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txt_studentId = new app.bolivia.swing.JCTextField();
        data_issueDate = new rojeru_san.componentes.RSDateChooser();
        jLabel19 = new javax.swing.JLabel();
        data_dueDate = new rojeru_san.componentes.RSDateChooser();
        jLabel20 = new javax.swing.JLabel();
        rSMaterialButtonCircle3 = new rojerusan.RSMaterialButtonCircle();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_main.setBackground(new java.awt.Color(255, 255, 255));
        panel_main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 300, 5));

        lbl_branch.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_branch.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_branch, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 500, 170, 30));

        lbl_studentid.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_studentid.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_studentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 200, 30));

        lbl_StudentName.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_StudentName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_StudentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 360, 210, 30));

        lbl_year.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_year.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 430, 180, 30));

        lbl5.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl5.setForeground(new java.awt.Color(255, 255, 255));
        lbl5.setText("Student id :");
        jPanel1.add(lbl5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 100, -1));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, 200, 30));

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 360, 200, 30));

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, 200, 30));

        student_name.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        student_name.setForeground(new java.awt.Color(255, 255, 255));
        student_name.setText("Student Name :");
        jPanel1.add(student_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 150, -1));

        lbl7.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl7.setForeground(new java.awt.Color(255, 255, 255));
        lbl7.setText("Year :");
        jPanel1.add(lbl7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 100, -1));

        lbl8.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl8.setForeground(new java.awt.Color(255, 255, 255));
        lbl8.setText("Branch :");
        jPanel1.add(lbl8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, 100, -1));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 25)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/AddNewBookIcons/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel2.setText("Student Details");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 290, -1));

        lbl_studentError.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_studentError.setForeground(new java.awt.Color(255, 214, 0));
        jPanel1.add(lbl_studentError, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 620, 340, 40));

        panel_main.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 440, 810));

        jPanel3.setBackground(new java.awt.Color(255, 51, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(102, 102, 255));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(102, 102, 153));
        jLabel15.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel15.setText("Back");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel15)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 25)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel16.setText("  Book Details");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 270, -1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 280, 5));

        lbl_quantity.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_quantity.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 500, 170, 30));

        lbl_bookid.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_bookid.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, 200, 30));

        lbl_bookName.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_bookName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 360, 220, 30));

        lbl_author.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_author.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 430, 180, 30));

        lbl1.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl1.setForeground(new java.awt.Color(255, 255, 255));
        lbl1.setText("Book id :");
        jPanel3.add(lbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 100, -1));

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, 200, 30));

        jLabel23.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 360, 200, 30));

        jLabel24.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, 200, 30));

        lbl2.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl2.setForeground(new java.awt.Color(255, 255, 255));
        lbl2.setText("Book Name :");
        jPanel3.add(lbl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 150, -1));

        lbl3.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl3.setForeground(new java.awt.Color(255, 255, 255));
        lbl3.setText("Author :");
        jPanel3.add(lbl3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 100, -1));

        lbl4.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl4.setForeground(new java.awt.Color(255, 255, 255));
        lbl4.setText("Quantity :");
        jPanel3.add(lbl4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, 100, -1));

        lbl_bookError.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_bookError.setForeground(new java.awt.Color(255, 204, 0));
        jPanel3.add(lbl_bookError, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 630, 360, 30));

        panel_main.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 810));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 25)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel1.setText("   Issue Book");
        panel_main.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 140, 290, -1));

        jPanel7.setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panel_main.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 210, 380, 5));

        jPanel8.setBackground(new java.awt.Color(51, 51, 255));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
        });
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 35)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("X");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel8.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 40, 50));

        panel_main.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1480, 0, 60, 60));

        txt_bookId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        txt_bookId.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txt_bookId.setPlaceholder("Enter Book Id");
        txt_bookId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookIdFocusLost(evt);
            }
        });
        txt_bookId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookIdActionPerformed(evt);
            }
        });
        panel_main.add(txt_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 272, 340, 40));

        jLabel17.setBackground(new java.awt.Color(255, 51, 51));
        jLabel17.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 51, 51));
        jLabel17.setText("Book Id :");
        panel_main.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 282, 100, 30));

        jLabel18.setBackground(new java.awt.Color(255, 51, 51));
        jLabel18.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 51, 51));
        jLabel18.setText("Issue Date : ");
        panel_main.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 460, 130, 30));

        txt_studentId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        txt_studentId.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txt_studentId.setPlaceholder("Enter student Id");
        txt_studentId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentIdFocusLost(evt);
            }
        });
        txt_studentId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_studentIdActionPerformed(evt);
            }
        });
        panel_main.add(txt_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 350, 340, 40));

        data_issueDate.setColorBackground(new java.awt.Color(255, 51, 51));
        data_issueDate.setColorForeground(new java.awt.Color(255, 51, 51));
        data_issueDate.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        data_issueDate.setPlaceholder("Select Issue Date");
        panel_main.add(data_issueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 450, 340, 40));

        jLabel19.setBackground(new java.awt.Color(255, 51, 51));
        jLabel19.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 51, 51));
        jLabel19.setText("Student Id :");
        panel_main.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 360, 130, 30));

        data_dueDate.setColorBackground(new java.awt.Color(255, 51, 51));
        data_dueDate.setColorForeground(new java.awt.Color(255, 51, 51));
        data_dueDate.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        data_dueDate.setPlaceholder("Select Due Date");
        panel_main.add(data_dueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 530, 340, 40));

        jLabel20.setBackground(new java.awt.Color(255, 51, 51));
        jLabel20.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 51, 51));
        jLabel20.setText("Due Date :");
        panel_main.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 540, 130, 30));

        rSMaterialButtonCircle3.setBackground(new java.awt.Color(255, 51, 51));
        rSMaterialButtonCircle3.setText("ISSUE BOOK");
        rSMaterialButtonCircle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle3ActionPerformed(evt);
            }
        });
        panel_main.add(rSMaterialButtonCircle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 640, 340, 60));

        getContentPane().add(panel_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 800));

        setSize(new java.awt.Dimension(1542, 803));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        // TODO add your handling code here:
        HomePage home=new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        HomePage home=new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jPanel8MouseClicked

    private void txt_bookIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookIdFocusLost
            // TODO add your handling code here:
            if(!txt_bookId.getText().equals("")){
                 getBookDetails();
            }
           
    }//GEN-LAST:event_txt_bookIdFocusLost

    private void txt_bookIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookIdActionPerformed

    private void txt_studentIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentIdFocusLost
        // TODO add your handling code here:
        if(!txt_studentId.getText().equals("")){
                 getStudentDetails();
            }
    }//GEN-LAST:event_txt_studentIdFocusLost

    private void txt_studentIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_studentIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentIdActionPerformed

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
     if(lbl_quantity.getText().equals("0")){
         JOptionPane.showMessageDialog(this,"Book is not avilable");
     }
        else{
            if(isAlreadyIssued()==false){
                if(issueBook()==true){
                    JOptionPane.showMessageDialog(this,"book issued Successfully");
     }
                else{
                    JOptionPane.showMessageDialog(this,"can't issue the book");
     }
     }       else{
                    JOptionPane.showMessageDialog(this,"this student already has this book");
     }
        }      
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.componentes.RSDateChooser data_dueDate;
    private rojeru_san.componentes.RSDateChooser data_issueDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbl7;
    private javax.swing.JLabel lbl8;
    private javax.swing.JLabel lbl_StudentName;
    private javax.swing.JLabel lbl_author;
    private javax.swing.JLabel lbl_bookError;
    private javax.swing.JLabel lbl_bookName;
    private javax.swing.JLabel lbl_bookid;
    private javax.swing.JLabel lbl_branch;
    private javax.swing.JLabel lbl_quantity;
    private javax.swing.JLabel lbl_studentError;
    private javax.swing.JLabel lbl_studentid;
    private javax.swing.JLabel lbl_year;
    private javax.swing.JPanel panel_main;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle3;
    private javax.swing.JLabel student_name;
    private app.bolivia.swing.JCTextField txt_bookId;
    private app.bolivia.swing.JCTextField txt_studentId;
    // End of variables declaration//GEN-END:variables
}
