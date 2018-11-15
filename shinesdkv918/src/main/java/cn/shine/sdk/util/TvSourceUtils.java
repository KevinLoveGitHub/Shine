package cn.shine.sdk.util;

import android.util.Log;

import com.mstar.android.tvapi.common.TvManager;
import com.mstar.android.tvapi.common.vo.TvOsType;
import com.mstar.tvsettingservice.DataBaseDesk.MAPI_VIDEO_ARC_Type;
import com.mstar.android.tvapi.common.vo.EnumVideoArcType;
import com.mstar.tvsettingservice.TvDeskProvider;
import com.mstar.tvsettingservice.DataBaseDesk.EN_MS_VIDEOITEM;
import cn.shine.sdk.tv.enums.EnumInputSource;
import com.mstar.tvsettingservice.DataBaseDesk.EN_MS_PICTURE;


/**
 * User: 姜春雨(1055655886@qq.com)
 * Date: 2015/9/21
 * Time: 10:16
 */
public class TvSourceUtils {
    public static EnumInputSource getCurSource() throws Exception {
        TvOsType.EnumInputSource cursourse= TvManager.getInstance().getCurrentInputSource();
       return EnumInputSource.values()[cursourse.ordinal()];
    }

    public static void setInputSource(EnumInputSource sourcType) throws Exception {
        Log.e("jiangcy","===setInputSource ");
        TvManager.getInstance().setInputSource(TvOsType.EnumInputSource.values()[sourcType.ordinal()]);
    }

    public static void setARCType(MAPI_VIDEO_ARC_Type eArcIdx) throws Exception {
        TvManager.getInstance().getPictureManager().setAspectRatio(EnumVideoArcType.values()[eArcIdx.ordinal()]);
    }

    public static void setColorMode(int mode,TvDeskProvider serviceProvider, short s) {
        switch(mode) {
            case 0:
            serviceProvider.getPictureManagerInstance().ExecVideoItem(
                            EN_MS_VIDEOITEM.MS_VIDEOITEM_BRIGHTNESS,
                            s);
            break;
            case 1:
            serviceProvider.getPictureManagerInstance().ExecVideoItem(
                    EN_MS_VIDEOITEM.MS_VIDEOITEM_CONTRAST, s);
            break;
            case 2:
            serviceProvider.getPictureManagerInstance().ExecVideoItem(
                    EN_MS_VIDEOITEM.MS_VIDEOITEM_SATURATION,
                    s);
            break;
        }
        // serviceProvider.getPictureManagerInstance().ExecVideoItem(EN_MS_VIDEOITEM.MS_VIDEOITEM_SHARPNESS, (short)20);
        // serviceProvider.getPictureManagerInstance().ExecVideoItem(EN_MS_VIDEOITEM.MS_VIDEOITEM_HUE, (short) 65);
    }

    public static void setUserMode(TvDeskProvider serviceProvider) {
        serviceProvider.getPictureManagerInstance().SetPictureModeIdx(EN_MS_PICTURE.values()[3]);
    }
}
