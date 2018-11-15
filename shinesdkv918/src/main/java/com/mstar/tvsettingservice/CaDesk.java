
package com.mstar.tvsettingservice;

import com.mstar.android.tvapi.dtv.common.CaManager.OnCaEventListener;
import com.mstar.android.tvapi.dtv.vo.CACardSNInfo;
import com.mstar.android.tvapi.dtv.vo.CARatingInfo;
import com.mstar.android.tvapi.dtv.vo.CaACListInfo;
import com.mstar.android.tvapi.dtv.vo.CaDetitleChkNums;
import com.mstar.android.tvapi.dtv.vo.CaEmailContentInfo;
import com.mstar.android.tvapi.dtv.vo.CaEmailHeadInfo;
import com.mstar.android.tvapi.dtv.vo.CaEmailHeadsInfo;
import com.mstar.android.tvapi.dtv.vo.CaEmailSpaceInfo;
import com.mstar.android.tvapi.dtv.vo.CaEntitleIDs;
import com.mstar.android.tvapi.dtv.vo.CaFeedDataInfo;
import com.mstar.android.tvapi.dtv.vo.CaIPPVProgramInfos;
import com.mstar.android.tvapi.dtv.vo.CaOperatorChildStatus;
import com.mstar.android.tvapi.dtv.vo.CaOperatorIds;
import com.mstar.android.tvapi.dtv.vo.CaOperatorInfo;
import com.mstar.android.tvapi.dtv.vo.CaServiceEntitles;
import com.mstar.android.tvapi.dtv.vo.CaSlotIDs;
import com.mstar.android.tvapi.dtv.vo.CaSlotInfo;
import com.mstar.android.tvapi.dtv.vo.CaStopIPPVBuyDlgInfo;
import com.mstar.android.tvapi.dtv.vo.CaWorkTimeInfo;

public interface CaDesk extends BaseDesk {
    /**
     * To Get CA Card Sn Infomation
     *
     * @return CACardSNInfo
     * @throws Exception
     */
    public CACardSNInfo CaGetCardSN() throws Exception;

    /**
     * To Change CA Pin Code
     *
     * @param1 old Pin code
     * @param2 new pin code
     * @return short
     * @throws Exception
     */
    public short CaChangePin(String pbyOldPin, String pbyNewPin) throws Exception;

    /**
     * To Set CA Rating
     *
     * @param1 Pin Code
     * @param2 New Rating
     * @return short
     * @throws Exception
     */
    public short CaSetRating(String pbyPin, short byRating) throws Exception;

    /**
     * To Get CA Rating Information
     *
     * @return CARatingInfo
     * @throws Exception
     */
    public CARatingInfo CaGetRating() throws Exception;

    /**
     * To Modify CA Work Time
     *
     * @param1 Pin Code
     * @param2 CA WorkTime Object
     * @return short
     * @throws Exception
     */
    public short CaSetWorkTime(String pbyPin, CaWorkTimeInfo worktime) throws Exception;

    /**
     * To Get CA WorkTime
     *
     * @return CaWorkTimeInfo
     * @throws Exception
     */
    public CaWorkTimeInfo CaGetWorkTime() throws Exception;

    /**
     * To Get CA System Version
     *
     * @return int
     * @throws Exception
     */
    public int CaGetVer() throws Exception;

    /**
     * To Get all Ca operatorid
     *
     * @return CaOperatorIds
     * @throws Exception
     */
    public CaOperatorIds CaGetOperatorIds() throws Exception;

    /**
     * To Get Operator information by Id
     *
     * @param CA operator id
     * @return CaOperatorInfo
     * @throws Exception
     */
    public CaOperatorInfo CaGetOperatorInfo(short wTVSID) throws Exception;

    /**
     * To Inquires the user features
     *
     * @param CA operator id
     * @return CaACListInfo
     * @throws Exception
     */
    public CaACListInfo CaGetACList(short wTVSID) throws Exception;

    /**
     * To Get all CA Slot Id by Operator Id
     *
     * @param CA operator id
     * @return CaSlotIDs
     * @throws Exception
     */
    public CaSlotIDs CaGetSlotIDs(short wTVSID) throws Exception;

    /**
     * To Get Slot information by OperatorId and SlotId
     *
     * @param1 CA operator id
     * @param2 CA Slot id
     * @return CaSlotInfo
     * @throws Exception
     */
    public CaSlotInfo CaGetSlotInfo(short wTVSID, short bySlotID) throws Exception;

    /**
     * To Get Ca Service Entitle Information
     *
     * @param CA operator id
     * @return CaServiceEntitles
     * @throws Exception
     */
    public CaServiceEntitles CaGetServiceEntitles(short wTVSID) throws Exception;

    /**
     * To Get Ca Entitle Id List
     *
     * @param CA operator id
     * @return CaEntitleIDs
     * @throws Exception
     */
    public CaEntitleIDs CaGetEntitleIDs(short wTVSID) throws Exception;

    /**
     * To Get ca Detitle information
     *
     * @param CA operator id
     * @return CaDetitleChkNums
     * @throws Exception
     */
    public CaDetitleChkNums CaGetDetitleChkNums(short wTVSID) throws Exception;

    /**
     * To Get CA Detitle information Read State
     *
     * @param CA operator id
     * @return boolean
     * @throws Exception
     */
    public boolean CaGetDetitleReaded(short wTvsID) throws Exception;

    /**
     * To Delete Detitle Check Num
     *
     * @param1 CA operator id
     * @param2 CA Detitle Check Num
     * @return boolean
     * @throws Exception
     */
    public boolean CaDelDetitleChkNum(short wTvsID, int dwDetitleChkNum) throws Exception;

    /**
     * To Get Machine card corresponding situation
     *
     * @param1 Smart CARDS corresponding set-top box number(MaxNum=5)
     * @param2 set-top box List
     * @return short
     * @throws Exception
     */
    public short CaIsPaired(short pbyNum, String pbySTBID_List) throws Exception;

    /**
     * To Get CA Platform Id
     *
     * @return short
     * @throws Exception
     */
    public short CaGetPlatformID() throws Exception;

    /**
     * To Stop IPPV Buy Dialog Info
     *
     * @param CA operator id
     * @return CaIPPVProgramInfo
     * @throws Exception
     */
    public short CaStopIPPVBuyDlg(CaStopIPPVBuyDlgInfo IppvInfo) throws Exception;

    /**
     * To Get Ca IPPV Program Information
     *
     * @param CA operator id
     * @return CaIPPVProgramInfo
     * @throws Exception
     */
    public CaIPPVProgramInfos CaGetIPPVProgram(short wTvsID) throws Exception;

    /**
     * To Get Ca Email Head Info
     *
     * @return CaEmailHeadInfo
     * @throws Exception
     */
    public CaEmailHeadsInfo CaGetEmailHeads(short byCount, short byFromIndex)
            throws Exception;

    /**
     * To Get Ca Email Head Info By Email Id
     *
     * @param Email Id
     * @return CaEmailHeadInfo
     * @throws Exception
     */
    public CaEmailHeadInfo CaGetEmailHead(int dwEmailID) throws Exception;

    /**
     * To Get Ca Email Content By Email Id
     *
     * @param Email Id
     * @return CaEmailContentInfo
     * @throws Exception
     */
    public CaEmailContentInfo CaGetEmailContent(int dwEmailID) throws Exception;

    /**
     * To Delete Email By Email Id
     *
     * @param Email Id
     * @throws Exception
     */
    public void CaDelEmail(int dwEmailID) throws Exception;

    /**
     * To Inquires Email Space Info
     *
     * @return CaEmailSpaceInfo
     * @throws Exception
     */
    public CaEmailSpaceInfo CaGetEmailSpaceInfo() throws Exception;

    /**
     * To Reads mother card and Child Card match info
     *
     * @param CA operator id
     * @return CaOperatorChildStatus
     * @throws Exception
     */
    public CaOperatorChildStatus CaGetOperatorChildStatus(short wTVSID) throws Exception;

    /**
     * To Read Feed Data From Parent Card
     *
     * @param CA operator id
     * @return CaFeedDataInfo
     * @throws Exception
     */
    public CaFeedDataInfo CaReadFeedDataFromParent(short wTVSID) throws Exception;

    /**
     * To Write Feed Data To Child Card
     *
     * @param1 CA operator id
     * @param2 Feed Data Object
     * @return short
     * @throws Exception
     */
    public short CaWriteFeedDataToChild(short wTVSID, CaFeedDataInfo FeedData)
            throws Exception;

    /**
     * To Refresh Interface
     *
     * @throws Exception
     */
    public void CaRefreshInterface() throws Exception;

    /**
     * To CaOTAStateConfirm Interface
     *
     * @throws Exception
     */
    public boolean CaOTAStateConfirm(int arg1, int arg2) throws Exception;

    /**
     * Register setOnCaEventListener(OnCaEventListener listener), your listener
     * will be triggered when the events posted from native code.
     *
     * @param listener OnCaEventListener
     */
    public void setOnCaEventListener(OnCaEventListener listener);

    /**
     * To get current CA Event Id
     *
     * @param
     * @return int
     */
    public int getCurrentEvent();

    /**
     * To get current CA message Type
     *
     * @param
     * @return int
     */
    public int getCurrentMsgType();

    /**
     * To set current CA Event Id
     *
     * @param CA Current Event id
     * @return
     */
    public void setCurrentEvent(int CurrentEvent);

    /**
     * To Set current CA message Type
     *
     * @param CA Msg Type
     * @return
     */
    public void setCurrentMsgType(int MsgType);

}
