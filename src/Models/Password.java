package Models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {
	private static String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff; // remove higher bits, sign
			if (val < 16)
				buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}

	public static String getHash(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update(password.getBytes());
			return hexToString(md.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static boolean passwordMatches(String pw_hashed, String pw2) {
		pw2 = getHash(pw2);
		return pw_hashed.equals(pw2);
	}
}
