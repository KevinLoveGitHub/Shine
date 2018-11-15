/**
 * MStar Software
 * Copyright (c) 2011 - 2012 MStar Semiconductor, Inc. All rights reserved.
 *
 * All software, firmware and related documentation herein (“MStar Software�? are
 * intellectual property of MStar Semiconductor, Inc. (“MStar�? and protected by
 * law, including, but not limited to, copyright law and international treaties.
 * Any use, modification, reproduction, retransmission, or republication of all
 * or part of MStar Software is expressly prohibited, unless prior written
 * permission has been granted by MStar.
 *
 * By accessing, browsing and/or using MStar Software, you acknowledge that you
 * have read, understood, and agree, to be bound by below terms (“Terms�? and to
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
 * 4. MStar Software is provided on an “AS IS�?basis without warranties of any kind.
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
 * in conjunction with your or your customer’s product (“Services�?.  You understand
 * and agree that, except otherwise agreed by both parties in writing, Services are
 * provided on an “AS IS�?basis and the warranty disclaimer set forth in Section 4
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

import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;

import com.mstar.android.tvapi.atv.AtvManager;
import com.mstar.android.tvapi.common.TvManager;
import com.mstar.android.tvapi.common.vo.EnumFirstServiceInputType;
import com.mstar.android.tvapi.common.vo.EnumFirstServiceType;
import com.mstar.android.tvapi.common.vo.TvOsType.EnumInputSource;
import com.mstar.android.tvapi.common.vo.TvOsType.EnumTimeZone;
import com.mstar.android.tvapi.common.vo.VideoInfo;
import com.mstar.android.tvapi.dtv.common.CaManager;
import com.mstar.android.tvapi.dtv.common.DtvManager;
import com.mstar.tvsettingservice.CommonDesk;

public class CommonDeskImpl extends BaseDeskImpl implements CommonDesk {
    int psl[] = null;

    private static CommonDesk commonService = null;

    private EnumInputSource curSourceType = EnumInputSource.E_INPUT_SOURCE_NONE;

    private ST_VIDEO_INFO videoInfo = null;

    public static boolean bThreadIsrunning = false;

    DeskAtvPlayerEventListener deskAtvPlayerLister;

    DeskDtvPlayerEventListener deskDtvPlayerLister;

    DeskTvPlayerEventListener deskTvPlayerLister;

    DeskTvEventListener deskTvLister;

    DeskCiEventListener deskCiLister;

    DeskCaEventListener deskCaLister;

    DeskTimerEventListener deskTimerLister;

    private CommonDeskImpl() {
        videoInfo = new ST_VIDEO_INFO((short) 1920, (short) 1080, (short) 60, (short) 12,
                EN_SCAN_TYPE.E_PROGRESSIVE);
        InitSourceList();
        deskAtvPlayerLister = DeskAtvPlayerEventListener.getInstance();
        deskDtvPlayerLister = DeskDtvPlayerEventListener.getInstance();
        deskTvPlayerLister = DeskTvPlayerEventListener.getInstance();
        deskTvLister = DeskTvEventListener.getInstance();
        deskCiLister = DeskCiEventListener.getInstance();
        deskCaLister = DeskCaEventListener.getInstance();
        deskTimerLister = DeskTimerEventListener.getInstance();
        if (deskDtvPlayerLister != null) {
            DtvManager.getDvbPlayerManager().setOnDtvPlayerEventListener(deskDtvPlayerLister);
        }
        if (deskAtvPlayerLister != null) {
            AtvManager.getAtvPlayerManager().setOnAtvPlayerEventListener(deskAtvPlayerLister);
        }
        if (TvManager.getInstance() != null) {
            TvManager.getInstance().getPlayerManager()
                    .setOnTvPlayerEventListener(deskTvPlayerLister);
            TvManager.getInstance().setOnTvEventListener(deskTvLister);
        }
        try {
            if (DtvManager.getCiManager() != null) {
                DtvManager.getCiManager().setOnCiEventListener(deskCiLister);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (CaManager.getInstance() != null) {
            CaManager.getInstance().setOnCaEventListener(deskCaLister);
        }
        if (TvManager.getInstance() != null && TvManager.getInstance().getTimerManager() != null) {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getTimerManager().setOnTimerEventListener(deskTimerLister);
            }
        }
    }

    public static CommonDesk getInstance() {
        if (commonService == null) {
            commonService = new CommonDeskImpl();
        }
        return commonService;
    }

    @Override
    public boolean setHandler(Handler handler, int index) {
        Log.d("TvApp", "setHandler:" + index);
        if (index == 0)// for rootactivity
        {
            deskTvLister.attachHandler(handler);
            deskTvPlayerLister.attachHandler(handler);
        } else if (index == 1) // for other activity
        {
            deskAtvPlayerLister.attachHandler(handler);
            deskDtvPlayerLister.attachHandler(handler);

        } else if (index == 2) // for ci
        {
            deskCiLister.attachHandler(handler);

        } else if (index == 3) // for timer
        {
            deskTimerLister.attachHandler(handler);

        } else if (index == 4) // for ca
        {
            deskCaLister.attachHandler(handler);

        }

        return super.setHandler(handler, index);
    }

    @Override
    public void releaseHandler(int index) {
        Log.d("TvApp", "releaseHandler:" + index);
        while (bThreadIsrunning) {
            Log.e("TvApp", "tv System not stable!!!");
        }
        if (index == 0)// for rootactivity
        {
            deskTvLister.releaseHandler();
            deskTvPlayerLister.releaseHandler();
        } else if (index == 1) // for other activity
        {
            deskAtvPlayerLister.releaseHandler();
            deskDtvPlayerLister.releaseHandler();
        } else if (index == 2) // for ci
        {
            deskCiLister.releaseHandler();

        } else if (index == 3) // for timer
        {
            deskTimerLister.releaseHandler();

        } else if (index == 4) // for ca
        {
            deskCaLister.releaseHandler();

        }
        super.releaseHandler(index);
    }

    @Override
    public EnumInputSource GetCurrentInputSource() {
        try {
            if (TvManager.getInstance() != null) {
                curSourceType = TvManager.getInstance().getCurrentInputSource();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return curSourceType;
    }

    @Override
    public void SetInputSource(EnumInputSource st) {
        curSourceType = st;
        Log.e("TvApp", "SetSourceType:" + curSourceType.toString());
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().setInputSource(st);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void SetInputSource(EnumInputSource st, boolean bWriteDB) {
        curSourceType = st;
        Log.e("TvApp", "SetSourceType:" + curSourceType.toString());
        try {
            if (bWriteDB == true) {
                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().setInputSource(st);
                }
            } else {
                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().setInputSource(st, false, false, false);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void InitSourceList() {
        try {
            if (TvManager.getInstance() != null) {
                psl = TvManager.getInstance().getSourceList();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int[] getSourceList() {
        if (psl != null) {
            /*
             * psl.get(EN_INPUT_SOURCE_TYPE.MAPI_INPUT_SOURCE_ATV.ordinal()).
             * enablePort = 1;
             * psl.get(EN_INPUT_SOURCE_TYPE.MAPI_INPUT_SOURCE_CVBS.ordinal()).
             * enablePort = 1;
             * psl.get(EN_INPUT_SOURCE_TYPE.MAPI_INPUT_SOURCE_SVIDEO.ordinal()).
             * enablePort = 1;
             * psl.get(EN_INPUT_SOURCE_TYPE.MAPI_INPUT_SOURCE_YPBPR.ordinal()).
             * enablePort = 1;
             * psl.get(EN_INPUT_SOURCE_TYPE.MAPI_INPUT_SOURCE_YPBPR2.ordinal()).
             * enablePort = 1;
             * psl.get(EN_INPUT_SOURCE_TYPE.MAPI_INPUT_SOURCE_VGA.ordinal()).
             * enablePort = 1;
             * psl.get(EN_INPUT_SOURCE_TYPE.MAPI_INPUT_SOURCE_HDMI.ordinal()).
             * enablePort = 1;
             * psl.get(EN_INPUT_SOURCE_TYPE.MAPI_INPUT_SOURCE_HDMI2.ordinal()).
             * enablePort = 1;
             * psl.get(EN_INPUT_SOURCE_TYPE.MAPI_INPUT_SOURCE_DTV.ordinal()).
             * enablePort = 1;
             */
            return psl;
        }
        return null;
    }

    @Override
    public boolean isSignalStable() {
        boolean bRet = false;
        try {
            if (TvManager.getInstance() != null) {
                bRet = TvManager.getInstance().getPlayerManager().isSignalStable();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bRet;
    }

    @Override
    public boolean isHdmiSignalMode() {

        if (TvManager.getInstance() != null) {
            return TvManager.getInstance().getPlayerManager().isHdmiMode();
        }
        return false;
    }

    @Override
    public ST_VIDEO_INFO getVideoInfo() {
        try {
            VideoInfo snvideoinfo = null;
            if (TvManager.getInstance() != null) {
                snvideoinfo = TvManager.getInstance().getPlayerManager().getVideoInfo();
            }
            videoInfo.s16FrameRate = (short) (snvideoinfo.frameRate);
            videoInfo.s16HResolution = (short) (snvideoinfo.hResolution);
            videoInfo.s16VResolution = (short) (snvideoinfo.vResolution);
            videoInfo.s16ModeIndex = (short) (snvideoinfo.modeIndex);
            videoInfo.enScanType = EN_SCAN_TYPE.values()[snvideoinfo.getScanType().ordinal()];
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return videoInfo;
    }

    @Override
    public boolean setDisplayHolder(SurfaceHolder sh) {
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getPlayerManager().setDisplay(sh);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void printfE(String sTag, String sMessage) {
        if (DEBUG_FLAG) {
            Log.e(sTag, sMessage);
        }
    }

    @Override
    public void printfE(String sMessage) {
        if (DEBUG_FLAG) {
            Log.e("TvApp", sMessage);
        }
    }

    @Override
    public void printfV(String sTag, String sMessage) {
        if (DEBUG_FLAG) {
            Log.v(sTag, sMessage);
        }
    }

    @Override
    public void printfV(String sMessage) {
        if (DEBUG_FLAG) {
            Log.v("TvApp", sMessage);
        }
    }

    @Override
    public void printfI(String sTag, String sMessage) {
        if (DEBUG_FLAG) {
            Log.i(sTag, sMessage);
        }
    }

    @Override
    public void printfI(String sMessage) {
        if (DEBUG_FLAG) {
            Log.i("TvApp", sMessage);
        }
    }

    @Override
    public void printfW(String sTag, String sMessage) {
        if (DEBUG_FLAG) {
            Log.w(sTag, sMessage);
        }
    }

    @Override
    public void printfW(String sMessage) {
        if (DEBUG_FLAG) {
            Log.w("TvApp", sMessage);
        }
    }

    @Override
    public boolean ExecSetInputSource(EnumInputSource st) {
        curSourceType = st;
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (CommonDeskImpl.this.getHandler(1) != null) {
                    CommonDeskImpl.this.getHandler(1).sendEmptyMessage(SETIS_START);
                    SetInputSource(curSourceType);
                    CommonDeskImpl.this.getHandler(1).sendEmptyMessage(SETIS_END_COMPLETE);
                }
            }
        }).start();
        return true;
    }

    private boolean bforcechangesource = false;

    private boolean execStartMsrv(EnumInputSource st, boolean forcechangesource) {
        curSourceType = st;
        bforcechangesource = forcechangesource;
        new Thread(new Runnable() {
            @Override
            public void run() {
                bThreadIsrunning = true;
                if (bforcechangesource) {
                    SetInputSource(curSourceType);
                }
                if (curSourceType == EnumInputSource.E_INPUT_SOURCE_ATV) {
                    ChannelDeskImpl.getChannelMgrInstance().changeToFirstService(
                            EnumFirstServiceInputType.E_FIRST_SERVICE_ATV,
                            EnumFirstServiceType.E_DEFAULT);
                } else if (curSourceType == EnumInputSource.E_INPUT_SOURCE_DTV) {
                    ChannelDeskImpl.getChannelMgrInstance().changeToFirstService(
                            EnumFirstServiceInputType.E_FIRST_SERVICE_DTV,
                            EnumFirstServiceType.E_DEFAULT);
                }
                bThreadIsrunning = false;
            }
        }).start();
        return true;
    }

    @Override
    public boolean startMsrv() {
        // TODO Auto-generated method stub
        EnumInputSource curInputSource = this.GetCurrentInputSource();
        DataBaseDeskImpl db = DataBaseDeskImpl.getDataBaseMgrInstance();
        curInputSource = EnumInputSource.values()[db.queryCurInputSrc()];
        // curInputSource =
        // DataBaseDeskImpl.getDataBaseMgrInstance().getUsrData().enInputSourceType;
        Log.d("TvApp", "5.Start Msrv------------GetCurrentInputSource:" + curInputSource);
        this.execStartMsrv(curInputSource, true);
        return false;
    }

    @Override
    public boolean enterSleepMode(boolean bMode, boolean bNoSignalPwDn) {
        try {
            Log.d("TvApp", "enterSleepMode:" + bMode);
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().enterSleepMode(bMode, bNoSignalPwDn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean setGpioDeviceStatus(int mGpio, boolean bEnable) {
        try {
            if (TvManager.getInstance() != null) {
                return TvManager.getInstance().setGpioDeviceStatus(mGpio, bEnable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setTimeZone(EnumTimeZone timezone, boolean isSaved) {
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getTimerManager().setTimeZone(timezone, isSaved);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disableTvosIr() {
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().disableTvosIr();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
