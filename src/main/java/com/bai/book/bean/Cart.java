package com.bai.book.bean;

import java.math.BigDecimal;
import java.util.*;

public class Cart {
    private Map<Integer,CartItem> itemList = new LinkedHashMap<>();


    /**
     * 添加商品
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        CartItem item = itemList.get(cartItem.getId());

        if (item == null){
            itemList.put(cartItem.getId(),cartItem);
        }else {
            item.setCount(item.getCount() + 1);
            item.setTotalPrice( item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    public void deleteItem(Integer id){
        itemList.remove(id);
    }

    public void clear(){
        itemList.clear();
    }

    public void update(Integer id,Integer count){
        CartItem cartItem = itemList.get(id);
        if (cartItem != null){
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", itemList=" + itemList +
                '}';
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;

        for (Map.Entry<Integer,CartItem> entry : itemList.entrySet()){
            totalCount += entry.getValue().getCount();
        }

        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);

        for (Map.Entry<Integer,CartItem> entry : itemList.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }

        return totalPrice;
    }
    public Map<Integer, CartItem> getItemList() {
        return itemList;
    }

    public void setItemList(Map<Integer, CartItem> itemList) {
        this.itemList = itemList;
    }
}
