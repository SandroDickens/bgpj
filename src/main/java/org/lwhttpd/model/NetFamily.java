package org.lwhttpd.model;

public enum NetFamily {
	IPv4(1),
	IPv6(2),
	L2VPN(3);
	private final int value;

	NetFamily(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return switch (this.value) {
			case 1 -> "IPv4";
			case 2 -> "IPv6";
			case 3 -> "L2VPN";
			default -> throw new IllegalStateException("Unexpected value: " + this.value);
		};
	}

	public static NetFamily valueOf(int value) {
		return switch (value) {
			case 1 -> NetFamily.IPv4;
			case 2 -> NetFamily.IPv6;
			case 3 -> NetFamily.L2VPN;
			default -> throw new IllegalArgumentException("Unknown address family " + value);
		};
	}
}
