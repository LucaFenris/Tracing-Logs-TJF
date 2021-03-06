package br.com.tjf.api;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.totvs.tjf.core.common.security.SecurityPrincipal;

import br.com.tjf.events.MessageEvent;
import br.com.tjf.messaging.MessagePublisher;
import br.com.tjf.model.ContentModel;

@RestController
@RequestMapping(path = "/api/message/v1")
public class MessageController {

	private MessagePublisher publisher;

	public MessageController(MessagePublisher publisher) {
		this.publisher = publisher;
	}

	@PostMapping("/send")
	public ResponseEntity<Void> sendMessage(@RequestBody ContentModel contentModel) {
		this.setTenant(UUID.randomUUID().toString());

		publisher.publish(new MessageEvent(contentModel), MessageEvent.NAME);
		publisher.publishSubscriberService(new MessageEvent(contentModel), MessageEvent.NAME);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	private void setTenant(String tenant) {
		SecurityPrincipal principal = new SecurityPrincipal(null, "", tenant, tenant);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal, "N/A",
				null);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
