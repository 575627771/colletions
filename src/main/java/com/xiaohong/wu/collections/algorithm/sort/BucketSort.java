package com.xiaohong.wu.collections.algorithm.sort;

/**
 * 桶排序
 *
 * 主要思想是 先判断出数据大小的范围，然后对数据进行初步的分桶（可以先指定每个桶装多少数据再计算出需要多少桶），
 * 当数据分布不均匀是，需要对数据量大或者已经超出桶容量的桶进行再分桶，直到能容纳下，然后再在桶内进行快速排序
 * 最后依次从桶中取出数据，排序完成
 *
 *
 * @author Wolf.2
 * @version 1.0
 * @date 2019/7/4 16:24
 **/
public class BucketSort {
}
