package cn.shine.sdk.tv;

import android.content.Context;

import com.mstar.tvsettingservice.CaDesk;
import com.mstar.tvsettingservice.ChannelDesk;
import com.mstar.tvsettingservice.CiDesk;
import com.mstar.tvsettingservice.CommonDesk;
import com.mstar.tvsettingservice.DataBaseDesk;
import com.mstar.tvsettingservice.DemoDesk;
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


public class TvDeskProviderWrapper implements TvDeskProvider {
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
	Context mContext;

	public TvDeskProviderWrapper(Context context) {
		super();
		this.mContext = context;
	}

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
}
