package com.hypermit.jrpi;

import com.jcraft.jsch.JSchException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
/**
 * @author: hypermit
 * Date: 2016
 * hypermit@gmail.com
 */
public class Controller {
    SshConnect sshConnect = new SshConnect();
    private byte gpioPinNumber;
    private String gpioMode;
    private String gpioReadCommand;
    private String gpioStatus;
    private String gpioSwitchCommand;
    private byte gpioBitStatus;
    private static final String BTN_STYLE_ON_AND_OFF[] = {"-fx-body-color: aliceblue",
            "-fx-body-color: chartreuse"};
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
    private Button btn0;

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

    public void btnGpioAction(ActionEvent actionEvent) throws IOException, JSchException {
        Object btnClicked = actionEvent.getSource();
        if (btnClicked == btn2 || btnClicked == btn4) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Gpio Information");
            alert.setHeaderText(null);
            alert.setContentText("This gpio pin is 5v power.");
            alert.showAndWait();
        } else if (btnClicked == btn1 || btnClicked == btn17) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Gpio Information");
            alert.setHeaderText(null);
            alert.setContentText("This gpio pin is 3.3v power.");
            alert.showAndWait();
        } else if (btnClicked == btn6 || btnClicked == btn9 || btnClicked == btn14 ||
                btnClicked == btn20 || btnClicked == btn25 || btnClicked == btn30 ||
                btnClicked == btn34 || btnClicked == btn39) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Gpio Information");
            alert.setHeaderText(null);
            alert.setContentText("This gpio pin is GND.");
            alert.showAndWait();
        } else {
            Button btClick = (Button) actionEvent.getSource();
            String strButtonId = btClick.getId();
            strButtonId = strButtonId.substring(3);
            byte pinNumber = Byte.parseByte(strButtonId);
            switchGpio(pinNumber);
        }
    }

    private void switchGpio(byte btnNumber) throws IOException, JSchException {
        Button btnArray[] = {btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10,
                btn11, btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19, btn20,
                btn21, btn22, btn23, btn24, btn25, btn26, btn27, btn28, btn29, btn30,
                btn31, btn32, btn33, btn34, btn35, btn36, btn37, btn38, btn39, btn40};
        gpioPinNumber = gpio2WiringPin(btnNumber);
        gpioMode = "gpio mode " + gpioPinNumber + " out";
        sshConnect.send(gpioMode);
        gpioReadCommand = "gpio read " + gpioPinNumber;
        gpioStatus = sshConnect.send(gpioReadCommand);
        // get first char from gpio read to remove "\n" and convert it to byte
        gpioBitStatus = Byte.parseByte(gpioStatus.substring(0, 1));
        gpioBitStatus = (byte) (1 - gpioBitStatus);
        gpioSwitchCommand = "gpio write " + gpioPinNumber + " " + gpioBitStatus;
        sshConnect.send(gpioSwitchCommand);
        btnArray[btnNumber].setStyle(BTN_STYLE_ON_AND_OFF[gpioBitStatus]);
    }

    // convert gpio number to wiring pi number
    private byte gpio2WiringPin(byte btPinNumber) {
        byte wiringPiPins[] = {-1, -1, -1, 8, -1, 9, -1, 7, 15, -1, 16, 0, 1, 2, -1, 3, 4, -1, 5, 12,
                -1, 13, 6, 14, 10, -1, 11, 30, 31, 21, -1, 22, 26, 23, -1, 24, 27, 25, 28, -1, 29};
        return wiringPiPins[btPinNumber];
    }
}