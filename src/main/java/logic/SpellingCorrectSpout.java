package logic;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import logic.model.Sentence;

import java.util.Map;

/**
 * Created by archit on 29/1/17.
 */
public class SpellingCorrectSpout implements IRichSpout {
    private TopologyContext context;
    private SpoutOutputCollector collector;

    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.context=topologyContext;
        this.collector=spoutOutputCollector;
    }
    String arSent[]={"My countri Archit Dwivedi this is a grl and I am a very good buoy. ","Helo"};

    int id=0;
    public void nextTuple() {
        if(id<arSent.length) {
            Sentence ob=new Sentence(id,arSent[id]);
            collector.emit(new Values(ob));
            id++;
        }

    }
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("sentence"));
    }

    public void close() {

    }

    public void activate() {

    }

    public void deactivate() {

    }



    public void ack(Object o) {

    }

    public void fail(Object o) {

    }



    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
