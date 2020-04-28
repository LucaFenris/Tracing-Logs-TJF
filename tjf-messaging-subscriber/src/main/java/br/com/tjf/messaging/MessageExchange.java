package br.com.tjf.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MessageExchange {

	String INPUT = "messaging-subscriber-input";

	@Input(MessageExchange.INPUT)
	SubscribableChannel input();

	String OUTPUT = "messaging-subscriber-output";

	@Output(MessageExchange.OUTPUT)
	MessageChannel output();

}
