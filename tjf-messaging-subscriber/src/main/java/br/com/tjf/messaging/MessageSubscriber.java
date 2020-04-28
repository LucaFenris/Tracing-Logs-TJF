package br.com.tjf.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.google.gson.Gson;
import com.totvs.tjf.core.message.TOTVSMessage;

import br.com.tjf.events.MessageEvent;
import br.com.tjf.model.ContentModel;
import br.com.tjf.repository.ContentRepository;
import io.opentracing.Span;
import io.opentracing.Tracer;

@EnableBinding(MessageExchange.class)
public class MessageSubscriber {

	@Autowired
	private ContentRepository contentRepository;

	@Autowired
	private Tracer tracer;

	private Gson gson = new Gson();

	@StreamListener(target = MessageExchange.INPUT, condition = MessageEvent.CONDITIONAL_EXPRESSION)
	public void subscriberMessage(TOTVSMessage<MessageEvent> message) {
		tracerSpan(message.getContent().getContentModel(), MessageEvent.NAME);

		contentRepository.save(message.getContent().getContentModel());
	}

	private void tracerSpan(ContentModel contentModel, String event) {
		Span span = tracer.buildSpan("Receive Message").start();
		String content = gson.toJson(contentModel);

		span.log(event);
		span.log(content);

		span.finish();
	}

}
