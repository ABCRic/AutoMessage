package com.TeamNovus.AutoMessage.Models;

import java.util.LinkedList;

import net.md_5.bungee.chat.ComponentSerializer;

public class Message {
	private static final String SPLIT_REGEX = "(?<!\\\\)\\\\n";
	private static final String REPLACE_REGEX = "\\\\\\\\n";
	private static final String REPLACEMENT = "\\\\n";

	private String raw;

	public Message(String raw) {
		this.raw = raw;
	}

	public String getMessage() {
		return raw;
	}

	public Message setMessage(String raw) {
		this.raw = raw;

		return this;
	}

	public boolean isJsonMessage(int index) {
		try {
			ComponentSerializer.parse(getMessages().get(index));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public LinkedList<String> getMessages() {
		LinkedList<String> messages = new LinkedList<String>();

		for (String line : raw.split(SPLIT_REGEX)) {
			if (!(line.startsWith("/"))) {
				line = line.replaceAll(REPLACE_REGEX, REPLACEMENT);
				messages.add(line);
			}
		}

		return messages;
	}

	public LinkedList<String> getCommands() {
		LinkedList<String> commands = new LinkedList<String>();

		for (String line : raw.split(SPLIT_REGEX)) {
			if (line.startsWith("/")) {
				line = line.replaceAll(REPLACE_REGEX, REPLACEMENT);
				commands.add(line);
			}
		}

		return commands;
	}

}
