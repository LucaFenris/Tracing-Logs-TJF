package br.com.tjf.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.totvs.tjf.core.message.TOTVSMessage;

import br.com.tjf.events.MessageEvent;
import br.com.tjf.repository.ContentRepository;

@EnableBinding(MessageExchange.class)
public class MessageSubscriber {

	@Autowired
	ContentRepository contentRepository;

	@StreamListener(target = MessageExchange.INPUT, condition = MessageEvent.CONDITIONAL_EXPRESSION)
	public void subscriberMessage(TOTVSMessage<MessageEvent> message) {
		contentRepository.save(message.getContent().getContentModel());
	}

}
