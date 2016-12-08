package com.hypermit.jrpi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * @author: hypermit
 * Date: 2016
 * hypermit@gmail.com
 */
public class Controller {
    SshConnect sshConnect = new SshConnect();

    @FXML
    Button btnConnect;

    @FXML
    Label lblConnectionStatus;


    public void btnConnectAction(ActionEvent actionEvent) {
        boolean bConnect = sshConnect.connect("192.168.1.100", "pi", "raspberry");

        if (bConnect) {
            lblConnectionStatus.setText("Connecting...");
        } else {
            lblConnectionStatus.setText("No connection");
        }
    }
}
