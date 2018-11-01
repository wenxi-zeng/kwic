package edu.utdallas.wxz180008.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Sentence implements Serializable
{

    @SerializedName("original")
    @Expose
    private String original;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    private final static long serialVersionUID = -4828783025774109962L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Sentence() {
    }

    /**
     *
     * @param title
     * @param description
     * @param original
     * @param url
     */
    public Sentence(String original, String url, String title, String description) {
        super();
        this.original = original;
        this.url = url;
        this.title = title;
        this.description = description;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public Sentence withOriginal(String original) {
        this.original = original;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Sentence withUrl(String url) {
        this.url = url;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Sentence withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Sentence withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("original", original).append("url", url).append("title", title).append("description", description).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(title).append(description).append(original).append(url).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Sentence) == false) {
            return false;
        }
        Sentence rhs = ((Sentence) other);
        return new EqualsBuilder().append(title, rhs.title).append(description, rhs.description).append(original, rhs.original).append(url, rhs.url).isEquals();
    }

}