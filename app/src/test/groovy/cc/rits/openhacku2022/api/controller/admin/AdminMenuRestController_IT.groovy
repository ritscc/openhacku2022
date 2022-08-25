package cc.rits.openhacku2022.api.controller.admin

import cc.rits.openhacku2022.api.controller.BaseRestController_IT
import cc.rits.openhacku2022.api.request.MenuUpsertRequest
import cc.rits.openhacku2022.api.response.MenusResponse
import cc.rits.openhacku2022.exception.*
import cc.rits.openhacku2022.helper.RandomHelper
import cc.rits.openhacku2022.helper.TableHelper
import org.springframework.http.HttpStatus
import spock.lang.Shared
import spock.lang.Unroll

/**
 * AdminMenuRestControllerの統合テスト
 */
class AdminMenuRestController_IT extends BaseRestController_IT {

    // API PATH
    static final String BASE_PATH = "/api/admin/shops/%d/menus"
    static final String GET_MENUS_PATH = BASE_PATH
    static final String CREATE_MENU_PATH = BASE_PATH
    static final String DELETE_MENU_PATH = BASE_PATH + "/%d"
    static final String UPDATE_MENU_PATH = BASE_PATH + "/%d"

    @Shared
    MenuUpsertRequest menuUpsertRequest

    def setup() {
        this.menuUpsertRequest = MenuUpsertRequest.builder()
            .name(RandomHelper.alphanumeric(10))
            .price(1000)
            .image(RandomHelper.base64())
            .build()
    }

    def "メニューリスト取得API: 正常系 自店舗のメニューリストを取得できる"() {
        given:
        this.loginShop()

        // @formatter:off
        TableHelper.insert sql, "menu", {
            id | shop_id | name | price | image_url
            1  | 1       | ""   | 100   | ""
            2  | 1       | ""   | 100   | ""
        }
        // @formatter:on

        when:
        final request = this.getRequest(String.format(GET_MENUS_PATH, 1))
        final response = execute(request, HttpStatus.OK, MenusResponse)

        then:
        response.menus*.id == [1, 2]
        response.menus*.shopId == [1, 1]
        response.menus*.price == [100, 100]
    }

    def "メニューリスト取得API: 異常系 店舗が存在しない場合は404エラー"() {
        given:
        this.loginShop()

        expect:
        final request = this.getRequest(String.format(GET_MENUS_PATH, 2))
        this.execute(request, new NotFoundException(ErrorCode.NOT_FOUND_SHOP))
    }

    def "メニューリスト取得API: 異常系 自店舗以外にアクセスした場合は403エラー"() {
        given:
        this.loginShop()

        // @formatter:off
        TableHelper.insert sql, "shop", {
            id | name | code | password
            2  | ""   | ""   | ""
        }
        // @formatter:on

        expect:
        final request = this.getRequest(String.format(GET_MENUS_PATH, 2))
        this.execute(request, new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION))
    }

    def "メニューリスト取得API: 異常系 ログインしていない場合は401エラー"() {
        expect:
        final request = this.getRequest(String.format(GET_MENUS_PATH, 1))
        this.execute(request, new UnauthorizedException(ErrorCode.USER_NOT_LOGGED_IN))
    }

    def "メニュー作成API: 正常系 メニューを作成できる"() {
        given:
        this.loginShop()

        when:
        final request = this.postRequest(String.format(CREATE_MENU_PATH, 1), this.menuUpsertRequest)
        this.execute(request, HttpStatus.OK)

        then:
        final createdMenu = sql.firstRow("SELECT * FROM menu")
        createdMenu.shop_id == 1
        createdMenu.name == this.menuUpsertRequest.name
        createdMenu.price == this.menuUpsertRequest.price
        createdMenu.image_url != null
    }

    def "メニュー作成API: 異常系 リクエストボディのバリデーション"() {
        given:
        this.loginShop()

        this.menuUpsertRequest.name = inputName
        this.menuUpsertRequest.price = inputPrice

        expect:
        final request = this.postRequest(String.format(CREATE_MENU_PATH, 1), this.menuUpsertRequest)
        this.execute(request, new BadRequestException(expectedErrorCode))

        where:
        inputName                      | inputPrice || expectedErrorCode
        RandomHelper.alphanumeric(0)   | 1          || ErrorCode.INVALID_MENU_NAME
        RandomHelper.alphanumeric(101) | 1          || ErrorCode.INVALID_MENU_NAME
        RandomHelper.alphanumeric(100) | 0          || ErrorCode.INVALID_MENU_PRICE
    }

    def "メニュー作成API: 異常系 店舗が存在しない場合は404エラー"() {
        given:
        this.loginShop()

        expect:
        final request = this.postRequest(String.format(CREATE_MENU_PATH, 2), this.menuUpsertRequest)
        this.execute(request, new NotFoundException(ErrorCode.NOT_FOUND_SHOP))
    }

    def "メニュー作成API: 異常系 自店舗以外にアクセスした場合は403エラー"() {
        given:
        this.loginShop()

        // @formatter:off
        TableHelper.insert sql, "shop", {
            id | name | code | password
            2  | ""   | ""   | ""
        }
        // @formatter:on

        expect:
        final request = this.postRequest(String.format(CREATE_MENU_PATH, 2), this.menuUpsertRequest)
        this.execute(request, new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION))
    }

    def "メニュー作成API: 異常系 ログインしていない場合は401エラー"() {
        expect:
        final request = this.postRequest(String.format(CREATE_MENU_PATH, 1), this.menuUpsertRequest)
        this.execute(request, new UnauthorizedException(ErrorCode.USER_NOT_LOGGED_IN))
    }

    def "メニュー削除API: 正常系 メニューを削除できる"() {
        given:
        this.loginShop()

        // @formatter:off
        TableHelper.insert sql, "menu", {
            id | shop_id | name | price | image_url
            1  | 1       | ""   | 100   | ""
            2  | 1       | ""   | 100   | ""
        }
        // @formatter:on

        when:
        final request = this.deleteRequest(String.format(DELETE_MENU_PATH, 1, 1))
        this.execute(request, HttpStatus.OK)

        then:
        final menus = sql.rows("SELECT * FROM menu")
        menus*.id == [2]
    }

    def "メニュー削除API: 異常系 店舗、もしくはメニューが存在しない場合は404エラー"() {
        given:
        this.loginShop()

        // @formatter:off
        TableHelper.insert sql, "menu", {
            id | shop_id | name | price | image_url
            1  | 1       | ""   | 100   | ""
        }
        // @formatter:on

        expect:
        final request = this.deleteRequest(String.format(DELETE_MENU_PATH, inputShopId, inputMenuId))
        this.execute(request, new NotFoundException(expectedErrorCode))

        where:
        inputShopId | inputMenuId || expectedErrorCode
        2           | 1           || ErrorCode.NOT_FOUND_SHOP
        1           | 2           || ErrorCode.NOT_FOUND_MENU
    }

    def "メニュー削除API: 異常系 自店舗以外にアクセスした場合は403エラー"() {
        given:
        this.loginShop()

        // @formatter:off
        TableHelper.insert sql, "shop", {
            id | name | code | password
            2  | ""   | ""   | ""
        }
        // @formatter:on

        expect:
        final request = this.deleteRequest(String.format(DELETE_MENU_PATH, 2, 1))
        this.execute(request, new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION))
    }

    def "メニュー削除API: 異常系 ログインしていない場合は401エラー"() {
        expect:
        final request = this.deleteRequest(String.format(DELETE_MENU_PATH, 1, 1))
        this.execute(request, new UnauthorizedException(ErrorCode.USER_NOT_LOGGED_IN))
    }

    def "メニュー更新API: 異常系 リクエストボディのバリデーション"() {
        given:
        this.loginShop()

        this.menuUpsertRequest.name = inputName
        this.menuUpsertRequest.price = inputPrice

        expect:
        final request = this.putRequest(String.format(UPDATE_MENU_PATH, 1, 1), this.menuUpsertRequest)
        this.execute(request, new BadRequestException(expectedErrorCode))

        where:
        inputName                      | inputPrice || expectedErrorCode
        RandomHelper.alphanumeric(0)   | 1          || ErrorCode.INVALID_MENU_NAME
        RandomHelper.alphanumeric(101) | 1          || ErrorCode.INVALID_MENU_NAME
        RandomHelper.alphanumeric(100) | 0          || ErrorCode.INVALID_MENU_PRICE
    }

    def "メニュー更新API: 正常系 メニューを更新できる"() {
        given:
        this.loginShop()

        // @formatter:off
        TableHelper.insert sql, "menu", {
            id | shop_id | name | price | image_url
            1  | 1       | ""   | 100   | ""
        }
        // @formatter:on

        when:
        final request = this.putRequest(String.format(UPDATE_MENU_PATH, 1, 1), this.menuUpsertRequest)
        this.execute(request, HttpStatus.OK)

        then:
        final updatedMenu = sql.firstRow("SELECT * FROM menu")
        updatedMenu.id == 1
        updatedMenu.name == this.menuUpsertRequest.name
        updatedMenu.price == this.menuUpsertRequest.price
    }

    def "メニュー更新API: 異常系 自店舗以外にアクセスした場合は403エラー"() {
        given:
        this.loginShop()

        // @formatter:off
        TableHelper.insert sql, "shop", {
            id | name | code | password
            2  | ""   | ""   | ""
        }
        // @formatter:on

        expect:
        final request = this.putRequest(String.format(UPDATE_MENU_PATH, 2, 1), this.menuUpsertRequest)
        this.execute(request, new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION))
    }

    @Unroll
    def "メニュー更新API: 異常系 店舗もしくはメニューが存在しない場合は404エラー"() {
        given:
        this.loginShop()

        // @formatter:off
        TableHelper.insert sql, "menu", {
            id | shop_id | name | price | image_url
            1  | 1       | ""   | 100   | ""
        }
        // @formatter:on

        expect:
        final request = this.putRequest(String.format(UPDATE_MENU_PATH, inputShopId, intputMenuId), this.menuUpsertRequest)
        this.execute(request, new NotFoundException(expectedErrorCode))

        where:
        inputShopId | intputMenuId || expectedErrorCode
        2           | 1            || ErrorCode.NOT_FOUND_SHOP
        1           | 2            || ErrorCode.NOT_FOUND_MENU
    }

    def "メニュー更新API: 異常系 ログインしていない場合は401エラー"() {
        expect:
        final request = this.putRequest(String.format(UPDATE_MENU_PATH, 1, 1), this.menuUpsertRequest)
        this.execute(request, new UnauthorizedException(ErrorCode.USER_NOT_LOGGED_IN))
    }

}
