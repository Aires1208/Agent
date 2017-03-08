smartsight agent部署

硬件要求
  由于smartsight agent与目标应用在同一进程内运行，并且smartsight agent耗费资源(cpu、内存、网络)都比较小，所以不需要再考虑硬件要求。

系统要求
  - Linux

软件要求
  - JDK6+

静态部署方法

1.访问版本发布地址：ftp://apm:apm@10.62.100.76/smartsight/，下载smartsight agent版本SMARTSIGHT_AGENT_[version]_[sprint]_[datetime].zip；

2.获取目标应用所运行的操作系统的(s)ftp登录信息，通过(s)ftp方式登录应用所在系统;

3.上传smartsight agent版本包到目标应用所在的操作系统的/home/目录下;

![](img/smartsight-deploy/2016-10-18-15_14_57.png)

4.解压缩smartsight agent版本包，解压缩后的文件夹名称为/home/smartsight-agent/；

![](img/smartsight-deploy/2016-10-18-15_17_49.png)

5.进入目标应用的目录，此处以名为test_app的应用为例，修改应用的启动脚本，在启动脚本中加入smartsight agent相关的启动参数；

![](img/smartsight-deploy/2016-10-17-16_52_41.png)

其中以下几个参数需要自定义：

	- Dsmartsight.agentId: 当前agent的id，必须是全局唯一的，不能与其它的agent实例的agentId重复
	
	- Dsmartsight.applicationName: 当前应用的名称
	
	- DcollectorIp: smartsight collector应用实例的ip地址
	
	- DlogstashIp: smartsight logstash应用实例的ip地址

6.正常启动目标应用，在这个例子里为运行上一步修改过的run.sh脚本


动态部署方法

smartsight agent也提供了动态部署的方式，用于不方便重启目标应用或者修改目标应用的启动脚本的情况下，为目标应用注入监控代理。这种方式注入的smartsight agent在目标应用停止或者重启后将会失效，如需使用smartsight agent监控目标应用，仍然需要再次部署。

1.获取smartsight agent版本以及解压缩的步骤，参加上一节"静态部署方法"中的步骤1-4；

2.获取正在运行的目标应用的进程号(pid)，运行命令："ps -l"；

![](img/smartsight-deploy/2016-11-02_08_50_49.png)

3.为目标应用注入smartsight agent，运行命令："java -Xbootclasspath/a:&lt;JAVA_HOME&gt;/lib/tools.jar -Dsmartsight.agentId=&lt;agentId&gt; -Dsmartsight.applicationName=&lt;appName&gt; -DcollectorIp=&lt;collectorIp&gt; -DlogstashIp=&lt;logstashIp&gt; -jar &lt;SMARTSIGHT_AGENT_HOME&gt;/smartsight-agent/smartsight-bootstrap.jar &lt;pid&gt;"；

![](img/smartsight-deploy/2016-11-02_09_04_50.png)




