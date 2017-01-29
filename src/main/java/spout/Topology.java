package spout;

import index.Searching;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 * Created by archit on 29/1/17.
 */
public class Topology {
    public static void main(String[] args)throws Exception{
        Config config=new Config();
        config.setDebug(true);
        Searching.load();

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("sentenceReader", new SpellingCorrectSpout());

        builder.setBolt("sentence", new SentenceBolt())
                .shuffleGrouping("sentenceReader");




        builder.setBolt("wordChecker", new WordCheckBolt())
                .shuffleGrouping("sentence");




        builder.setBolt("wordRule",new WordRuleBolt())
        .shuffleGrouping("wordChecker");


        // builder.setBolt("call-log-counter-bolt", new FakeCallCounterBolt())
         ///       .shuffleGrouping("call-log-creator-bolt");

        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("LogAnalyserStormk", config, builder.createTopology());
        Thread.sleep(10000);

        //Stop the topology

        cluster.shutdown();


    }
}
