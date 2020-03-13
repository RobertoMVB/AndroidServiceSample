package br.com.vw.myvwautomotive.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Roberto Mandolesi Vilas Boas - rvilas@br.ibm.com
 */
public class MessageContentModel implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("group")
    @Expose
    private String group;
    @SerializedName("content")
    @Expose
    private ContentModel content;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    private final static long serialVersionUID = -3984797817910468742L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public ContentModel getContent() {
        return content;
    }

    public void setContent(ContentModel content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
