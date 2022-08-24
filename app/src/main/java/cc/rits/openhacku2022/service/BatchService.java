package cc.rits.openhacku2022.service;

import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.rits.openhacku2022.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;

/**
 * バッチサービス
 */
@RequiredArgsConstructor
@Service
@Transactional
public class BatchService {

    private final FindByIndexNameSessionRepository<? extends Session> sessionRepository;

    private final TransactionRepository transactionRepository;

    /**
     * 期限切れの取引を削除
     */
    public void deleteExpiredTransaction() {
        final var transactions = this.transactionRepository.selectAll();
        transactions.stream() //
            .filter(transaction -> this.sessionRepository.findByPrincipalName(transaction.getCode()).isEmpty()) //
            .forEach(this.transactionRepository::delete);
    }

}
