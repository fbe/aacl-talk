package name.felixbecker.aacl.shadingexample.fancylibrary.impl;

import name.felixbecker.aacl.shadingexample.fancylibrary.api.Echo;

import java.util.Date;

public class EchoEntity implements Echo {

    private final String echoValue;
    private final Date echoDate;

    public EchoEntity(String echoValue, Date echoDate) {
        this.echoValue = echoValue;
        this.echoDate = echoDate;
    }

    @Override
    public String getEchoValue() {
        return echoValue;
    }

    @Override
    public Date getEchoDate() {
        return echoDate;
    }
}
