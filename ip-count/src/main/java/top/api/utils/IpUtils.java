package top.api.utils;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;
import top.api.domain.IpInfo;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;

/**
 * ip查询处理
 *
 * @author videomonster
 */
@Slf4j
public final class IpUtils {

    public static final String dbPath = "D:\\ip2region.db";
    private static final String UNKOWN_ADDRESS = "未知位置";

    /**
     * 根据IP获取地址
     *
     * @return 国家|区域|省份|城市|ISP
     */
    public static IpInfo getAddress(String ip) {
        if (ip == null || "".equals(ip)){
            return null;
        }
        return getAddress(ip, DbSearcher.BTREE_ALGORITHM);
    }

    /**
     * 根据IP获取地址
     *
     * @param ip
     * @param algorithm 查询算法
     * @return 国家|区域|省份|城市|ISP
     * @see DbSearcher
     * DbSearcher.BTREE_ALGORITHM; //B-tree
     * DbSearcher.BINARY_ALGORITHM //Binary
     * DbSearcher.MEMORY_ALGORITYM //Memory
     */
    @SneakyThrows
    public static IpInfo getAddress(String ip, int algorithm) {
        if (!Util.isIpAddress(ip)) {
            log.error("错误格式的ip地址: {}", ip);
            return null;
        }

        File file = new File(dbPath);
        if (!file.exists()) {
            log.error("地址库文件不存在");
            return null;
        }
        DbSearcher searcher = new DbSearcher(new DbConfig(), dbPath);
        DataBlock dataBlock;
        switch (algorithm) {
            case DbSearcher.BTREE_ALGORITHM:
                dataBlock = searcher.btreeSearch(ip);
                break;
            case DbSearcher.BINARY_ALGORITHM:
                dataBlock = searcher.binarySearch(ip);
                break;
            case DbSearcher.MEMORY_ALGORITYM:
                dataBlock = searcher.memorySearch(ip);
                break;
            default:
                log.error("未传入正确的查询算法");
                return null;
        }

        String ips = dataBlock.getRegion();

        String[] split = ips.split("\\|");

        IpInfo ipInfo;
        if (split.length == 5) {
            ipInfo = new IpInfo();
            ipInfo.setCountry(split[0]);
            ipInfo.setRegion(split[1]);
            ipInfo.setProvince(split[2]);
            ipInfo.setCity(split[3]);
            ipInfo.setIsp(split[4]);
        } else {
            return null;
        }

        return ipInfo;
    }
}
