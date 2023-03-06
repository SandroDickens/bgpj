package org.lwhttpd.model;

import java.io.Serial;

public class Net6Address extends NetAddress {
	@Serial
	private static final long serialVersionUID = 1;
	static final int IN6ADDRSZ = 4;
	static final int MASK_BITS = 128;
	private int prefix;
	private short maskLength;

	Net6Address(String addr) {
		this.prefix = 0;
		maskLength = MASK_BITS;
		String[] ip;
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
		ip = hostAddr.split(":");
		for (short i = 0; i < IN6ADDRSZ; i++) {
			this.prefix = this.prefix << 8;
			this.prefix |= Integer.parseInt(ip[i], 10);
		}
	}

	@Override
	public boolean isOverlap(NetAddress address) {
		if (address instanceof Net6Address) {
			short minLength = this.maskLength < ((Net6Address)address).maskLength?this.maskLength:((Net6Address)address).maskLength;
			int mask = 0x1 << (MASK_BITS - 1);
			for (short i = 0; i < minLength; i++) {
				mask |= mask >> 1;
			}
			int prefix1 = this.prefix & mask;
			int prefix2 = ((Net6Address)address).prefix & mask;
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
}
