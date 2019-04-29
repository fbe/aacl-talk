package name.felixbecker.aacl.shadingdemo.fancylib.bad;

import java.util.Date;

public class Echo {

    final String echoValue;
    final Date echoDate;

    public Echo(String echoValue, Date echoDate) {
        this.echoValue = echoValue;
        this.echoDate = echoDate;
    }

    public String getEchoValue() {
        return echoValue;
    }

    public Date getEchoDate() {
        return echoDate;
    }
}
