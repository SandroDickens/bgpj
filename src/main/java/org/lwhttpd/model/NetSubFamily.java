package org.lwhttpd.model;

public enum NetSubFamily {
	UniCast(1),
	MultiCast(2);
	private final int value;

	NetSubFamily(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return switch (this.value) {
			case 1 -> "Unicast";
			case 2 -> "Multicast";
			default -> throw new IllegalStateException("Unexpected value: " + this.value);
		};
	}

	public static NetSubFamily valueOf(int value) {
		return switch (value) {
			case 1 -> NetSubFamily.UniCast;
			case 2 -> NetSubFamily.MultiCast;
			default -> throw new IllegalArgumentException("Unknown sub address family " + value);
		};
	}
}
