```sql
BACKUP DATABASE java5
    TO DISK = '/var/opt/mssql/backups/database_backup.bak'
    WITH FORMAT, INIT;

USE master
GO
RESTORE DATABASE java5
    FROM DISK = '/var/opt/mssql/backups/database_backup.bak'
    WITH REPLACE, MOVE 'java5' TO '/var/opt/mssql/data/java5.mdf', MOVE 'java5_log' TO '/var/opt/mssql/data/java5_log.ldf';


RESTORE FILELISTONLY
    FROM DISK = '/var/opt/mssql/backups/database_backup.bak';

```