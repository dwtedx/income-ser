<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>JVM memory</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<meta http-equiv="Cache-Control" content="no-siteapp" />
<meta name="applicable-device" content="pc,mobile">

<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="author" content="dwtedx">
<meta content="index,follow" name="robots" />
<meta content="Copyright 2012-2020. shinyuu . All Rights Reserved." name="copyright" />
<link rev="made" href="mailto:dwtedx@126.com">
<link rev="made" href="http://dwtedx.com">
</head>
<body>
	<%
		double total = (Runtime.getRuntime().totalMemory()) / (1024.0 * 1024);
		double max = (Runtime.getRuntime().maxMemory()) / (1024.0 * 1024);
		double free = (Runtime.getRuntime().freeMemory()) / (1024.0 * 1024);
		out.println("Java 虚拟机试图使用的最大内存量(当前JVM的最大可用内存)maxMemory(): " + max + "MB<br/>");
		out.println("Java 虚拟机中的内存总量(当前JVM占用的内存总数)totalMemory(): " + total + "MB<br/>");
		out.println("Java 虚拟机中的空闲内存量(当前JVM空闲内存)freeMemory(): " + free + "MB<br/>");
		out.println("因为JVM只有在需要内存时才占用物理内存使用，所以freeMemory()的值一般情况下都很小，<br/>"
				+ "而JVM实际可用内存并不等于freeMemory()，而应该等于 maxMemory()-totalMemory()+freeMemory()。<br/>");
		out.println("JVM实际可用内存: " + (max - total + free) + "MB<br/>");
		out.println("jspcn");
	%>
</body>
</html>