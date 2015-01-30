package org.jasig.cas.util;
/**
 * @Description: MD5加密类.
 * @FileName: MD5.java
 * @Author: WuF
 * @Version: 1.0
 * @Date: 2015年1月30日 下午4:34:32
 */

public class MD5 {
    /** javadoc. */
    public static final String SPASS = "4576321F30C8B8EADC6B2F87E6721581";
    /** javadoc. */
    static final int S11 = 7;
    /** javadoc. */
    static final int S12 = 12;
    /** javadoc. */
    static final int S13 = 17;
    /** javadoc. */
    static final int S14 = 22;
    /** javadoc. */
    static final int S21 = 5;
    /** javadoc. */
    static final int S22 = 9;
    /** javadoc. */
    static final int S23 = 14;
    /** javadoc. */
    static final int S24 = 20;
    /** javadoc. */
    static final int S31 = 4;
    /** javadoc. */
    static final int S32 = 11;
    /** javadoc. */
    static final int S33 = 16;
    /** javadoc. */
    static final int S34 = 23;
    /** javadoc. */
    static final int S41 = 6;
    /** javadoc. */
    static final int S42 = 10;
    /** javadoc. */
    static final int S43 = 15;
    /** javadoc. */
    static final int S44 = 21;
    /** javadoc. */
    static final byte[] PADDING = {-128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0};
    /** javadoc. */
    private long[] state = new long[4]; // state (ABCD)
    /** javadoc. */
    private long[] count = new long[2]; // number of bits, modulo 2^64 (lsb
                                        // first)
    /** javadoc. */
    private byte[] buffer = new byte[64]; // input buffer
    /** javadoc. */
    private byte[] digest = new byte[16];
    
    /** 构造方法.*/
    public MD5() {
        md5Init();
        return;
    }
    /**
     * 获取加密后的字符串.
     * @param  inbuf 需要进行加密的字符串
     * @return 加密后的字符串.
     */
    public final String getMD5ofStr(final String inbuf) {
        md5Init();
        this.md5Update(inbuf.getBytes(), inbuf.length());
        md5Final();
        String digestHexStr = "";
        for (int i = 0; i < 16; i++) {
            digestHexStr += byteHEX(digest[i]);
        }
        return digestHexStr;
    }
    
    /** 加密初始化工作. */
    private void md5Init() {
        count[0] = 0L;
        count[1] = 0L;
        // /* Load magic initialization constants.

        state[0] = 0x67452301L;
        state[1] = 0xefcdab89L;
        state[2] = 0x98badcfeL;
        state[3] = 0x10325476L;

        return;
    }
    /**
     * @param x ..
     * @param y ..
     * @param z ..
     * @return ...
     */
    private long f(final long x, final long y, final long z) {
        return (x & y) | ((~x) & z);

    }
    /**
     * @param x ..
     * @param y ..
     * @param z ..
     * @return ...
     */
    private long g(final long x, final long y, final long z) {
        return (x & z) | (y & (~z));
    }
    /**
     * @param x ..
     * @param y ..
     * @param z ..
     * @return ...
     */
    private long h(final long x, final long y, final long z) {
        return x ^ y ^ z;
    }
    /**
     * @param x ..
     * @param y ..
     * @param z ..
     * @return ...
     */
    private long i(final long x, final long y, final long z) {
        return y ^ (x | (~z));
    }

    /**
     * @param a ..
     * @param b ..
     * @param c ..
     * @param d ..
     * @param x ..
     * @param s ..
     * @param ac ..
     * @return a ..
     */
    private long ff(long a, final long b, final long c, final long d, final long x, final long s, final long ac) {
        a += f(b, c, d) + x + ac;
        a = ((int) a << s) | ((int) a >>> (32 - s));
        a += b;
        return a;
    }
    /**
     * @param a ..
     * @param b ..
     * @param c ..
     * @param d ..
     * @param x ..
     * @param s ..
     * @param ac ..
     * @return a ..
     * */
    private long gg(long a, final long b, final long c, final long d, final long x, final long s, final long ac) {
        a += g(b, c, d) + x + ac;
        a = ((int) a << s) | ((int) a >>> (32 - s));
        a += b;
        return a;
    }
    /**
     * @param a ..
     * @param b ..
     * @param c ..
     * @param d ..
     * @param x ..
     * @param s ..
     * @param ac ..
     * @return a ..
     */
    private long hh(long a, final long b, final long c, final long d, final long x, final long s, final long ac) {
        a += h(b, c, d) + x + ac;
        a = ((int) a << s) | ((int) a >>> (32 - s));
        a += b;
        return a;
    }
    /**
     * @param a ..
     * @param b ..
     * @param c ..
     * @param d ..
     * @param x ..
     * @param s ..
     * @param ac ..
     * @return a ..
     */
    private long ii(long a, final long b, final long c, final long d, final long x, final long s, final long ac) {
        a += i(b, c, d) + x + ac;
        a = ((int) a << s) | ((int) a >>> (32 - s));
        a += b;
        long r = a;
        return r;
    }
    /**
     * @param inbuf ..
     * @param inputLen ..
     */
    private void md5Update(final byte[] inbuf, final int inputLen) {

        int i, index, partLen;
        byte[] block = new byte[64];
        index = (int) (count[0] >>> 3) & 0x3F;
        // /* Update number of bits */
        count[0] = count[0] +(inputLen << 3);
        boolean b =  count[0] < (inputLen << 3);
        if (b){
            count[1]++;
        }
        count[1] += (inputLen >>> 29);

        partLen = 64 - index;

        // Transform as many times as possible.
        if (inputLen >= partLen) {
            md5Memcpy(buffer, inbuf, index, 0, partLen);
            md5Transform(buffer);

            for (i = partLen; i + 63 < inputLen; i += 64) {

                md5Memcpy(block, inbuf, 0, i, 64);
                md5Transform(block);
            }
            index = 0;

        } else{
            i = 0;
        }
        // /* Buffer remaining input */
        md5Memcpy(buffer, inbuf, index, i, inputLen - i);

    }
    /**javadoc.*/
    private void md5Final() {
        byte[] bits = new byte[8];
        int index, padLen;

        // /* Save number of bits */
        encode(bits, count, 8);

        // /* Pad out to 56 mod 64.
        index = (int) (count[0] >>> 3) & 0x3f;
        padLen = (index < 56) ? (56 - index) : (120 - index);
        md5Update(PADDING, padLen);

        // /* Append length (before padding) */
        md5Update(bits, 8);

        // /* Store state in digest */
        encode(digest, state, 16);

    }
    /**
     * @param output ..
     * @param input ..
     * @param outpos ..
     * @param inpos ..
     * @param len ..
     */
    private void md5Memcpy(final byte[] output, final byte[] input, final int outpos, final int inpos, final int len) {
        int i;
        for (i = 0; i < len; i++){
            output[outpos + i] = input[inpos + i];
        }
    }
    /**
     * @param block ..
     */
    private void md5Transform(final byte[] block) {
        long a = state[0], b = state[1], c = state[2], d = state[3];
        long[] x = new long[16];

        decode(x, block, 64);

        /* Round 1 */
        a = ff(a, b, c, d, x[0], S11, 0xd76aa478L); /* 1 */
        d = ff(d, a, b, c, x[1], S12, 0xe8c7b756L); /* 2 */
        c = ff(c, d, a, b, x[2], S13, 0x242070dbL); /* 3 */
        b = ff(b, c, d, a, x[3], S14, 0xc1bdceeeL); /* 4 */
        a = ff(a, b, c, d, x[4], S11, 0xf57c0fafL); /* 5 */
        d = ff(d, a, b, c, x[5], S12, 0x4787c62aL); /* 6 */
        c = ff(c, d, a, b, x[6], S13, 0xa8304613L); /* 7 */
        b = ff(b, c, d, a, x[7], S14, 0xfd469501L); /* 8 */
        a = ff(a, b, c, d, x[8], S11, 0x698098d8L); /* 9 */
        d = ff(d, a, b, c, x[9], S12, 0x8b44f7afL); /* 10 */
        c = ff(c, d, a, b, x[10], S13, 0xffff5bb1L); /* 11 */
        b = ff(b, c, d, a, x[11], S14, 0x895cd7beL); /* 12 */
        a = ff(a, b, c, d, x[12], S11, 0x6b901122L); /* 13 */
        d = ff(d, a, b, c, x[13], S12, 0xfd987193L); /* 14 */
        c = ff(c, d, a, b, x[14], S13, 0xa679438eL); /* 15 */
        b = ff(b, c, d, a, x[15], S14, 0x49b40821L); /* 16 */

        /* Round 2 */
        a = gg(a, b, c, d, x[1], S21, 0xf61e2562L); /* 17 */
        d = gg(d, a, b, c, x[6], S22, 0xc040b340L); /* 18 */
        c = gg(c, d, a, b, x[11], S23, 0x265e5a51L); /* 19 */
        b = gg(b, c, d, a, x[0], S24, 0xe9b6c7aaL); /* 20 */
        a = gg(a, b, c, d, x[5], S21, 0xd62f105dL); /* 21 */
        d = gg(d, a, b, c, x[10], S22, 0x2441453L); /* 22 */
        c = gg(c, d, a, b, x[15], S23, 0xd8a1e681L); /* 23 */
        b = gg(b, c, d, a, x[4], S24, 0xe7d3fbc8L); /* 24 */
        a = gg(a, b, c, d, x[9], S21, 0x21e1cde6L); /* 25 */
        d = gg(d, a, b, c, x[14], S22, 0xc33707d6L); /* 26 */
        c = gg(c, d, a, b, x[3], S23, 0xf4d50d87L); /* 27 */
        b = gg(b, c, d, a, x[8], S24, 0x455a14edL); /* 28 */
        a = gg(a, b, c, d, x[13], S21, 0xa9e3e905L); /* 29 */
        d = gg(d, a, b, c, x[2], S22, 0xfcefa3f8L); /* 30 */
        c = gg(c, d, a, b, x[7], S23, 0x676f02d9L); /* 31 */
        b = gg(b, c, d, a, x[12], S24, 0x8d2a4c8aL); /* 32 */

        /* Round 3 */
        a = hh(a, b, c, d, x[5], S31, 0xfffa3942L); /* 33 */
        d = hh(d, a, b, c, x[8], S32, 0x8771f681L); /* 34 */
        c = hh(c, d, a, b, x[11], S33, 0x6d9d6122L); /* 35 */
        b = hh(b, c, d, a, x[14], S34, 0xfde5380cL); /* 36 */
        a = hh(a, b, c, d, x[1], S31, 0xa4beea44L); /* 37 */
        d = hh(d, a, b, c, x[4], S32, 0x4bdecfa9L); /* 38 */
        c = hh(c, d, a, b, x[7], S33, 0xf6bb4b60L); /* 39 */
        b = hh(b, c, d, a, x[10], S34, 0xbebfbc70L); /* 40 */
        a = hh(a, b, c, d, x[13], S31, 0x289b7ec6L); /* 41 */
        d = hh(d, a, b, c, x[0], S32, 0xeaa127faL); /* 42 */
        c = hh(c, d, a, b, x[3], S33, 0xd4ef3085L); /* 43 */
        b = hh(b, c, d, a, x[6], S34, 0x4881d05L); /* 44 */
        a = hh(a, b, c, d, x[9], S31, 0xd9d4d039L); /* 45 */
        d = hh(d, a, b, c, x[12], S32, 0xe6db99e5L); /* 46 */
        c = hh(c, d, a, b, x[15], S33, 0x1fa27cf8L); /* 47 */
        b = hh(b, c, d, a, x[2], S34, 0xc4ac5665L); /* 48 */

        /* Round 4 */
        a = ii(a, b, c, d, x[0], S41, 0xf4292244L); /* 49 */
        d = ii(d, a, b, c, x[7], S42, 0x432aff97L); /* 50 */
        c = ii(c, d, a, b, x[14], S43, 0xab9423a7L); /* 51 */
        b = ii(b, c, d, a, x[5], S44, 0xfc93a039L); /* 52 */
        a = ii(a, b, c, d, x[12], S41, 0x655b59c3L); /* 53 */
        d = ii(d, a, b, c, x[3], S42, 0x8f0ccc92L); /* 54 */
        c = ii(c, d, a, b, x[10], S43, 0xffeff47dL); /* 55 */
        b = ii(b, c, d, a, x[1], S44, 0x85845dd1L); /* 56 */
        a = ii(a, b, c, d, x[8], S41, 0x6fa87e4fL); /* 57 */
        d = ii(d, a, b, c, x[15], S42, 0xfe2ce6e0L); /* 58 */
        c = ii(c, d, a, b, x[6], S43, 0xa3014314L); /* 59 */
        b = ii(b, c, d, a, x[13], S44, 0x4e0811a1L); /* 60 */
        a = ii(a, b, c, d, x[4], S41, 0xf7537e82L); /* 61 */
        d = ii(d, a, b, c, x[11], S42, 0xbd3af235L); /* 62 */
        c = ii(c, d, a, b, x[2], S43, 0x2ad7d2bbL); /* 63 */
        b = ii(b, c, d, a, x[9], S44, 0xeb86d391L); /* 64 */

        state[0] += a;
        state[1] += b;
        state[2] += c;
        state[3] += d;

    }
    /**
     * @param output ..
     * @param input ..
     * @param len ..
     */
    private void encode(final byte[] output, final long[] input, final int len) {
        int i, j;

        for (i = 0, j = 0; j < len; i++, j += 4) {
            output[j] = (byte) (input[i] & 0xffL);
            output[j + 1] = (byte) ((input[i] >>> 8) & 0xffL);
            output[j + 2] = (byte) ((input[i] >>> 16) & 0xffL);
            output[j + 3] = (byte) ((input[i] >>> 24) & 0xffL);
        }
    }
    /**
     * @param output ..
     * @param input ..
     * @param len ..
     */
    private void decode(final long[] output, final byte[] input, final int len) {
        int i, j;

        for (i = 0, j = 0; j < len; i++, j += 4){
            output[i] = b2iu(input[j]) | (b2iu(input[j + 1]) << 8)
                    | (b2iu(input[j + 2]) << 16) | (b2iu(input[j + 3]) << 24);
        }   
        return;
    }
    /**
     * @param b ..
     * @return ..
     */
    public static long b2iu(final byte b) {
        return b < 0 ? b & 0x7F + 128 : b;
    }
    /**
     * @param ib ..
     * @return ..
     */
    public static String byteHEX(final byte ib) {
        char[] digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F'};
        char[] ob = new char[2];
        ob[0] = digit[(ib >>> 4) & 0X0F];
        ob[1] = digit[ib & 0X0F];
        String s = new String(ob);
        return s;
    }
    /**
     * @param source ..
     * @return ..
     */
    public final String toMD5(final String source) {
        return this.getMD5ofStr(source);
    }
}
