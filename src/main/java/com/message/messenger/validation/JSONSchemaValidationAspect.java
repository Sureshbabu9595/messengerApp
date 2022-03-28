package com.message.messenger.validation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.LogLevel;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;
import com.message.messenger.request.RESTRequest;
import com.message.messenger.response.RESTResponse;
import com.message.messenger.validation.exception.JSONSchemaValidationException;
@Aspect
@Component
public class JSONSchemaValidationAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(JSONSchemaValidationAspect.class);

	private Map<String, String> requestSchemaMap;

	private Map<String, String> responseSchemaMap;

	private ResourceLoader resourceLoader;

	@Value("${messenger.isSchemaValidationEnabled}")
	private boolean isSchemaValidationEnabled;

	@Value("${messenger.isRequestValidationEnabled}")
	private boolean isRequestValidationEnabled;

	@Value("${messenger.isResponseValidationEnabled}")
	private boolean isResponseValidationEnabled;

	@Autowired
	private DeserializationProblemHandler handler;

	@Autowired
	public JSONSchemaValidationAspect(Map<String, String> requestSchemaMap, Map<String, String> responseSchemaMap,
			ResourceLoader resourceLoader) {
		super();
		this.requestSchemaMap = requestSchemaMap;
		this.responseSchemaMap = responseSchemaMap;
		this.resourceLoader = resourceLoader;
	}

	@Around(value = "@annotation(enableJSONSchemaValidation)")
	public Object validateJSONResponse(ProceedingJoinPoint pjp, EnableJSONSchemaValidation enableJSONSchemaValidation)
			throws Throwable {
		RESTResponse responseObject = null;
		Object[] args = pjp.getArgs();
		if (isSchemaValidationEnabled) {
			HttpServletRequest httpServletRequest = null;
			RESTRequest requestObject = null;

			for (Object object : args) {
				if (object instanceof HttpServletRequest) {
					httpServletRequest = (HttpServletRequest) object;
				} else if (object instanceof RESTRequest) {
					requestObject = (RESTRequest) object;
				}
			}

			if (isRequestValidationEnabled && null != httpServletRequest && null != requestObject) {
				String jsonRequest = (new ObjectMapper().addHandler(handler)
						.setSerializationInclusion(Include.NON_NULL)).writeValueAsString(requestObject);
				validateJson(httpServletRequest, jsonRequest, this.requestSchemaMap);
			}

			responseObject = (RESTResponse) pjp.proceed(args);
			if (isResponseValidationEnabled && null != httpServletRequest && null != responseObject) {
				String jsonResponse = (new ObjectMapper().addHandler(handler)
						.setSerializationInclusion(Include.NON_NULL)).writeValueAsString(responseObject);
				validateJson(httpServletRequest, jsonResponse, this.responseSchemaMap);
			}

		} else {
			responseObject = (RESTResponse) pjp.proceed(args);
		}
		return responseObject;
	}

	private void validateJson(HttpServletRequest httpServletRequest, String jsonString, Map<String, String> schemaMap)
			throws JSONSchemaValidationException, IOException {
		String requestURI = httpServletRequest.getRequestURI();
		String requestSchemaPath = schemaMap.get(requestURI);
		if (null != jsonString && !jsonString.isEmpty() && null != requestSchemaPath) {
			Resource jsonSchemaStream = this.resourceLoader.getResource(requestSchemaPath);
			if (jsonSchemaStream != null) {
				validateJsonData(jsonSchemaStream.getInputStream(), jsonString, requestURI);
			}

		}

	}

	private void validateJsonData(InputStream jsonSchemaInputStream, String jsonString, String requestURI)
			throws JSONSchemaValidationException, IOException {
		try {
			String jsonSchema = getStringFromInputStream(jsonSchemaInputStream);
			final JsonNode s = JsonLoader.fromString(jsonSchema);
			final JsonNode r = JsonLoader.fromString(jsonString);
			JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.byDefault();
			JsonValidator validator = jsonSchemaFactory.getValidator();
			ProcessingReport processingReport = validator.validate(s, r);
			if (!processingReport.toString().contains("success")) {
				if (LOGGER.isErrorEnabled()) {
					LOGGER.error(
							String.format("JSON Schema Validation FAILED for URI:[%s]. The Processing Report: [%s]",
									requestURI, processingReport));
				}
				throw new JSONSchemaValidationException(processErrorMessage(processingReport));
			}
		} catch (ProcessingException e) {
			throw new JSONSchemaValidationException(e.getMessage());
		}
	}

	private String processErrorMessage(ProcessingReport processingReport) {
		String returnValue = null;
		Iterator<ProcessingMessage> itr = processingReport.iterator();
		while(itr.hasNext()) {
			ProcessingMessage message = itr.next();
			if(message.getLogLevel().equals(LogLevel.ERROR)) {
				returnValue = message.toString();
			}
		}
		return returnValue;
	}

	private String getStringFromInputStream(InputStream jsonSchemaInputStream) {
		BufferedReader br = null;
		StringBuilder sbuf = new StringBuilder();
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(jsonSchemaInputStream));
			while ((line = br.readLine()) != null) {
				sbuf.append(line);
			}
		} catch (IOException exception) {
			if (LOGGER.isErrorEnabled()) {
				LOGGER.error(String.format("Error reading the Input Stream: %s", exception));
			}
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException exception) {
					LOGGER.error(String.format("Error Closing Reader: %s", exception));
				}
			}
		}
		return sbuf.toString();
	}
}