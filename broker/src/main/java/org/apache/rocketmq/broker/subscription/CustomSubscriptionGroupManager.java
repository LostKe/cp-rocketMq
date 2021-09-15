package org.apache.rocketmq.broker.subscription;

import org.apache.rocketmq.common.ConfigManager;

/**
 * @author zhangshuqing
 * @version 1.0
 * @description
 * @Date 2021/8/31 16:45
 **/
public class CustomSubscriptionGroupManager extends ConfigManager {
    @Override
    public String encode() {
        return null;
    }

    @Override
    public String configFilePath() {
        return null;
    }

    @Override
    public void decode(String jsonString) {
    }

    @Override
    public String encode(boolean prettyFormat) {
        return null;
    }
}
