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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2017-2-10")
public class TFileSystemInfo implements org.apache.thrift.TBase<TFileSystemInfo, TFileSystemInfo._Fields>, java.io.Serializable, Cloneable, Comparable<TFileSystemInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TFileSystemInfo");

  private static final org.apache.thrift.protocol.TField FILE_SYSTEM_FIELD_DESC = new org.apache.thrift.protocol.TField("fileSystem", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField MOUNTED_ON_FIELD_DESC = new org.apache.thrift.protocol.TField("mountedOn", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField TOTAL_FIELD_DESC = new org.apache.thrift.protocol.TField("total", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField USED_FIELD_DESC = new org.apache.thrift.protocol.TField("used", org.apache.thrift.protocol.TType.I64, (short)4);
  private static final org.apache.thrift.protocol.TField FREE_FIELD_DESC = new org.apache.thrift.protocol.TField("free", org.apache.thrift.protocol.TType.I64, (short)5);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TFileSystemInfoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TFileSystemInfoTupleSchemeFactory());
  }

  private String fileSystem; // optional
  private String mountedOn; // optional
  private long total; // optional
  private long used; // optional
  private long free; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    FILE_SYSTEM((short)1, "fileSystem"),
    MOUNTED_ON((short)2, "mountedOn"),
    TOTAL((short)3, "total"),
    USED((short)4, "used"),
    FREE((short)5, "free");

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
        case 1: // FILE_SYSTEM
          return FILE_SYSTEM;
        case 2: // MOUNTED_ON
          return MOUNTED_ON;
        case 3: // TOTAL
          return TOTAL;
        case 4: // USED
          return USED;
        case 5: // FREE
          return FREE;
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
  private static final int __TOTAL_ISSET_ID = 0;
  private static final int __USED_ISSET_ID = 1;
  private static final int __FREE_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.FILE_SYSTEM,_Fields.MOUNTED_ON,_Fields.TOTAL,_Fields.USED,_Fields.FREE};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.FILE_SYSTEM, new org.apache.thrift.meta_data.FieldMetaData("fileSystem", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.MOUNTED_ON, new org.apache.thrift.meta_data.FieldMetaData("mountedOn", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TOTAL, new org.apache.thrift.meta_data.FieldMetaData("total", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.USED, new org.apache.thrift.meta_data.FieldMetaData("used", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.FREE, new org.apache.thrift.meta_data.FieldMetaData("free", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TFileSystemInfo.class, metaDataMap);
  }

  public TFileSystemInfo() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TFileSystemInfo(TFileSystemInfo other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetFileSystem()) {
      this.fileSystem = other.fileSystem;
    }
    if (other.isSetMountedOn()) {
      this.mountedOn = other.mountedOn;
    }
    this.total = other.total;
    this.used = other.used;
    this.free = other.free;
  }

  public TFileSystemInfo deepCopy() {
    return new TFileSystemInfo(this);
  }

  @Override
  public void clear() {
    this.fileSystem = null;
    this.mountedOn = null;
    setTotalIsSet(false);
    this.total = 0;
    setUsedIsSet(false);
    this.used = 0;
    setFreeIsSet(false);
    this.free = 0;
  }

  public String getFileSystem() {
    return this.fileSystem;
  }

  public void setFileSystem(String fileSystem) {
    this.fileSystem = fileSystem;
  }

  public void unsetFileSystem() {
    this.fileSystem = null;
  }

  /** Returns true if field fileSystem is set (has been assigned a value) and false otherwise */
  public boolean isSetFileSystem() {
    return this.fileSystem != null;
  }

  public void setFileSystemIsSet(boolean value) {
    if (!value) {
      this.fileSystem = null;
    }
  }

  public String getMountedOn() {
    return this.mountedOn;
  }

  public void setMountedOn(String mountedOn) {
    this.mountedOn = mountedOn;
  }

  public void unsetMountedOn() {
    this.mountedOn = null;
  }

  /** Returns true if field mountedOn is set (has been assigned a value) and false otherwise */
  public boolean isSetMountedOn() {
    return this.mountedOn != null;
  }

  public void setMountedOnIsSet(boolean value) {
    if (!value) {
      this.mountedOn = null;
    }
  }

  public long getTotal() {
    return this.total;
  }

  public void setTotal(long total) {
    this.total = total;
    setTotalIsSet(true);
  }

  public void unsetTotal() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TOTAL_ISSET_ID);
  }

  /** Returns true if field total is set (has been assigned a value) and false otherwise */
  public boolean isSetTotal() {
    return EncodingUtils.testBit(__isset_bitfield, __TOTAL_ISSET_ID);
  }

  public void setTotalIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TOTAL_ISSET_ID, value);
  }

  public long getUsed() {
    return this.used;
  }

  public void setUsed(long used) {
    this.used = used;
    setUsedIsSet(true);
  }

  public void unsetUsed() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __USED_ISSET_ID);
  }

  /** Returns true if field used is set (has been assigned a value) and false otherwise */
  public boolean isSetUsed() {
    return EncodingUtils.testBit(__isset_bitfield, __USED_ISSET_ID);
  }

  public void setUsedIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __USED_ISSET_ID, value);
  }

  public long getFree() {
    return this.free;
  }

  public void setFree(long free) {
    this.free = free;
    setFreeIsSet(true);
  }

  public void unsetFree() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __FREE_ISSET_ID);
  }

  /** Returns true if field free is set (has been assigned a value) and false otherwise */
  public boolean isSetFree() {
    return EncodingUtils.testBit(__isset_bitfield, __FREE_ISSET_ID);
  }

  public void setFreeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __FREE_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case FILE_SYSTEM:
      if (value == null) {
        unsetFileSystem();
      } else {
        setFileSystem((String)value);
      }
      break;

    case MOUNTED_ON:
      if (value == null) {
        unsetMountedOn();
      } else {
        setMountedOn((String)value);
      }
      break;

    case TOTAL:
      if (value == null) {
        unsetTotal();
      } else {
        setTotal((Long)value);
      }
      break;

    case USED:
      if (value == null) {
        unsetUsed();
      } else {
        setUsed((Long)value);
      }
      break;

    case FREE:
      if (value == null) {
        unsetFree();
      } else {
        setFree((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case FILE_SYSTEM:
      return getFileSystem();

    case MOUNTED_ON:
      return getMountedOn();

    case TOTAL:
      return Long.valueOf(getTotal());

    case USED:
      return Long.valueOf(getUsed());

    case FREE:
      return Long.valueOf(getFree());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case FILE_SYSTEM:
      return isSetFileSystem();
    case MOUNTED_ON:
      return isSetMountedOn();
    case TOTAL:
      return isSetTotal();
    case USED:
      return isSetUsed();
    case FREE:
      return isSetFree();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TFileSystemInfo)
      return this.equals((TFileSystemInfo)that);
    return false;
  }

  public boolean equals(TFileSystemInfo that) {
    if (that == null)
      return false;

    boolean this_present_fileSystem = true && this.isSetFileSystem();
    boolean that_present_fileSystem = true && that.isSetFileSystem();
    if (this_present_fileSystem || that_present_fileSystem) {
      if (!(this_present_fileSystem && that_present_fileSystem))
        return false;
      if (!this.fileSystem.equals(that.fileSystem))
        return false;
    }

    boolean this_present_mountedOn = true && this.isSetMountedOn();
    boolean that_present_mountedOn = true && that.isSetMountedOn();
    if (this_present_mountedOn || that_present_mountedOn) {
      if (!(this_present_mountedOn && that_present_mountedOn))
        return false;
      if (!this.mountedOn.equals(that.mountedOn))
        return false;
    }

    boolean this_present_total = true && this.isSetTotal();
    boolean that_present_total = true && that.isSetTotal();
    if (this_present_total || that_present_total) {
      if (!(this_present_total && that_present_total))
        return false;
      if (this.total != that.total)
        return false;
    }

    boolean this_present_used = true && this.isSetUsed();
    boolean that_present_used = true && that.isSetUsed();
    if (this_present_used || that_present_used) {
      if (!(this_present_used && that_present_used))
        return false;
      if (this.used != that.used)
        return false;
    }

    boolean this_present_free = true && this.isSetFree();
    boolean that_present_free = true && that.isSetFree();
    if (this_present_free || that_present_free) {
      if (!(this_present_free && that_present_free))
        return false;
      if (this.free != that.free)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_fileSystem = true && (isSetFileSystem());
    list.add(present_fileSystem);
    if (present_fileSystem)
      list.add(fileSystem);

    boolean present_mountedOn = true && (isSetMountedOn());
    list.add(present_mountedOn);
    if (present_mountedOn)
      list.add(mountedOn);

    boolean present_total = true && (isSetTotal());
    list.add(present_total);
    if (present_total)
      list.add(total);

    boolean present_used = true && (isSetUsed());
    list.add(present_used);
    if (present_used)
      list.add(used);

    boolean present_free = true && (isSetFree());
    list.add(present_free);
    if (present_free)
      list.add(free);

    return list.hashCode();
  }

  @Override
  public int compareTo(TFileSystemInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetFileSystem()).compareTo(other.isSetFileSystem());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFileSystem()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.fileSystem, other.fileSystem);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMountedOn()).compareTo(other.isSetMountedOn());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMountedOn()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.mountedOn, other.mountedOn);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTotal()).compareTo(other.isSetTotal());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTotal()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.total, other.total);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUsed()).compareTo(other.isSetUsed());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUsed()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.used, other.used);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetFree()).compareTo(other.isSetFree());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFree()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.free, other.free);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("TFileSystemInfo(");
    boolean first = true;

    if (isSetFileSystem()) {
      sb.append("fileSystem:");
      if (this.fileSystem == null) {
        sb.append("null");
      } else {
        sb.append(this.fileSystem);
      }
      first = false;
    }
    if (isSetMountedOn()) {
      if (!first) sb.append(", ");
      sb.append("mountedOn:");
      if (this.mountedOn == null) {
        sb.append("null");
      } else {
        sb.append(this.mountedOn);
      }
      first = false;
    }
    if (isSetTotal()) {
      if (!first) sb.append(", ");
      sb.append("total:");
      sb.append(this.total);
      first = false;
    }
    if (isSetUsed()) {
      if (!first) sb.append(", ");
      sb.append("used:");
      sb.append(this.used);
      first = false;
    }
    if (isSetFree()) {
      if (!first) sb.append(", ");
      sb.append("free:");
      sb.append(this.free);
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TFileSystemInfoStandardSchemeFactory implements SchemeFactory {
    public TFileSystemInfoStandardScheme getScheme() {
      return new TFileSystemInfoStandardScheme();
    }
  }

  private static class TFileSystemInfoStandardScheme extends StandardScheme<TFileSystemInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TFileSystemInfo struct) throws TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // FILE_SYSTEM
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.fileSystem = iprot.readString();
              struct.setFileSystemIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // MOUNTED_ON
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.mountedOn = iprot.readString();
              struct.setMountedOnIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TOTAL
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.total = iprot.readI64();
              struct.setTotalIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // USED
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.used = iprot.readI64();
              struct.setUsedIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // FREE
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.free = iprot.readI64();
              struct.setFreeIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TFileSystemInfo struct) throws TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.fileSystem != null) {
        if (struct.isSetFileSystem()) {
          oprot.writeFieldBegin(FILE_SYSTEM_FIELD_DESC);
          oprot.writeString(struct.fileSystem);
          oprot.writeFieldEnd();
        }
      }
      if (struct.mountedOn != null) {
        if (struct.isSetMountedOn()) {
          oprot.writeFieldBegin(MOUNTED_ON_FIELD_DESC);
          oprot.writeString(struct.mountedOn);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetTotal()) {
        oprot.writeFieldBegin(TOTAL_FIELD_DESC);
        oprot.writeI64(struct.total);
        oprot.writeFieldEnd();
      }
      if (struct.isSetUsed()) {
        oprot.writeFieldBegin(USED_FIELD_DESC);
        oprot.writeI64(struct.used);
        oprot.writeFieldEnd();
      }
      if (struct.isSetFree()) {
        oprot.writeFieldBegin(FREE_FIELD_DESC);
        oprot.writeI64(struct.free);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TFileSystemInfoTupleSchemeFactory implements SchemeFactory {
    public TFileSystemInfoTupleScheme getScheme() {
      return new TFileSystemInfoTupleScheme();
    }
  }

  private static class TFileSystemInfoTupleScheme extends TupleScheme<TFileSystemInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TFileSystemInfo struct) throws TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetFileSystem()) {
        optionals.set(0);
      }
      if (struct.isSetMountedOn()) {
        optionals.set(1);
      }
      if (struct.isSetTotal()) {
        optionals.set(2);
      }
      if (struct.isSetUsed()) {
        optionals.set(3);
      }
      if (struct.isSetFree()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetFileSystem()) {
        oprot.writeString(struct.fileSystem);
      }
      if (struct.isSetMountedOn()) {
        oprot.writeString(struct.mountedOn);
      }
      if (struct.isSetTotal()) {
        oprot.writeI64(struct.total);
      }
      if (struct.isSetUsed()) {
        oprot.writeI64(struct.used);
      }
      if (struct.isSetFree()) {
        oprot.writeI64(struct.free);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TFileSystemInfo struct) throws TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.fileSystem = iprot.readString();
        struct.setFileSystemIsSet(true);
      }
      if (incoming.get(1)) {
        struct.mountedOn = iprot.readString();
        struct.setMountedOnIsSet(true);
      }
      if (incoming.get(2)) {
        struct.total = iprot.readI64();
        struct.setTotalIsSet(true);
      }
      if (incoming.get(3)) {
        struct.used = iprot.readI64();
        struct.setUsedIsSet(true);
      }
      if (incoming.get(4)) {
        struct.free = iprot.readI64();
        struct.setFreeIsSet(true);
      }
    }
  }

}

