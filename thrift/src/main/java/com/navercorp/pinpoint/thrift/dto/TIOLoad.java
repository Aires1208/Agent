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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2016-7-19")
public class TIOLoad implements org.apache.thrift.TBase<TIOLoad, TIOLoad._Fields>, java.io.Serializable, Cloneable, Comparable<TIOLoad> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TIOLoad");

  private static final org.apache.thrift.protocol.TField TOTAL_FIELD_DESC = new org.apache.thrift.protocol.TField("total", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField FREE_FIELD_DESC = new org.apache.thrift.protocol.TField("free", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField USED_FIELD_DESC = new org.apache.thrift.protocol.TField("used", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField USAGE_FIELD_DESC = new org.apache.thrift.protocol.TField("usage", org.apache.thrift.protocol.TType.DOUBLE, (short)4);
  private static final org.apache.thrift.protocol.TField READS_FIELD_DESC = new org.apache.thrift.protocol.TField("reads", org.apache.thrift.protocol.TType.I64, (short)5);
  private static final org.apache.thrift.protocol.TField WRITES_FIELD_DESC = new org.apache.thrift.protocol.TField("writes", org.apache.thrift.protocol.TType.I64, (short)6);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TIOLoadStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TIOLoadTupleSchemeFactory());
  }

  private long total; // optional
  private long free; // optional
  private long used; // optional
  private double usage; // optional
  private long reads; // optional
  private long writes; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    TOTAL((short)1, "total"),
    FREE((short)2, "free"),
    USED((short)3, "used"),
    USAGE((short)4, "usage"),
    READS((short)5, "reads"),
    WRITES((short)6, "writes");

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
        case 1: // TOTAL
          return TOTAL;
        case 2: // FREE
          return FREE;
        case 3: // USED
          return USED;
        case 4: // USAGE
          return USAGE;
        case 5: // READS
          return READS;
        case 6: // WRITES
          return WRITES;
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
  private static final int __FREE_ISSET_ID = 1;
  private static final int __USED_ISSET_ID = 2;
  private static final int __USAGE_ISSET_ID = 3;
  private static final int __READS_ISSET_ID = 4;
  private static final int __WRITES_ISSET_ID = 5;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.TOTAL,_Fields.FREE,_Fields.USED,_Fields.USAGE,_Fields.READS,_Fields.WRITES};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TOTAL, new org.apache.thrift.meta_data.FieldMetaData("total", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.FREE, new org.apache.thrift.meta_data.FieldMetaData("free", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.USED, new org.apache.thrift.meta_data.FieldMetaData("used", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.USAGE, new org.apache.thrift.meta_data.FieldMetaData("usage", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.READS, new org.apache.thrift.meta_data.FieldMetaData("reads", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.WRITES, new org.apache.thrift.meta_data.FieldMetaData("writes", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TIOLoad.class, metaDataMap);
  }

  public TIOLoad() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TIOLoad(TIOLoad other) {
    __isset_bitfield = other.__isset_bitfield;
    this.total = other.total;
    this.free = other.free;
    this.used = other.used;
    this.usage = other.usage;
    this.reads = other.reads;
    this.writes = other.writes;
  }

  public TIOLoad deepCopy() {
    return new TIOLoad(this);
  }

  @Override
  public void clear() {
    setTotalIsSet(false);
    this.total = 0;
    setFreeIsSet(false);
    this.free = 0;
    setUsedIsSet(false);
    this.used = 0;
    setUsageIsSet(false);
    this.usage = 0.0;
    setReadsIsSet(false);
    this.reads = 0;
    setWritesIsSet(false);
    this.writes = 0;
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

  public double getUsage() {
    return this.usage;
  }

  public void setUsage(double usage) {
    this.usage = usage;
    setUsageIsSet(true);
  }

  public void unsetUsage() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __USAGE_ISSET_ID);
  }

  /** Returns true if field usage is set (has been assigned a value) and false otherwise */
  public boolean isSetUsage() {
    return EncodingUtils.testBit(__isset_bitfield, __USAGE_ISSET_ID);
  }

  public void setUsageIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __USAGE_ISSET_ID, value);
  }

  public long getReads() {
    return this.reads;
  }

  public void setReads(long reads) {
    this.reads = reads;
    setReadsIsSet(true);
  }

  public void unsetReads() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __READS_ISSET_ID);
  }

  /** Returns true if field reads is set (has been assigned a value) and false otherwise */
  public boolean isSetReads() {
    return EncodingUtils.testBit(__isset_bitfield, __READS_ISSET_ID);
  }

  public void setReadsIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __READS_ISSET_ID, value);
  }

  public long getWrites() {
    return this.writes;
  }

  public void setWrites(long writes) {
    this.writes = writes;
    setWritesIsSet(true);
  }

  public void unsetWrites() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __WRITES_ISSET_ID);
  }

  /** Returns true if field writes is set (has been assigned a value) and false otherwise */
  public boolean isSetWrites() {
    return EncodingUtils.testBit(__isset_bitfield, __WRITES_ISSET_ID);
  }

  public void setWritesIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __WRITES_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case TOTAL:
      if (value == null) {
        unsetTotal();
      } else {
        setTotal((Long)value);
      }
      break;

    case FREE:
      if (value == null) {
        unsetFree();
      } else {
        setFree((Long)value);
      }
      break;

    case USED:
      if (value == null) {
        unsetUsed();
      } else {
        setUsed((Long)value);
      }
      break;

    case USAGE:
      if (value == null) {
        unsetUsage();
      } else {
        setUsage((Double)value);
      }
      break;

    case READS:
      if (value == null) {
        unsetReads();
      } else {
        setReads((Long)value);
      }
      break;

    case WRITES:
      if (value == null) {
        unsetWrites();
      } else {
        setWrites((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case TOTAL:
      return Long.valueOf(getTotal());

    case FREE:
      return Long.valueOf(getFree());

    case USED:
      return Long.valueOf(getUsed());

    case USAGE:
      return Double.valueOf(getUsage());

    case READS:
      return Long.valueOf(getReads());

    case WRITES:
      return Long.valueOf(getWrites());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case TOTAL:
      return isSetTotal();
    case FREE:
      return isSetFree();
    case USED:
      return isSetUsed();
    case USAGE:
      return isSetUsage();
    case READS:
      return isSetReads();
    case WRITES:
      return isSetWrites();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TIOLoad)
      return this.equals((TIOLoad)that);
    return false;
  }

  public boolean equals(TIOLoad that) {
    if (that == null)
      return false;

    boolean this_present_total = true && this.isSetTotal();
    boolean that_present_total = true && that.isSetTotal();
    if (this_present_total || that_present_total) {
      if (!(this_present_total && that_present_total))
        return false;
      if (this.total != that.total)
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

    boolean this_present_used = true && this.isSetUsed();
    boolean that_present_used = true && that.isSetUsed();
    if (this_present_used || that_present_used) {
      if (!(this_present_used && that_present_used))
        return false;
      if (this.used != that.used)
        return false;
    }

    boolean this_present_usage = true && this.isSetUsage();
    boolean that_present_usage = true && that.isSetUsage();
    if (this_present_usage || that_present_usage) {
      if (!(this_present_usage && that_present_usage))
        return false;
      if (this.usage != that.usage)
        return false;
    }

    boolean this_present_reads = true && this.isSetReads();
    boolean that_present_reads = true && that.isSetReads();
    if (this_present_reads || that_present_reads) {
      if (!(this_present_reads && that_present_reads))
        return false;
      if (this.reads != that.reads)
        return false;
    }

    boolean this_present_writes = true && this.isSetWrites();
    boolean that_present_writes = true && that.isSetWrites();
    if (this_present_writes || that_present_writes) {
      if (!(this_present_writes && that_present_writes))
        return false;
      if (this.writes != that.writes)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_total = true && (isSetTotal());
    list.add(present_total);
    if (present_total)
      list.add(total);

    boolean present_free = true && (isSetFree());
    list.add(present_free);
    if (present_free)
      list.add(free);

    boolean present_used = true && (isSetUsed());
    list.add(present_used);
    if (present_used)
      list.add(used);

    boolean present_usage = true && (isSetUsage());
    list.add(present_usage);
    if (present_usage)
      list.add(usage);

    boolean present_reads = true && (isSetReads());
    list.add(present_reads);
    if (present_reads)
      list.add(reads);

    boolean present_writes = true && (isSetWrites());
    list.add(present_writes);
    if (present_writes)
      list.add(writes);

    return list.hashCode();
  }

  @Override
  public int compareTo(TIOLoad other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

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
    lastComparison = Boolean.valueOf(isSetUsage()).compareTo(other.isSetUsage());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUsage()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.usage, other.usage);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetReads()).compareTo(other.isSetReads());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetReads()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.reads, other.reads);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetWrites()).compareTo(other.isSetWrites());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetWrites()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.writes, other.writes);
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
    StringBuilder sb = new StringBuilder("TIOLoad(");
    boolean first = true;

    if (isSetTotal()) {
      sb.append("total:");
      sb.append(this.total);
      first = false;
    }
    if (isSetFree()) {
      if (!first) sb.append(", ");
      sb.append("free:");
      sb.append(this.free);
      first = false;
    }
    if (isSetUsed()) {
      if (!first) sb.append(", ");
      sb.append("used:");
      sb.append(this.used);
      first = false;
    }
    if (isSetUsage()) {
      if (!first) sb.append(", ");
      sb.append("usage:");
      sb.append(this.usage);
      first = false;
    }
    if (isSetReads()) {
      if (!first) sb.append(", ");
      sb.append("reads:");
      sb.append(this.reads);
      first = false;
    }
    if (isSetWrites()) {
      if (!first) sb.append(", ");
      sb.append("writes:");
      sb.append(this.writes);
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

  private static class TIOLoadStandardSchemeFactory implements SchemeFactory {
    public TIOLoadStandardScheme getScheme() {
      return new TIOLoadStandardScheme();
    }
  }

  private static class TIOLoadStandardScheme extends StandardScheme<TIOLoad> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TIOLoad struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TOTAL
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.total = iprot.readI64();
              struct.setTotalIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // FREE
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.free = iprot.readI64();
              struct.setFreeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // USED
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.used = iprot.readI64();
              struct.setUsedIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // USAGE
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.usage = iprot.readDouble();
              struct.setUsageIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // READS
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.reads = iprot.readI64();
              struct.setReadsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // WRITES
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.writes = iprot.readI64();
              struct.setWritesIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TIOLoad struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetTotal()) {
        oprot.writeFieldBegin(TOTAL_FIELD_DESC);
        oprot.writeI64(struct.total);
        oprot.writeFieldEnd();
      }
      if (struct.isSetFree()) {
        oprot.writeFieldBegin(FREE_FIELD_DESC);
        oprot.writeI64(struct.free);
        oprot.writeFieldEnd();
      }
      if (struct.isSetUsed()) {
        oprot.writeFieldBegin(USED_FIELD_DESC);
        oprot.writeI64(struct.used);
        oprot.writeFieldEnd();
      }
      if (struct.isSetUsage()) {
        oprot.writeFieldBegin(USAGE_FIELD_DESC);
        oprot.writeDouble(struct.usage);
        oprot.writeFieldEnd();
      }
      if (struct.isSetReads()) {
        oprot.writeFieldBegin(READS_FIELD_DESC);
        oprot.writeI64(struct.reads);
        oprot.writeFieldEnd();
      }
      if (struct.isSetWrites()) {
        oprot.writeFieldBegin(WRITES_FIELD_DESC);
        oprot.writeI64(struct.writes);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TIOLoadTupleSchemeFactory implements SchemeFactory {
    public TIOLoadTupleScheme getScheme() {
      return new TIOLoadTupleScheme();
    }
  }

  private static class TIOLoadTupleScheme extends TupleScheme<TIOLoad> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TIOLoad struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetTotal()) {
        optionals.set(0);
      }
      if (struct.isSetFree()) {
        optionals.set(1);
      }
      if (struct.isSetUsed()) {
        optionals.set(2);
      }
      if (struct.isSetUsage()) {
        optionals.set(3);
      }
      if (struct.isSetReads()) {
        optionals.set(4);
      }
      if (struct.isSetWrites()) {
        optionals.set(5);
      }
      oprot.writeBitSet(optionals, 6);
      if (struct.isSetTotal()) {
        oprot.writeI64(struct.total);
      }
      if (struct.isSetFree()) {
        oprot.writeI64(struct.free);
      }
      if (struct.isSetUsed()) {
        oprot.writeI64(struct.used);
      }
      if (struct.isSetUsage()) {
        oprot.writeDouble(struct.usage);
      }
      if (struct.isSetReads()) {
        oprot.writeI64(struct.reads);
      }
      if (struct.isSetWrites()) {
        oprot.writeI64(struct.writes);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TIOLoad struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(6);
      if (incoming.get(0)) {
        struct.total = iprot.readI64();
        struct.setTotalIsSet(true);
      }
      if (incoming.get(1)) {
        struct.free = iprot.readI64();
        struct.setFreeIsSet(true);
      }
      if (incoming.get(2)) {
        struct.used = iprot.readI64();
        struct.setUsedIsSet(true);
      }
      if (incoming.get(3)) {
        struct.usage = iprot.readDouble();
        struct.setUsageIsSet(true);
      }
      if (incoming.get(4)) {
        struct.reads = iprot.readI64();
        struct.setReadsIsSet(true);
      }
      if (incoming.get(5)) {
        struct.writes = iprot.readI64();
        struct.setWritesIsSet(true);
      }
    }
  }

}

