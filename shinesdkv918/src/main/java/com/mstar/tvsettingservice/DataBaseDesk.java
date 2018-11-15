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

package com.mstar.tvsettingservice;

import com.mstar.android.tvapi.common.vo.TvOsType.EnumInputSource;
import com.mstar.android.tvapi.common.vo.TvOsType.EnumLanguage;

public interface DataBaseDesk {
    // /////////////////////////////////////////////////////////////////////////////////////////////////
    // All SQL DB table name
    // /////////////////////////////////////////////////////////////////////////////////////////////////
    // ------------------user_setting.db-----------------------------
    public final static short T_3DInfo_IDX = 0x00;

    public final static short T_3DSetting_IDX = 0x01;

    public final static short T_BlockSysSetting_IDX = 0x02;

    public final static short T_CECSetting_IDX = 0x03;

    public final static short T_CISettineUpInfo_IDX = 0x13;

    public final static short T_PicMode_Setting_IDX = 0x14;

    public final static short T_PipSetting_IDX = 0x15;

    public final static short T_SoundMode_Setting_IDX = 0x16;

    public final static short T_SoundSetting_IDX = 0x17;

    public final static short T_SubtitleSetting_IDX = 0x18;

    public final static short T_SystemSetting_IDX = 0x19;

    public final static short T_ThreeDVideoMode_IDX = 0x1A;

    public final static short T_TimeSetting_IDX = 0x1B;

    public final static short T_USER_COLORTEMP_IDX = 0x1C;

    public final static short T_USER_COLORTEMP_EX_IDX = 0x1D;

    public final static short T_UserLocationSetting_IDX = 0x1E;

    public final static short T_UserMMSetting_IDX = 0x1F;

    public final static short T_UserOverScanMode_IDX = 0x20;

    public final static short T_IsdbUserSetting_IDX = 0x0B;

    public final static short T_MediumSetting_IDX = 0x0C;

    public final static short T_MfcMode_IDX = 0x0D;

    public final static short T_NRMode_IDX = 0x0E;

    public final static short T_NitInfo_IDX = 0x0F;

    public final static short T_Nit_TSInfo_IDX = 0x10;

    public final static short T_OADInfo_IDX = 0x11;

    public final static short T_OADInfo_UntDescriptor_IDX = 0x12;

    public final static short T_CISetting_IDX = 0x04;

    public final static short T_DB_VERSION_IDX = 0x05;

    public final static short T_DvbtPresetting_IDX = 0x06;

    public final static short T_EpgTimer_IDX = 0x07;

    public final static short T_FavTypeName_IDX = 0x08;

    public final static short T_InputSource_Type_IDX = 0x09;

    public final static short T_IsdbSysSetting_IDX = 0x0A;

    public final static short T_UserPCModeSetting_IDX = 0x21;

    public final static short T_VideoSetting_IDX = 0x22;

    public final static short T_ThreeDVideoRouterSetting_IDX = 0x23;

    // ------------------factory.db-----------------------------
    public final static short T_ADCAdjust_IDX = 0x24;

    public final static short T_FacrotyColorTemp_IDX = 0x25;

    public final static short T_FacrotyColorTempEx_IDX = 0x26;

    public final static short T_FactoryExtern_IDX = 0x27;

    public final static short T_NonStarndardAdjust_IDX = 0x28;

    public final static short T_SSCAdjust_IDX = 0x29;

    public final static short T_NonLinearAdjust_IDX = 0x2A;

    public final static short T_OverscanAdjust_IDX = 0x2B;

    public final static short T_PEQAdjust_IDX = 0x2C;

    public final static short T_Factory_DB_VERSION_IDX = 0x2D;

    public final static short T_HDMIOverscanSetting_IDX = 0x2E;

    public final static short T_YPbPrOverscanSetting_IDX = 0x2F;

    public final static short T_DTVOverscanSetting_IDX = 0x30;

    // ------------------customer.db-----------------------------
    public final static short T_ATVDefaultPrograms_IDX = 0x31;

    public final static short T_DTVDefaultPrograms_IDX = 0x32;

    public final static short T_MAX_IDX = 0x33;

    /** picture mode setting */
    public static enum EN_MS_PICTURE {
        // / picture mode dynamic
        PICTURE_DYNAMIC,
        // / picture mode normal
        PICTURE_NORMAL,
        // / picture mode mild
        PICTURE_SOFT,
        // / picture mode user
        PICTURE_USER,
        // / picture game mode
        PICTURE_GAME,
        // / picture auto mode
        PICTURE_AUTO,
        // / picture pc mode
        PICTURE_PC,
        // / picture mode vivid
        PICTURE_VIVID,
        // / picture mode natural
        PICTURE_NATURAL,
        // / picture mode sports
        PICTURE_SPORTS,
        // / picture mode number
        PICTURE_NUMS
    }

    /** color temperature */
    public static enum EN_MS_COLOR_TEMP {
        // /color temperature cool
        MS_COLOR_TEMP_COOL,
        // /color temperature medium
        MS_COLOR_TEMP_NATURE,
        // /color temperature warm
        MS_COLOR_TEMP_WARM,
        // /color temperature user
        MS_COLOR_TEMP_USER,
        // /color temperature
        MS_COLOR_TEMP_NUM
    }

    /** color tempEx input source */
    public static enum EN_MS_COLOR_TEMP_INPUT_SOURCE {
        // /<VGA input
        E_INPUT_SOURCE_VGA,
        // /<ATV input
        E_INPUT_SOURCE_ATV,
        // /<AV
        E_INPUT_SOURCE_CVBS,
        // /<S-video
        E_INPUT_SOURCE_SVIDEO,
        // /<Component
        E_INPUT_SOURCE_YPBPR,
        // /<Scart
        E_INPUT_SOURCE_SCART,
        // /<HDMI
        E_INPUT_SOURCE_HDMI,
        // /<DTV <DTV2
        E_INPUT_SOURCE_DTV,
        // /<DVI <Storage <KTV <Storage2
        E_INPUT_SOURCE_OTHERS,

        // /<number of the source
        E_INPUT_SOURCE_NUM,
        // /<NULL input
        E_INPUT_SOURCE_NONE,
    }

    /** advanced picture settings */
    public static enum EN_MS_PIC_ADV {
        // /off
        MS_OFF,
        // /low
        MS_LOW,
        // /middle
        MS_MIDDLE,
        // /high
        MS_HIGH,
        // /auto
        MS_AUTO,
        // /number
        MS_NUM,
    }

    /** define noise reduction setting */
    public static enum EN_MS_NR {
        // / noise reduction off
        MS_NR_OFF,
        // / noise reduction low
        MS_NR_LOW,
        // / noise reduction middle
        MS_NR_MIDDLE,
        // / noise reduction high
        MS_NR_HIGH,
        // / noise reduction auto
        MS_NR_AUTO,
        // / total noise reduction type number
        MS_NR_NUM,
    }

    /** MPEG noise reduction setting */
    public static enum EN_MS_MPEG_NR {
        // / MPEG noise reduction off
        MS_MPEG_NR_OFF,
        // / MPEG noise reduction low
        MS_MPEG_NR_LOW,
        // / MPEG noise reduction middle
        MS_MPEG_NR_MIDDLE,
        // / MPEG noise reduction high
        MS_MPEG_NR_HIGH,
        // / total mpeg noise reduction type number
        MS_MPEG_NR_NUM,
    }

    /** define enum for noise reduction and mpeg noise reduction */
    public static class T_MS_NR_MODE {
        // / noise reduction setting
        public EN_MS_NR eNR;

        // / MPEG noise reduction setting
        public EN_MS_MPEG_NR eMPEG_NR;

        public T_MS_NR_MODE(EN_MS_NR evalue1, EN_MS_MPEG_NR evalue2) {
            this.eNR = evalue1;
            this.eMPEG_NR = evalue2;
        }
    }

    /** SubColor Setting */
    public class T_MS_SUB_COLOR {
        // / check sum <<checksum should be put at top of the struct, do not
        // move it to other place>>
        int Checksum;

        // / brightness
        public short SubBrightness;

        // / contrast
        public short SubContrast;

        public T_MS_SUB_COLOR(int v1, short v2, short v3) {
            this.Checksum = v1;
            this.SubBrightness = v2;
            this.SubContrast = v3;
        }
    }

    // / Define aspect ratio type
    public static enum MAPI_VIDEO_ARC_Type {
        // / Default
        E_AR_DEFAULT,
        // / 16x9
        E_AR_16x9,
        // / 4x3
        E_AR_4x3,
        // / Auto
        E_AR_AUTO,
        // / Panorama
        E_AR_Panorama,
        // / Just Scan
        E_AR_JustScan,
        // / Zoom 1
        E_AR_Zoom1,
        // / Zoom 2
        E_AR_Zoom2,
        // / 14:9
        E_AR_14x9,
        // / point to point
        E_AR_DotByDot,
        // / Subtitle
        E_AR_Subtitle,
        // / movie
        E_AR_Movie,
        // / Personal
        E_AR_Personal,
        // / 4x3 Panorama
        E_AR_4x3_PanScan,
        // / 4x3 Letter Box
        E_AR_4x3_LetterBox,
        // / 16x9 PillarBox
        E_AR_16x9_PillarBox,
        // / 16x9 PanScan
        E_AR_16x9_PanScan,
        // / 4x3 Combind
        E_AR_4x3_Combind,
        // / 16x9 Combind
        E_AR_16x9_Combind,
        // / Zoom 2X
        E_AR_Zoom_2x,
        // / Zoom 3X
        E_AR_Zoom_3x,
        // / Zoom 4X
        E_AR_Zoom_4x,
        // / Maximum value of this enum
        E_AR_MAX,
    }

    // / the display resolution type
    public enum EN_DISPLAY_RES_TYPE {
        DISPLAY_SEC32_LE32A_FULLHD,
        // For Normal LVDS panel
        DISPLAY_RES_SXGA, // /< 1280x1024, Pnl_AU17_EN05_SXGA
        DISPLAY_RES_WXGA, // /< 1366x768, Pnl_AU20_T200XW02_WXGA,
        DISPLAY_RES_WXGA_PLUS, // /< 1440x900, Pnl_CMO19_M190A1_WXGA,
        // Pnl_AU19PW01_WXGA
        DISPLAY_RES_WSXGA, // /< 1680x1050, Pnl_AU20_M201EW01_WSXGA,
        DISPLAY_RES_FULL_HD, // /< 1920x1080, Pnl_AU37_T370HW01_HD,
        // Pnl_CMO216H1_L01_HD.h
        // / Maximum value of this enum
        DISPLAY_DACOUT_576I, // pal
        DISPLAY_DACOUT_576P, DISPLAY_DACOUT_720P_50, DISPLAY_DACOUT_1080P_24, DISPLAY_DACOUT_1080P_25, DISPLAY_DACOUT_1080I_50, DISPLAY_DACOUT_1080P_50,

        DISPLAY_DACOUT_480I, // ntsc
        DISPLAY_DACOUT_480P, DISPLAY_DACOUT_720P_60, DISPLAY_DACOUT_1080P_30, DISPLAY_DACOUT_1080I_60, DISPLAY_DACOUT_1080P_60, DISPLAY_DACOUT_AUTO,

        DISPLAY_CMO_CMO260J2_WUXGA, // 1920*1200
        // For VGA OUTPUT 60HZ
        DISPLAY_VGAOUT_640x480P_60,
        // For VGA OUTPUT 50HZ
        // For TTL output
        DISPLAY_TTLOUT_480X272_60,

        DISPLAY_RES_MAX_NUM,
    }

    // / Video out VE type
    public enum MAPI_VIDEO_OUT_VE_SYS {
        MAPI_VIDEO_OUT_VE_NTSC, // /< NTSC
        MAPI_VIDEO_OUT_VE_PAL, // /< PAL_B
        MAPI_VIDEO_OUT_VE_AUTO, // /< Auto
    }

    // / Video standard
    public enum MAPI_AVD_VideoStandardType {
        // / Video standard PAL BGHI
        E_MAPI_VIDEOSTANDARD_PAL_BGHI,
        // / Video standard NTSC M
        E_MAPI_VIDEOSTANDARD_NTSC_M,
        // / Video standard SECAM
        E_MAPI_VIDEOSTANDARD_SECAM,
        // / Video standard NTSC 44
        E_MAPI_VIDEOSTANDARD_NTSC_44,
        // / Video standard PAL M
        E_MAPI_VIDEOSTANDARD_PAL_M,
        // / Video standard PAL N
        E_MAPI_VIDEOSTANDARD_PAL_N,
        // / Video standard PAL 60
        E_MAPI_VIDEOSTANDARD_PAL_60,
        // / NOT Video standard
        E_MAPI_VIDEOSTANDARD_NOTSTANDARD,
        // / Video standard AUTO
        E_MAPI_VIDEOSTANDARD_AUTO,
        // / Max Number
        E_MAPI_VIDEOSTANDARD_MAX
    }

    public enum AUDIOMODE_TYPE_ { // /< Audio Mode Invalid
        E_AUDIOMODE_INVALID_,
        // /< Audio Mode MONO
        E_AUDIOMODE_MONO_,
        // /< Audio Mode Forced MONO
        E_AUDIOMODE_FORCED_MONO_,
        // /< Audio Mode G Stereo
        E_AUDIOMODE_G_STEREO_,
        // /< Audio Mode K Stereo
        E_AUDIOMODE_K_STEREO_,
        // /< Audio Mode MONO SAP
        E_AUDIOMODE_MONO_SAP_,
        // /< Audio Mode Stereo SAP
        E_AUDIOMODE_STEREO_SAP_,
        // /< Audio Mode Dual A
        E_AUDIOMODE_DUAL_A_,
        // /< Audio Mode Dual B
        E_AUDIOMODE_DUAL_B_,
        // /< Audio Mode Dual AB
        E_AUDIOMODE_DUAL_AB_,
        // /< Audio Mode NICAM MONO
        E_AUDIOMODE_NICAM_MONO_,
        // /< Audio Mode NICAM Stereo
        E_AUDIOMODE_NICAM_STEREO_,
        // /< Audio Mode NICAM DUAL A
        E_AUDIOMODE_NICAM_DUAL_A_,
        // /< Audio Mode NICAM DUAL B
        E_AUDIOMODE_NICAM_DUAL_B_,
        // /< Audio Mode NICAM DUAL AB
        E_AUDIOMODE_NICAM_DUAL_AB_,
        // /< Audio Mode HIDEV MONO
        E_AUDIOMODE_HIDEV_MONO_,
        // /< Audio Mode Left left
        E_AUDIOMODE_LEFT_LEFT_,
        // /< Audio Mode right right
        E_AUDIOMODE_RIGHT_RIGHT_,
        // /< Audio Mode left right
        E_AUDIOMODE_LEFT_RIGHT_
    }

    /** dynamic contrast settings */
    public enum EN_MS_Dynamic_Contrast {
        // / Dynamic Contrast off
        MS_Dynamic_Contrast_OFF,
        // / Dynamic Contrast on
        MS_Dynamic_Contrast_ON,
        // / Dynamic Contrast type number
        MS_Dynamic_Contrast_NUM,
    }

    /** film mode settings */
    public enum EN_MS_FILM {
        // / FILM off
        MS_FILM_OFF,
        // / FILM on
        MS_FILM_ON,
        // / FILM number
        MS_FILM_NUM
    }

    /** 3D Video mode */
    public static enum EN_ThreeD_Video {
        DB_ThreeD_Video_OFF, DB_ThreeD_Video_2D_TO_3D, DB_ThreeD_Video_SIDE_BY_SIDE, DB_ThreeD_Video_TOP_BOTTOM, DB_ThreeD_Video_FRAME_INTERLEAVING, DB_ThreeD_Video_PACKING_1080at24p, DB_ThreeD_Video_PACKING_720at60p, DB_ThreeD_Video_PACKING_720at50p, DB_ThreeD_Video_CHESS_BOARD, DB_ThreeD_Video_COUNT
    }

    /** 3D Video self adaptive detect triple */
    public static enum EN_ThreeD_Video_SELFADAPTIVE_DETECT {
        // / 3D Self Adaptive detect Off
        DB_ThreeD_Video_SELF_ADAPTIVE_DETECT_OFF,
        // / 3D Self Adaptive detect Right Now
        DB_ThreeD_Video_SELF_ADAPTIVE_DETECT_RIGHT_NOW,
        // / 3D Self Adaptive detect When Source Change
        DB_ThreeD_Video_SELF_ADAPTIVE_DETECT_WHEN_SOURCE_CHANGE,
        // / 3D Self Adaptive detect real time
        DB_ThreeD_Video_SELF_ADAPTIVE_DETECT_REALTIME,
        // / total detect number
        DB_ThreeD_Video_DISPLAYFORMAT_COUNT
    }

    /** 3D Video self adaptive level */
    public static enum EN_ThreeD_Video_SELFADAPTIVE_LEVEL {
        // / 3D Self Adaptive level Low
        DB_ThreeD_Video_SELF_ADAPTIVE_LOW,
        // / 3D Self Adaptive level Middle
        DB_ThreeD_Video_SELF_ADAPTIVE_MIDDLE,
        // / 3D Self Adaptive level High
        DB_ThreeD_Video_SELF_ADAPTIVE_HIGH,
        // / total level number
        DB_ThreeD_Video_DISPLAYFORMAT_COUNT
    }

    /** 3D Video display format */
    public static enum EN_ThreeD_Video_DISPLAYFORMAT {
        // / 3D Off mode
        DB_ThreeD_Video_DISPLAYFORMAT_NONE,
        // / 3D Side By Side mode
        DB_ThreeD_Video_DISPLAYFORMAT_SIDE_BY_SIDE,
        // / 3D Top Bottom mode
        DB_ThreeD_Video_DISPLAYFORMAT_TOP_BOTTOM,
        // / 3D Frame Packing mode
        DB_ThreeD_Video_DISPLAYFORMAT_FRAME_PACKING,
        // / 3D Line Alternative mode
        DB_ThreeD_Video_DISPLAYFORMAT_LINE_ALTERNATIVE,
        // / 3D 2Dto3D mode
        DB_ThreeD_Video_DISPLAYFORMAT_2DTO3D,
        // / 3D Frame Alternative mode
        DB_ThreeD_Video_DISPLAYFORMAT_FRAME_ALTERNATIVE,
        // / 3D Auto mode
        DB_ThreeD_Video_DISPLAYFORMAT_AUTO,
        // / 3D Check Board mode
        DB_ThreeD_Video_DISPLAYFORMAT_CHECK_BOARD,
        // / 3D Pixel Alternative mode
        DB_ThreeD_Video_DISPLAYFORMAT_PIXEL_ALTERNATIVE,
        // / total format number
        DB_ThreeD_Video_DISPLAYFORMAT_COUNT
    }

    /** 3D Video 3dto2d mode */
    public static enum EN_ThreeD_Video_3DTO2D {
        // / 3Dto2D Off mode
        DB_ThreeD_Video_3DTO2D_NONE,
        // / 3Dto2D Side By Side mode
        DB_ThreeD_Video_3DTO2D_SIDE_BY_SIDE,
        // / 3Dto2D Top Bottom mode
        DB_ThreeD_Video_3DTO2D_TOP_BOTTOM,
        // / 3Dto2D Frame Packing mode
        DB_ThreeD_Video_3DTO2D_FRAME_PACKING,
        // / 3Dto2D Line Alternative mode
        DB_ThreeD_Video_3DTO2D_LINE_ALTERNATIVE,
        // / 3Dto2D Frame Alternative mode
        DB_ThreeD_Video_3DTO2D_FRAME_ALTERNATIVE,
        // / 3Dto2D Auto mode
        DB_ThreeD_Video_3DTO2D_AUTO,
        // / 3Dto2D Check Board mode
        DB_ThreeD_Video_3DTO2D_CHECK_BOARD,
        // / 3Dto2D Pixel Alternative mode
        DB_ThreeD_Video_3DTO2D_PIXEL_ALTERNATIVE,
        // / total mode number
        DB_ThreeD_Video_3DTO2D_COUNT
    }

    /** 3D Video 3D Depth */
    public enum EN_ThreeD_Video_3DDEPTH {
        // / 3D Depth level 0
        DB_ThreeD_Video_3DDEPTH_LEVEL_0,
        // / 3D Depth level 1
        DB_ThreeD_Video_3DDEPTH_LEVEL_1,
        // / 3D Depth level 2
        DB_ThreeD_Video_3DDEPTH_LEVEL_2,
        // / 3D Depth level 3
        DB_ThreeD_Video_3DDEPTH_LEVEL_3,
        // / 3D Depth level 4
        DB_ThreeD_Video_3DDEPTH_LEVEL_4,
        // / 3D Depth level 5
        DB_ThreeD_Video_3DDEPTH_LEVEL_5,
        // / 3D Depth level 6
        DB_ThreeD_Video_3DDEPTH_LEVEL_6,
        // / 3D Depth level 7
        DB_ThreeD_Video_3DDEPTH_LEVEL_7,
        // / 3D Depth level 8
        DB_ThreeD_Video_3DDEPTH_LEVEL_8,
        // / 3D Depth level 9
        DB_ThreeD_Video_3DDEPTH_LEVEL_9,
        // / 3D Depth level 10
        DB_ThreeD_Video_3DDEPTH_LEVEL_10,
        // / 3D Depth level 11
        DB_ThreeD_Video_3DDEPTH_LEVEL_11,
        // / 3D Depth level 12
        DB_ThreeD_Video_3DDEPTH_LEVEL_12,
        // / 3D Depth level 13
        DB_ThreeD_Video_3DDEPTH_LEVEL_13,
        // / 3D Depth level 14
        DB_ThreeD_Video_3DDEPTH_LEVEL_14,
        // / 3D Depth level 15
        DB_ThreeD_Video_3DDEPTH_LEVEL_15,
        // / 3D Depth level 16
        DB_ThreeD_Video_3DDEPTH_LEVEL_16,
        // / 3D Depth level 17
        DB_ThreeD_Video_3DDEPTH_LEVEL_17,
        // / 3D Depth level 18
        DB_ThreeD_Video_3DDEPTH_LEVEL_18,
        // / 3D Depth level 19
        DB_ThreeD_Video_3DDEPTH_LEVEL_19,
        // / 3D Depth level 20
        DB_ThreeD_Video_3DDEPTH_LEVEL_20,
        // / 3D Depth level 21
        DB_ThreeD_Video_3DDEPTH_LEVEL_21,
        // / 3D Depth level 22
        DB_ThreeD_Video_3DDEPTH_LEVEL_22,
        // / 3D Depth level 23
        DB_ThreeD_Video_3DDEPTH_LEVEL_23,
        // / 3D Depth level 24
        DB_ThreeD_Video_3DDEPTH_LEVEL_24,
        // / 3D Depth level 25
        DB_ThreeD_Video_3DDEPTH_LEVEL_25,
        // / 3D Depth level 26
        DB_ThreeD_Video_3DDEPTH_LEVEL_26,
        // / 3D Depth level 27
        DB_ThreeD_Video_3DDEPTH_LEVEL_27,
        // / 3D Depth level 28
        DB_ThreeD_Video_3DDEPTH_LEVEL_28,
        // / 3D Depth level 29
        DB_ThreeD_Video_3DDEPTH_LEVEL_29,
        // / 3D Depth level 30
        DB_ThreeD_Video_3DDEPTH_LEVEL_30,
        // / 3D Depth level 31
        DB_ThreeD_Video_3DDEPTH_LEVEL_31,
        // / 3D Depth total number
        DB_ThreeD_Video_3DDEPTH_COUNT
    }

    /** 3D Video 3D Offset */
    public enum EN_ThreeD_Video_3DOFFSET {
        // / 3D Offset level 0
        DB_ThreeD_Video_3DOFFSET_LEVEL_0,
        // / 3D Offset level 1
        DB_ThreeD_Video_3DOFFSET_LEVEL_1,
        // / 3D Offset level 2
        DB_ThreeD_Video_3DOFFSET_LEVEL_2,
        // / 3D Offset level 3
        DB_ThreeD_Video_3DOFFSET_LEVEL_3,
        // / 3D Offset level 4
        DB_ThreeD_Video_3DOFFSET_LEVEL_4,
        // / 3D Offset level 5
        DB_ThreeD_Video_3DOFFSET_LEVEL_5,
        // / 3D Offset level 6
        DB_ThreeD_Video_3DOFFSET_LEVEL_6,
        // / 3D Offset level 7
        DB_ThreeD_Video_3DOFFSET_LEVEL_7,
        // / 3D Offset level 8
        DB_ThreeD_Video_3DOFFSET_LEVEL_8,
        // / 3D Offset level 9
        DB_ThreeD_Video_3DOFFSET_LEVEL_9,
        // / 3D Offset level 10
        DB_ThreeD_Video_3DOFFSET_LEVEL_10,
        // / 3D Offset level 11
        DB_ThreeD_Video_3DOFFSET_LEVEL_11,
        // / 3D Offset level 12
        DB_ThreeD_Video_3DOFFSET_LEVEL_12,
        // / 3D Offset level 13
        DB_ThreeD_Video_3DOFFSET_LEVEL_13,
        // / 3D Offset level 14
        DB_ThreeD_Video_3DOFFSET_LEVEL_14,
        // / 3D Offset level 15
        DB_ThreeD_Video_3DOFFSET_LEVEL_15,
        // / 3D Offset level 16
        DB_ThreeD_Video_3DOFFSET_LEVEL_16,
        // / 3D Offset level 17
        DB_ThreeD_Video_3DOFFSET_LEVEL_17,
        // / 3D Offset level 18
        DB_ThreeD_Video_3DOFFSET_LEVEL_18,
        // / 3D Offset level 19
        DB_ThreeD_Video_3DOFFSET_LEVEL_19,
        // / 3D Offset level 20
        DB_ThreeD_Video_3DOFFSET_LEVEL_20,
        // / 3D Offset level 21
        DB_ThreeD_Video_3DOFFSET_LEVEL_21,
        // / 3D Offset level 22
        DB_ThreeD_Video_3DOFFSET_LEVEL_22,
        // / 3D Offset level 23
        DB_ThreeD_Video_3DOFFSET_LEVEL_23,
        // / 3D Offset level 24
        DB_ThreeD_Video_3DOFFSET_LEVEL_24,
        // / 3D Offset level 25
        DB_ThreeD_Video_3DOFFSET_LEVEL_25,
        // / 3D Offset level 26
        DB_ThreeD_Video_3DOFFSET_LEVEL_26,
        // / 3D Offset level 27
        DB_ThreeD_Video_3DOFFSET_LEVEL_27,
        // / 3D Offset level 28
        DB_ThreeD_Video_3DOFFSET_LEVEL_28,
        // / 3D Offset level 29
        DB_ThreeD_Video_3DOFFSET_LEVEL_29,
        // / 3D Offset level 30
        DB_ThreeD_Video_3DOFFSET_LEVEL_30,
        // / 3D Offset level 31
        DB_ThreeD_Video_3DOFFSET_LEVEL_31,
        // / 3D Offset total number
        DB_ThreeD_Video_3DOFFSET_COUNT
    }

    /** 3D Video Auto Start */
    public enum EN_ThreeD_Video_AUTOSTART {
        // /auto start off
        DB_ThreeD_Video_AUTOSTART_OFF,
        // /auto start 2D
        DB_ThreeD_Video_AUTOSTART_2D,
        // /auto start 3D
        DB_ThreeD_Video_AUTOSTART_3D,
        // /auto start total number
        DB_ThreeD_Video_AUTOSTART_COUNT
    }

    /** 3D Video 3D Output Aspect */
    public static enum EN_ThreeD_Video_3DOUTPUTASPECT {
        // / 3D output aspect in fullscreen
        DB_ThreeD_Video_3DOUTPUTASPECT_FULLSCREEN,
        // / 3D output aspect in center
        DB_ThreeD_Video_3DOUTPUTASPECT_CENTER,
        // / 3D output aspect in auto adapted
        DB_ThreeD_Video_3DOUTPUTASPECT_AUTOADAPTED,
        // / 3D output aspect total number
        DB_ThreeD_Video_3DOUTPUTASPECT_COUNT
    }

    /** 3D Video LR View Switch */
    public enum EN_ThreeD_Video_LRVIEWSWITCH {
        // / 3D Left Right exchanging
        DB_ThreeD_Video_LRVIEWSWITCH_EXCHANGE,
        // / 3D Left Right not exchanging
        DB_ThreeD_Video_LRVIEWSWITCH_NOTEXCHANGE,
        // / 3D Left Right exchanging total number
        DB_ThreeD_Video_LRVIEWSWITCH_COUNT
    }

    /** setting of 3D Video */
    public class ThreeD_Video_MODE {
        // / 3D Video Setting
        public EN_ThreeD_Video eThreeDVideo;

        // / 3D Video Self Adaptive Detect
        public EN_ThreeD_Video_SELFADAPTIVE_DETECT eThreeDVideoSelfAdaptiveDetect;

        // / 3D Video Self Adaptive Level
        // public EN_ThreeD_Video_SELFADAPTIVE_LEVEL
        // eThreeDVideoSelfAdaptiveLevel;
        // / 3D Video 3D Conversion
        public EN_ThreeD_Video_DISPLAYFORMAT eThreeDVideoDisplayFormat;

        // / 3D Video 3Dto2D
        public EN_ThreeD_Video_3DTO2D eThreeDVideo3DTo2D;

        // / 3D Video 3D Depth
        public EN_ThreeD_Video_3DDEPTH eThreeDVideo3DDepth;

        // / 3D Video 3D Offset
        public EN_ThreeD_Video_3DOFFSET eThreeDVideo3DOffset;

        // / 3D Video Auto Start
        public EN_ThreeD_Video_AUTOSTART eThreeDVideoAutoStart;

        // / 3D Video 3D Output Aspect
        public EN_ThreeD_Video_3DOUTPUTASPECT eThreeDVideo3DOutputAspect;

        // / 3D Video 3D Left Rigth View Switch
        public EN_ThreeD_Video_LRVIEWSWITCH eThreeDVideoLRViewSwitch;

        public ThreeD_Video_MODE(
                EN_ThreeD_Video eValue1,
                EN_ThreeD_Video_SELFADAPTIVE_DETECT eValue2,
                // EN_ThreeD_Video_SELFADAPTIVE_LEVEL eValue3,
                EN_ThreeD_Video_DISPLAYFORMAT eValue4, EN_ThreeD_Video_3DTO2D eValue5,
                EN_ThreeD_Video_3DDEPTH eValue6, EN_ThreeD_Video_3DOFFSET eValue7,
                EN_ThreeD_Video_AUTOSTART eValue8, EN_ThreeD_Video_3DOUTPUTASPECT eValue9,
                EN_ThreeD_Video_LRVIEWSWITCH eValue10) {
            this.eThreeDVideo = eValue1;
            this.eThreeDVideoSelfAdaptiveDetect = eValue2;
            // this.eThreeDVideoSelfAdaptiveLevel = eValue3;
            this.eThreeDVideoDisplayFormat = eValue4;
            this.eThreeDVideo3DTo2D = eValue5;
            this.eThreeDVideo3DDepth = eValue6;
            this.eThreeDVideo3DOffset = eValue7;
            this.eThreeDVideoAutoStart = eValue8;
            this.eThreeDVideo3DOutputAspect = eValue9;
            this.eThreeDVideoLRViewSwitch = eValue10;
        }
    }

    /** define detail setting of picture mode */
    public class T_MS_PICTURE {
        // / backlight
        public short backlight;

        // / contrast
        public short contrast;

        // / brightness
        public short brightness;

        // / Saturation
        public short saturation;

        // / Sharpness
        public short sharpness;

        // / Hue
        public short hue;

        // / color temperature setting
        public EN_MS_COLOR_TEMP eColorTemp;

        public EN_MS_PIC_ADV eVibrantColour;

        public EN_MS_PIC_ADV ePerfectClear;

        public EN_MS_PIC_ADV eDynamicContrast;

        public EN_MS_PIC_ADV eDynamicBacklight;

        public T_MS_PICTURE(short backlight, short con, short bri, short sat, short sha, short hue,
                EN_MS_COLOR_TEMP colortemp, EN_MS_PIC_ADV evcolor, EN_MS_PIC_ADV epClear,
                EN_MS_PIC_ADV edcontrast, EN_MS_PIC_ADV edbackling) {
            this.backlight = backlight;
            this.contrast = con;
            this.brightness = bri;
            this.saturation = sat;
            this.sharpness = sha;
            this.hue = hue;
            this.eColorTemp = colortemp;
            this.eVibrantColour = evcolor;
            this.ePerfectClear = epClear;
            this.eDynamicContrast = edcontrast;
            this.eDynamicBacklight = edbackling;
        }
    } // T_MS_PICTURE

    /** overscan setting */
    public class T_MS_OVERSCAN_SETTING_USER {
        // /overscanHposition
        public short OverScanHposition;

        // /overscanVposition
        public short OverScanVposition;

        // /OverScanHRatio
        public short OverScanHRatio;

        // /OverScanVRatio
        public short OverScanVRatio;

        public T_MS_OVERSCAN_SETTING_USER(short x, short y, short w, short h) {
            this.OverScanHposition = x;
            this.OverScanVposition = y;
            this.OverScanHRatio = w;
            this.OverScanVRatio = h;
        }
    }

    // / Receiver TV format
    public static enum EN_DISPLAY_TVFORMAT {
        DISPLAY_TVFORMAT_4TO3, DISPLAY_TVFORMAT_16TO9SD, DISPLAY_TVFORMAT_16TO9HD, DISPLAY_TVFORMAT_COUNT,
    }

    /** define video setting for */
    public class T_MS_VIDEO {
        // / check sum <<checksum should be put at top of the struct, do not
        // move it to other place>>
        public int CheckSum;

        // / picture mode setting
        public EN_MS_PICTURE ePicture;

        // / picture mode detail setting, 24Byte
        public T_MS_PICTURE astPicture[];

        // / enum for noise reduction and mpeg noise reduction
        public T_MS_NR_MODE eNRMode[];

        // / SubColor Setting
        public T_MS_SUB_COLOR g_astSubColor;

        // / Aspect ratio type
        public MAPI_VIDEO_ARC_Type enARCType;

        // / resolution type setting
        public EN_DISPLAY_RES_TYPE fOutput_RES;

        // / tv system setting
        public MAPI_VIDEO_OUT_VE_SYS tvsys;

        // / last video standard mode setting
        public MAPI_AVD_VideoStandardType LastVideoStandardMode;

        // / last audio standard mode setting
        public AUDIOMODE_TYPE_ LastAudioStandardMode;

        // / dynamic contrast mode
        public EN_MS_Dynamic_Contrast eDynamic_Contrast;

        // / film mode
        public EN_MS_FILM eFilm;

        // / ThreeD_Video
        public ThreeD_Video_MODE ThreeDVideoMode;

        // /useroverscan_setting
        public T_MS_OVERSCAN_SETTING_USER stUserOverScanMode;

        // /// SUPER
        // SUPER_MODE SUPERMode;
        // / TV format setting (4:3/16:9SD/16:9HD)
        public EN_DISPLAY_TVFORMAT eTvFormat;

        public int bIsPcMode;
    }

    /** video adjust item settings */
    public enum EN_MS_VIDEOITEM {
        // / brightness
        MS_VIDEOITEM_BRIGHTNESS,
        // / contrast
        MS_VIDEOITEM_CONTRAST,
        // / saturation
        MS_VIDEOITEM_SATURATION,
        // / sharpness
        MS_VIDEOITEM_SHARPNESS,
        // / hue
        MS_VIDEOITEM_HUE,
        // / backlight
        MS_VIDEOITEM_BACKLIGHT,
        // num of video item
        MS_VIDEOITEM_NUM
    }

    public class T_MS_COLOR_TEMP_DATA {
        public short redgain;

        public short greengain;

        public short bluegain;

        public short redoffset;

        public short greenoffset;

        public short blueoffset;

        public T_MS_COLOR_TEMP_DATA(short v1, short v2, short v3, short v4, short v5, short v6) {
            this.redgain = v1;
            this.greengain = v2;
            this.bluegain = v3;
            this.redoffset = v4;
            this.greenoffset = v5;
            this.blueoffset = v6;
        }
    }

    // / define color temperature mode setting
    public class T_MS_COLOR_TEMP {
        // / check sum <<checksum should be put at top of the struct, do not
        // move it to other place>>
        public int u16CheckSum;

        // / color temperature mode setting
        public T_MS_COLOR_TEMP_DATA astColorTemp[]; // 24Byte

        public T_MS_COLOR_TEMP() {
            int i;
            astColorTemp = new T_MS_COLOR_TEMP_DATA[EN_MS_COLOR_TEMP.MS_COLOR_TEMP_NUM.ordinal()];
            for (i = 0; i < EN_MS_COLOR_TEMP.MS_COLOR_TEMP_NUM.ordinal(); i++) {
                astColorTemp[i] = new T_MS_COLOR_TEMP_DATA((short) 0x80, (short) 0x80,
                        (short) 0x80, (short) 0x80, (short) 0x80, (short) 0x80);
            }
            u16CheckSum = 0xFFFF;
        }
    }

    public class T_MS_COLOR_TEMPEX_DATA {
        public int redgain;

        public int greengain;

        public int bluegain;

        public int redoffset;

        public int greenoffset;

        public int blueoffset;

        public T_MS_COLOR_TEMPEX_DATA(int v1, int v2, int v3, int v4, int v5, int v6) {
            this.redgain = v1;
            this.greengain = v2;
            this.bluegain = v3;
            this.redoffset = v4;
            this.greenoffset = v5;
            this.blueoffset = v6;
        }
    }

    // / define color temperature mode setting
    public class T_MS_COLOR_TEMPEX {
        // / check sum <<checksum should be put at top of the struct, do not
        // move it to other place>>
        public int u16CheckSum;

        // / color temperature mode setting
        public T_MS_COLOR_TEMPEX_DATA astColorTempEx[][]; // 24Byte

        public T_MS_COLOR_TEMPEX() {
            int i;
            int j;
            astColorTempEx = new T_MS_COLOR_TEMPEX_DATA[EN_MS_COLOR_TEMP.MS_COLOR_TEMP_NUM
                    .ordinal()][EN_MS_COLOR_TEMP_INPUT_SOURCE.E_INPUT_SOURCE_NUM.ordinal()];
            for (i = 0; i < EN_MS_COLOR_TEMP.MS_COLOR_TEMP_NUM.ordinal(); i++) {
                for (j = 0; j < EN_MS_COLOR_TEMP_INPUT_SOURCE.E_INPUT_SOURCE_NUM.ordinal(); j++) {
                    astColorTempEx[i][j] = new T_MS_COLOR_TEMPEX_DATA(0x80, 0x80, 0x80, 0x80, 0x80,
                            0x80);
                }
            }
            u16CheckSum = 0xFFFF;
        }
    }

    // / ADC setting index
    public static enum E_ADC_SET_INDEX {
        // / ADC setting VGA
        ADC_SET_VGA,
        // / ADC setting YPBPR_SD
        ADC_SET_YPBPR_SD,
        // / ADC setting YPBPR_HD
        ADC_SET_YPBPR_HD,
        // / ADC setting ADC_SET_SCART_RGB
        ADC_SET_SCART_RGB,
        // / ADC setting YPBPR2_SD
        ADC_SET_YPBPR2_SD,
        // / ADC setting YPBPR2_HD
        ADC_SET_YPBPR2_HD,
        // / ADC setting YPBPR3_SD
        ADC_SET_YPBPR3_SD,
        // / ADC setting YPBPR3_HD
        ADC_SET_YPBPR3_HD,
        // / ADC setting number
        ADC_SET_NUMS
    }

    public class T_MS_CALIBRATION_DATA {
        public int redgain;

        public int greengain;

        public int bluegain;

        public int redoffset;

        public int greenoffset;

        public int blueoffset;

        public T_MS_CALIBRATION_DATA(int v1, int v2, int v3, int v4, int v5, int v6) {
            this.redgain = v1;
            this.greengain = v2;
            this.bluegain = v3;
            this.redoffset = v4;
            this.greenoffset = v5;
            this.blueoffset = v6;
        }
    }

    public class MS_ADC_SETTING {
        // / check sum <<checksum should be put at top of the struct, do not
        // move it to other place>>
        public int u16CheckSum;

        // / gain, offset setting for ADC
        public T_MS_CALIBRATION_DATA stAdcGainOffsetSetting[];

        public MS_ADC_SETTING() {
            int i;
            stAdcGainOffsetSetting = new T_MS_CALIBRATION_DATA[E_ADC_SET_INDEX.ADC_SET_NUMS
                    .ordinal()];
            for (i = 0; i < E_ADC_SET_INDEX.ADC_SET_NUMS.ordinal(); i++) {
                stAdcGainOffsetSetting[i] = new T_MS_CALIBRATION_DATA((short) 0x80, (short) 0x80,
                        (short) 0x80, (short) 0x80, (short) 0x80, (short) 0x80);
            }
        }
    }

    // / Define 3D input type
    public static enum EN_3D_INPUT_TYPE {
        // range [0000 ~ 1111] reserved for hdmi 3D spec
        // / 3D input type is Frame Packing
        E_3D_INPUT_FRAME_PACKING, // 0000
        // / 3D input type is Field Alternative
        E_3D_INPUT_FIELD_ALTERNATIVE, // 0001
        // / 3D input type is Line Alternativ
        E_3D_INPUT_LINE_ALTERNATIVE, // 0010
        // / 3D input type is Side Full
        E_3D_INPUT_SIDE_BY_SIDE_FULL, // 0011
        // / 3D input type is L Depth
        E_3D_INPUT_L_DEPTH, // 0100
        // / 3D input type is LDepth Graphics Graphics Depth
        E_3D_INPUT_L_DEPTH_GRAPHICS_GRAPHICS_DEPTH, // 0101
        // / 3D input type is Top and Bottom
        E_3D_INPUT_TOP_BOTTOM, // 0110
        // / 3D input type is Side by Side Half
        E_3D_INPUT_SIDE_BY_SIDE_HALF, // 1000
        // / 3D input type is Check board
        E_3D_INPUT_CHECK_BORAD,
        // / 3D input type is User
        E_3D_INPUT_MODE_USER,
        // / 3D input type is Frame Alternative
        E_3D_INPUT_FRAME_ALTERNATIVE,
        // / 3D input type is Side by Side Half Interlace
        E_3D_INPUT_SIDE_BY_SIDE_HALF_INTERLACE,
        // / 3D input type is Frame Packing Opt
        E_3D_INPUT_FRAME_PACKING_OPT,
        // / 3D input type is Top and Bottom Opt
        E_3D_INPUT_TOP_BOTTOM_OPT,
        // / 3D input type is Normal 2d
        E_3D_INPUT_NORMAL_2D,
        // / 3D input type is Normal 2d interlace
        E_3D_INPUT_NORMAL_2D_INTERLACE,
        // / 3D input type is normal 2d interlace point to point
        E_3D_INPUT_NORMAL_2D_INTERLACE_PTP,
        // / 3D input type is HW Normal 2d
        E_3D_INPUT_NORMAL_2D_HW,
        // / Maximum number of 3D input type
        E_3D_INPUT_TYPE_NUM,
    }

    // / Define 3D output type
    public static enum EN_3D_OUTPUT_TYPE {
        // / 3D input type is None
        E_3D_OUTPUT_MODE_NONE,
        // / 3D input type is Line Alternativ
        E_3D_OUTPUT_LINE_ALTERNATIVE,
        // / 3D input type is Top and Bottom
        E_3D_OUTPUT_TOP_BOTTOM,
        // / 3D input type is side by Side Half
        E_3D_OUTPUT_SIDE_BY_SIDE_HALF,
        // / 3D input type is Frame Alternativ
        E_3D_OUTPUT_FRAME_ALTERNATIVE, // 25-->50,30-->60,24-->48,50-->100,60-->120----FRC
        // 1:2
        // / 3D input type is Frame L
        E_3D_OUTPUT_FRAME_L,
        // / 3D input type is Frame R
        E_3D_OUTPUT_FRAME_R,
        // / 3D input type is Frame Alternative NoRFC
        E_3D_OUTPUT_FRAME_ALTERNATIVE_NOFRC, // 50->50,
        // 60->60-------------------------------FRC
        // 1:1
        // / 3D input type is HW Line Alternativ
        E_3D_OUTPUT_LINE_ALTERNATIVE_HW,
        // / Maximum number of 3D output type
        E_3D_OUTPUT_TYPE_NUM,
    }

    // / Define 3D info
    public class ST_MAPI_3D_INFO {
        // / Enable / Disable 3D feature
        boolean bEnable3D;

        // / 3D input type
        EN_3D_INPUT_TYPE enInput3DMode;

        // / 3D output type
        EN_3D_OUTPUT_TYPE enOutput3DMode;

        public ST_MAPI_3D_INFO(boolean bEnable, EN_3D_INPUT_TYPE eInput, EN_3D_OUTPUT_TYPE eOutput) {
            this.bEnable3D = bEnable;
            this.enInput3DMode = eInput;
            this.enOutput3DMode = eOutput;
        }
    }

    // / 3D aspect ratio type
    public enum MAPI_VIDEO_3D_ARC_Type {
        E_3D_AR_FULL, // full screen
        E_3D_AR_AUTO, // auto adapt
        E_3D_AR_CENTER, // centering
    }

    /** MFC mode */
    public static enum EN_MFC {
        MS_MFC_OFF, MS_MFC_LOW, MS_MFC_HIGH, MS_MFC_COUNT
    }

    /** setting of MFC */
    public class MFC_MODE {
        // / MFC setting
        EN_MFC eMFC;

        public MFC_MODE(EN_MFC eMode) {
            this.eMFC = eMode;
        }
    }

    // =========================================================================
    // ==================== User Setting =====================================
    /** the user settings */
    public static enum MEMBER_COUNTRY {
        E_AUSTRALIA, // /< Australia
        E_AUSTRIA, // /< Austria
        E_BELGIUM, // /< Belgium
        E_BULGARIA, // /< Bulgaria
        E_CROATIA, // /< Croatia
        E_CZECH, // /< Czech
        E_DENMARK, // /< Denmark
        E_FINLAND, // /< Finland
        E_FRANCE, // /< France
        E_GERMANY, // /< Germany
        E_GREECE, // /< Greece
        E_HUNGARY, // /< Hungary
        E_ITALY, // /< Italy
        E_LUXEMBOURG, // /< Luxembourg
        E_NETHERLANDS, // /< Netherland
        E_NORWAY, // /< Norway
        E_POLAND, // /< Poland
        E_PORTUGAL, // /< Portugal
        E_RUMANIA, // /< Rumania
        E_RUSSIA, // /< Russia
        E_SERBIA, // /< Serbia
        E_SLOVENIA, // /< Slovenia
        E_SPAIN, // /< Spain
        E_SWEDEN, // /< Sweden
        E_SWITZERLAND, // /< Switzerland
        E_UK, // /< UK
        E_NEWZEALAND, // /< New Zealand
        E_ARAB, // /< Arab
        E_ESTONIA, // /< Estonia
        E_HEBREW, // /< Hebrew
        E_LATVIA, // /< Latvia
        E_SLOVAKIA, // /< Slovakia
        E_TURKEY, // /< Turkey
        E_IRELAND, // /< Ireland
        E_JAPAN, // /<Japan
        E_PHILIPPINES, // /<Philippines
        E_THAILAND, // /<Thailand
        E_MALDIVES, // /<Maldives
        E_URUGUAY, // /<Uruguay
        E_PERU, // /<Peru
        E_ARGENTINA, // /<Argentina
        E_CHILE, // /<Chile
        E_VENEZUELA, // /Venezuela
        E_ECUADOR, // /<Ecuador
        E_COSTARICA, // /<Costarica
        E_PARAGUAY, // /<Paraguay
        E_BOLIVIA, // /<Bolivia
        E_BELIZE, // /<Belize
        E_NICARAGUA, // /<Nicaragua
        E_GUATEMALA, // /<Guatemala
        E_CHINA, // /< China
        E_TAIWAN, // /< Taiwan
        E_BRAZIL, // /< Brazil
        /* ATSC Country Start */
        E_CANADA, // /< Canada
        E_MEXICO, // /< Mexico
        E_US, // /< United States
        E_SOUTHKOREA, // /< South Korea
        /* ATSC Country End */
        E_OTHERS, // /< Others
        // ------------------------------------
        E_COUNTRY_NUM,
    }

    public static enum EN_CABLE_OPERATORS {
        EN_CABLEOP_OTHER, EN_CABLEOP_CDSMATV, EN_CABLEOP_CDCABLE, EN_CABLEOP_COMHEM, EN_CABLEOP_UPC, EN_CABLEOP_YOUSEE, EN_CABLEOP_CABLEREADY, EN_CABLEOP_ZIGGO,
        // -------------------------------------
        EN_CABLEOP_NUM,
    }

    public static enum EN_SATELLITE_PLATFORM {
        EN_SATEPF_OTHER, EN_SATEPF_HDPLUS, EN_SATEPF_FREESAT,
    }

    // ISO 639: 3-letter codes
    // public static enum MEMBER_LANGUAGE
    // {
    // E_LANGUAGE_ENGLISH, E_LANGUAGE_CHINESE_S, E_LANGUAGE_CHINESE_T,
    // E_LANGUAGE_MAX
    // // maximum
    // }

    public class MS_USER_SUBTITLE_SETTING {
        // / user subtitle language 1
        public EnumLanguage SubtitleDefaultLanguage;

        // / user subtitle language 2
        public EnumLanguage SubtitleDefaultLanguage_2;

        // / HardOfHearing setting, 0=Off, 1= On
        public boolean fHardOfHearing;

        // / subtitle enable or not, 0=Off, 1= On
        public boolean fEnableSubTitle;

        public MS_USER_SUBTITLE_SETTING(EnumLanguage eLang1, EnumLanguage eLang2, boolean bHearing,
                boolean bSubtitle) {
            this.SubtitleDefaultLanguage = eLang1;
            this.SubtitleDefaultLanguage_2 = eLang2;
            this.fHardOfHearing = bHearing;
            this.fEnableSubTitle = bSubtitle;
        }
    }

    // / SPDIF Tx Mode Type
    public static enum SPDIF_TYPE {
        // / SPDIF output type is PCM
        MSAPI_AUD_SPDIF_PCM_,
        // / SPDIF output software OFF, don't use
        MSAPI_AUD_SPDIF_OFF_,
        // / SPDIF output type is nonPCM
        MSAPI_AUD_SPDIF_NONPCM_,
    }

    /** define user location */
    public class MS_USER_LOCATION_SETTING {
        // / the ID of Location.
        public int mLocationNo;

        // / the Longitude value
        public int mManualLongitude;

        // / the Latitude value
        public int mManualLatitude;

        public MS_USER_LOCATION_SETTING(int v1, int v2, int v3) {
            this.mLocationNo = v1;
            this.mManualLongitude = v2;
            this.mManualLatitude = v3;
        }
    }

    /** Super mode settings */
    public static enum EN_MS_SUPER {
        // / SUPER min
        MS_SUPER_MIN,
        // / SUPER off
        MS_SUPER_OFF, // MS_Dynamic_Contrast_MIN,
        // / SUPER on
        MS_SUPER_ON,
        // / SUPER number
        MS_SUPER_NUM,
    }

    /** Super mode settings */
    public static enum EN_MS_CHANNEL_SWITCH_MODE {
        // / Black Screen Channel Switch
        MS_CHANNEL_SWM_BLACKSCREEN,
        // / Freeze Channel Switch
        MS_CHANNEL_SWM_FREEZE,
        // / Channel Switch number
        MS_CHANNEL_SWM_NUM,
    }

    public static enum EN_MS_OFFLINE_DET_MODE {
        // / Offline detection off
        MS_OFFLINE_DET_OFF,
        // / Offline detection on indication mode
        MS_COFFLINE_DET_INDICATION,
        // / Offline detection on auto mode
        MS_COFFLINE_DET_AUTO,
        // / Offline detection number
        MS_OFFLINE_DET_NUM,
    }

    public static enum EN_MS_POWERON_MUSIC {
        // / Power On Music off
        MS_POWERON_MUSIC_OFF,
        // / Power On Music by Default
        MS_POWERON_MUSIC_DEFAULT,
        // / Power On Music by User
        MS_POWERON_MUSIC_USER,
        // / Power On Music number
        MS_POWERON_MUSIC_NUM,
    }

    public static enum EN_MS_POWERON_LOGO {
        // / Power On Music off
        MS_POWERON_LOGO_OFF,
        // / Power On Music by Default
        MS_POWERON_LOGO_DEFAULT,
        // / Power On Music by User
        MS_POWERON_LOGO_USER,
        // / Power On Music number
        MS_POWERON_LOGO_NUM,
    }

    public class MS_USER_SYSTEM_SETTING {
        // / check sum <<checksum should be put at top of the struct, do not
        // move it to other place>>
        public int checkSum;

        // / check to run InstallationGuide or not
        public boolean fRunInstallationGuide;

        // / check if no channel to show banner
        public boolean fNoChannel;

        // / check SI auto update off or not,CableReady Manual scanning shall
        // set SI updates to "OFF" on all physical channels.
        public boolean bDisableSiAutoUpdate;

        // / input source selection
        public EnumInputSource enInputSourceType;

        // / country setting
        public MEMBER_COUNTRY Country;

        // / Cable Operator setting
        public EN_CABLE_OPERATORS enCableOperators;

        // / Satellite platform setting
        public EN_SATELLITE_PLATFORM enSatellitePlatform;

        // / OSD language
        public EnumLanguage enLanguage;

        // / subtitle language setting
        // public MS_USER_SUBTITLE_SETTING stSubtitleSetting; -->>>Indepent
        // / color temperature setting
        // MAPI_PQL_COLOR_TEMP_DATA stUserColorTemp;
        // / color temperature setting for dvbt
        // MAPI_PQL_COLOR_TEMPEX_DATA
        // stUserColorTempEx[MS_INPUT_SOURCE_TYPE_NUM]; //Dawn :color temp for
        // each source type
        // / 3D settings
        // ST_MAPI_3D_INFO st3DInfo;
        // / 3D ARC
        // mapi_video::MAPI_VIDEO_3D_ARC_Type en3DARC;
        // / SPDIF mode setting
        public SPDIF_TYPE enSPDIFMODE;

        // / user location setting
        // public MS_USER_LOCATION_SETTING stUserLocationSetting; --->>>Indepent
        // / SoftwareUpdate 0=Off, 1= On
        public short fSoftwareUpdate;

        // / OAD Upddate Time
        public short u8OADTime;

        // / OAD Scan auto execution after system bootup 0=Off, 1=On
        public short fOADScanAfterWakeup;

        // / autovolume 0=Off, 1= On
        public short fAutoVolume;

        // / DcPowerOFFMode 0= Power Off, 1= DC Power Off
        public short fDcPowerOFFMode;

        // / DTV Player Extend
        public short DtvRoute;

        // / SCART output RGB
        public short ScartOutRGB;

        // / OSD Transparency, 0=0%, 1=25%, 2=50%, 3=75%, 4=100%
        public short U8Transparency;

        // / OSD timeout (seconds)
        public long u32MenuTimeOut;

        // / Audio Only
        public short AudioOnly;

        // / watch dog
        public short bEnableWDT;

        // /Favorite Network Region
        public short u8FavoriteRegion;

        // /Bandwidth
        public short u8Bandwidth;

        // /Time Shift Size Type
        public short u8TimeShiftSizeType;

        // /Do OAD scan right now
        public short fOadScan;

        // /PVR Record All Enable in factory menu
        public short bEnablePVRRecordAll;

        // /Color range mode 0-255\16-235 for HDMI
        public short u8ColorRangeMode;

        // /HDMI Audio Source 0: DVD 1: PC
        public short u8HDMIAudioSource;

        // / PVR enable always timeshift (1 bit, union with bEnablePVRRecordAll
        // if needed)
        public short bEnableAlwaysTimeshift;

        // / MFC
        // MFC_MODE MfcMode;
        // / enum for SUPER
        public EN_MS_SUPER eSUPER;

        // DVBS2 multi-favorite list
        // /Favorite type name
        // U8 FavTypeName[MAX_FAVLIST_NUM][MAX_FAVTYPE_NAME_SIZE];
        // / check to Uart Bus
        public boolean bUartBus;

        // /For DTV AutoZoom
        public short m_AutoZoom;

        // OverScan on/off for all source
        public boolean bOverScan;

        // / 3D settings
        // public MS_3D_SETTING st3DSetting;
        // / Brazil video system
        public short m_u8BrazilVideoStandardType;

        public short m_u8SoftwareUpdateMode;

        // #if 1
        // ///For rename inputSource TV
        // char InputSourceName_TV[INPUTSOURCE_NAME_MAX_SIZE];
        // ///For rename inputSource Componant1
        // char InputSourceName_Componant1[INPUTSOURCE_NAME_MAX_SIZE];
        // ///For rename inputSource PC_RGB
        // char InputSourceName_PC_RGB[INPUTSOURCE_NAME_MAX_SIZE];
        // ///For rename inputSource HDMI1
        // char InputSourceName_HDMI1[INPUTSOURCE_NAME_MAX_SIZE];
        // ///For rename inputSource HDMI2
        // char InputSourceName_HDMI2[INPUTSOURCE_NAME_MAX_SIZE];
        // ///For rename inputSource HDMI3
        // char InputSourceName_HDMI3[INPUTSOURCE_NAME_MAX_SIZE];
        // ///For rename inputSource AV1
        // char InputSourceName_AV1[INPUTSOURCE_NAME_MAX_SIZE];
        // #endif
        public long u32OSD_Active_Time;

        public boolean m_MessageBoxExist;// 0 not exit 1 exit

        // /OAD SW VErsion
        public int u16LastOADVersion;

        public boolean bEnableAutoChannelUpdate;// run standby scan and OAD

        // check when enter standby mode
        // /OSD Duration Time 0: 5s 1: 10 2: 15s 3: 20 4: 30
        public short u8OsdDuration;

        // /Channel Switch mode
        public EN_MS_CHANNEL_SWITCH_MODE eChSwMode;

        // / Offline Detection
        public EN_MS_OFFLINE_DET_MODE eOffDetMode;

        // / TV no signal mode: 0: Noise; 1: Blue Screen
        public boolean bBlueScreen;

        // / Power On Music
        public EN_MS_POWERON_MUSIC ePWR_Music;

        // / Power On Logo
        public EN_MS_POWERON_LOGO ePWR_Logo;
    }

    /****************************************************************************
     * Sound data base
     *
     * @author stan
     */
    /**
     * Define sound mode setting enum
     *
     * @author stan
     */
    public enum EN_SOUND_MODE {
        // / sound mode is standard
        SOUND_MODE_STANDARD,
        // / sound mode is music
        SOUND_MODE_MUSIC,
        // / sound mode is movie
        SOUND_MODE_MOVIE,
        // / sound mode is sport
        SOUND_MODE_NEWS,
        // / sound mode is user
        SOUND_MODE_USER,
        // / sound mode is on-site1
        SOUND_MODE_ONSITE1,
        // / sound mode is on-site2
        SOUND_MODE_ONSITE2, SOUND_MODE_NUM;
    }

    /**
     * Define sound surround mode enum
     *
     * @author stan
     */
    public enum EN_SURROUND_MODE {
        // /Surround mode type is OFF
        E_SURROUND_MODE_OFF,
        // /Surround mode type is ON
        E_SURROUND_MODE_ON
    }

    /**
     * Define spdif mode
     *
     * @author stan
     */
    public enum EN_SPDIF_MODE {
        // /DTS type
        PDIF_MODE_OFF,
        // /PCM type
        PDIF_MODE_PCM,
        // /RAW type
        PDIF_MODE_RAW
    }

    // / Define audio mode setting
    public static enum EN_AUD_MODE {
        // / audio mode is LR
        AUD_MODE_LR,
        // / audio mode is LL
        AUD_MODE_LL,
        // / audio mode is RR
        AUD_MODE_RR, AUD_MODE_NUM
    }

    // / Define stSoundModeSeting
    public class SoundModeSeting {
        // / stSoundModeSeting is Bass
        public short Bass;

        // / stSoundModeSeting is Treble
        public short Treble;

        // / stSoundModeSeting is EqBand1
        public short EqBand1;

        // / stSoundModeSeting is EqBand2
        public short EqBand2;

        // / stSoundModeSeting is EqBand3
        public short EqBand3;

        // / stSoundModeSeting is EqBand4
        public short EqBand4;

        // / stSoundModeSeting is EqBand5
        public short EqBand5;

        // / stSoundModeSeting is EqBand6
        public short EqBand6;

        // / stSoundModeSeting is EqBand7
        public short EqBand7;

        // / stSoundModeSeting is UserMode
        public boolean UserMode;

        // / stSoundModeSeting is Balance
        public short Balance;

        // / stSoundModeSeting is enSoundAudioChannel
        public EN_AUD_MODE enSoundAudioChannel;

        public SoundModeSeting(short Bass, short treble, short EqBand1, short EqBand2,
                short EqBand3, short EqBand4, short EqBand5) {
            this.Bass = Bass;
            this.Treble = treble;
            this.EqBand1 = EqBand1;
            this.EqBand2 = EqBand2;
            this.EqBand3 = EqBand3;
            this.EqBand4 = EqBand4;
            this.EqBand5 = EqBand5;
        }
    }

    // / Audyssey dynamic volume setting
    public static enum EN_AUDYSSEY_DYNAMIC_VOLUME_MODE {
        // / OFF
        AUDYSSEY_DYNAMIC_VOLUME_OFF,
        // / ON
        AUDYSSEY_DYNAMIC_VOLUME_ON,
        // / number of the setting
        AUDYSSEY_DYNAMIC_VOLUME_NUM
    }

    // / Audyssey EQ setting
    public static enum EN_AUDYSSEY_EQ_MODE {
        // / OFF
        AUDYSSEY_EQ_OFF,
        // / ON
        AUDYSSEY_EQ_ON,
        // / number of the setting
        AUDYSSEY_EQ_NUM
    }

    // / Define surround system type
    public static enum EN_SURROUND_SYSTEM_TYPE {
        // /surround system type is OFF
        SURROUND_SYSTEM_OFF,
        // /surround system type is BBE
        SURROUND_SYSTEM_BBE,
        // /surround system type is SRS
        SURROUND_SYSTEM_SRS,
        // /surround system type is VDS
        SURROUND_SYSTEM_VDS,
        // /surround system type is VSPK
        SURROUND_SYSTEM_VSPK,
        // /surround system type is surroundmax
        SURROUND_SYSTEM_SURROUNDMAX, SURROUND_SYSTEM_NUMS
    }

    // / Define surround mode
    public static enum EN_SURROUND_TYPE {
        // / surround mode is mountain
        SURROUND_MODE_MOUNTAIN, // 0
        // / surround mode is champaign
        SURROUND_MODE_CHAMPAIGN, // 1
        // / surround mode is city
        SURROUND_MODE_CITY, // 2
        // / surround mode is theater
        SURROUND_MODE_THEATER, // 3
        SURROUND_MODE_NUM
    }

    // / define sound ad output
    public static enum EN_SOUND_AD_OUTPUT {
        // / sound ad output is speaker
        AD_SPEAKER,
        // / sound ad output is headphone
        AD_HEADPHONE,
        // / sound ad output is both
        AD_BOTH,
    }

    // / define user _SoundSettingType
    public class MS_USER_SOUND_SETTING {
        // / check sum <<checksum should be put at top of the struct, do not
        // move it to other place>>
        public int u16CheckSum;

        // / Sound Mode Enumeration
        public EN_SOUND_MODE SoundMode = EN_SOUND_MODE.SOUND_MODE_STANDARD;

        // / The settings for each sound mode
        // public stSoundModeSeting astSoundModeSetting[]; //------>>Indepent
        // / Audyssey Dynamic Volume
        public EN_AUDYSSEY_DYNAMIC_VOLUME_MODE AudysseyDynamicVolume;

        // / Audyssey EQ
        public EN_AUDYSSEY_EQ_MODE AudysseyEQ;

        // / Surround Sound Mode
        public EN_SURROUND_SYSTEM_TYPE SurroundSoundMode;

        // / surround mode enum
        public EN_SURROUND_TYPE Surround;

        // / AVC enable
        public boolean bEnableAVC = false;

        // / Volume
        public short Volume;

        // / Headphone Volume
        public short HPVolume;

        // / Balance
        public short Balance;

        // / Primary_Flag
        public short Primary_Flag;

        // / Audio language setting 1
        public EnumLanguage enSoundAudioLan1; // EN_LANGUAGE

        // / Audio language setting 2
        public EnumLanguage enSoundAudioLan2; // EN_LANGUAGE

        // / Audio mute
        public short MUTE_Flag; // for ATSC_TRUNK

        // / audio mode setting
        public EN_AUD_MODE enSoundAudioChannel;

        // / AD enable
        public boolean bEnableAD;

        // / AD volume adjust
        public short ADVolume;

        // / sound ad output
        public EN_SOUND_AD_OUTPUT ADOutput;

        // / the delay of SPDIF
        public short SPDIF_Delay;

        // / the delay of speaker
        public short Speaker_Delay;

        // / audo path prescale value
        public short CH1PreScale;

        // / spdif mode value
        public EN_SPDIF_MODE spdifMode = EN_SPDIF_MODE.PDIF_MODE_RAW;

        // / surroundMode
        public EN_SURROUND_MODE SurroundMode = EN_SURROUND_MODE.E_SURROUND_MODE_OFF;

        public MS_USER_SOUND_SETTING() {
            SoundMode = EN_SOUND_MODE.SOUND_MODE_STANDARD;
            // astSoundModeSetting = new
            // stSoundModeSeting[EN_SOUND_MODE.SOUND_MODE_NUM
            // .ordinal()];
            // astSoundModeSetting[0] = new stSoundModeSeting(
            // (short) 50, (short) 50, (short) 50, (short) 50,
            // (short) 50, (short) 50, (short) 50);
            // astSoundModeSetting[1] = new stSoundModeSeting(
            // (short) 50, (short) 50, (short) 50, (short) 50,
            // (short) 50, (short) 50, (short) 50);
            // astSoundModeSetting[2] = new stSoundModeSeting(
            // (short) 50, (short) 50, (short) 50, (short) 50,
            // (short) 50, (short) 50, (short) 50);
            // astSoundModeSetting[3] = new stSoundModeSeting(
            // (short) 50, (short) 50, (short) 50, (short) 50,
            // (short) 50, (short) 50, (short) 50);
            // astSoundModeSetting[4] = new stSoundModeSeting(
            // (short) 50, (short) 50, (short) 50, (short) 50,
            // (short) 50, (short) 50, (short) 50);
            this.Balance = 50;
        }
    }

    /**
     * *************************************************************************
     * ******
     */
    /** CEC settings */
    public class MS_CEC_SETTING {
        // / check sum <<checksum should be put at top of the struct, do not
        // move it to other place>>
        public int mCheckSum;

        // / CEC on/off; 0:off, 1:on
        public short mCECStatus;

        // / CEC auto standby; 0:off, 1:on
        public short mAutoStandby;

        // / CEC ARC; 0:off, 1:on
        public short mARCStatus;

        // / Audio Mode; 0:off, 1:on
        public short mAudioModeStatus;

        public MS_CEC_SETTING(int v1, short v2, short v3, short v4, short v5) {
            this.mCheckSum = v1;
            this.mCECStatus = v2;
            this.mAutoStandby = v3;
            this.mARCStatus = v4;
            this.mAudioModeStatus = v5;
        }
    }

    /**
     * ***************************************************************
     */
    // / define commad of access factory db
    public static enum EN_SYSTEM_FACTORY_DB_COMMAND {
        // / set factory mode color temperature
        E_FACTORY_COLOR_TEMP_SET,
        // / set factory mode video adc
        E_FACTORY_VIDEO_ADC_SET,
        // / set factory mode restore to default
        E_FACTORY_RESTORE_DEFAULT,
        // / set User Setting restore to default
        E_USER_RESTORE_DEFAULT,
        // / number of command
        E_FACTORY_COMMAND_NUM,
    }

    /*******************************************************************
     * factory setting enum
     ******************************************************************/
    // / SSC settings
    public class MS_FACTORY_SSC_SET {
        // / LVDS SSC span value
        public int Lvds_SscSpan;

        // / LVDS SSC step value
        public int Lvds_SscStep;

        // / MIU0 SSC span value
        public int Miu0_SscSpan;

        // / MIU0 SSC step value
        public int Miu0_SscStep;

        // / MIU1 SSC span value
        public int Miu1_SscSpan;

        // / MIU1 SSC step value
        public int Miu1_SscStep;

        // / MIU1 SSC span value
        public int Miu2_SscSpan;

        // / MIU1 SSC step value
        public int Miu2_SscStep;

        // / flag to indicate LVDS SSC enable
        public boolean Lvds_SscEnable;

        // / flag to indicate MIU SSC enable
        public boolean Miu_SscEnable;

        public MS_FACTORY_SSC_SET() {
            Lvds_SscEnable = false;
            Miu_SscEnable = false;
            Lvds_SscSpan = 128;
            Lvds_SscStep = 128;
            Miu0_SscSpan = 128;
            Miu0_SscStep = 128;
            Miu1_SscSpan = 128;
            Miu1_SscStep = 128;
        }
    }

    // / VD setting
    public class MS_Factory_NS_VD_SET {
        // / AFEC D4
        public short u8AFEC_D4;

        // / AFEC D8 bit 0~3
        public short u8AFEC_D8_Bit3210;

        // / AFEC D8 bit2
        public short u8AFEC_D5_Bit2;// //[2]When CF[2]=1, K1/K2 Default Value,
                                    // K1=2E,K2=6A

        // / AFEC D7 lower bound
        public short u8AFEC_D7_LOW_BOUND;// Color kill

        // / AFEC D7 higher bound
        public short u8AFEC_D7_HIGH_BOUND;// Color kill

        // / AFEC D9 bit 0
        public short u8AFEC_D9_Bit0;

        // / AFEC A0
        public short u8AFEC_A0; // only debug

        // / AFEC A1
        public short u8AFEC_A1; // only debug

        // / AFEC 66 bit 6~7
        public short u8AFEC_66_Bit76;// only debug

        // / AFEC 6E bit 4~7
        public short u8AFEC_6E_Bit7654;// only debug

        // / AFEC 6E bit 0~3
        public short u8AFEC_6E_Bit3210;// only debug

        // / AFEC 43
        public short u8AFEC_43;// auto or Fixed AGC

        // / AFEC 44
        public short u8AFEC_44;// AGC gain

        // / AFEC CB
        public short u8AFEC_CB;

        public MS_Factory_NS_VD_SET() {
            this.u8AFEC_D4 = 0x00;
            this.u8AFEC_D8_Bit3210 = 0x00;
            this.u8AFEC_D5_Bit2 = 0x00;
            this.u8AFEC_D7_LOW_BOUND = 0x00;
            this.u8AFEC_D7_HIGH_BOUND = 0x00;
            this.u8AFEC_D9_Bit0 = 0x00;
            this.u8AFEC_A0 = 0x00;
            this.u8AFEC_A1 = 0x00;
            this.u8AFEC_66_Bit76 = 0x00;
            this.u8AFEC_6E_Bit7654 = 0x00;
            this.u8AFEC_6E_Bit3210 = 0x00;
            this.u8AFEC_43 = 0x00;
            this.u8AFEC_44 = 0x00;
            this.u8AFEC_CB = 0x00;
        }
    };

    // / VID setting
    public class MS_Factory_NS_VIF_SET {
        // / top
        public short VifTop;

        // / VGA max
        public int VifVgaMaximum;

        // / Gain distribution threshold
        public int GainDistributionThr;

        // / VIF AGC VGA base
        public short VifAgcVgaBase;

        // / china descrambler box mode: A1(0~5) J2(0~11) usefull
        public short ChinaDescramblerBox;

        // / CRKP1
        public short VifCrKp1;

        // / CRKI1
        public short VifCrKi1;

        // / CRKP2
        public short VifCrKp2;

        // / CRKI2
        public short VifCrKi2;

        // / CRKP
        public short VifCrKp;

        // / CRKI
        public short VifCrKi;

        // / CR lock threshold
        public int VifCrLockThr;

        // / CR threshold
        public int VifCrThr;

        // / flag to indicate CR KPKI
        public boolean VifCrKpKiAdjust;

        // / delay reduce
        public short VifDelayReduce;

        // / over modulation
        public boolean VifOverModulation;

        // / clamping values
        public int VifClampgainClampOvNegative;

        // / clamping gain values
        public int VifClampgainGainOvNegative;

        // / VIF AGC REF VALUE
        public short VifACIAGCREF;

        // / VIF AGC REF NEGATIVE VALUE
        public short VifAgcRefNegative;

        // / VIF_ASIA_SIGNAL_OPTION
        public boolean VifAsiaSignalOption;

        // / Vif version
        public short VifVersion;

        public MS_Factory_NS_VIF_SET() {
            this.VifTop = 0x00;
            this.VifVgaMaximum = 0x00;
            this.GainDistributionThr = 0x00;
            this.VifAgcVgaBase = 0x00;
            this.ChinaDescramblerBox = 0x00;
            this.VifCrKp1 = 0x00;
            this.VifCrKi1 = 0x00;
            this.VifCrKp2 = 0x00;
            this.VifCrKi2 = 0x00;
            this.VifCrKp = 0x00;
            this.VifCrKi = 0x00;
            this.VifCrLockThr = 0x00;
            this.VifCrThr = 0x00;
            this.VifCrKpKiAdjust = false;
            this.VifDelayReduce = 0x00;
            this.VifOverModulation = false;
            this.VifClampgainClampOvNegative = 0x00;
            this.VifClampgainGainOvNegative = 0x00;
            this.VifACIAGCREF = 0x00;
            this.VifAgcRefNegative = 0x60;
            this.VifAsiaSignalOption = false;
            this.VifVersion = 0x00;
        }
    }

    // / Factory Power-On Mode
    // public static enum MS_FACTORY_POWER_MODE
    // {
    final int EN_POWER_MODE_SECONDARY = 0x00;

    final int EN_POWER_MODE_MEMORY = 0x01;

    final int EN_POWER_MODE_DIRECT = 0x02;

    final int EN_POWER_MODE_MAX = 0x03;

    // }
    // / Hi-Dev bandwidth setting
    // public static enum MS_FACTORY_HIDEV_INDEX
    // {
    // / OFF
    final int EN_AUDIO_HIDEV_OFF = 0x00;

    // / L1
    final int EN_AUDIO_HIDEV_BW_L1 = 0x01;

    // / L2
    final int EN_AUDIO_HIDEV_BW_L2 = 0x02;

    // / L3
    final int EN_AUDIO_HIDEV_BW_L3 = 0x03;

    // / the number of the setting
    final int EN_AUDIO_HIDEV_BW_MAX = 0x04;

    // }
    // public static enum EN_TEST_PATTERN_MODE
    // {
    final int TEST_PATTERN_MODE_GRAY = 0x00;

    final int TEST_PATTERN_MODE_RED = 0x01;

    final int TEST_PATTERN_MODE_GREEN = 0x02;

    final int TEST_PATTERN_MODE_BLUE = 0x03;

    final int TEST_PATTERN_MODE_BLACK = 0x04;

    final int TEST_PATTERN_MODE_OFF = 0x05;

    // }
    // public static enum EN_FACTORY_PRE_SET
    // {
    final int FACTORY_PRE_SET_ATV = 0x00;

    final int FACTORY_PRE_SET_DTV = 0x01;

    final int FACTORY_PRE_SET_NUM = 0x02;

    final int EN_3D_SELFADAPTIVE_LEVEL_LOW = 0x00;

    final int EN_3D_SELFADAPTIVE_LEVEL_MIDDLE = 0x01;

    final int EN_3D_SELFADAPTIVE_LEVEL_HIGH = 0x02;

    final int EN_3D_SELFADAPTIVE_LEVEL_MAX = 0x03;

    // }
    // / factory device setting
    public class MS_FACTORY_EXTERN_SETTING {
        // / check sum <<checksum should be put at top of the struct, do not
        // move it to other place>>
        public int u16CheckSum;

        public static String softVersion = "0.0.1";

        public static String boardType = "A3";

        public static String panelType = "Full-HD";

        public static String dayAndTime = "2011.9.22 12:00";

        public int testPatternMode; // EN_TEST_PATTERN_MODE

        public boolean dtvAvAbnormalDelay;

        public int factoryPreset; // EN_FACTORY_PRE_SET

        public short panelSwingVal;

        public short audioPreScale;

        // / 3Dselfadaptive level setting
        public int st3DSelfAdaptiveLevel;

        // / power mode setting
        public int stPowerMode; // MS_FACTORY_POWER_MODE

        // / VD setting
        // public MS_Factory_NS_VD_SET stNSVDsetting;
        // // / VIF setting
        // public MS_Factory_NS_VIF_SET stNSVIFsetting;
        // // / SSC setting
        // public MS_FACTORY_MIU_SSC_SET stSSCsetting;
        // // / SSC2 setting
        // public MS_FACTORY_LVDS_SSC_SET stSSC2setting;
        // / Hi-Devivation setting
        public int eHidevMode; // MS_FACTORY_HIDEV_INDEX

        // / factory aging mode on/off
        public boolean m_bAgingMode;

        // / factory Dsp version
        public short vdDspVersion;

        // / factory audio nr threshold
        public short audioNrThr;

        // / factory audio sif threshold
        public short audioSifThreshold;

        // / factory audio dsp version
        public short audioDspVersion;

        public MS_FACTORY_EXTERN_SETTING() {
            testPatternMode = TEST_PATTERN_MODE_OFF;
            factoryPreset = FACTORY_PRE_SET_ATV;
            dtvAvAbnormalDelay = false;
            panelSwingVal = 0;
            audioPreScale = 0;
            st3DSelfAdaptiveLevel = EN_3D_SELFADAPTIVE_LEVEL_MIDDLE;
            stPowerMode = EN_POWER_MODE_MEMORY;
            // stNSVDsetting = new MS_Factory_NS_VD_SET();
            // stNSVIFsetting = new MS_Factory_NS_VIF_SET();
            // stSSCsetting = new MS_FACTORY_MIU_SSC_SET();
            // stSSC2setting = new MS_FACTORY_LVDS_SSC_SET();
            eHidevMode = EN_AUDIO_HIDEV_OFF;
            m_bAgingMode = false;
            vdDspVersion = 0;
            audioNrThr = 0;
            audioSifThreshold = 0;
            audioDspVersion = 0;
        }
    }

    // / Usr-NLA setting
    public class MS_NLA_POINT {
        // / ponint 0
        public short u8OSD_V0;

        // / ponint 25
        public short u8OSD_V25;

        // / point 50
        public short u8OSD_V50;

        // / point 75
        public short u8OSD_V75;

        // / point 100
        public short u8OSD_V100;

        public MS_NLA_POINT() {
            u8OSD_V0 = 128;
            u8OSD_V25 = 128;
            u8OSD_V50 = 128;
            u8OSD_V75 = 128;
            u8OSD_V100 = 128;
        }
    }

    // / define items which use no-linear adjust
    public enum MS_NLA_SET_INDEX {
        // / volume
        EN_NLA_VOLUME,
        // / picture brightness
        EN_NLA_BRIGHTNESS,
        // / picture contrast
        EN_NLA_CONTRAST,
        // / picture saturation
        EN_NLA_SATURATION,
        // / picture sharpness
        EN_NLA_SHARPNESS,
        // / picture hue
        EN_NLA_HUE,
        // / the number of the index
        EN_NLA_NUMS
    }

    // / Usr-NLA setting
    public class MS_NLA_SETTING {
        // / check sum <<checksum should be put at top of the struct, do not
        // move it to other place>>
        public int u16CheckSum;

        // / items which use no-linear adjust
        public MS_NLA_SET_INDEX msNlaSetIndex;

        // / Point 0,25,50,75,100
        public MS_NLA_POINT stNLASetting[];

        public MS_NLA_SETTING() {
            int i;
            stNLASetting = new MS_NLA_POINT[MS_NLA_SET_INDEX.EN_NLA_NUMS.ordinal()];
            for (i = 0; i < MS_NLA_SET_INDEX.EN_NLA_NUMS.ordinal(); i++) {
                stNLASetting[i] = new MS_NLA_POINT();
            }
            msNlaSetIndex = MS_NLA_SET_INDEX.EN_NLA_VOLUME;
        }
    }

    // ==============================================
    // / PEQ Parameter Type
    public class AUDIO_PEQ_PARAM {
        // / PEQ Band parameter
        public int Band;

        // / PEQ Gain parameter
        public int Gain;

        // / PEQ Foh parameter
        public int Foh;

        // / PEQ Fol parameter
        public int Fol;

        // / PEQ Q-Value parameter
        public int QValue;

        public AUDIO_PEQ_PARAM() {
            Band = 3;
            Gain = 120;
            Foh = 80;
            Fol = 45;
            QValue = 80;
        }
    }

    // /define factory PEQ setting
    public class ST_FACTORY_PEQ_SETTING {
        // /check sum
        public int u16CheckSum;

        // /PEQ param
        public AUDIO_PEQ_PARAM stPEQParam[];

        public ST_FACTORY_PEQ_SETTING() {
            int i, PEQBandNum = 3;
            stPEQParam = new AUDIO_PEQ_PARAM[PEQBandNum];
            for (i = 0; i < PEQBandNum; i++) {
                stPEQParam[i] = new AUDIO_PEQ_PARAM();
            }
        }
    }

    // / to define the structure for the video window info
    public class ST_MAPI_VIDEO_WINDOW_INFO {
        public int u16H_CapStart; // /< Capture window H start

        public int u16V_CapStart; // /< Capture window V start

        public short u8HCrop_Left; // /< H Crop Left

        public short u8HCrop_Right; // /< H crop Right

        public short u8VCrop_Up; // /< V Crop Up

        public short u8VCrop_Down; // /< V Crop Down

        public ST_MAPI_VIDEO_WINDOW_INFO() {
            u16H_CapStart = 0;
            u16V_CapStart = 0;
            u8HCrop_Left = 50;
            u8HCrop_Right = 50;
            u8VCrop_Up = 50;
            u8VCrop_Down = 50;
        }
    }

    // /Define DTV resolution
    public static enum MAX_DTV_Resolution_Info {
        // /480i_60
        E_DTV480i_60,
        // /480p_60
        E_DTV480p_60,
        // /576i_50
        E_DTV576i_50,
        // /576p_50
        E_DTV576p_50,
        // /720p_60
        E_DTV720p_60,
        // /720p_50
        E_DTV720p_50,
        // /1080i_60
        E_DTV1080i_60,
        // /1080i_50
        E_DTV1080i_50,
        // /1080p_60
        E_DTV1080p_60,
        // /1080p_50
        E_DTV1080p_50,
        // /1080p_30
        E_DTV1080p_30,
        // /1080p_24
        E_DTV1080p_24,
        // /resolution number
        E_DTV_MAX
    }

    // /Define HDMI resolution type
    public static enum MAX_HDMI_Resolution_Info {
        // /480i_60
        E_HDMI480i_60,
        // /480p_60
        E_HDMI480p_60,
        // /576i_50
        E_HDMI576i_50,
        // /576p_50
        E_HDMI576p_50,
        // /720p_60
        E_HDMI720p_60,
        // /720p_50
        E_HDMI720p_50,
        // /1080i_60
        E_HDMI1080i_60,
        // /1080i_50
        E_HDMI1080i_50,
        // /1080p_60
        E_HDMI1080p_60,
        // /1080p_50
        E_HDMI1080p_50,
        // /1080p_30
        E_HDMI1080p_30,
        // /1080p_24
        E_HDMI1080p_24,
        // /1440x480i_60
        E_HDMI1440x480i_60,
        // /1440x480p_60
        E_HDMI1440x480p_60,
        // /1440x576i_50
        E_HDMI1440x576i_50,
        // /1440x576p_50
        E_HDMI1440x576p_50,
        // /MAX
        E_HDMI_MAX,
    }

    // /Define component resolution type
    public static enum MAX_YPbPr_Resolution_Info {
        // /480i_60
        E_YPbPr480i_60,
        // /480p_60
        E_YPbPr480p_60,
        // /576i_50
        E_YPbPr576i_50,
        // /576p_50
        E_YPbPr576p_50,
        // /720p_60
        E_YPbPr720p_60,
        // /720p_50
        E_YPbPr720p_50,
        // /1080i_60
        E_YPbPr1080i_60,
        // /1080i_50
        E_YPbPr1080i_50,
        // /1080p_60
        E_YPbPr1080p_60,
        // /1080p_50
        E_YPbPr1080p_50,
        // /1080p_30
        E_YPbPr1080p_30,
        // /1080p_24
        E_YPbPr1080p_24,
        // /1080p_25
        E_YPbPr1080p_25,
        // /Max
        E_YPbPr_MAX,
    }

    // /vd signal type
    public static enum EN_VD_SIGNALTYPE {
        // /NTSC
        SIG_NTSC,
        // /PAL
        SIG_PAL,
        // /SECAM
        SIG_SECAM,
        // /NTSC443
        SIG_NTSC_443,
        // /PAL_M
        SIG_PAL_M,
        // /PAL_NC
        SIG_PAL_NC,
        // /Signal number
        SIG_NUMS,
        // /Signal none
        SIG_NONE
    }

    // /DTV overscan setting
    public class MS_FACTORY_DTV_OVERSCAN_SETTING {
        // / check sum
        public int u16CheckSum;

        // /DTV overscan table
        public ST_MAPI_VIDEO_WINDOW_INFO stVideoWinInfo[][];

        public MS_FACTORY_DTV_OVERSCAN_SETTING() {
            int i, j;
            stVideoWinInfo = new ST_MAPI_VIDEO_WINDOW_INFO[MAX_DTV_Resolution_Info.E_DTV_MAX
                    .ordinal()][MAPI_VIDEO_ARC_Type.E_AR_MAX.ordinal()];
            for (i = 0; i < MAX_DTV_Resolution_Info.E_DTV_MAX.ordinal(); i++) {
                for (j = 0; j < MAPI_VIDEO_ARC_Type.E_AR_MAX.ordinal(); j++) {
                    stVideoWinInfo[i][j] = new ST_MAPI_VIDEO_WINDOW_INFO();
                }
            }
        }
    }

    // /HDMI overscan setting
    public class MS_FACTORY_HDMI_OVERSCAN_SETTING {
        // / check sum
        public int u16CheckSum;

        // /HDMI overscan table
        public ST_MAPI_VIDEO_WINDOW_INFO stVideoWinInfo[][];

        public MS_FACTORY_HDMI_OVERSCAN_SETTING() {
            int i, j;
            stVideoWinInfo = new ST_MAPI_VIDEO_WINDOW_INFO[MAX_HDMI_Resolution_Info.E_HDMI_MAX
                    .ordinal()][MAPI_VIDEO_ARC_Type.E_AR_MAX.ordinal()];
            for (i = 0; i < MAX_DTV_Resolution_Info.E_DTV_MAX.ordinal(); i++) {
                for (j = 0; j < MAPI_VIDEO_ARC_Type.E_AR_MAX.ordinal(); j++) {
                    stVideoWinInfo[i][j] = new ST_MAPI_VIDEO_WINDOW_INFO();
                }
            }
        }
    }

    // /YPbPr overscan setting
    public class MS_FACTORY_YPbPr_OVERSCAN_SETTING {
        // / check sum
        public int u16CheckSum;

        // /YPbPr overscan table
        public ST_MAPI_VIDEO_WINDOW_INFO stVideoWinInfo[][];

        public MS_FACTORY_YPbPr_OVERSCAN_SETTING() {
            int i, j;
            stVideoWinInfo = new ST_MAPI_VIDEO_WINDOW_INFO[MAX_YPbPr_Resolution_Info.E_YPbPr_MAX
                    .ordinal()][MAPI_VIDEO_ARC_Type.E_AR_MAX.ordinal()];
            for (i = 0; i < MAX_DTV_Resolution_Info.E_DTV_MAX.ordinal(); i++) {
                for (j = 0; j < MAPI_VIDEO_ARC_Type.E_AR_MAX.ordinal(); j++) {
                    stVideoWinInfo[i][j] = new ST_MAPI_VIDEO_WINDOW_INFO();
                }
            }
        }
    }

    // /VD overscan setting
    public class MS_FACTORY_VD_OVERSCAN_SETTING {
        // / check sum
        public int u16CheckSum;

        // /VD overscan table
        public ST_MAPI_VIDEO_WINDOW_INFO stVideoWinInfo[][];

        public MS_FACTORY_VD_OVERSCAN_SETTING() {
            int i, j;
            stVideoWinInfo = new ST_MAPI_VIDEO_WINDOW_INFO[EN_VD_SIGNALTYPE.SIG_NUMS.ordinal()][MAPI_VIDEO_ARC_Type.E_AR_MAX
                    .ordinal()];
            for (i = 0; i < MAX_DTV_Resolution_Info.E_DTV_MAX.ordinal(); i++) {
                for (j = 0; j < MAPI_VIDEO_ARC_Type.E_AR_MAX.ordinal(); j++) {
                    stVideoWinInfo[i][j] = new ST_MAPI_VIDEO_WINDOW_INFO();
                }
            }
        }
    }

    public static enum EN_CI_FUNCTION {
        EN_CI_FUNCTION_RM, EN_CI_FUNCTION_APPINFO, EN_CI_FUNCTION_CAS, EN_CI_FUNCTION_HC, EN_CI_FUNCTION_DT, EN_CI_FUNCTION_MMI, EN_CI_FUNCTION_LSC, EN_CI_FUNCTION_CC, EN_CI_FUNCTION_HLC, EN_CI_FUNCTION_CU, EN_CI_FUNCTION_OP, EN_CI_FUNCTION_SAS, EN_CI_FUNCTION_APPMMI, EN_CI_FUNCTION_PMT, EN_CI_FUNCTION_HSS, EN_CI_FUNCTION_AUTH, EN_CI_FUNCTION_DEFAULT,
        // please insert new enum before debug count
        EN_CI_FUNCTION_DEBUG_COUNT
    }

    // /define factory CI setting
    public class ST_FACTORY_CI_SETTING {
        // /check sum
        public int u16CheckSum;

        // / CI credential mode
        public short enCredentialMode;

        // /CI PerformanceMonitor
        public boolean bPerformanceMonitor;

        // /CI debug level 0~4
        public short u8CIFunctionDebugLevel[];

        public ST_FACTORY_CI_SETTING() {
            int i;
            u8CIFunctionDebugLevel = new short[EN_CI_FUNCTION.EN_CI_FUNCTION_DEBUG_COUNT.ordinal()];
            for (i = 0; i < EN_CI_FUNCTION.EN_CI_FUNCTION_DEBUG_COUNT.ordinal(); i++) {
                u8CIFunctionDebugLevel[i] = 128;
            }
        }
    }

    public T_MS_VIDEO getVideo();

    /**
     * Get the video info about the picture mode setting, SubColor Setting, ...
     *
     * @param video video the pointer to the structure of T_MS_VIDEO
     * @return true:sucessed; false:failed
     */
    public boolean setVideo(T_MS_VIDEO video);

    public T_MS_COLOR_TEMPEX_DATA getVideoTempEx();

    public boolean setVideoTempEx(T_MS_COLOR_TEMPEX_DATA videotmp);

    public T_MS_COLOR_TEMP getVideoTemp();

    public boolean setVideoTemp(T_MS_COLOR_TEMP videotmp);

    public MS_ADC_SETTING getAdcSetting();

    public boolean setAdcSetting(MS_ADC_SETTING adcSet);

    public MS_USER_SYSTEM_SETTING getUsrData();

    public void setUsrData(MS_USER_SYSTEM_SETTING stData);

    public MS_USER_SUBTITLE_SETTING getSubtitleSet();

    public boolean setSubtitleSet(MS_USER_SUBTITLE_SETTING stData);

    public MS_USER_LOCATION_SETTING getLocationSet();

    public boolean setLocationSet(MS_USER_LOCATION_SETTING stData);

    public MS_CEC_SETTING getCECVar();

    public boolean setCECVar(MS_CEC_SETTING stCec);

    public MS_USER_SOUND_SETTING getSound();

    public boolean setSound(MS_USER_SOUND_SETTING stMode);

    public SoundModeSeting getSoundMode(EN_SOUND_MODE eMode);

    public boolean setSoundMode(EN_SOUND_MODE eMode, SoundModeSeting stSoundMode);

    public MS_FACTORY_EXTERN_SETTING getFactoryExt();

    public boolean setFactoryExt(MS_FACTORY_EXTERN_SETTING stFactory);

    public MS_Factory_NS_VD_SET getNoStandSet();

    public boolean setNoStandSet(MS_Factory_NS_VD_SET stNonStand);

    public MS_Factory_NS_VIF_SET getNoStandVifSet();

    public boolean setNoStandVifSet(MS_Factory_NS_VIF_SET stVif);

    public MS_FACTORY_SSC_SET getSscSet();

    public boolean setSscSet(MS_FACTORY_SSC_SET stLvdsSsc);

    public boolean restoreUsrDB(EN_SYSTEM_FACTORY_DB_COMMAND eType);

    public void openDB();

    public void closeDB();

    public void loadEssentialDataFromDB();

    public void UpdateDB();

    public String queryServiceName(short epgTimerIndex);

    public String queryEventName(short epgTimerIndex);

    public void updateServiceName(String serviceName, short epgTimerIndex);

    public void updateEventName(String eventName, short epgTimerIndex);

    public SPDIF_TYPE getSpdifMode();

    public void setSpdifMode(SPDIF_TYPE mode);

    public int querySourceIdent();

    public void updateSourceIdent(short curStatue);

    public int querySourcePreview();

    public void updateSourcePreview(short curStatue);

    public void updateMonitorITC(short curStatue);

    public short queryMonitorITC();

    public int querySourceSwit();

    public void updateSourceSwit(short curStatue);

    public int queryAutoMHLSwitch();

    public void updateAutoMHLSwitch(short curStatue);
}
