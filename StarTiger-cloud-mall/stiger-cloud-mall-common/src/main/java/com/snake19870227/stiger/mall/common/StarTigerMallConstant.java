package com.snake19870227.stiger.mall.common;

/**
 * @author Bu HuaYang
 */
public class StarTigerMallConstant {

    public static class BusMessageChannel {

        public static final String INPUT_CHANNEL_SUFFIX = "-input";

        public static final String OUTPUT_CHANNEL_SUFFIX = "-output";

        public static final String MALL_BUS_CHANNEL = "mall-bus";
    }

    public static class BusMessageBusiness {

        public static final String EVENT_ORDER_CREATED = "EventOrderCreated";
    }
}
