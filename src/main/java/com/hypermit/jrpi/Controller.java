package com.hypermit.jrpi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * @author: hypermit
 * Date: 2016
 * hypermit@gmail.com
 */
public class Controller {
    SshConnect sshConnect = new SshConnect();
    @FXML
    private Label lblConnectionStatus;
    @FXML
    private TextField tfHost;
    @FXML
    private TextField tfUser;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Button btnConnect;
    @FXML
    private Button btnDisconnect;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn7;
    @FXML
    private Button btn9;
    @FXML
    private Button btn11;
    @FXML
    private Button btn13;
    @FXML
    private Button btn15;
    @FXML
    private Button btn17;
    @FXML
    private Button btn19;
    @FXML
    private Button btn21;
    @FXML
    private Button btn23;
    @FXML
    private Button btn25;
    @FXML
    private Button btn27;
    @FXML
    private Button btn29;
    @FXML
    private Button btn31;
    @FXML
    private Button btn33;
    @FXML
    private Button btn35;
    @FXML
    private Button btn37;
    @FXML
    private Button btn39;
    @FXML
    private Button btn6;
    @FXML
    private Button btn8;
    @FXML
    private Button btn10;
    @FXML
    private Button btn12;
    @FXML
    private Button btn14;
    @FXML
    private Button btn16;
    @FXML
    private Button btn18;
    @FXML
    private Button btn20;
    @FXML
    private Button btn22;
    @FXML
    private Button btn24;
    @FXML
    private Button btn26;
    @FXML
    private Button btn28;
    @FXML
    private Button btn30;
    @FXML
    private Button btn32;
    @FXML
    private Button btn34;
    @FXML
    private Button btn36;
    @FXML
    private Button btn38;
    @FXML
    private Button btn40;

    public void btnConnectAction(ActionEvent actionEvent) {
        String host = tfHost.getText();
        String user = tfUser.getText();
        String password = pfPassword.getText();
        if (tfHost.getText() == null || tfHost.getText().equals("")) {
            host = "192.168.1.100";
        }
        if (tfUser == null || tfUser.getText().equals("")) {
            user = "pi";
        }
        if (pfPassword.getText() == null || pfPassword.getText().equals("")) {
            password = "raspberry";
        }
        boolean bConnect = sshConnect.connect(host, user, password);
        if (bConnect) {
            lblConnectionStatus.setText("Connecting...");
        } else {
            lblConnectionStatus.setText("No connection");
        }
    }

    public void btnDisconnectAction(ActionEvent actionEvent) {
        boolean bDisconnect = sshConnect.disconnect();
        if (bDisconnect) {
            lblConnectionStatus.setText("No connection");
        }
    }
}
