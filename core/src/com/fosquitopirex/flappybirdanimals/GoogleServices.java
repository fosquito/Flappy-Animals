package com.fosquitopirex.flappybirdanimals;

public interface GoogleServices {
    public boolean hasVideoLoaded();
    public void loadRewardedVideoAd();
    public void showRewardedVideoAd();
    public void setVideoEventListener(VideoEventListener listener);
}
