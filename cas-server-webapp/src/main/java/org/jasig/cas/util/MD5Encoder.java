package org.jasig.cas.util;

import org.jasig.cas.authentication.handler.PasswordEncoder;

/**
 * @Description: 用于实现自定义加密函数.
 * @Author: WuF
 * @Version: 1.0
 * @Date: 2015年1月14日 上午9:17:50
 * @Others:
 */
public class MD5Encoder implements PasswordEncoder {
    @Override
    public final String encode(final String password) {
        return new MD5().getMD5ofStr(password);
    }
}
