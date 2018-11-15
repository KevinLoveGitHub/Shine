/**
 * MStar Software
 * Copyright (c) 2011 - 2012 MStar Semiconductor, Inc. All rights reserved.
 *
 * All software, firmware and related documentation herein (鈥淢Star Software鈥� are
 * intellectual property of MStar Semiconductor, Inc. (鈥淢Star鈥� and protected by
 * law, including, but not limited to, copyright law and international treaties.
 * Any use, modification, reproduction, retransmission, or republication of all
 * or part of MStar Software is expressly prohibited, unless prior written
 * permission has been granted by MStar.
 *
 * By accessing, browsing and/or using MStar Software, you acknowledge that you
 * have read, understood, and agree, to be bound by below terms (鈥淭erms鈥� and to
 * comply with all applicable laws and regulations:
 *
 * 1. MStar shall retain any and all right, ownership and interest to MStar
 * Software and any modification/derivatives thereof.  No right, ownership,
 * or interest to MStar Software and any modification/derivatives thereof is
 * transferred to you under Terms.
 *
 * 2. You understand that MStar Software might include, incorporate or be supplied
 * together with third party鈥檚 software and the use of MStar Software may require
 * additional licenses from third parties.  Therefore, you hereby agree it is your
 * sole responsibility to separately obtain any and all third party right and
 * license necessary for your use of such third party鈥檚 software.
 *
 * 3. MStar Software and any modification/derivatives thereof shall be deemed as
 * MStar鈥檚 confidential information and you agree to keep MStar鈥檚 confidential
 * information in strictest confidence and not disclose to any third party.
 *
 * 4. MStar Software is provided on an 鈥淎S IS鈥�basis without warranties of any kind.
 * Any warranties are hereby expressly disclaimed by MStar, including without
 * limitation, any warranties of merchantability, non-infringement of intellectual
 * property rights, fitness for a particular purpose, error free and in conformity
 * with any international standard.  You agree to waive any claim against MStar for
 * any loss, damage, cost or expense that you may incur related to your use of MStar
 * Software.  In no event shall MStar be liable for any direct, indirect, incidental
 * or consequential damages, including without limitation, lost of profit or revenues,
 * lost or damage of data, and unauthorized system use.  You agree that this Section 4
 * shall still apply without being affected even if MStar Software has been modified
 * by MStar in accordance with your request or instruction for your use, except
 * otherwise agreed by both parties in writing.
 *
 * 5. If requested, MStar may from time to time provide technical supports or
 * services in relation with MStar Software to you for your use of MStar Software
 * in conjunction with your or your customer鈥檚 product (鈥淪ervices鈥�.  You understand
 * and agree that, except otherwise agreed by both parties in writing, Services are
 * provided on an 鈥淎S IS鈥�basis and the warranty disclaimer set forth in Section 4
 * above shall apply.
 *
 * 6. Nothing contained herein shall be construed as by implication, estoppels or
 * otherwise: (a) conferring any license or right to use MStar name, trademark,
 * service mark, symbol or any other identification; (b) obligating MStar or any
 * of its affiliates to furnish any person, including without limitation, you and
 * your customers, any assistance of any kind whatsoever, or any information; or
 * (c) conferring any license or right under any intellectual property right.
 *
 * 7. These terms shall be governed by and construed in accordance with the laws
 * of Taiwan, R.O.C., excluding its conflict of law rules.  Any and all dispute
 * arising out hereof or related hereto shall be finally settled by arbitration
 * referred to the Chinese Arbitration Association, Taipei in accordance with
 * the ROC Arbitration Law and the Arbitration Rules of the Association by three (3)
 * arbitrators appointed in accordance with the said Rules.  The place of
 * arbitration shall be in Taipei, Taiwan and the language shall be English.
 * The arbitration award shall be final and binding to both parties.
 */

package com.mstar.tvsrvfunc;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.mstar.android.tvapi.dtv.common.CaManager;
import com.mstar.android.tvapi.dtv.vo.CaLockService;
import com.mstar.android.tvapi.dtv.vo.CaStartIPPVBuyDlgInfo;

public class DeskCaEventListener implements CaManager.OnCaEventListener {
    public enum CA_EVENT {
        EV_CA_START_IPPV_BUY_DLG, EV_CA_HIDE_IPPV_DLG, EV_CA_EMAIL_NOTIFY_ICON, EV_CA_SHOW_OSD_MESSAGE, EV_CA_HIDE_OSD_MESSAGE, EV_CA_REQUEST_FEEDING, EV_CA_SHOW_BUY_MESSAGE, EV_CA_SHOW_FINGER_MESSAGE, EV_CA_SHOW_PROGRESS_STRIP, EV_CA_ACTION_REQUEST, EV_CA_ENTITLE_CHANGED, EV_CA_DETITLE_RECEVIED, EV_CA_LOCKSERVICE, EV_CA_UNLOCKSERVICE, EV_CA_OTASTATE,
    }

    private Handler m_handler = null;

    static private DeskCaEventListener caEventListener = null;

    public DeskCaEventListener() {
        m_handler = null;
    }

    public void attachHandler(Handler handler) {
        m_handler = handler;
    }

    public void releaseHandler() {
        m_handler = null;
    }

    public static DeskCaEventListener getInstance() {
        if (caEventListener == null) {
            caEventListener = new DeskCaEventListener();
        }
        return caEventListener;
    }

    /**
     * IPPV Program Notify Message
     *
     * @param1 mgr the CaManager the info pertains to.
     * @param2 what the type of info or the Event.
     * @param3 Reserves(Default=0).
     * @param4 what the type of the Message.
     * @return True if the method handled the info, false if it didn't. Returning
     *         false, or not having an OnErrorListener at all, will cause the
     *         info to be discarded.
     */
    @Override
    public boolean onStartIppvBuyDlg(CaManager mgr, int what, int arg1, int arg2,
            CaStartIPPVBuyDlgInfo arg3) {
        if (m_handler == null) {
            return false;
        }
        Bundle b = new Bundle();
        Message msg = m_handler.obtainMessage();
        msg.what = CA_EVENT.EV_CA_START_IPPV_BUY_DLG.ordinal();
        b.putInt("MessageType", arg1);
        b.putInt("MessageType2", arg2);
        msg.setData(b);
        msg.obj = arg3;
        m_handler.sendMessage(msg);
        return false;
    }

    /**
     * Hide IPPV Program Dialog
     *
     * @param1 mgr the CaManager the info pertains to.
     * @param2 what the type of info or the Event.
     * @param3 Reserves(Default=0).
     * @param4 what the type of the Message.
     * @return True if the method handled the info, false if it didn't. Returning
     *         false, or not having an OnErrorListener at all, will cause the
     *         info to be discarded.
     */
    @Override
    public boolean onHideIPPVDlg(CaManager mgr, int what, int arg1, int arg2) {
        if (m_handler == null) {
            return false;
        }
        Bundle b = new Bundle();
        Message msg = m_handler.obtainMessage();
        msg.what = CA_EVENT.EV_CA_HIDE_IPPV_DLG.ordinal();
        b.putInt("MessageType", arg1);
        b.putInt("MessageType2", arg2);
        msg.setData(b);
        m_handler.sendMessage(msg);
        return false;
    }

    /**
     * Email Notify
     *
     * @param1 mgr the CaManager the info pertains to.
     * @param2 what the type of info or the Event.
     * @param3 Reserves(Default=0).
     * @param4 what the type of the Message.
     * @return True if the method handled the info, false if it didn't. Returning
     *         false, or not having an OnErrorListener at all, will cause the
     *         info to be discarded.
     */
    @Override
    public boolean onEmailNotifyIcon(CaManager mgr, int what, int arg1, int arg2) {

        if (m_handler == null) {
            return false;
        }
        Bundle b = new Bundle();
        Message msg = m_handler.obtainMessage();
        msg.what = CA_EVENT.EV_CA_EMAIL_NOTIFY_ICON.ordinal();
        b.putInt("MessageType", arg1);
        b.putInt("MessageType2", arg2);
        msg.setData(b);
        m_handler.sendMessage(msg);
        return false;
    }

    /**
     * Show OSD Message
     *
     * @param1 mgr the CaManager the info pertains to.
     * @param2 what the type of info or the Event.
     * @param3 Reserves(Default=0).
     * @param4 what the type of the Message.
     * @return True if the method handled the info, false if it didn't. Returning
     *         false, or not having an OnErrorListener at all, will cause the
     *         info to be discarded.
     */
    @Override
    public boolean onShowOSDMessage(CaManager mgr, int what, int arg1, int arg2, String arg3) {
        if (m_handler == null) {
            return false;
        }
        Bundle b = new Bundle();
        Message msg = m_handler.obtainMessage();
        msg.what = CA_EVENT.EV_CA_SHOW_OSD_MESSAGE.ordinal();
        b.putInt("MessageType", arg1);
        b.putInt("MessageType2", arg2);
        b.putString("StringType", arg3);
        System.out.print("onShowOSDMessage:" + arg3);
        msg.setData(b);
        m_handler.sendMessage(msg);
        return false;
    }

    /**
     * Hide OSD Message
     *
     * @param1 mgr the CaManager the info pertains to.
     * @param2 what the type of info or the Event.
     * @param3 Reserves(Default=0).
     * @param4 what the type of the Message.
     * @return True if the method handled the info, false if it didn't. Returning
     *         false, or not having an OnErrorListener at all, will cause the
     *         info to be discarded.
     */
    @Override
    public boolean onHideOSDMessage(CaManager mgr, int what, int arg1, int arg2) {

        if (m_handler == null) {
            return false;
        }
        Bundle b = new Bundle();
        Message msg = m_handler.obtainMessage();
        msg.what = CA_EVENT.EV_CA_HIDE_OSD_MESSAGE.ordinal();
        b.putInt("MessageType", arg1);
        b.putInt("MessageType2", arg2);
        msg.setData(b);
        m_handler.sendMessage(msg);
        return false;
    }

    /**
     * Request Feeding Data Message
     *
     * @param1 mgr the CaManager the info pertains to.
     * @param2 what the type of info or the Event.
     * @param3 Reserves(Default=0).
     * @param4 what the type of the Message.
     * @return True if the method handled the info, false if it didn't. Returning
     *         false, or not having an OnErrorListener at all, will cause the
     *         info to be discarded.
     */
    @Override
    public boolean onRequestFeeding(CaManager mgr, int what, int arg1, int arg2) {

        if (m_handler == null) {
            return false;
        }
        Bundle b = new Bundle();
        Message msg = m_handler.obtainMessage();
        msg.what = CA_EVENT.EV_CA_REQUEST_FEEDING.ordinal();
        b.putInt("MessageType", arg1);
        b.putInt("MessageType2", arg2);
        msg.setData(b);
        m_handler.sendMessage(msg);
        return false;
    }

    /**
     * Notify Message when Can't watch the shows
     *
     * @param1 mgr the CaManager the info pertains to.
     * @param2 what the type of info or the Event.
     * @param3 Reserves(Default=0).
     * @param4 what the type of the Message.
     * @return True if the method handled the info, false if it didn't. Returning
     *         false, or not having an OnErrorListener at all, will cause the
     *         info to be discarded.
     */
    @Override
    public boolean onShowBuyMessage(CaManager mgr, int what, int arg1, int arg2) {

        if (m_handler == null) {
            return false;
        }
        Log.i("DeskCaEventListener",
                "//////////////////////////////////EV_CA_SHOW_BUY_MESSAGE/////////////////////////////////////////////////");
        Bundle b = new Bundle();
        Message msg = m_handler.obtainMessage();
        msg.what = CA_EVENT.EV_CA_SHOW_BUY_MESSAGE.ordinal();
        b.putInt("MessageType", arg2);
        b.putInt("MessageFrom", 0);
        msg.setData(b);
        m_handler.sendMessage(msg);
        // System.out.print("SN Post Event:"+CA_EVENT.EV_CA_SHOW_BUY_MESSAGE.toString()+"\n");
        // System.out.print("SN Post Message Type:"+arg2);
        return false;
    }

    /**
     * For Finger Message Show
     *
     * @param1 mgr the CaManager the info pertains to.
     * @param2 what the type of info or the Event.
     * @param3 Reserves(Default=0).
     * @param4 what the type of the Message.
     * @return True if the method handled the info, false if it didn't. Returning
     *         false, or not having an OnErrorListener at all, will cause the
     *         info to be discarded.
     */
    @Override
    public boolean onShowFingerMessage(CaManager mgr, int what, int arg1, int arg2) {

        if (m_handler == null) {
            return false;
        }
        Bundle b = new Bundle();
        Message msg = m_handler.obtainMessage();
        msg.what = CA_EVENT.EV_CA_SHOW_FINGER_MESSAGE.ordinal();
        b.putInt("MessageType", arg1);
        b.putInt("MessageType2", arg2);
        msg.setData(b);
        m_handler.sendMessage(msg);
        return false;
    }

    /**
     * For Progress Strip Show
     *
     * @param1 mgr the CaManager the info pertains to.
     * @param2 what the type of info or the Event.
     * @param3 Reserves(Default=0).
     * @param4 what the type of the Message.
     * @return True if the method handled the info, false if it didn't. Returning
     *         false, or not having an OnErrorListener at all, will cause the
     *         info to be discarded.
     */
    @Override
    public boolean onShowProgressStrip(CaManager mgr, int what, int arg1, int arg2) {

        if (m_handler == null) {
            return false;
        }
        Bundle b = new Bundle();
        Message msg = m_handler.obtainMessage();
        msg.what = CA_EVENT.EV_CA_SHOW_PROGRESS_STRIP.ordinal();
        b.putInt("MessageType", arg1);
        b.putInt("MessageType2", arg2);
        msg.setData(b);
        m_handler.sendMessage(msg);
        return false;
    }

    /**
     * For STB Action Request
     *
     * @param1 mgr the CaManager the info pertains to.
     * @param2 what the type of info or the Event.
     * @param3 Reserves(Default=0).
     * @param4 what the type of the Message.
     * @return True if the method handled the info, false if it didn't. Returning
     *         false, or not having an OnErrorListener at all, will cause the
     *         info to be discarded.
     */
    @Override
    public boolean onActionRequest(CaManager mgr, int what, int arg1, int arg2) {
        if (m_handler == null) {
            return false;
        }
        Bundle b = new Bundle();
        Message msg = m_handler.obtainMessage();
        msg.what = CA_EVENT.EV_CA_ACTION_REQUEST.ordinal();
        b.putInt("MessageType", arg1);
        b.putInt("MessageType2", arg2);
        msg.setData(b);
        m_handler.sendMessage(msg);
        return false;
    }

    /**
     * For STB Entitle Changed Notify
     *
     * @param1 mgr the CaManager the info pertains to.
     * @param2 what the type of info or the Event.
     * @param3 Reserves(Default=0).
     * @param4 what the type of the Message.
     * @return True if the method handled the info, false if it didn't. Returning
     *         false, or not having an OnErrorListener at all, will cause the
     *         info to be discarded.
     */
    @Override
    public boolean onEntitleChanged(CaManager mgr, int what, int arg1, int arg2) {

        if (m_handler == null) {
            return false;
        }
        Bundle b = new Bundle();
        Message msg = m_handler.obtainMessage();
        msg.what = CA_EVENT.EV_CA_ENTITLE_CHANGED.ordinal();
        b.putInt("MessageType", arg1);
        b.putInt("MessageType2", arg2);
        msg.setData(b);
        m_handler.sendMessage(msg);
        return false;
    }

    /**
     * For Notify the Status of STB Detitle Check Num Space
     *
     * @param1 mgr the CaManager the info pertains to.
     * @param2 what the type of info or the Event.
     * @param3 Reserves(Default=0).
     * @param4 what the type of the Message.
     * @return True if the method handled the info, false if it didn't. Returning
     *         false, or not having an OnErrorListener at all, will cause the
     *         info to be discarded.
     */
    @Override
    public boolean onDetitleReceived(CaManager mgr, int what, int arg1, int arg2) {

        if (m_handler == null) {
            return false;
        }
        Bundle b = new Bundle();
        Message msg = m_handler.obtainMessage();
        msg.what = CA_EVENT.EV_CA_DETITLE_RECEVIED.ordinal();
        b.putInt("MessageType", arg1);
        b.putInt("MessageType2", arg2);
        msg.setData(b);
        m_handler.sendMessage(msg);
        return false;
    }

    /**
     * For Notify the Status of STB Detitle Check Num Space
     *
     * @param1 mgr the CaManager the info pertains to.
     * @param2 what the type of info or the Event.
     * @param3 Reserves(Default=0).
     * @param4 what the type of the Message.
     * @return True if the method handled the info, false if it didn't. Returning
     *         false, or not having an OnErrorListener at all, will cause the
     *         info to be discarded.
     */
    @Override
    public boolean onLockService(CaManager mgr, int what, int arg1, int arg2, CaLockService arg3) {

        if (m_handler == null) {
            return false;
        }
        Bundle b = new Bundle();
        Message msg = m_handler.obtainMessage();
        msg.what = CA_EVENT.EV_CA_LOCKSERVICE.ordinal();
        b.putInt("MessageType", arg1);
        b.putInt("MessageType2", arg2);
        msg.setData(b);
        msg.obj = arg3;
        m_handler.sendMessage(msg);
        return false;
    }

    /**
     * For Notify the Status of STB Detitle Check Num Space
     *
     * @param1 mgr the CaManager the info pertains to.
     * @param2 what the type of info or the Event.
     * @param3 Reserves(Default=0).
     * @param4 what the type of the Message.
     * @return True if the method handled the info, false if it didn't. Returning
     *         false, or not having an OnErrorListener at all, will cause the
     *         info to be discarded.
     */
    @Override
    public boolean onUNLockService(CaManager mgr, int what, int arg1, int arg2) {

        if (m_handler == null) {
            return false;
        }
        Bundle b = new Bundle();
        Message msg = m_handler.obtainMessage();
        msg.what = CA_EVENT.EV_CA_LOCKSERVICE.ordinal();
        b.putInt("MessageType", arg1);
        b.putInt("MessageType2", arg2);
        msg.setData(b);
        m_handler.sendMessage(msg);
        return false;
    }

    /**
     * For Notify the Status of STB Detitle Check Num Space
     *
     * @param1 mgr the CaManager the info pertains to.
     * @param2 what the type of info or the Event.
     * @param3 Reserves(Default=0).
     * @param4 what the type of the Message.
     * @return True if the method handled the info, false if it didn't. Returning
     *         false, or not having an OnErrorListener at all, will cause the
     *         info to be discarded.
     */
    @Override
    public boolean onOtaState(CaManager mgr, int what, int arg1, int arg2) {

        if (m_handler == null) {
            return false;
        }
        Bundle b = new Bundle();
        Message msg = m_handler.obtainMessage();
        msg.what = CA_EVENT.EV_CA_OTASTATE.ordinal();
        b.putInt("MessageType", arg1);
        b.putInt("MessageType2", arg2);
        msg.setData(b);
        m_handler.sendMessage(msg);
        return false;
    }
}
