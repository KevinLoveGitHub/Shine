/**
 * MStar Software
 * Copyright (c) 2011 - 2012 MStar Semiconductor, Inc. All rights reserved.
 *
 * All software, firmware and related documentation herein (“MStar Software”) are
 * intellectual property of MStar Semiconductor, Inc. (“MStar”) and protected by
 * law, including, but not limited to, copyright law and international treaties.
 * Any use, modification, reproduction, retransmission, or republication of all
 * or part of MStar Software is expressly prohibited, unless prior written
 * permission has been granted by MStar.
 *
 * By accessing, browsing and/or using MStar Software, you acknowledge that you
 * have read, understood, and agree, to be bound by below terms (“Terms”) and to
 * comply with all applicable laws and regulations:
 *
 * 1. MStar shall retain any and all right, ownership and interest to MStar
 * Software and any modification/derivatives thereof.  No right, ownership,
 * or interest to MStar Software and any modification/derivatives thereof is
 * transferred to you under Terms.
 *
 * 2. You understand that MStar Software might include, incorporate or be supplied
 * together with third party’s software and the use of MStar Software may require
 * additional licenses from third parties.  Therefore, you hereby agree it is your
 * sole responsibility to separately obtain any and all third party right and
 * license necessary for your use of such third party’s software.
 *
 * 3. MStar Software and any modification/derivatives thereof shall be deemed as
 * MStar’s confidential information and you agree to keep MStar’s confidential
 * information in strictest confidence and not disclose to any third party.
 *
 * 4. MStar Software is provided on an “AS IS” basis without warranties of any kind.
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
 * in conjunction with your or your customer’s product (“Services”).  You understand
 * and agree that, except otherwise agreed by both parties in writing, Services are
 * provided on an “AS IS” basis and the warranty disclaimer set forth in Section 4
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

import com.mstar.android.tvapi.common.TimerManager;

public class DeskTimerEventListener implements TimerManager.OnTimerEventListener {
    public enum EVENT {
        EV_DESTROY_COUNTDOWN, EV_ONESECOND_BEAT, EV_LASTMINUTE_WARN, EV_UPDATE_LASTMINUTE, EV_SIGNAL_LOCK, EV_EPG_TIME_UP, EV_EPGTIMER_COUNTDOWN, EV_EPGTIMER_RECORD_START, EV_PVR_NOTIFY_RECORD_STOP, EV_OAD_TIMESCAN,
    }

    private Handler m_handler = null;

    static private DeskTimerEventListener timerEventListener = null;

    public DeskTimerEventListener() {
        m_handler = null;
    }

    public void attachHandler(Handler handler) {
        m_handler = handler;
    }

    public void releaseHandler() {
        m_handler = null;
    }

    public static DeskTimerEventListener getInstance() {
        if (timerEventListener == null) {
            timerEventListener = new DeskTimerEventListener();
        }
        return timerEventListener;
    }

    /**
     * Called to indicate an info or a warning about destroy count down.
     *
     * @param mgr the TimerManager the info pertains to.
     * @param what the type of info or warning.
     * @param arg1 an extra code, specific to the info. Typically implementation
     *            dependent.
     * @param arg2 an extra code, specific to the info. Typically implementation
     *            dependent.
     * @return True if the method handled the info, false if it didn't.
     *         Returning false, or not having an OnErrorListener at all, will
     *         cause the info to be discarded.
     */
    public boolean onDestroyCountDown(TimerManager mgr, int what, int arg1, int arg2) {
        Log.d("DeskTimer", "onDestroyCountDown\n");
        // TODO Auto-generated method stub
        if (m_handler == null) {
            return false;
        }
        Bundle b = new Bundle();
        Message msg = m_handler.obtainMessage();
        msg.what = EVENT.EV_DESTROY_COUNTDOWN.ordinal();
        msg.setData(b);
        m_handler.sendMessage(msg);
        return false;
    }

    /**
     * Called to indicate an info or a warning about one second beat
     *
     * @param mgr the TimerManager the info pertains to.
     * @param what the type of info or warning.
     * @param arg1 an extra code, specific to the info. Typically implementation
     *            dependent.
     * @param arg2 an extra code, specific to the info. Typically implementation
     *            dependent.
     * @return True if the method handled the info, false if it didn't.
     *         Returning false, or not having an OnErrorListener at all, will
     *         cause the info to be discarded.
     */
    public boolean onOneSecondBeat(TimerManager mgr, int what, int arg1, int arg2) {
        // TODO Auto-generated method stub
        Log.d("DeskTimer", "onOneSecondBeat\n");
        if (m_handler == null) {
            return false;
        }
        Bundle b = new Bundle();
        b.putInt("LeftTime", arg1);
        Message msg = m_handler.obtainMessage();
        msg.what = EVENT.EV_ONESECOND_BEAT.ordinal();
        msg.setData(b);
        m_handler.sendMessage(msg);
        return false;
    }

    /**
     * Called to indicate an info or a warning about last minute warn
     *
     * @param mgr the TimerManager the info pertains to.
     * @param what the type of info or warning.
     * @param arg1 an extra code, specific to the info. Typically implementation
     *            dependent.
     * @param arg2 an extra code, specific to the info. Typically implementation
     *            dependent.
     * @return True if the method handled the info, false if it didn't.
     *         Returning false, or not having an OnErrorListener at all, will
     *         cause the info to be discarded.
     */
    public boolean onLastMinuteWarn(TimerManager mgr, int what, int arg1, int arg2) {
        // TODO Auto-generated method stub
        Log.d("DeskTimer", "onLastMinuteWarn\n");
        if (m_handler == null) {
            return false;
        }
        Bundle b = new Bundle();
        b.putInt("LeftTime", arg1);
        Message msg = m_handler.obtainMessage();
        msg.what = EVENT.EV_LASTMINUTE_WARN.ordinal();
        msg.setData(b);
        m_handler.sendMessage(msg);
        return false;
    }

    /**
     * Called to indicate an info or a warning about update last minute
     *
     * @param mgr the TimerManager the info pertains to.
     * @param what the type of info or warning.
     * @param arg1 an extra code, specific to the info. Typically implementation
     *            dependent.
     * @param arg2 an extra code, specific to the info. Typically implementation
     *            dependent.
     * @return True if the method handled the info, false if it didn't.
     *         Returning false, or not having an OnErrorListener at all, will
     *         cause the info to be discarded.
     */
    public boolean onUpdateLastMinute(TimerManager mgr, int what, int arg1, int arg2) {
        // TODO Auto-generated method stub
        Log.d("DeskTimer", "onUpdateLastMinute\n");
        if (m_handler == null) {
            return false;
        }
        Bundle b = new Bundle();
        b.putInt("LeftTime", arg1);
        Message msg = m_handler.obtainMessage();
        msg.what = EVENT.EV_UPDATE_LASTMINUTE.ordinal();
        msg.setData(b);
        m_handler.sendMessage(msg);
        return false;
    }

    /**
     * Called to indicate an info or a warning about signal lock
     *
     * @param mgr the TimerManager the info pertains to.
     * @param what the type of info or warning.
     * @param arg1 an extra code, specific to the info. Typically implementation
     *            dependent.
     * @param arg2 an extra code, specific to the info. Typically implementation
     *            dependent.
     * @return True if the method handled the info, false if it didn't.
     *         Returning false, or not having an OnErrorListener at all, will
     *         cause the info to be discarded.
     */
    public boolean onSignalLock(TimerManager mgr, int what, int arg1, int arg2) {
        // TODO Auto-generated method stub
        if (m_handler == null) {
            return false;
        }
        Bundle b = new Bundle();
        Message msg = m_handler.obtainMessage();
        msg.what = EVENT.EV_SIGNAL_LOCK.ordinal();
        msg.setData(b);
        m_handler.sendMessage(msg);
        return false;
    }

    /**
     * Called to indicate an info or a warning about epg time up
     *
     * @param mgr the TimerManager the info pertains to.
     * @param what the type of info or warning.
     * @param arg1 an extra code, specific to the info. Typically implementation
     *            dependent.
     * @param arg2 an extra code, specific to the info. Typically implementation
     *            dependent.
     * @return True if the method handled the info, false if it didn't.
     *         Returning false, or not having an OnErrorListener at all, will
     *         cause the info to be discarded.
     */
    public boolean onEpgTimeUp(TimerManager mgr, int what, int arg1, int arg2) {
        // TODO Auto-generated method stub
        Log.d("DeskTimer", "onEpgTimeUp\n");
        if (m_handler == null) {
            return false;
        }
        Bundle b = new Bundle();
        b.putInt("LeftTime", arg1);
        Message msg = m_handler.obtainMessage();
        msg.what = EVENT.EV_EPG_TIME_UP.ordinal();
        msg.setData(b);
        m_handler.sendMessage(msg);
        return false;
    }

    /**
     * Called to indicate an info or a warning about epg timer count down
     *
     * @param mgr the TimerManager the info pertains to.
     * @param what the type of info or warning.
     * @param arg1 an extra code, specific to the info. Typically implementation
     *            dependent.
     * @param arg2 an extra code, specific to the info. Typically implementation
     *            dependent.
     * @return True if the method handled the info, false if it didn't.
     *         Returning false, or not having an OnErrorListener at all, will
     *         cause the info to be discarded.
     */
    public boolean onEpgTimerCountDown(TimerManager mgr, int what, int arg1, int arg2) {
        // TODO Auto-generated method stub
        Log.d("DeskTimer", "onEpgTimerCountDown\n");
        if (m_handler == null) {
            return false;
        }
        Bundle b = new Bundle();
        b.putInt("LeftTime", arg1);
        Message msg = m_handler.obtainMessage();
        msg.what = EVENT.EV_EPGTIMER_COUNTDOWN.ordinal();
        msg.setData(b);
        m_handler.sendMessage(msg);
        return false;
    }

    /**
     * Called to indicate an info or a warning about epg timer record start
     *
     * @param mgr the TimerManager the info pertains to.
     * @param what the type of info or warning.
     * @param arg1 an extra code, specific to the info. Typically implementation
     *            dependent.
     * @param arg2 an extra code, specific to the info. Typically implementation
     *            dependent.
     * @return True if the method handled the info, false if it didn't.
     *         Returning false, or not having an OnErrorListener at all, will
     *         cause the info to be discarded.
     */
    public boolean onEpgTimerRecordStart(TimerManager mgr, int what, int arg1, int arg2) {
        // TODO Auto-generated method stub
        Log.d("DeskTimer", "onEpgTimerRecordStart\n");
        if (m_handler == null) {
            return false;
        }
        Bundle b = new Bundle();
        b.putInt("LeftTime", arg1);
        Message msg = m_handler.obtainMessage();
        msg.what = EVENT.EV_EPGTIMER_RECORD_START.ordinal();
        msg.setData(b);
        m_handler.sendMessage(msg);
        return false;
    }

    /**
     * Called to indicate an info or a warning about pvr notify rcord stop
     *
     * @param mgr the TimerManager the info pertains to.
     * @param what the type of info or warning.
     * @param arg1 an extra code, specific to the info. Typically implementation
     *            dependent.
     * @param arg2 an extra code, specific to the info. Typically implementation
     *            dependent.
     * @return True if the method handled the info, false if it didn't.
     *         Returning false, or not having an OnErrorListener at all, will
     *         cause the info to be discarded.
     */
    public boolean onPvrNotifyRecordStop(TimerManager mgr, int what, int arg1, int arg2) {
        // TODO Auto-generated method stub
        if (m_handler == null) {
            return false;
        }
        Bundle b = new Bundle();
        b.putInt("LeftTime", arg1);
        Message msg = m_handler.obtainMessage();
        msg.what = EVENT.EV_PVR_NOTIFY_RECORD_STOP.ordinal();
        msg.setData(b);
        m_handler.sendMessage(msg);
        return false;
    }

    /**
     * Called to indicate an info or a warning about oad time scan
     *
     * @param mgr the TimerManager the info pertains to.
     * @param what the type of info or warning.
     * @param arg1 an extra code, specific to the info. Typically implementation
     *            dependent.
     * @param arg2 an extra code, specific to the info. Typically implementation
     *            dependent.
     * @return True if the method handled the info, false if it didn't.
     *         Returning false, or not having an OnErrorListener at all, will
     *         cause the info to be discarded.
     */
    public boolean onOadTimeScan(TimerManager mgr, int what, int arg1, int arg2) {
        // TODO Auto-generated method stub
        if (m_handler == null) {
            return false;
        }
        Bundle b = new Bundle();
        b.putInt("LeftTime", arg1);
        Message msg = m_handler.obtainMessage();
        msg.what = EVENT.EV_OAD_TIMESCAN.ordinal();
        msg.setData(b);
        m_handler.sendMessage(msg);
        return false;
    }

    @Override
    public boolean onPowerDownTime(TimerManager mgr, int what, int arg1, int arg2) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean onSystemClkChg(TimerManager arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
        return false;
    }

}
