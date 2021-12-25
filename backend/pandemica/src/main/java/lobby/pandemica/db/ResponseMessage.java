package lobby.pandemica.db;
/**
 * Helper class for file uploading system, inclues the message in uploading
 */
public class ResponseMessage {
    private String message;

    public ResponseMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}