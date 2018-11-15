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

import android.text.format.Time;


import com.mstar.android.tvapi.common.vo.EpgEventTimerInfo;
import com.mstar.android.tvapi.common.vo.PresentFollowingEventInfo;
import com.mstar.android.tvapi.common.vo.TvOsType;
import com.mstar.android.tvapi.dtv.vo.DtvEitInfo;
import com.mstar.android.tvapi.dtv.vo.DtvType.EnumEpgDescriptionType;
import com.mstar.android.tvapi.dtv.vo.EnumEpgTimerCheck;
import com.mstar.android.tvapi.dtv.vo.EpgCridEventInfo;
import com.mstar.android.tvapi.dtv.vo.EpgCridStatus;
import com.mstar.android.tvapi.dtv.vo.EpgEventInfo;
import com.mstar.android.tvapi.dtv.vo.EpgFirstMatchHdCast;
import com.mstar.android.tvapi.dtv.vo.EpgHdSimulcast;
import com.mstar.android.tvapi.dtv.vo.EpgTrailerLinkInfo;
import com.mstar.android.tvapi.dtv.vo.NvodEventInfo;

import java.util.ArrayList;

/**
 * Base Interface of Epg Service
 *
 * @author steven.li
 */
public interface EpgDesk extends BaseDesk {
    /**
     * Get total event info from u32BaseTime
     *
     * @param serviceType short Program service type. (DTV, radio, data service)
     * @param serviceNo int Program service number.
     * @param baseTime Time base time based on UTC time
     * @param maxEventInfoCount int maximum object size in return arraylist
     * @return ArrayList<EpgEventInfo>
     * @throws Exception
     */
    public ArrayList<EpgEventInfo> getEventInfo(short serviceType, int serviceNo, Time baseTime,
                                                int maxEventInfoCount) throws Exception;

    /**
     * Get total event counts from base time
     *
     * @param serviceType short Program service type. (DTV, radio, data service)
     * @param serviceNo int Program service number.
     * @param baseTime Time UTC time
     * @return int event count from basetime
     */
    public int getEventCount(short serviceType, int serviceNo, Time baseTime);

    /**
     * Get program EPG event information by time.
     *
     * @param serviceType short Program service type. (DTV, radio, data service)
     * @param serviceNo int Program service number.
     * @param baseTime Time base time based on UTC time
     * @return EpgEventInfo
     * @throws Exception
     */
    public EpgEventInfo getEventInfoByTime(short serviceType, int serviceNo, Time baseTime)
            throws Exception;

    /**
     * Get program EPG event information by event ID.
     *
     * @param serviceType short Program service type. (DTV, radio, data service)
     * @param serviceNo int Program service number.
     * @param eventID short
     * @return EpgEventInfo
     * @throws Exception
     */
    public EpgEventInfo getEventInfoById(short serviceType, int serviceNo, short eventID)
            throws Exception;

    /**
     * Get program EPG event descriptor.
     *
     * @param serviceType short Program service type. (DTV, radio, data service)
     * @param serviceNumber int Program service number.
     * @param baseTime Time UTC time
     * @param epgDescriptionType EN_EPG_DESCRIPTION_TYPE
     * @return String
     * @throws Exception
     */
    public String getEventDescriptor(short serviceType, int serviceNumber, Time baseTime,
                                     EnumEpgDescriptionType epgDescriptionType) throws Exception;

    /**
     * Get EPG HD simulcast
     *
     * @param serviceType short Program service type. (DTV, radio, data service)
     * @param serviceNo int Program service number.
     * @param baseTime Time UTC time
     * @param maxCount short maximum count want to get
     * @return EpgHdSimulcast
     * @throws Exception
     */
    public ArrayList<EpgHdSimulcast> getEventHdSimulcast(short serviceType, int serviceNo,
                                                         Time baseTime, short maxCount) throws Exception;

    /**
     * Reset the service priority to default for the case EPG database is full.
     *
     * @throws Exception
     */
    public void resetEpgProgramPriority() throws Exception;

    /**
     * Get CRID status. Series / split / alternate / recommend.
     *
     * @param serviceType short Program service type. (DTV, radio, data service)
     * @param serviceNumber int Program service number.
     * @param eventStartTime Time Target event start time base on UTC time.
     * @return EpgCridStatus EPG CRID Status
     * @throws Exception
     */
    public EpgCridStatus getCridStatus(short serviceType, int serviceNumber, Time eventStartTime)
            throws Exception;

    /**
     * Get CRID series list.
     *
     * @param serviceType short Program service type. (DTV, radio, data service)
     * @param serviceNumber int Program service number.
     * @param eventStartTime Time Target event start time base on UTC time.
     * @return ArrayList<EpgCridEventInfo> ArrayList of EpgCridEventInfo
     * @throws Exception
     */
    public ArrayList<EpgCridEventInfo> getCridSeriesList(short serviceType, int serviceNumber,
                                                         Time eventStartTime) throws Exception;

    /**
     * Get CRID split list.
     *
     * @param serviceType short Program service type. (DTV, radio, data service)
     * @param serviceNumber int Program service number.
     * @param eventStartTime Time Target event start time base on UTC time.
     * @return ArrayList<EpgCridEventInfo> ArrayList of EpgCridEventInfo
     * @throws Exception
     */
    public ArrayList<EpgCridEventInfo> getCridSplitList(short serviceType, int serviceNumber,
                                                        Time eventStartTime) throws Exception;

    /**
     * Get CRID alternate list.
     *
     * @param serviceType short Program service type. (DTV, radio, data service)
     * @param serviceNumber int Program service number.
     * @param eventStartTime Time event start time based on UTC time
     * @return ArrayList<EpgCridEventInfo> vector of ST_DTV_EPG_CRID_EVENT_INFO
     * @throws Exception
     */
    public ArrayList<EpgCridEventInfo> getCridAlternateList(short serviceType, int serviceNumber,
                                                            Time eventStartTime) throws Exception;

    /**
     * Get trailer link
     *
     * @return ArrayList<EpgTrailerLinkInfo> arraylist of EPG trailer link
     *         infomation
     * @throws Exception
     */
    public ArrayList<EpgTrailerLinkInfo> getRctTrailerLink() throws Exception;

    /**
     * Get event info(s) by trailer link
     *
     * @param epgTrailerLinkInfo EpgTrailerLinkInfo
     * @return ArrayList<EpgCridEventInfo> arraylist of EpgCridEventInfo
     * @throws Exception
     */
    public ArrayList<EpgCridEventInfo> getEventInfoByRctLink(EpgTrailerLinkInfo epgTrailerLinkInfo)
            throws Exception;

    /**
     * Eable EPG Barker channel work.
     *
     * @return boolean
     * @throws Exception
     */
    public boolean enableEpgBarkerChannel() throws Exception;

    /**
     * Disable EPG Barker channel work.
     *
     * @return boolean
     * @throws Exception
     */
    public boolean disableEpgBarkerChannel() throws Exception;

    /**
     * Get offset time for event time between time of offset time change case
     *
     * @param utcTime Time UTC time
     * @param isStartTime boolean TRUE: event start time, FALSE: event end time
     * @return int
     * @throws Exception
     */
    public int getEpgEventOffsetTime(Time utcTime, boolean isStartTime) throws Exception;

    /**
     * Get present/following event information of certain service
     *
     * @param serviceType short Program service type. (DTV, radio, data service)
     * @param serviceNo int Program service number.
     * @param present boolean
     * @param eventComponentInfo DtvEventComponentInfo
     * @param descriptionType EnumEpgDescriptionType
     * @return EpgEventInfo
     * @throws Exception
     */
    public PresentFollowingEventInfo getPresentFollowingEventInfo(short serviceType, int serviceNo,
                                                                  boolean present, EnumEpgDescriptionType descriptionType) throws Exception;

    /**
     * Get first matched Epg HD simulcast.
     *
     * @param serviceType short Program service type. (DTV, radio, data service)
     * @param serviceNo int Program service number.
     * @param baseTime Time Event start time based on UTC time.
     * @return Epg1stMatchHDSimulcastVO including if resolvable,target program
     *         name, target event
     * @throws Exception
     */
    public EpgFirstMatchHdCast getEvent1stMatchHdSimulcast(short serviceType, int serviceNo,
                                                           Time baseTime) throws Exception;

    /**
     * Get first matched EPG HD broadcast later.
     *
     * @param serviceType short Program service type. (DTV, radio, data service)
     * @param serviceNo int Program service number.
     * @param baseTime Time Event start time base on UTC time.
     * @return Epg1stMatchHDSimulcastVO including if resolvable,target program
     *         name, target event
     * @throws Exception
     */
    public EpgFirstMatchHdCast getEvent1stMatchHdBroadcast(short serviceType, int serviceNo,
                                                           Time baseTime) throws Exception;

    /**
     * Get EIT information.
     *
     * @return DtvEitInfo The EIT information.
     * @throws Exception
     */
    public DtvEitInfo getEitInfo(boolean bPresent) throws Exception;

    /**
     * Setting the service priority for the case EPG database is full. the EIT
     * buffer assigned to the service with lowest priority will be replaced.
     * CM/UI should call this function whenever channel change.
     *
     * @param programIndex int service position or program index.
     * @throws Exception
     */
    public void setEpgProgramPriority(int programIndex) throws Exception;

    /**
     * Setting the service priority for the case EPG database is full. the EIT
     * buffer assigned to the service with lowest priority will be replaced.
     * CM/UI should call this function whenever channel change.
     *
     * @param serviceType short Program service type. (DTV, radio, data service)
     * @param serviceNo int Program service number.
     * @throws Exception
     */
    public void setEpgProgramPriority(short serviceType, int serviceNo) throws Exception;

    /**
     * Get NVOD time shift event counts belong to the input NVOD reference
     * service
     *
     * @param serviceType short nvod program service type.
     * @param serviceNumber int nvod program service number.
     * @return int the found events info count
     * @throws Exception
     */
    public int getNvodTimeShiftEventCount(short serviceType, int serviceNumber)
            throws Exception;

    /**
     * Get NVOD time shift event info service
     *
     * @param serviceType short nvod program service type.
     * @param serviceNumber int nvod program service number.
     * @return int the found events info count
     * @throws Exception
     */
    public ArrayList<NvodEventInfo> getNvodTimeShiftEventInfo(short serviceType, int serviceNumber,
                                                              int maxEventNum, EnumEpgDescriptionType eEpgDescritionType) throws Exception;

    /**
     * Add epg event
     *
     * @param vo EpgEventTimerInfo
     * @return EnumEpgTimerCheck
     * @throws Exception
     */
    public EnumEpgTimerCheck addEpgEvent(EpgEventTimerInfo vo) throws Exception;

    /**
     * get epg timer event by index
     *
     * @param index int
     * @return EpgEventTimerInfo timer event info
     * @throws Exception
     */
    public EpgEventTimerInfo getEpgTimerEventByIndex(int index) throws Exception;

    /**
     * get epg timer event count
     *
     * @return int timer event count
     * @throws Exception
     */
    public int getEpgTimerEventCount() throws Exception;

    /**
     * is Epg TimerSetting Valid
     *
     * @param timerInfoVo EpgEventTimerInfo.
     * @return TvOsType.EnumEpgTimerCheck
     * @throws Exception
     */
    public EnumEpgTimerCheck isEpgTimerSettingValid(EpgEventTimerInfo timerInfoVo)
            throws Exception;

    /**
     * del All Epg Event
     *
     * @return boolean
     * @throws Exception
     */
    public boolean delAllEpgEvent() throws Exception;

    /**
     * is Epg TimerSetting Valid
     *
     * @param epgEvent int.
     * @return boolean
     * @throws Exception
     */
    public boolean delEpgEvent(int epgEvent) throws Exception;

    /**
     * delete Past Epg Timer
     *
     * @return boolean
     * @throws Exception
     */
    public boolean deletePastEpgTimer() throws Exception;

    /**
     * exec Epg Timer Action
     *
     * @return boolean
     * @throws Exception
     */
    public boolean execEpgTimerAction() throws Exception;

    /**
     * Reconfig EPG timer list & setting monitors
     *
     * @param timeActing int (the time before valid list items.)
     * @param checkEndTime boolean (delete according to end time.)
     * @throws Exception
     */
    public void cancelEpgTimerEvent(int timeActing, boolean checkEndTime) throws Exception;

    /**
     * Get timer recording program.
     *
     * @return EpgEventTimerInfo The current timer info
     * @throws Exception
     */
    public EpgEventTimerInfo getEpgTimerRecordingProgram() throws Exception;

    /**
     * Gets timezone state
     *
     * @return TvOsType.EnumTimeZone (Timezone state)
     * @throws Exception
     */
    public TvOsType.EnumTimeZone getTimeZone() throws Exception;
}
