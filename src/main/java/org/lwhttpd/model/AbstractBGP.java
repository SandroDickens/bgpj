package org.lwhttpd.model;

import java.util.Set;

public interface AbstractBGP {
	Set<AbstractNeighbor> getNeighbors();

	boolean addNeighbor(AbstractNeighbor neighbor);

	boolean delNeighbor(AbstractNeighbor neighbor);
}
