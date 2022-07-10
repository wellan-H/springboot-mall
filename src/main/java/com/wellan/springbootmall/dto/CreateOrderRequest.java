package com.wellan.springbootmall.dto;

import javax.validation.constraints.NotEmpty;
import java.util.List;
//對應前端所傳的Object
public class CreateOrderRequest {
    @NotEmpty//表示此集合不得為空
    private List<BuyItem> buyItemList;
    //這裡面接受另一個Json Object的值
//    因此需要另外寫一個類別接收

    public List<BuyItem> getBuyItemList() {
        return buyItemList;
    }

    public void setBuyItemList(List<BuyItem> buyItemList) {
        this.buyItemList = buyItemList;
    }
}
