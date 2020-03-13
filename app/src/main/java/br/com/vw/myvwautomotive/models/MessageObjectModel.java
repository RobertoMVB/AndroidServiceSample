package br.com.vw.myvwautomotive.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Roberto Mandolesi Vilas Boas - rvilas@br.ibm.com
 */
public class MessageObjectModel implements Serializable {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("message")
    @Expose
    private MessageContentModel message;
    private final static long serialVersionUID = -5778742734316145268L;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MessageContentModel getMessage() {
        return message;
    }

    public void setMessage(MessageContentModel message) {
        this.message = message;
    }

}
