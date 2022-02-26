package restriction;

import lombok.NonNull;


public class RestrictionAtIp {
    private final String ip;
    private final int count;
    private final int period;

    public RestrictionAtIp(@NonNull String ip, int count, int period) {
        this.ip = ip;
        this.count = count;
        this.period = period;
    }
    

}
