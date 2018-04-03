package cn.future.client.util;

/**
 * @author： ygl
 * @date： 2018/3/27-10:23
 * @Description：
 * 各种ID生成器
 */
public class IdGenerateHelper {

    private static IdWorker idWorker = new IdWorker(-1, -1);

    /**
     * 获取新唯一编号（18为数值）
     * 来自于twitter项目snowflake的id产生方案，全局唯一，时间有序。
     * 64位ID (42(毫秒)+5(机器ID)+5(业务编码)+12(重复累加))
     */
    public static String snowflakeId() {
        return String.valueOf(idWorker.nextId());
    }
}
