//package com.andreafueyo.tarea3DWESandreafueyo;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.Lob;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//
//import java.util.UUID;
//
//@Entity
//@Table(name = "SPRING_SESSION_ATTRIBUTES")
//public class SpringSessionAttributesEntity {
//
//    @Id
//    @Column(name = "SESSION_PRIMARY_ID")
//    private String sessionPrimaryId;
//
//    @Id
//    @Column(name = "ATTRIBUTE_NAME")
//    private String attributeName;
//
//    @Lob
//    @Column(name = "ATTRIBUTE_BYTES", nullable = false)
//    private byte[] attributeBytes;
//
//    // Relación con la tabla SPRING_SESSION (suponiendo que tienes la clase SpringSession)
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "SESSION_PRIMARY_ID", referencedColumnName = "primaryId", insertable = false, updatable = false)
//    private SpringSessionEntity springSession;
//
//    // Constructor vacío
//    public SpringSessionAttributesEntity() {
//    }
//
//    // Constructor con parámetros
//    public SpringSessionAttributesEntity(String sessionPrimaryId, String attributeName, byte[] attributeBytes) {
//        this.sessionPrimaryId = sessionPrimaryId;
//        this.attributeName = attributeName;
//        this.attributeBytes = attributeBytes;
//    }
//
//    // Getters y Setters
//    public String getSessionPrimaryId() {
//        return sessionPrimaryId;
//    }
//
//    public void setSessionPrimaryId(String sessionPrimaryId) {
//        this.sessionPrimaryId = sessionPrimaryId;
//    }
//
//    public String getAttributeName() {
//        return attributeName;
//    }
//
//    public void setAttributeName(String attributeName) {
//        this.attributeName = attributeName;
//    }
//
//    public byte[] getAttributeBytes() {
//        return attributeBytes;
//    }
//
//    public void setAttributeBytes(byte[] attributeBytes) {
//        this.attributeBytes = attributeBytes;
//    }
//
//    public SpringSessionEntity getSpringSession() {
//        return springSession;
//    }
//
//    public void setSpringSession(SpringSessionEntity springSession) {
//        this.springSession = springSession;
//    }
//
//    @Override
//    public String toString() {
//        return "SpringSessionAttributes{" +
//                "sessionPrimaryId='" + sessionPrimaryId + '\'' +
//                ", attributeName='" + attributeName + '\'' +
//                ", attributeBytes=" + (attributeBytes != null ? attributeBytes.length : 0) + " bytes" +
//                '}';
//    }
//}
