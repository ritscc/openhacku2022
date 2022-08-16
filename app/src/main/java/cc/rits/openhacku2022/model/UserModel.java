package cc.rits.openhacku2022.model;

import cc.rits.openhacku2022.db.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザモデル
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    /**
     * ユーザID
     */
    private Integer id;

    /**
     * ファーストネーム
     */
    private String firstName;

    /**
     * ラストネーム
     */
    private String lastName;

    /**
     * メールアドレス
     */
    private String email;

    /**
     * パスワード (ハッシュ値)
     */
    private String password;

    public UserModel(final User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

}
