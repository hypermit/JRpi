package com.hypermit.jrpi;

import com.jcraft.jsch.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: hypermit
 * Date: 2016
 * hypermit@gmail.com
 */
public class SshConnect {
    Session session;
    protected boolean connect(String host, String user, String password){
        try{
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            session=jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            //System.out.println("Connected");
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    protected String send(String command) throws JSchException, IOException {
        String commandResult = null;
        Channel channel=session.openChannel("exec");
        ((ChannelExec)channel).setCommand(command);
        channel.setInputStream(null);
        ((ChannelExec)channel).setErrStream(System.err);

        InputStream in=channel.getInputStream();
        channel.connect();
        byte[] tmp=new byte[1024];
        while(true){
            while(in.available()>0){
                int i=in.read(tmp, 0, 1024);
                if(i<0)break;
                //System.out.print(new String(tmp, 0, i));
                commandResult = new String(tmp, 0, i);
            }
            if(channel.isClosed()){
                //System.out.println("exit-status: "+channel.getExitStatus());
                break;
            }
            try{Thread.sleep(1000);}catch(Exception ee){}
        }
        //System.out.println("DONE");
        return commandResult;
    }

    protected boolean disconnect(){
        session.disconnect();
        return true;
    }
}
