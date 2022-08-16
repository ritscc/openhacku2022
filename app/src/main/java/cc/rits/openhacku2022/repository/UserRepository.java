package cc.rits.openhacku2022.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import cc.rits.openhacku2022.db.entity.UserExample;
import cc.rits.openhacku2022.db.mapper.UserMapper;
import cc.rits.openhacku2022.model.UserModel;
import lombok.RequiredArgsConstructor;

/**
 * ユーザリポジトリ
 */
@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final UserMapper userMapper;

    /**
     * メールアドレスからユーザを取得
     * 
     * @param email メールアドレス
     * @return ユーザ
     */
    public Optional<UserModel> selectByEmail(final String email) {
        final var example = new UserExample();
        example.createCriteria().andEmailEqualTo(email);
        return this.userMapper.selectByExample(example).stream() //
            .map(UserModel::new) //
            .findFirst();
    }

}
