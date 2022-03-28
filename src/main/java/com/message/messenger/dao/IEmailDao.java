package com.message.messenger.dao;

import java.util.List;

import com.message.messenger.dto.EmailDTO;
import com.message.messenger.vo.MessageVO;

public interface IEmailDao {
	
	public boolean sendEmail(EmailDTO emailDTO);
	
	List<MessageVO> getMessages();

}
