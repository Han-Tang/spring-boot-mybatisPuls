package cn.xunhang.common.base;

/**
 * dly
 */
public class ObjectResponse<T> extends Response {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ObjectResponse(T object) {
        this.data = object;
    }


}
