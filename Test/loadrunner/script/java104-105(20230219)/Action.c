Action()
{
//	1、访问http://127.0.0.1:1080/WebTours/首页
//	2、输入登陆的账号和密码
	lr_start_transaction("index_trans");

	
	web_url("index",
		"URL=http://127.0.0.1:1080/WebTours/",
		"TargetFrame=",
		"Resource=0",
		"Referer=",
		LAST);
//	插入登陆的集合点
	lr_rendezvous("login_rendezvous");

	lr_start_transaction("login_trans");
//插入登陆成功后的检查点
	web_reg_find("Text=to the Web Tours reservation pages",
		LAST);

	web_submit_form("login",
		ITEMDATA,
		"Name=username", "Value={username}", ENDITEM,
		"Name=password", "Value=bean", ENDITEM,
		LAST);
	lr_end_transaction("login_trans", LR_AUTO);

	lr_end_transaction("index_trans", LR_AUTO);

	
	
	return 0;
}
