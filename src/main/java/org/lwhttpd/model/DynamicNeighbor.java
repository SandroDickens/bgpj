package org.lwhttpd.model;

public class DynamicNeighbor implements AbstractNeighbor {
	private NetFamily family;
	private NetSubFamily subFamily;
	private NetAddress address;

	public DynamicNeighbor(NetFamily family, NetSubFamily subFamily, NetAddress address) {
		if (address.isHostAddr()) {
			throw new IllegalArgumentException("The address mask length of dynamic neighbor must be greater than 0 and less than 32/128");
		}
		this.family = family;
		this.subFamily = subFamily;
		this.address = address;
	}

	@Override
	public NetFamily getFamily() {
		return this.family;
	}

	@Override
	public void setFamily(NetFamily afi, NetSubFamily safi) {
		this.family = afi;
		this.subFamily = safi;
	}

	@Override
	public NetSubFamily getSubFamily() {
		return this.subFamily;
	}

	@Override
	public NetAddress getAddress() {
		return this.address;
	}

	@Override
	public void setAddress(NetAddress address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "DynamicNeighbor{" + "family=" + family + ", subFamily=" + subFamily + ", address=" + address + '}';
	}
}
