package com.fosquitopirex.flappybirdanimals;

public interface VideoEventListener {
    void onRewardedEvent(int type, int amount);
    void onRewardedVideoAdLoadedEvent();
    void onRewardedVideoAdClosedEvent();
}
