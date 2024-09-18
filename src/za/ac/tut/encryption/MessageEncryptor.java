
package za.ac.tut.encryption;

import za.ac.tut.message.Message;


public class MessageEncryptor {
    public String encrypt(String plainMessage){
        
        Message message = new Message(plainMessage);
        
        for (int i = 0; i < message.getMessage().length(); i++) {
            char ch = message.getMessage().charAt(i); 

            if (ch >= 'A' && ch <= 'Z') {
                ch = (char) (ch + 5);
                if (ch > 'Z') {
                    ch = (char) (ch - 26);
                }
            }
            
            else if (ch >= 'a' && ch <= 'z') {
                ch = (char) (ch + 5);   
                if (ch > 'z') {
                    ch = (char) (ch - 26);
                }
            }
            else {
                message.appendEncryptionString(ch);
                continue;  
            }
            message.appendEncryptionString(ch);
        }
        return message.getEncryptedmessage();  
    }

}

