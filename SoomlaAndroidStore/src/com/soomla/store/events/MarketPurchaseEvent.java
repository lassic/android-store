/**
 * Copyright (C) 2012-2014 Soomla Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.soomla.store.events;

import com.soomla.store.domain.PurchasableVirtualItem;

/**
 * This event is fired when a Market purchase has finished.
 */
public class MarketPurchaseEvent {

    /**
     * Constructor
     *
     * @param purchasableVirtualItem the item that was purchased
     * @param payload the amount paid by the user (with real money!)
     * @param token token associated with in-app billing purchase
     */
    public MarketPurchaseEvent(PurchasableVirtualItem purchasableVirtualItem, String payload,
                               String token) {
        mPurchasableVirtualItem = purchasableVirtualItem;
        mPayload = payload;
        mToken = token;
    }


    /** Setters and Getters */

    public PurchasableVirtualItem getPurchasableVirtualItem() {
        return mPurchasableVirtualItem;
    }

    public String getPayload() {
        return mPayload;
    }

    public String getToken() {
        return mToken;
    }


    /** Private Members */

    private PurchasableVirtualItem mPurchasableVirtualItem;

    private String mPayload;

    private String mToken;
}
