/**
 * MStar Software
 * Copyright (c) 2011 - 2012 MStar Semiconductor, Inc. All rights reserved.
 * <p/>
 * All software, firmware and related documentation herein (鈥淢Star Software鈥? are
 * intellectual property of MStar Semiconductor, Inc. (鈥淢Star鈥? and protected by
 * law, including, but not limited to, copyright law and international treaties.
 * Any use, modification, reproduction, retransmission, or republication of all
 * or part of MStar Software is expressly prohibited, unless prior written
 * permission has been granted by MStar.
 * <p/>
 * By accessing, browsing and/or using MStar Software, you acknowledge that you
 * have read, understood, and agree, to be bound by below terms (鈥淭erms鈥? and to
 * comply with all applicable laws and regulations:
 * <p/>
 * 1. MStar shall retain any and all right, ownership and interest to MStar
 * Software and any modification/derivatives thereof.  No right, ownership,
 * or interest to MStar Software and any modification/derivatives thereof is
 * transferred to you under Terms.
 * <p/>
 * 2. You understand that MStar Software might include, incorporate or be supplied
 * together with third party鈥檚 software and the use of MStar Software may require
 * additional licenses from third parties.  Therefore, you hereby agree it is your
 * sole responsibility to separately obtain any and all third party right and
 * license necessary for your use of such third party鈥檚 software.
 * <p/>
 * 3. MStar Software and any modification/derivatives thereof shall be deemed as
 * MStar鈥檚 confidential information and you agree to keep MStar鈥檚 confidential
 * information in strictest confidence and not disclose to any third party.
 * <p/>
 * 4. MStar Software is provided on an 鈥淎S IS鈥?basis without warranties of any kind.
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
 * <p/>
 * 5. If requested, MStar may from time to time provide technical supports or
 * services in relation with MStar Software to you for your use of MStar Software
 * in conjunction with your or your customer鈥檚 product (鈥淪ervices鈥?.  You understand
 * and agree that, except otherwise agreed by both parties in writing, Services are
 * provided on an 鈥淎S IS鈥?basis and the warranty disclaimer set forth in Section 4
 * above shall apply.
 * <p/>
 * 6. Nothing contained herein shall be construed as by implication, estoppels or
 * otherwise: (a) conferring any license or right to use MStar name, trademark,
 * service mark, symbol or any other identification; (b) obligating MStar or any
 * of its affiliates to furnish any person, including without limitation, you and
 * your customers, any assistance of any kind whatsoever, or any information; or
 * (c) conferring any license or right under any intellectual property right.
 * <p/>
 * 7. These terms shall be governed by and construed in accordance with the laws
 * of Taiwan, R.O.C., excluding its conflict of law rules.  Any and all dispute
 * arising out hereof or related hereto shall be finally settled by arbitration
 * referred to the Chinese Arbitration Association, Taipei in accordance with
 * the ROC Arbitration Law and the Arbitration Rules of the Association by three (3)
 * arbitrators appointed in accordance with the said Rules.  The place of
 * arbitration shall be in Taipei, Taiwan and the language shall be English.
 * The arbitration award shall be final and binding to both parties.
 */
package cn.shine.sdk.tv;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.mstar.android.tvapi.common.vo.TvOsType.EnumInputSource;
import com.mstar.android.tvapi.common.vo.EnumFirstServiceInputType;
import com.mstar.android.tvapi.common.vo.EnumFirstServiceType;
import com.mstar.android.tvapi.common.vo.EnumProgramCountType;
import com.mstar.android.tvapi.dtv.dvb.dvbc.vo.EnumCabConstelType;
import com.mstar.android.tvapi.dtv.dvb.vo.DvbMuxInfo;
import com.mstar.tvsettingservice.CaDesk;
import com.mstar.tvsettingservice.ChannelDesk;
import com.mstar.tvsettingservice.ChannelDesk.EN_TUNING_SCAN_TYPE;
import com.mstar.tvsettingservice.CiDesk;
import com.mstar.tvsettingservice.CommonDesk;
import com.mstar.tvsettingservice.DataBaseDesk;
import com.mstar.tvsettingservice.DemoDesk;
import com.mstar.tvsettingservice.DtvInterface.DTV_SCAN_EVENT.EN_SCAN_RET_STATUS;
import com.mstar.tvsettingservice.DtvInterface.DvbcScanParam;
import com.mstar.tvsettingservice.DtvInterface.EN_ANTENNA_TYPE;
import com.mstar.tvsettingservice.EpgDesk;
import com.mstar.tvsettingservice.PictureDesk;
import com.mstar.tvsettingservice.PvrDesk;
import com.mstar.tvsettingservice.S3DDesk;
import com.mstar.tvsettingservice.SettingDesk;
import com.mstar.tvsettingservice.SoundDesk;
import com.mstar.tvsettingservice.TvDeskProvider;
import com.mstar.tvsrvfunc.CaDeskImpl;
import com.mstar.tvsrvfunc.ChannelDeskImpl;
import com.mstar.tvsrvfunc.CiDeskImpl;
import com.mstar.tvsrvfunc.CommonDeskImpl;
import com.mstar.tvsrvfunc.DataBaseDeskImpl;
import com.mstar.tvsrvfunc.DemoDeskImpl;
import com.mstar.tvsrvfunc.EpgDeskImpl;
import com.mstar.tvsrvfunc.PictureDeskImpl;
import com.mstar.tvsrvfunc.PvrDeskImpl;
import com.mstar.tvsrvfunc.S3DDeskImpl;
import com.mstar.tvsrvfunc.SettingDeskImpl;
import com.mstar.tvsrvfunc.SoundDeskImpl;

import cn.shine.sdk.tv.listener.ScanListener;
import cn.shine.sdk.util.Logger;

class Channeltuning {
    private static int ATV_MIN_FREQ = 48250;
    private static int ATV_MAX_FREQ = 877250;
    private static int ATV_EVENTINTERVAL = 500 * 1000;// every 500ms to show
    private static int dtvServiceCount = 0;
    private MyHandler myHandler;
    private ChannelDesk commonSkin;
    TvDeskProvider serviceProvider;
    boolean isDtvAutoUpdateScan;
    private Context mContext;
    private ChannelDesk ts;
    ScanListener mListener;
    ChannelDesk.EN_TUNING_SCAN_TYPE scantype;

    public void start(Context context, int[] data, boolean isDtvAutoUpdateScan, ScanListener listener, ChannelDesk.EN_TUNING_SCAN_TYPE scantype) {
        Logger.e("isDtvAutoUpdateScan: " + isDtvAutoUpdateScan);
        mListener = listener;
        mContext = context;
        this.scantype = scantype;
        this.isDtvAutoUpdateScan = isDtvAutoUpdateScan;
        myHandler = new MyHandler();
        initTvDeskProvider();
        serviceProvider.getCommonManagerInstance().setHandler(myHandler, 1);
        serviceProvider.getDataBaseManagerInstance().openDB();

        ts = serviceProvider.getChannelManagerInstance();

        commonSkin = serviceProvider.getChannelManagerInstance();

        dtvServiceCount = 0;

        if (isDtvAutoUpdateScan) {
            scanDtvAutoUpdate();
        } else if (scantype == ChannelDesk.EN_TUNING_SCAN_TYPE.E_SCAN_ALL
                || scantype == ChannelDesk.EN_TUNING_SCAN_TYPE.E_SCAN_DTV) {
            scanDtv(data);
        } else {
            Logger.e("scan atv");
            String str = "0%49.25";
            ts.atvSetAutoTuningStart(ATV_EVENTINTERVAL, ATV_MIN_FREQ,
                    ATV_MAX_FREQ);
        }
    }

    private void scanDtv(int[] data) {
        Logger.e("scanDtv");
        if (ts.dtvGetAntennaType() == EN_ANTENNA_TYPE.E_ROUTE_DVBC) {
            Logger.e("switchMSrvDtvRouteCmd 1");
            DvbcScanParam sp = new DvbcScanParam();
            ts.switchMSrvDtvRouteCmd((short) EN_ANTENNA_TYPE.E_ROUTE_DVBC
                    .ordinal());
            ts.dvbcgetScanParam(sp);
            if (data != null) {
                sp.u32NITFrequency = data[0] * 1000;
                sp.QAM_Type = EnumCabConstelType.values()[data[1]];
                sp.u16SymbolRate = (short) data[2];
                ts.dvbcsetScanParam(sp.u16SymbolRate, sp.QAM_Type,
                        sp.u32NITFrequency, 905000, (short) 0x0000);
                ts.dtvAutoScan();

            } else {
                // FULL SCAN
                ts.dvbcsetScanParam(sp.u16SymbolRate, sp.QAM_Type,
                        sp.u32NITFrequency, 0, (short) 0x0000);
                ts.dtvFullScan();
            }
        } else if (ts.dtvGetAntennaType() == EN_ANTENNA_TYPE.E_ROUTE_DVBT) {
            Logger.e("switchMSrvDtvRouteCmd 2");
            ts.switchMSrvDtvRouteCmd((short) EN_ANTENNA_TYPE.E_ROUTE_DVBT
                    .ordinal());
            ts.dtvAutoScan();
        } else {
            Logger.e("switchMSrvDtvRouteCmd 0");
            ts.switchMSrvDtvRouteCmd((short) EN_ANTENNA_TYPE.E_ROUTE_DTMB
                    .ordinal());
            ts.dtvAutoScan();
        }
    }

    private void scanDtvAutoUpdate() {
        Logger.e("scanDtvAutoUpdate");
        int m_nServiceNum = ts
                .getProgramCount(EnumProgramCountType.E_COUNT_DTV);
        DvbcScanParam sp = new DvbcScanParam();
        ts.switchMSrvDtvRouteCmd((short) EN_ANTENNA_TYPE.E_ROUTE_DVBC.ordinal());
        ts.dvbcgetScanParam(sp);

        if (m_nServiceNum > 0) {
            DvbMuxInfo dmi = ts.getCurrentMuxInfo();
            if (dmi != null) {
                sp.u32NITFrequency = dmi.frequency;
                sp.QAM_Type = EnumCabConstelType.values()[dmi.modulationMode];
                sp.u16SymbolRate = (short) dmi.symbRate;
                Logger.i("\ndmi.u32NITFrequencye: " + sp.u32NITFrequency);
                Logger.i("\ndmi.QAM_Type: " + sp.QAM_Type);
                Logger.i("\ndmi.u16SymbolRate: " + sp.u16SymbolRate);
                ts.setUserScanType(EN_TUNING_SCAN_TYPE.E_SCAN_DTV);
                ts.dvbcsetScanParam(sp.u16SymbolRate, sp.QAM_Type,
                        sp.u32NITFrequency, 0, (short) 0x0000);
                ts.startQuickScan();
            } else {
                Logger.e("getCurrentMuxInfo error");
            }
        } else {
            Logger.e("m_nServiceNum = 0");
        }
    }

    public void pause() {
        // serviceProvider.getCommonManagerInstance().releaseHandler(1);
        serviceProvider.getDataBaseManagerInstance().closeDB();
    }

    public void stop() {
        serviceProvider.getCommonManagerInstance().releaseHandler(1);
        channetuningActivityLeave();
        pauseChannelTuning();
    }

    public void resume() {
        serviceProvider.getDataBaseManagerInstance().openDB();
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            ChannelDesk ts = serviceProvider.getChannelManagerInstance();
            if (what == EnumInputSource.E_INPUT_SOURCE_ATV.ordinal()) {
                Bundle b = msg.getData();
                String str = new String();
                int percent = b.getInt("percent");
                int frequencyKHz = b.getInt("frequency");
                int scannedChannelNum = b.getInt("scanNum");
                mListener.onScanChanged(0, scannedChannelNum, percent,
                        frequencyKHz);
                // double df= frequencyKHz/1000;
                str = "" + scannedChannelNum;
                String sFreq = " " + (frequencyKHz / 1000) + "."
                        + (frequencyKHz % 1000) / 10 + "Mhz";
                str = "" + percent + '%' + sFreq;
                if ((percent >= 100) || (frequencyKHz > ATV_MAX_FREQ)) {
                    ts.atvSetAutoTuningEnd();
                    if (scantype == ChannelDesk.EN_TUNING_SCAN_TYPE.E_SCAN_ALL) {
                        Logger.d("@@@@@@@@@@@@@start makeSourceDtv");
                        if (dtvServiceCount > 0) {
                            ts.makeSourceDtv();
                            ts.changeToFirstService(
                                    EnumFirstServiceInputType.E_FIRST_SERVICE_DTV,
                                    EnumFirstServiceType.E_DEFAULT);
                        } else {
                            Logger.d("@@@@@@@@@@@@@start makeSourceAtv");
                            ts.makeSourceAtv();
                            ts.changeToFirstService(
                                    EnumFirstServiceInputType.E_FIRST_SERVICE_ATV,
                                    EnumFirstServiceType.E_DEFAULT);
                        }
                    } else {
                        Logger.d("@@@@@@@@@@@@@start makeSourceAtv");
                        ts.makeSourceAtv();
                        ts.changeToFirstService(
                                EnumFirstServiceInputType.E_FIRST_SERVICE_ATV,
                                EnumFirstServiceType.E_DEFAULT);
                    }
                    channetuningActivityExit();
                }
            } else if (what == EnumInputSource.E_INPUT_SOURCE_DTV.ordinal()) {
                Bundle b = msg.getData();
                String str;
                int dtv = b.getInt("dtvSrvCount");
                int radio = b.getInt("radioSrvCount");
                int data = b.getInt("dataSrvCount");
                int percent = b.getInt("percent");
                int scan_status = b.getInt("scanstatus");
                mListener.onScanChanged(1, dtv, percent, 0);
                str = "" + dtv;
                str = "" + radio;
                str = "" + data;
                str = "" + percent + '%';
                if (scan_status == EN_SCAN_RET_STATUS.STATUS_SCAN_END.ordinal()) {
                    if (scantype == ChannelDesk.EN_TUNING_SCAN_TYPE.E_SCAN_ALL) {
                        dtvServiceCount = dtv + radio + data;
                        ts.atvSetAutoTuningStart(ATV_EVENTINTERVAL,
                                ATV_MIN_FREQ, ATV_MAX_FREQ);// go
                    } else if (ts.getUserScanType() == EN_TUNING_SCAN_TYPE.E_SCAN_DTV) {
                        ts.changeToFirstService(
                                EnumFirstServiceInputType.E_FIRST_SERVICE_DTV,
                                EnumFirstServiceType.E_DEFAULT);
                        if (isDtvAutoUpdateScan) {
                        } else {
                            channetuningActivityExit();
                        }
                    }
                }
            }
            super.handleMessage(msg);
        }
    }

    private void channetuningActivityLeave() {
        ChannelDesk ts = serviceProvider.getChannelManagerInstance();
        switch (ts.GetTsStatus()) {
            case E_TS_ATV_AUTO_TUNING:
                ts.atvSetAutoTuningPause();
                break;
            case E_TS_DTV_AUTO_TUNING:
            case E_TS_DTV_FULL_TUNING:
                ts.dtvPauseScan();
                break;
            default:
                break;
        }
    }

    private void channetuningActivityExit() {
        Logger.e("TvService", "channetuningActivityExit");
    }

    private void pauseChannelTuning() {
        ChannelDesk ts = serviceProvider.getChannelManagerInstance();
        switch (ts.GetTsStatus()) {
            case E_TS_ATV_SCAN_PAUSING:
                ts.atvSetAutoTuningEnd();
                ts.changeToFirstService(
                        EnumFirstServiceInputType.E_FIRST_SERVICE_ATV,
                        EnumFirstServiceType.E_DEFAULT);
                break;
            case E_TS_DTV_SCAN_PAUSING:
                ts.DtvStopScan();
                if (scantype == ChannelDesk.EN_TUNING_SCAN_TYPE.E_SCAN_ALL) {

                    boolean res = ts.atvSetAutoTuningStart(ATV_EVENTINTERVAL,
                            ATV_MIN_FREQ, ATV_MAX_FREQ);// go
                    if (res == false) {
                        Logger.e("TuningService", "atvSetAutoTuningStart Error!!!");
                    }
                } else {
                    ts.changeToFirstService(
                            EnumFirstServiceInputType.E_FIRST_SERVICE_DTV,
                            EnumFirstServiceType.E_DEFAULT);
                }
                break;
            default:
                break;
        }
    }

    private void initTvDeskProvider() {
        serviceProvider = new TvDeskProvider() {
            CommonDesk comManager = null;

            PictureDesk pictureManager = null;

            DataBaseDeskImpl dataBaseManager = null;

            SettingDesk settingManager = null;

            ChannelDesk channelManager = null;

            SoundDesk soundManager = null;

            S3DDesk s3dManager = null;

            DemoDesk demoManager = null;

            EpgDesk epgManager = null;

            PvrDesk pvrManager = null;

            CiDesk ciManager = null;
            CaDesk caManager = null;

            @Override
            public void initTvSrvProvider() {
            }

            @Override
            public CommonDesk getCommonManagerInstance() {
                comManager = CommonDeskImpl.getInstance();
                return comManager;
            }

            @Override
            public PictureDesk getPictureManagerInstance() {
                pictureManager = PictureDeskImpl.getPictureMgrInstance();
                return pictureManager;
            }

            @Override
            public DataBaseDesk getDataBaseManagerInstance() {
                DataBaseDeskImpl.setContext(mContext);
                dataBaseManager = DataBaseDeskImpl.getDataBaseMgrInstance();
                return dataBaseManager;
            }

            @Override
            public SettingDesk getSettingManagerInstance() {
                settingManager = SettingDeskImpl.getSettingMgrInstance();
                return settingManager;
            }

            @Override
            public ChannelDesk getChannelManagerInstance() {
                channelManager = ChannelDeskImpl.getChannelMgrInstance();
                return channelManager;
            }

            @Override
            public SoundDesk getSoundManagerInstance() {
                soundManager = SoundDeskImpl.getSoundMgrInstance();
                return soundManager;
            }

            @Override
            public S3DDesk getS3DManagerInstance() {
                s3dManager = S3DDeskImpl.getS3DMgrInstance();
                return s3dManager;
            }

            @Override
            public DemoDesk getDemoManagerInstance() {
                demoManager = DemoDeskImpl.getDemoMgrInstance();
                return demoManager;
            }

            @Override
            public EpgDesk getEpgManagerInstance() {
                epgManager = EpgDeskImpl.getEpgMgrInstance();
                return epgManager;
            }

            @Override
            public PvrDesk getPvrManagerInstance() {
                pvrManager = PvrDeskImpl.getPvrMgrInstance();
                return pvrManager;
            }

            @Override
            public CiDesk getCiManagerInstance() {
                ciManager = CiDeskImpl.getCiMgrInstance();
                return ciManager;
            }

            @Override
            public CaDesk getCaManagerInstance() {
                caManager = CaDeskImpl.getCaMgrInstance();
                return caManager;
            }
        };

    }

}