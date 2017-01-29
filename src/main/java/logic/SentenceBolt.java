package logic;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import logic.model.Sentence;
import logic.model.Word;

import java.util.Map;

/**
 * Created by archit on 29/1/17.
 */
public class SentenceBolt implements IRichBolt {
    private TopologyContext context;
    private OutputCollector collector;

    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.context=topologyContext;
        this.collector=outputCollector;
    }

    public void execute(Tuple tuple) {
        Sentence sent=(Sentence)tuple.getValue(0);
        String sentence[]=sent.getSentence().split(" ");
        for (int x =0;x<sentence.length;x++) {
            Word ob=new Word(sent.getId(),x,sentence[x],false);
            collector.emit(new Values(ob));
        }
        collector.ack(tuple);
    }

    public void cleanup() {

    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
            outputFieldsDeclarer.declare(new Fields("word"));
    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
