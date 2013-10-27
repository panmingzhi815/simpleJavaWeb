package org.opensource.webapp.framework.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.opensource.webapp.framework.domain.enums.EmailSendStateEnum;

/**
 * Created with IntelliJ IDEA.
 * User: panmingzhi815
 * Date: 13-10-26
 * Time: 下午5:33
 * 系统邮件对象
 */
@Entity
public class SysEmail extends BasicDomain{
	//邮件接收者
	private String toEmail;
	//发送内容
	private String content;
	//标题
	private String title;
	//创建时间
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	//当前邮件的发送状态
	@Enumerated(EnumType.STRING)
	private EmailSendStateEnum emailSendStateEnum;
	
	public String getToEmail() {
		return toEmail;
	}
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public EmailSendStateEnum getEmailSendStateEnum() {
		return emailSendStateEnum;
	}
	public void setEmailSendStateEnum(EmailSendStateEnum emailSendStateEnum) {
		this.emailSendStateEnum = emailSendStateEnum;
	}
	
}
