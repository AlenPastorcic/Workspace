-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER final_demo_owner
WITH PASSWORD 'finaldemo';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO final_demo_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO final_demo_owner;

CREATE USER final_demo_appuser
WITH PASSWORD 'finaldemo';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO final_demo_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO final_demo_appuser;
