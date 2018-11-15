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

package com.mstar.tvsettingservice;

import com.mstar.android.tvapi.common.vo.EnumFavoriteId;
import com.mstar.android.tvapi.common.vo.EnumProgramAttribute;
import com.mstar.android.tvapi.dtv.dvb.dvbc.vo.EnumCabConstelType;
import com.mstar.android.tvapi.dtv.dvb.vo.DvbMuxInfo;
import com.mstar.android.tvapi.dtv.vo.EnumRfChannelBandwidth;
import com.mstar.android.tvapi.dtv.vo.RfInfo;

import java.util.ArrayList;

public interface DtvInterface {
    public enum EN_ANTENNA_TYPE {
        E_ROUTE_DTMB, E_ROUTE_DVBC, E_ROUTE_DVBT, E_ROUTE_MAX,
    }

    public String[] dtvantentype = {
            "DTMB", "DVB-C", "DVB-T", "MAX"
    };

    public enum EN_SCAN_TYPE {
        // user scan type atv
        E_SCAN_ATV,
        // user scan type dtv
        E_SCAN_DTV,
        // user scan type atv plus dtv
        E_SCAN_ATVDTV,
        // user scan type max
        E_SCAN_MAX,
    }

    // / ISO 639 Language
    public class LANG_ISO639 {
        String u8ISOLangInfo[]; // :24

        byte u8AudMode; // : 2 ///< 0x00: Stereo, 0x01: Mono right, 0x02: Mono

        // left
        byte u8AudType; // : 3 ///< 0x00: Undefined, 0x01: Clean effects, 0x02:

        // Hearing impaired, 0x03: Visual impaired commentary,
        // 0x04~0xFF: Reserved.
        byte u8IsValid; // : 1 ///< Valid or not
    };

    // / the video type
    public enum VIDEO_TYPE {
        // / NONE
        E_VIDEOTYPE_NONE,
        // / MPEG
        E_VIDEOTYPE_MPEG,
        // / H.264
        E_VIDEOTYPE_H264,
        // / AVS
        E_VIDEOTYPE_AVS,
        // / VC1
        E_VIDEOTYPE_VC1
    };

    // / the audio type
    public enum AUDIO_TYPE {
        // / MPEG
        E_AUDIOTYPE_MPEG(0x00),
        // / AC3
        E_AUDIOTYPE_AC3(0x01),
        // / MPEG4
        // E_AUDIOTYPE_MPEG4 ,
        // / AAC
        E_AUDIOTYPE_AAC(0x02),
        // / AC3+
        E_AUDIOTYPE_AC3P(0x03),
        // / AAC+
        E_AUDIOTYPE_AACP(0x04),
        // E_AUDIOTYPE_MPEG_AD = 0x06,
        // E_AUDIOTYPE_AC3_AD = 0x07,
        // E_AUDIOTYPE_AC3P_AD = 0x08,
        // E_AUDIOTYPE_AACP_AD = 0x09,
        E_AUDIOTYPE_HEAAC(0x10), E_AUDIOTYPE_AAC_V2(0x11), E_AUDIOTYPE_AAC_V4(0x12), E_AUDIOTYPE_HEAAC_V2(
                0x13), E_AUDIOTYPE_HEAAC_V4(0x14);
        private int keyValue;

        AUDIO_TYPE(int key) {
            this.keyValue = key;
        }

        public int key_value() {
            return keyValue;
        }
    };

    public enum AUDIO_MODE {
        E_AUDIOMODE_STEREO, E_AUDIOMODE_LL, E_AUDIOMODE_RR,
    };

    // / Audio information
    public class AUD_INFO {
        public LANG_ISO639 aISOLangInfo;// :30 ///< ISO Language Info

        public short u16AudPID; // :13 ///< Audio PID

        public byte u8AudType; // : 3 ///< 0x01: MPEG, 0x02: AC-3, 0x03:

        // MPEG4_AUD
        // WORD Reserved : 1; ///< Reserved
        public boolean bBroadcastMixAD;

        // /AAC component type
        public byte u8AACType;

        // /AAC profile and level
        public byte u8AACProfileAndLevel;
    }

    // Define information of program
    public static final short MAPI_SI_MAX_SERVICE_NAME = 50;

    public static final short MAX_AUD_LANG_NUM = 16;

    public class MW_DVB_PROGRAM_INFO // Same Service ID
    {
        // / service_id of program
        public short u16ServiceID;

        // / the program is scramble service or not
        public boolean bIsCAExist;

        // / the program is still picture service or not
        public boolean bIsStillPic;

        // / the program select by nummeric direct or not
        public boolean bIsSelectable;

        // / the program is visible or not
        public boolean bIsVisible;

        // / the program is data broadcast service or not
        public boolean bIsDataBroadcastService;// MHEG5/MHP

        // / the program is data service or not
        public boolean bIsDataService;// TTX/subtitle

        // / the program only has service_id or not
        public boolean bIsServiceIDOnly;

        // / the program is special lcn service or not
        public boolean bIsSpecialSrv;

        // / version of PAT
        public short u8PatVer;

        // / version of PMT
        public byte u8PmtVer;

        // / version of SDT
        public byte u8SdtVer;

        // / version of NIT
        public byte u8NitVer;

        // / the service type carried in the TS
        public byte u8RealServiceType;

        // / the service type of user define
        public byte u8ServiceType;

        // / the program priority of service type
        public byte u8ServiceTypePrio;

        // / the program PMT PID
        public short u16PmtPID;

        // / the program PCR PID
        public short u16PCRPid;

        // / video information of program
        // MAPI_SI_VIDEO_INFO stVideoInfo;
        // / the logical channe number of program
        public short u16LCN;

        // / the simulcast logical channel number of program
        public short u16SimuLCN;

        // / audio information of program
        AUD_INFO stAudInfo[];

        // / the program name
        public String au8ServiceName;
    }

    // / DVB channel attribute
    public class ST_DVB_CHANNEL_ATTRIBUTE {
        // / the signal strength
        public int u16SignalStrength;

        // / the region
        public byte u8Region;

        // / service is visible or not
        public boolean u8VisibleServiceFlag; // : 1;

        // / Hidden but selectable through direct numeric entry
        public boolean u8NumericSelectionFlag; // : 1;

        // / program is delete or not
        public boolean u8IsDelete; // : 1;

        // / program is moved or not
        public boolean u8IsMove; // : 1;

        // / program is renamed or not
        public boolean u8IsReName;

        // / 0=FTA, 1=Scramble
        public boolean u8IsScramble; // : 1;

        // / for Channel Edit skip function.
        public boolean u8IsSkipped; // : 1;

        // / program is locked or not
        public boolean u8IsLock; // : 1;

        // / for information from Video_stream_descriptor(0x02)
        public boolean u8IsStillPicture; // : 1;

        // / program includes MHEG or not
        public boolean u8IsMHEGIncluded; // : 1;

        // / the video type
        public byte u8VideoType; // : 2;

        // / If the program is not shown in PAT, set to MAPI_TRUE
        public boolean u8IsServiceIdOnly; // : 1;

        // / If ttx or subtitle is available, the flag set to MAPI_TRUE
        public boolean u8IsDataServiceAvailable; // : 1;

        // / program is replaced/delete or not
        public boolean u8IsReplaceDel; // : 1;

        // / for information from PMT for empty Video_PID
        public byte u8ServiceType; // : 4; //add service type 2 bit to 4 bit

        // / program is favorite or not
        public boolean u8Favorite; // : 1;

        // / Service type priority
        public byte u8ServiceTypePrio; // : 2;

        // / Invalid cell
        public byte u8InvalidCell; // : 1;

        // / Unconfirm service
        public boolean u8UnconfirmedService; // : 1;

        // / for NTV spec Special Service LCN 900 ~ 999
        public boolean u8IsSpecialSrv;

        // / Real service type MAPI_SI_SERVICETYPE
        public byte u8RealServiceType;
    };

    // DVB program info
    public static final short MAX_SERVICE_NAME = 50;

    public class ST_DVB_PROGRAMINFO {
        // / Program ID in CM DB
        public int m_u32ID; // please don't modify this variable...

        // / Program reference Mux ID
        public short m_u16MuxTableID; // please don't modify this variable...

        // / Program PSI/SI version descripition
        // DVB_TABLE_VERSION stPSI_SI_Version;
        // / Program channel attribute descripition
        public ST_DVB_CHANNEL_ATTRIBUTE stCHAttribute;

        // / Program PCR PID
        public short u16PCRPID;

        // / Program video PID
        public short u16VideoPID;

        // / the program number
        public short u16Number;

        // / Program number
        // / Program logical channel number
        public short u16LCN;

        // / Program LCM assignment type
        public short u16LCNAssignmentType;

        // / Program PMT table PID
        public short u16PmtPID;

        // / Program service ID
        public short u16ServiceID;

        // / Program HD Simulcast Logical Channel numbber
        public short u16SimuLCN;

        // / Program MEMBER_LANGUAGE
        // MEMBER_LANGUAGE enAudioUserLanguage;
        // / Program subtitle language
        // MEMBER_LANGUAGE enSubtitleUserLanguage;
        // / Program audio information descripiton
        // AUD_INFO stAudInfo[MAX_AUD_LANG_NUM];
        // / Program service name descripiton
        public String pServiceName;
    };

    // / Define DTV specific info struct
    public class ST_DTV_SPECIFIC_PROGINFO {
        // Res. :Show resolution, SD,HD ,
        // / Service name
        public String m_sServiceName;

        // / Program number
        public int m_u32Number;

        // / Width
        public short m_u16Width;

        // / Height
        public short m_u16Height;

        // / Framerate
        public short m_u16FrameRate; // framerate x 1000

        // / Interlace or not
        public boolean m_bInterlace;

        // FIXME :
        // / Color sys : PAL,SCAM ,NTSC ?
        // / sound sys : Audio, standard,L,R
        // / MTS : mono, Stereo, MtsSap, MtsDUAL_A/B/AB, NICAM, NICAM_STEREO,
        // NICAM_DUAL_A/B,
        // / MtsRIGHT_RIGHTKK
        // / input Text : Radio,Data,air TV
        // / Audio : mpeg,dolby
        public MEMBER_SERVICETYPE m_eServiceType;

        // / Audio info
        public AUD_INFO m_stAudioInfo;

        // / Video type
        public VIDEO_TYPE m_eVideoType;

        // / Service MHEG5 or not
        public boolean m_bMHEG5Service;

        // / Service Subtitle or not
        public boolean m_bSubtitleService;

        // / Service TTX or not
        public boolean m_bTTXService;

        // / Service CC or not
        public boolean m_bCCService;

        // MHEG : mheg,video, data only
        // Narration :
        // Subtitle :
        // Audiolang :
        // Teletext :
        // Epg info page :
        // / Service HD or not
        public boolean m_bHD;

        // / Service AD or not
        public boolean m_bAD;

        // / Service audio track number
        public byte m_u8AudioTrackNum;

        // / Service subtitle number
        public byte m_u8SubtitleNum;
        // / Service aspec ratio
        // EN_ASPECT_RATIO_CODE m_enAspectRatio;
        // / Service genre
        // MW_EN_EPG_MAIN_GENRE_TYPE m_enGenreType;
        // / Service parental rating
        // U8 m_u8ParentalRating;
    };

    public class DTV_SCAN_EVENT {
        // / status of the DTV auto scan process
        public enum EN_SCAN_RET_STATUS {
            // / None
            STATUS_SCAN_NONE,
            // / auto tuning process
            STATUS_AUTOTUNING_PROGRESS,
            // / signal quality
            STATUS_SIGNAL_QUALITY,
            // / get programes
            STATUS_GET_PROGRAMS,
            // / set region
            STATUS_SET_REGION,
            // / favorite region
            STATUS_SET_FAVORITE_REGION,
            // / exit to OAD download
            STATUS_EXIT_TO_DL,
            // / LCN conflict
            STATUS_LCN_CONFLICT,
            // / end of scan
            STATUS_SCAN_END,
            // /Scan end and rearrange done to set first prog. done
            STATUS_SCAN_SETFIRSTPROG_DONE
        }

        public DTV_SCAN_EVENT() {
            enScanStatus = EN_SCAN_RET_STATUS.STATUS_SCAN_NONE;
            u8CurrRFCh = 0;
            u8ScanPercentageNum = 0;
            u16DTVSrvCount = 0;
            u16RadioSrvCount = 0;
            u16DataSrvCount = 0;
            u16SignalQuality = 0;
            u16SignalStrength = 0;
            u32CurrFrequency = 0;
            u32UsrData = 0;
        };

        // / the scan status
        public EN_SCAN_RET_STATUS enScanStatus;

        // / current RF number
        public short u8CurrRFCh;

        // / scan percentage
        public short u8ScanPercentageNum;

        // / DTV service counts
        public short u16DTVSrvCount;

        // / Radio service counts
        public short u16RadioSrvCount;

        // / Data service counts
        public short u16DataSrvCount;

        // / signal quality
        public short u16SignalQuality;

        // / signal strength
        public short u16SignalStrength;

        // / current frequency
        public int u32CurrFrequency;

        // / user data
        public int u32UsrData;
    };

    public enum MEMBER_SERVICETYPE {
        E_SERVICETYPE_ATV, // /< Service type ATV
        E_SERVICETYPE_DTV, // /< Service type DTV
        E_SERVICETYPE_RADIO, // /< Service type Radio
        E_SERVICETYPE_DATA, // /< Service type Data
        E_SERVICETYPE_UNITED_TV, // /< Service type United TV
        E_SERVICETYPE_INVALID, // /< Service type INVALID
    };

    public class EPG_EVENT_TIMER_INFO {
        public int checkSum;

        public short enTimerType;

        public short enRepeatMode;

        public int startTime;

        public int durationTime;

        public short serviceType;

        public int serviceNumber;

        public int eventID;

        public int majorNumber;

        public int minorNumber;

        public boolean isEndTimeBeforeStart;
    }

    public class EPG_CRID_EVENT_INFO {
        public short serviceType;

        public int serviceNumber;

        public EPG_EVENT_INFO eventInfo;
    }

    public class EPG_CRID_STATUS {
        // / CRID is series
        public boolean isSeries;

        // / CRID is split
        public boolean isSplit;

        // / CRID is alternate
        public boolean isAlternate;

        // / CRID is recommend
        public boolean isRecommend;

        // / CRID series count
        public short seriesCount;
    }

    /**
     * come from ST_EPG_EVENT_INFO
     * <p>
     * Title:
     * </p>
     * <p>
     * Description:
     * </p>
     * <p>
     * Copyright: Copyright (c) 2011
     * </p>
     * <p>
     * Company: Mstarsemi Inc.
     * </p>
     *
     * @author Jacky.Lin
     * @version 1.0
     */
    public class EPG_EVENT_INFO {
        // / Success: Operation success.
        public static final int EN_EPG_FUNC_STATUS_SUCCESS = 0;

        // / Error: operation general failure.
        public static final int EN_EPG_FUNC_STATUS_INVALID = 1;

        // / Error: Event not found.
        public static final int EN_EPG_FUNC_STATUS_NO_EVENT = 2;

        // / Error: No string or empty string.
        public static final int EN_EPG_FUNC_STATUS_NO_STRING = 3;

        // / Error: Program not found in channel manage database.
        public static final int EN_EPG_FUNC_STATUS_NO_CHANNEL = 4;

        // / Error: Program not found in channel manage database.
        public static final int EN_EPG_FUNC_STATUS_CRID_NOT_FOUND = 5;

        // / Error: EPG database not proper initialed.
        public static final int EN_EPG_FUNC_STATUS_DB_NO_CONNECT = 10;

        // / Error: Need Lock/Unlock to enclose retrieving actions.
        public static final int EN_EPG_FUNC_STATUS_DB_NO_LOCK = 11;

        // / Error: Program not found in EPG database.
        public static final int EN_EPG_FUNC_STATUS_DB_NO_CHANNEL_DB = 12;

        // / Error: Function is not available.
        public static final int EN_EPG_FUNC_STATUS_NO_FUNCTION = 255;

        // / EPG event start time
        public int startTime;

        // / EPG event end time, for time of offset time change is between start
        // and end
        public int endTime;

        // / EPG event duration time
        public int durationTime;

        // / EPG interface function status for return value
        // / EPG event info name
        public String name;

        // / EPG event id in db
        public int eventId;

        // / EPG event is scrambled or not
        public boolean isScrambled;

        // / EPG event genre
        public short genre;

        // / EPG event rating
        public short parentalRating;

        // / EPG event description
        public String description;

        // / EPG original start time
        public int originalStartTime;

        // / EPG function status
        public int functionStatus;
    }

    public class EPG_FIRST_MATCH_HDCAST {
        public EPG_EVENT_INFO epgEventInfoVO;

        public String serviceName;

        public boolean isResolvable;
    }

    public class EPG_HD_SIMULCAST {
        public boolean isSimulcast;

        public boolean isResolvable;

        public short count;

        public String serviceName;

        public ArrayList<?> epgEventInfos;
    }

    /**
     * <p>
     * Title: EPG_TRAILER_LINK_INFO
     * </p>
     * <p>
     * Description: This class encapsulate the the information about EPG RCT
     * link information
     * </p>
     * <p>
     * Copyright: Copyright (c) 2011
     * </p>
     * <p>
     * Company: Mstarsemi Inc.
     * </p>
     *
     * @author not attributable
     * @version 1.0
     */
    public class EPG_TRAILER_LINK_INFO {
        // / promotion text
        public char[] promotionText = new char[256];

        // / crid
        public char[] trailerCrid = new char[64];

        // / trailer type
        public int cridType;

        // / Associated event name
        public char[] pEventTitle = new char[256];

        // / tailer icon id
        public short iconId;
    }

    public enum EN_EPG_DESCRIPTION_TYPE {
        // / EPG description type is short
        E_SHORT_DESCRIPTION,
        // / EPG description type detail
        E_DETAIL_DESCRIPTION,
        // / EPG description type guidance
        E_GUIDANCE_DESCRIPTION,
        // / No need to get any description
        E_NONE_DESCRIPTION,
        // / EPG description type number
        E_DESCRIPTION_MAX,
    }

    public class EIT_CURRENT_EVENT_PF {
        /**
         * @todo implements the data structure
         */
        // / EIT table event name.
        public String eventName;

        // / EIT table short event text.
        public String shortEventText;

        // / EIT table extended event item.
        public String extendedEventItem;

        // / EIT table extended event text.
        public String extendedEventText;

        // / Event start time.
        long stStartTime;

        // / Event start time is day light time or not
        public boolean isStartTimeDayLightTime;

        // / Event end time.
        long stEndTime;

        // / Event end time is day light time or not
        boolean isEndTimeDayLightTime;

        // / EIT table duration time.
        public int durationInSeconds;

        // / EIT table is scrambled or not
        public boolean isScrambled;

        // / EIT table parental control.
        public short parentalControl;

        // / EIT table parental objective content.
        public short parentalObjectiveContent;

        // / EIT table content nibble level
        public short contentNibbleLevel1;

        // / EIT table content nibble level
        public short contentNibbleLevel2;
    }

    public class NVOD_EVENT_INFO {
        // / EPG event info
        EPG_EVENT_INFO epgEventInfo;

        // / Time shift service triple ID
        DTV_TRIPLE_ID timeShiftedServiceIds;
    }

    public class DTV_TRIPLE_ID {
        // / Original Network ID
        public int originalNetworkId;

        // / Transport Stream ID
        public int transportStreamId;

        // / Service ID
        public int serviceId;
    }

    public class DTV_EIT_INFO {
        public EIT_CURRENT_EVENT_PF eitCurrentEventPf;

        /**
         * Present EIT information or not
         */
        public boolean present;
    }

    public class TIME {
        // / the boot up type
        // / on timer boot up
        public static final int E_TIMER_BOOT_ON_TIMER = 0;

        // / boot up because of reminder
        public static final int E_TIMER_BOOT_REMINDER = 1;

        // / boot up because of recorder
        public static final int E_TIMER_BOOT_RECORDER = 2;

        // checksum
        public int timeDataCS;

        // is on time or not
        public short onTimeFlag;

        // is off time or not
        public int offTimeFlag;

        // the state of off time
        public short offTimeState;

        // the hour of off time
        public short offTimerInfoHour;

        // the minute of off time
        public short offTimerInfoMin;

        // the state of on time
        public short enOnTimeState;

        // the hour of on time
        public short onTimerInfoHour;

        // the minute of on time
        public short onTimerInfoMin;

        // the channel for on time
        public int onTimerChannel;

        // the input source for on time
        public short onTimeTvSrc;

        // the volume for on time
        public short onTimerVolume;

        // the time zone
        public short eTimeZoneInfo;

        // is 12-hour or 24-hour
        public boolean is12Hour;

        // is auto sync or not
        public boolean isAutoSync;

        // clock mode: auto or manual
        public boolean clockMode;

        // is auto-sleep or not
        public boolean autoSleepFlag;

        public boolean isDaylightSaving;

        // offset time in seconds
        public int offsetTime;

        // the boot up type (EN_TIMER_BOOT_ON_TIMER, EN_TIMER_BOOT_REMINDER,
        // EN_TIMER_BOOT_RECORDER)
        public int timerBootMode;
    }

    /**
     * set dtv Scan type : dvbc or dvbt
     *
     * @param type
     */
    public void dtvSetAntennaType(EN_ANTENNA_TYPE type);

    /**
     * Get Dtv Scan type dvbc or dmbt
     *
     * @return
     */
    public EN_ANTENNA_TYPE dtvGetAntennaType();

    /**
     * Do DVBC Full Scan.
     *
     * @return
     */
    public boolean dtvFullScan();

    /**
     * The DTV player start to auto scan
     *
     * @return
     */
    public boolean dtvAutoScan();

    public void setBandwidth(EnumRfChannelBandwidth bandwidth);

    /**
     * The DTV player set manual scan RF.
     *
     * @param RFNum \b IN: Identification of specific scan RF.
     * @return TRUE: Set manual scan RF success, FALSE: Set manual scan RF
     *         failure.
     */
    public boolean dtvManualScanRF(short RFNum);

    /**
     * The DTV player set to manual scan frequency
     *
     * @param FrequencyKHz \b IN: Identification of specific scan frequency.
     * @return \b TRUE: Set manual scan frequency success, FALSE: Set manual scan
     *         frequency failure.
     */
    public boolean dtvManualScanFreq(int FrequencyKHz);

    /**
     * The DTV player change manual scan RF
     *
     * @param RFNum \b IN: Change to specific RF
     * @return \b TRUE: Change manual scan RF success, FALSE: Change manual scan
     *         RF failure.
     */
    public boolean dtvChangeManualScanRF(short RFNum);

    /**
     * The DTV player pause auto scan
     *
     * @return
     */
    public boolean dtvPauseScan();

    /**
     * The DTV player resume auto scan
     *
     * @return
     */
    public boolean dtvResumeScan();

    /**
     * The DTV player stop to auto scan
     *
     * @return \b TRUE: Stop auto scan success, FALSE: Stop auto scan failure.
     */
    public boolean DtvStopScan();

    /**
     * The DTV player start to manual scan.
     *
     * @return
     */
    public boolean dtvStartManualScan();

    /**
     * Get program RF informaiotn.
     *
     * @param enInfoType \b IN: Program infomation type
     * @param RFChNum \b IN: Program RF number
     * @return
     */
    public RfInfo dtvGetRFInfo(RfInfo.EnumInfoType enInfoType, int RFChNum);

    /**
     * Get current program specific structure informaion
     *
     * @param cResult
     * @return
     */
    public boolean getCurrentProgramSpecificInfo(ST_DTV_SPECIFIC_PROGINFO cResult);

    /**
     * Get program specific structure informaion by index
     *
     * @param programIndex
     * @param cResult
     * @return
     */
    public boolean getProgramSpecificInfoByIndex(int programIndex, ST_DTV_SPECIFIC_PROGINFO cResult);

    /**
     * send msg to notify actvity upgrade
     */
    public void sendDtvScaninfo();

    /**
     * switchMSrvDtvRoute add will be work in msrv
     * GetTVInfo().u8RoutePath[u8DtvRoute] ;
     *
     * @param u8DtvRoute 0 or 1
     * @return
     */
    public boolean switchMSrvDtvRouteCmd(short u8DtvRoute);

    public short getMSrvDtvRoute();

    /**
     * Set Scan releated parameter,only use in DVBC scan (auto, full and manual
     * scan).
     *
     * @param u16SymbolRate \b IN: symbol rate
     * @param enConstellation \b IN: constellation type
     * @param u32NITFrequency \b IN: NIT freq
     * @param u32EndFrequncy \b IN: end freq
     * @param u16NetworkID \b IN: network ID
     * @return
     */
    boolean dvbcsetScanParam(short u16SymbolRate, EnumCabConstelType enConstellation,
                             int u32NITFrequency, int u32EndFrequncy, short u16NetworkID);

    public class DvbcScanParam {
        public short u16SymbolRate;

        public EnumCabConstelType QAM_Type;

        public int u32NITFrequency;
    };

    /**
     * get dvbcgetScanParam for dvbc autoscan
     *
     * @param sp
     * @return
     */
    boolean dvbcgetScanParam(DvbcScanParam sp);

    /**
     * /// The DTV player play current program record in database.
     *
     * @return
     */
    boolean dtvplayCurrentProgram();

    /**
     * programe attribute oprate function
     *
     * @param enpa
     * @param programNo
     * @param pt
     * @param pd
     * @param bv
     */
    public void setProgramAttribute(EnumProgramAttribute enpa, int programNo, short pt, int pd,
                                    boolean bv);

    public boolean getProgramAttribute(EnumProgramAttribute enpa, int programNo, short pt, int pd,
                                       boolean bv);

    public void addProgramToFavorite(EnumFavoriteId favoriteId, int programNo, short programType,
                                     int programId);

    public void deleteProgramFromFavorite(EnumFavoriteId favoriteId, int programNo,
                                          short programType, int programId);

    public DvbMuxInfo getCurrentMuxInfo();
}
