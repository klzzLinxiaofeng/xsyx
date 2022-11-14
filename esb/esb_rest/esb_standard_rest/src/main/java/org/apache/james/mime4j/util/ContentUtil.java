package org.apache.james.mime4j.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * 覆盖文件上传转码工具类，它默认是CharsetUtil.US_ASCII且无法指定
 * 所以文件名为中文的时候会产生乱码，这里将CharsetUtil.US_ASCII改为CharsetUtil.UTF_8
 * 暂时只有这个解决办法，如果找到更好的解决办法，请更换
 * @author user
 */
public class ContentUtil {

    private ContentUtil() {
    }

    /**
     * Encodes the specified string into an immutable sequence of bytes using
     * the US-ASCII charset.
     * 
     * @param string
     *            string to encode.
     * @return encoded string as an immutable sequence of bytes.
     */
    public static ByteSequence encode(String string) {
        return encode(CharsetUtil.UTF_8, string);
    }
    
    /**
     * Encodes the specified string into an immutable sequence of bytes using
     * the specified charset.
     * 
     * @param charset
     *            Java charset to be used for the conversion.
     * @param string
     *            string to encode.
     * @return encoded string as an immutable sequence of bytes.
     */
    public static ByteSequence encode(Charset charset, String string) {
        ByteBuffer encoded = charset.encode(CharBuffer.wrap(string));
        ByteArrayBuffer bab = new ByteArrayBuffer(encoded.remaining());
        bab.append(encoded.array(), encoded.position(), encoded.remaining());
        return bab;
    }

    /**
     * Decodes the specified sequence of bytes into a string using the US-ASCII
     * charset.
     * 
     * @param byteSequence
     *            sequence of bytes to decode.
     * @return decoded string.
     */
    public static String decode(ByteSequence byteSequence) {
        return decode(CharsetUtil.UTF_8, byteSequence, 0, byteSequence
                .length());
    }

    /**
     * Decodes the specified sequence of bytes into a string using the specified
     * charset.
     * 
     * @param charset
     *            Java charset to be used for the conversion.
     * @param byteSequence
     *            sequence of bytes to decode. 
     * @return decoded string.
     */
    public static String decode(Charset charset, ByteSequence byteSequence) {
        return decode(charset, byteSequence, 0, byteSequence.length());
    }

    /**
     * Decodes a sub-sequence of the specified sequence of bytes into a string
     * using the US-ASCII charset.
     * 
     * @param byteSequence
     *            sequence of bytes to decode.
     * @param offset
     *            offset into the byte sequence.
     * @param length
     *            number of bytes.
     * @return decoded string.
     */
    public static String decode(ByteSequence byteSequence, int offset,
            int length) {
        return decode(CharsetUtil.UTF_8, byteSequence, offset, length);
    }

    /**
     * Decodes a sub-sequence of the specified sequence of bytes into a string
     * using the specified charset.
     * 
     * @param charset
     *            Java charset to be used for the conversion.
     * @param byteSequence
     *            sequence of bytes to decode.
     * @param offset
     *            offset into the byte sequence.
     * @param length
     *            number of bytes.
     * @return decoded string.
     */
    public static String decode(Charset charset, ByteSequence byteSequence,
            int offset, int length) {
        if (byteSequence instanceof ByteArrayBuffer) {
            ByteArrayBuffer bab = (ByteArrayBuffer) byteSequence;
            return decode(charset, bab.buffer(), offset, length);
        } else {
            byte[] bytes = byteSequence.toByteArray();
            return decode(charset, bytes, offset, length);
        }
    }

    private static String decode(Charset charset, byte[] buffer, int offset,
            int length) {
        return charset.decode(ByteBuffer.wrap(buffer, offset, length))
                .toString();
    }

}
