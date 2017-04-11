package com.github.lhsm.util;

import java.util.Optional;

public class Shortener {

    private final String alphabet;

    public Shortener(String alphabet) {
        this.alphabet = alphabet;
    }

    public String encode(long index) {
        StringBuilder result = new StringBuilder();

        while (index > 0) {
            result.insert(0, alphabet.charAt((int) (index % alphabet.length())));
            index = index / alphabet.length();
        }

        return result.toString();
    }


    public Optional<Long> decode(String hash) {
        long index = 0;

        for (int i = 0; i < hash.length(); i++) {
            index = alphabet.length() * index + alphabet.indexOf(hash.charAt(i));
            if (index < 0) {
                return Optional.empty();
            }
        }

        return Optional.of(index);
    }

}
