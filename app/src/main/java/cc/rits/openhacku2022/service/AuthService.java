package cc.rits.openhacku2022.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.rits.openhacku2022.model.TransactionModel;
import cc.rits.openhacku2022.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;

/**
 * 認証サービス
 */
@RequiredArgsConstructor
@Service
@Transactional
public class AuthService {

    private final TransactionRepository transactionRepository;

    private final HttpSession httpSession;

    /**
     * ログアウト
     * 
     * @param transaction 取引
     */
    public void logout(final TransactionModel transaction) {
        // 取引を削除
        this.transactionRepository.delete(transaction);

        // セッションを無効にする
        this.httpSession.invalidate();
    }

}
