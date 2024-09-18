package za.ac.tut.message;
public class Message {
    public String message = "";
    public String encryptedmessage = "";

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getEncryptedmessage() {
        return encryptedmessage;
    }
        
    public void setMessage(String message) {
        this.message = message;
    }
    
    public void appendEncryptionString(char character){
        
        this.encryptedmessage = this.encryptedmessage += character;
    }
}
