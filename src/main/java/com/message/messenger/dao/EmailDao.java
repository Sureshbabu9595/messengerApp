package com.message.messenger.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.message.messenger.dto.EmailDTO;
import com.message.messenger.vo.MessageVO;

public class EmailDao implements IEmailDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailDao.class);

	private static Map<String, String> messagesMap = new HashMap<>();

	@Override
	public boolean sendEmail(EmailDTO emailDTO) {
		messagesMap.put(emailDTO.getEmailAddress(), emailDTO.getEmailContent());
		return true;

	}

	@Override
	public List<MessageVO> getMessages() {
		LOGGER.info("Enteriing... getMessages");
		List<MessageVO> returnValue = new ArrayList<>();
		Set<String> emailAddresses = messagesMap.keySet();
		emailAddresses.stream().filter(Objects::nonNull)
				.forEach(s -> returnValue.add(new MessageVO(s, messagesMap.get(s))));
		return returnValue;
	}
}