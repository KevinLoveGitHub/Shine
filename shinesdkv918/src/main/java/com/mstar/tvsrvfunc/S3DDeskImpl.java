//<MStar Software>
//******************************************************************************
// MStar Software
// Copyright (c) 2010 - 2012 MStar Semiconductor, Inc. All rights reserved.
// All software, firmware and related documentation herein ("MStar Software") are
// intellectual property of MStar Semiconductor, Inc. ("MStar") and protected by
// law, including, but not limited to, copyright law and international treaties.
// Any use, modification, reproduction, retransmission, or republication of all
// or part of MStar Software is expressly prohibited, unless prior written
// permission has been granted by MStar.
//
// By accessing, browsing and/or using MStar Software, you acknowledge that you
// have read, understood, and agree, to be bound by below terms ("Terms") and to
// comply with all applicable laws and regulations:
//
// 1. MStar shall retain any and all right, ownership and interest to MStar
//    Software and any modification/derivatives thereof.
//    No right, ownership, or interest to MStar Software and any
//    modification/derivatives thereof is transferred to you under Terms.
//
// 2. You understand that MStar Software might include, incorporate or be
//    supplied together with third party's software and the use of MStar
//    Software may require additional licenses from third parties.
//    Therefore, you hereby agree it is your sole responsibility to separately
//    obtain any and all third party right and license necessary for your use of
//    such third party's software.
//
// 3. MStar Software and any modification/derivatives thereof shall be deemed as
//    MStar's confidential information and you agree to keep MStar's
//    confidential information in strictest confidence and not disclose to any
//    third party.
//
// 4. MStar Software is provided on an "AS IS" basis without warranties of any
//    kind. Any warranties are hereby expressly disclaimed by MStar, including
//    without limitation, any warranties of merchantability, non-infringement of
//    intellectual property rights, fitness for a particular purpose, error free
//    and in conformity with any international standard.  You agree to waive any
//    claim against MStar for any loss, damage, cost or expense that you may
//    incur related to your use of MStar Software.
//    In no event shall MStar be liable for any direct, indirect, incidental or
//    consequential damages, including without limitation, lost of profit or
//    revenues, lost or damage of data, and unauthorized system use.
//    You agree that this Section 4 shall still apply without being affected
//    even if MStar Software has been modified by MStar in accordance with your
//    request or instruction for your use, except otherwise agreed by both
//    parties in writing.
//
// 5. If requested, MStar may from time to time provide technical supports or
//    services in relation with MStar Software to you for your use of
//    MStar Software in conjunction with your or your customer's product
//    ("Services").
//    You understand and agree that, except otherwise agreed by both parties in
//    writing, Services are provided on an "AS IS" basis and the warranty
//    disclaimer set forth in Section 4 above shall apply.
//
// 6. Nothing contained herein shall be construed as by implication, estoppels
//    or otherwise:
//    (a) conferring any license or right to use MStar name, trademark, service
//        mark, symbol or any other identification;
//    (b) obligating MStar or any of its affiliates to furnish any person,
//        including without limitation, you and your customers, any assistance
//        of any kind whatsoever, or any information; or
//    (c) conferring any license or right under any intellectual property right.
//
// 7. These terms shall be governed by and construed in accordance with the laws
//    of Taiwan, R.O.C., excluding its conflict of law rules.
//    Any and all dispute arising out hereof or related hereto shall be finally
//    settled by arbitration referred to the Chinese Arbitration Association,
//    Taipei in accordance with the ROC Arbitration Law and the Arbitration
//    Rules of the Association by three (3) arbitrators appointed in accordance
//    with the said Rules.
//    The place of arbitration shall be in Taipei, Taiwan and the language shall
//    be English.
//    The arbitration award shall be final and binding to both parties.
//
//******************************************************************************
//<MStar Software>

package com.mstar.tvsrvfunc;

import android.os.Handler;
import android.util.Log;

import com.mstar.android.tvapi.common.TvManager;
import com.mstar.android.tvapi.common.vo.Enum3dAspectRatioType;
import com.mstar.android.tvapi.common.vo.Enum3dType;
import com.mstar.android.tvapi.common.vo.EnumScalerWindow;
import com.mstar.android.tvapi.common.vo.EnumScreenMuteType;
import com.mstar.android.tvapi.common.vo.TvOsType.EnumInputSource;
import com.mstar.tvsettingservice.CommonDesk;
import com.mstar.tvsettingservice.DataBaseDesk.EN_ThreeD_Video_3DDEPTH;
import com.mstar.tvsettingservice.DataBaseDesk.EN_ThreeD_Video_3DOFFSET;
import com.mstar.tvsettingservice.DataBaseDesk.EN_ThreeD_Video_3DOUTPUTASPECT;
import com.mstar.tvsettingservice.DataBaseDesk.EN_ThreeD_Video_3DTO2D;
import com.mstar.tvsettingservice.DataBaseDesk.EN_ThreeD_Video_AUTOSTART;
import com.mstar.tvsettingservice.DataBaseDesk.EN_ThreeD_Video_DISPLAYFORMAT;
import com.mstar.tvsettingservice.DataBaseDesk.EN_ThreeD_Video_LRVIEWSWITCH;
import com.mstar.tvsettingservice.DataBaseDesk.EN_ThreeD_Video_SELFADAPTIVE_DETECT;
import com.mstar.tvsettingservice.DataBaseDesk.ThreeD_Video_MODE;
import com.mstar.tvsettingservice.S3DDesk;

public class S3DDeskImpl extends BaseDeskImpl implements S3DDesk {
    private static S3DDeskImpl s3DMgrImpl;

    private DataBaseDeskImpl databaseMgrImpl = null;

    private ThreeD_Video_MODE ThreeDSetting = null;

    private CommonDesk com = null;

    private EN_ThreeD_Video_SELFADAPTIVE_DETECT selAdaptDetectMode = null;

    private Thread SelfAdaptDetectThread = null;

    private Enum3dType _3dtype_detect = Enum3dType.EN_3D_NONE;

    private Enum3dType _3dtype_detect_temp = Enum3dType.EN_3D_NONE;

    private int _3dtype_counter = 0;

    private final int _3dtype_detect_realtime_Threshold = 5;

    private final int _3dtype_detect_realtime_time_internal = 200;

    private Handler handler = new Handler();

    private S3DDeskImpl() {
        com = CommonDeskImpl.getInstance();
        com.printfI("TvService", "S3DManagerImpl constructor!!");
        databaseMgrImpl = DataBaseDeskImpl.getDataBaseMgrInstance();
        ThreeDSetting = databaseMgrImpl.getVideo().ThreeDVideoMode;
        selAdaptDetectMode = EN_ThreeD_Video_SELFADAPTIVE_DETECT.DB_ThreeD_Video_SELF_ADAPTIVE_DETECT_OFF;
        _3dtype_detect = Enum3dType.EN_3D_NONE;
        _3dtype_detect_temp = Enum3dType.EN_3D_NONE;
    }

    public static S3DDeskImpl getS3DMgrInstance() {
        if (s3DMgrImpl == null)
            s3DMgrImpl = new S3DDeskImpl();
        return s3DMgrImpl;
    }

    @Override
    public boolean setSelfAdaptiveDetect(EN_ThreeD_Video_SELFADAPTIVE_DETECT selfAdaptiveDetect) {
        selAdaptDetectMode = selfAdaptiveDetect;
        if (selfAdaptiveDetect != EN_ThreeD_Video_SELFADAPTIVE_DETECT.DB_ThreeD_Video_SELF_ADAPTIVE_DETECT_REALTIME) {
            if (SelfAdaptDetectThread != null) {
                SelfAdaptDetectThread.interrupt();
                _3dtype_detect = Enum3dType.EN_3D_NONE;
                _3dtype_counter = 0;
                _3dtype_detect_temp = Enum3dType.EN_3D_NONE;
                SelfAdaptDetectThread = null;
            }
        }
        if (selfAdaptiveDetect == EN_ThreeD_Video_SELFADAPTIVE_DETECT.DB_ThreeD_Video_SELF_ADAPTIVE_DETECT_OFF) {
            ThreeDSetting.eThreeDVideoSelfAdaptiveDetect = selfAdaptiveDetect;
            ThreeDSetting.eThreeDVideoDisplayFormat = EN_ThreeD_Video_DISPLAYFORMAT.DB_ThreeD_Video_DISPLAYFORMAT_NONE;
        } else {
            ThreeDSetting.eThreeDVideoSelfAdaptiveDetect = selfAdaptiveDetect;
            ThreeDSetting.eThreeDVideoDisplayFormat = EN_ThreeD_Video_DISPLAYFORMAT.DB_ThreeD_Video_DISPLAYFORMAT_AUTO;
        }

        databaseMgrImpl.updateVideo3DAdaptiveDetectMode(
                ThreeDSetting.eThreeDVideoSelfAdaptiveDetect,
                EnumInputSource.E_INPUT_SOURCE_HDMI.ordinal());
        databaseMgrImpl.updateVideo3DDisplayFormat(ThreeDSetting.eThreeDVideoDisplayFormat, com
                .GetCurrentInputSource().ordinal());
        // databaseMgrImpl.updateVideo3DMode(ThreeDSetting,
        // EnumInputSource.E_INPUT_SOURCE_HDMI.ordinal());
        // com.printfI("TvS3DManagerIml", "Self Adaptive Detect is "
        // + ThreeDSetting.eThreeDVideoSelfAdaptiveLevel);

        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().setVideoMute(true, EnumScreenMuteType.E_BLACK, 0,
                        com.GetCurrentInputSource());
            }
        } catch (Exception e) {
            com.printfE("S3DDeskImpl", "setVideoMute False Exception");
            e.printStackTrace();
        }
        if (selfAdaptiveDetect == EN_ThreeD_Video_SELFADAPTIVE_DETECT.DB_ThreeD_Video_SELF_ADAPTIVE_DETECT_WHEN_SOURCE_CHANGE) {
            try {
                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().getThreeDimensionManager().set3dFormatDetectFlag(true);
                }
            } catch (Exception e) {
                com.printfE("S3DDeskImpl", "Set set3dFormatDetectFlag True Exception");
                e.printStackTrace();
            }
        } else if (selfAdaptiveDetect == EN_ThreeD_Video_SELFADAPTIVE_DETECT.DB_ThreeD_Video_SELF_ADAPTIVE_DETECT_RIGHT_NOW) {
            if (com.isSignalStable()) {
                com.printfE("S3DDeskImpl", "Signal Stable!!!");
                try {
                    if (TvManager.getInstance() != null) {
                        Enum3dType _3dtype = null;
                        _3dtype = TvManager.getInstance().getThreeDimensionManager()
                                .detect3dFormat(EnumScalerWindow.E_MAIN_WINDOW);
                        com.printfE("S3DDeskImpl", "Detect 3D formate is" + _3dtype);
                        TvManager.getInstance().getThreeDimensionManager().enable3d(_3dtype);
                        TvManager.getInstance().getThreeDimensionManager()
                                .set3dFormatDetectFlag(true);
                    }
                } catch (Exception e) {
                    com.printfE("S3DDeskImpl", "Set set3dFormatDetectFlag True Exception");
                    e.printStackTrace();
                }
            } else {
                com.printfE("S3DDeskImpl", "Signal Not Stable!!!");
                try {
                    if (TvManager.getInstance() != null) {
                        TvManager.getInstance().getThreeDimensionManager()
                                .set3dFormatDetectFlag(true);
                    }
                } catch (Exception e) {
                    com.printfE("S3DDeskImpl", "Set set3dFormatDetectFlag True Exception");
                    e.printStackTrace();
                }
            }
        } else if (selfAdaptiveDetect == EN_ThreeD_Video_SELFADAPTIVE_DETECT.DB_ThreeD_Video_SELF_ADAPTIVE_DETECT_REALTIME) {
            _3dtype_counter = 0;
            if (SelfAdaptDetectThread == null) {
                SelfAdaptDetectThread = new Thread() {
                    public void run() {
                        while (selAdaptDetectMode == EN_ThreeD_Video_SELFADAPTIVE_DETECT.DB_ThreeD_Video_SELF_ADAPTIVE_DETECT_REALTIME) {
                            if (com.isSignalStable()) {
                                com.printfE("S3DDeskImpl", "Signal Stable!!!");
                                try {
                                    if (TvManager.getInstance() != null) {
                                        Enum3dType _3dtype = Enum3dType.EN_3D_NONE;
                                        _3dtype = TvManager.getInstance()
                                                .getThreeDimensionManager()
                                                .detect3dFormat(EnumScalerWindow.E_MAIN_WINDOW);
                                        com.printfE("S3DDeskImpl", "Detect 3D formate is" + _3dtype);
                                        if (_3dtype_detect != _3dtype) {
                                            if (_3dtype_counter == 0) {
                                                _3dtype_detect_temp = _3dtype;
                                                _3dtype_counter++;
                                            } else if (_3dtype_counter == _3dtype_detect_realtime_Threshold) {
                                                try {
                                                    if (TvManager.getInstance() != null) {
                                                        TvManager.getInstance().setVideoMute(true,
                                                                EnumScreenMuteType.E_BLACK, 0,
                                                                com.GetCurrentInputSource());
                                                    }
                                                } catch (Exception e) {
                                                    com.printfE("S3DDeskImpl",
                                                            "setVideoMute False Exception");
                                                    e.printStackTrace();
                                                }
                                                TvManager.getInstance().getThreeDimensionManager()
                                                        .enable3d(_3dtype);
                                                TvManager.getInstance().getThreeDimensionManager()
                                                        .set3dFormatDetectFlag(true);
                                                _3dtype_detect = _3dtype;
                                                _3dtype_detect_temp = Enum3dType.EN_3D_NONE;
                                                _3dtype_counter = 0;
                                                try {
                                                    if (TvManager.getInstance() != null) {
                                                        TvManager.getInstance().setVideoMute(false,
                                                                EnumScreenMuteType.E_BLACK, 0,
                                                                com.GetCurrentInputSource());
                                                    }
                                                } catch (Exception e) {
                                                    com.printfE("S3DDeskImpl",
                                                            "setVideoMute False Exception");
                                                    e.printStackTrace();
                                                }
                                            } else if (_3dtype_detect_temp == _3dtype) {
                                                _3dtype_counter++;
                                                Log.v("S3DDeskImpl", "Detect 3D formate is"
                                                        + _3dtype + "timer is " + _3dtype_counter);
                                            } else {
                                                _3dtype_detect = Enum3dType.EN_3D_NONE;
                                                _3dtype_counter = 0;
                                            }
                                        } else {
                                            Log.v("S3DDeskImpl", "3D formate not change");
                                        }
                                    }
                                } catch (Exception e) {
                                    com.printfE("S3DDeskImpl",
                                            "Set set3dFormatDetectFlag True Exception");
                                    e.printStackTrace();
                                }
                            } else {
                                com.printfE("S3DDeskImpl", "Signal Not Stable!!!");
                                try {
                                    if (TvManager.getInstance() != null) {
                                        TvManager.getInstance().getThreeDimensionManager()
                                                .set3dFormatDetectFlag(true);
                                    }
                                } catch (Exception e) {
                                    com.printfE("S3DDeskImpl",
                                            "Set set3dFormatDetectFlag True Exception");
                                    e.printStackTrace();
                                }
                            }
                            try {
                                Thread.sleep(_3dtype_detect_realtime_time_internal);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                        }
                    };
                };
                SelfAdaptDetectThread.start();
            }

        } else {
            // if(selfAdaptiveDetect ==
            // EN_ThreeD_Video_SELFADAPTIVE_DETECT.DB_ThreeD_Video_SELF_ADAPTIVE_DETECT_OFF)
            try {
                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().getThreeDimensionManager().set3dFormatDetectFlag(false);
                    TvManager.getInstance().getThreeDimensionManager()
                            .enable3d(Enum3dType.EN_3D_NONE);
                }

            } catch (Exception e) {
                com.printfE("S3DDeskImpl", "Set set3dFormatDetectFlag False Exception");
                e.printStackTrace();
            }
        }
        handler.removeCallbacks(resetVideoMute);
        handler.postDelayed(resetVideoMute, 250);// black for more 0.25s
        return true;
    }

    private Runnable resetVideoMute = new Runnable() {
        public void run() {
            try {
                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().setVideoMute(false, EnumScreenMuteType.E_BLACK, 0,
                            com.GetCurrentInputSource());
                }
            } catch (Exception e) {
                com.printfE("S3DDeskImpl", "setVideoMute False Exception");
                e.printStackTrace();
            }
        }
    };

    @Override
    public EN_ThreeD_Video_SELFADAPTIVE_DETECT getSelfAdaptiveDetect() {
        return ThreeDSetting.eThreeDVideoSelfAdaptiveDetect;
    }

    // @Override
    // public boolean setSelfAdaptiveLevel(EN_ThreeD_Video_SELFADAPTIVE_LEVEL
    // selfAdaptiveLevel)
    // {
    // // ThreeDSetting.eThreeDVideoSelfAdaptiveLevel = selfAdaptiveLevel;
    // // databaseMgrImpl.updateVideo3DMode(ThreeDSetting,
    // com.GetCurrentInputSource().ordinal());
    // // com.printfI("TvS3DManagerIml", "Self Adaptive Level is "
    // // + ThreeDSetting.eThreeDVideoSelfAdaptiveLevel);
    // return true;
    // }
    //
    // @Override
    // public EN_ThreeD_Video_SELFADAPTIVE_LEVEL getSelfAdaptiveLevel()
    // {
    // //return ThreeDSetting.eThreeDVideoSelfAdaptiveLevel;
    // return
    // EN_ThreeD_Video_SELFADAPTIVE_LEVEL.DB_ThreeD_Video_DISPLAYFORMAT_COUNT;
    // }

    @Override
    public boolean setDisplayFormat(EN_ThreeD_Video_DISPLAYFORMAT displayFormat) {
        ThreeDSetting.eThreeDVideoDisplayFormat = displayFormat;
        databaseMgrImpl.updateVideo3DMode(ThreeDSetting, com.GetCurrentInputSource().ordinal());
        com.printfI("TvS3DManagerIml", "Display format is "
                + ThreeDSetting.eThreeDVideoDisplayFormat);
        boolean ret = true;
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().setVideoMute(true, EnumScreenMuteType.E_BLACK, 0,
                        com.GetCurrentInputSource());
            }
        } catch (Exception e) {
            com.printfE("S3DDeskImpl", "setVideoMute False Exception");
            e.printStackTrace();
        }
        if (displayFormat == EN_ThreeD_Video_DISPLAYFORMAT.DB_ThreeD_Video_DISPLAYFORMAT_SIDE_BY_SIDE) {
            try {
                if (TvManager.getInstance() != null) {
                    ret = TvManager.getInstance().getThreeDimensionManager()
                            .enable3d(Enum3dType.EN_3D_SIDE_BY_SIDE_HALF);
                }
            } catch (Exception e) {
                com.printfE("S3DDeskImpl", "setDisplayFormat False Exception");
                e.printStackTrace();
            }
        } else if (displayFormat == EN_ThreeD_Video_DISPLAYFORMAT.DB_ThreeD_Video_DISPLAYFORMAT_TOP_BOTTOM) {
            try {
                if (TvManager.getInstance() != null) {
                    ret = TvManager.getInstance().getThreeDimensionManager()
                            .enable3d(Enum3dType.EN_3D_TOP_BOTTOM);
                }
            } catch (Exception e) {
                com.printfE("S3DDeskImpl", "setDisplayFormat False Exception");
                e.printStackTrace();
            }
        } else if (displayFormat == EN_ThreeD_Video_DISPLAYFORMAT.DB_ThreeD_Video_DISPLAYFORMAT_FRAME_PACKING) {
            try {
                if (TvManager.getInstance() != null) {
                    ret = TvManager.getInstance().getThreeDimensionManager()
                            .enable3d(Enum3dType.EN_3D_FRAME_PACKING_1080P);
                }
            } catch (Exception e) {
                com.printfE("S3DDeskImpl", "setDisplayFormat False Exception");
                e.printStackTrace();
            }
        } else if (displayFormat == EN_ThreeD_Video_DISPLAYFORMAT.DB_ThreeD_Video_DISPLAYFORMAT_LINE_ALTERNATIVE) {
            try {
                if (TvManager.getInstance() != null) {
                    ret = TvManager.getInstance().getThreeDimensionManager()
                            .enable3d(Enum3dType.EN_3D_LINE_ALTERNATIVE);
                }
            } catch (Exception e) {
                com.printfE("S3DDeskImpl", "setDisplayFormat False Exception");
                e.printStackTrace();
            }
        } else if (displayFormat == EN_ThreeD_Video_DISPLAYFORMAT.DB_ThreeD_Video_DISPLAYFORMAT_2DTO3D) {
            try {
                if (TvManager.getInstance() != null) {
                    ret = TvManager.getInstance().getThreeDimensionManager()
                            .enable3d(Enum3dType.EN_3D_2DTO3D);
                    TvManager.getInstance().getThreeDimensionManager().set3dGain(get3DDepthMode());
                    TvManager.getInstance().getThreeDimensionManager()
                            .set3dOffset(get3DOffsetMode());
                }
            } catch (Exception e) {
                com.printfE("S3DDeskImpl", "setDisplayFormat False Exception");
                e.printStackTrace();
            }
        } else if (displayFormat == EN_ThreeD_Video_DISPLAYFORMAT.DB_ThreeD_Video_DISPLAYFORMAT_AUTO) {
            if (com.isSignalStable()) {
                com.printfE("S3DDeskImpl", "Signal Stable!!!");
                try {
                    if (TvManager.getInstance() != null) {
                        ret = TvManager.getInstance().getThreeDimensionManager()
                                .enable3d(Enum3dType.EN_3D_NONE);
                    }
                } catch (Exception e) {
                    com.printfE("S3DDeskImpl", "setDisplayFormat False Exception");
                    e.printStackTrace();
                }
                try {
                    if (TvManager.getInstance() != null) {
                        Enum3dType _3dtype;
                        _3dtype = TvManager.getInstance().getThreeDimensionManager()
                                .detect3dFormat(EnumScalerWindow.E_MAIN_WINDOW);
                        com.printfE("S3DDeskImpl", "Detect 3D formate is" + _3dtype);
                        ret = TvManager.getInstance().getThreeDimensionManager().enable3d(_3dtype);
                    }
                } catch (Exception e) {
                    com.printfE("S3DDeskImpl", "setDisplayFormat False Exception");
                    e.printStackTrace();
                }
            } else {
                com.printfE("S3DDeskImpl", "Signal Not Stable!!!");
            }
        } else if (displayFormat == EN_ThreeD_Video_DISPLAYFORMAT.DB_ThreeD_Video_DISPLAYFORMAT_CHECK_BOARD) {
            try {
                if (TvManager.getInstance() != null) {
                    ret = TvManager.getInstance().getThreeDimensionManager()
                            .enable3d(Enum3dType.EN_3D_CHECK_BORAD);
                }
            } catch (Exception e) {
                com.printfE("S3DDeskImpl", "setDisplayFormat False Exception");
                e.printStackTrace();
            }
        } else if (displayFormat == EN_ThreeD_Video_DISPLAYFORMAT.DB_ThreeD_Video_DISPLAYFORMAT_PIXEL_ALTERNATIVE) {
            try {
                if (TvManager.getInstance() != null) {
                    ret = TvManager.getInstance().getThreeDimensionManager()
                            .enable3d(Enum3dType.EN_3D_PIXEL_ALTERNATIVE);
                }
            } catch (Exception e) {
                com.printfE("S3DDeskImpl", "setDisplayFormat False Exception");
                e.printStackTrace();
            }
        } else if (displayFormat == EN_ThreeD_Video_DISPLAYFORMAT.DB_ThreeD_Video_DISPLAYFORMAT_FRAME_ALTERNATIVE) {
            try {
                if (TvManager.getInstance() != null) {
                    ret = TvManager.getInstance().getThreeDimensionManager()
                            .enable3d(Enum3dType.EN_3D_FRAME_ALTERNATIVE);
                }
            } catch (Exception e) {
                com.printfE("S3DDeskImpl", "setDisplayFormat False Exception");
                e.printStackTrace();
            }
        } else {
            // if(displayFormat ==
            // EN_ThreeD_Video_DISPLAYFORMAT.DB_ThreeD_Video_DISPLAYFORMAT_NONE)
            try {
                if (TvManager.getInstance() != null) {
                    ret = TvManager.getInstance().getThreeDimensionManager()
                            .enable3d(Enum3dType.EN_3D_NONE);
                }
            } catch (Exception e) {
                com.printfE("S3DDeskImpl", "setDisplayFormat False Exception");
                e.printStackTrace();
            }
        }
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().setVideoMute(false, EnumScreenMuteType.E_BLACK, 0,
                        com.GetCurrentInputSource());
            }
        } catch (Exception e) {
            com.printfE("S3DDeskImpl", "setVideoMute False Exception");
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public EN_ThreeD_Video_DISPLAYFORMAT getDisplayFormat() {
        if (ThreeDSetting.eThreeDVideoSelfAdaptiveDetect == EN_ThreeD_Video_SELFADAPTIVE_DETECT.DB_ThreeD_Video_SELF_ADAPTIVE_DETECT_OFF) {
            ThreeDSetting.eThreeDVideoDisplayFormat = databaseMgrImpl
                    .queryThreeDVideoDisplayFormat(com.GetCurrentInputSource().ordinal());
        } else {
            ThreeDSetting.eThreeDVideoDisplayFormat = EN_ThreeD_Video_DISPLAYFORMAT.DB_ThreeD_Video_DISPLAYFORMAT_AUTO;
        }
        return ThreeDSetting.eThreeDVideoDisplayFormat;
    }

    @Override
    public boolean set3DTo2D(EN_ThreeD_Video_3DTO2D display3dto2dMode) {
        ThreeDSetting.eThreeDVideo3DTo2D = display3dto2dMode;
        databaseMgrImpl.updateVideo3DMode(ThreeDSetting, com.GetCurrentInputSource().ordinal());
        com.printfI("TvS3DManagerIml", "Display 3DTo2D Mode is " + ThreeDSetting.eThreeDVideo3DTo2D);
        boolean ret = true;
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().setVideoMute(true, EnumScreenMuteType.E_BLACK, 0,
                        com.GetCurrentInputSource());
            }
        } catch (Exception e) {
            com.printfE("S3DDeskImpl", "setVideoMute False Exception");
            e.printStackTrace();
        }
        if (display3dto2dMode == EN_ThreeD_Video_3DTO2D.DB_ThreeD_Video_3DTO2D_SIDE_BY_SIDE) {
            try {
                if (TvManager.getInstance() != null) {
                    ret = TvManager.getInstance().getThreeDimensionManager()
                            .enable3dTo2d(Enum3dType.EN_3D_SIDE_BY_SIDE_HALF);
                }
            } catch (Exception e) {
                com.printfE("S3DDeskImpl", "Set set3DTo2D False Exception");
                e.printStackTrace();
            }
        } else if (display3dto2dMode == EN_ThreeD_Video_3DTO2D.DB_ThreeD_Video_3DTO2D_TOP_BOTTOM) {
            try {
                if (TvManager.getInstance() != null) {
                    ret = TvManager.getInstance().getThreeDimensionManager()
                            .enable3dTo2d(Enum3dType.EN_3D_TOP_BOTTOM);
                }
            } catch (Exception e) {
                com.printfE("S3DDeskImpl", "Set set3DTo2D False Exception");
                e.printStackTrace();
            }
        } else if (display3dto2dMode == EN_ThreeD_Video_3DTO2D.DB_ThreeD_Video_3DTO2D_FRAME_PACKING) {
            try {
                if (TvManager.getInstance() != null) {
                    ret = TvManager.getInstance().getThreeDimensionManager()
                            .enable3dTo2d(Enum3dType.EN_3D_FRAME_PACKING_1080P);
                }
            } catch (Exception e) {
                com.printfE("S3DDeskImpl", "Set set3DTo2D False Exception");
                e.printStackTrace();
            }
        } else if (display3dto2dMode == EN_ThreeD_Video_3DTO2D.DB_ThreeD_Video_3DTO2D_LINE_ALTERNATIVE) {
            try {
                if (TvManager.getInstance() != null) {
                    ret = TvManager.getInstance().getThreeDimensionManager()
                            .enable3dTo2d(Enum3dType.EN_3D_LINE_ALTERNATIVE);
                }
            } catch (Exception e) {
                com.printfE("S3DDeskImpl", "Set set3DTo2D False Exception");
                e.printStackTrace();
            }
        } else if (display3dto2dMode == EN_ThreeD_Video_3DTO2D.DB_ThreeD_Video_3DTO2D_AUTO) {
            if (com.isSignalStable()) {
                com.printfE("S3DDeskImpl", "Signal Stable!!!");
                try {
                    if (TvManager.getInstance() != null) {
                        Enum3dType _3dtype;
                        _3dtype = TvManager.getInstance().getThreeDimensionManager()
                                .detect3dFormat(EnumScalerWindow.E_MAIN_WINDOW);
                        com.printfE("S3DDeskImpl", "Detect 3D formate is" + _3dtype);
                        ret = TvManager.getInstance().getThreeDimensionManager()
                                .enable3dTo2d(_3dtype);
                    }
                } catch (Exception e) {
                    com.printfE("S3DDeskImpl", "Set set3dFormatDetectFlag True Exception");
                    e.printStackTrace();
                }
            } else {
                com.printfE("S3DDeskImpl", "Signal Not Stable!!!");
            }
        } else if (display3dto2dMode == EN_ThreeD_Video_3DTO2D.DB_ThreeD_Video_3DTO2D_CHECK_BOARD) {
            try {
                if (TvManager.getInstance() != null) {
                    ret = TvManager.getInstance().getThreeDimensionManager()
                            .enable3d(Enum3dType.EN_3D_CHECK_BORAD);
                }
            } catch (Exception e) {
                com.printfE("S3DDeskImpl", "setDisplayFormat False Exception");
                e.printStackTrace();
            }
        } else if (display3dto2dMode == EN_ThreeD_Video_3DTO2D.DB_ThreeD_Video_3DTO2D_PIXEL_ALTERNATIVE) {
            try {
                if (TvManager.getInstance() != null) {
                    ret = TvManager.getInstance().getThreeDimensionManager()
                            .enable3d(Enum3dType.EN_3D_PIXEL_ALTERNATIVE);
                }
            } catch (Exception e) {
                com.printfE("S3DDeskImpl", "setDisplayFormat False Exception");
                e.printStackTrace();
            }
        } else if (display3dto2dMode == EN_ThreeD_Video_3DTO2D.DB_ThreeD_Video_3DTO2D_FRAME_ALTERNATIVE) {
            try {
                if (TvManager.getInstance() != null) {
                    ret = TvManager.getInstance().getThreeDimensionManager()
                            .enable3dTo2d(Enum3dType.EN_3D_FRAME_ALTERNATIVE);
                }
            } catch (Exception e) {
                com.printfE("S3DDeskImpl", "Set set3DTo2D False Exception");
                e.printStackTrace();
            }
        } else {
            // (display3dto2dMode ==
            // EN_ThreeD_Video_3DTO2D.DB_ThreeD_Video_3DTO2D_NONE)
            try {
                if (TvManager.getInstance() != null) {
                    ret = TvManager.getInstance().getThreeDimensionManager()
                            .enable3dTo2d(Enum3dType.EN_3D_NONE);
                }
            } catch (Exception e) {
                com.printfE("S3DDeskImpl", "Set set3DTo2D False Exception");
                e.printStackTrace();
            }
        }
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().setVideoMute(false, EnumScreenMuteType.E_BLACK, 0,
                        com.GetCurrentInputSource());
            }
        } catch (Exception e) {
            com.printfE("S3DDeskImpl", "setVideoMute False Exception");
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public EN_ThreeD_Video_3DTO2D getDisplay3DTo2DMode() {
        return ThreeDSetting.eThreeDVideo3DTo2D;
    }

    @Override
    public boolean set3DDepthMode(int mode3DDepth) {
        ThreeDSetting.eThreeDVideo3DDepth = EN_ThreeD_Video_3DDEPTH.values()[mode3DDepth];
        databaseMgrImpl.updateVideo3DMode(ThreeDSetting, com.GetCurrentInputSource().ordinal());
        com.printfI("TvS3DManagerIml", "3D Depth mode is " + ThreeDSetting.eThreeDVideo3DDepth);
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getThreeDimensionManager().set3dGain(mode3DDepth);
            }
        } catch (Exception e) {
            com.printfE("S3DDeskImpl", "Set set3DDepthMode False Exception");
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public int get3DDepthMode() {
        return ThreeDSetting.eThreeDVideo3DDepth.ordinal();
    }

    @Override
    public boolean set3DOffsetMode(int mode3DOffset) {
        ThreeDSetting.eThreeDVideo3DOffset = EN_ThreeD_Video_3DOFFSET.values()[mode3DOffset];
        databaseMgrImpl.updateVideo3DMode(ThreeDSetting, com.GetCurrentInputSource().ordinal());
        com.printfI("TvS3DManagerIml", "3D Depth mode is " + ThreeDSetting.eThreeDVideo3DDepth);
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getThreeDimensionManager().set3dOffset(mode3DOffset);
            }
        } catch (Exception e) {
            com.printfE("S3DDeskImpl", "Set set3DDepthMode False Exception");
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public int get3DOffsetMode() {
        return ThreeDSetting.eThreeDVideo3DOffset.ordinal();
    }

    @Override
    public boolean setAutoStartMode(EN_ThreeD_Video_AUTOSTART autoStartMode) {
        ThreeDSetting.eThreeDVideoAutoStart = autoStartMode;
        databaseMgrImpl.updateVideo3DMode(ThreeDSetting, com.GetCurrentInputSource().ordinal());
        com.printfI("TvS3DManagerIml", "auto start mode is " + ThreeDSetting.eThreeDVideoAutoStart);
        return true;
    }

    @Override
    public EN_ThreeD_Video_AUTOSTART getAutoStartMode() {
        return ThreeDSetting.eThreeDVideoAutoStart;
    }

    @Override
    public boolean set3DOutputAspectMode(EN_ThreeD_Video_3DOUTPUTASPECT outputAspectMode) {
        Enum3dAspectRatioType arType = Enum3dAspectRatioType.E_3D_ASPECTRATIO_FULL;
        ThreeDSetting.eThreeDVideo3DOutputAspect = outputAspectMode;
        databaseMgrImpl.updateVideo3DMode(ThreeDSetting, com.GetCurrentInputSource().ordinal());
        com.printfI("TvS3DManagerIml", "output aspect mode is "
                + ThreeDSetting.eThreeDVideo3DOutputAspect);

        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().setVideoMute(true, EnumScreenMuteType.E_BLACK, 0,
                        com.GetCurrentInputSource());
            }
        } catch (Exception e) {
            com.printfE("S3DDeskImpl", "setVideoMute False Exception");
            e.printStackTrace();
        }
        if (outputAspectMode == EN_ThreeD_Video_3DOUTPUTASPECT.DB_ThreeD_Video_3DOUTPUTASPECT_CENTER) {
            arType = Enum3dAspectRatioType.E_3D_ASPECTRATIO_CENTER;
        } else if (outputAspectMode == EN_ThreeD_Video_3DOUTPUTASPECT.DB_ThreeD_Video_3DOUTPUTASPECT_AUTOADAPTED) {
            arType = Enum3dAspectRatioType.E_3D_ASPECTRATIO_AUTO;
        } else {
            // if(outputAspectMode ==
            // EN_ThreeD_Video_3DOUTPUTASPECT.DB_ThreeD_Video_3DOUTPUTASPECT_FULLSCREEN)
            arType = Enum3dAspectRatioType.E_3D_ASPECTRATIO_FULL;
        }
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getThreeDimensionManager().set3dArc(arType);
            }
        } catch (Exception e) {
            com.printfE("S3DDeskImpl", "Set set3DDepthMode False Exception");
            e.printStackTrace();
        }
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().setVideoMute(false, EnumScreenMuteType.E_BLACK, 0,
                        com.GetCurrentInputSource());
            }
        } catch (Exception e) {
            com.printfE("S3DDeskImpl", "setVideoMute False Exception");
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public EN_ThreeD_Video_3DOUTPUTASPECT get3DOutputAspectMode() {
        return ThreeDSetting.eThreeDVideo3DOutputAspect;
    }

    @Override
    public boolean setLRViewSwitch(EN_ThreeD_Video_LRVIEWSWITCH LRViewSwitchMode) {
        ThreeDSetting.eThreeDVideoLRViewSwitch = LRViewSwitchMode;
        databaseMgrImpl.updateVideo3DMode(ThreeDSetting, com.GetCurrentInputSource().ordinal());
        com.printfI("TvS3DManagerIml", "set View switch is "
                + ThreeDSetting.eThreeDVideoLRViewSwitch);
        try {
            if (ThreeDSetting.eThreeDVideoLRViewSwitch == EN_ThreeD_Video_LRVIEWSWITCH.DB_ThreeD_Video_LRVIEWSWITCH_EXCHANGE) {
                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().getThreeDimensionManager().enable3dLrSwitch();
                }
            } else {
                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().getThreeDimensionManager().disable3dLrSwitch();
                }
            }

        } catch (Exception e) {
            com.printfE("S3DDeskImpl", "Get LRViewSwitch False Exception");
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public EN_ThreeD_Video_LRVIEWSWITCH getLRViewSwitch() {
        boolean bLRSwitch = false;
        try {
            if (TvManager.getInstance() != null) {
                bLRSwitch = TvManager.getInstance().getThreeDimensionManager().is3dLrSwitched();
            }
        } catch (Exception e) {
            com.printfE("S3DDeskImpl", "Get LRViewSwitch False Exception");
            e.printStackTrace();
        }
        if (bLRSwitch) {
            ThreeDSetting.eThreeDVideoLRViewSwitch = EN_ThreeD_Video_LRVIEWSWITCH.DB_ThreeD_Video_LRVIEWSWITCH_EXCHANGE;
        } else {
            ThreeDSetting.eThreeDVideoLRViewSwitch = EN_ThreeD_Video_LRVIEWSWITCH.DB_ThreeD_Video_LRVIEWSWITCH_NOTEXCHANGE;
        }
        return ThreeDSetting.eThreeDVideoLRViewSwitch;
    }
}
