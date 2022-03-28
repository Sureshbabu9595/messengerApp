package com.message.messenger.service;

import java.util.List;

import com.message.messenger.dto.EmailDTO;
import com.message.messenger.vo.MessageVO;

public interface IEmailService {

	boolean sendEmail(EmailDTO emailDTO);

	List<MessageVO> getMessages();

}
