package io.agora.dynamickey5;

/**
 * Created by Li on 10/1/2016.
 */
public interface Packable {
    ByteBuf marshall(ByteBuf out);
}
