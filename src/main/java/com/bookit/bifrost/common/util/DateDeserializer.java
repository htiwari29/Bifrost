package com.bookit.bifrost.common.util;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;

public class DateDeserializer extends JsonDeserializer<Date> {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

	@Override
	public Date deserialize(com.fasterxml.jackson.core.JsonParser jsonParser,
			com.fasterxml.jackson.databind.DeserializationContext deserializationContext) throws IOException {
		String date = jsonParser.getText();
		try {
			return dateFormat.parse(date);
		}
		catch (ParseException ex) {
			throw new IOException("Error in parsing date", ex);
		}
	}

}
