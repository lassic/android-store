/*
 * Copyright (C) 2012 Soomla Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.soomla.store.data;


import com.soomla.store.BusProvider;
import com.soomla.store.domain.VirtualItem;
import com.soomla.store.domain.virtualCurrencies.VirtualCurrency;
import com.soomla.store.events.CurrencyBalanceChangedEvent;

/**
 * This class provides basic storage operations on VirtualCurrencies.
 */
public class VirtualCurrencyStorage extends VirtualItemStorage{

    /**
     * Constructor
     */
    public VirtualCurrencyStorage() {
        mTag = "SOOMLA VirtualCurrencyStorage";
    }

    /**
     * see parent
     *
     * @param itemId id of the virtual item whose balance is to be retrieved
     * @return see parent
     */
    @Override
    protected String keyBalance(String itemId) {
        return KeyValDatabase.keyCurrencyBalance(itemId);
    }

    /**
     * see parent
     *
     * @param item virtual item whose balance has changed
     * @param balance the balance that has changed
     * @param amountAdded the amount added to the item's balance
     */
    @Override
    protected void postBalanceChangeEvent(VirtualItem item, int balance, int amountAdded) {
        BusProvider.getInstance().post(new CurrencyBalanceChangedEvent((VirtualCurrency) item,
                balance, amountAdded));
    }
}
