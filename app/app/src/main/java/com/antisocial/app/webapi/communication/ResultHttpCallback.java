package com.antisocial.app.webapi.communication;

import com.dg.libs.rest.callbacks.HttpCallback;
import com.dg.libs.rest.domain.ResponseStatus;

public class ResultHttpCallback<T> implements HttpCallback<T> {
	
	private T result;
	
	private ResponseStatus status;
	
	public ResponseStatus getStatus() {
		return status;
	}

	public T getResult() {
		return result;
	}

    @Override
    public void onSuccess(T responseData, ResponseStatus responseStatus) {
        this.result = responseData;
        this.status = responseStatus;
    }

    @Override
	public void onHttpError(ResponseStatus status) {
		this.status = status;
	}
}
