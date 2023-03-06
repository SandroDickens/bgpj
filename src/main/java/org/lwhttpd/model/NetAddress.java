package org.lwhttpd.model;

import java.io.Serial;
import java.io.Serializable;

public abstract class NetAddress implements Serializable {
	@Serial
	private static final long serialVersionUID = 1;

	public abstract boolean isOverlap(NetAddress address);

	public abstract boolean isHostAddr();

	public abstract short getMaskLength();
}
