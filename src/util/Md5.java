package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class Md5 {
	public static String jiami(String pas) throws UnsupportedEncodingException {
		if(Tools.isnull(pas)) {
			return null;
		}
		String newpas=null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			 newpas = base64en.encode(md5.digest(pas.getBytes("utf-8")));
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newpas;
		
	}

}
