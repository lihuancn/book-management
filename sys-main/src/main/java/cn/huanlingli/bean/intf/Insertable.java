package cn.huanlingli.bean.intf;

/**
 * 接口{@code Insertable}，用于指定该类可以插入到数据库中
 *
 * @author vorbote thills@vorbote.cn
 */
public interface Insertable {

    /**
     * 该函数将返回被格式化的数值，其中id可以不用返回，其余数值应该要
     * 以{@code ('string_val_1', int_val_2, ...)}的格式返回。
     *
     * @return 该实体的值
     */
    String GetValues();
}