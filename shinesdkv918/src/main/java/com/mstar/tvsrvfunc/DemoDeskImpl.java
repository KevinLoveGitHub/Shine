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

import android.util.Log;

import com.mstar.android.tvapi.common.PictureManager;
import com.mstar.android.tvapi.common.TvManager;
import com.mstar.android.tvapi.common.vo.MweType.EnumMweType;
import com.mstar.android.tvapi.common.vo.NoiseReduction.EnumNoiseReduction;
import com.mstar.tvsettingservice.DataBaseDesk.EN_MS_NR;
import com.mstar.tvsettingservice.DataBaseDesk.T_MS_VIDEO;
import com.mstar.tvsettingservice.DemoDesk;

public class DemoDeskImpl implements DemoDesk {
    private EnumMweType e_MWE_TYPE = EnumMweType.E_OFF;

    private EN_MS_DBC_TYPE e_DBC_TYPE = EN_MS_DBC_TYPE.E_MS_DBC_OFF;

    private EN_MS_DCC_TYPE e_DCC_TYPE = EN_MS_DCC_TYPE.E_MS_DCC_OFF;

    private EN_MS_DLC_TYPE e_DLC_TYPE = EN_MS_DLC_TYPE.E_MS_DLC_ON;

    private EN_MS_UCLEAR_TYPE e_UCLEAR_TYPE = EN_MS_UCLEAR_TYPE.E_MS_UCLEAR_ON; // add

    private EnumNoiseReduction e_NR_TYPE = EnumNoiseReduction.E_NR_OFF;

    private static DemoDeskImpl demoMgrImpl;

    private static Thread mythread = null;

    private boolean bforceThreadSleep = true;

    PictureManager pm = TvManager.getInstance().getPictureManager();

    public static DemoDeskImpl getDemoMgrInstance() {
        if (demoMgrImpl == null)
            demoMgrImpl = new DemoDeskImpl();
        return demoMgrImpl;
    }

    private DemoDeskImpl() {
        /**
         * inital value show read from msrv from tv api
         */
        e_MWE_TYPE = EnumMweType.E_OFF;
        e_DBC_TYPE = EN_MS_DBC_TYPE.E_MS_DBC_OFF;
        e_DCC_TYPE = EN_MS_DCC_TYPE.E_MS_DCC_OFF;
        e_DLC_TYPE = EN_MS_DLC_TYPE.E_MS_DLC_ON;
        e_UCLEAR_TYPE = EN_MS_UCLEAR_TYPE.E_MS_UCLEAR_ON; // add
        e_NR_TYPE = EnumNoiseReduction.E_NR_OFF;
        if (mythread == null) {
            mythread = new MyThread();
            bforceThreadSleep = false;
            mythread.start();
        }
    }

    @Override
    public void setMWEStatus(EnumMweType eMWE) {
        e_MWE_TYPE = eMWE;
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getPictureManager().setDemoMode(eMWE);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public EnumMweType getMWEStatus() {
        // TODO Auto-generated method stub
        try {
            if (TvManager.getInstance() != null) {
                e_MWE_TYPE = TvManager.getInstance().getPictureManager().getDemoMode();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return e_MWE_TYPE;
    }

    @Override
    public void setDBCStatus(EN_MS_DBC_TYPE eDBC) {
        e_DBC_TYPE = eDBC;
        bforceThreadSleep = false;
        ReCntSt();
    }

    @Override
    public EN_MS_DBC_TYPE getDBCStatus() {
        // TODO Auto-generated method stub
        return e_DBC_TYPE;
    }

    @Override
    public void setDLCStatus(EN_MS_DLC_TYPE eDLC) {
        if (eDLC == EN_MS_DLC_TYPE.E_MS_DLC_ON) {
            try {
                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().getPictureManager().enableDlc();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            try {
                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().getPictureManager().disableDlc();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        e_DLC_TYPE = eDLC;
    }

    @Override
    public EN_MS_DLC_TYPE getDLCStatus() {
        // TODO Auto-generated method stub
        return e_DLC_TYPE;
    }

    @Override
    public void setDCCStatus(EN_MS_DCC_TYPE eDCC) {
        Log.d("TvApp", "setDCCStatus:" + eDCC);
        e_DCC_TYPE = eDCC;
        bforceThreadSleep = false;
        ReCntSt();
    }

    @Override
    public EN_MS_DCC_TYPE getDCCStatus() {
        // TODO Auto-generated method stub
        return e_DCC_TYPE;
    }

    @Override
    public void setUClearStatus(EN_MS_UCLEAR_TYPE eUClear) {
        if (eUClear == EN_MS_UCLEAR_TYPE.E_MS_UCLEAR_ON) {
            try {
                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().getPictureManager().setUltraClear(true);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            try {
                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().getPictureManager().setUltraClear(false);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        e_UCLEAR_TYPE = eUClear;
    }

    @Override
    public EN_MS_UCLEAR_TYPE getUClearStatus() {
        // TODO Auto-generated method stub
        return e_UCLEAR_TYPE;
    }

    @Override
    public void set3DNR(EnumNoiseReduction eNR) {
        try {
            T_MS_VIDEO stVedioTemp;
            stVedioTemp = DataBaseDeskImpl.getDataBaseMgrInstance().getVideo();
            int ct_index = stVedioTemp.astPicture[stVedioTemp.ePicture.ordinal()].eColorTemp
                    .ordinal();
            stVedioTemp.eNRMode[ct_index].eNR = EN_MS_NR.values()[ct_index];
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getPictureManager().setNoiseReduction(eNR);
            }
            // int inputSrcType =
            // CommonDeskImpl.getInstance().GetCurrentInputSource().ordinal();
            // DataBaseDeskImpl.getDataBaseMgrInstance().updateVideoNRMode(stVedioTemp.eNRMode[ct_index],inputSrcType
            // , ct_index);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        e_NR_TYPE = eNR;
    }

    @Override
    public EnumNoiseReduction get3DNRStatus() {
        // TODO Auto-generated method stub
        T_MS_VIDEO stVedioTemp;
        stVedioTemp = DataBaseDeskImpl.getDataBaseMgrInstance().getVideo();
        int ct_index = stVedioTemp.astPicture[stVedioTemp.ePicture.ordinal()].eColorTemp.ordinal();
        int db_nrmode = stVedioTemp.eNRMode[ct_index].eNR.ordinal();
        if (db_nrmode < EnumNoiseReduction.E_NR_NUM.getValue()) {
            e_NR_TYPE = EnumNoiseReduction.values()[db_nrmode];
        } else {
            Log.e("Tvapp", "get3DNRStatus error:" + db_nrmode);
        }
        return e_NR_TYPE;
    }

    private final static int cmvalue1 = 0x11;

    private final static int cmvalue2 = 0x30;

    private final static int cmvalue3 = 0x58;

    private final static int cmvalue4 = 0x70;

    private final static int cmvalue5 = 0x85;

    private final static int cmvalue6 = 0xa0;

    private final static int cmvalue7 = 0xf0;

    private final static int cmvalue8 = 0xff;

    private final static int PBLV1 = 20;

    private final static int PBLV2 = 40;

    private final static int PBLV3 = 60;

    private final static int PBLV4 = 80;

    private final static int PBLV5 = 100;

    private int getrandom() {
        return (int) (Math.random() * 1234);
    }

    private int mapi_GetRealValue(int MaxLVal, int MinLVal, int ADVal, int MaxADVal, int MinADVal) {
        int iu8Loop_i = 0;
        int denominator = MaxADVal - MinADVal;

        if (denominator == 0) {
            denominator = 1;
        }

        iu8Loop_i = (int) (ADVal - MinADVal) * (MaxLVal - MinLVal) / denominator;

        return iu8Loop_i;
    }

    private int mapi_GetImageBackLight() {
        int ucBacklight, ucTmp = 0;
        // ucTmp = getrandom() % 100;
        try {
            if (TvManager.getInstance() != null) {
                ucTmp = TvManager.getInstance().getPictureManager().getDlcAverageLuma();
            }
            Log.d("TvApp", "getDlcAverageLuma:" + ucTmp);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (ucTmp < cmvalue1)
            ucBacklight = PBLV1 - mapi_GetRealValue(PBLV2, PBLV1, ucTmp, cmvalue1, 0);
        else if (ucTmp < cmvalue2)
            ucBacklight = PBLV2 - mapi_GetRealValue(PBLV3, PBLV2, ucTmp, cmvalue2, cmvalue1);
        else if (ucTmp < cmvalue3)
            ucBacklight = PBLV3 - mapi_GetRealValue(PBLV4, PBLV3, ucTmp, cmvalue3, cmvalue2);
        else if (ucTmp < cmvalue4)
            ucBacklight = PBLV4 - mapi_GetRealValue(PBLV5, PBLV4, ucTmp, cmvalue4, cmvalue3);
        else if (ucTmp < cmvalue5)
            ucBacklight = PBLV5 + mapi_GetRealValue(PBLV5, PBLV4, ucTmp, cmvalue5, cmvalue4);
        else if (ucTmp < cmvalue6)
            ucBacklight = PBLV4 + mapi_GetRealValue(PBLV4, PBLV3, ucTmp, cmvalue6, cmvalue6);
        else if (ucTmp < cmvalue7)
            ucBacklight = PBLV3 + mapi_GetRealValue(PBLV3, PBLV2, ucTmp, cmvalue7, cmvalue6);
        else
            ucBacklight = PBLV2 + mapi_GetRealValue(PBLV2, PBLV1, ucTmp, cmvalue8, cmvalue7);
        return ucBacklight;
    }

    private void dbchandler() {
        int value = mapi_GetImageBackLight();
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getPictureManager().setBacklight(value);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.d("TvApp", "dbchandler:" + value);
    }

    private void dcchandler() {
        int value = mapi_GetImageBackLight();
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getPictureManager().setPictureModeContrast((short) value);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.d("TvApp", "dcchandler:" + value);
    }

    private static int sleeptimeCnt = 500;

    private void ReCntSt() {
        if (e_DBC_TYPE == EN_MS_DBC_TYPE.E_MS_DBC_OFF && e_DCC_TYPE == EN_MS_DCC_TYPE.E_MS_DCC_OFF) {
            sleeptimeCnt = 2000;
        } else if (e_DBC_TYPE == EN_MS_DBC_TYPE.E_MS_DBC_ON
                && e_DCC_TYPE == EN_MS_DCC_TYPE.E_MS_DCC_ON) {
            sleeptimeCnt = 500;
        } else {
            sleeptimeCnt = 1000;
        }
    }

    class MyThread extends Thread {
        public void run() {
            while (true) {
                if (bforceThreadSleep) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else {
                    if (e_DBC_TYPE == EN_MS_DBC_TYPE.E_MS_DBC_ON) {
                        dbchandler();
                    }
                    if (e_DCC_TYPE == EN_MS_DCC_TYPE.E_MS_DCC_ON) {
                        dcchandler();
                    }
                    try {
                        Thread.sleep(sleeptimeCnt);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void forceThreadSleep(boolean flag) {
        // TODO Auto-generated method stub
        bforceThreadSleep = flag;
    }
}
