
package com.mstar.tvsrvfunc;

import com.mstar.android.tvapi.dtv.common.CaManager;
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
import com.mstar.tvsettingservice.CaDesk;

public class CaDeskImpl extends BaseDeskImpl implements CaDesk {
    private static CaDeskImpl caMgrImpl = null;

    private CaDeskImpl() {

    }

    public static CaDeskImpl getCaMgrInstance() {
        if (caMgrImpl == null)
            caMgrImpl = new CaDeskImpl();
        return caMgrImpl;
    }

    /**
     * To Get CA Card Sn Infomation
     *
     * @return CACardSNInfo
     * @throws Exception
     */
    public CACardSNInfo CaGetCardSN() throws Exception {
        return CaManager.getInstance().CaGetCardSN();
    }

    /**
     * To Change CA Pin Code
     *
     * @param1 old Pin code
     * @param2 new pin code
     * @return short
     * @throws Exception
     */
    public short CaChangePin(String pbyOldPin, String pbyNewPin) throws Exception {
        return CaManager.getInstance().CaChangePin(pbyOldPin, pbyNewPin);
    }

    /**
     * To Set CA Rating
     *
     * @param1 Pin Code
     * @param2 New Rating
     * @return short
     * @throws Exception
     */
    public short CaSetRating(String pbyPin, short byRating) throws Exception {
        return CaManager.getInstance().CaSetRating(pbyPin, byRating);
    }

    /**
     * To Get CA Rating Information
     *
     * @return CARatingInfo
     * @throws Exception
     */
    public CARatingInfo CaGetRating() throws Exception {
        return CaManager.getInstance().CaGetRating();
    }

    /**
     * To Modify CA Work Time
     *
     * @param1 Pin Code
     * @param2 CA WorkTime Object
     * @return short
     * @throws Exception
     */
    public short CaSetWorkTime(String pbyPin, CaWorkTimeInfo worktime) throws Exception {
        return CaManager.getInstance().CaSetWorkTime(pbyPin, worktime);
    }

    /**
     * To Get CA WorkTime
     *
     * @return CaWorkTimeInfo
     * @throws Exception
     */
    public CaWorkTimeInfo CaGetWorkTime() throws Exception {
        return CaManager.getInstance().CaGetWorkTime();
    }

    /**
     * To Get CA System Version
     *
     * @return int
     * @throws Exception
     */
    public int CaGetVer() throws Exception {
        return CaManager.getInstance().CaGetVer();
    }

    /**
     * To Get all Ca operatorid
     *
     * @return CaOperatorIds
     * @throws Exception
     */
    public CaOperatorIds CaGetOperatorIds() throws Exception {
        return CaManager.getInstance().CaGetOperatorIds();
    }

    /**
     * To Get Operator information by Id
     *
     * @param CA operator id
     * @return CaOperatorInfo
     * @throws Exception
     */
    public CaOperatorInfo CaGetOperatorInfo(short wTVSID) throws Exception {
        return CaManager.getInstance().CaGetOperatorInfo(wTVSID);
    }

    /**
     * To Inquires the user features
     *
     * @param CA operator id
     * @return CaACListInfo
     * @throws Exception
     */
    public CaACListInfo CaGetACList(short wTVSID) throws Exception {
        return CaManager.getInstance().CaGetACList(wTVSID);
    }

    /**
     * To Get all CA Slot Id by Operator Id
     *
     * @param CA operator id
     * @return CaSlotIDs
     * @throws Exception
     */
    public CaSlotIDs CaGetSlotIDs(short wTVSID) throws Exception {
        return CaManager.getInstance().CaGetSlotIDs(wTVSID);
    }

    /**
     * To Get Slot information by OperatorId and SlotId
     *
     * @param1 CA operator id
     * @param2 CA Slot id
     * @return CaSlotInfo
     * @throws Exception
     */
    public CaSlotInfo CaGetSlotInfo(short wTVSID, short bySlotID) throws Exception {
        return CaManager.getInstance().CaGetSlotInfo(wTVSID, bySlotID);
    }

    /**
     * To Get Ca Service Entitle Information
     *
     * @param CA operator id
     * @return CaServiceEntitles
     * @throws Exception
     */
    public CaServiceEntitles CaGetServiceEntitles(short wTVSID) throws Exception {
        return CaManager.getInstance().CaGetServiceEntitles(wTVSID);
    }

    /**
     * To Get Ca Entitle Id List
     *
     * @param CA operator id
     * @return CaEntitleIDs
     * @throws Exception
     */
    public CaEntitleIDs CaGetEntitleIDs(short wTVSID) throws Exception {
        return CaManager.getInstance().CaGetEntitleIDs(wTVSID);
    }

    /**
     * To Get ca Detitle information
     *
     * @param CA operator id
     * @return CaDetitleChkNums
     * @throws Exception
     */
    public CaDetitleChkNums CaGetDetitleChkNums(short wTVSID) throws Exception {
        return CaManager.getInstance().CaGetDetitleChkNums(wTVSID);
    }

    /**
     * To Get CA Detitle information Read State
     *
     * @param CA operator id
     * @return boolean
     * @throws Exception
     */
    public boolean CaGetDetitleReaded(short wTvsID) throws Exception {
        return CaManager.getInstance().CaGetDetitleReaded(wTvsID);
    }

    /**
     * To Delete Detitle Check Num
     *
     * @param1 CA operator id
     * @param2 CA Detitle Check Num
     * @return boolean
     * @throws Exception
     */
    public boolean CaDelDetitleChkNum(short wTvsID, int dwDetitleChkNum) throws Exception {
        return CaManager.getInstance().CaDelDetitleChkNum(wTvsID, dwDetitleChkNum);
    }

    /**
     * To Get Machine card corresponding situation
     *
     * @param1 Smart CARDS corresponding set-top box number(MaxNum=5)
     * @param2 set-top box List
     * @return short
     * @throws Exception
     */
    public short CaIsPaired(short pbyNum, String pbySTBID_List) throws Exception {
        return CaManager.getInstance().CaIsPaired(pbyNum, pbySTBID_List);
    }

    /**
     * To Get CA Platform Id
     *
     * @return short
     * @throws Exception
     */
    public short CaGetPlatformID() throws Exception {
        return CaManager.getInstance().CaGetPlatformID();
    }

    /**
     * To Stop IPPV Buy Dialog Info
     *
     * @param CA operator id
     * @return CaIPPVProgramInfo
     * @throws Exception
     */
    public short CaStopIPPVBuyDlg(CaStopIPPVBuyDlgInfo IppvInfo) throws Exception {
        return CaManager.getInstance().CaStopIPPVBuyDlg(IppvInfo);
    }

    /**
     * To Get Ca IPPV Program Information
     *
     * @param CA operator id
     * @return CaIPPVProgramInfo
     * @throws Exception
     */
    public CaIPPVProgramInfos CaGetIPPVProgram(short wTvsID) throws Exception {
        return CaManager.getInstance().CaGetIPPVProgram(wTvsID);
    }

    /**
     * To Get Ca Email Head Info
     *
     * @return CaEmailHeadInfo
     * @throws Exception
     */
    public CaEmailHeadsInfo CaGetEmailHeads(short byCount, short byFromIndex)
            throws Exception {
        return CaManager.getInstance().CaGetEmailHeads(byCount, byFromIndex);
    }

    /**
     * To Get Ca Email Head Info By Email Id
     *
     * @param Email Id
     * @return CaEmailHeadInfo
     * @throws Exception
     */
    public CaEmailHeadInfo CaGetEmailHead(int dwEmailID) throws Exception {
        return CaManager.getInstance().CaGetEmailHead(dwEmailID);
    }

    /**
     * To Get Ca Email Content By Email Id
     *
     * @param Email Id
     * @return CaEmailContentInfo
     * @throws Exception
     */
    public CaEmailContentInfo CaGetEmailContent(int dwEmailID) throws Exception {
        return CaManager.getInstance().CaGetEmailContent(dwEmailID);
    }

    /**
     * To Delete Email By Email Id
     *
     * @param Email Id
     * @throws Exception
     */
    public void CaDelEmail(int dwEmailID) throws Exception {
        CaManager.getInstance().CaDelEmail(dwEmailID);
    }

    /**
     * To Inquires Email Space Info
     *
     * @return CaEmailSpaceInfo
     * @throws Exception
     */
    public CaEmailSpaceInfo CaGetEmailSpaceInfo() throws Exception {
        return CaManager.getInstance().CaGetEmailSpaceInfo();
    }

    /**
     * To Reads mother card and Child Card match info
     *
     * @param CA operator id
     * @return CaOperatorChildStatus
     * @throws Exception
     */
    public CaOperatorChildStatus CaGetOperatorChildStatus(short wTVSID) throws Exception {
        return CaManager.getInstance().CaGetOperatorChildStatus(wTVSID);
    }

    /**
     * To Read Feed Data From Parent Card
     *
     * @param CA operator id
     * @return CaFeedDataInfo
     * @throws Exception
     */
    public CaFeedDataInfo CaReadFeedDataFromParent(short wTVSID) throws Exception {
        return CaManager.getInstance().CaReadFeedDataFromParent(wTVSID);
    }

    /**
     * To Write Feed Data To Child Card
     *
     * @param1 CA operator id
     * @param2 Feed Data Object
     * @return short
     * @throws Exception
     */
    public short CaWriteFeedDataToChild(short wTVSID, CaFeedDataInfo FeedData)
            throws Exception {
        return CaManager.getInstance().CaWriteFeedDataToChild(wTVSID, FeedData);
    }

    /**
     * To Refresh Interface
     *
     * @throws Exception
     */
    public void CaRefreshInterface() throws Exception {
        CaManager.getInstance().CaRefreshInterface();
    }

    /**
     * To Refresh Interface
     *
     * @throws Exception
     */
    public boolean CaOTAStateConfirm(int arg1, int arg2) throws Exception {
        return CaManager.getInstance().CaOTAStateConfirm(arg1, arg2);
    }

    /**
     * Register setOnCaEventListener(OnCaEventListener listener), your listener
     * will be triggered when the events posted from native code.
     *
     * @param listener OnCaEventListener
     */
    public void setOnCaEventListener(OnCaEventListener listener) {
        CaManager.getInstance().setOnCaEventListener(listener);
    }

    /**
     * To get current CA Event Id
     *
     * @param
     * @return int
     */
    public int getCurrentEvent() {
        return CaManager.getCurrentEvent();
    }

    /**
     * To get current CA message Type
     *
     * @param
     * @return int
     */
    public int getCurrentMsgType() {
        return CaManager.getCurrentMsgType();
    }

    /**
     * To set current CA Event Id
     *
     * @param CA Current Event id
     * @return
     */
    public void setCurrentEvent(int CurrentEvent) {
        CaManager.setCurrentEvent(CurrentEvent);
    }

    /**
     * To Set current CA message Type
     *
     * @param CA Msg Type
     * @return
     */
    public void setCurrentMsgType(int MsgType) {
        CaManager.setCurrentMsgType(MsgType);
    }
}
