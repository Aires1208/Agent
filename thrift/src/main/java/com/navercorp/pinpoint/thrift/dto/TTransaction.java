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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2015-10-29")
public class TTransaction implements org.apache.thrift.TBase<TTransaction, TTransaction._Fields>, java.io.Serializable, Cloneable, Comparable<TTransaction> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TTransaction");

  private static final org.apache.thrift.protocol.TField SAMPLED_NEW_COUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("sampledNewCount", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField SAMPLED_CONTINUATION_COUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("sampledContinuationCount", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField UNSAMPLED_NEW_COUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("unsampledNewCount", org.apache.thrift.protocol.TType.I64, (short)4);
  private static final org.apache.thrift.protocol.TField UNSAMPLED_CONTINUATION_COUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("unsampledContinuationCount", org.apache.thrift.protocol.TType.I64, (short)5);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TTransactionStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TTransactionTupleSchemeFactory());
  }

  private long sampledNewCount; // optional
  private long sampledContinuationCount; // optional
  private long unsampledNewCount; // optional
  private long unsampledContinuationCount; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SAMPLED_NEW_COUNT((short)2, "sampledNewCount"),
    SAMPLED_CONTINUATION_COUNT((short)3, "sampledContinuationCount"),
    UNSAMPLED_NEW_COUNT((short)4, "unsampledNewCount"),
    UNSAMPLED_CONTINUATION_COUNT((short)5, "unsampledContinuationCount");

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
        case 2: // SAMPLED_NEW_COUNT
          return SAMPLED_NEW_COUNT;
        case 3: // SAMPLED_CONTINUATION_COUNT
          return SAMPLED_CONTINUATION_COUNT;
        case 4: // UNSAMPLED_NEW_COUNT
          return UNSAMPLED_NEW_COUNT;
        case 5: // UNSAMPLED_CONTINUATION_COUNT
          return UNSAMPLED_CONTINUATION_COUNT;
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
  private static final int __SAMPLEDNEWCOUNT_ISSET_ID = 0;
  private static final int __SAMPLEDCONTINUATIONCOUNT_ISSET_ID = 1;
  private static final int __UNSAMPLEDNEWCOUNT_ISSET_ID = 2;
  private static final int __UNSAMPLEDCONTINUATIONCOUNT_ISSET_ID = 3;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.SAMPLED_NEW_COUNT,_Fields.SAMPLED_CONTINUATION_COUNT,_Fields.UNSAMPLED_NEW_COUNT,_Fields.UNSAMPLED_CONTINUATION_COUNT};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SAMPLED_NEW_COUNT, new org.apache.thrift.meta_data.FieldMetaData("sampledNewCount", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.SAMPLED_CONTINUATION_COUNT, new org.apache.thrift.meta_data.FieldMetaData("sampledContinuationCount", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.UNSAMPLED_NEW_COUNT, new org.apache.thrift.meta_data.FieldMetaData("unsampledNewCount", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.UNSAMPLED_CONTINUATION_COUNT, new org.apache.thrift.meta_data.FieldMetaData("unsampledContinuationCount", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TTransaction.class, metaDataMap);
  }

  public TTransaction() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TTransaction(TTransaction other) {
    __isset_bitfield = other.__isset_bitfield;
    this.sampledNewCount = other.sampledNewCount;
    this.sampledContinuationCount = other.sampledContinuationCount;
    this.unsampledNewCount = other.unsampledNewCount;
    this.unsampledContinuationCount = other.unsampledContinuationCount;
  }

  public TTransaction deepCopy() {
    return new TTransaction(this);
  }

  @Override
  public void clear() {
    setSampledNewCountIsSet(false);
    this.sampledNewCount = 0;
    setSampledContinuationCountIsSet(false);
    this.sampledContinuationCount = 0;
    setUnsampledNewCountIsSet(false);
    this.unsampledNewCount = 0;
    setUnsampledContinuationCountIsSet(false);
    this.unsampledContinuationCount = 0;
  }

  public long getSampledNewCount() {
    return this.sampledNewCount;
  }

  public void setSampledNewCount(long sampledNewCount) {
    this.sampledNewCount = sampledNewCount;
    setSampledNewCountIsSet(true);
  }

  public void unsetSampledNewCount() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __SAMPLEDNEWCOUNT_ISSET_ID);
  }

  /** Returns true if field sampledNewCount is set (has been assigned a value) and false otherwise */
  public boolean isSetSampledNewCount() {
    return EncodingUtils.testBit(__isset_bitfield, __SAMPLEDNEWCOUNT_ISSET_ID);
  }

  public void setSampledNewCountIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __SAMPLEDNEWCOUNT_ISSET_ID, value);
  }

  public long getSampledContinuationCount() {
    return this.sampledContinuationCount;
  }

  public void setSampledContinuationCount(long sampledContinuationCount) {
    this.sampledContinuationCount = sampledContinuationCount;
    setSampledContinuationCountIsSet(true);
  }

  public void unsetSampledContinuationCount() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __SAMPLEDCONTINUATIONCOUNT_ISSET_ID);
  }

  /** Returns true if field sampledContinuationCount is set (has been assigned a value) and false otherwise */
  public boolean isSetSampledContinuationCount() {
    return EncodingUtils.testBit(__isset_bitfield, __SAMPLEDCONTINUATIONCOUNT_ISSET_ID);
  }

  public void setSampledContinuationCountIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __SAMPLEDCONTINUATIONCOUNT_ISSET_ID, value);
  }

  public long getUnsampledNewCount() {
    return this.unsampledNewCount;
  }

  public void setUnsampledNewCount(long unsampledNewCount) {
    this.unsampledNewCount = unsampledNewCount;
    setUnsampledNewCountIsSet(true);
  }

  public void unsetUnsampledNewCount() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __UNSAMPLEDNEWCOUNT_ISSET_ID);
  }

  /** Returns true if field unsampledNewCount is set (has been assigned a value) and false otherwise */
  public boolean isSetUnsampledNewCount() {
    return EncodingUtils.testBit(__isset_bitfield, __UNSAMPLEDNEWCOUNT_ISSET_ID);
  }

  public void setUnsampledNewCountIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __UNSAMPLEDNEWCOUNT_ISSET_ID, value);
  }

  public long getUnsampledContinuationCount() {
    return this.unsampledContinuationCount;
  }

  public void setUnsampledContinuationCount(long unsampledContinuationCount) {
    this.unsampledContinuationCount = unsampledContinuationCount;
    setUnsampledContinuationCountIsSet(true);
  }

  public void unsetUnsampledContinuationCount() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __UNSAMPLEDCONTINUATIONCOUNT_ISSET_ID);
  }

  /** Returns true if field unsampledContinuationCount is set (has been assigned a value) and false otherwise */
  public boolean isSetUnsampledContinuationCount() {
    return EncodingUtils.testBit(__isset_bitfield, __UNSAMPLEDCONTINUATIONCOUNT_ISSET_ID);
  }

  public void setUnsampledContinuationCountIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __UNSAMPLEDCONTINUATIONCOUNT_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case SAMPLED_NEW_COUNT:
      if (value == null) {
        unsetSampledNewCount();
      } else {
        setSampledNewCount((Long)value);
      }
      break;

    case SAMPLED_CONTINUATION_COUNT:
      if (value == null) {
        unsetSampledContinuationCount();
      } else {
        setSampledContinuationCount((Long)value);
      }
      break;

    case UNSAMPLED_NEW_COUNT:
      if (value == null) {
        unsetUnsampledNewCount();
      } else {
        setUnsampledNewCount((Long)value);
      }
      break;

    case UNSAMPLED_CONTINUATION_COUNT:
      if (value == null) {
        unsetUnsampledContinuationCount();
      } else {
        setUnsampledContinuationCount((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case SAMPLED_NEW_COUNT:
      return Long.valueOf(getSampledNewCount());

    case SAMPLED_CONTINUATION_COUNT:
      return Long.valueOf(getSampledContinuationCount());

    case UNSAMPLED_NEW_COUNT:
      return Long.valueOf(getUnsampledNewCount());

    case UNSAMPLED_CONTINUATION_COUNT:
      return Long.valueOf(getUnsampledContinuationCount());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case SAMPLED_NEW_COUNT:
      return isSetSampledNewCount();
    case SAMPLED_CONTINUATION_COUNT:
      return isSetSampledContinuationCount();
    case UNSAMPLED_NEW_COUNT:
      return isSetUnsampledNewCount();
    case UNSAMPLED_CONTINUATION_COUNT:
      return isSetUnsampledContinuationCount();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TTransaction)
      return this.equals((TTransaction)that);
    return false;
  }

  public boolean equals(TTransaction that) {
    if (that == null)
      return false;

    boolean this_present_sampledNewCount = true && this.isSetSampledNewCount();
    boolean that_present_sampledNewCount = true && that.isSetSampledNewCount();
    if (this_present_sampledNewCount || that_present_sampledNewCount) {
      if (!(this_present_sampledNewCount && that_present_sampledNewCount))
        return false;
      if (this.sampledNewCount != that.sampledNewCount)
        return false;
    }

    boolean this_present_sampledContinuationCount = true && this.isSetSampledContinuationCount();
    boolean that_present_sampledContinuationCount = true && that.isSetSampledContinuationCount();
    if (this_present_sampledContinuationCount || that_present_sampledContinuationCount) {
      if (!(this_present_sampledContinuationCount && that_present_sampledContinuationCount))
        return false;
      if (this.sampledContinuationCount != that.sampledContinuationCount)
        return false;
    }

    boolean this_present_unsampledNewCount = true && this.isSetUnsampledNewCount();
    boolean that_present_unsampledNewCount = true && that.isSetUnsampledNewCount();
    if (this_present_unsampledNewCount || that_present_unsampledNewCount) {
      if (!(this_present_unsampledNewCount && that_present_unsampledNewCount))
        return false;
      if (this.unsampledNewCount != that.unsampledNewCount)
        return false;
    }

    boolean this_present_unsampledContinuationCount = true && this.isSetUnsampledContinuationCount();
    boolean that_present_unsampledContinuationCount = true && that.isSetUnsampledContinuationCount();
    if (this_present_unsampledContinuationCount || that_present_unsampledContinuationCount) {
      if (!(this_present_unsampledContinuationCount && that_present_unsampledContinuationCount))
        return false;
      if (this.unsampledContinuationCount != that.unsampledContinuationCount)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_sampledNewCount = true && (isSetSampledNewCount());
    list.add(present_sampledNewCount);
    if (present_sampledNewCount)
      list.add(sampledNewCount);

    boolean present_sampledContinuationCount = true && (isSetSampledContinuationCount());
    list.add(present_sampledContinuationCount);
    if (present_sampledContinuationCount)
      list.add(sampledContinuationCount);

    boolean present_unsampledNewCount = true && (isSetUnsampledNewCount());
    list.add(present_unsampledNewCount);
    if (present_unsampledNewCount)
      list.add(unsampledNewCount);

    boolean present_unsampledContinuationCount = true && (isSetUnsampledContinuationCount());
    list.add(present_unsampledContinuationCount);
    if (present_unsampledContinuationCount)
      list.add(unsampledContinuationCount);

    return list.hashCode();
  }

  @Override
  public int compareTo(TTransaction other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetSampledNewCount()).compareTo(other.isSetSampledNewCount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSampledNewCount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.sampledNewCount, other.sampledNewCount);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSampledContinuationCount()).compareTo(other.isSetSampledContinuationCount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSampledContinuationCount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.sampledContinuationCount, other.sampledContinuationCount);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUnsampledNewCount()).compareTo(other.isSetUnsampledNewCount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUnsampledNewCount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.unsampledNewCount, other.unsampledNewCount);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUnsampledContinuationCount()).compareTo(other.isSetUnsampledContinuationCount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUnsampledContinuationCount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.unsampledContinuationCount, other.unsampledContinuationCount);
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
    StringBuilder sb = new StringBuilder("TTransaction(");
    boolean first = true;

    if (isSetSampledNewCount()) {
      sb.append("sampledNewCount:");
      sb.append(this.sampledNewCount);
      first = false;
    }
    if (isSetSampledContinuationCount()) {
      if (!first) sb.append(", ");
      sb.append("sampledContinuationCount:");
      sb.append(this.sampledContinuationCount);
      first = false;
    }
    if (isSetUnsampledNewCount()) {
      if (!first) sb.append(", ");
      sb.append("unsampledNewCount:");
      sb.append(this.unsampledNewCount);
      first = false;
    }
    if (isSetUnsampledContinuationCount()) {
      if (!first) sb.append(", ");
      sb.append("unsampledContinuationCount:");
      sb.append(this.unsampledContinuationCount);
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

  private static class TTransactionStandardSchemeFactory implements SchemeFactory {
    public TTransactionStandardScheme getScheme() {
      return new TTransactionStandardScheme();
    }
  }

  private static class TTransactionStandardScheme extends StandardScheme<TTransaction> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TTransaction struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 2: // SAMPLED_NEW_COUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.sampledNewCount = iprot.readI64();
              struct.setSampledNewCountIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // SAMPLED_CONTINUATION_COUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.sampledContinuationCount = iprot.readI64();
              struct.setSampledContinuationCountIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // UNSAMPLED_NEW_COUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.unsampledNewCount = iprot.readI64();
              struct.setUnsampledNewCountIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // UNSAMPLED_CONTINUATION_COUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.unsampledContinuationCount = iprot.readI64();
              struct.setUnsampledContinuationCountIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TTransaction struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetSampledNewCount()) {
        oprot.writeFieldBegin(SAMPLED_NEW_COUNT_FIELD_DESC);
        oprot.writeI64(struct.sampledNewCount);
        oprot.writeFieldEnd();
      }
      if (struct.isSetSampledContinuationCount()) {
        oprot.writeFieldBegin(SAMPLED_CONTINUATION_COUNT_FIELD_DESC);
        oprot.writeI64(struct.sampledContinuationCount);
        oprot.writeFieldEnd();
      }
      if (struct.isSetUnsampledNewCount()) {
        oprot.writeFieldBegin(UNSAMPLED_NEW_COUNT_FIELD_DESC);
        oprot.writeI64(struct.unsampledNewCount);
        oprot.writeFieldEnd();
      }
      if (struct.isSetUnsampledContinuationCount()) {
        oprot.writeFieldBegin(UNSAMPLED_CONTINUATION_COUNT_FIELD_DESC);
        oprot.writeI64(struct.unsampledContinuationCount);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TTransactionTupleSchemeFactory implements SchemeFactory {
    public TTransactionTupleScheme getScheme() {
      return new TTransactionTupleScheme();
    }
  }

  private static class TTransactionTupleScheme extends TupleScheme<TTransaction> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TTransaction struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetSampledNewCount()) {
        optionals.set(0);
      }
      if (struct.isSetSampledContinuationCount()) {
        optionals.set(1);
      }
      if (struct.isSetUnsampledNewCount()) {
        optionals.set(2);
      }
      if (struct.isSetUnsampledContinuationCount()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetSampledNewCount()) {
        oprot.writeI64(struct.sampledNewCount);
      }
      if (struct.isSetSampledContinuationCount()) {
        oprot.writeI64(struct.sampledContinuationCount);
      }
      if (struct.isSetUnsampledNewCount()) {
        oprot.writeI64(struct.unsampledNewCount);
      }
      if (struct.isSetUnsampledContinuationCount()) {
        oprot.writeI64(struct.unsampledContinuationCount);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TTransaction struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.sampledNewCount = iprot.readI64();
        struct.setSampledNewCountIsSet(true);
      }
      if (incoming.get(1)) {
        struct.sampledContinuationCount = iprot.readI64();
        struct.setSampledContinuationCountIsSet(true);
      }
      if (incoming.get(2)) {
        struct.unsampledNewCount = iprot.readI64();
        struct.setUnsampledNewCountIsSet(true);
      }
      if (incoming.get(3)) {
        struct.unsampledContinuationCount = iprot.readI64();
        struct.setUnsampledContinuationCountIsSet(true);
      }
    }
  }

}

