package com.soul.learn.power.net;

public interface Params {

	public static final String HEADER_ACCEPT_JSON = "Accept: application/json";
	public static final String HEADER_ENCODE_GZIP = "Accept-Encoding: gzip";

	public static final String STORE_CODE = "storeCode";


	/**分类*/
	public static final String CATGNAME = "catgName";
	public static final String SUBCATGNAME = "subCatgName";
	public static final String SORTTYPE = "sortType";

	/**分页*/
	public static final String APP_PAGE = "page";
	public static final String APP_SIZE = "size";
	public static final String APP_SIZE_BY_EACH_PAGE = "8";
}
