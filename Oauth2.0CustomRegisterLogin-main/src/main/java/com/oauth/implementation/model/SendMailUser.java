package com.oauth.implementation.model;

public class SendMailUser {
	
	private long id;
    private String recipient;
    private String ccList;
    private String bccList;
    private String msgBody;
    private String subject;
    private String attachment;
    
    private String emailStatus;
    
    public String getEmailStatus() {
		return emailStatus;
	}
	public void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCcList() {
		return ccList;
	}
	public void setCcList(String ccList) {
		this.ccList = ccList;
	}
	public String getBccList() {
		return bccList;
	}
	public void setBccList(String bccList) {
		this.bccList = bccList;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getMsgBody() {
		return msgBody;
	}
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	  @Override
		public String toString() {
			return "EmailDetails [id=" + id + ", recipient=" + recipient + ", ccList=" + ccList + ", bccList=" + bccList
					+ ", msgBody=" + msgBody + ", subject=" + subject + ", attachment=" + attachment + ", emailStatus="
					+ emailStatus + ", getEmailStatus()=" + getEmailStatus() + ", getId()=" + getId() + ", getCcList()="
					+ getCcList() + ", getBccList()=" + getBccList() + ", getRecipient()=" + getRecipient()
					+ ", getMsgBody()=" + getMsgBody() + ", getSubject()=" + getSubject() + ", getAttachment()="
					+ getAttachment() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
					+ super.toString() + "]";
		}

}



