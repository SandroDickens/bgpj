package org.lwhttpd.model;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Set;

public class ModelTests {
	@Test
	public void bgpDynamicProxyTests() {
		AbstractBGP bgp = new BGP();
		InvocationHandler invocationHandler = new BGPInvocationHandler(bgp);
		AbstractBGP proxy = (AbstractBGP)Proxy.newProxyInstance(AbstractBGP.class.getClassLoader(), new Class<?>[]{AbstractBGP.class}, invocationHandler);
		AbstractNeighbor dynamicNeighbor = new DynamicNeighbor(NetFamily.IPv4, NetSubFamily.UniCast, new Net4Address("192.16.100.1/24"));
		AbstractNeighbor neighbor = new Neighbor(NetFamily.IPv6, NetSubFamily.UniCast, new Net4Address("192.16.100.200/32"));
		proxy.addNeighbor(dynamicNeighbor);
		proxy.addNeighbor(neighbor);
		Set<AbstractNeighbor> neighbors = proxy.getNeighbors();
		for (AbstractNeighbor tmpNeighbor : neighbors) {
			System.out.println(tmpNeighbor);
		}
		proxy.delNeighbor(dynamicNeighbor);
		proxy.delNeighbor(neighbor);
		Assert.assertTrue(proxy.getNeighbors().isEmpty());
	}

	@Test
	public void netAddressTests() {
		NetAddress address = new Net4Address("192.168.100.10");
		Assert.assertTrue(address.isHostAddr());
		address = new Net4Address("192.168.100.10/32");
		Assert.assertTrue(address.isHostAddr());
		address = new Net4Address("192.168.100.10/24");
		Assert.assertFalse(address.isHostAddr());
	}

	@Test
	public void neighborTests() {
		AbstractNeighbor dynamicNeighbor = new DynamicNeighbor(NetFamily.IPv4, NetSubFamily.UniCast, new Net4Address("192.16.100.1/24"));
		AbstractNeighbor neighbor = new Neighbor(NetFamily.IPv6, NetSubFamily.UniCast, new Net4Address("192.16.100.200/32"));
		System.out.println(dynamicNeighbor);
		System.out.println(neighbor);
	}
}