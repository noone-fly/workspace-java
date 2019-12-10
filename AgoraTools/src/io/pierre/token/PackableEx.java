package io.pierre.token;

public interface PackableEx extends Packable {
    void unmarshal(ByteBuf in);
}
