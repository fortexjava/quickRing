package com.fortex.quickRing.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fortex.quickRing.model.FieldValidateModel;

import quickfix.FieldNotFound;
import quickfix.Group;
import quickfix.Message;
import quickfix.Session;
import quickfix.field.MsgType;

public class GenerateFortexFix {
	
	private static ParseXML parser;
	public static void init(String fileName) throws Exception {
		parser = new ParseXML(fileName);
	}
	
	public static Message generateMessage(Message message, Session session, boolean isFromServer) throws FieldNotFound {
		Message result = null;
		if (session != null) {
			String msgType = message.getHeader().getString(MsgType.FIELD);
			Message msg = session.getMessageFactory().create(session.getSessionID().getBeginString(), msgType);
			result = generalMessage(message, msg, isFromServer);
		}
		return result;
	}

	public static Message generalMessage(Message message,
			Message msg, boolean isFromServer) throws FieldNotFound {
		String msgType = message.getHeader().getString(MsgType.FIELD);
		Map<Integer, FieldValidateModel> map = parser.getModelsMap().get(msgType);
		Map<Integer, Map<Integer, String>> textMap = parser.getMapping();
		if(map == null) return message;
		Iterator<Entry<Integer, FieldValidateModel>> ite = map.entrySet().iterator();
		Integer fixNum;
		FieldValidateModel model;
		while(ite.hasNext()) {
			Entry<Integer, FieldValidateModel> next = ite.next();
			fixNum = next.getKey();
			if(fixNum == MsgType.FIELD) {
				continue;
			}
			
			model = next.getValue();
			if(message.isSetField(fixNum)) {
				Map<Integer, String> m = textMap.get(fixNum);
				String msgText = message.getString(fixNum);
				if(isFromServer && m != null && msgText != null && msgText.matches("\\d{1,}")) {
					msg.setString(fixNum, m.get(Integer.parseInt(msgText)));
				} else {
					msg.setString(fixNum, message.getString(fixNum));
				}
			}else if(model.isRequired()){
				String defaultVal = model.getDefaultVal();
				//if(defaultVal == null) {
				//	throw new FieldNotFound("not find field : " + fixNum);
				//}else {
				if(defaultVal != null && !"".equals(defaultVal))
					msg.setString(fixNum, defaultVal);
				//}
			}
			Map<Integer, FieldValidateModel> groups = model.getGroups();
			if(groups != null) {
				generateGroup(groups, message, fixNum, msg);
			}
		}
		return msg;
	}

	private static void generateGroup(Map<Integer, FieldValidateModel> groups,
			Message message, int fixNum, Message msg) throws FieldNotFound {
		List<Group> messageGroup = message.getGroups(fixNum);
		int num = 0;
		FieldValidateModel model;
		Iterator<Entry<Integer, FieldValidateModel>> ite;
		boolean flag = true;
		for(Group group : messageGroup) {
			ite = groups.entrySet().iterator();
			while(ite.hasNext()) {
				Entry<Integer, FieldValidateModel> next = ite.next();
				num = next.getKey();
				model = next.getValue();
				if(!group.isSetField(num) && model.isRequired()) {
					//flag = flag || !model.isRequired();
					String defaultVal = model.getDefaultVal();
					if(defaultVal == null) {
						flag = false;
					}else {
						group.setString(num, defaultVal);
					}
				}
				if(!flag){
					break;
				}
			}
			if(!flag) {
				throw new FieldNotFound("not find field : " + num);
			}
			msg.addGroup(group);
		}
	}

}
