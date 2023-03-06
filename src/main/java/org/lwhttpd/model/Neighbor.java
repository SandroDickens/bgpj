package org.lwhttpd.model;

public class Neighbor implements AbstractNeighbor {
	private NetFamily family;
	private NetSubFamily subFamily;
	private NetAddress address;

	public Neighbor(NetFamily family, NetSubFamily subFamily, NetAddress address) {
		if (!address.isHostAddr()) {
			throw new IllegalArgumentException("The address mask length of non-dynamic neighbor must be 32");
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
		return "Neighbor{" + "family=" + family + ", subFamily=" + subFamily + ", address=" + address + '}';
	}
}
