package com.shop.utils;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
@Service
public class HashUtil {
	public static String hash(String plain) {
		String salt=BCrypt.gensalt();
		return BCrypt.hashpw(plain, salt);
	}
	public static boolean verify(String plain,String hash) {
		return BCrypt.checkpw(plain, hash);
	}
}
