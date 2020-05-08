package com.xm.httpapi.BaseApi;

import com.xm.httpapi.BaseApiStatus.ErrorType;
import com.xm.httpapi.BaseApiStatus.ServerException;
import com.xm.httpapi.BaseUtils.BaseLog;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 *
 * @param <T>
 */

public class ErrorTransformer<T> implements ObservableTransformer<BaseHttpResult<T>, T> {

    private static ErrorTransformer errorTransformer = null;
    private static final String TAG = "ErrorTransformer";

//    @Override
//    public ObservableSource<T> apply(Observable<BaseHttpResult<T>> upstream) {
//        return upstream.map(new Function<BaseHttpResult<T>, T>() {
//            @Override
//            public T apply(BaseHttpResult<T> tBaseHttpResult) throws Exception {
//                if (tBaseHttpResult == null)
//                    throw new ServerException(ErrorType.EMPTY_BEAN, "解析对象为空");
//
//                log.e(tBaseHttpResult.toString());
//
//                if (tBaseHttpResult.getCode() != ErrorType.SUCCESS)
//                    throw new ServerException(tBaseHttpResult.getCode(), tBaseHttpResult.getMessage());
//                return tBaseHttpResult.getData();
//            }
//        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends T>>() {
//            @Override
//            public ObservableSource<? extends T> apply(Throwable throwable) throws Exception {
//                throwable.printStackTrace();
//                return Observable.error(ExceptionEngine.handleException(throwable));
//            }
//        });
//    }

    @Override
    public ObservableSource<T> apply(Observable<BaseHttpResult<T>> upstream) {
        return upstream.map(new Function<BaseHttpResult<T>, T>() {
            @Override
            public T apply(BaseHttpResult<T> tBaseHttpResult) throws Exception {
                if (tBaseHttpResult == null)
                    throw new ServerException(ErrorType.EMPTY_BEAN.getCode(), ErrorType.EMPTY_BEAN.getMessage());

                BaseLog.e(tBaseHttpResult.toString());

                if (tBaseHttpResult.getCode() != ErrorType.SUCCESS.getCode())
                    throw new ServerException(tBaseHttpResult.getCode(), tBaseHttpResult.getMessage());
                return tBaseHttpResult.getData();
            }
        });
    }


    /**
     * @return 线程安全, 双层校验
     */
    public static <T> ErrorTransformer<T> getInstance() {

        if (errorTransformer == null) {
            synchronized (ErrorTransformer.class) {
                if (errorTransformer == null) {
                    errorTransformer = new ErrorTransformer<>();
                }
            }
        }
        return errorTransformer;
    }
}
