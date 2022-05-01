package com.liucongblog.flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

public class FlinkTest2 {
    public static void main(String[] args) throws Exception {
        //Flink tools class to get running args
        ParameterTool parameterTool = ParameterTool.fromArgs(args);
        String hostname = parameterTool.get("hostname");
        int port = parameterTool.getInt("port");
        //step1: get running env
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //step2: get data stream
        DataStream<String> dataStream = env.socketTextStream(hostname, port);
        //step3: run business logic
        DataStream<WordCount> wordAndOneStream = dataStream.flatMap(new FlatMapFunction<String, WordCount>() {
            public void flatMap(String line, Collector<WordCount> out) {
                String[] fields = line.split(",");
                for (String word : fields) {
                    out.collect(new WordCount(word, 1L));
                }
            }
        });
        // like calculator in spark streaming
        DataStream<WordCount> resultStream = wordAndOneStream.keyBy("word").timeWindow(Time.seconds(2), Time.seconds(1))
                //count current 2 seconds per second.
                .sum("count");
        //step4: result print
        resultStream.print();
        //step5: task start
        env.execute("WindowWordCountJava");
    }

    public static class WordCount {
        public String word;
        public long count;

        //empty constructor
        public WordCount() {
        }

        public WordCount(String word, long count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public String toString() {
            return "WordCount{" + "word='" + word + "', count='" + count + "';}";
        }
    }
}
