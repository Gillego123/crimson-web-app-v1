package com.crimson.app.crimson.service;

import com.crimson.app.crimson.model.AuditLog;
import com.crimson.app.crimson.repository.AuditLogRepository;
import com.crimson.app.crimson.service.imp.IAuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditLogServiceImp implements IAuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Override
    public void logAction(String action, String performedBy, String entityType, Long entityId, String details) {
        try {
            AuditLog auditLog = new AuditLog(action, performedBy, entityType, entityId, details);
            auditLogRepository.save(auditLog);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<AuditLog> getLogsForEntity(String entityType, Long entityId) {
        return auditLogRepository.findByEntityTypeAndEntityId(entityType,entityId);
    }
}
