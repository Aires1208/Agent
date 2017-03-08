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
public class TAgentStatBatch implements org.apache.thrift.TBase<TAgentStatBatch, TAgentStatBatch._Fields>, java.io.Serializable, Cloneable, Comparable<TAgentStatBatch> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TAgentStatBatch");

  private static final org.apache.thrift.protocol.TField AGENT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("agentId", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField START_TIMESTAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("startTimestamp", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField AGENT_STATS_FIELD_DESC = new org.apache.thrift.protocol.TField("agentStats", org.apache.thrift.protocol.TType.LIST, (short)10);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TAgentStatBatchStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TAgentStatBatchTupleSchemeFactory());
  }

  private String agentId; // required
  private long startTimestamp; // required
  private List<TAgentStat> agentStats; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    AGENT_ID((short)1, "agentId"),
    START_TIMESTAMP((short)2, "startTimestamp"),
    AGENT_STATS((short)10, "agentStats");

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
        case 1: // AGENT_ID
          return AGENT_ID;
        case 2: // START_TIMESTAMP
          return START_TIMESTAMP;
        case 10: // AGENT_STATS
          return AGENT_STATS;
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
  private static final int __STARTTIMESTAMP_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.AGENT_ID, new org.apache.thrift.meta_data.FieldMetaData("agentId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.START_TIMESTAMP, new org.apache.thrift.meta_data.FieldMetaData("startTimestamp", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.AGENT_STATS, new org.apache.thrift.meta_data.FieldMetaData("agentStats", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TAgentStat.class))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TAgentStatBatch.class, metaDataMap);
  }

  public TAgentStatBatch() {
  }

  public TAgentStatBatch(
    String agentId,
    long startTimestamp,
    List<TAgentStat> agentStats)
  {
    this();
    this.agentId = agentId;
    this.startTimestamp = startTimestamp;
    setStartTimestampIsSet(true);
    this.agentStats = agentStats;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TAgentStatBatch(TAgentStatBatch other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetAgentId()) {
      this.agentId = other.agentId;
    }
    this.startTimestamp = other.startTimestamp;
    if (other.isSetAgentStats()) {
      List<TAgentStat> __this__agentStats = new ArrayList<TAgentStat>(other.agentStats.size());
      for (TAgentStat other_element : other.agentStats) {
        __this__agentStats.add(new TAgentStat(other_element));
      }
      this.agentStats = __this__agentStats;
    }
  }

  public TAgentStatBatch deepCopy() {
    return new TAgentStatBatch(this);
  }

  @Override
  public void clear() {
    this.agentId = null;
    setStartTimestampIsSet(false);
    this.startTimestamp = 0;
    this.agentStats = null;
  }

  public String getAgentId() {
    return this.agentId;
  }

  public void setAgentId(String agentId) {
    this.agentId = agentId;
  }

  public void unsetAgentId() {
    this.agentId = null;
  }

  /** Returns true if field agentId is set (has been assigned a value) and false otherwise */
  public boolean isSetAgentId() {
    return this.agentId != null;
  }

  public void setAgentIdIsSet(boolean value) {
    if (!value) {
      this.agentId = null;
    }
  }

  public long getStartTimestamp() {
    return this.startTimestamp;
  }

  public void setStartTimestamp(long startTimestamp) {
    this.startTimestamp = startTimestamp;
    setStartTimestampIsSet(true);
  }

  public void unsetStartTimestamp() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __STARTTIMESTAMP_ISSET_ID);
  }

  /** Returns true if field startTimestamp is set (has been assigned a value) and false otherwise */
  public boolean isSetStartTimestamp() {
    return EncodingUtils.testBit(__isset_bitfield, __STARTTIMESTAMP_ISSET_ID);
  }

  public void setStartTimestampIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __STARTTIMESTAMP_ISSET_ID, value);
  }

  public int getAgentStatsSize() {
    return (this.agentStats == null) ? 0 : this.agentStats.size();
  }

  public java.util.Iterator<TAgentStat> getAgentStatsIterator() {
    return (this.agentStats == null) ? null : this.agentStats.iterator();
  }

  public void addToAgentStats(TAgentStat elem) {
    if (this.agentStats == null) {
      this.agentStats = new ArrayList<TAgentStat>();
    }
    this.agentStats.add(elem);
  }

  public List<TAgentStat> getAgentStats() {
    return this.agentStats;
  }

  public void setAgentStats(List<TAgentStat> agentStats) {
    this.agentStats = agentStats;
  }

  public void unsetAgentStats() {
    this.agentStats = null;
  }

  /** Returns true if field agentStats is set (has been assigned a value) and false otherwise */
  public boolean isSetAgentStats() {
    return this.agentStats != null;
  }

  public void setAgentStatsIsSet(boolean value) {
    if (!value) {
      this.agentStats = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case AGENT_ID:
      if (value == null) {
        unsetAgentId();
      } else {
        setAgentId((String)value);
      }
      break;

    case START_TIMESTAMP:
      if (value == null) {
        unsetStartTimestamp();
      } else {
        setStartTimestamp((Long)value);
      }
      break;

    case AGENT_STATS:
      if (value == null) {
        unsetAgentStats();
      } else {
        setAgentStats((List<TAgentStat>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case AGENT_ID:
      return getAgentId();

    case START_TIMESTAMP:
      return Long.valueOf(getStartTimestamp());

    case AGENT_STATS:
      return getAgentStats();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case AGENT_ID:
      return isSetAgentId();
    case START_TIMESTAMP:
      return isSetStartTimestamp();
    case AGENT_STATS:
      return isSetAgentStats();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TAgentStatBatch)
      return this.equals((TAgentStatBatch)that);
    return false;
  }

  public boolean equals(TAgentStatBatch that) {
    if (that == null)
      return false;

    boolean this_present_agentId = true && this.isSetAgentId();
    boolean that_present_agentId = true && that.isSetAgentId();
    if (this_present_agentId || that_present_agentId) {
      if (!(this_present_agentId && that_present_agentId))
        return false;
      if (!this.agentId.equals(that.agentId))
        return false;
    }

    boolean this_present_startTimestamp = true;
    boolean that_present_startTimestamp = true;
    if (this_present_startTimestamp || that_present_startTimestamp) {
      if (!(this_present_startTimestamp && that_present_startTimestamp))
        return false;
      if (this.startTimestamp != that.startTimestamp)
        return false;
    }

    boolean this_present_agentStats = true && this.isSetAgentStats();
    boolean that_present_agentStats = true && that.isSetAgentStats();
    if (this_present_agentStats || that_present_agentStats) {
      if (!(this_present_agentStats && that_present_agentStats))
        return false;
      if (!this.agentStats.equals(that.agentStats))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_agentId = true && (isSetAgentId());
    list.add(present_agentId);
    if (present_agentId)
      list.add(agentId);

    boolean present_startTimestamp = true;
    list.add(present_startTimestamp);
    if (present_startTimestamp)
      list.add(startTimestamp);

    boolean present_agentStats = true && (isSetAgentStats());
    list.add(present_agentStats);
    if (present_agentStats)
      list.add(agentStats);

    return list.hashCode();
  }

  @Override
  public int compareTo(TAgentStatBatch other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetAgentId()).compareTo(other.isSetAgentId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAgentId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.agentId, other.agentId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetStartTimestamp()).compareTo(other.isSetStartTimestamp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStartTimestamp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.startTimestamp, other.startTimestamp);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAgentStats()).compareTo(other.isSetAgentStats());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAgentStats()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.agentStats, other.agentStats);
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
    StringBuilder sb = new StringBuilder("TAgentStatBatch(");
    boolean first = true;

    sb.append("agentId:");
    if (this.agentId == null) {
      sb.append("null");
    } else {
      sb.append(this.agentId);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("startTimestamp:");
    sb.append(this.startTimestamp);
    first = false;
    if (!first) sb.append(", ");
    sb.append("agentStats:");
    if (this.agentStats == null) {
      sb.append("null");
    } else {
      sb.append(this.agentStats);
    }
    first = false;
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

  private static class TAgentStatBatchStandardSchemeFactory implements SchemeFactory {
    public TAgentStatBatchStandardScheme getScheme() {
      return new TAgentStatBatchStandardScheme();
    }
  }

  private static class TAgentStatBatchStandardScheme extends StandardScheme<TAgentStatBatch> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TAgentStatBatch struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // AGENT_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.agentId = iprot.readString();
              struct.setAgentIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // START_TIMESTAMP
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.startTimestamp = iprot.readI64();
              struct.setStartTimestampIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 10: // AGENT_STATS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list32 = iprot.readListBegin();
                struct.agentStats = new ArrayList<TAgentStat>(_list32.size);
                TAgentStat _elem33;
                for (int _i34 = 0; _i34 < _list32.size; ++_i34)
                {
                  _elem33 = new TAgentStat();
                  _elem33.read(iprot);
                  struct.agentStats.add(_elem33);
                }
                iprot.readListEnd();
              }
              struct.setAgentStatsIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TAgentStatBatch struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.agentId != null) {
        oprot.writeFieldBegin(AGENT_ID_FIELD_DESC);
        oprot.writeString(struct.agentId);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(START_TIMESTAMP_FIELD_DESC);
      oprot.writeI64(struct.startTimestamp);
      oprot.writeFieldEnd();
      if (struct.agentStats != null) {
        oprot.writeFieldBegin(AGENT_STATS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.agentStats.size()));
          for (TAgentStat _iter35 : struct.agentStats)
          {
            _iter35.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TAgentStatBatchTupleSchemeFactory implements SchemeFactory {
    public TAgentStatBatchTupleScheme getScheme() {
      return new TAgentStatBatchTupleScheme();
    }
  }

  private static class TAgentStatBatchTupleScheme extends TupleScheme<TAgentStatBatch> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TAgentStatBatch struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetAgentId()) {
        optionals.set(0);
      }
      if (struct.isSetStartTimestamp()) {
        optionals.set(1);
      }
      if (struct.isSetAgentStats()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetAgentId()) {
        oprot.writeString(struct.agentId);
      }
      if (struct.isSetStartTimestamp()) {
        oprot.writeI64(struct.startTimestamp);
      }
      if (struct.isSetAgentStats()) {
        {
          oprot.writeI32(struct.agentStats.size());
          for (TAgentStat _iter36 : struct.agentStats)
          {
            _iter36.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TAgentStatBatch struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.agentId = iprot.readString();
        struct.setAgentIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.startTimestamp = iprot.readI64();
        struct.setStartTimestampIsSet(true);
      }
      if (incoming.get(2)) {
        {
          org.apache.thrift.protocol.TList _list37 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.agentStats = new ArrayList<TAgentStat>(_list37.size);
          TAgentStat _elem38;
          for (int _i39 = 0; _i39 < _list37.size; ++_i39)
          {
            _elem38 = new TAgentStat();
            _elem38.read(iprot);
            struct.agentStats.add(_elem38);
          }
        }
        struct.setAgentStatsIsSet(true);
      }
    }
  }

}

