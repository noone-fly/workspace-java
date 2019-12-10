package io.pierre.rtm;

public interface PackableEx extends Packable {
    void unmarshal(ByteBuf in);
}
