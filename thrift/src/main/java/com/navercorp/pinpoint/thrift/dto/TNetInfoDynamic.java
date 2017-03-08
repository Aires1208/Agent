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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2017-2-13")
public class TNetInfoDynamic implements org.apache.thrift.TBase<TNetInfoDynamic, TNetInfoDynamic._Fields>, java.io.Serializable, Cloneable, Comparable<TNetInfoDynamic> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TNetInfoDynamic");

  private static final org.apache.thrift.protocol.TField NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("name", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField RECEIVE_BYTES_FIELD_DESC = new org.apache.thrift.protocol.TField("receiveBytes", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField TRANSMIT_BYTES_FIELD_DESC = new org.apache.thrift.protocol.TField("transmitBytes", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField RECEIVE_ERRORS_FIELD_DESC = new org.apache.thrift.protocol.TField("receiveErrors", org.apache.thrift.protocol.TType.I64, (short)4);
  private static final org.apache.thrift.protocol.TField TRANSMIT_ERRORS_FIELD_DESC = new org.apache.thrift.protocol.TField("transmitErrors", org.apache.thrift.protocol.TType.I64, (short)5);
  private static final org.apache.thrift.protocol.TField COLLS_FIELD_DESC = new org.apache.thrift.protocol.TField("colls", org.apache.thrift.protocol.TType.I64, (short)6);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TNetInfoDynamicStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TNetInfoDynamicTupleSchemeFactory());
  }

  private String name; // optional
  private long receiveBytes; // optional
  private long transmitBytes; // optional
  private long receiveErrors; // optional
  private long transmitErrors; // optional
  private long colls; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    NAME((short)1, "name"),
    RECEIVE_BYTES((short)2, "receiveBytes"),
    TRANSMIT_BYTES((short)3, "transmitBytes"),
    RECEIVE_ERRORS((short)4, "receiveErrors"),
    TRANSMIT_ERRORS((short)5, "transmitErrors"),
    COLLS((short)6, "colls");

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
        case 1: // NAME
          return NAME;
        case 2: // RECEIVE_BYTES
          return RECEIVE_BYTES;
        case 3: // TRANSMIT_BYTES
          return TRANSMIT_BYTES;
        case 4: // RECEIVE_ERRORS
          return RECEIVE_ERRORS;
        case 5: // TRANSMIT_ERRORS
          return TRANSMIT_ERRORS;
        case 6: // COLLS
          return COLLS;
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
  private static final int __RECEIVEBYTES_ISSET_ID = 0;
  private static final int __TRANSMITBYTES_ISSET_ID = 1;
  private static final int __RECEIVEERRORS_ISSET_ID = 2;
  private static final int __TRANSMITERRORS_ISSET_ID = 3;
  private static final int __COLLS_ISSET_ID = 4;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.NAME,_Fields.RECEIVE_BYTES,_Fields.TRANSMIT_BYTES,_Fields.RECEIVE_ERRORS,_Fields.TRANSMIT_ERRORS,_Fields.COLLS};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.NAME, new org.apache.thrift.meta_data.FieldMetaData("name", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.RECEIVE_BYTES, new org.apache.thrift.meta_data.FieldMetaData("receiveBytes", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.TRANSMIT_BYTES, new org.apache.thrift.meta_data.FieldMetaData("transmitBytes", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.RECEIVE_ERRORS, new org.apache.thrift.meta_data.FieldMetaData("receiveErrors", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.TRANSMIT_ERRORS, new org.apache.thrift.meta_data.FieldMetaData("transmitErrors", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.COLLS, new org.apache.thrift.meta_data.FieldMetaData("colls", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TNetInfoDynamic.class, metaDataMap);
  }

  public TNetInfoDynamic() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TNetInfoDynamic(TNetInfoDynamic other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetName()) {
      this.name = other.name;
    }
    this.receiveBytes = other.receiveBytes;
    this.transmitBytes = other.transmitBytes;
    this.receiveErrors = other.receiveErrors;
    this.transmitErrors = other.transmitErrors;
    this.colls = other.colls;
  }

  public TNetInfoDynamic deepCopy() {
    return new TNetInfoDynamic(this);
  }

  @Override
  public void clear() {
    this.name = null;
    setReceiveBytesIsSet(false);
    this.receiveBytes = 0;
    setTransmitBytesIsSet(false);
    this.transmitBytes = 0;
    setReceiveErrorsIsSet(false);
    this.receiveErrors = 0;
    setTransmitErrorsIsSet(false);
    this.transmitErrors = 0;
    setCollsIsSet(false);
    this.colls = 0;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void unsetName() {
    this.name = null;
  }

  /** Returns true if field name is set (has been assigned a value) and false otherwise */
  public boolean isSetName() {
    return this.name != null;
  }

  public void setNameIsSet(boolean value) {
    if (!value) {
      this.name = null;
    }
  }

  public long getReceiveBytes() {
    return this.receiveBytes;
  }

  public void setReceiveBytes(long receiveBytes) {
    this.receiveBytes = receiveBytes;
    setReceiveBytesIsSet(true);
  }

  public void unsetReceiveBytes() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __RECEIVEBYTES_ISSET_ID);
  }

  /** Returns true if field receiveBytes is set (has been assigned a value) and false otherwise */
  public boolean isSetReceiveBytes() {
    return EncodingUtils.testBit(__isset_bitfield, __RECEIVEBYTES_ISSET_ID);
  }

  public void setReceiveBytesIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __RECEIVEBYTES_ISSET_ID, value);
  }

  public long getTransmitBytes() {
    return this.transmitBytes;
  }

  public void setTransmitBytes(long transmitBytes) {
    this.transmitBytes = transmitBytes;
    setTransmitBytesIsSet(true);
  }

  public void unsetTransmitBytes() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TRANSMITBYTES_ISSET_ID);
  }

  /** Returns true if field transmitBytes is set (has been assigned a value) and false otherwise */
  public boolean isSetTransmitBytes() {
    return EncodingUtils.testBit(__isset_bitfield, __TRANSMITBYTES_ISSET_ID);
  }

  public void setTransmitBytesIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TRANSMITBYTES_ISSET_ID, value);
  }

  public long getReceiveErrors() {
    return this.receiveErrors;
  }

  public void setReceiveErrors(long receiveErrors) {
    this.receiveErrors = receiveErrors;
    setReceiveErrorsIsSet(true);
  }

  public void unsetReceiveErrors() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __RECEIVEERRORS_ISSET_ID);
  }

  /** Returns true if field receiveErrors is set (has been assigned a value) and false otherwise */
  public boolean isSetReceiveErrors() {
    return EncodingUtils.testBit(__isset_bitfield, __RECEIVEERRORS_ISSET_ID);
  }

  public void setReceiveErrorsIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __RECEIVEERRORS_ISSET_ID, value);
  }

  public long getTransmitErrors() {
    return this.transmitErrors;
  }

  public void setTransmitErrors(long transmitErrors) {
    this.transmitErrors = transmitErrors;
    setTransmitErrorsIsSet(true);
  }

  public void unsetTransmitErrors() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TRANSMITERRORS_ISSET_ID);
  }

  /** Returns true if field transmitErrors is set (has been assigned a value) and false otherwise */
  public boolean isSetTransmitErrors() {
    return EncodingUtils.testBit(__isset_bitfield, __TRANSMITERRORS_ISSET_ID);
  }

  public void setTransmitErrorsIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TRANSMITERRORS_ISSET_ID, value);
  }

  public long getColls() {
    return this.colls;
  }

  public void setColls(long colls) {
    this.colls = colls;
    setCollsIsSet(true);
  }

  public void unsetColls() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __COLLS_ISSET_ID);
  }

  /** Returns true if field colls is set (has been assigned a value) and false otherwise */
  public boolean isSetColls() {
    return EncodingUtils.testBit(__isset_bitfield, __COLLS_ISSET_ID);
  }

  public void setCollsIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __COLLS_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case NAME:
      if (value == null) {
        unsetName();
      } else {
        setName((String)value);
      }
      break;

    case RECEIVE_BYTES:
      if (value == null) {
        unsetReceiveBytes();
      } else {
        setReceiveBytes((Long)value);
      }
      break;

    case TRANSMIT_BYTES:
      if (value == null) {
        unsetTransmitBytes();
      } else {
        setTransmitBytes((Long)value);
      }
      break;

    case RECEIVE_ERRORS:
      if (value == null) {
        unsetReceiveErrors();
      } else {
        setReceiveErrors((Long)value);
      }
      break;

    case TRANSMIT_ERRORS:
      if (value == null) {
        unsetTransmitErrors();
      } else {
        setTransmitErrors((Long)value);
      }
      break;

    case COLLS:
      if (value == null) {
        unsetColls();
      } else {
        setColls((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case NAME:
      return getName();

    case RECEIVE_BYTES:
      return Long.valueOf(getReceiveBytes());

    case TRANSMIT_BYTES:
      return Long.valueOf(getTransmitBytes());

    case RECEIVE_ERRORS:
      return Long.valueOf(getReceiveErrors());

    case TRANSMIT_ERRORS:
      return Long.valueOf(getTransmitErrors());

    case COLLS:
      return Long.valueOf(getColls());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case NAME:
      return isSetName();
    case RECEIVE_BYTES:
      return isSetReceiveBytes();
    case TRANSMIT_BYTES:
      return isSetTransmitBytes();
    case RECEIVE_ERRORS:
      return isSetReceiveErrors();
    case TRANSMIT_ERRORS:
      return isSetTransmitErrors();
    case COLLS:
      return isSetColls();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TNetInfoDynamic)
      return this.equals((TNetInfoDynamic)that);
    return false;
  }

  public boolean equals(TNetInfoDynamic that) {
    if (that == null)
      return false;

    boolean this_present_name = true && this.isSetName();
    boolean that_present_name = true && that.isSetName();
    if (this_present_name || that_present_name) {
      if (!(this_present_name && that_present_name))
        return false;
      if (!this.name.equals(that.name))
        return false;
    }

    boolean this_present_receiveBytes = true && this.isSetReceiveBytes();
    boolean that_present_receiveBytes = true && that.isSetReceiveBytes();
    if (this_present_receiveBytes || that_present_receiveBytes) {
      if (!(this_present_receiveBytes && that_present_receiveBytes))
        return false;
      if (this.receiveBytes != that.receiveBytes)
        return false;
    }

    boolean this_present_transmitBytes = true && this.isSetTransmitBytes();
    boolean that_present_transmitBytes = true && that.isSetTransmitBytes();
    if (this_present_transmitBytes || that_present_transmitBytes) {
      if (!(this_present_transmitBytes && that_present_transmitBytes))
        return false;
      if (this.transmitBytes != that.transmitBytes)
        return false;
    }

    boolean this_present_receiveErrors = true && this.isSetReceiveErrors();
    boolean that_present_receiveErrors = true && that.isSetReceiveErrors();
    if (this_present_receiveErrors || that_present_receiveErrors) {
      if (!(this_present_receiveErrors && that_present_receiveErrors))
        return false;
      if (this.receiveErrors != that.receiveErrors)
        return false;
    }

    boolean this_present_transmitErrors = true && this.isSetTransmitErrors();
    boolean that_present_transmitErrors = true && that.isSetTransmitErrors();
    if (this_present_transmitErrors || that_present_transmitErrors) {
      if (!(this_present_transmitErrors && that_present_transmitErrors))
        return false;
      if (this.transmitErrors != that.transmitErrors)
        return false;
    }

    boolean this_present_colls = true && this.isSetColls();
    boolean that_present_colls = true && that.isSetColls();
    if (this_present_colls || that_present_colls) {
      if (!(this_present_colls && that_present_colls))
        return false;
      if (this.colls != that.colls)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_name = true && (isSetName());
    list.add(present_name);
    if (present_name)
      list.add(name);

    boolean present_receiveBytes = true && (isSetReceiveBytes());
    list.add(present_receiveBytes);
    if (present_receiveBytes)
      list.add(receiveBytes);

    boolean present_transmitBytes = true && (isSetTransmitBytes());
    list.add(present_transmitBytes);
    if (present_transmitBytes)
      list.add(transmitBytes);

    boolean present_receiveErrors = true && (isSetReceiveErrors());
    list.add(present_receiveErrors);
    if (present_receiveErrors)
      list.add(receiveErrors);

    boolean present_transmitErrors = true && (isSetTransmitErrors());
    list.add(present_transmitErrors);
    if (present_transmitErrors)
      list.add(transmitErrors);

    boolean present_colls = true && (isSetColls());
    list.add(present_colls);
    if (present_colls)
      list.add(colls);

    return list.hashCode();
  }

  @Override
  public int compareTo(TNetInfoDynamic other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetName()).compareTo(other.isSetName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.name, other.name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetReceiveBytes()).compareTo(other.isSetReceiveBytes());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetReceiveBytes()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.receiveBytes, other.receiveBytes);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTransmitBytes()).compareTo(other.isSetTransmitBytes());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTransmitBytes()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.transmitBytes, other.transmitBytes);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetReceiveErrors()).compareTo(other.isSetReceiveErrors());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetReceiveErrors()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.receiveErrors, other.receiveErrors);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTransmitErrors()).compareTo(other.isSetTransmitErrors());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTransmitErrors()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.transmitErrors, other.transmitErrors);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetColls()).compareTo(other.isSetColls());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetColls()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.colls, other.colls);
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
    StringBuilder sb = new StringBuilder("TNetInfoDynamic(");
    boolean first = true;

    if (isSetName()) {
      sb.append("name:");
      if (this.name == null) {
        sb.append("null");
      } else {
        sb.append(this.name);
      }
      first = false;
    }
    if (isSetReceiveBytes()) {
      if (!first) sb.append(", ");
      sb.append("receiveBytes:");
      sb.append(this.receiveBytes);
      first = false;
    }
    if (isSetTransmitBytes()) {
      if (!first) sb.append(", ");
      sb.append("transmitBytes:");
      sb.append(this.transmitBytes);
      first = false;
    }
    if (isSetReceiveErrors()) {
      if (!first) sb.append(", ");
      sb.append("receiveErrors:");
      sb.append(this.receiveErrors);
      first = false;
    }
    if (isSetTransmitErrors()) {
      if (!first) sb.append(", ");
      sb.append("transmitErrors:");
      sb.append(this.transmitErrors);
      first = false;
    }
    if (isSetColls()) {
      if (!first) sb.append(", ");
      sb.append("colls:");
      sb.append(this.colls);
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

  private static class TNetInfoDynamicStandardSchemeFactory implements SchemeFactory {
    public TNetInfoDynamicStandardScheme getScheme() {
      return new TNetInfoDynamicStandardScheme();
    }
  }

  private static class TNetInfoDynamicStandardScheme extends StandardScheme<TNetInfoDynamic> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TNetInfoDynamic struct) throws TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.name = iprot.readString();
              struct.setNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // RECEIVE_BYTES
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.receiveBytes = iprot.readI64();
              struct.setReceiveBytesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TRANSMIT_BYTES
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.transmitBytes = iprot.readI64();
              struct.setTransmitBytesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // RECEIVE_ERRORS
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.receiveErrors = iprot.readI64();
              struct.setReceiveErrorsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // TRANSMIT_ERRORS
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.transmitErrors = iprot.readI64();
              struct.setTransmitErrorsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // COLLS
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.colls = iprot.readI64();
              struct.setCollsIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TNetInfoDynamic struct) throws TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.name != null) {
        if (struct.isSetName()) {
          oprot.writeFieldBegin(NAME_FIELD_DESC);
          oprot.writeString(struct.name);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetReceiveBytes()) {
        oprot.writeFieldBegin(RECEIVE_BYTES_FIELD_DESC);
        oprot.writeI64(struct.receiveBytes);
        oprot.writeFieldEnd();
      }
      if (struct.isSetTransmitBytes()) {
        oprot.writeFieldBegin(TRANSMIT_BYTES_FIELD_DESC);
        oprot.writeI64(struct.transmitBytes);
        oprot.writeFieldEnd();
      }
      if (struct.isSetReceiveErrors()) {
        oprot.writeFieldBegin(RECEIVE_ERRORS_FIELD_DESC);
        oprot.writeI64(struct.receiveErrors);
        oprot.writeFieldEnd();
      }
      if (struct.isSetTransmitErrors()) {
        oprot.writeFieldBegin(TRANSMIT_ERRORS_FIELD_DESC);
        oprot.writeI64(struct.transmitErrors);
        oprot.writeFieldEnd();
      }
      if (struct.isSetColls()) {
        oprot.writeFieldBegin(COLLS_FIELD_DESC);
        oprot.writeI64(struct.colls);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TNetInfoDynamicTupleSchemeFactory implements SchemeFactory {
    public TNetInfoDynamicTupleScheme getScheme() {
      return new TNetInfoDynamicTupleScheme();
    }
  }

  private static class TNetInfoDynamicTupleScheme extends TupleScheme<TNetInfoDynamic> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TNetInfoDynamic struct) throws TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetName()) {
        optionals.set(0);
      }
      if (struct.isSetReceiveBytes()) {
        optionals.set(1);
      }
      if (struct.isSetTransmitBytes()) {
        optionals.set(2);
      }
      if (struct.isSetReceiveErrors()) {
        optionals.set(3);
      }
      if (struct.isSetTransmitErrors()) {
        optionals.set(4);
      }
      if (struct.isSetColls()) {
        optionals.set(5);
      }
      oprot.writeBitSet(optionals, 6);
      if (struct.isSetName()) {
        oprot.writeString(struct.name);
      }
      if (struct.isSetReceiveBytes()) {
        oprot.writeI64(struct.receiveBytes);
      }
      if (struct.isSetTransmitBytes()) {
        oprot.writeI64(struct.transmitBytes);
      }
      if (struct.isSetReceiveErrors()) {
        oprot.writeI64(struct.receiveErrors);
      }
      if (struct.isSetTransmitErrors()) {
        oprot.writeI64(struct.transmitErrors);
      }
      if (struct.isSetColls()) {
        oprot.writeI64(struct.colls);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TNetInfoDynamic struct) throws TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(6);
      if (incoming.get(0)) {
        struct.name = iprot.readString();
        struct.setNameIsSet(true);
      }
      if (incoming.get(1)) {
        struct.receiveBytes = iprot.readI64();
        struct.setReceiveBytesIsSet(true);
      }
      if (incoming.get(2)) {
        struct.transmitBytes = iprot.readI64();
        struct.setTransmitBytesIsSet(true);
      }
      if (incoming.get(3)) {
        struct.receiveErrors = iprot.readI64();
        struct.setReceiveErrorsIsSet(true);
      }
      if (incoming.get(4)) {
        struct.transmitErrors = iprot.readI64();
        struct.setTransmitErrorsIsSet(true);
      }
      if (incoming.get(5)) {
        struct.colls = iprot.readI64();
        struct.setCollsIsSet(true);
      }
    }
  }

}

