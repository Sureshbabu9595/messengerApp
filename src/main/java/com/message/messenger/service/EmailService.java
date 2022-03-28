package com.message.messenger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.message.messenger.dao.IEmailDao;
import com.message.messenger.dto.EmailDTO;
import com.message.messenger.vo.MessageVO;

@Service
public class EmailService implements IEmailService {
	
	@Autowired
	private IEmailDao emailDao;

	@Override
	public boolean sendEmail(EmailDTO emailDTO) {
		return emailDao.sendEmail(emailDTO);

	}

	@Override
	public List<MessageVO> getMessages() {
		return emailDao.getMessages();
	}
}
