Action()
{
//	1������http://127.0.0.1:1080/WebTours/��ҳ
//	2�������½���˺ź�����
	lr_start_transaction("index_trans");

	
	web_url("index",
		"URL=http://127.0.0.1:1080/WebTours/",
		"TargetFrame=",
		"Resource=0",
		"Referer=",
		LAST);
//	�����½�ļ��ϵ�
	lr_rendezvous("login_rendezvous");

	lr_start_transaction("login_trans");
//�����½�ɹ���ļ���
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
