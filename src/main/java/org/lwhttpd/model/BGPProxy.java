package org.lwhttpd.model;

import java.util.Set;

public class BGPProxy implements AbstractBGP {
	private final AbstractBGP bgpInst;

	public BGPProxy(AbstractBGP bgpInst) {
		this.bgpInst = bgpInst;
	}

	@Override
	public Set<AbstractNeighbor> getNeighbors() {
		return bgpInst.getNeighbors();
	}

	@Override
	public boolean addNeighbor(AbstractNeighbor neighbor) {
		return bgpInst.addNeighbor(neighbor);
	}

	@Override
	public boolean delNeighbor(AbstractNeighbor neighbor) {
		return false;
	}
}
