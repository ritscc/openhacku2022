package cc.rits.openhacku2022.service;

import static org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME;

import java.util.Comparator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.rits.openhacku2022.api.request.LoginRequest;
import cc.rits.openhacku2022.exception.BadRequestException;
import cc.rits.openhacku2022.exception.ErrorCode;
import cc.rits.openhacku2022.exception.NotFoundException;
import cc.rits.openhacku2022.model.TableModel;
import cc.rits.openhacku2022.model.TransactionModel;
import cc.rits.openhacku2022.repository.ShopRepository;
import cc.rits.openhacku2022.repository.TableRepository;
import cc.rits.openhacku2022.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;

/**
 * 認証サービス
 */
@RequiredArgsConstructor
@Service
@Transactional
public class AuthService {

    private final ShopRepository shopRepository;

    private final TableRepository tableRepository;

    private final TransactionRepository transactionRepository;

    private final HttpSession httpSession;

    private final HttpServletRequest httpServletRequest;

    /**
     * ログイン
     * 
     * @param requestBody ログインリクエスト
     */
    public void login(final LoginRequest requestBody) {
        // 店舗を取得
        final var shop = this.shopRepository.selectById(requestBody.getShopId()) //
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_SHOP));

        // 座れるテーブルを取得
        final var table = this.tableRepository.selectByShopId(shop.getId()).stream() //
            .filter((tableModel -> tableModel.available(requestBody.getNumberOfPeople())))
            .min(Comparator.comparing(TableModel::getCapacity)) //
            .orElseThrow(() -> new BadRequestException(ErrorCode.ALL_TABLES_ARE_BOOKED));

        // 取引を開始
        final var transaction = TransactionModel.builder() //
            .shopId(shop.getId()) //
            .tableId(table.getId()) //
            .numberOfPeople(requestBody.getNumberOfPeople()) //
            .build();
        this.transactionRepository.insert(transaction);

        // 取引コードをセッション管理する
        this.httpSession.setAttribute(PRINCIPAL_NAME_INDEX_NAME, transaction.getCode());
        this.httpServletRequest.changeSessionId();
    }

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
