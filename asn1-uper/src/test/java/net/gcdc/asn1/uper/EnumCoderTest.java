package net.gcdc.asn1.uper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import net.gcdc.asn1.datatypes.HasExtensionMarker;

public class EnumCoderTest {
    @HasExtensionMarker
    private enum ConstructedType {
        CHOICE(1), SEQUENCE(2), SET(3), SEQUENCE_OF(4), SET_OF(5);

        private final int value;

        private ConstructedType(int value) {
            this.value = value;
        }
    }

    @Test
    public void extensibleNoExt() {
        assertEquals(ConstructedType.SEQUENCE,
                UperEncoder.decode(new byte[] {0x10}, ConstructedType.class));
    }

    @Test
    public void extensibleExtSmall() {
        assertNull(UperEncoder.decode(new byte[] {(byte) 0xA9}, ConstructedType.class));
    }

    @Test
    public void extensibleWithLength() {
        assertNull(UperEncoder.decode(new byte[] {(byte) 0xC0, 0x50, 0x00}, ConstructedType.class));
    }
}
