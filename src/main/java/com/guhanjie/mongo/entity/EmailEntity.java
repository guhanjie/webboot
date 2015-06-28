package com.guhanjie.mongo.entity;

import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

/**
 * @Document注解表示该对象存储到MongoDB的对应Collection中去
 * @author Guhanjie
 */
@Document(collection = "email")
@TypeAlias("email")
public class EmailEntity{
    private boolean html = true;
    private String from;
    private String replyTo;
    @Indexed
    private String[] to;
    private String[] cc;
    private String[] bcc;
    private String subject;
    @Transient
    private String content;

    public boolean isHtml() {
        return html;
    }

    public void setHtml(boolean html) {
        this.html = html;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String[] getBcc() {
        return bcc;
    }

    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "EmailEntity{" +
                "html=" + html +
                ", from='" + from + '\'' +
                ", replyTo='" + replyTo + '\'' +
                ", to=" + Arrays.toString(to) +
                ", cc=" +  Arrays.toString(cc) +
                ", bcc=" +  Arrays.toString(bcc) +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                "} " + super.toString();
    }
}
