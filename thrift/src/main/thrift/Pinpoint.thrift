namespace java com.navercorp.pinpoint.thrift.dto

enum TJvmGcType {
    UNKNOWN,
    SERIAL,
    PARALLEL,
    CMS,
    G1
}

struct TServiceInfo {
    1: optional string          serviceName
    2: optional list<string>    serviceLibs
}

struct TServerMetaData {
    1: optional string              serverInfo
    2: optional list<string>        vmArgs
    10: optional list<TServiceInfo>  serviceInfos
}

struct TJvmInfo {
    1:          i16         version = 0
    2: optional string      vmVersion
    3: optional TJvmGcType  gcType = TJvmGcType.UNKNOWN
}

struct TAgentInfo {
	1: string	hostname
	2: string	ip
	3: string	ports
	4: string	agentId
	5: string	applicationName
	6: i16	    serviceType
	7: i32      pid
	8: string   agentVersion;
	9: string   vmVersion;

	10: i64	    startTimestamp

	11: optional i64     endTimestamp
	12: optional i32     endStatus
	
	20: optional TServerMetaData   serverMetaData

	30: optional TJvmInfo   jvmInfo

	40: optional string  mac

	50: optional string os

	60: optional bool isDocker
}

struct TJvmGc {
    1: TJvmGcType   type = TJvmGcType.UNKNOWN
    2: i64          jvmMemoryHeapUsed
    3: i64          jvmMemoryHeapMax
    4: i64          jvmMemoryNonHeapUsed
    5: i64          jvmMemoryNonHeapMax
    6: i64          jvmGcOldCount
    7: i64          jvmGcOldTime
    8: optional TJvmGcDetailed    jvmGcDetailed

}

struct TJvmGcDetailed {
    1: optional i64 jvmGcNewCount
    2: optional i64 jvmGcNewTime
    3: optional double jvmPoolCodeCacheUsed
    4: optional double jvmPoolNewGenUsed
    5: optional double jvmPoolOldGenUsed
    6: optional double jvmPoolSurvivorSpaceUsed
    7: optional double jvmPoolPermGenUsed
    8: optional double jvmPoolMetaspaceUsed
}

struct TCpuLoad {
    1: optional double       jvmCpuLoad
    2: optional double       systemCpuLoad
}

struct TIOLoad {
    1: optional i64      total
    2: optional i64      free
    3: optional i64      used
    4: optional double   usage
    5: optional i64      reads
    6: optional i64      writes
}

struct TNetLoad {
    11: optional double inSpeed
    12: optional double outSpeed
    13: optional i64 speed
}

struct TMemLoad {
    1: optional i64 total
    2: optional i64 free
    3: optional i64 used
}

struct TMemInfo {
    1: optional i64 memTotal
    2: optional i64 memFree
    3: optional i64 memUsed
    4: optional i64 swapTotal
    5: optional i64 swapFree
    6: optional i64 swapUsed
    7: optional i64 vmTotal
    8: optional i64 vmFree
    9: optional i64 vmUsed
}


struct TDeviceInfo {
    1: optional string deviceName
    2: optional double tps
    3: optional double readPerSecond
    4: optional double writePerSecond
    5: optional i64 read
    6: optional i64 write
}

struct TDevices {
    1: list<TDeviceInfo>        tDevices
}

struct TFileSystemInfo {
    1: optional string fileSystem
    2: optional string mountedOn
    3: optional i64 total
    4: optional i64 used
    5: optional i64 free
}

struct TFileSystems {
    1: list<TFileSystemInfo>        tFileSystemInfos
}

struct TNetInfoDynamic {
    1: optional string name
    2: optional i64 receiveBytes
    3: optional i64 transmitBytes
    4: optional i64 receiveErrors
    5: optional i64 transmitErrors
    6: optional i64 colls
}


struct TNetInfoStatic {
    1: optional string name
    2: optional string v4Address
    3: optional string macAddress
    4: optional i64 mtu
}

struct TNets {
    1: list<TNetInfoStatic> tNetInfoStatics
    2: list<TNetInfoDynamic> tNetInfoDynamics
}


struct TCpuInfoStatic {
    1: optional string processor
    2: optional string vendor
    3: optional string cpuFamily
    4: optional string model
    5: optional string modelName
    6: optional string cpuMHz
    7: optional string cacheSize
}


struct TCpuInfoDynamic {
    1: optional string processor
    2: optional i64 user
    3: optional i64 nice
    4: optional i64 system
    5: optional i64 idle
    6: optional i64 iowait
    7: optional i64 irq
    8: optional i64 softirq

}

struct TCpus {
    1: list<TCpuInfoStatic> tCpuInfoStatics
    2: list<TCpuInfoDynamic> tCpuInfoDynamics
}


struct TProcessInfo {
    1: optional string pID
    2: optional string process
    3: optional string command
    4: optional double cpuUsage
    5: optional i64 virt
    6: optional i64 cpuTime
}


struct TProcesses {
    1: list<TProcessInfo> tProcessesCpuUsage
    2: list<TProcessInfo> tProcessesCpuTime
    3: list<TProcessInfo> tProcessesVirt
}


struct TTransaction {
    2: optional i64     sampledNewCount
    3: optional i64     sampledContinuationCount
    4: optional i64     unsampledNewCount
    5: optional i64     unsampledContinuationCount
}

struct TActiveTraceHistogram {
    1:          i16         version = 0
	2: optional i32         histogramSchemaType
	3: optional list<i32>   activeTraceCount
}

struct TActiveTrace {
	1: optional TActiveTraceHistogram   histogram
}

struct TAgentStat {
    1: optional string      agentId
    2: optional i64         startTimestamp
    3: optional i64         timestamp
    4: optional i64         collectInterval
    10: optional TJvmGc     gc
    20: optional TCpuLoad   cpuLoad
    21: optional TIOLoad    IOLoad
    22: optional TNetLoad   netLoad
    23: optional TMemLoad   memLoad
    24: optional TMemInfo   memInfo
    25: optional TDevices   devices
    26: optional TFileSystems   fileSystems
    27: optional TNets   nets
    28: optional TCpus   cpus
    29: optional TProcesses   processes
    30: optional TTransaction   transaction
    40: optional TActiveTrace   activeTrace
    200: optional string    metadata    
}

struct TAgentStatBatch {
    1: string                   agentId
    2: i64                      startTimestamp
    10: list<TAgentStat>        agentStats
}
