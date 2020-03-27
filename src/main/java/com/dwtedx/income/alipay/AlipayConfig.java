package com.dwtedx.income.alipay;

public class AlipayConfig {
	// 商户ID
	public static String appid = "2021001109682133";
    // 私钥
	public static String rsa_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDIqnqoRh/96pZ8O/mrizO5ngaeqNQP1+CrRUoAE6dXFbGrEqEYCxEgE5TzeTPK/xNzHJrUSeM6pR1TCh84NPORmzxViNnCRDVu7vTJbuvGREh9mYeVKb7hdoBpXCx3zu5ZM6sJQLDfrIjjNDgYRx5YGSFtoDbz6iytTrhovmI5j5JQvGAuljwS9SJv/7X2S6jXInJcHK9/l3+8RmyNq/b2v3lJrH9Ih1CmJxGihFwFoRDU5No9VcSdvQFRIt4DjmnEftaKE5oFPXcQi4JCDk/Zo+XjNutgHcGkmWHEagFmCSDYl1F5q3mqUrp4/lsSGdwOtO2eviQQStDTD7aEm1JtAgMBAAECggEBAISag6uni4ST/RXj3D1MI8ZJcz5tepVtpv4HL7uVQUP/BgVyrhnhVlPIHOo38c33ebbb9ETM/ufGLPau/ap682oJF3m+kYga4uSH7CMgtZWzkePODRa5G3aKhJkbXBdgG4m+5wuaQEfPEDZ0T41bkeXN1ZVIBohw+00bdCljhi1aUTzsCRqfq113WOJafUV1Jk9rcwnH51Grj5qvcg6bcAEaCRtvEgSVLN90xVnwBf4ZqfwH857WVFnDYm+gVdmwJq/W7tujhj8jorIWLXOcVxs40jvhpoZKudwkGOjP7qZ5CCgbCgvxYOad9M+OCt5eXfMhhd6nX/rOOFtS/Iv8L9ECgYEA/0lYWxyq3Xeb7lBgGd7er/7r9QWN8Kg27fxXLyvciDuvzW/woKUuB1DO036f+FfQDhyOdWFMXCQ8+ebHiTRmIEpXGLMZfybha6psbeZy5DoyIfO/Uz5XCLRg+k1X1aa7N7gAthUn8xPYImUkY6twhUFhIxqKLxsetVLtg9IxzzMCgYEAyToNtH9FZXsOu57h40MVOnR4NQCFbEnrEmL+RfoAbq4jo05fIIOTQR/+Ju3JAmR/IP+vdxi+mfZSTJTsnlsG5g09Ga8NAm2QT9b+2svcbU3e+JyFylp39h0WEOWavsdeeE/t9gdDTVIU8weEBxMwUvyGaIIvkSolliLJ4uD8198CgYEAnxOPMUdN2a9cxUXkfHp6fWT2l7ocVqgbgnTJxyUnyOSYigWFBjWn1vstxnCTMmdVyFVXGCHHOWuDa525HPA/60MnvsZrzuXldfe+lUUSttDkyG9g+3hPN6de/toPL50z8GaCWvQX7sqg1d38rPb5qK+y3fL9xJi7q/W+93OVgi0CgYAVZEWGtazz79QOIALVfREpqK4KvGZyr5z81GdBmyUmnv6M4sz8EOC7oKINE2Q2C+bz5BqMJ6ZDicsP9xj8cKLYG/ot280fP/SCeFGZguiI+ilE9qbGbz2nfUoWO8Jqm5kwYxbh+72s35f4igkAE4skl1HWlFhoLznVrxcVNmPKswKBgQDk2p0wv7IqeOGiX9x/5PMgoV2yUwpkomnXYennsFDu6Ok9R62KRjfWhId2lIlhL1GiXkuWG5anRMo5+jH6s8pmbadLV+bnU8Ma2d0oOXtLr0atn9wTtuZym7jYStd0FV27OYa/RRtrHdxQU8ij+u97kbFrk3SfKXn8jVfeg6qQtA==";
    // 异步回调地址
	public static String notify_url = "https://diapp.dwtedx.com/vipinfo/ordernotify";
    // 同步回调地址
	public static String return_url;
    // 请求网关地址
	public static String gateway_url;
    // 编码
	public static String charset = "UTF-8";
    // 返回格式
	public static String format = "json";
    // 支付宝公钥
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiuLohug0/zSI+Z3v3dcBEgiPVUk/b7w8SNr/MNe5VNePh2OCJjM6mMSb7AaPdq7Oxk+sCiBrvGXImve8ffJrSK2k6aiEQ245eB/X5+WnQjhKh4/8epyACb45BD+y6z3U4OYg/DSqSPd88MvYuR1J6stdwLMQHSFuEAvdlYKC77HLabStMEPPszfboSv76ewT0PZzbsyhGUPdIXr06cv1DXIh/Adv4DRX9wbCtWuUXX1Z9cYP6TZ769n2BDV9GbIk43c/Uw1wXjInzY2BAhmCiCvx649jC5Kfie6tVAmeMGXoFY+KjwXZGPmIvmWjVxeic7oGqhqNvVae5+VyTscBwQIDAQAB";
    // RSA2
	public static String signtype = "RSA2";
	
}
