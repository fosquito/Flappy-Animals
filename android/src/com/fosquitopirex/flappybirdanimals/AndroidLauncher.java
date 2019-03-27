package com.fosquitopirex.flappybirdanimals;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;


public class AndroidLauncher extends AndroidApplication implements AdHandler,RewardedVideoAdListener, GoogleServices, ActionResolver {

	protected AdView adView;
    private RewardedVideoAd adRewardedVideoView;
    private static final String AD_UNIT_ID_BANNER = "ca-app-pub-8030976745457467/5859164679";
    private static final String REWARDED_VIDEO_AD_UNIT_ID = "ca-app-pub-8030976745457467/9709676123";
    private static final String INTERSTITIAL_UNIT_ID = "ca-app-pub-8030976745457467/7439015247";
    private VideoEventListener vel;
    private boolean is_video_ad_loaded;
    private InterstitialAd interstitialAd;

	private final int SHOW_BANNER = 1;
    private final int HIDE_BANNER = 0;

	Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SHOW_BANNER:adView.setVisibility(View.VISIBLE);break;
                case HIDE_BANNER:adView.setVisibility(View.GONE);break;
            }
        }
    };

	@Override
	protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout layout = new RelativeLayout(this);

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        View gameView = initializeForView(new FlappyBirdAnimals(this, this, this), config);
        layout.addView(gameView);
        setupRewarded();

        adView = new AdView(this);

        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId(AD_UNIT_ID_BANNER);
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(INTERSTITIAL_UNIT_ID);


        AdRequest.Builder builder = new AdRequest.Builder();
        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        layout.addView(adView,adParams);
        adView.loadAd(builder.build());
        setContentView(layout);

		//initialize(new FlappyBirdAnimals(), config);
	}

    public boolean hasVideoLoaded(){
        if(is_video_ad_loaded) {
            return true;
        }
        runOnUiThread(new Runnable() {
            public void run() {
                if (!adRewardedVideoView.isLoaded()) {
                    loadRewardedVideoAd();
                }
            }
        });
        return false;
    }

    @Override
    public void showBanner(boolean show) {
        handler.sendEmptyMessage(show ? SHOW_BANNER : HIDE_BANNER);
    }

    public void loadRewardedVideoAd() {
        adRewardedVideoView.loadAd(REWARDED_VIDEO_AD_UNIT_ID, new AdRequest.Builder().build());
    }

    public void setupRewarded() {
        adRewardedVideoView = MobileAds.getRewardedVideoAdInstance(this);
        adRewardedVideoView.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();
    }

    public void showRewardedVideoAd(){
        runOnUiThread(new Runnable() {
            public void run() {
                if (adRewardedVideoView.isLoaded()) {
                    adRewardedVideoView.show();
                } else {
                    loadRewardedVideoAd();
                }
            }
        });
    }

    @Override
    public void onRewarded(RewardItem reward) {
        if(vel != null) {
            // The type and the amount can be set in your AdMob console
            vel.onRewardedEvent(reward.getAmount(), reward.getAmount());
        }
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public void onRewardedVideoCompleted() {

    }

    // Each time the video ends we need to load a new one
    @Override
    public void onRewardedVideoAdClosed() {
        is_video_ad_loaded = false;
        loadRewardedVideoAd();
        if(vel != null) {
            vel.onRewardedVideoAdClosedEvent();
        }
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        if(vel != null) {
            vel.onRewardedVideoAdLoadedEvent();
        }
        is_video_ad_loaded = true;
    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    public void setVideoEventListener (VideoEventListener listener) {
        this.vel = listener;
    }

    @Override
    public void showOrLoadInterstital() {
        try {
            runOnUiThread(new Runnable() {
                public void run() {
                    if (interstitialAd.isLoaded()) {
                        interstitialAd.show();
                    }
                    else {
                        AdRequest interstitialRequest = new AdRequest.Builder().build();
                        interstitialAd.loadAd(interstitialRequest);
                    }
                }
            });
        } catch (Exception e) {
        }
    }

}