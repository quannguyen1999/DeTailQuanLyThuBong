package com.spring.services.Impl;

import java.util.concurrent.Future;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.spring.entities.util.AsyncResponse;
import com.spring.services.EmailService;

@Service
public class EmailLmpl implements EmailService{

	@Autowired
	private  JavaMailSender sender;
	/**
	 * The Logger for this class.
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean send(String gmail,int idRandom) {
		logger.info("> send");

		Boolean success = Boolean.FALSE;

		try {
			
			MimeMessage message = sender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(message);

			helper.setSubject("Xác nhận tài khoản từ cửa hàng BearToy");

			helper.setText("Mã:"+idRandom);

			helper.setTo(gmail);

			sender.send(message);


		} catch (Exception e) {
			e.printStackTrace();
			// do nothing
		}

		success = Boolean.TRUE;

		logger.info("< send");
		
		return success;
	}

	@Async
	@Override
	public void sendAsync(String gmail,int idRandom) {
		
		logger.info("> sendAsync");

		try {
			
			send(gmail,idRandom);
			
		} catch (Exception e) {
			
			logger.warn("Exception caught sending asynchronous mail.", e);
			
		}

		logger.info("< sendAsync");
		
	}

	@Async
	@Override
	public Future<Boolean> sendAsyncWithResult(String gmail,int idRandom) {
		
		logger.info("> sendAsyncWithResult");

		AsyncResponse<Boolean> response = new AsyncResponse<Boolean>();

		try {
			
			  Boolean success = send(gmail,idRandom);
			
			response.complete(success);
			
		} catch (Exception e) {
			
			logger.warn("Exception caught sending asynchronous mail.", e);
			
			response.completeExceptionally(e);
			
		}

		logger.info("< sendAsyncWithResult");
		
		return response;
	}
}
