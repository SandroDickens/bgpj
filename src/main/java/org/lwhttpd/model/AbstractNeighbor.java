package org.lwhttpd.model;

public interface AbstractNeighbor {
	NetFamily getFamily();

	void setFamily(NetFamily afi, NetSubFamily safi);

	NetSubFamily getSubFamily();

	NetAddress getAddress();

	void setAddress(NetAddress address);
}
