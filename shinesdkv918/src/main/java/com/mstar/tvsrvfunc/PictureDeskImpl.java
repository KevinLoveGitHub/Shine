//<MStar Software>
//******************************************************************************
// MStar Software
// Copyright (c) 2010 - 2013 MStar Semiconductor, Inc. All rights reserved.
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

import com.mstar.android.tvapi.common.TvManager;
import com.mstar.android.tvapi.common.vo.ColorTemperature;
import com.mstar.android.tvapi.common.vo.EnumColorTemperature;
import com.mstar.android.tvapi.common.vo.EnumScalerWindow;
import com.mstar.android.tvapi.common.vo.EnumScreenMuteType;
import com.mstar.android.tvapi.common.vo.EnumVideoArcType;
import com.mstar.android.tvapi.common.vo.MpegNoiseReduction.EnumMpegNoiseReduction;
import com.mstar.android.tvapi.common.vo.NoiseReduction.EnumNoiseReduction;
import com.mstar.android.tvapi.common.vo.TvOsType.EnumInputSource;
import com.mstar.android.tvapi.common.vo.VideoWindowType;
import com.mstar.tvsettingservice.CommonDesk;
import com.mstar.tvsettingservice.DataBaseDesk.EN_MS_COLOR_TEMP;
import com.mstar.tvsettingservice.DataBaseDesk.EN_MS_COLOR_TEMP_INPUT_SOURCE;
import com.mstar.tvsettingservice.DataBaseDesk.EN_MS_MPEG_NR;
import com.mstar.tvsettingservice.DataBaseDesk.EN_MS_NR;
import com.mstar.tvsettingservice.DataBaseDesk.EN_MS_PICTURE;
import com.mstar.tvsettingservice.DataBaseDesk.EN_MS_VIDEOITEM;
import com.mstar.tvsettingservice.DataBaseDesk.EN_ThreeD_Video_DISPLAYFORMAT;
import com.mstar.tvsettingservice.DataBaseDesk.EN_ThreeD_Video_SELFADAPTIVE_DETECT;
import com.mstar.tvsettingservice.DataBaseDesk.MAPI_VIDEO_ARC_Type;
import com.mstar.tvsettingservice.DataBaseDesk.T_MS_COLOR_TEMPEX_DATA;
import com.mstar.tvsettingservice.PictureDesk;
import android.util.Log;

public class PictureDeskImpl extends BaseDeskImpl implements PictureDesk {
    private DataBaseDeskImpl databaseMgr = null;

    private DataBaseDeskImpl.T_MS_VIDEO videoPara = null;

    private static PictureDeskImpl pictureMgrImpl = null;

    private CommonDesk com = null;

    public static PictureDeskImpl getPictureMgrInstance() {
        if (pictureMgrImpl == null)
            pictureMgrImpl = new PictureDeskImpl();
        return pictureMgrImpl;
    }

    private PictureDeskImpl() {
        databaseMgr = DataBaseDeskImpl.getDataBaseMgrInstance();
        videoPara = databaseMgr.getVideo();
        com = CommonDeskImpl.getInstance();
    }

    @Override
    public boolean ExecVideoItem(EN_MS_VIDEOITEM eIndex, short value) {
        // com.printfE("TvService", "execvideo nothings to do in simluator!!");
        int idx;
        idx = videoPara.ePicture.ordinal();
        if (eIndex.ordinal() == EN_MS_VIDEOITEM.MS_VIDEOITEM_BRIGHTNESS.ordinal()) {
            // com.printfE("TvService", "execvideo Brightness!!");
            videoPara.astPicture[idx].brightness = value;
            databaseMgr.updateVideoAstPicture(videoPara.astPicture[idx], com
                    .GetCurrentInputSource().ordinal(), idx);
            try {
                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().getPictureManager().setPictureModeBrightness(value);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (eIndex.ordinal() == EN_MS_VIDEOITEM.MS_VIDEOITEM_CONTRAST.ordinal()) {
            // com.printfE("execvideo Contrast!!");
            videoPara.astPicture[idx].contrast = value;
            databaseMgr.updateVideoAstPicture(videoPara.astPicture[idx], com
                    .GetCurrentInputSource().ordinal(), idx);
            try {
                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().getPictureManager().setPictureModeContrast(value);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (eIndex.ordinal() == EN_MS_VIDEOITEM.MS_VIDEOITEM_SATURATION.ordinal()) {
            // com.printfE("TvService", "execvideo Saturation!!");
            videoPara.astPicture[idx].saturation = value;
            databaseMgr.updateVideoAstPicture(videoPara.astPicture[idx], com
                    .GetCurrentInputSource().ordinal(), idx);
            try {
                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().getPictureManager().setPictureModeColor(value);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (eIndex.ordinal() == EN_MS_VIDEOITEM.MS_VIDEOITEM_SHARPNESS.ordinal()) {
            // com.printfE("TvService", "execvideo Sharpness!!");
            videoPara.astPicture[idx].sharpness = value;
            databaseMgr.updateVideoAstPicture(videoPara.astPicture[idx], com
                    .GetCurrentInputSource().ordinal(), idx);
            try {
                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().getPictureManager().setPictureModeSharpness(value);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (eIndex.ordinal() == EN_MS_VIDEOITEM.MS_VIDEOITEM_HUE.ordinal()) {
            // com.printfE("TvService", "execvideo Hue!!");
            videoPara.astPicture[idx].hue = value;
            databaseMgr.updateVideoAstPicture(videoPara.astPicture[idx], com
                    .GetCurrentInputSource().ordinal(), idx);
            try {
                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().getPictureManager().setPictureModeTint(value);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (eIndex.ordinal() == EN_MS_VIDEOITEM.MS_VIDEOITEM_BACKLIGHT.ordinal()) {
            // com.printfE("TvService", "execvideo Backlight!!");
            videoPara.astPicture[idx].backlight = value;
            databaseMgr.updateVideoAstPicture(videoPara.astPicture[idx], com
                    .GetCurrentInputSource().ordinal(), idx);
            try {
                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().getPictureManager().setBacklight(value);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            // com.printfE("TvService", "Haven't this item!!");
        }

        databaseMgr.updateVideoAstPicture(videoPara.astPicture[idx], com.GetCurrentInputSource()
                .ordinal(), videoPara.ePicture.ordinal());
        return true;
    }

    public boolean ExecVideoItem(EN_MS_VIDEOITEM eIndex, short value, int idx) {
        // com.printfE("TvService", "execvideo nothings to do in simluator!!");
        idx = 0;
        if (eIndex.ordinal() == EN_MS_VIDEOITEM.MS_VIDEOITEM_BRIGHTNESS.ordinal()) {
            // com.printfE("TvService", "execvideo Brightness!!");
            videoPara.astPicture[idx].brightness = value;
            databaseMgr.updateVideoAstPicture(videoPara.astPicture[idx], com
                    .GetCurrentInputSource().ordinal(), idx);
            try {
                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().getPictureManager().setPictureModeBrightness(value);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (eIndex.ordinal() == EN_MS_VIDEOITEM.MS_VIDEOITEM_CONTRAST.ordinal()) {
            // com.printfE("execvideo Contrast!!");
            videoPara.astPicture[idx].contrast = value;
            databaseMgr.updateVideoAstPicture(videoPara.astPicture[idx], com
                    .GetCurrentInputSource().ordinal(), idx);
            try {
                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().getPictureManager().setPictureModeContrast(value);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (eIndex.ordinal() == EN_MS_VIDEOITEM.MS_VIDEOITEM_SATURATION.ordinal()) {
            // com.printfE("TvService", "execvideo Saturation!!");
            videoPara.astPicture[idx].saturation = value;
            databaseMgr.updateVideoAstPicture(videoPara.astPicture[idx], com
                    .GetCurrentInputSource().ordinal(), idx);
            try {
                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().getPictureManager().setPictureModeColor(value);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (eIndex.ordinal() == EN_MS_VIDEOITEM.MS_VIDEOITEM_SHARPNESS.ordinal()) {
            // com.printfE("TvService", "execvideo Sharpness!!");
            videoPara.astPicture[idx].sharpness = value;
            databaseMgr.updateVideoAstPicture(videoPara.astPicture[idx], com
                    .GetCurrentInputSource().ordinal(), idx);
            try {
                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().getPictureManager().setPictureModeSharpness(value);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (eIndex.ordinal() == EN_MS_VIDEOITEM.MS_VIDEOITEM_HUE.ordinal()) {
            // com.printfE("TvService", "execvideo Hue!!");
            videoPara.astPicture[idx].hue = value;
            databaseMgr.updateVideoAstPicture(videoPara.astPicture[idx], com
                    .GetCurrentInputSource().ordinal(), idx);
            try {
                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().getPictureManager().setPictureModeTint(value);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (eIndex.ordinal() == EN_MS_VIDEOITEM.MS_VIDEOITEM_BACKLIGHT.ordinal()) {
            // com.printfE("TvService", "execvideo Backlight!!");
            videoPara.astPicture[idx].backlight = value;
            databaseMgr.updateVideoAstPicture(videoPara.astPicture[idx], com
                    .GetCurrentInputSource().ordinal(), idx);
            try {
                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().getPictureManager().setBacklight(value);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            // com.printfE("TvService", "Haven't this item!!");
        }

        databaseMgr.updateVideoAstPicture(videoPara.astPicture[idx], com.GetCurrentInputSource()
                .ordinal(), videoPara.ePicture.ordinal());
        return true;
    }

    @Override
    public short GetVideoItem(EN_MS_VIDEOITEM eIndex) {
        // com.printfE("TvService", "Get Video Item Value!!");
        int idx;
        idx = videoPara.ePicture.ordinal();
        short result = 0x50;
        switch (eIndex) {
            case MS_VIDEOITEM_BRIGHTNESS:
                // com.printfE("TvService", "GetBrightness = " +
                // videoPara.astPicture[idx].brightness + " !!");
                // result = (short) (videoPara.astPicture[idx].brightness);
                result = (short) databaseMgr.queryPicModeSetting(eIndex, com
                        .GetCurrentInputSource().ordinal(), idx);
                // com.printfE("TvService11", "GetBrightness = " + result +
                // " !!");
                break;
            case MS_VIDEOITEM_CONTRAST:
                // com.printfE("TvService", "GetContrast = " +
                // videoPara.astPicture[idx].contrast + " !!");
                // result = (short) (videoPara.astPicture[idx].contrast);
                result = (short) databaseMgr.queryPicModeSetting(eIndex, com
                        .GetCurrentInputSource().ordinal(), idx);
                // com.printfE("TvService11", "GetContrast = " + result +
                // " !!");
                break;
            case MS_VIDEOITEM_SATURATION:
                // com.printfE("TvService", "GetSaturation = " +
                // videoPara.astPicture[idx].saturation + " !!");
                // result = (short) (videoPara.astPicture[idx].saturation);
                result = (short) databaseMgr.queryPicModeSetting(eIndex, com
                        .GetCurrentInputSource().ordinal(), idx);
                // com.printfE("TvService11", "GetSaturation = " + result +
                // " !!");
                break;
            case MS_VIDEOITEM_SHARPNESS:
                // com.printfE("TvService", "Getsharpness = " +
                // videoPara.astPicture[idx].sharpness + " !!");
                // result = (short) (videoPara.astPicture[idx].sharpness);
                result = (short) databaseMgr.queryPicModeSetting(eIndex, com
                        .GetCurrentInputSource().ordinal(), idx);
                // com.printfE("TvService11", "Getsharpness = " + result +
                // " !!");
                break;
            case MS_VIDEOITEM_HUE:
                // com.printfE("TvService", "Gethue = " +
                // videoPara.astPicture[idx].hue + " !!");
                // result = (short) (videoPara.astPicture[idx].hue);
                result = (short) databaseMgr.queryPicModeSetting(eIndex, com
                        .GetCurrentInputSource().ordinal(), idx);
                // com.printfE("TvService11", "Gethue = " + result + " !!");
                break;
            case MS_VIDEOITEM_BACKLIGHT:
                // com.printfE("TvService", "Gethue = " +
                // videoPara.astPicture[idx].hue + " !!");
                // result = (short) (videoPara.astPicture[idx].hue);
                result = (short) databaseMgr.queryPicModeSetting(eIndex, com
                        .GetCurrentInputSource().ordinal(), idx);
                // com.printfE("TvService11", "Getbacklight = " + result +
                // " !!");
                break;
            default:
                com.printfE("TvService", "Haven't this item internal error !!");
                break;
        }
        return result;
    }

    // @Override
    // public boolean SetPictureModeIdx(EN_MS_PICTURE ePicMode)
    // {
    // videoPara.ePicture = ePicMode;
    //
    // videoPara.astPicture[ePicMode.ordinal()].brightness=GetVideoItem(EN_MS_VIDEOITEM.MS_VIDEOITEM_BRIGHTNESS);
    // videoPara.astPicture[ePicMode.ordinal()].contrast=GetVideoItem(EN_MS_VIDEOITEM.MS_VIDEOITEM_CONTRAST);
    // videoPara.astPicture[ePicMode.ordinal()].saturation=GetVideoItem(EN_MS_VIDEOITEM.MS_VIDEOITEM_SATURATION);
    // videoPara.astPicture[ePicMode.ordinal()].sharpness=GetVideoItem(EN_MS_VIDEOITEM.MS_VIDEOITEM_SHARPNESS);
    // videoPara.astPicture[ePicMode.ordinal()].hue=GetVideoItem(EN_MS_VIDEOITEM.MS_VIDEOITEM_HUE);
    // videoPara.astPicture[ePicMode.ordinal()].backlight=GetVideoItem(EN_MS_VIDEOITEM.MS_VIDEOITEM_BACKLIGHT);
    //
    // ExecVideoItem(EN_MS_VIDEOITEM.MS_VIDEOITEM_BRIGHTNESS,
    // videoPara.astPicture[ePicMode.ordinal()].brightness);
    // ExecVideoItem(EN_MS_VIDEOITEM.MS_VIDEOITEM_CONTRAST,
    // videoPara.astPicture[ePicMode.ordinal()].contrast);
    // ExecVideoItem(EN_MS_VIDEOITEM.MS_VIDEOITEM_SATURATION,
    // videoPara.astPicture[ePicMode.ordinal()].saturation);
    // ExecVideoItem(EN_MS_VIDEOITEM.MS_VIDEOITEM_SHARPNESS,
    // videoPara.astPicture[ePicMode.ordinal()].sharpness);
    // ExecVideoItem(EN_MS_VIDEOITEM.MS_VIDEOITEM_HUE,
    // videoPara.astPicture[ePicMode.ordinal()].hue);
    // ExecVideoItem(EN_MS_VIDEOITEM.MS_VIDEOITEM_BACKLIGHT,
    // videoPara.astPicture[ePicMode.ordinal()].backlight);
    // databaseMgr.updateVideoBasePara(videoPara,com.GetCurrentInputSource().ordinal()
    // );
    //
    // return true;
    // }

    @Override
    public boolean SetPictureModeIdx(EN_MS_PICTURE ePicMode) {
        videoPara.ePicture = ePicMode;
        try {
            if (TvManager.getInstance() != null) {
                TvManager
                        .getInstance()
                        .getPictureManager()
                        .setPictureModeBrightness(
                                videoPara.astPicture[ePicMode.ordinal()].brightness);
                TvManager.getInstance().getPictureManager()
                        .setPictureModeContrast(videoPara.astPicture[ePicMode.ordinal()].contrast);
                TvManager.getInstance().getPictureManager()
                        .setPictureModeColor(videoPara.astPicture[ePicMode.ordinal()].saturation);
                TvManager
                        .getInstance()
                        .getPictureManager()
                        .setPictureModeSharpness(videoPara.astPicture[ePicMode.ordinal()].sharpness);
                TvManager.getInstance().getPictureManager()
                        .setPictureModeTint(videoPara.astPicture[ePicMode.ordinal()].hue);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        databaseMgr.updateVideoBasePara(videoPara, com.GetCurrentInputSource().ordinal());
        databaseMgr.updateVideoAstPicture(videoPara.astPicture[ePicMode.ordinal()], com
                .GetCurrentInputSource().ordinal(), ePicMode.ordinal());
        return true;
    }

    @Override
    public EN_MS_PICTURE GetPictureModeIdx() {
        // com.printfE("TvService", "GetPictureModeIdx:" + videoPara.ePicture +
        // "!!");
        videoPara = databaseMgr.queryAllVideoPara(com.GetCurrentInputSource().ordinal());
        return videoPara.ePicture;
    }

    @Override
    public int GetbIsPcMode() {
        return videoPara.bIsPcMode;
    }

    @Override
    public boolean SetBacklight(short value) {
        // com.printfE("TvService", "setbacklight nothing to do!!");
        int pictureModeType = videoPara.ePicture.ordinal();
        videoPara.astPicture[pictureModeType].backlight = value;
        databaseMgr.updateVideoAstPicture(videoPara.astPicture[pictureModeType], com
                .GetCurrentInputSource().ordinal(), pictureModeType);
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getPictureManager().setBacklight(value);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public short GetBacklight() {
        int idx;
        idx = videoPara.ePicture.ordinal();
        // com.printfE("TvService", "GetBacklight = " +
        // videoPara.astPicture[idx].backlight + " !!");
        return (short) (videoPara.astPicture[idx].backlight);
    }

    @Override
    public boolean SetColorTempIdx(EN_MS_COLOR_TEMP eColorTemp) {
        int idx;
        idx = videoPara.ePicture.ordinal();
        // com.printfE("TvService", "SetColorTempIdx nothing to do!!");
        videoPara.astPicture[idx].eColorTemp = eColorTemp;
        ColorTemperature vo = new ColorTemperature();
        EnumInputSource curSource = null;
        T_MS_COLOR_TEMPEX_DATA temp = null;
        try {
            if (TvManager.getInstance() != null) {
                curSource = TvManager.getInstance().getCurrentInputSource();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        switch (curSource) {
            case E_INPUT_SOURCE_VGA:
                temp = DataBaseDeskImpl.getDataBaseMgrInstance().m_stFactoryColorTempEx.astColorTempEx[eColorTemp
                        .ordinal()][EN_MS_COLOR_TEMP_INPUT_SOURCE.E_INPUT_SOURCE_VGA.ordinal()];
                break;
            case E_INPUT_SOURCE_ATV:
                temp = DataBaseDeskImpl.getDataBaseMgrInstance().m_stFactoryColorTempEx.astColorTempEx[eColorTemp
                        .ordinal()][EN_MS_COLOR_TEMP_INPUT_SOURCE.E_INPUT_SOURCE_ATV.ordinal()];
                break;
            case E_INPUT_SOURCE_CVBS:
            case E_INPUT_SOURCE_CVBS2:
            case E_INPUT_SOURCE_CVBS3:
            case E_INPUT_SOURCE_CVBS4:
            case E_INPUT_SOURCE_CVBS5:
            case E_INPUT_SOURCE_CVBS6:
            case E_INPUT_SOURCE_CVBS7:
            case E_INPUT_SOURCE_CVBS8:
                temp = DataBaseDeskImpl.getDataBaseMgrInstance().m_stFactoryColorTempEx.astColorTempEx[eColorTemp
                        .ordinal()][EN_MS_COLOR_TEMP_INPUT_SOURCE.E_INPUT_SOURCE_CVBS.ordinal()];
                break;
            case E_INPUT_SOURCE_SVIDEO:
            case E_INPUT_SOURCE_SVIDEO2:
            case E_INPUT_SOURCE_SVIDEO3:
            case E_INPUT_SOURCE_SVIDEO4:
                temp = DataBaseDeskImpl.getDataBaseMgrInstance().m_stFactoryColorTempEx.astColorTempEx[eColorTemp
                        .ordinal()][EN_MS_COLOR_TEMP_INPUT_SOURCE.E_INPUT_SOURCE_SVIDEO.ordinal()];
                break;
            case E_INPUT_SOURCE_YPBPR:
            case E_INPUT_SOURCE_YPBPR2:
            case E_INPUT_SOURCE_YPBPR3:
                temp = DataBaseDeskImpl.getDataBaseMgrInstance().m_stFactoryColorTempEx.astColorTempEx[eColorTemp
                        .ordinal()][EN_MS_COLOR_TEMP_INPUT_SOURCE.E_INPUT_SOURCE_YPBPR.ordinal()];
                break;
            case E_INPUT_SOURCE_SCART:
            case E_INPUT_SOURCE_SCART2:
                temp = DataBaseDeskImpl.getDataBaseMgrInstance().m_stFactoryColorTempEx.astColorTempEx[eColorTemp
                        .ordinal()][EN_MS_COLOR_TEMP_INPUT_SOURCE.E_INPUT_SOURCE_SCART.ordinal()];
                break;
            case E_INPUT_SOURCE_HDMI:
            case E_INPUT_SOURCE_HDMI2:
            case E_INPUT_SOURCE_HDMI3:
            case E_INPUT_SOURCE_HDMI4:
                temp = DataBaseDeskImpl.getDataBaseMgrInstance().m_stFactoryColorTempEx.astColorTempEx[eColorTemp
                        .ordinal()][EN_MS_COLOR_TEMP_INPUT_SOURCE.E_INPUT_SOURCE_HDMI.ordinal()];
                break;
            case E_INPUT_SOURCE_DTV:
            case E_INPUT_SOURCE_DTV2:
                temp = DataBaseDeskImpl.getDataBaseMgrInstance().m_stFactoryColorTempEx.astColorTempEx[eColorTemp
                        .ordinal()][EN_MS_COLOR_TEMP_INPUT_SOURCE.E_INPUT_SOURCE_DTV.ordinal()];
                break;
            case E_INPUT_SOURCE_DVI:
            case E_INPUT_SOURCE_DVI2:
            case E_INPUT_SOURCE_DVI3:
            case E_INPUT_SOURCE_DVI4:
            case E_INPUT_SOURCE_STORAGE:
            case E_INPUT_SOURCE_KTV:
            case E_INPUT_SOURCE_JPEG:
            case E_INPUT_SOURCE_STORAGE2:
                temp = DataBaseDeskImpl.getDataBaseMgrInstance().m_stFactoryColorTempEx.astColorTempEx[eColorTemp
                        .ordinal()][EN_MS_COLOR_TEMP_INPUT_SOURCE.E_INPUT_SOURCE_OTHERS.ordinal()];
                break;
            default:
                com.printfE("TvService", "Haven't this input source type !!");
                break;
        }
        vo.redGain = (short) temp.redgain;
        vo.greenGain = (short) temp.greengain;
        vo.buleGain = (short) temp.bluegain;
        vo.redOffset = (short) temp.redoffset;
        vo.greenOffset = (short) temp.greenoffset;
        vo.blueOffset = (short) temp.blueoffset;
        try {
            // TvManager.getInstance().getPictureManager().setColorTemperature(vo);
            if (TvManager.getInstance() != null) {
                TvManager
                        .getInstance()
                        .getFactoryManager()
                        .setWbGainOffsetEx(EnumColorTemperature.values()[eColorTemp.ordinal() + 1],
                                vo.redGain, vo.greenGain, vo.buleGain, vo.redOffset,
                                vo.greenOffset, vo.blueOffset, curSource);
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        databaseMgr.updateVideoAstPicture(videoPara.astPicture[idx], com.GetCurrentInputSource()
                .ordinal(), idx);
        return true;
    }

    @Override
    public EN_MS_COLOR_TEMP GetColorTempIdx() {
        int idx;
        idx = videoPara.ePicture.ordinal();
        // Log.e("TvService", "GetColorTempIdx:"
        // videopara.astPicture[idx].eColorTemp + "!!");
        return videoPara.astPicture[idx].eColorTemp;
    }

    @Override
    public boolean SetColorTempPara(T_MS_COLOR_TEMPEX_DATA stColorTemp) {
        boolean bRet = false;
        bRet = databaseMgr.setVideoTempEx(stColorTemp);
        int colorTmpIdx = databaseMgr.getVideo().astPicture[databaseMgr.getVideo().ePicture
                .ordinal()].eColorTemp.ordinal();
        databaseMgr.updateUsrColorTmpExData(stColorTemp, colorTmpIdx);
        // com.printfE("TvService", "SetColorTemp Parameter!!");
        return bRet;
    }

    @Override
    public T_MS_COLOR_TEMPEX_DATA GetColorTempPara() {
        // com.printfE("TvService", "GetColorTemp Parameter!!");
        return databaseMgr.getVideoTempEx();
    }

    @Override
    public boolean SetVideoArc(MAPI_VIDEO_ARC_Type eArcIdx) {
        // com.printfE("TvService", "SetVideoArc:" + eArcIdx);
        EnumVideoArcType arcType = EnumVideoArcType.E_DEFAULT;
        videoPara.enARCType = eArcIdx;
        // databaseMgr.updateVideoBasePara(videoPara,
        // com.GetCurrentInputSource().ordinal());

        // if(eArcIdx.ordinal() < MAPI_VIDEO_ARC_Type.E_AR_DotByDot.ordinal())
        // arcType = EnumVideoArcType.values()[eArcIdx.ordinal()];
        // else if(eArcIdx.ordinal() ==
        // MAPI_VIDEO_ARC_Type.E_AR_DotByDot.ordinal())
        // arcType = EnumVideoArcType.E_16x9;
        if (S3DDeskImpl.getS3DMgrInstance().getSelfAdaptiveDetect() != EN_ThreeD_Video_SELFADAPTIVE_DETECT.DB_ThreeD_Video_SELF_ADAPTIVE_DETECT_OFF
                || S3DDeskImpl.getS3DMgrInstance().getDisplayFormat() != EN_ThreeD_Video_DISPLAYFORMAT.DB_ThreeD_Video_DISPLAYFORMAT_NONE) {
            try {
                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().setVideoMute(true, EnumScreenMuteType.E_BLACK, 0,
                            com.GetCurrentInputSource());
                }
            } catch (Exception e) {
                // com.printfE("S3DDeskImpl", "setVideoMute False Exception");
                e.printStackTrace();
            }
        }

        if (eArcIdx.ordinal() < MAPI_VIDEO_ARC_Type.E_AR_MAX.ordinal())
            arcType = EnumVideoArcType.values()[eArcIdx.ordinal()];
        else if (eArcIdx.ordinal() == MAPI_VIDEO_ARC_Type.E_AR_MAX.ordinal())
            arcType = EnumVideoArcType.E_16x9;
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getPictureManager().setAspectRatio(arcType);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (S3DDeskImpl.getS3DMgrInstance().getSelfAdaptiveDetect() != EN_ThreeD_Video_SELFADAPTIVE_DETECT.DB_ThreeD_Video_SELF_ADAPTIVE_DETECT_OFF
                || S3DDeskImpl.getS3DMgrInstance().getDisplayFormat() != EN_ThreeD_Video_DISPLAYFORMAT.DB_ThreeD_Video_DISPLAYFORMAT_NONE) {
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
        return true;
    }

    @Override
    public boolean setAspectRatio(MAPI_VIDEO_ARC_Type eArcIdx) {
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getPictureManager()
                        .setAspectRatio(EnumVideoArcType.values()[eArcIdx.ordinal()]);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;

    }

    @Override
    public MAPI_VIDEO_ARC_Type GetVideoArc() {
        EnumVideoArcType eArcIdx = EnumVideoArcType.E_DEFAULT;
        try {
            if (TvManager.getInstance() != null) {
                eArcIdx = TvManager.getInstance().getPictureManager().getAspectRatio();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return MAPI_VIDEO_ARC_Type.values()[eArcIdx.ordinal()];
    }

    @Override
    public boolean SetNR(EN_MS_NR eNRIdx) {
        int idx;
        EnumNoiseReduction nrType = EnumNoiseReduction.E_NR_OFF;
        idx = videoPara.ePicture.ordinal();
        idx = videoPara.astPicture[idx].eColorTemp.ordinal();
        // com.printfE("TvService", "SetNR value=" + eNRIdx);
        videoPara.eNRMode[idx].eNR = eNRIdx;
        databaseMgr.updateVideoNRMode(videoPara.eNRMode[idx],
                com.GetCurrentInputSource().ordinal(), idx);
        switch (eNRIdx) {
            case MS_NR_OFF:
                nrType = EnumNoiseReduction.E_NR_OFF;
                break;
            case MS_NR_LOW:
                nrType = EnumNoiseReduction.E_NR_LOW;
                break;
            case MS_NR_MIDDLE:
                nrType = EnumNoiseReduction.E_NR_MIDDLE;
                break;
            case MS_NR_HIGH:
                nrType = EnumNoiseReduction.E_NR_HIGH;
                break;
            case MS_NR_AUTO:
                nrType = EnumNoiseReduction.E_NR_AUTO;
                break;
            default:
                break;
        }
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getPictureManager().setNoiseReduction(nrType);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public EN_MS_NR GetNR() {
        int idx;
        idx = videoPara.ePicture.ordinal();
        idx = videoPara.astPicture[idx].eColorTemp.ordinal();
        // com.printfE("TvService", "GetNR:" + videoPara.eNRMode[idx].eNR +
        // "!!");
        return videoPara.eNRMode[idx].eNR;
    }

    @Override
    public boolean SetMpegNR(EN_MS_MPEG_NR eMpNRIdx) {
        int idx;
        EnumMpegNoiseReduction mpegnrType = EnumMpegNoiseReduction.E_MPEG_NR_OFF;
        idx = videoPara.ePicture.ordinal();
        idx = videoPara.astPicture[idx].eColorTemp.ordinal();
        // com.printfE("TvService", "SetMpegNR nothing to do!!");
        videoPara.eNRMode[idx].eMPEG_NR = eMpNRIdx;
        databaseMgr.updateVideoNRMode(videoPara.eNRMode[idx],
                com.GetCurrentInputSource().ordinal(), idx);

        switch (eMpNRIdx) {
            case MS_MPEG_NR_OFF:
                mpegnrType = EnumMpegNoiseReduction.E_MPEG_NR_OFF;
                break;
            case MS_MPEG_NR_LOW:
                mpegnrType = EnumMpegNoiseReduction.E_MPEG_NR_LOW;
                break;
            case MS_MPEG_NR_MIDDLE:
                mpegnrType = EnumMpegNoiseReduction.E_MPEG_NR_MIDDLE;
                break;
            case MS_MPEG_NR_HIGH:
                mpegnrType = EnumMpegNoiseReduction.E_MPEG_NR_HIGH;
                break;
            case MS_MPEG_NR_NUM:
                mpegnrType = EnumMpegNoiseReduction.E_MPEG_NR_NUM;
                break;
            default:
                break;
        }
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getPictureManager().setMpegNoiseReduction(mpegnrType);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public EN_MS_MPEG_NR GetMpegNR() {
        int idx;
        idx = videoPara.ePicture.ordinal();
        idx = videoPara.astPicture[idx].eColorTemp.ordinal();
        // com.printfE("TvService", "GetMpegNR:" +
        // videoPara.eNRMode[idx].eMPEG_NR + "!!");
        return videoPara.eNRMode[idx].eMPEG_NR;
    }

    @Override
    public void SetITC(short ITC) {
        databaseMgr.updateMonitorITC(ITC);
    }

    @Override
    public short GetITC() {
        return databaseMgr.queryMonitorITC();
    }

    @Override
    public short GetPCHPos() {
        return (short) databaseMgr.queryPCHPos();
    }

    @Override
    public short GetPCModeIndex(int id) {
        return (short) databaseMgr.queryPCModeIndex(id);
    }

    @Override
    public boolean SetPCHPos(short hpos) {
        // com.printfE("TvService", "SetPCHPos Parameter!!");
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getPlayerManager().setHPosition(hpos);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public short GetPCVPos() {
        return (short) databaseMgr.queryPCVPos();
    }

    @Override
    public boolean SetPCVPos(short vpos) {
        // com.printfE("TvService", "SetPCVPos Parameter!!");
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getPlayerManager().setVPosition(vpos);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public short GetPCClock() {
        return (short) databaseMgr.queryPCClock();
    }

    @Override
    public boolean SetPCClock(short clock) {
        // com.printfE("TvService", "SetPCVPos Parameter!!");
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getPlayerManager().setSize(clock);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public short GetPCPhase() {
        return (short) databaseMgr.queryPCPhase();
    }

    @Override
    public boolean SetPCPhase(short phase) {
        // com.printfE("TvService", "SetPCPhase Parameter!!");
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getPlayerManager().setPhase(phase);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean ExecAutoPc() {
        // com.printfE("TvService", "ExecAutoPc: Noting to do!!");
        // com.printfE("TvService", "ExecAutoPc: Start!!");
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (PictureDeskImpl.this.getHandler(1) != null) {
                    boolean autotune_flag = false;
                    System.out.println("start run");
                    PictureDeskImpl.this.getHandler(1).sendEmptyMessage(AUTOPC_START);
                    try {
                        if (TvManager.getInstance() != null) {
                            autotune_flag = TvManager.getInstance().getPlayerManager()
                                    .startPcModeAtuoTune();
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if (autotune_flag) {
                        if (PictureDeskImpl.this.getHandler(1) != null) {
                            PictureDeskImpl.this.getHandler(1)
                                    .sendEmptyMessage(AUTOPC_END_SUCESSED);
                        }

                    } else {
                        if (PictureDeskImpl.this.getHandler(1) != null) {
                            PictureDeskImpl.this.getHandler(1).sendEmptyMessage(AUTOPC_END_FAILED);
                        }

                    }
                }
            }
        }).start();
        // com.printfE("TvService", "ExecAutoPc: End!!");
        return true;
    }

    @Override
    public void setDisplayWindow(VideoWindowType videoWindowType) {
        try {
            // TvManager.getPictureManager().setDebugMode(true);
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getPictureManager()
                        .selectWindow(EnumScalerWindow.E_MAIN_WINDOW);
                TvManager.getInstance().getPictureManager().setDisplayWindow(videoWindowType);
                TvManager.getInstance().getPictureManager().scaleWindow();
            }

            // TvManager.getPictureManager().setDebugMode(false);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public short getDlcAverageLuma() {
        // TODO Auto-generated method stub
        short ret = 0;
        try {
            if (TvManager.getInstance() != null) {
                ret = TvManager.getInstance().getPictureManager().getDlcAverageLuma();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public void enableBacklight() {
        // TODO Auto-generated method stub
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getPictureManager().enableBacklight();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void disableBacklight() {
        // TODO Auto-generated method stub
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getPictureManager().disableBacklight();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public int[] getDlcLumArray(int dlcLumArrayLength) {
        // TODO Auto-generated method stub
        try {
            if (TvManager.getInstance() != null) {
                return TvManager.getInstance().getPictureManager()
                        .getDlcLumArray(dlcLumArrayLength);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getCustomerPqRuleNumber() {
        // TODO Auto-generated method stub
        try {
            int pqRuleNumber = 0;
            if (TvManager.getInstance() != null) {
                pqRuleNumber = TvManager.getInstance().getPictureManager()
                        .getCustomerPqRuleNumber();
            }
            return pqRuleNumber;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int getStatusNumberByCustomerPqRule(int ruleType) {
        // TODO Auto-generated method stub
        try {
            int statusNumberByCustomerPqRule = 0;
            if (TvManager.getInstance() != null) {
                statusNumberByCustomerPqRule = TvManager.getInstance().getPictureManager()
                        .getStatusNumberByCustomerPqRule(ruleType);
            }
            return statusNumberByCustomerPqRule;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean setStatusByCustomerPqRule(int ruleType, int ruleStatus) {
        // TODO Auto-generated method stub
        try {
            if (TvManager.getInstance() != null) {
                return TvManager.getInstance().getPictureManager()
                        .setStatusByCustomerPqRule(ruleType, ruleStatus);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean enableXvyccCompensation(boolean bEnable, int eWin) {
        // TODO Auto-generated method stub
        try {
            if (TvManager.getInstance() != null) {
                return TvManager.getInstance().getPictureManager()
                        .enableXvyccCompensation(bEnable, eWin);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
}
