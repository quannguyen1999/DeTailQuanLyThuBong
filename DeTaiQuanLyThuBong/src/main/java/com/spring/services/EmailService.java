package com.spring.services;

import java.util.concurrent.Future;


public interface EmailService {

	boolean send(String gmail,int idRandom);

	void sendAsync(String gmail,int idRandom);

    Future<Boolean> sendAsyncWithResult(String gmail,int idRandom);

}
