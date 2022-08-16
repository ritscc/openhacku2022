package cc.rits.openhacku2022.util;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import cc.rits.openhacku2022.exception.BadRequestException;
import cc.rits.openhacku2022.exception.ErrorCode;
import lombok.RequiredArgsConstructor;

/**
 * 認証のユーティリティ
 */
@Component
@RequiredArgsConstructor
public class AuthUtil {

    private final PasswordEncoder passwordEncoder;

    /**
     * 有効なメールアドレスかチェック
     *
     * @param email メールアドレス
     * @return 有効なメールアドレスか
     */
    public static Boolean isEmailValid(final String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    /**
     * パスワードポリシーに即しているかチェック
     *
     * @param password パスワード
     */
    public static void checkIsPasswordValid(final String password) {
        // NOTE: 本来はBooleanを返したいのだが、どのルールで弾かれたのかを提示したいので例外をスローする実装にした
        // 8~32文字かどうか
        if (!ValidationUtil.checkStringLength(password, 8, 32)) {
            throw new BadRequestException(ErrorCode.INVALID_PASSWORD_LENGTH);
        }
        // 大文字・小文字・数字を含むか
        if (!password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).+$")) {
            throw new BadRequestException(ErrorCode.PASSWORD_IS_TOO_SIMPLE);
        }
    }

    /**
     * パスワードをハッシュ化
     *
     * @param password パスワード
     * @return ハッシュ値
     */
    public String hashingPassword(final String password) {
        return this.passwordEncoder.encode(password);
    }

    /**
     * パスワードとハッシュ値が一致するか
     *
     * @param password パスワード
     * @param hash ハッシュ値
     * @return パスワードとハッシュが一致するか
     */
    public Boolean isMatchPasswordAndHash(final String password, final String hash) {
        return this.passwordEncoder.matches(password, hash);
    }

}
