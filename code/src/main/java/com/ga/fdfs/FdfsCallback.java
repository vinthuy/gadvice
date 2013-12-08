package com.ga.fdfs;

public interface FdfsCallback<T> {
	T doInRedis(FDFSConnection connection) throws FdfsException;
}