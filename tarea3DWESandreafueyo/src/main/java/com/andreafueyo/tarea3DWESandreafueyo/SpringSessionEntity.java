//package com.andreafueyo.tarea3DWESandreafueyo;
//
//import jakarta.persistence.*;
//import java.util.Set;
//
//@Entity
//@Table(name = "SPRING_SESSION")
//public class SpringSessionEntity {
//
//    @Id
//    private String primaryId;
//    private String sessionId;
//    private Long creationTime;
//    private Long lastAccessTime;
//    private Integer maxInactiveInterval;
//    private Long expiryTime;
//    private String principalName;
//
//    // Relación con SpringSessionAttributesEntity
//    @OneToMany(mappedBy = "springSession", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<SpringSessionAttributesEntity> sessionAttributes;
//
//    // Getters y Setters
//    public String getPrimaryId() { return primaryId; }
//    public void setPrimaryId(String primaryId) { this.primaryId = primaryId; }
//
//    public String getSessionId() { return sessionId; }
//    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
//
//    public Long getCreationTime() { return creationTime; }
//    public void setCreationTime(Long creationTime) { this.creationTime = creationTime; }
//
//    public Long getLastAccessTime() { return lastAccessTime; }
//    public void setLastAccessTime(Long lastAccessTime) { this.lastAccessTime = lastAccessTime; }
//
//    public Integer getMaxInactiveInterval() { return maxInactiveInterval; }
//    public void setMaxInactiveInterval(Integer maxInactiveInterval) { this.maxInactiveInterval = maxInactiveInterval; }
//
//    public Long getExpiryTime() { return expiryTime; }
//    public void setExpiryTime(Long expiryTime) { this.expiryTime = expiryTime; }
//
//    public String getPrincipalName() { return principalName; }
//    public void setPrincipalName(String principalName) { this.principalName = principalName; }
//
//    public Set<SpringSessionAttributesEntity> getSessionAttributes() {
//        return sessionAttributes;
//    }
//
//    public void setSessionAttributes(Set<SpringSessionAttributesEntity> sessionAttributes) {
//        this.sessionAttributes = sessionAttributes;
//    }
//}
