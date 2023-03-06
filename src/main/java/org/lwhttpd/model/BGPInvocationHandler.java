package org.lwhttpd.model;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class BGPInvocationHandler implements InvocationHandler {
	private final AbstractBGP target;

	public BGPInvocationHandler(AbstractBGP bgp) {
		this.target = bgp;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return method.invoke(target, args);
	}
}
