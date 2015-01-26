/**
 * 
 */
package org.jasig.cas.util;

import org.jasig.cas.authentication.handler.PasswordEncoder;

/**
 * @Description:	 
 * @FileName: 		 Snippet.java
 * @Author:		 WuF
 * @Version: 		 1.0
 * @Date: 			 2015年1月14日 上午9:17:50
 * @Others: 
 * @FuntionList:
 * @History:
 * @Category:
 */
public class MD5Encoder implements PasswordEncoder {
	@Override
	public String encode(String password) {
		return new MD5().getMD5ofStr(password);
	}
}

