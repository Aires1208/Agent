/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.navercorp.pinpoint.thrift.dto;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2017-2-16")
public class TProcessInfo implements org.apache.thrift.TBase<TProcessInfo, TProcessInfo._Fields>, java.io.Serializable, Cloneable, Comparable<TProcessInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TProcessInfo");

  private static final org.apache.thrift.protocol.TField P_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("pID", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField PROCESS_FIELD_DESC = new org.apache.thrift.protocol.TField("process", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField COMMAND_FIELD_DESC = new org.apache.thrift.protocol.TField("command", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField CPU_USAGE_FIELD_DESC = new org.apache.thrift.protocol.TField("cpuUsage", org.apache.thrift.protocol.TType.DOUBLE, (short)4);
  private static final org.apache.thrift.protocol.TField VIRT_FIELD_DESC = new org.apache.thrift.protocol.TField("virt", org.apache.thrift.protocol.TType.I64, (short)5);
  private static final org.apache.thrift.protocol.TField CPU_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("cpuTime", org.apache.thrift.protocol.TType.I64, (short)6);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TProcessInfoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TProcessInfoTupleSchemeFactory());
  }

  private String pID; // optional
  private String process; // optional
  private String command; // optional
  private double cpuUsage; // optional
  private long virt; // optional
  private long cpuTime; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    P_ID((short)1, "pID"),
    PROCESS((short)2, "process"),
    COMMAND((short)3, "command"),
    CPU_USAGE((short)4, "cpuUsage"),
    VIRT((short)5, "virt"),
    CPU_TIME((short)6, "cpuTime");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // P_ID
          return P_ID;
        case 2: // PROCESS
          return PROCESS;
        case 3: // COMMAND
          return COMMAND;
        case 4: // CPU_USAGE
          return CPU_USAGE;
        case 5: // VIRT
          return VIRT;
        case 6: // CPU_TIME
          return CPU_TIME;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __CPUUSAGE_ISSET_ID = 0;
  private static final int __VIRT_ISSET_ID = 1;
  private static final int __CPUTIME_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.P_ID,_Fields.PROCESS,_Fields.COMMAND,_Fields.CPU_USAGE,_Fields.VIRT,_Fields.CPU_TIME};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.P_ID, new org.apache.thrift.meta_data.FieldMetaData("pID", org.apache.thrift.TFieldRequirementType.OPTIONAL,
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PROCESS, new org.apache.thrift.meta_data.FieldMetaData("process", org.apache.thrift.TFieldRequirementType.OPTIONAL,
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.COMMAND, new org.apache.thrift.meta_data.FieldMetaData("command", org.apache.thrift.TFieldRequirementType.OPTIONAL,
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CPU_USAGE, new org.apache.thrift.meta_data.FieldMetaData("cpuUsage", org.apache.thrift.TFieldRequirementType.OPTIONAL,
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.VIRT, new org.apache.thrift.meta_data.FieldMetaData("virt", org.apache.thrift.TFieldRequirementType.OPTIONAL,
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.CPU_TIME, new org.apache.thrift.meta_data.FieldMetaData("cpuTime", org.apache.thrift.TFieldRequirementType.OPTIONAL,
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TProcessInfo.class, metaDataMap);
  }

  public TProcessInfo() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TProcessInfo(TProcessInfo other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetPID()) {
      this.pID = other.pID;
    }
    if (other.isSetProcess()) {
      this.process = other.process;
    }
    if (other.isSetCommand()) {
      this.command = other.command;
    }
    this.cpuUsage = other.cpuUsage;
    this.virt = other.virt;
    this.cpuTime = other.cpuTime;
  }

  public TProcessInfo deepCopy() {
    return new TProcessInfo(this);
  }

  @Override
  public void clear() {
    this.pID = null;
    this.process = null;
    this.command = null;
    setCpuUsageIsSet(false);
    this.cpuUsage = 0.0;
    setVirtIsSet(false);
    this.virt = 0;
    setCpuTimeIsSet(false);
    this.cpuTime = 0;
  }

  public String getPID() {
    return this.pID;
  }

  public void setPID(String pID) {
    this.pID = pID;
  }

  public void unsetPID() {
    this.pID = null;
  }

  /** Returns true if field pID is set (has been assigned a value) and false otherwise */
  public boolean isSetPID() {
    return this.pID != null;
  }

  public void setPIDIsSet(boolean value) {
    if (!value) {
      this.pID = null;
    }
  }

  public String getProcess() {
    return this.process;
  }

  public void setProcess(String process) {
    this.process = process;
  }

  public void unsetProcess() {
    this.process = null;
  }

  /** Returns true if field process is set (has been assigned a value) and false otherwise */
  public boolean isSetProcess() {
    return this.process != null;
  }

  public void setProcessIsSet(boolean value) {
    if (!value) {
      this.process = null;
    }
  }

  public String getCommand() {
    return this.command;
  }

  public void setCommand(String command) {
    this.command = command;
  }

  public void unsetCommand() {
    this.command = null;
  }

  /** Returns true if field command is set (has been assigned a value) and false otherwise */
  public boolean isSetCommand() {
    return this.command != null;
  }

  public void setCommandIsSet(boolean value) {
    if (!value) {
      this.command = null;
    }
  }

  public double getCpuUsage() {
    return this.cpuUsage;
  }

  public void setCpuUsage(double cpuUsage) {
    this.cpuUsage = cpuUsage;
    setCpuUsageIsSet(true);
  }

  public void unsetCpuUsage() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __CPUUSAGE_ISSET_ID);
  }

  /** Returns true if field cpuUsage is set (has been assigned a value) and false otherwise */
  public boolean isSetCpuUsage() {
    return EncodingUtils.testBit(__isset_bitfield, __CPUUSAGE_ISSET_ID);
  }

  public void setCpuUsageIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __CPUUSAGE_ISSET_ID, value);
  }

  public long getVirt() {
    return this.virt;
  }

  public void setVirt(long virt) {
    this.virt = virt;
    setVirtIsSet(true);
  }

  public void unsetVirt() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __VIRT_ISSET_ID);
  }

  /** Returns true if field virt is set (has been assigned a value) and false otherwise */
  public boolean isSetVirt() {
    return EncodingUtils.testBit(__isset_bitfield, __VIRT_ISSET_ID);
  }

  public void setVirtIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __VIRT_ISSET_ID, value);
  }

  public long getCpuTime() {
    return this.cpuTime;
  }

  public void setCpuTime(long cpuTime) {
    this.cpuTime = cpuTime;
    setCpuTimeIsSet(true);
  }

  public void unsetCpuTime() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __CPUTIME_ISSET_ID);
  }

  /** Returns true if field cpuTime is set (has been assigned a value) and false otherwise */
  public boolean isSetCpuTime() {
    return EncodingUtils.testBit(__isset_bitfield, __CPUTIME_ISSET_ID);
  }

  public void setCpuTimeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __CPUTIME_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
      case P_ID:
        if (value == null) {
          unsetPID();
        } else {
          setPID((String)value);
        }
        break;

      case PROCESS:
        if (value == null) {
          unsetProcess();
        } else {
          setProcess((String)value);
        }
        break;

      case COMMAND:
        if (value == null) {
          unsetCommand();
        } else {
          setCommand((String)value);
        }
        break;

      case CPU_USAGE:
        if (value == null) {
          unsetCpuUsage();
        } else {
          setCpuUsage((Double)value);
        }
        break;

      case VIRT:
        if (value == null) {
          unsetVirt();
        } else {
          setVirt((Long)value);
        }
        break;

      case CPU_TIME:
        if (value == null) {
          unsetCpuTime();
        } else {
          setCpuTime((Long)value);
        }
        break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
      case P_ID:
        return getPID();

      case PROCESS:
        return getProcess();

      case COMMAND:
        return getCommand();

      case CPU_USAGE:
        return Double.valueOf(getCpuUsage());

      case VIRT:
        return Long.valueOf(getVirt());

      case CPU_TIME:
        return Long.valueOf(getCpuTime());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
      case P_ID:
        return isSetPID();
      case PROCESS:
        return isSetProcess();
      case COMMAND:
        return isSetCommand();
      case CPU_USAGE:
        return isSetCpuUsage();
      case VIRT:
        return isSetVirt();
      case CPU_TIME:
        return isSetCpuTime();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TProcessInfo)
      return this.equals((TProcessInfo)that);
    return false;
  }

  public boolean equals(TProcessInfo that) {
    if (that == null)
      return false;

    boolean this_present_pID = true && this.isSetPID();
    boolean that_present_pID = true && that.isSetPID();
    if (this_present_pID || that_present_pID) {
      if (!(this_present_pID && that_present_pID))
        return false;
      if (!this.pID.equals(that.pID))
        return false;
    }

    boolean this_present_process = true && this.isSetProcess();
    boolean that_present_process = true && that.isSetProcess();
    if (this_present_process || that_present_process) {
      if (!(this_present_process && that_present_process))
        return false;
      if (!this.process.equals(that.process))
        return false;
    }

    boolean this_present_command = true && this.isSetCommand();
    boolean that_present_command = true && that.isSetCommand();
    if (this_present_command || that_present_command) {
      if (!(this_present_command && that_present_command))
        return false;
      if (!this.command.equals(that.command))
        return false;
    }

    boolean this_present_cpuUsage = true && this.isSetCpuUsage();
    boolean that_present_cpuUsage = true && that.isSetCpuUsage();
    if (this_present_cpuUsage || that_present_cpuUsage) {
      if (!(this_present_cpuUsage && that_present_cpuUsage))
        return false;
      if (this.cpuUsage != that.cpuUsage)
        return false;
    }

    boolean this_present_virt = true && this.isSetVirt();
    boolean that_present_virt = true && that.isSetVirt();
    if (this_present_virt || that_present_virt) {
      if (!(this_present_virt && that_present_virt))
        return false;
      if (this.virt != that.virt)
        return false;
    }

    boolean this_present_cpuTime = true && this.isSetCpuTime();
    boolean that_present_cpuTime = true && that.isSetCpuTime();
    if (this_present_cpuTime || that_present_cpuTime) {
      if (!(this_present_cpuTime && that_present_cpuTime))
        return false;
      if (this.cpuTime != that.cpuTime)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_pID = true && (isSetPID());
    list.add(present_pID);
    if (present_pID)
      list.add(pID);

    boolean present_process = true && (isSetProcess());
    list.add(present_process);
    if (present_process)
      list.add(process);

    boolean present_command = true && (isSetCommand());
    list.add(present_command);
    if (present_command)
      list.add(command);

    boolean present_cpuUsage = true && (isSetCpuUsage());
    list.add(present_cpuUsage);
    if (present_cpuUsage)
      list.add(cpuUsage);

    boolean present_virt = true && (isSetVirt());
    list.add(present_virt);
    if (present_virt)
      list.add(virt);

    boolean present_cpuTime = true && (isSetCpuTime());
    list.add(present_cpuTime);
    if (present_cpuTime)
      list.add(cpuTime);

    return list.hashCode();
  }

  @Override
  public int compareTo(TProcessInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetPID()).compareTo(other.isSetPID());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPID()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.pID, other.pID);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetProcess()).compareTo(other.isSetProcess());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetProcess()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.process, other.process);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCommand()).compareTo(other.isSetCommand());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCommand()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.command, other.command);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCpuUsage()).compareTo(other.isSetCpuUsage());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCpuUsage()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.cpuUsage, other.cpuUsage);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetVirt()).compareTo(other.isSetVirt());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetVirt()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.virt, other.virt);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCpuTime()).compareTo(other.isSetCpuTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCpuTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.cpuTime, other.cpuTime);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("TProcessInfo(");
    boolean first = true;

    if (isSetPID()) {
      sb.append("pID:");
      if (this.pID == null) {
        sb.append("null");
      } else {
        sb.append(this.pID);
      }
      first = false;
    }
    if (isSetProcess()) {
      if (!first) sb.append(", ");
      sb.append("process:");
      if (this.process == null) {
        sb.append("null");
      } else {
        sb.append(this.process);
      }
      first = false;
    }
    if (isSetCommand()) {
      if (!first) sb.append(", ");
      sb.append("command:");
      if (this.command == null) {
        sb.append("null");
      } else {
        sb.append(this.command);
      }
      first = false;
    }
    if (isSetCpuUsage()) {
      if (!first) sb.append(", ");
      sb.append("cpuUsage:");
      sb.append(this.cpuUsage);
      first = false;
    }
    if (isSetVirt()) {
      if (!first) sb.append(", ");
      sb.append("virt:");
      sb.append(this.virt);
      first = false;
    }
    if (isSetCpuTime()) {
      if (!first) sb.append(", ");
      sb.append("cpuTime:");
      sb.append(this.cpuTime);
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TProcessInfoStandardSchemeFactory implements SchemeFactory {
    public TProcessInfoStandardScheme getScheme() {
      return new TProcessInfoStandardScheme();
    }
  }

  private static class TProcessInfoStandardScheme extends StandardScheme<TProcessInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TProcessInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
          break;
        }
        switch (schemeField.id) {
          case 1: // P_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.pID = iprot.readString();
              struct.setPIDIsSet(true);
            } else {
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // PROCESS
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.process = iprot.readString();
              struct.setProcessIsSet(true);
            } else {
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // COMMAND
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.command = iprot.readString();
              struct.setCommandIsSet(true);
            } else {
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // CPU_USAGE
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.cpuUsage = iprot.readDouble();
              struct.setCpuUsageIsSet(true);
            } else {
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // VIRT
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.virt = iprot.readI64();
              struct.setVirtIsSet(true);
            } else {
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // CPU_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.cpuTime = iprot.readI64();
              struct.setCpuTimeIsSet(true);
            } else {
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TProcessInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.pID != null) {
        if (struct.isSetPID()) {
          oprot.writeFieldBegin(P_ID_FIELD_DESC);
          oprot.writeString(struct.pID);
          oprot.writeFieldEnd();
        }
      }
      if (struct.process != null) {
        if (struct.isSetProcess()) {
          oprot.writeFieldBegin(PROCESS_FIELD_DESC);
          oprot.writeString(struct.process);
          oprot.writeFieldEnd();
        }
      }
      if (struct.command != null) {
        if (struct.isSetCommand()) {
          oprot.writeFieldBegin(COMMAND_FIELD_DESC);
          oprot.writeString(struct.command);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetCpuUsage()) {
        oprot.writeFieldBegin(CPU_USAGE_FIELD_DESC);
        oprot.writeDouble(struct.cpuUsage);
        oprot.writeFieldEnd();
      }
      if (struct.isSetVirt()) {
        oprot.writeFieldBegin(VIRT_FIELD_DESC);
        oprot.writeI64(struct.virt);
        oprot.writeFieldEnd();
      }
      if (struct.isSetCpuTime()) {
        oprot.writeFieldBegin(CPU_TIME_FIELD_DESC);
        oprot.writeI64(struct.cpuTime);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TProcessInfoTupleSchemeFactory implements SchemeFactory {
    public TProcessInfoTupleScheme getScheme() {
      return new TProcessInfoTupleScheme();
    }
  }

  private static class TProcessInfoTupleScheme extends TupleScheme<TProcessInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TProcessInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetPID()) {
        optionals.set(0);
      }
      if (struct.isSetProcess()) {
        optionals.set(1);
      }
      if (struct.isSetCommand()) {
        optionals.set(2);
      }
      if (struct.isSetCpuUsage()) {
        optionals.set(3);
      }
      if (struct.isSetVirt()) {
        optionals.set(4);
      }
      if (struct.isSetCpuTime()) {
        optionals.set(5);
      }
      oprot.writeBitSet(optionals, 6);
      if (struct.isSetPID()) {
        oprot.writeString(struct.pID);
      }
      if (struct.isSetProcess()) {
        oprot.writeString(struct.process);
      }
      if (struct.isSetCommand()) {
        oprot.writeString(struct.command);
      }
      if (struct.isSetCpuUsage()) {
        oprot.writeDouble(struct.cpuUsage);
      }
      if (struct.isSetVirt()) {
        oprot.writeI64(struct.virt);
      }
      if (struct.isSetCpuTime()) {
        oprot.writeI64(struct.cpuTime);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TProcessInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(6);
      if (incoming.get(0)) {
        struct.pID = iprot.readString();
        struct.setPIDIsSet(true);
      }
      if (incoming.get(1)) {
        struct.process = iprot.readString();
        struct.setProcessIsSet(true);
      }
      if (incoming.get(2)) {
        struct.command = iprot.readString();
        struct.setCommandIsSet(true);
      }
      if (incoming.get(3)) {
        struct.cpuUsage = iprot.readDouble();
        struct.setCpuUsageIsSet(true);
      }
      if (incoming.get(4)) {
        struct.virt = iprot.readI64();
        struct.setVirtIsSet(true);
      }
      if (incoming.get(5)) {
        struct.cpuTime = iprot.readI64();
        struct.setCpuTimeIsSet(true);
      }
    }
  }

}

