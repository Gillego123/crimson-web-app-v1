package com.crimson.app.crimson.service.imp;


import com.crimson.app.crimson.model.AuditLog;

import java.util.List;

public interface IAuditLogService {
    void logAction(String action, String performedBy, String entityType, Long entityId, String details);
    List<AuditLog> getLogsForEntity(String entityType, Long entityId);
}
