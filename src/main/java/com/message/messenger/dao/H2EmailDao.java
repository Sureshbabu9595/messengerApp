package com.message.messenger.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.message.messenger.dto.EmailDTO;
import com.message.messenger.vo.MessageVO;

public class H2EmailDao implements IEmailDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(H2EmailDao.class);

	@Value("${insertMessage.query}")
	private String insertMessageQuery;

	@Value("${fetchMessages.query}")
	private String fetchMessagesQuery;

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public H2EmailDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		super();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public boolean sendEmail(EmailDTO emailDTO) {
		LOGGER.info("Entering... sendEmail");
		int count = namedParameterJdbcTemplate.update(insertMessageQuery, new BeanPropertySqlParameterSource(emailDTO));
		LOGGER.info("Saved message::{}", count);
		return count > 0;
	}

	@Override
	public List<MessageVO> getMessages() {
		LOGGER.info("Entering... getMessages");
		List<MessageVO> returnValue = namedParameterJdbcTemplate.query(fetchMessagesQuery, rs -> {
			List<MessageVO> messageVOs = new ArrayList<>();
			try {
				while (rs.next()) {
					MessageVO messageVO = new MessageVO();
					messageVO.setEmailAddress(rs.getString("EMAIL_ADDRESS"));
					messageVO.setEmailContent(rs.getString("MESSAGE_BODY"));
					messageVOs.add(messageVO);
				}
			} catch (SQLException e) {
				if (LOGGER.isErrorEnabled()) {
					LOGGER.error("");
				}
			}
			return messageVOs;
		});
		return returnValue;
	}
}