import com.trevorism.threshold.FastThresholdClient
import com.trevorism.threshold.PingingThresholdClient
import com.trevorism.threshold.ThresholdClient
import com.trevorism.threshold.model.Threshold

class Setup {

    static void main(String [] args){
        new PingingThresholdClient().ping()
        ThresholdClient thresholdClient = new FastThresholdClient()

        thresholdClient.create(new Threshold(name: "ICXXBT", description: "Icon vs Bitcoin stop", operator: "<=", value: 0.00003))
        thresholdClient.create(new Threshold(name: "ICXXBT", description: "Icon vs Bitcoin breakout", operator: ">=", value: 0.000042))

        thresholdClient.create(new Threshold(name: "XRPUSD", description: "Ripple vs USD stop", operator: "<=", value: 0.17))
        thresholdClient.create(new Threshold(name: "XRPUSD", description: "Ripple vs USD breakout", operator: ">=", value: 0.25))

        thresholdClient.create(new Threshold(name: "XBTUSD", description: "Bitcoin vs USD stop", operator: "<=", value: 8650))
        thresholdClient.create(new Threshold(name: "XBTUSD", description: "Bitcoin vs USD breakout", operator: ">=", value: 11000))

        thresholdClient.create(new Threshold(name: "SCXBT", description: "Siacoin vs Bitcoin stop", operator: "<=", value: 0.0000003))
        thresholdClient.create(new Threshold(name: "SCXBT", description: "Siacoin vs Bitcoin breakout", operator: ">=", value: 0.0000005))

        thresholdClient.create(new Threshold(name: "ADAXBT", description: "Cardano vs Bitcoin stop", operator: "<=", value: 0.0000092))
        thresholdClient.create(new Threshold(name: "ADAXBT", description: "Cardano vs Bitcoin breakout", operator: ">=", value: 0.000013))

        thresholdClient.create(new Threshold(name: "WAVESXBT", description: "Waves vs Bitcoin stop", operator: "<=", value: 0.00011))
        thresholdClient.create(new Threshold(name: "WAVESXBT", description: "Waves vs Bitcoin breakout", operator: ">=", value: 0.00016))

        thresholdClient.create(new Threshold(name: "BCHXBT", description: "Bitcoin Cash vs Bitcoin breakout", operator: ">=", value: 0.034))

        thresholdClient.create(new Threshold(name: "ALGOXBT", description: "Algorand vs Bitcoin stop", operator: "<=", value: 0.0000215))
        thresholdClient.create(new Threshold(name: "ALGOXBT", description: "Algorand vs Bitcoin breakout", operator: ">=", value: 0.00003))

    }
}
