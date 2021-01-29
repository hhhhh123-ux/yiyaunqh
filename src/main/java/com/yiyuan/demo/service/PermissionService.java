package com.yiyuan.demo.service;


import com.yiyuan.demo.entiy.Permission;

/**
 * @author SongYC
 */
public interface PermissionService {
    /**保存功能
     * @param record
     * @return int
     */
    int insertSelective(Permission record);

    /**保存功能
     * @param code
     * @return int
     */
    int countselectCode(String code);
    /**更新功能
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(Permission record);
    /**查询详情功能
     * @param id
     * @return int
     */
    Permission selectByPrimaryKey(Long id);
}
