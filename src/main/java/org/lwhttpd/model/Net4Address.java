package org.lwhttpd.model;

import java.io.Serial;

public class Net4Address extends NetAddress {
	@Serial
	private static final long serialVersionUID = 1;
	static final int INADDRSZ = 4;
	static final int MASK_BITS = 32;
	private int prefix;
	private short maskLength;

	public Net4Address(String addr) {
		this.prefix = 0;
		maskLength = MASK_BITS;
		int split = addr.indexOf('/');
		String hostAddr = addr;
		if (split != -1) {
			hostAddr = addr.substring(0, split);
			String tmpMask = addr.substring(split + 1);
			maskLength = Short.parseShort(tmpMask, 10);
			if ((maskLength <= 0) || (maskLength > MASK_BITS)) {
				throw new IllegalArgumentException("The mask length must be greater than 0 and less than or equal to " + MASK_BITS);
			}
		}
		String[] ip = hostAddr.split("\\.");
		prefix |= (Integer.parseInt(ip[0], 10) << 24);
		prefix |= (Integer.parseInt(ip[1], 10) << 16);
		prefix |= (Integer.parseInt(ip[2], 10) << 8);
		prefix |= Integer.parseInt(ip[3], 10);
	}

	@Override
	public boolean isOverlap(NetAddress address) {
		if (address instanceof Net4Address) {
			short minLength = this.maskLength < ((Net4Address)address).maskLength?this.maskLength:((Net4Address)address).maskLength;
			int mask = 0x1 << (MASK_BITS - 1);
			for (short i = 0; i < minLength; i++) {
				mask |= mask >> 1;
			}
			int prefix1 = this.prefix & mask;
			int prefix2 = ((Net4Address)address).prefix & mask;
			return prefix1 == prefix2;
		}
		else {
			throw new IllegalArgumentException("Not a Net4Address object.");
		}
	}

	@Override
	public boolean isHostAddr() {
		return this.maskLength == MASK_BITS;
	}

	@Override
	public short getMaskLength() {
		return this.maskLength;
	}

	@Override
	public int hashCode() {
		return this.prefix;
	}

	@Override
	public boolean equals(Object other) {
		/* Reflexive */
		if (this == other) {
			return true;
		}

		/* x instanceof SomeClass is false if x is null */
		if (other instanceof Net4Address address) {
			return (address.prefix == this.prefix) && (address.maskLength == this.maskLength);
		}
		return false;
	}

	@Override
	public String toString() {
		String addr = Long.toString((this.prefix & 0xFF000000L) >> 24, 10) + "." + Long.toString((this.prefix & 0xFF0000) >> 16, 10) + "." + Long.toString((this.prefix & 0xFF00) >> 8, 10) + "." + Long.toString(this.prefix & 0xFF, 10);
		if (maskLength != MASK_BITS) {
			addr += "/" + this.maskLength;
		}
		return addr;
	}
}
