package arch.rpc;

/**
 * Created by i325622 on 4/5/17.
 */
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String ping) {
        return ping != null?ping + " --> I am ok.": " I am ok.";
    }
}
