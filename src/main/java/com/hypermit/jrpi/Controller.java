package com.hypermit.jrpi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private TextField tfHost, tfUser;
    @FXML
    private PasswordField pfPassword;



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
