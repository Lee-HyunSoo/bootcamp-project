package com.travel.vo;

public class MsgVO {
	private int msgIDX;
	private String msgName;
	private String msgSender;
	private String msgReceiver;
	private String msgTitle;
	private String msgContent;
	private String msgAskDate;
	private String msgAnswerDate;
	private boolean msgUserIsRead;
	private boolean msgAdminIsRead;
	private boolean msgAdminAnswer;
	
	public MsgVO() {
		// TODO Auto-generated constructor stub
	}



	public MsgVO(int msgIDX, String msgName, String msgSender, String msgReceiver, String msgTitle, String msgContent,
			String msgAskDate, String msgAnswerDate, boolean msgUserIsRead, boolean msgAdminIsRead,
			boolean msgAdminAnswer) {
		super();
		this.msgIDX = msgIDX;
		this.msgName = msgName;
		this.msgSender = msgSender;
		this.msgReceiver = msgReceiver;
		this.msgTitle = msgTitle;
		this.msgContent = msgContent;
		this.msgAskDate = msgAskDate;
		this.msgAnswerDate = msgAnswerDate;
		this.msgUserIsRead = msgUserIsRead;
		this.msgAdminIsRead = msgAdminIsRead;
		this.msgAdminAnswer = msgAdminAnswer;
	}



	public int getMsgIDX() {
		return msgIDX;
	}



	public String getMsgName() {
		return msgName;
	}



	public String getMsgSender() {
		return msgSender;
	}



	public String getMsgReceiver() {
		return msgReceiver;
	}



	public String getMsgTitle() {
		return msgTitle;
	}



	public String getMsgContent() {
		return msgContent;
	}



	public String getMsgAskDate() {
		return msgAskDate;
	}



	public String getMsgAnswerDate() {
		return msgAnswerDate;
	}



	public boolean isMsgUserIsRead() {
		return msgUserIsRead;
	}



	public boolean isMsgAdminIsRead() {
		return msgAdminIsRead;
	}



	public boolean isMsgAdminAnswer() {
		return msgAdminAnswer;
	}



	@Override
	public String toString() {
		return "MsgDAO [msgIDX=" + msgIDX + ", msgName=" + msgName + ", msgSender=" + msgSender + ", msgReceiver="
				+ msgReceiver + ", msgTitle=" + msgTitle + ", msgContent=" + msgContent + ", msgAskDate=" + msgAskDate
				+ ", msgAnswerDate=" + msgAnswerDate + ", msgUserIsRead=" + msgUserIsRead + ", msgAdminIsRead="
				+ msgAdminIsRead + ", msgAdminAnswer=" + msgAdminAnswer + "]";
	}


}
