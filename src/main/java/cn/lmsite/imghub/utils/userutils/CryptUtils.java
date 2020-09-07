package cn.lmsite.imghub.utils.userutils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

public class CryptUtils {

    /**
     * 加密
     *
     * @param content 内容
     * @return {@link String} 加密后的Base64
     */
    public static String Encrypt(String content) {
        return buildAes().encryptBase64(content, CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * 解密为字符串
     *
     * @param encryptBase64 加密十六进制
     * @return {@link String}
     */
    public static String Decrypt(String encryptBase64) {
        return buildAes().decryptStr(encryptBase64, CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * 随机生成密钥 构建 SymmetricCrypto
     *
     * @return {@link SymmetricCrypto}
     */
    private static SymmetricCrypto buildAes() {
        return new SymmetricCrypto(SymmetricAlgorithm.AES, SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded());
    }
}
