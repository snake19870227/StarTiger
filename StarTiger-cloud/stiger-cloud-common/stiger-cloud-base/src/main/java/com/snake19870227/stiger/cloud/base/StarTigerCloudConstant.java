package com.snake19870227.stiger.cloud.base;

/**
 * @author Bu HuaYang
 */
public class StarTigerCloudConstant {

    public static class StreamTopic {

        public static final String INPUT_SUFFIX = "-input";
        public static final String OUTPUT_SUFFIX = "-output";

        public static final String DEFAULT_TOPIC_PREFIX = "stiger-topic";

        public static class DefaultTopic {
            public static final String INPUT = DEFAULT_TOPIC_PREFIX + INPUT_SUFFIX;
            public static final String OUTPUT = DEFAULT_TOPIC_PREFIX + OUTPUT_SUFFIX;
        }

        public static class RetryTopic {
            public static final String INPUT = "stiger-retry-topic" + INPUT_SUFFIX;
            public static final String OUTPUT = "stiger-retry-topic" + OUTPUT_SUFFIX;
        }

        public static class RetryDlqTopic {
            public static final String INPUT = "stiger-retry-dlq-topic" + INPUT_SUFFIX;
            public static final String OUTPUT = "stiger-retry-dlq-topic" + OUTPUT_SUFFIX;
        }
    }
}
