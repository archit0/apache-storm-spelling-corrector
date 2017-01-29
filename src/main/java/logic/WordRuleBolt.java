package logic;

import index.Searching;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;
import logic.model.Word;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by archit on 29/1/17.
 */
public class WordRuleBolt implements IRichBolt {
    private OutputCollector collector;
    private TopologyContext context;

    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.context = topologyContext;
        this.collector = outputCollector;

    }

    Map<Integer, String> map = new HashMap<Integer, String>();

    public void execute(Tuple tuple) {
        try {
            Word word = (Word) tuple.getValue(0);
            boolean status = word.isStatus();
            String ans="";
            if (!status) {
                ans = Searching.suggestion(word.getWord());
            }

            if (!map.containsKey(word.getSentenceId())) {
                map.put(word.getSentenceId(),word.getWord()+"_"+word.isStatus()+"_"+ans);
            }
            else {
                String words=map.get(word.getSentenceId());
                words+=" "+word.getWord()+"_"+word.isStatus()+"_"+ans;
                this.map.put(word.getSentenceId(), words);
            }

            collector.ack(tuple);
        }
        catch (Exception e) {
        }
    }

    public void cleanup() {
        System.out.println("FINAL MAP: " + this.map.toString());
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {


    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
