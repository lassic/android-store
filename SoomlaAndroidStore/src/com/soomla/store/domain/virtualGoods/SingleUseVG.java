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

package com.soomla.store.domain.virtualGoods;

import com.soomla.store.data.StorageManager;
import com.soomla.store.purchaseTypes.PurchaseType;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Single use virtual goods are the most common type of <code>VirtualGood</code>.
 *
 * The <code>SingleUseVG</code>'s characteristics are:
 *  1. Can be purchased an unlimited number of times.
 *  2. Has a balance that is saved in the database. Its balance goes up when you "give" it or
 *     "buy" it. The balance goes down when you "take" or (unfriendly) "refund" it.
 *
 * Real Game Examples: 'Hat', 'Sword', 'Muffin'
 *
 * NOTE: In case you want this item to be available for purchase with real money
 * you will need to define the item in the market (Google Play, Amazon App Store, etc...).
 *
 * Inheritance: {@link com.soomla.store.domain.virtualGoods.SingleUseVG} >
 * {@link com.soomla.store.domain.virtualGoods.VirtualGood} >
 * {@link com.soomla.store.domain.PurchasableVirtualItem} >
 * {@link com.soomla.store.domain.VirtualItem}
 */
public class SingleUseVG extends VirtualGood{

    /**
     * Constructor
     *
     * @param mName see parent
     * @param mDescription see parent
     * @param mItemId see parent
     * @param purchaseType see parent
     */
    public SingleUseVG(String mName, String mDescription, String mItemId, PurchaseType purchaseType) {
        super(mName, mDescription, mItemId, purchaseType);
    }

    /**
     * Constructor
     *
     * @param jsonObject see parent
     * @throws JSONException
     */
    public SingleUseVG(JSONObject jsonObject) throws JSONException {
        super(jsonObject);
    }

    /**
     * see parent
     *
     * @return see parent
     */
    @Override
    public JSONObject toJSONObject() {
        return super.toJSONObject();
    }

    /**
     * see parent
     *
     * @param amount see parent
     * @return see parent
     */
    @Override
    public int give(int amount, boolean notify) {
        return StorageManager.getVirtualGoodsStorage().add(this, amount, notify);
    }

    /**
     * see parent
     *
     * @param amount see parent
     * @return see parent
     */
    @Override
    public int take(int amount, boolean notify) {
        return StorageManager.getVirtualGoodsStorage().remove(this, amount, notify);
    }

    /**
     * Determines if user is in a state that allows him to buy a <code>SingleUseVG</code>.
     *
     * @return true - users can ALWAYS purchase <code>SingleUseVG</code>s.
     */
    @Override
    protected boolean canBuy() {
        return true;
    }
}
