ALTER TABLE pipelines ALTER COLUMN name TYPE CITEXT;
ALTER TABLE stages ALTER COLUMN pipeline_name TYPE CITEXT, ALTER COLUMN stage_name TYPE CITEXT;
ALTER TABLE jobs ALTER COLUMN pipeline_name TYPE CITEXT, ALTER COLUMN stage_name TYPE CITEXT, ALTER COLUMN job_name TYPE CITEXT;