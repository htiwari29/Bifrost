package com.bookit.bifrost.common.errors;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class Errors implements Serializable {

	private final String target;

	private final int code;

	private final String timestamp;

	private final String message;

	public Errors(String target, int code, String message) {
		this.message = message;
		this.code = code;
		this.target = target;
		this.timestamp = new Timestamp(System.currentTimeMillis()).toString();
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}

	public String getTarget() {
		return target;
	}

	public String getTimestamp() {
		return timestamp;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Errors other))
			return false;

		return Objects.equals(code, other.getCode());
	}

	@Override
	public int hashCode() {
		return Objects.hash(code);
	}

	@Override
	public String toString() {
		return "Errors{" + "target='" + target + '\'' + ", code=" + code + ", timestamp='" + timestamp + '\''
				+ ", message='" + message + '\'' + '}';
	}

}
