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
package com.soomla.store.domain;

import com.soomla.store.StoreUtils;
import com.soomla.store.data.JSONConsts;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This is the parent class of all virtual items in the application.
 * Almost every entity in your virtual economy will be a virtual item. There are many types
 * of virtual items, each one will extend this class. Each one of the various types extends
 * VirtualItem and adds its own behavior on top of it.
 */
public abstract class VirtualItem {

    /**
     * Constructor
     *
     * @param mName the name of the virtual item
     * @param mDescription the description of the virtual item
     * @param mItemId the itemId of the virtual item
     */
    public VirtualItem(String mName, String mDescription, String mItemId) {
        this.mName = mName;
        this.mDescription = mDescription;
        this.mItemId = mItemId.trim();
    }

    /**
     * Constructor
     * Generates an instance of VirtualItem from a {@link JSONObject}.
     *
     * @param jsonObject a JSONObject representation of the wanted VirtualItem.
     * @throws JSONException
     */
    public VirtualItem(JSONObject jsonObject) throws JSONException{
        mName = jsonObject.getString(JSONConsts.ITEM_NAME);
        try{
            mDescription = jsonObject.getString(JSONConsts.ITEM_DESCRIPTION);
        } catch (JSONException ex) {
        }
        mItemId = jsonObject.getString(JSONConsts.ITEM_ITEMID);
    }

    /**
     * Converts the current VirtualItem to a {@link JSONObject}.
     *
     * @return a {@link JSONObject} representation of the current VirtualItem.
     */
    public JSONObject toJSONObject(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(JSONConsts.ITEM_NAME, mName);
            jsonObject.put(JSONConsts.ITEM_DESCRIPTION, mDescription);
            jsonObject.put(JSONConsts.ITEM_ITEMID, mItemId);
        } catch (JSONException e) {
            StoreUtils.LogError(TAG, "An error occurred while generating JSON object.");
        }

        return jsonObject;
    }

    /**
     * Gives your user the given amount of the specific virtual item.
     * For example, when your user plays your game for the first time you GIVE him 1000 gems.
     *
     * NOTE: This action is different than buy() (a method of PurchasableVirtualItems):
     * You use give(int amount) to give your user something for free.
     * You use buy() to give your user something and get something in return.
     *
     * @param amount the amount of the specific item to be given
     * @return balance after the giving process
     */
    public int give(int amount) {
        return give(amount, true);
    }

    /**
     * Works like {@link #give(int)} but receives an argument, notify, to indicate
     * if there has been a change in the balance of the current virtual item.
     *
     * @param amount the amount of the specific item to be given
     * @param notify notify of change in user's balance of current virtual item
     * @return balance after the giving process
     */
    public abstract int give(int amount, boolean notify);

    /**
     * Takes from your user the given amount of the specific virtual item.
     *
     * @param amount the amount of the specific item to be taken
     * @return balance after the taking process
     */
    public int take(int amount) {
        return take(amount, true);
    }

    /**
     * Works like {@link #take(int)} but receives an argument, notify, to indicate
     * if there has been a change in the balance of the current virtual item.
     *
     * @param amount the amount of the specific item to be taken
     * @param notify notify of change in user's balance of current virtual item
     * @return balance after the taking process
     */
    public abstract int take(int amount, boolean notify);

    /**
     * Resets the balance to the given balance.
     *
     * @param balance the balance of the current virtual item
     * @return balance after the reset process
     */
    public int resetBalance(int balance) {
        return resetBalance(balance, true);
    }

    /**
     * Works like {@link #resetBalance(int)} but receives an argument, notify, to indicate
     * if there has been a change in the balance of the current virtual item.
     *
     * @param balance the balance of the current virtual item
     * @param notify notify of change in user's balance of current virtual item
     * @return balance after the reset process
     */
    public abstract int resetBalance(int balance, boolean notify);

    /**
     * Checks if the given object is equal to this object, by comparing the given object's
     * item id with this VirtualItem's itemId
     *
     * @param o the object to compare
     * @return true if the objects are equal, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VirtualItem)) return false;

        VirtualItem that = (VirtualItem) o;

        return mItemId.equals(that.mItemId);
    }

    /**
     * Returns the hashCode of mItemId if it exists
     *
     * @return the hashCode of mItemId
     */
    @Override
    public int hashCode() {
        return mItemId != null ? mItemId.hashCode() : 0;
    }


    /** Setters and Getters **/

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getItemId(){
        return mItemId;
    }


    /** Private Members **/

    private static final String TAG = "SOOMLA VirtualItem"; //used for Log messages

    private String mName;

    private String mDescription;

    private String mItemId;
}
