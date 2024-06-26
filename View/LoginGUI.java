package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;

public class LoginGUI extends Layout{
    private JPanel wrapper;
    private JTextField tf_user;
    private JPasswordField tf_pass;
    private JButton btn_login;
    private JLabel lbl_user;
    private JLabel lbl_pass;
    private JPanel w_top;
    private JPanel w_bot;
    private UserManager userManager;

    public LoginGUI(){
        this.add(wrapper);
        this.guiInitilaze(300,300);
        this.userManager = new UserManager();


        // Çıkış buton dispose(); döndürüyor.
        btn_login.addActionListener(e -> {
            JTextField[] checkFieldList = {this.tf_user,this.tf_pass};
            if (Helper.isFieldListEmpty(checkFieldList)){
                Helper.showMsg("fill");

            }else {
                User loginUser = this.userManager.findByLoging(this.tf_user.getText(),this.tf_pass.getText());
                if (loginUser == null){
                    Helper.showMsg("notFound");
                }else {

                    if (loginUser.getRole().equals("admin")){
                        AdminGUI adminGUI = new AdminGUI(loginUser);
                        dispose();
                    }else {
                        EmployeeGUI employeeGUI = new EmployeeGUI(loginUser);
                        dispose();
                    }
                }
            }
        });
    }
}