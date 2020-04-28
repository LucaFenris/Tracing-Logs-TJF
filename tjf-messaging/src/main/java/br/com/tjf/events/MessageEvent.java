package br.com.tjf.events;

import br.com.tjf.model.ContentModel;

public class MessageEvent {

	public static final transient String NAME = "MessageEvent";
	public static final transient String CONDITIONAL_EXPRESSION = "headers['type']=='" + NAME + "'";

	private ContentModel contentModel;

	public MessageEvent() {
	}

	public MessageEvent(ContentModel contentModel) {
		this.contentModel = contentModel;
	}

	public ContentModel getContentModel() {
		return contentModel;
	}

	public void setContentModel(ContentModel contentModel) {
		this.contentModel = contentModel;
	}

}
