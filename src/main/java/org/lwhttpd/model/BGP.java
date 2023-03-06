package org.lwhttpd.model;

import java.util.HashSet;
import java.util.Set;

public class BGP implements AbstractBGP {
	private final Set<AbstractNeighbor> neighbors;

	public BGP() {
		this.neighbors = new HashSet<>();
	}

	@Override
	public Set<AbstractNeighbor> getNeighbors() {
		return this.neighbors;
	}

	@Override
	public boolean addNeighbor(AbstractNeighbor neighbor) {
		return this.neighbors.add(neighbor);
	}

	@Override
	public boolean delNeighbor(AbstractNeighbor neighbor) {
		return this.neighbors.remove(neighbor);
	}
}
