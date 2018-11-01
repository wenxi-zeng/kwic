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

    private final static long serialVersionUID = 665150296946841891L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Sentence() {
    }

    /**
     *
     * @param original
     * @param url
     */
    public Sentence(String original, String url) {
        super();
        this.original = original;
        this.url = url;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("original", original).append("url", url).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(original).append(url).toHashCode();
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
        return new EqualsBuilder().append(original, rhs.original).append(url, rhs.url).isEquals();
    }

}