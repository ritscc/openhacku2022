import { Component, OnInit } from "@angular/core";
import { MenuResponse } from "@api/models/menu-response";

@Component({
    selector: "app-menus",
    templateUrl: "./menus.component.html",
    styleUrls: ["./menus.component.scss"],
})
export class MenusComponent implements OnInit {
    menus: MenuResponse[] = [];

    constructor() {}

    ngOnInit(): void {
        // TODO: メニューリスト取得APIから取得
        this.menus = [
            {
                id: 1,
                shopId: 1,
                name: "アスパラガスとベーコンのパスタ",
                price: 1200,
                imageUrl: "https://gahag.net/img/201512/21s/gahag-0038751717-1.jpg",
            },
            {
                id: 2,
                shopId: 1,
                name: "濃厚生クリームのカルボナーラ",
                price: 1320,
                imageUrl: "https://gahag.net/img/201512/21s/gahag-0038751717-1.jpg",
            },
            {
                id: 3,
                shopId: 1,
                name: "本場の味を厳格に再現 ペペロンチーノ",
                price: 1150,
                imageUrl: "https://gahag.net/img/201512/21s/gahag-0038751717-1.jpg",
            },
            {
                id: 4,
                shopId: 1,
                name: "アスパラガスとベーコンのパスタ",
                price: 1200,
                imageUrl: "https://gahag.net/img/201512/21s/gahag-0038751717-1.jpg",
            },
            {
                id: 5,
                shopId: 1,
                name: "濃厚生クリームのカルボナーラ",
                price: 1320,
                imageUrl: "https://gahag.net/img/201512/21s/gahag-0038751717-1.jpg",
            },
            {
                id: 6,
                shopId: 1,
                name: "本場の味を厳格に再現 ペペロンチーノ",
                price: 1150,
                imageUrl: "https://gahag.net/img/201512/21s/gahag-0038751717-1.jpg",
            },
            {
                id: 7,
                shopId: 1,
                name: "アスパラガスとベーコンのパスタ",
                price: 1200,
                imageUrl: "https://gahag.net/img/201512/21s/gahag-0038751717-1.jpg",
            },
            {
                id: 8,
                shopId: 1,
                name: "濃厚生クリームのカルボナーラ",
                price: 1320,
                imageUrl: "https://gahag.net/img/201512/21s/gahag-0038751717-1.jpg",
            },
            {
                id: 9,
                shopId: 1,
                name: "本場の味を厳格に再現 ペペロンチーノ",
                price: 1150,
                imageUrl: "https://gahag.net/img/201512/21s/gahag-0038751717-1.jpg",
            },
        ];
    }
}
