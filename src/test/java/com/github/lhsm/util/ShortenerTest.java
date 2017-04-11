package com.github.lhsm.util;

import com.github.lhsm.Application;
import com.github.lhsm.util.Shortener;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;

public class ShortenerTest {

    private Shortener shortener = new Shortener(Application.ALPHABET);

    @Test
    public void decode() throws Exception {
        Assert.assertThat(shortener.decode("ssdfSDSDD4"), is(Optional.of(605342676851236509L)));
    }

    @Test
    public void decodeMax() throws Exception {
        Assert.assertThat(shortener.decode("ABCDEFGHIJKLMNOPQ"), is(Optional.empty()));
    }

    @Test
    public void encode() throws Exception {
        Assert.assertThat(shortener.encode(623424234L), is("qLzHk"));
    }

    @Test
    public void encodeMax() throws Exception {
        Assert.assertThat(shortener.encode(Long.MAX_VALUE), is("K0VIxAiFIwH"));
    }

    @Test
    public void encodeDecode() throws Exception {
        Assert.assertThat(shortener.decode(shortener.encode(645345345L)), is(Optional.of(645345345L)));
    }

    @Test
    public void decodeEncode() throws Exception {
        Assert.assertThat(shortener.encode(shortener.decode("4C12C2C12").get()), is("4C12C2C12"));
    }

}