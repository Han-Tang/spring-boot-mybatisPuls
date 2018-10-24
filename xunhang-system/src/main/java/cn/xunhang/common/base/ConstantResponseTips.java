package cn.xunhang.common.base;

public class ConstantResponseTips {

    private static final String NO_DATA = "没有数据";
    private static final String SUBMIT_SUCCESS = "提交成功";
    private static final String DELETE_SUCCESS = "删除成功";
    private static final String MODIFY_SUCCESS = "修改成功";
    private static final String WRONG_DATA = "数据出错";


    public static String getSubmitSuccess() {
        return SUBMIT_SUCCESS;
    }

    public static String getDeleteSuccess() {
        return DELETE_SUCCESS;
    }

    public static String getModifySuccess() {
        return MODIFY_SUCCESS;
    }

    public static String getNoData() {
        return NO_DATA;
    }
}
