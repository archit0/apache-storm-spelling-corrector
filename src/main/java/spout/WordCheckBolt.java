package spout;

import index.Searching;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import spout.model.Word;

import java.util.Map;

/**
 * Created by archit on 29/1/17.
 */
public class WordCheckBolt implements IRichBolt {
    private TopologyContext context;
    private OutputCollector collector;

    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.context=topologyContext;
        this.collector=outputCollector;


    }
    public void execute(Tuple tuple) {
        try {
            Word word=(Word)tuple.getValue(0);
            boolean reply=Searching.searchWord(word.getWord());
                word.setStatus(reply);
                collector.emit(new Values(word));

            collector.ack(tuple);
        }
        catch (Exception e){

        }
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
