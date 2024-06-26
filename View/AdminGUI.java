package view;

import business.UserManager;
import core.ComboItem;
import core.Helper;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class AdminGUI extends Layout {

    private JPanel wrapper;
    private JTabbedPane tabbedPane1;
    private JLabel lbl_welcome;
    private JButton LOGOUTButton;
    private JTable tbl_user;
    private JTextField tf_username;
    private JTextField tf_pass;
    private JComboBox<ComboItem> cmb_user_role;
    private JButton btn_add;
    private JButton DELETEButton;
    private JComboBox cmb_users;
    private JButton btn_search_user;
    private JScrollPane scrl_user;
    private JButton btn_new_user;
    private JButton btn_clear_user;
    private JPanel w_top;
    private JPanel w_bot;
    private User user;
    private UserManager userManager;
    private DefaultTableModel tmdl_user = new DefaultTableModel();
    private Object[] col_user ;
    private JPopupMenu user_menu;



    public AdminGUI(User user) {
        this.user_menu = new JPopupMenu();
        this.col_user = col_user;
        this.userManager = new UserManager();
        this.add(wrapper);
        this.guiInitilaze(1000, 500);
        this.user = user;
        if (user == null) {
            dispose();

        }


        this.lbl_welcome.setText("Welcome : " + this.user.getUsername());

        loadUserTable(null);
        tableRowSelect(tbl_user);

        LOGOUTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginGUI loginGUI = new LoginGUI();
                dispose();
            }
        });

        btn_add.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFieldListEmpty(new JTextField[]{tf_username, tf_pass})) {
                    Helper.showMsg("fill");
                } else {
                    boolean result;
                    User user1 = new User();
                    if (getUserUpdated() != null){
                        user1 = getUserUpdated();
                    }


                    user1.setUsername(tf_username.getText());
                    user1.setPassword(tf_pass.getText());
                    user1.setRole((String) cmb_user_role.getSelectedItem());

                    if (btn_add.getText().equals("UPDATE")){
                        result = userManager.update(user1);

                    }else{
                        result = userManager.save(user1);
                    }


                    if (result) {
                        Helper.showMsg("done");
                        loadUserTable(null);
                    } else {
                        Helper.showMsg("error");

                    }
                }
            }
        });
        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Helper.confirm("sure")){

                    int selectUserId = getTableSelectedRow(tbl_user,0);
                    if (userManager.delete(selectUserId)){
                        Helper.showMsg("done");
                        loadUserTable(null);
                    }else{
                        Helper.showMsg("error");
                    }
                }
            }
        });
        btn_search_user.addActionListener(e -> {
            String selectedUser= (String) this.cmb_users.getSelectedItem();
            ArrayList<User> userListBySearch=this.userManager.searchForTable(selectedUser);
            ArrayList<Object[]> userRowListBySearch=this.userManager.getForTable(col_user.length,userListBySearch);
            loadUserTable(userRowListBySearch);

        });
        tableRowSelect(tbl_user);

        this.btn_new_user.addActionListener(e -> {
            this.tf_username.setEnabled(true);
            this.tf_pass.setEnabled(true);
            this.cmb_user_role.setEnabled(true);
            this.btn_add.setEnabled(true);
            this.tf_username.setText(null);
            this.tf_pass.setText(null);
            this.cmb_user_role.setSelectedItem("ADMIN");
            this.btn_add.setText("ADD");
            setUserUpdated(null);

        });

//

        this.user_menu.add("Update").addActionListener(e -> {
            int selectUserId = this.getTableSelectedRow(tbl_user,0);
            User userUpdate = this.userManager.getById(selectUserId);
            this.tf_username.setText(userUpdate.getUsername());
            this.tf_pass.setText(userUpdate.getPassword());
            this.cmb_user_role.setSelectedItem(userUpdate.getRole());
            this.btn_add.setText("UPDATE");
            setUserUpdated(userUpdate);
        });
        tbl_user.setComponentPopupMenu(user_menu);


        btn_clear_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               cmb_users.setSelectedItem(null);
               loadUserTable(null);

                //tmdl_user.setColumnCount(0);
            }
        });
    }

    public void loadUserTable(ArrayList<Object[]> userList) {

        this.col_user = new Object[] {"ID", "Username", "Password", "Role"};
        if(userList==null){
            userList=this.userManager.getForTable(this.col_user.length,this.userManager.findAll());
        }
        createTable(this.tmdl_user,this.tbl_user,col_user,userList);

    }
    public void tableRowSelect(JTable table){
        table.addMouseListener(new MouseAdapter() {


            @Override
            public void mouseReleased(MouseEvent e) {
                int selected_row = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(selected_row,selected_row);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
    private User getUserUpdated (){
        return user;

    }
    private void setUserUpdated (User user) {
        this.user = user;
    }

}

