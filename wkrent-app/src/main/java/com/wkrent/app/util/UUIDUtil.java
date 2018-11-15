package com.wkrent.app.util;

import java.util.UUID;

public class UUIDUtil {

	public static String getUUIDString() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
