package com.github.jedis;

import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;

public class Use_Jedis {

    protected static final HostAndPort hnp = HostAndPortUtil.getRedisServers().get(0);

    public static void main(String[] args) {
        //Jedis jedis = new Jedis(hnp, DefaultJedisClientConfig.builder().build());
        // ???DefaultJedisClientConfig in 3.5.2 is 404
//        安全池
        securePool();
    }

    /*
    pool.setTestOnBorrow(true);
    pool.setTestWhileIdle(true);
    pool.setTestOnReturn(true);
     */
    private static void securePool() {
        JedisPoolConfig config = new JedisPoolConfig();
        //config.setTestOnBorrow(true);
        JedisPool pool = new JedisPool(config, hnp.getHost(), hnp.getPort(), 2000, "foobared");
        System.out.println(pool);//  redis.clients.jedis.JedisPool@759ebb3d
        try (Jedis jedis = pool.getResource()) { // 没有预热，取值的时候获取
            jedis.set("foo", "bar");
        }
        pool.close();
        boolean closed = pool.isClosed();
        System.out.println(closed);
    }


    static class HostAndPortUtil {

        private static List<HostAndPort> redisHostAndPortList = new ArrayList<>();
        private static List<HostAndPort> sentinelHostAndPortList = new ArrayList<>();
        private static List<HostAndPort> clusterHostAndPortList = new ArrayList<>();

        private HostAndPortUtil() {
            throw new InstantiationError("Must not instantiate this class");
        }

        public static List<HostAndPort> getRedisServers() {
            return redisHostAndPortList;
        }

        public static List<HostAndPort> getSentinelServers() {
            return sentinelHostAndPortList;
        }

        public static List<HostAndPort> getClusterServers() {
            return clusterHostAndPortList;
        }

        static {
            redisHostAndPortList.add(new HostAndPort("localhost", Protocol.DEFAULT_PORT));
            redisHostAndPortList.add(new HostAndPort("localhost", Protocol.DEFAULT_PORT + 1));
            sentinelHostAndPortList.add(new HostAndPort("localhost", Protocol.DEFAULT_SENTINEL_PORT));
            sentinelHostAndPortList.add(new HostAndPort("localhost", Protocol.DEFAULT_SENTINEL_PORT + 1));
            clusterHostAndPortList.add(new HostAndPort("localhost", 7379));
            clusterHostAndPortList.add(new HostAndPort("localhost", 7380));
            String envRedisHosts = System.getProperty("redis-hosts");
            String envSentinelHosts = System.getProperty("sentinel-hosts");
            String envClusterHosts = System.getProperty("cluster-hosts");
            redisHostAndPortList = parseHosts(envRedisHosts, redisHostAndPortList);
            sentinelHostAndPortList = parseHosts(envSentinelHosts, sentinelHostAndPortList);
            clusterHostAndPortList = parseHosts(envClusterHosts, clusterHostAndPortList);
        }

        public static List<HostAndPort> parseHosts(String envHosts,
                                                   List<HostAndPort> existingHostsAndPorts) {
            if (null != envHosts && 0 < envHosts.length()) {
                String[] hostDefs = envHosts.split(",");
                if (null != hostDefs && 2 <= hostDefs.length) {
                    List<HostAndPort> envHostsAndPorts = new ArrayList<>(hostDefs.length);
                    for (String hostDef : hostDefs) {
                        String[] hostAndPortParts = HostAndPort.extractParts(hostDef);
                        if (null != hostAndPortParts && 2 == hostAndPortParts.length) {
                            String host = hostAndPortParts[0];
                            int port = Protocol.DEFAULT_PORT;
                            try {
                                port = Integer.parseInt(hostAndPortParts[1]);
                            } catch (final NumberFormatException nfe) {
                            }
                            envHostsAndPorts.add(new HostAndPort(host, port));
                        }
                    }
                    return envHostsAndPorts;
                }
            }
            return existingHostsAndPorts;
        }
    }
}
