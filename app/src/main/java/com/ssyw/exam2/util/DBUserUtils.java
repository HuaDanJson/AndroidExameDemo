package com.ssyw.exam2.util;

import android.content.Context;

import com.aidebar.greendaotest.gen.UserInfoDao;
import com.ssyw.exam2.data.UserInfo;

import java.util.List;

public class DBUserUtils {

    private UserInfoDao dbCurrentUserDao;

    private static DBUserUtils dbCurrentUserUtils = null;

    public DBUserUtils(Context context) {
        dbCurrentUserDao = DaoManager.getInstance(context).getNewSession().getUserInfoDao();
    }

    public static DBUserUtils getInstance() {
        return dbCurrentUserUtils;
    }

    public static void Init(Context context) {
        if (dbCurrentUserUtils == null) {
            dbCurrentUserUtils = new DBUserUtils(context);
        }
    }

    /**
     * 完成对数据库中插入一条数据操作
     *
     * @param
     * @return
     */
    public void insertOneData(UserInfo dbUserInvestment) {
        dbCurrentUserDao.insertOrReplace(dbUserInvestment);
    }

    /**
     * 完成对数据库中插入多条数据操作
     *
     * @param dbUserInvestmentList
     * @return
     */
    public boolean insertManyData(List<UserInfo> dbUserInvestmentList) {
        boolean flag = false;
        try {
            dbCurrentUserDao.insertOrReplaceInTx(dbUserInvestmentList);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库中删除一条数据操作
     *
     * @param dbUserInvestment
     * @return
     */
    public boolean deleteOneData(UserInfo dbUserInvestment) {
        boolean flag = false;
        try {
            dbCurrentUserDao.delete(dbUserInvestment);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库中删除一条数据 ByKey操作
     *
     * @return
     */
    public boolean deleteOneDataByKey(long id) {
        boolean flag = false;
        try {
            dbCurrentUserDao.deleteByKey(id);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库中批量删除数据操作
     *
     * @return
     */
    public boolean deleteManData(List<UserInfo> dbUserInvestmentList) {
        boolean flag = false;
        try {
            dbCurrentUserDao.deleteInTx(dbUserInvestmentList);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库中数据全部删除
     *
     * @return
     */
    public boolean deleteAll() {
        boolean flag = false;
        try {
            dbCurrentUserDao.deleteAll();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库更新数据操作
     *
     * @return
     */
    public boolean updateData(UserInfo dbUserInvestment) {
        boolean flag = false;
        try {
            dbCurrentUserDao.update(dbUserInvestment);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库批量更新数据操作
     *
     * @return
     */
    public boolean updateManData(List<UserInfo> dbUserInvestmentList) {
        boolean flag = false;
        try {
            dbCurrentUserDao.updateInTx(dbUserInvestmentList);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库查询数据操作
     *
     * @return
     */
    public UserInfo queryOneData(long id) {
        return dbCurrentUserDao.load(id);
    }

    /**
     * 完成对数据库查询所有数据操作
     *
     * @return
     */
    public List<UserInfo> queryAllData() {
        return dbCurrentUserDao.loadAll();
    }

    /**
     * 完成对数据库条件查询数据操作 bookName
     *
     * @return
     */
    public List<UserInfo> queryUserDependBookName(String userName) {
        return dbCurrentUserDao.queryBuilder().where(UserInfoDao.Properties.UserName.like("%" + userName + "%")).build().list();
    }

    public List<UserInfo> queryUserDependUserName(String userName) {
        return dbCurrentUserDao.queryBuilder().where(UserInfoDao.Properties.UserName.eq(userName)).build().list();
    }

}
